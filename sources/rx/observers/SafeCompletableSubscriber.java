package rx.observers;

import rx.CompletableSubscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public final class SafeCompletableSubscriber implements CompletableSubscriber, Subscription {
    final CompletableSubscriber actual;
    boolean done;
    Subscription s;

    public SafeCompletableSubscriber(CompletableSubscriber actual) {
        this.actual = actual;
    }

    @Override // rx.CompletableSubscriber
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onCompleted();
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw new OnCompletedFailedException(ex);
        }
    }

    @Override // rx.CompletableSubscriber
    public void onError(Throwable e) {
        RxJavaHooks.onError(e);
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onError(e);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw new OnErrorFailedException(new CompositeException(e, ex));
        }
    }

    @Override // rx.CompletableSubscriber
    public void onSubscribe(Subscription d) {
        this.s = d;
        try {
            this.actual.onSubscribe(this);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            d.unsubscribe();
            onError(ex);
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.s.unsubscribe();
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.done || this.s.isUnsubscribed();
    }
}
