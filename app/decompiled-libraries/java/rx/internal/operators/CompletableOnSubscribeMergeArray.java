package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableOnSubscribeMergeArray implements Completable.OnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeMergeArray(Completable[] sources) {
        this.sources = sources;
    }

    @Override // rx.functions.Action1
    public void call(final CompletableSubscriber s) throws Throwable {
        final CompositeSubscription set = new CompositeSubscription();
        boolean z = false;
        boolean z2 = true;
        final AtomicInteger wip = new AtomicInteger(this.sources.length + 1);
        final AtomicBoolean once = new AtomicBoolean();
        s.onSubscribe(set);
        Completable[] arr$ = this.sources;
        int len$ = arr$.length;
        int i$ = 0;
        while (true) {
            int i$2 = i$;
            if (i$2 >= len$) {
                int i$3 = wip.decrementAndGet();
                if (i$3 == 0 && once.compareAndSet(false, true)) {
                    s.onCompleted();
                    return;
                }
                return;
            }
            Completable c = arr$[i$2];
            if (set.isUnsubscribed()) {
                return;
            }
            if (c == null) {
                set.unsubscribe();
                NullPointerException npe = new NullPointerException("A completable source is null");
                if (once.compareAndSet(z, z2)) {
                    s.onError(npe);
                    return;
                }
                RxJavaHooks.onError(npe);
            }
            c.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeArray.1
                @Override // rx.CompletableSubscriber
                public void onSubscribe(Subscription d) {
                    set.add(d);
                }

                @Override // rx.CompletableSubscriber
                public void onError(Throwable e) throws Throwable {
                    set.unsubscribe();
                    if (once.compareAndSet(false, true)) {
                        s.onError(e);
                    } else {
                        RxJavaHooks.onError(e);
                    }
                }

                @Override // rx.CompletableSubscriber
                public void onCompleted() {
                    if (wip.decrementAndGet() == 0 && once.compareAndSet(false, true)) {
                        s.onCompleted();
                    }
                }
            });
            i$ = i$2 + 1;
            z = false;
            z2 = true;
        }
    }
}
