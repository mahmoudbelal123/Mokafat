package rx.internal.operators;

import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;

/* JADX INFO: loaded from: classes.dex */
public final class SingleObserveOn<T> implements Single.OnSubscribe<T> {
    final Scheduler scheduler;
    final Single.OnSubscribe<T> source;

    public SingleObserveOn(Single.OnSubscribe<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super T> t) {
        Scheduler.Worker w = this.scheduler.createWorker();
        ObserveOnSingleSubscriber observeOnSingleSubscriber = new ObserveOnSingleSubscriber(t, w);
        t.add(w);
        t.add(observeOnSingleSubscriber);
        this.source.call(observeOnSingleSubscriber);
    }

    static final class ObserveOnSingleSubscriber<T> extends SingleSubscriber<T> implements Action0 {
        final SingleSubscriber<? super T> actual;
        Throwable error;
        T value;
        final Scheduler.Worker w;

        public ObserveOnSingleSubscriber(SingleSubscriber<? super T> actual, Scheduler.Worker w) {
            this.actual = actual;
            this.w = w;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            this.value = value;
            this.w.schedule(this);
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            this.error = error;
            this.w.schedule(this);
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
