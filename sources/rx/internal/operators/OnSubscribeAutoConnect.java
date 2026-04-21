package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.observers.Subscribers;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeAutoConnect<T> extends AtomicInteger implements Observable.OnSubscribe<T> {
    final Action1<? super Subscription> connection;
    final int numberOfSubscribers;
    final ConnectableObservable<? extends T> source;

    public OnSubscribeAutoConnect(ConnectableObservable<? extends T> source, int numberOfSubscribers, Action1<? super Subscription> connection) {
        if (numberOfSubscribers <= 0) {
            throw new IllegalArgumentException("numberOfSubscribers > 0 required");
        }
        this.source = source;
        this.numberOfSubscribers = numberOfSubscribers;
        this.connection = connection;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> child) {
        this.source.unsafeSubscribe(Subscribers.wrap(child));
        if (incrementAndGet() == this.numberOfSubscribers) {
            this.source.connect(this.connection);
        }
    }
}
