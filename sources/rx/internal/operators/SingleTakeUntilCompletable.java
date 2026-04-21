package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class SingleTakeUntilCompletable<T> implements Single.OnSubscribe<T> {
    final Completable other;
    final Single.OnSubscribe<T> source;

    public SingleTakeUntilCompletable(Single.OnSubscribe<T> source, Completable other) {
        this.source = source;
        this.other = other;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super T> t) {
        TakeUntilSourceSubscriber takeUntilSourceSubscriber = new TakeUntilSourceSubscriber(t);
        t.add(takeUntilSourceSubscriber);
        this.other.subscribe(takeUntilSourceSubscriber);
        this.source.call(takeUntilSourceSubscriber);
    }

    static final class TakeUntilSourceSubscriber<T> extends SingleSubscriber<T> implements CompletableSubscriber {
        final SingleSubscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();

        TakeUntilSourceSubscriber(SingleSubscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription d) {
            add(d);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            if (this.once.compareAndSet(false, true)) {
                unsubscribe();
                this.actual.onSuccess(value);
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            if (this.once.compareAndSet(false, true)) {
                unsubscribe();
                this.actual.onError(error);
            } else {
                RxJavaHooks.onError(error);
            }
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            onError(new CancellationException("Single::takeUntil(Completable) - Stream was canceled before emitting a terminal event."));
        }
    }
}
