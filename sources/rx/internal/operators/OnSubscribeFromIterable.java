package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeFromIterable<T> implements Observable.OnSubscribe<T> {
    final Iterable<? extends T> is;

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.is = iterable;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> o) throws Throwable {
        try {
            Iterator<? extends T> it = this.is.iterator();
            boolean b = it.hasNext();
            if (!o.isUnsubscribed()) {
                if (!b) {
                    o.onCompleted();
                } else {
                    o.setProducer(new IterableProducer(o, it));
                }
            }
        } catch (Throwable ex) {
            Exceptions.throwOrReport(ex, o);
        }
    }

    static final class IterableProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -8730475647105475802L;
        private final Iterator<? extends T> it;
        private final Subscriber<? super T> o;

        IterableProducer(Subscriber<? super T> o, Iterator<? extends T> it) {
            this.o = o;
            this.it = it;
        }

        @Override // rx.Producer
        public void request(long n) {
            if (get() == LongCompanionObject.MAX_VALUE) {
                return;
            }
            if (n == LongCompanionObject.MAX_VALUE && compareAndSet(0L, LongCompanionObject.MAX_VALUE)) {
                fastPath();
            } else if (n > 0 && BackpressureUtils.getAndAddRequest(this, n) == 0) {
                slowPath(n);
            }
        }

        void slowPath(long j) {
            Subscriber<? super T> subscriber = this.o;
            Iterator<? extends T> it = this.it;
            long jProduced = j;
            long j2 = 0;
            while (true) {
                if (j2 != jProduced) {
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    try {
                        subscriber.onNext(it.next());
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        try {
                            if (!it.hasNext()) {
                                if (!subscriber.isUnsubscribed()) {
                                    subscriber.onCompleted();
                                    return;
                                }
                                return;
                            }
                            j2++;
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, subscriber);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwOrReport(th2, subscriber);
                        return;
                    }
                } else {
                    jProduced = get();
                    if (j2 == jProduced) {
                        jProduced = BackpressureUtils.produced(this, j2);
                        if (jProduced != 0) {
                            j2 = 0;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }

        void fastPath() {
            Subscriber<? super T> subscriber = this.o;
            Iterator<? extends T> it = this.it;
            while (!subscriber.isUnsubscribed()) {
                try {
                    subscriber.onNext(it.next());
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    try {
                        if (!it.hasNext()) {
                            if (!subscriber.isUnsubscribed()) {
                                subscriber.onCompleted();
                                return;
                            }
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber);
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, subscriber);
                    return;
                }
            }
        }
    }
}
