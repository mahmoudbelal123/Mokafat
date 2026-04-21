package rx.internal.operators;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.FuncN;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class SingleOperatorZip {
    private SingleOperatorZip() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, R> Single<R> zip(final Single<? extends T>[] singles, final FuncN<? extends R> zipper) {
        return Single.create(new Single.OnSubscribe<R>() { // from class: rx.internal.operators.SingleOperatorZip.1
            @Override // rx.functions.Action1
            public void call(final SingleSubscriber<? super R> subscriber) {
                if (singles.length == 0) {
                    subscriber.onError(new NoSuchElementException("Can't zip 0 Singles."));
                    return;
                }
                final AtomicInteger wip = new AtomicInteger(singles.length);
                final AtomicBoolean once = new AtomicBoolean();
                final Object[] values = new Object[singles.length];
                CompositeSubscription compositeSubscription = new CompositeSubscription();
                subscriber.add(compositeSubscription);
                int i = 0;
                while (true) {
                    final int i2 = i;
                    if (i2 >= singles.length || compositeSubscription.isUnsubscribed() || once.get()) {
                        return;
                    }
                    Subscription subscription = new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOperatorZip.1.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // rx.SingleSubscriber
                        public void onSuccess(T value) {
                            values[i2] = value;
                            if (wip.decrementAndGet() == 0) {
                                try {
                                    subscriber.onSuccess(zipper.call(values));
                                } catch (Throwable e) {
                                    Exceptions.throwIfFatal(e);
                                    onError(e);
                                }
                            }
                        }

                        @Override // rx.SingleSubscriber
                        public void onError(Throwable error) {
                            if (once.compareAndSet(false, true)) {
                                subscriber.onError(error);
                            } else {
                                RxJavaHooks.onError(error);
                            }
                        }
                    };
                    compositeSubscription.add(subscription);
                    if (compositeSubscription.isUnsubscribed() || once.get()) {
                        return;
                    }
                    singles[i2].subscribe((SingleSubscriber) subscription);
                    i = i2 + 1;
                }
            }
        });
    }
}
