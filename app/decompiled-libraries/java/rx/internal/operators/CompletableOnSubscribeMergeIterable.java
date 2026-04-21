package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableOnSubscribeMergeIterable implements Completable.OnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeMergeIterable(Iterable<? extends Completable> sources) {
        this.sources = sources;
    }

    @Override // rx.functions.Action1
    public void call(final CompletableSubscriber s) throws Throwable {
        final CompositeSubscription set = new CompositeSubscription();
        s.onSubscribe(set);
        try {
            Iterator<? extends Completable> iterator = this.sources.iterator();
            if (iterator == null) {
                s.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            boolean z = true;
            final AtomicInteger wip = new AtomicInteger(1);
            AtomicBoolean once = new AtomicBoolean();
            boolean z2 = false;
            while (true) {
                final AtomicBoolean once2 = once;
                if (set.isUnsubscribed()) {
                    return;
                }
                try {
                    boolean b = iterator.hasNext();
                    if (b) {
                        if (set.isUnsubscribed()) {
                            return;
                        }
                        try {
                            Completable c = iterator.next();
                            if (set.isUnsubscribed()) {
                                return;
                            }
                            if (c == null) {
                                set.unsubscribe();
                                NullPointerException npe = new NullPointerException("A completable source is null");
                                if (once2.compareAndSet(z2, z)) {
                                    s.onError(npe);
                                    return;
                                } else {
                                    RxJavaHooks.onError(npe);
                                    return;
                                }
                            }
                            wip.getAndIncrement();
                            c.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeIterable.1
                                @Override // rx.CompletableSubscriber
                                public void onSubscribe(Subscription d) {
                                    set.add(d);
                                }

                                @Override // rx.CompletableSubscriber
                                public void onError(Throwable e) throws Throwable {
                                    set.unsubscribe();
                                    if (once2.compareAndSet(false, true)) {
                                        s.onError(e);
                                    } else {
                                        RxJavaHooks.onError(e);
                                    }
                                }

                                @Override // rx.CompletableSubscriber
                                public void onCompleted() {
                                    if (wip.decrementAndGet() == 0 && once2.compareAndSet(false, true)) {
                                        s.onCompleted();
                                    }
                                }
                            });
                            once = once2;
                            z = true;
                            z2 = false;
                        } catch (Throwable e) {
                            set.unsubscribe();
                            if (once2.compareAndSet(false, true)) {
                                s.onError(e);
                                return;
                            } else {
                                RxJavaHooks.onError(e);
                                return;
                            }
                        }
                    } else {
                        if (wip.decrementAndGet() == 0 && once2.compareAndSet(z2, z)) {
                            s.onCompleted();
                            return;
                        }
                        return;
                    }
                } catch (Throwable e2) {
                    set.unsubscribe();
                    if (once2.compareAndSet(false, true)) {
                        s.onError(e2);
                        return;
                    } else {
                        RxJavaHooks.onError(e2);
                        return;
                    }
                }
            }
        } catch (Throwable e3) {
            s.onError(e3);
        }
    }
}
