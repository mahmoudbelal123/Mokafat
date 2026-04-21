package rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
public final class SingleFromFuture<T> implements Single.OnSubscribe<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    public SingleFromFuture(Future<? extends T> future, long timeout, TimeUnit unit) {
        this.future = future;
        this.timeout = timeout;
        this.unit = unit;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super T> singleSubscriber) {
        T t;
        Future<? extends T> future = this.future;
        singleSubscriber.add(Subscriptions.from(future));
        try {
            if (this.timeout == 0) {
                t = future.get();
            } else {
                t = future.get(this.timeout, this.unit);
            }
            singleSubscriber.onSuccess(t);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            singleSubscriber.onError(th);
        }
    }
}
