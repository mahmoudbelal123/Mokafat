package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;

/* JADX INFO: loaded from: classes.dex */
public final class SingleDelay<T> implements Single.OnSubscribe<T> {
    final long delay;
    final Scheduler scheduler;
    final Single.OnSubscribe<T> source;
    final TimeUnit unit;

    public SingleDelay(Single.OnSubscribe<T> source, long delay, TimeUnit unit, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
        this.delay = delay;
        this.unit = unit;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super T> t) {
        Scheduler.Worker w = this.scheduler.createWorker();
        ObserveOnSingleSubscriber observeOnSingleSubscriber = new ObserveOnSingleSubscriber(t, w, this.delay, this.unit);
        t.add(w);
        t.add(observeOnSingleSubscriber);
        this.source.call(observeOnSingleSubscriber);
    }

    static final class ObserveOnSingleSubscriber<T> extends SingleSubscriber<T> implements Action0 {
        final SingleSubscriber<? super T> actual;
        final long delay;
        Throwable error;
        final TimeUnit unit;
        T value;
        final Scheduler.Worker w;

        public ObserveOnSingleSubscriber(SingleSubscriber<? super T> actual, Scheduler.Worker w, long delay, TimeUnit unit) {
            this.actual = actual;
            this.w = w;
            this.delay = delay;
            this.unit = unit;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            this.value = value;
            this.w.schedule(this, this.delay, this.unit);
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            this.error = error;
            this.w.schedule(this, this.delay, this.unit);
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                Throwable ex = this.error;
                if (ex != null) {
                    this.error = null;
                    this.actual.onError(ex);
                } else {
                    T v = this.value;
                    this.value = null;
                    this.actual.onSuccess(v);
                }
            } finally {
                this.w.unsubscribe();
            }
        }
    }
}
