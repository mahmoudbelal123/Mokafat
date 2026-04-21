package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.subscriptions.SequentialSubscription;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableOnSubscribeConcat implements Completable.OnSubscribe {
    final int prefetch;
    final Observable<Completable> sources;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeConcat(Observable<? extends Completable> observable, int prefetch) {
        this.sources = observable;
        this.prefetch = prefetch;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber s) {
        CompletableConcatSubscriber parent = new CompletableConcatSubscriber(s, this.prefetch);
        s.onSubscribe(parent);
        this.sources.unsafeSubscribe(parent);
    }

    static final class CompletableConcatSubscriber extends Subscriber<Completable> {
        volatile boolean active;
        final CompletableSubscriber actual;
        volatile boolean done;
        final SpscArrayQueue<Completable> queue;
        final SequentialSubscription sr = new SequentialSubscription();
        final ConcatInnerSubscriber inner = new ConcatInnerSubscriber();
        final AtomicBoolean once = new AtomicBoolean();

        public CompletableConcatSubscriber(CompletableSubscriber actual, int prefetch) {
            this.actual = actual;
            this.queue = new SpscArrayQueue<>(prefetch);
            add(this.sr);
            request(prefetch);
        }

        @Override // rx.Observer
        public void onNext(Completable t) {
            if (!this.queue.offer(t)) {
                onError(new MissingBackpressureException());
            } else {
                drain();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable t) {
            if (this.once.compareAndSet(false, true)) {
                this.actual.onError(t);
            } else {
                RxJavaHooks.onError(t);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        void innerError(Throwable e) {
            unsubscribe();
            onError(e);
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        void drain() {
            ConcatInnerSubscriber inner = this.inner;
            if (inner.getAndIncrement() != 0) {
                return;
            }
            while (!isUnsubscribed()) {
                if (!this.active) {
                    boolean d = this.done;
                    Completable c = this.queue.poll();
                    boolean empty = c == null;
                    if (d && empty) {
                        this.actual.onCompleted();
                        return;
                    } else if (!empty) {
                        this.active = true;
                        c.subscribe(inner);
                        request(1L);
                    }
                }
                if (inner.decrementAndGet() == 0) {
                    return;
                }
            }
        }

        final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
            private static final long serialVersionUID = 7233503139645205620L;

            ConcatInnerSubscriber() {
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription d) {
                CompletableConcatSubscriber.this.sr.set(d);
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable e) {
                CompletableConcatSubscriber.this.innerError(e);
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                CompletableConcatSubscriber.this.innerComplete();
            }
        }
    }
}
