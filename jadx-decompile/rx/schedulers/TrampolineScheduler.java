package rx.schedulers;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;

public final class TrampolineScheduler extends Scheduler {
    private static final TrampolineScheduler INSTANCE;

    private static class InnerCurrentThreadScheduler extends Worker implements Subscription {
        final AtomicInteger counter;
        private final BooleanSubscription innerSubscription;
        final PriorityBlockingQueue<TimedAction> queue;
        private final AtomicInteger wip;

        /* renamed from: rx.schedulers.TrampolineScheduler.InnerCurrentThreadScheduler.1 */
        class AnonymousClass1 implements Action0 {
            final /* synthetic */ TimedAction val$timedAction;

            AnonymousClass1(TimedAction timedAction) {
                this.val$timedAction = timedAction;
            }

            public void call() {
                InnerCurrentThreadScheduler.this.queue.remove(this.val$timedAction);
            }
        }

        InnerCurrentThreadScheduler() {
            this.counter = new AtomicInteger();
            this.queue = new PriorityBlockingQueue();
            this.innerSubscription = new BooleanSubscription();
            this.wip = new AtomicInteger();
        }

        public Subscription schedule(Action0 action) {
            return enqueue(action, now());
        }

        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            long execTime = now() + unit.toMillis(delayTime);
            return enqueue(new SleepingAction(action, this, execTime), execTime);
        }

        private Subscription enqueue(Action0 action, long execTime) {
            if (this.innerSubscription.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            TimedAction timedAction = new TimedAction(action, Long.valueOf(execTime), this.counter.incrementAndGet());
            this.queue.add(timedAction);
            if (this.wip.getAndIncrement() != 0) {
                return Subscriptions.create(new AnonymousClass1(timedAction));
            }
            do {
                TimedAction polled = (TimedAction) this.queue.poll();
                if (polled != null) {
                    polled.action.call();
                }
            } while (this.wip.decrementAndGet() > 0);
            return Subscriptions.unsubscribed();
        }

        public void unsubscribe() {
            this.innerSubscription.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }
    }

    private static final class TimedAction implements Comparable<TimedAction> {
        final Action0 action;
        final int count;
        final Long execTime;

        TimedAction(Action0 action, Long execTime, int count) {
            this.action = action;
            this.execTime = execTime;
            this.count = count;
        }

        public int compareTo(TimedAction that) {
            int result = this.execTime.compareTo(that.execTime);
            if (result == 0) {
                return TrampolineScheduler.compare(this.count, that.count);
            }
            return result;
        }
    }

    static {
        INSTANCE = new TrampolineScheduler();
    }

    static TrampolineScheduler instance() {
        return INSTANCE;
    }

    public Worker createWorker() {
        return new InnerCurrentThreadScheduler();
    }

    TrampolineScheduler() {
    }

    static int compare(int x, int y) {
        if (x < y) {
            return -1;
        }
        return x == y ? 0 : 1;
    }
}
