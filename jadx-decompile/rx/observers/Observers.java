package rx.observers;

import rx.Observer;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;

public final class Observers {
    private static final Observer<Object> EMPTY;

    /* renamed from: rx.observers.Observers.2 */
    static class AnonymousClass2 implements Observer<T> {
        final /* synthetic */ Action1 val$onNext;

        AnonymousClass2(Action1 action1) {
            this.val$onNext = action1;
        }

        public final void onCompleted() {
        }

        public final void onError(Throwable e) {
            throw new OnErrorNotImplementedException(e);
        }

        public final void onNext(T args) {
            this.val$onNext.call(args);
        }
    }

    /* renamed from: rx.observers.Observers.3 */
    static class AnonymousClass3 implements Observer<T> {
        final /* synthetic */ Action1 val$onError;
        final /* synthetic */ Action1 val$onNext;

        AnonymousClass3(Action1 action1, Action1 action12) {
            this.val$onError = action1;
            this.val$onNext = action12;
        }

        public final void onCompleted() {
        }

        public final void onError(Throwable e) {
            this.val$onError.call(e);
        }

        public final void onNext(T args) {
            this.val$onNext.call(args);
        }
    }

    /* renamed from: rx.observers.Observers.4 */
    static class AnonymousClass4 implements Observer<T> {
        final /* synthetic */ Action0 val$onComplete;
        final /* synthetic */ Action1 val$onError;
        final /* synthetic */ Action1 val$onNext;

        AnonymousClass4(Action0 action0, Action1 action1, Action1 action12) {
            this.val$onComplete = action0;
            this.val$onError = action1;
            this.val$onNext = action12;
        }

        public final void onCompleted() {
            this.val$onComplete.call();
        }

        public final void onError(Throwable e) {
            this.val$onError.call(e);
        }

        public final void onNext(T args) {
            this.val$onNext.call(args);
        }
    }

    private Observers() {
        throw new IllegalStateException("No instances!");
    }

    static {
        EMPTY = new Observer<Object>() {
            public final void onCompleted() {
            }

            public final void onError(Throwable e) {
                throw new OnErrorNotImplementedException(e);
            }

            public final void onNext(Object args) {
            }
        };
    }

    public static <T> Observer<T> empty() {
        return EMPTY;
    }

    public static <T> Observer<T> create(Action1<? super T> onNext) {
        if (onNext != null) {
            return new AnonymousClass2(onNext);
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> Observer<T> create(Action1<? super T> onNext, Action1<Throwable> onError) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (onError != null) {
            return new AnonymousClass3(onError, onNext);
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    public static <T> Observer<T> create(Action1<? super T> onNext, Action1<Throwable> onError, Action0 onComplete) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (onError == null) {
            throw new IllegalArgumentException("onError can not be null");
        } else if (onComplete != null) {
            return new AnonymousClass4(onComplete, onError, onNext);
        } else {
            throw new IllegalArgumentException("onComplete can not be null");
        }
    }
}
