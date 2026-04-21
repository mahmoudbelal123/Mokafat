package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.internal.util.OpenHashSet;
import rx.observables.ConnectableObservable;
import rx.schedulers.Timestamped;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorReplay<T> extends ConnectableObservable<T> {
    static final Func0 DEFAULT_UNBOUNDED_FACTORY = new Func0() { // from class: rx.internal.operators.OperatorReplay.1
        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    };
    final Func0<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Observable<? extends T> source;

    interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerProducer<T> innerProducer);
    }

    public static <T, U, R> Observable<R> multicastSelector(final Func0<? extends ConnectableObservable<U>> connectableFactory, final Func1<? super Observable<U>, ? extends Observable<R>> selector) {
        return Observable.create(new Observable.OnSubscribe<R>() { // from class: rx.internal.operators.OperatorReplay.2
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super R> child) {
                try {
                    ConnectableObservable connectableObservable = (ConnectableObservable) connectableFactory.call();
                    Observable<R> observable = (Observable) selector.call(connectableObservable);
                    observable.subscribe(child);
                    connectableObservable.connect(new Action1<Subscription>() { // from class: rx.internal.operators.OperatorReplay.2.1
                        @Override // rx.functions.Action1
                        public void call(Subscription t) {
                            child.add(t);
                        }
                    });
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, child);
                }
            }
        });
    }

    public static <T> ConnectableObservable<T> observeOn(final ConnectableObservable<T> co, Scheduler scheduler) {
        final Observable<T> observable = co.observeOn(scheduler);
        Observable.OnSubscribe<T> onSubscribe = new Observable.OnSubscribe<T>() { // from class: rx.internal.operators.OperatorReplay.3
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super T> child) {
                observable.unsafeSubscribe(new Subscriber<T>(child) { // from class: rx.internal.operators.OperatorReplay.3.1
                    @Override // rx.Observer
                    public void onNext(T t) {
                        child.onNext(t);
                    }

                    @Override // rx.Observer
                    public void onError(Throwable e) {
                        child.onError(e);
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        child.onCompleted();
                    }
                });
            }
        };
        return new ConnectableObservable<T>(onSubscribe) { // from class: rx.internal.operators.OperatorReplay.4
            @Override // rx.observables.ConnectableObservable
            public void connect(Action1<? super Subscription> connection) {
                co.connect(connection);
            }
        };
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source) {
        return create(source, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, final int bufferSize) {
        if (bufferSize == Integer.MAX_VALUE) {
            return create(source);
        }
        return create(source, new Func0<ReplayBuffer<T>>() { // from class: rx.internal.operators.OperatorReplay.5
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            public ReplayBuffer<T> call() {
                return new SizeBoundReplayBuffer(bufferSize);
            }
        });
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, long maxAge, TimeUnit unit, Scheduler scheduler) {
        return create(source, maxAge, unit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, long maxAge, TimeUnit unit, final Scheduler scheduler, final int bufferSize) {
        final long maxAgeInMillis = unit.toMillis(maxAge);
        return create(source, new Func0<ReplayBuffer<T>>() { // from class: rx.internal.operators.OperatorReplay.6
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            public ReplayBuffer<T> call() {
                return new SizeAndTimeBoundReplayBuffer(bufferSize, maxAgeInMillis, scheduler);
            }
        });
    }

    static <T> ConnectableObservable<T> create(Observable<? extends T> source, final Func0<? extends ReplayBuffer<T>> bufferFactory) {
        final AtomicReference<ReplaySubscriber<T>> curr = new AtomicReference<>();
        Observable.OnSubscribe<T> onSubscribe = new Observable.OnSubscribe<T>() { // from class: rx.internal.operators.OperatorReplay.7
            @Override // rx.functions.Action1
            public void call(Subscriber<? super T> child) throws Throwable {
                ReplaySubscriber<T> r;
                while (true) {
                    r = (ReplaySubscriber) curr.get();
                    if (r != null) {
                        break;
                    }
                    ReplaySubscriber<T> u = new ReplaySubscriber<>((ReplayBuffer) bufferFactory.call());
                    u.init();
                    if (curr.compareAndSet(r, u)) {
                        r = u;
                        break;
                    }
                }
                InnerProducer<T> inner = new InnerProducer<>(r, child);
                r.add((InnerProducer) inner);
                child.add(inner);
                r.buffer.replay(inner);
                child.setProducer(inner);
            }
        };
        return new OperatorReplay(onSubscribe, source, curr, bufferFactory);
    }

    private OperatorReplay(Observable.OnSubscribe<T> onSubscribe, Observable<? extends T> source, AtomicReference<ReplaySubscriber<T>> current, Func0<? extends ReplayBuffer<T>> bufferFactory) {
        super(onSubscribe);
        this.source = source;
        this.current = current;
        this.bufferFactory = bufferFactory;
    }

    @Override // rx.observables.ConnectableObservable
    public void connect(Action1<? super Subscription> connection) {
        ReplaySubscriber<T> ps;
        while (true) {
            ps = this.current.get();
            if (ps != null && !ps.isUnsubscribed()) {
                break;
            }
            ReplaySubscriber<T> u = new ReplaySubscriber<>(this.bufferFactory.call());
            u.init();
            if (this.current.compareAndSet(ps, u)) {
                ps = u;
                break;
            }
        }
        boolean doConnect = !ps.shouldConnect.get() && ps.shouldConnect.compareAndSet(false, true);
        connection.call(ps);
        if (doConnect) {
            this.source.unsafeSubscribe(ps);
        }
    }

    static final class ReplaySubscriber<T> extends Subscriber<T> implements Subscription {
        static final InnerProducer[] EMPTY = new InnerProducer[0];
        static final InnerProducer[] TERMINATED = new InnerProducer[0];
        final ReplayBuffer<T> buffer;
        boolean coordinateAll;
        List<InnerProducer<T>> coordinationQueue;
        boolean done;
        boolean emitting;
        long maxChildRequested;
        long maxUpstreamRequested;
        boolean missed;
        volatile Producer producer;
        long producersCacheVersion;
        volatile long producersVersion;
        volatile boolean terminated;
        final OpenHashSet<InnerProducer<T>> producers = new OpenHashSet<>();
        InnerProducer<T>[] producersCache = EMPTY;
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        public ReplaySubscriber(ReplayBuffer<T> buffer) {
            this.buffer = buffer;
            request(0L);
        }

        void init() {
            add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorReplay.ReplaySubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    if (!ReplaySubscriber.this.terminated) {
                        synchronized (ReplaySubscriber.this.producers) {
                            if (!ReplaySubscriber.this.terminated) {
                                ReplaySubscriber.this.producers.terminate();
                                ReplaySubscriber.this.producersVersion++;
                                ReplaySubscriber.this.terminated = true;
                            }
                        }
                    }
                }
            }));
        }

        boolean add(InnerProducer<T> producer) {
            if (producer == null) {
                throw new NullPointerException();
            }
            if (this.terminated) {
                return false;
            }
            synchronized (this.producers) {
                if (this.terminated) {
                    return false;
                }
                this.producers.add(producer);
                this.producersVersion++;
                return true;
            }
        }

        void remove(InnerProducer<T> producer) {
            if (this.terminated) {
                return;
            }
            synchronized (this.producers) {
                if (this.terminated) {
                    return;
                }
                this.producers.remove(producer);
                if (this.producers.isEmpty()) {
                    this.producersCache = EMPTY;
                }
                this.producersVersion++;
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) throws Throwable {
            Producer p0 = this.producer;
            if (p0 != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.producer = p;
            manageRequests(null);
            replay();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                replay();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (!this.done) {
                this.done = true;
                try {
                    this.buffer.error(e);
                    replay();
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                try {
                    this.buffer.complete();
                    replay();
                } finally {
                    unsubscribe();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v3 */
        /* JADX WARN: Type inference failed for: r10v4, types: [int] */
        /* JADX WARN: Type inference failed for: r10v8 */
        void manageRequests(InnerProducer<T> innerProducer) throws Throwable {
            long jMax;
            Throwable th;
            List<InnerProducer<T>> list;
            boolean z;
            boolean z2;
            long j;
            boolean z3;
            if (isUnsubscribed()) {
                return;
            }
            synchronized (this) {
                if (this.emitting) {
                    if (innerProducer != null) {
                        List<InnerProducer<T>> arrayList = this.coordinationQueue;
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            this.coordinationQueue = arrayList;
                        }
                        arrayList.add(innerProducer);
                    } else {
                        this.coordinateAll = true;
                    }
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                long j2 = this.maxChildRequested;
                List<InnerProducer<T>> list2 = null;
                boolean z4 = false;
                if (innerProducer != null) {
                    jMax = Math.max(j2, innerProducer.totalRequested.get());
                } else {
                    jMax = j2;
                    for (InnerProducer<T> innerProducer2 : copyProducers()) {
                        if (innerProducer2 != null) {
                            jMax = Math.max(jMax, innerProducer2.totalRequested.get());
                        }
                    }
                }
                makeRequest(jMax, j2);
                while (!isUnsubscribed()) {
                    synchronized (this) {
                        try {
                            if (!this.missed) {
                                this.emitting = z4;
                                return;
                            }
                            this.missed = z4;
                            list = this.coordinationQueue;
                            this.coordinationQueue = list2;
                            z = this.coordinateAll;
                            try {
                                this.coordinateAll = z4;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                    long j3 = this.maxChildRequested;
                    long jMax2 = j3;
                    if (list != null) {
                        Iterator<InnerProducer<T>> it = list.iterator();
                        while (it.hasNext()) {
                            jMax2 = Math.max(jMax2, it.next().totalRequested.get());
                        }
                    }
                    if (z) {
                        InnerProducer<T>[] innerProducerArrCopyProducers = copyProducers();
                        int length = innerProducerArrCopyProducers.length;
                        long jMax3 = jMax2;
                        for (?? r10 = z4; r10 < length; r10++) {
                            InnerProducer<T> innerProducer3 = innerProducerArrCopyProducers[r10];
                            if (innerProducer3 != null) {
                                z3 = z;
                                jMax3 = Math.max(jMax3, innerProducer3.totalRequested.get());
                            } else {
                                z3 = z;
                            }
                            z = z3;
                        }
                        z2 = z;
                        j = jMax3;
                    } else {
                        z2 = z;
                        j = jMax2;
                    }
                    makeRequest(j, j3);
                    list2 = null;
                    z4 = false;
                }
            }
        }

        InnerProducer<T>[] copyProducers() {
            InnerProducer<T>[] result;
            synchronized (this.producers) {
                Object[] a = this.producers.values();
                int n = a.length;
                result = new InnerProducer[n];
                System.arraycopy(a, 0, result, 0, n);
            }
            return result;
        }

        void makeRequest(long maxTotalRequests, long previousTotalRequests) {
            long ur = this.maxUpstreamRequested;
            Producer p = this.producer;
            long diff = maxTotalRequests - previousTotalRequests;
            if (diff == 0) {
                if (ur != 0 && p != null) {
                    this.maxUpstreamRequested = 0L;
                    p.request(ur);
                    return;
                }
                return;
            }
            this.maxChildRequested = maxTotalRequests;
            if (p == null) {
                long u = ur + diff;
                if (u < 0) {
                    u = LongCompanionObject.MAX_VALUE;
                }
                this.maxUpstreamRequested = u;
                return;
            }
            if (ur != 0) {
                this.maxUpstreamRequested = 0L;
                p.request(ur + diff);
            } else {
                p.request(diff);
            }
        }

        void replay() {
            InnerProducer<T>[] pc = this.producersCache;
            if (this.producersCacheVersion != this.producersVersion) {
                synchronized (this.producers) {
                    pc = this.producersCache;
                    Object[] a = this.producers.values();
                    int n = a.length;
                    if (pc.length != n) {
                        pc = new InnerProducer[n];
                        this.producersCache = pc;
                    }
                    System.arraycopy(a, 0, pc, 0, n);
                    this.producersCacheVersion = this.producersVersion;
                }
            }
            ReplayBuffer<T> b = this.buffer;
            for (InnerProducer<T> rp : pc) {
                if (rp != null) {
                    b.replay(rp);
                }
            }
        }
    }

    static final class InnerProducer<T> extends AtomicLong implements Producer, Subscription {
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        public InnerProducer(ReplaySubscriber<T> parent, Subscriber<? super T> child) {
            this.parent = parent;
            this.child = child;
        }

        @Override // rx.Producer
        public void request(long n) throws Throwable {
            long r;
            long u;
            if (n < 0) {
                return;
            }
            do {
                r = get();
                if (r == Long.MIN_VALUE) {
                    return;
                }
                if (r >= 0 && n == 0) {
                    return;
                }
                u = r + n;
                if (u < 0) {
                    u = LongCompanionObject.MAX_VALUE;
                }
            } while (!compareAndSet(r, u));
            addTotalRequested(n);
            this.parent.manageRequests(this);
            this.parent.buffer.replay(this);
        }

        void addTotalRequested(long n) {
            long r;
            long u;
            do {
                r = this.totalRequested.get();
                u = r + n;
                if (u < 0) {
                    u = LongCompanionObject.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(r, u));
        }

        public long produced(long n) {
            long r;
            long u;
            if (n <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            do {
                r = get();
                if (r == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                u = r - n;
                if (u < 0) {
                    throw new IllegalStateException("More produced (" + n + ") than requested (" + r + ")");
                }
            } while (!compareAndSet(r, u));
            return u;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // rx.Subscription
        public void unsubscribe() throws Throwable {
            long r = get();
            if (r != Long.MIN_VALUE) {
                long r2 = getAndSet(Long.MIN_VALUE);
                if (r2 != Long.MIN_VALUE) {
                    this.parent.remove(this);
                    this.parent.manageRequests(this);
                    this.child = null;
                }
            }
        }

        <U> U index() {
            return (U) this.index;
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        public UnboundedReplayBuffer(int capacityHint) {
            super(capacityHint);
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void next(T value) {
            add(NotificationLite.next(value));
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void error(Throwable e) {
            add(NotificationLite.error(e));
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void complete() {
            add(NotificationLite.completed());
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void replay(InnerProducer<T> output) throws Throwable {
            synchronized (output) {
                if (output.emitting) {
                    output.missed = true;
                    return;
                }
                output.emitting = true;
                while (!output.isUnsubscribed()) {
                    int sourceIndex = this.size;
                    Integer destinationIndexObject = (Integer) output.index();
                    int destinationIndex = destinationIndexObject != null ? destinationIndexObject.intValue() : 0;
                    Subscriber<? super T> child = output.child;
                    if (child == null) {
                        return;
                    }
                    long r = output.get();
                    long e = 0;
                    while (e != r && destinationIndex < sourceIndex) {
                        Object o = get(destinationIndex);
                        try {
                            if (NotificationLite.accept(child, o) || output.isUnsubscribed()) {
                                return;
                            }
                            destinationIndex++;
                            e++;
                        } catch (Throwable err) {
                            Exceptions.throwIfFatal(err);
                            output.unsubscribe();
                            if (!NotificationLite.isError(o) && !NotificationLite.isCompleted(o)) {
                                child.onError(OnErrorThrowable.addValueAsLastCause(err, NotificationLite.getValue(o)));
                                return;
                            }
                            return;
                        }
                    }
                    if (e != 0) {
                        output.index = Integer.valueOf(destinationIndex);
                        if (r != LongCompanionObject.MAX_VALUE) {
                            output.produced(e);
                        }
                    }
                    synchronized (output) {
                        if (!output.missed) {
                            output.emitting = false;
                            return;
                        }
                        output.missed = false;
                    }
                }
            }
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        public Node(Object value, long index) {
            this.value = value;
            this.index = index;
        }
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        Node tail;

        public BoundedReplayBuffer() {
            Node n = new Node(null, 0L);
            this.tail = n;
            set(n);
        }

        final void addLast(Node n) {
            this.tail.set(n);
            this.tail = n;
            this.size++;
        }

        final void removeFirst() {
            Node head = get();
            Node next = head.get();
            if (next == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(next);
        }

        final void removeSome(int n) {
            Node head = get();
            while (n > 0) {
                head = head.get();
                n--;
                this.size--;
            }
            setFirst(head);
        }

        final void setFirst(Node n) {
            set(n);
        }

        Node getInitialHead() {
            return get();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void next(T value) {
            Object o = enterTransform(NotificationLite.next(value));
            long j = this.index + 1;
            this.index = j;
            Node n = new Node(o, j);
            addLast(n);
            truncate();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void error(Throwable e) {
            Object o = enterTransform(NotificationLite.error(e));
            long j = this.index + 1;
            this.index = j;
            Node n = new Node(o, j);
            addLast(n);
            truncateFinal();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void complete() {
            Object o = enterTransform(NotificationLite.completed());
            long j = this.index + 1;
            this.index = j;
            Node n = new Node(o, j);
            addLast(n);
            truncateFinal();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void replay(InnerProducer<T> output) throws Throwable {
            Subscriber<? super T> child;
            Node v;
            synchronized (output) {
                if (output.emitting) {
                    output.missed = true;
                    return;
                }
                output.emitting = true;
                while (!output.isUnsubscribed()) {
                    Node node = (Node) output.index();
                    if (node == null) {
                        node = getInitialHead();
                        output.index = node;
                        output.addTotalRequested(node.index);
                    }
                    if (output.isUnsubscribed() || (child = output.child) == null) {
                        return;
                    }
                    long r = output.get();
                    long e = 0;
                    while (e != r && (v = node.get()) != null) {
                        Object o = leaveTransform(v.value);
                        try {
                            if (NotificationLite.accept(child, o)) {
                                output.index = null;
                                return;
                            }
                            long e2 = e + 1;
                            node = v;
                            if (!output.isUnsubscribed()) {
                                e = e2;
                            } else {
                                return;
                            }
                        } catch (Throwable err) {
                            output.index = null;
                            Exceptions.throwIfFatal(err);
                            output.unsubscribe();
                            if (!NotificationLite.isError(o) && !NotificationLite.isCompleted(o)) {
                                child.onError(OnErrorThrowable.addValueAsLastCause(err, NotificationLite.getValue(o)));
                                return;
                            }
                            return;
                        }
                    }
                    if (e != 0) {
                        output.index = node;
                        if (r != LongCompanionObject.MAX_VALUE) {
                            output.produced(e);
                        }
                    }
                    synchronized (output) {
                        if (!output.missed) {
                            output.emitting = false;
                            return;
                        }
                        output.missed = false;
                    }
                }
            }
        }

        Object enterTransform(Object value) {
            return value;
        }

        Object leaveTransform(Object value) {
            return value;
        }

        void truncate() {
        }

        void truncateFinal() {
        }

        final void collect(Collection<? super T> collection) {
            Node initialHead = getInitialHead();
            while (true) {
                Node node = initialHead.get();
                if (node != null) {
                    Object objLeaveTransform = leaveTransform(node.value);
                    if (!NotificationLite.isCompleted(objLeaveTransform) && !NotificationLite.isError(objLeaveTransform)) {
                        collection.add((Object) NotificationLite.getValue(objLeaveTransform));
                        initialHead = node;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isCompleted(leaveTransform(this.tail.value));
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        public SizeBoundReplayBuffer(int limit) {
            this.limit = limit;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAgeInMillis;
        final Scheduler scheduler;

        public SizeAndTimeBoundReplayBuffer(int limit, long maxAgeInMillis, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = limit;
            this.maxAgeInMillis = maxAgeInMillis;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Object enterTransform(Object value) {
            return new Timestamped(this.scheduler.now(), value);
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Object leaveTransform(Object value) {
            return ((Timestamped) value).getValue();
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Node getInitialHead() {
            long timeLimit = this.scheduler.now() - this.maxAgeInMillis;
            Node prev = (Node) get();
            for (Node next = prev.get(); next != null && ((Timestamped) next.value).getTimestampMillis() <= timeLimit; next = next.get()) {
                prev = next;
            }
            return prev;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncate() {
            long timeLimit = this.scheduler.now() - this.maxAgeInMillis;
            Node prev = (Node) get();
            Node next = prev.get();
            int e = 0;
            while (next != null) {
                if (this.size > this.limit) {
                    e++;
                    this.size--;
                    prev = next;
                    next = next.get();
                } else {
                    Timestamped<?> v = (Timestamped) next.value;
                    if (v.getTimestampMillis() > timeLimit) {
                        break;
                    }
                    e++;
                    this.size--;
                    prev = next;
                    next = next.get();
                }
            }
            if (e != 0) {
                setFirst(prev);
            }
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncateFinal() {
            long timeLimit = this.scheduler.now() - this.maxAgeInMillis;
            Node prev = (Node) get();
            int e = 0;
            for (Node next = prev.get(); next != null && this.size > 1; next = next.get()) {
                Timestamped<?> v = (Timestamped) next.value;
                if (v.getTimestampMillis() > timeLimit) {
                    break;
                }
                e++;
                this.size--;
                prev = next;
            }
            if (e != 0) {
                setFirst(prev);
            }
        }
    }
}
