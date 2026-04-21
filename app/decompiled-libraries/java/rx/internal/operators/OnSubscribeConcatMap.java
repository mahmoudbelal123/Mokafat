package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observers.SerializedSubscriber;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeConcatMap<T, R> implements Observable.OnSubscribe<R> {
    public static final int BOUNDARY = 1;
    public static final int END = 2;
    public static final int IMMEDIATE = 0;
    final int delayErrorMode;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    public OnSubscribeConcatMap(Observable<? extends T> source, Func1<? super T, ? extends Observable<? extends R>> mapper, int prefetch, int delayErrorMode) {
        this.source = source;
        this.mapper = mapper;
        this.prefetch = prefetch;
        this.delayErrorMode = delayErrorMode;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super R> child) throws Throwable {
        Subscriber<? super R> s;
        if (this.delayErrorMode == 0) {
            s = new SerializedSubscriber<>(child);
        } else {
            s = child;
        }
        final ConcatMapSubscriber<T, R> parent = new ConcatMapSubscriber<>(s, this.mapper, this.prefetch, this.delayErrorMode);
        child.add(parent);
        child.add(parent.inner);
        child.setProducer(new Producer() { // from class: rx.internal.operators.OnSubscribeConcatMap.1
            @Override // rx.Producer
            public void request(long n) {
                parent.requestMore(n);
            }
        });
        if (!child.isUnsubscribed()) {
            this.source.unsafeSubscribe(parent);
        }
    }

    static final class ConcatMapSubscriber<T, R> extends Subscriber<T> {
        volatile boolean active;
        final Subscriber<? super R> actual;
        final int delayErrorMode;
        volatile boolean done;
        final SerialSubscription inner;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        final Queue<Object> queue;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final AtomicInteger wip = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        public ConcatMapSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends Observable<? extends R>> mapper, int prefetch, int delayErrorMode) {
            Queue<Object> q;
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrorMode = delayErrorMode;
            if (UnsafeAccess.isUnsafeAvailable()) {
                q = new SpscArrayQueue<>(prefetch);
            } else {
                q = new SpscAtomicArrayQueue<>(prefetch);
            }
            this.queue = q;
            this.inner = new SerialSubscription();
            request(prefetch);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.queue.offer(NotificationLite.next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
            } else {
                drain();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable mainError) {
            if (ExceptionsUtils.addThrowable(this.error, mainError)) {
                this.done = true;
                if (this.delayErrorMode == 0) {
                    Throwable ex = ExceptionsUtils.terminate(this.error);
                    if (!ExceptionsUtils.isTerminated(ex)) {
                        this.actual.onError(ex);
                    }
                    this.inner.unsubscribe();
                    return;
                }
                drain();
                return;
            }
            pluginError(mainError);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        void requestMore(long n) {
            if (n > 0) {
                this.arbiter.request(n);
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
        }

        void innerNext(R value) {
            this.actual.onNext(value);
        }

        void innerError(Throwable innerError, long produced) {
            if (!ExceptionsUtils.addThrowable(this.error, innerError)) {
                pluginError(innerError);
                return;
            }
            if (this.delayErrorMode == 0) {
                Throwable ex = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(ex)) {
                    this.actual.onError(ex);
                }
                unsubscribe();
                return;
            }
            if (produced != 0) {
                this.arbiter.produced(produced);
            }
            this.active = false;
            drain();
        }

        void innerCompleted(long produced) {
            if (produced != 0) {
                this.arbiter.produced(produced);
            }
            this.active = false;
            drain();
        }

        void pluginError(Throwable e) {
            RxJavaHooks.onError(e);
        }

        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            int i = this.delayErrorMode;
            while (!this.actual.isUnsubscribed()) {
                if (!this.active) {
                    if (i == 1 && this.error.get() != null) {
                        Throwable thTerminate = ExceptionsUtils.terminate(this.error);
                        if (!ExceptionsUtils.isTerminated(thTerminate)) {
                            this.actual.onError(thTerminate);
                            return;
                        }
                        return;
                    }
                    boolean z = this.done;
                    Object objPoll = this.queue.poll();
                    boolean z2 = objPoll == null;
                    if (z && z2) {
                        Throwable thTerminate2 = ExceptionsUtils.terminate(this.error);
                        if (thTerminate2 == null) {
                            this.actual.onCompleted();
                            return;
                        } else {
                            if (!ExceptionsUtils.isTerminated(thTerminate2)) {
                                this.actual.onError(thTerminate2);
                                return;
                            }
                            return;
                        }
                    }
                    if (!z2) {
                        try {
                            Observable<? extends R> observableCall = this.mapper.call((Object) NotificationLite.getValue(objPoll));
                            if (observableCall != null) {
                                if (observableCall != Observable.empty()) {
                                    if (observableCall instanceof ScalarSynchronousObservable) {
                                        this.active = true;
                                        this.arbiter.setProducer(new ConcatMapInnerScalarProducer(((ScalarSynchronousObservable) observableCall).get(), this));
                                    } else {
                                        ConcatMapInnerSubscriber concatMapInnerSubscriber = new ConcatMapInnerSubscriber(this);
                                        this.inner.set(concatMapInnerSubscriber);
                                        if (!concatMapInnerSubscriber.isUnsubscribed()) {
                                            this.active = true;
                                            observableCall.unsafeSubscribe(concatMapInnerSubscriber);
                                        } else {
                                            return;
                                        }
                                    }
                                    request(1L);
                                } else {
                                    request(1L);
                                }
                            } else {
                                drainError(new NullPointerException("The source returned by the mapper was null"));
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            drainError(th);
                            return;
                        }
                    }
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
        }

        void drainError(Throwable mapperError) {
            unsubscribe();
            if (ExceptionsUtils.addThrowable(this.error, mapperError)) {
                Throwable ex = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(ex)) {
                    this.actual.onError(ex);
                    return;
                }
                return;
            }
            pluginError(mapperError);
        }
    }

    static final class ConcatMapInnerSubscriber<T, R> extends Subscriber<R> {
        final ConcatMapSubscriber<T, R> parent;
        long produced;

        public ConcatMapInnerSubscriber(ConcatMapSubscriber<T, R> parent) {
            this.parent = parent;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) {
            this.parent.arbiter.setProducer(p);
        }

        @Override // rx.Observer
        public void onNext(R t) {
            this.produced++;
            this.parent.innerNext(t);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.parent.innerError(e, this.produced);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.parent.innerCompleted(this.produced);
        }
    }

    static final class ConcatMapInnerScalarProducer<T, R> implements Producer {
        boolean once;
        final ConcatMapSubscriber<T, R> parent;
        final R value;

        public ConcatMapInnerScalarProducer(R value, ConcatMapSubscriber<T, R> parent) {
            this.value = value;
            this.parent = parent;
        }

        @Override // rx.Producer
        public void request(long n) {
            if (!this.once && n > 0) {
                this.once = true;
                ConcatMapSubscriber<T, R> p = this.parent;
                p.innerNext(this.value);
                p.innerCompleted(1L);
            }
        }
    }
}
