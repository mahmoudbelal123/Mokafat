package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableOnSubscribeTimeout implements Completable.OnSubscribe {
    final Completable other;
    final Scheduler scheduler;
    final Completable source;
    final long timeout;
    final TimeUnit unit;

    public CompletableOnSubscribeTimeout(Completable source, long timeout, TimeUnit unit, Scheduler scheduler, Completable other) {
        this.source = source;
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }

    @Override // rx.functions.Action1
    public void call(final CompletableSubscriber completableSubscriber) {
        final CompositeSubscription set = new CompositeSubscription();
        completableSubscriber.onSubscribe(set);
        final AtomicBoolean once = new AtomicBoolean();
        Scheduler.Worker w = this.scheduler.createWorker();
        set.add(w);
        w.schedule(new Action0() { // from class: rx.internal.operators.CompletableOnSubscribeTimeout.1
            @Override // rx.functions.Action0
            public void call() throws Throwable {
                if (once.compareAndSet(false, true)) {
                    set.clear();
                    if (CompletableOnSubscribeTimeout.this.other == null) {
                        completableSubscriber.onError(new TimeoutException());
                    } else {
                        CompletableOnSubscribeTimeout.this.other.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeTimeout.1.1
                            @Override // rx.CompletableSubscriber
                            public void onSubscribe(Subscription d) {
                                set.add(d);
                            }

                            @Override // rx.CompletableSubscriber
                            public void onError(Throwable e) throws Throwable {
                                set.unsubscribe();
                                completableSubscriber.onError(e);
                            }

                            @Override // rx.CompletableSubscriber
                            public void onCompleted() throws Throwable {
                                set.unsubscribe();
                                completableSubscriber.onCompleted();
                            }
                        });
                    }
                }
            }
        }, this.timeout, this.unit);
        this.source.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeTimeout.2
            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription d) {
                set.add(d);
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable e) throws Throwable {
                if (once.compareAndSet(false, true)) {
                    set.unsubscribe();
                    completableSubscriber.onError(e);
                } else {
                    RxJavaHooks.onError(e);
                }
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() throws Throwable {
                if (once.compareAndSet(false, true)) {
                    set.unsubscribe();
                    completableSubscriber.onCompleted();
                }
            }
        });
    }
}
