package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.internal.util.unsafe.MpscLinkedQueue;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableOnSubscribeMergeDelayErrorIterable implements Completable.OnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeMergeDelayErrorIterable(Iterable<? extends Completable> sources) {
        this.sources = sources;
    }

    @Override // rx.functions.Action1
    public void call(final CompletableSubscriber s) {
        final CompositeSubscription set = new CompositeSubscription();
        s.onSubscribe(set);
        try {
            Iterator<? extends Completable> iterator = this.sources.iterator();
            if (iterator == null) {
                s.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            final AtomicInteger wip = new AtomicInteger(1);
            Queue<Throwable> queue = new MpscLinkedQueue<>();
            while (true) {
                final Queue<Throwable> queue2 = queue;
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
                                NullPointerException e = new NullPointerException("A completable source is null");
                                queue2.offer(e);
                                if (wip.decrementAndGet() == 0) {
                                    if (queue2.isEmpty()) {
                                        s.onCompleted();
                                        return;
                                    } else {
                                        s.onError(CompletableOnSubscribeMerge.collectErrors(queue2));
                                        return;
                                    }
                                }
                                return;
                            }
                            wip.getAndIncrement();
                            c.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeDelayErrorIterable.1
                                @Override // rx.CompletableSubscriber
                                public void onSubscribe(Subscription d) {
                                    set.add(d);
                                }

                                @Override // rx.CompletableSubscriber
                                public void onError(Throwable e2) {
                                    queue2.offer(e2);
                                    tryTerminate();
                                }

                                @Override // rx.CompletableSubscriber
                                public void onCompleted() {
                                    tryTerminate();
                                }

                                void tryTerminate() {
                                    if (wip.decrementAndGet() == 0) {
                                        if (queue2.isEmpty()) {
                                            s.onCompleted();
                                        } else {
                                            s.onError(CompletableOnSubscribeMerge.collectErrors(queue2));
                                        }
                                    }
                                }
                            });
                            queue = queue2;
                        } catch (Throwable e2) {
                            queue2.offer(e2);
                            if (wip.decrementAndGet() == 0) {
                                if (queue2.isEmpty()) {
                                    s.onCompleted();
                                    return;
                                } else {
                                    s.onError(CompletableOnSubscribeMerge.collectErrors(queue2));
                                    return;
                                }
                            }
                            return;
                        }
                    } else {
                        if (wip.decrementAndGet() == 0) {
                            if (queue2.isEmpty()) {
                                s.onCompleted();
                                return;
                            } else {
                                s.onError(CompletableOnSubscribeMerge.collectErrors(queue2));
                                return;
                            }
                        }
                        return;
                    }
                } catch (Throwable e3) {
                    queue2.offer(e3);
                    if (wip.decrementAndGet() == 0) {
                        if (queue2.isEmpty()) {
                            s.onCompleted();
                            return;
                        } else {
                            s.onError(CompletableOnSubscribeMerge.collectErrors(queue2));
                            return;
                        }
                    }
                    return;
                }
            }
        } catch (Throwable e4) {
            s.onError(e4);
        }
    }
}
