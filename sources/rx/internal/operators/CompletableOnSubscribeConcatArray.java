package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableOnSubscribeConcatArray implements Completable.OnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeConcatArray(Completable[] sources) {
        this.sources = sources;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber s) {
        ConcatInnerSubscriber inner = new ConcatInnerSubscriber(s, this.sources);
        s.onSubscribe(inner.sd);
        inner.next();
    }

    static final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableSubscriber actual;
        int index;
        final SerialSubscription sd = new SerialSubscription();
        final Completable[] sources;

        public ConcatInnerSubscriber(CompletableSubscriber actual, Completable[] sources) {
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
            Completable[] a = this.sources;
            while (!this.sd.isUnsubscribed()) {
                int idx = this.index;
                this.index = idx + 1;
                if (idx == a.length) {
                    this.actual.onCompleted();
                    return;
                } else {
                    a[idx].unsafeSubscribe(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
