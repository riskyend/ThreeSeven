package rx.observables;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscription;
import rx.annotations.Beta;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.OnSubscribeAutoConnect;
import rx.internal.operators.OnSubscribeRefCount;

public abstract class ConnectableObservable<T> extends Observable<T> {

    /* renamed from: rx.observables.ConnectableObservable.1 */
    class AnonymousClass1 implements Action1<Subscription> {
        final /* synthetic */ Subscription[] val$out;

        AnonymousClass1(Subscription[] subscriptionArr) {
            this.val$out = subscriptionArr;
        }

        public void call(Subscription t1) {
            this.val$out[0] = t1;
        }
    }

    public abstract void connect(Action1<? super Subscription> action1);

    protected ConnectableObservable(OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }

    public final Subscription connect() {
        Subscription[] out = new Subscription[1];
        connect(new AnonymousClass1(out));
        return out[0];
    }

    public Observable<T> refCount() {
        return Observable.create(new OnSubscribeRefCount(this));
    }

    @Beta
    public Observable<T> autoConnect() {
        return autoConnect(1);
    }

    @Beta
    public Observable<T> autoConnect(int numberOfSubscribers) {
        return autoConnect(numberOfSubscribers, Actions.empty());
    }

    @Beta
    public Observable<T> autoConnect(int numberOfSubscribers, Action1<? super Subscription> connection) {
        if (numberOfSubscribers > 0) {
            return Observable.create(new OnSubscribeAutoConnect(this, numberOfSubscribers, connection));
        }
        connect(connection);
        return this;
    }
}
