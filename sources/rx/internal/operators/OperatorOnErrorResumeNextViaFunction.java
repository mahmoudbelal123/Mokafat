package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorOnErrorResumeNextViaFunction<T> implements Observable.Operator<T, T> {
    final Func1<? super Throwable, ? extends Observable<? extends T>> resumeFunction;

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withSingle(final Func1<? super Throwable, ? extends T> resumeFunction) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.1
            @Override // rx.functions.Func1
            public Observable<? extends T> call(Throwable t) {
                return Observable.just(resumeFunction.call(t));
            }
        });
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withOther(final Observable<? extends T> other) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.2
            @Override // rx.functions.Func1
            public Observable<? extends T> call(Throwable t) {
                return other;
            }
        });
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withException(final Observable<? extends T> other) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.3
            @Override // rx.functions.Func1
            public Observable<? extends T> call(Throwable t) {
                if (t instanceof Exception) {
                    return other;
                }
                return Observable.error(t);
            }
        });
    }

    public OperatorOnErrorResumeNextViaFunction(Func1<? super Throwable, ? extends Observable<? extends T>> f) {
        this.resumeFunction = f;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) throws Throwable {
        final ProducerArbiter producerArbiter = new ProducerArbiter();
        final SerialSubscription serialSubscription = new SerialSubscription();
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.4
            private boolean done;
            long produced;

            @Override // rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                subscriber.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                if (this.done) {
                    Exceptions.throwIfFatal(e);
                    RxJavaHooks.onError(e);
                    return;
                }
                this.done = true;
                try {
                    unsubscribe();
                    Subscriber<T> next = new Subscriber<T>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.4.1
                        @Override // rx.Observer
                        public void onNext(T t) {
                            subscriber.onNext(t);
                        }

                        @Override // rx.Observer
                        public void onError(Throwable e2) {
                            subscriber.onError(e2);
                        }

                        @Override // rx.Observer
                        public void onCompleted() {
                            subscriber.onCompleted();
                        }

                        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
                        public void setProducer(Producer producer) {
                            producerArbiter.setProducer(producer);
                        }
                    };
                    serialSubscription.set(next);
                    long p = this.produced;
                    if (p != 0) {
                        producerArbiter.produced(p);
                    }
                    Observable<? extends T> resume = OperatorOnErrorResumeNextViaFunction.this.resumeFunction.call(e);
                    resume.unsafeSubscribe(next);
                } catch (Throwable e2) {
                    Exceptions.throwOrReport(e2, subscriber);
                }
            }

            @Override // rx.Observer
            public void onNext(T t) {
                if (this.done) {
                    return;
                }
                this.produced++;
                subscriber.onNext(t);
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                producerArbiter.setProducer(producer);
            }
        };
        serialSubscription.set(subscriber2);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producerArbiter);
        return subscriber2;
    }
}
