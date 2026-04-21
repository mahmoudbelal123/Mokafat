package rx.observers;

import java.util.concurrent.atomic.AtomicReference;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public abstract class AsyncCompletableSubscriber implements CompletableSubscriber, Subscription {
    static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();
    private final AtomicReference<Subscription> upstream = new AtomicReference<>();

    @Override // rx.CompletableSubscriber
    public final void onSubscribe(Subscription d) {
        if (!this.upstream.compareAndSet(null, d)) {
            d.unsubscribe();
            if (this.upstream.get() != UNSUBSCRIBED) {
                RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
                return;
            }
            return;
        }
        onStart();
    }

    protected void onStart() {
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.upstream.get() == UNSUBSCRIBED;
    }

    protected final void clear() {
        this.upstream.set(UNSUBSCRIBED);
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        Subscription current = this.upstream.get();
        if (current != UNSUBSCRIBED) {
            Subscription current2 = this.upstream.getAndSet(UNSUBSCRIBED);
            Subscription current3 = current2;
            if (current3 != null && current3 != UNSUBSCRIBED) {
                current3.unsubscribe();
            }
        }
    }

    static final class Unsubscribed implements Subscription {
        Unsubscribed() {
        }

        @Override // rx.Subscription
        public void unsubscribe() {
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return true;
        }
    }
}
