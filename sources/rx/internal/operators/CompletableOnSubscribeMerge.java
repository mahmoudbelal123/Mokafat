package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableOnSubscribeMerge implements Completable.OnSubscribe {
    final boolean delayErrors;
    final int maxConcurrency;
    final Observable<Completable> source;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeMerge(Observable<? extends Completable> observable, int maxConcurrency, boolean delayErrors) {
        this.source = observable;
        this.maxConcurrency = maxConcurrency;
        this.delayErrors = delayErrors;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber s) {
        CompletableMergeSubscriber parent = new CompletableMergeSubscriber(s, this.maxConcurrency, this.delayErrors);
        s.onSubscribe(parent);
        this.source.unsafeSubscribe(parent);
    }

    static final class CompletableMergeSubscriber extends Subscriber<Completable> {
        final CompletableSubscriber actual;
        final boolean delayErrors;
        volatile boolean done;
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger wip = new AtomicInteger(1);
        final AtomicBoolean once = new AtomicBoolean();
        final AtomicReference<Queue<Throwable>> errors = new AtomicReference<>();

        public CompletableMergeSubscriber(CompletableSubscriber actual, int maxConcurrency, boolean delayErrors) {
            this.actual = actual;
            this.delayErrors = delayErrors;
            if (maxConcurrency == Integer.MAX_VALUE) {
                request(LongCompanionObject.MAX_VALUE);
            } else {
                request(maxConcurrency);
            }
        }

        Queue<Throwable> getOrCreateErrors() {
            Queue<Throwable> q = this.errors.get();
            if (q != null) {
                return q;
            }
            Queue<Throwable> q2 = new ConcurrentLinkedQueue<>();
            return this.errors.compareAndSet(null, q2) ? q2 : this.errors.get();
        }

        @Override // rx.Observer
        public void onNext(Completable t) {
            if (this.done) {
                return;
            }
            this.wip.getAndIncrement();
            t.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMerge.CompletableMergeSubscriber.1
                Subscription d;
                boolean innerDone;

                @Override // rx.CompletableSubscriber
                public void onSubscribe(Subscription d) {
                    this.d = d;
                    CompletableMergeSubscriber.this.set.add(d);
                }

                @Override // rx.CompletableSubscriber
                public void onError(Throwable e) {
                    if (this.innerDone) {
                        RxJavaHooks.onError(e);
                        return;
                    }
                    this.innerDone = true;
                    CompletableMergeSubscriber.this.set.remove(this.d);
                    CompletableMergeSubscriber.this.getOrCreateErrors().offer(e);
                    CompletableMergeSubscriber.this.terminate();
                    if (CompletableMergeSubscriber.this.delayErrors && !CompletableMergeSubscriber.this.done) {
                        CompletableMergeSubscriber.this.request(1L);
                    }
                }

                @Override // rx.CompletableSubscriber
                public void onCompleted() {
                    if (this.innerDone) {
                        return;
                    }
                    this.innerDone = true;
                    CompletableMergeSubscriber.this.set.remove(this.d);
                    CompletableMergeSubscriber.this.terminate();
                    if (!CompletableMergeSubscriber.this.done) {
                        CompletableMergeSubscriber.this.request(1L);
                    }
                }
            });
        }

        @Override // rx.Observer
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            getOrCreateErrors().offer(t);
            this.done = true;
            terminate();
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            terminate();
        }

        void terminate() {
            Queue<Throwable> q;
            if (this.wip.decrementAndGet() == 0) {
                Queue<Throwable> q2 = this.errors.get();
                if (q2 == null || q2.isEmpty()) {
                    this.actual.onCompleted();
                    return;
                }
                Throwable e = CompletableOnSubscribeMerge.collectErrors(q2);
                if (this.once.compareAndSet(false, true)) {
                    this.actual.onError(e);
                    return;
                } else {
                    RxJavaHooks.onError(e);
                    return;
                }
            }
            if (!this.delayErrors && (q = this.errors.get()) != null && !q.isEmpty()) {
                Throwable e2 = CompletableOnSubscribeMerge.collectErrors(q);
                if (this.once.compareAndSet(false, true)) {
                    this.actual.onError(e2);
                } else {
                    RxJavaHooks.onError(e2);
                }
            }
        }
    }

    public static Throwable collectErrors(Queue<Throwable> q) {
        List<Throwable> list = new ArrayList<>();
        while (true) {
            Throwable t = q.poll();
            if (t == null) {
                break;
            }
            list.add(t);
        }
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return new CompositeException(list);
    }
}
