package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.internal.util.LinkedArrayList;
import rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class CachedObservable<T> extends Observable<T> {
    private final CacheState<T> state;

    public static <T> CachedObservable<T> from(Observable<? extends T> source) {
        return from(source, 16);
    }

    public static <T> CachedObservable<T> from(Observable<? extends T> source, int capacityHint) {
        if (capacityHint < 1) {
            throw new IllegalArgumentException("capacityHint > 0 required");
        }
        CacheState<T> state = new CacheState<>(source, capacityHint);
        CachedSubscribe<T> onSubscribe = new CachedSubscribe<>(state);
        return new CachedObservable<>(onSubscribe, state);
    }

    private CachedObservable(Observable.OnSubscribe<T> onSubscribe, CacheState<T> state) {
        super(onSubscribe);
        this.state = state;
    }

    boolean isConnected() {
        return this.state.isConnected;
    }

    boolean hasObservers() {
        return this.state.producers.length != 0;
    }

    static final class CacheState<T> extends LinkedArrayList implements Observer<T> {
        static final ReplayProducer<?>[] EMPTY = new ReplayProducer[0];
        final SerialSubscription connection;
        volatile boolean isConnected;
        volatile ReplayProducer<?>[] producers;
        final Observable<? extends T> source;
        boolean sourceDone;

        public CacheState(Observable<? extends T> source, int capacityHint) {
            super(capacityHint);
            this.source = source;
            this.producers = EMPTY;
            this.connection = new SerialSubscription();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void addProducer(ReplayProducer<T> p) {
            synchronized (this.connection) {
                ReplayProducer<?>[] a = this.producers;
                int n = a.length;
                ReplayProducer<?>[] replayProducerArr = new ReplayProducer[n + 1];
                System.arraycopy(a, 0, replayProducerArr, 0, n);
                replayProducerArr[n] = p;
                this.producers = replayProducerArr;
            }
        }

        public void removeProducer(ReplayProducer<T> p) {
            synchronized (this.connection) {
                ReplayProducer<?>[] a = this.producers;
                int n = a.length;
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    }
                    if (!a[i].equals(p)) {
                        i++;
                    } else {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (n == 1) {
                    this.producers = EMPTY;
                    return;
                }
                ReplayProducer<?>[] b = new ReplayProducer[n - 1];
                System.arraycopy(a, 0, b, 0, j);
                System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                this.producers = b;
            }
        }

        public void connect() {
            Subscriber<T> subscriber = new Subscriber<T>() { // from class: rx.internal.operators.CachedObservable.CacheState.1
                @Override // rx.Observer
                public void onNext(T t) throws Throwable {
                    CacheState.this.onNext(t);
                }

                @Override // rx.Observer
                public void onError(Throwable e) throws Throwable {
                    CacheState.this.onError(e);
                }

                @Override // rx.Observer
                public void onCompleted() throws Throwable {
                    CacheState.this.onCompleted();
                }
            };
            this.connection.set(subscriber);
            this.source.unsafeSubscribe(subscriber);
            this.isConnected = true;
        }

        @Override // rx.Observer
        public void onNext(T t) throws Throwable {
            if (!this.sourceDone) {
                Object o = NotificationLite.next(t);
                add(o);
                dispatch();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) throws Throwable {
            if (!this.sourceDone) {
                this.sourceDone = true;
                Object o = NotificationLite.error(e);
                add(o);
                this.connection.unsubscribe();
                dispatch();
            }
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            if (!this.sourceDone) {
                this.sourceDone = true;
                Object o = NotificationLite.completed();
                add(o);
                this.connection.unsubscribe();
                dispatch();
            }
        }

        void dispatch() throws Throwable {
            ReplayProducer<?>[] a = this.producers;
            for (ReplayProducer<?> rp : a) {
                rp.replay();
            }
        }
    }

    static final class CachedSubscribe<T> extends AtomicBoolean implements Observable.OnSubscribe<T> {
        private static final long serialVersionUID = -2817751667698696782L;
        final CacheState<T> state;

        public CachedSubscribe(CacheState<T> state) {
            this.state = state;
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super T> t) throws Throwable {
            ReplayProducer<T> rp = new ReplayProducer<>(t, this.state);
            this.state.addProducer(rp);
            t.add(rp);
            t.setProducer(rp);
            if (!get() && compareAndSet(false, true)) {
                this.state.connect();
            }
        }
    }

    static final class ReplayProducer<T> extends AtomicLong implements Producer, Subscription {
        private static final long serialVersionUID = -2557562030197141021L;
        final Subscriber<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        boolean emitting;
        int index;
        boolean missed;
        final CacheState<T> state;

        public ReplayProducer(Subscriber<? super T> child, CacheState<T> state) {
            this.child = child;
            this.state = state;
        }

        @Override // rx.Producer
        public void request(long n) throws Throwable {
            long r;
            long u;
            do {
                r = get();
                if (r < 0) {
                    return;
                }
                u = r + n;
                if (u < 0) {
                    u = LongCompanionObject.MAX_VALUE;
                }
            } while (!compareAndSet(r, u));
            replay();
        }

        public long produced(long n) {
            return addAndGet(-n);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() < 0;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            long r = get();
            if (r >= 0) {
                long r2 = getAndSet(-1L);
                if (r2 >= 0) {
                    this.state.removeProducer(this);
                }
            }
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:153:0x014a
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
            */
        public void replay() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 352
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.CachedObservable.ReplayProducer.replay():void");
        }
    }
}
