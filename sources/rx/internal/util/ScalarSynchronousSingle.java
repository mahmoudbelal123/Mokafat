package rx.internal.util;

import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.EventLoopsScheduler;

/* JADX INFO: loaded from: classes.dex */
public final class ScalarSynchronousSingle<T> extends Single<T> {
    final T value;

    public static <T> ScalarSynchronousSingle<T> create(T t) {
        return new ScalarSynchronousSingle<>(t);
    }

    protected ScalarSynchronousSingle(final T t) {
        super(new Single.OnSubscribe<T>() { // from class: rx.internal.util.ScalarSynchronousSingle.1
            @Override // rx.functions.Action1
            public void call(SingleSubscriber<? super T> singleSubscriber) {
                singleSubscriber.onSuccess((Object) t);
            }
        });
        this.value = t;
    }

    public T get() {
        return this.value;
    }

    public Single<T> scalarScheduleOn(Scheduler scheduler) {
        if (scheduler instanceof EventLoopsScheduler) {
            EventLoopsScheduler es2 = (EventLoopsScheduler) scheduler;
            return create((Single.OnSubscribe) new DirectScheduledEmission(es2, this.value));
        }
        return create((Single.OnSubscribe) new NormalScheduledEmission(scheduler, this.value));
    }

    static final class DirectScheduledEmission<T> implements Single.OnSubscribe<T> {

        /* JADX INFO: renamed from: es, reason: collision with root package name */
        private final EventLoopsScheduler f4es;
        private final T value;

        DirectScheduledEmission(EventLoopsScheduler es2, T value) {
            this.f4es = es2;
            this.value = value;
        }

        @Override // rx.functions.Action1
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.add(this.f4es.scheduleDirect(new ScalarSynchronousSingleAction(singleSubscriber, this.value)));
        }
    }

    static final class NormalScheduledEmission<T> implements Single.OnSubscribe<T> {
        private final Scheduler scheduler;
        private final T value;

        NormalScheduledEmission(Scheduler scheduler, T value) {
            this.scheduler = scheduler;
            this.value = value;
        }

        @Override // rx.functions.Action1
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Scheduler.Worker worker = this.scheduler.createWorker();
            singleSubscriber.add(worker);
            worker.schedule(new ScalarSynchronousSingleAction(singleSubscriber, this.value));
        }
    }

    static final class ScalarSynchronousSingleAction<T> implements Action0 {
        private final SingleSubscriber<? super T> subscriber;
        private final T value;

        ScalarSynchronousSingleAction(SingleSubscriber<? super T> subscriber, T value) {
            this.subscriber = subscriber;
            this.value = value;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                this.subscriber.onSuccess(this.value);
            } catch (Throwable th) {
                this.subscriber.onError(th);
            }
        }
    }

    public <R> Single<R> scalarFlatMap(final Func1<? super T, ? extends Single<? extends R>> func) {
        return create((Single.OnSubscribe) new Single.OnSubscribe<R>() { // from class: rx.internal.util.ScalarSynchronousSingle.2
            @Override // rx.functions.Action1
            public void call(final SingleSubscriber<? super R> singleSubscriber) {
                Single single = (Single) func.call(ScalarSynchronousSingle.this.value);
                if (single instanceof ScalarSynchronousSingle) {
                    singleSubscriber.onSuccess(((ScalarSynchronousSingle) single).value);
                    return;
                }
                SingleSubscriber<R> singleSubscriber2 = new SingleSubscriber<R>() { // from class: rx.internal.util.ScalarSynchronousSingle.2.1
                    @Override // rx.SingleSubscriber
                    public void onError(Throwable e) {
                        singleSubscriber.onError(e);
                    }

                    @Override // rx.SingleSubscriber
                    public void onSuccess(R r) {
                        singleSubscriber.onSuccess(r);
                    }
                };
                singleSubscriber.add(singleSubscriber2);
                single.subscribe(singleSubscriber2);
            }
        });
    }
}
