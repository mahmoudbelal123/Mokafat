package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableOnSubscribeConcatIterable implements Completable.OnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeConcatIterable(Iterable<? extends Completable> sources) {
        this.sources = sources;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber s) {
        try {
            Iterator<? extends Completable> it = this.sources.iterator();
            if (it == null) {
                s.onSubscribe(Subscriptions.unsubscribed());
                s.onError(new NullPointerException("The iterator returned is null"));
            } else {
                ConcatInnerSubscriber inner = new ConcatInnerSubscriber(s, it);
                s.onSubscribe(inner.sd);
                inner.next();
            }
        } catch (Throwable e) {
            s.onSubscribe(Subscriptions.unsubscribed());
            s.onError(e);
        }
    }

    static final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableSubscriber actual;
        final SerialSubscription sd = new SerialSubscription();
        final Iterator<? extends Completable> sources;

        public ConcatInnerSubscriber(CompletableSubscriber actual, Iterator<? extends Completable> sources) {
            this.actual = actual;
            this.sources = sources;
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription d) {
            this.sd.set(d);
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            next();
        }

        void next() {
            if (this.sd.isUnsubscribed() || getAndIncrement() != 0) {
                return;
            }
            Iterator<? extends Completable> a = this.sources;
            while (!this.sd.isUnsubscribed()) {
                try {
                    boolean b = a.hasNext();
                    if (!b) {
                        this.actual.onCompleted();
                        return;
                    }
                    try {
                        Completable c = a.next();
                        if (c == null) {
                            this.actual.onError(new NullPointerException("The completable returned is null"));
                            return;
                        } else {
                            c.unsafeSubscribe(this);
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        }
                    } catch (Throwable ex) {
                        this.actual.onError(ex);
                        return;
                    }
                } catch (Throwable ex2) {
                    this.actual.onError(ex2);
                    return;
                }
            }
        }
    }
}
