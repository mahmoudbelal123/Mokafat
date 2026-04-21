package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public final class ReplaySubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    final ReplayState<T> state;

    interface ReplayBuffer<T> {
        void complete();

        void drain(ReplayProducer<T> replayProducer);

        Throwable error();

        void error(Throwable th);

        boolean isComplete();

        boolean isEmpty();

        T last();

        void next(T t);

        int size();

        T[] toArray(T[] tArr);
    }

    public static <T> ReplaySubject<T> create() {
        return create(16);
    }

    public static <T> ReplaySubject<T> create(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity > 0 required but it was " + capacity);
        }
        ReplayBuffer<T> buffer = new ReplayUnboundedBuffer<>(capacity);
        ReplayState<T> state = new ReplayState<>(buffer);
        return new ReplaySubject<>(state);
    }

    static <T> ReplaySubject<T> createUnbounded() {
        ReplayBuffer<T> buffer = new ReplaySizeBoundBuffer<>(Integer.MAX_VALUE);
        ReplayState<T> state = new ReplayState<>(buffer);
        return new ReplaySubject<>(state);
    }

    static <T> ReplaySubject<T> createUnboundedTime() {
        ReplayBuffer<T> buffer = new ReplaySizeAndTimeBoundBuffer<>(Integer.MAX_VALUE, LongCompanionObject.MAX_VALUE, Schedulers.immediate());
        ReplayState<T> state = new ReplayState<>(buffer);
        return new ReplaySubject<>(state);
    }

    public static <T> ReplaySubject<T> createWithSize(int size) {
        ReplayBuffer<T> buffer = new ReplaySizeBoundBuffer<>(size);
        ReplayState<T> state = new ReplayState<>(buffer);
        return new ReplaySubject<>(state);
    }

    public static <T> ReplaySubject<T> createWithTime(long time, TimeUnit unit, Scheduler scheduler) {
        return createWithTimeAndSize(time, unit, Integer.MAX_VALUE, scheduler);
    }

    public static <T> ReplaySubject<T> createWithTimeAndSize(long time, TimeUnit unit, int size, Scheduler scheduler) {
        ReplayBuffer<T> buffer = new ReplaySizeAndTimeBoundBuffer<>(size, unit.toMillis(time), scheduler);
        ReplayState<T> state = new ReplayState<>(buffer);
        return new ReplaySubject<>(state);
    }

    ReplaySubject(ReplayState<T> state) {
        super(state);
        this.state = state;
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.state.onNext(t);
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        this.state.onError(e);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    int subscriberCount() {
        return this.state.get().length;
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.get().length != 0;
    }

    public boolean hasThrowable() {
        return this.state.isTerminated() && this.state.buffer.error() != null;
    }

    public boolean hasCompleted() {
        return this.state.isTerminated() && this.state.buffer.error() == null;
    }

    public Throwable getThrowable() {
        if (this.state.isTerminated()) {
            return this.state.buffer.error();
        }
        return null;
    }

    public int size() {
        return this.state.buffer.size();
    }

    public boolean hasAnyValue() {
        return !this.state.buffer.isEmpty();
    }

    public boolean hasValue() {
        return hasAnyValue();
    }

    public T[] getValues(T[] a) {
        return this.state.buffer.toArray(a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] values = getValues(EMPTY_ARRAY);
        if (values == EMPTY_ARRAY) {
            return new Object[0];
        }
        return values;
    }

    public T getValue() {
        return this.state.buffer.last();
    }

    static final class ReplayState<T> extends AtomicReference<ReplayProducer<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        static final ReplayProducer[] EMPTY = new ReplayProducer[0];
        static final ReplayProducer[] TERMINATED = new ReplayProducer[0];
        private static final long serialVersionUID = 5952362471246910544L;
        final ReplayBuffer<T> buffer;

        public ReplayState(ReplayBuffer<T> buffer) {
            this.buffer = buffer;
            lazySet(EMPTY);
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super T> t) throws Throwable {
            ReplayProducer<T> rp = new ReplayProducer<>(t, this);
            t.add(rp);
            t.setProducer(rp);
            if (add(rp) && rp.isUnsubscribed()) {
                remove(rp);
            } else {
                this.buffer.drain(rp);
            }
        }

        boolean add(ReplayProducer<T> rp) {
            ReplayProducer<T>[] a;
            ReplayProducer<T>[] b;
            do {
                a = get();
                if (a == TERMINATED) {
                    return false;
                }
                int n = a.length;
                b = new ReplayProducer[n + 1];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = rp;
            } while (!compareAndSet(a, b));
            return true;
        }

        void remove(ReplayProducer<T> rp) {
            ReplayProducer<T>[] a;
            ReplayProducer<T>[] b;
            do {
                a = get();
                if (a == TERMINATED || a == EMPTY) {
                    return;
                }
                int n = a.length;
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    }
                    if (a[i] != rp) {
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
                    b = EMPTY;
                } else {
                    ReplayProducer<T>[] b2 = new ReplayProducer[n - 1];
                    System.arraycopy(a, 0, b2, 0, j);
                    System.arraycopy(a, j + 1, b2, j, (n - j) - 1);
                    b = b2;
                }
            } while (!compareAndSet(a, b));
        }

        @Override // rx.Observer
        public void onNext(T t) {
            ReplayBuffer<T> b = this.buffer;
            b.next(t);
            for (ReplayProducer<T> rp : get()) {
                b.drain(rp);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            ReplayBuffer<T> b = this.buffer;
            b.error(e);
            List<Throwable> errors = null;
            for (ReplayProducer<T> rp : getAndSet(TERMINATED)) {
                try {
                    b.drain(rp);
                } catch (Throwable ex) {
                    if (errors == null) {
                        errors = new ArrayList<>();
                    }
                    errors.add(ex);
                }
            }
            Exceptions.throwIfAny(errors);
        }

        @Override // rx.Observer
        public void onCompleted() {
            ReplayBuffer<T> b = this.buffer;
            b.complete();
            for (ReplayProducer<T> rp : getAndSet(TERMINATED)) {
                b.drain(rp);
            }
        }

        boolean isTerminated() {
            return get() == TERMINATED;
        }
    }

    static final class ReplayUnboundedBuffer<T> implements ReplayBuffer<T> {
        final int capacity;
        volatile boolean done;
        Throwable error;
        final Object[] head;
        volatile int size;
        Object[] tail;
        int tailIndex;

        public ReplayUnboundedBuffer(int capacity) {
            this.capacity = capacity;
            Object[] objArr = new Object[capacity + 1];
            this.head = objArr;
            this.tail = objArr;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            if (this.done) {
                return;
            }
            int i = this.tailIndex;
            Object[] a = this.tail;
            if (i == a.length - 1) {
                Object[] b = new Object[a.length];
                b[0] = t;
                this.tailIndex = 1;
                a[i] = b;
                this.tail = b;
            } else {
                a[i] = t;
                this.tailIndex = i + 1;
            }
            this.size++;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
            } else {
                this.error = e;
                this.done = true;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void drain(ReplayProducer<T> rp) {
            if (rp.getAndIncrement() != 0) {
                return;
            }
            int missed = 1;
            Subscriber<? super T> a = rp.actual;
            int n = this.capacity;
            do {
                long r = rp.requested.get();
                long e = 0;
                Object[] node = (Object[]) rp.node;
                if (node == null) {
                    node = this.head;
                }
                int tailIndex = rp.tailIndex;
                int index = rp.index;
                while (true) {
                    if (e == r) {
                        break;
                    }
                    if (a.isUnsubscribed()) {
                        rp.node = null;
                        return;
                    }
                    boolean d = this.done;
                    boolean empty = index == this.size;
                    if (d && empty) {
                        rp.node = null;
                        Throwable ex = this.error;
                        if (ex != null) {
                            a.onError(ex);
                            return;
                        } else {
                            a.onCompleted();
                            return;
                        }
                    }
                    if (empty) {
                        break;
                    }
                    if (tailIndex == n) {
                        node = (Object[]) node[tailIndex];
                        tailIndex = 0;
                    }
                    a.onNext(node[tailIndex]);
                    tailIndex++;
                    index++;
                    e++;
                }
                if (e == r) {
                    if (a.isUnsubscribed()) {
                        rp.node = null;
                        return;
                    }
                    boolean d2 = this.done;
                    boolean empty2 = index == this.size;
                    if (d2 && empty2) {
                        rp.node = null;
                        Throwable ex2 = this.error;
                        if (ex2 != null) {
                            a.onError(ex2);
                            return;
                        } else {
                            a.onCompleted();
                            return;
                        }
                    }
                }
                if (e != 0 && r != LongCompanionObject.MAX_VALUE) {
                    BackpressureUtils.produced(rp.requested, e);
                }
                rp.index = index;
                rp.tailIndex = tailIndex;
                rp.node = node;
                missed = rp.addAndGet(-missed);
            } while (missed != 0);
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            int i = this.size;
            if (i == 0) {
                return null;
            }
            Object[] objArr = this.head;
            int i2 = this.capacity;
            while (i >= i2) {
                objArr = (Object[]) objArr[i2];
                i -= i2;
            }
            return (T) objArr[i - 1];
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            return this.size;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return this.size == 0;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            int i = this.size;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            Object[] objArr = this.head;
            int i2 = this.capacity;
            Object[] objArr2 = objArr;
            int i3 = 0;
            while (i3 + i2 < i) {
                System.arraycopy(objArr2, 0, tArr, i3, i2);
                i3 += i2;
                objArr2 = objArr2[i2];
            }
            System.arraycopy(objArr2, 0, tArr, i3, i - i3);
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }
    }

    static final class ReplaySizeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile Node<T> head;
        final int limit;
        int size;
        Node<T> tail;

        public ReplaySizeBoundBuffer(int limit) {
            this.limit = limit;
            Node<T> n = new Node<>(null);
            this.tail = n;
            this.head = n;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T value) {
            Node<T> n = new Node<>(value);
            this.tail.set(n);
            this.tail = n;
            int s = this.size;
            if (s == this.limit) {
                this.head = this.head.get();
            } else {
                this.size = s + 1;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable ex) {
            this.error = ex;
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x005f, code lost:
        
            if (r7 != r5) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0065, code lost:
        
            if (r2.isUnsubscribed() == false) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
        
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0069, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
        
            r10 = r17.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0070, code lost:
        
            if (r9.get() != null) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0072, code lost:
        
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
        
            if (r10 == false) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0076, code lost:
        
            if (r11 == false) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0078, code lost:
        
            r18.node = null;
            r3 = r17.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007c, code lost:
        
            if (r3 == null) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x007e, code lost:
        
            r2.onError(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0082, code lost:
        
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0085, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x008a, code lost:
        
            if (r7 == 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0093, code lost:
        
            if (r5 == kotlin.jvm.internal.LongCompanionObject.MAX_VALUE) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0095, code lost:
        
            rx.internal.operators.BackpressureUtils.produced(r18.requested, r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x009a, code lost:
        
            r18.node = r9;
            r4 = r18.addAndGet(-r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:?, code lost:
        
            return;
         */
        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain(rx.subjects.ReplaySubject.ReplayProducer<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                r3 = 1
                r4 = r3
            Lf:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                r7 = 0
                java.lang.Object r9 = r1.node
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node r9 = (rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.Node) r9
                if (r9 != 0) goto L1f
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node<T> r9 = r0.head
            L1f:
                int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                r11 = 0
                r12 = 0
                if (r10 == 0) goto L5d
                boolean r10 = r2.isUnsubscribed()
                if (r10 == 0) goto L2e
                r1.node = r12
                return
            L2e:
                boolean r10 = r0.done
                java.lang.Object r13 = r9.get()
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node r13 = (rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.Node) r13
                if (r13 != 0) goto L3a
                r14 = r3
                goto L3b
            L3a:
                r14 = r11
            L3b:
                if (r10 == 0) goto L4d
                if (r14 == 0) goto L4d
                r1.node = r12
                java.lang.Throwable r3 = r0.error
                if (r3 == 0) goto L49
                r2.onError(r3)
                goto L4c
            L49:
                r2.onCompleted()
            L4c:
                return
            L4d:
                if (r14 == 0) goto L50
                goto L5d
            L50:
                T r11 = r13.value
                r2.onNext(r11)
                r11 = 1
                long r15 = r7 + r11
                r9 = r13
                r7 = r15
                goto L1f
            L5d:
                int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r10 != 0) goto L86
                boolean r10 = r2.isUnsubscribed()
                if (r10 == 0) goto L6a
                r1.node = r12
                return
            L6a:
                boolean r10 = r0.done
                java.lang.Object r13 = r9.get()
                if (r13 != 0) goto L74
                r11 = r3
            L74:
                if (r10 == 0) goto L86
                if (r11 == 0) goto L86
                r1.node = r12
                java.lang.Throwable r3 = r0.error
                if (r3 == 0) goto L82
                r2.onError(r3)
                goto L85
            L82:
                r2.onCompleted()
            L85:
                return
            L86:
                r10 = 0
                int r12 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
                if (r12 == 0) goto L9a
                r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r12 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
                if (r12 == 0) goto L9a
                java.util.concurrent.atomic.AtomicLong r10 = r1.requested
                rx.internal.operators.BackpressureUtils.produced(r10, r7)
            L9a:
                r1.node = r9
                int r10 = -r4
                int r4 = r1.addAndGet(r10)
                if (r4 != 0) goto La4
                return
            La4:
                goto Lf
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.drain(rx.subjects.ReplaySubject$ReplayProducer):void");
        }

        static final class Node<T> extends AtomicReference<Node<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final T value;

            public Node(T value) {
                this.value = value;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            Node<T> h = this.head;
            while (true) {
                Node<T> n = h.get();
                if (n != null) {
                    h = n;
                } else {
                    return h.value;
                }
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            int s = 0;
            Node<T> n = this.head.get();
            while (n != null && s != Integer.MAX_VALUE) {
                n = n.get();
                s++;
            }
            return s;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return this.head.get() == null;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (Node<T> node = this.head.get(); node != null; node = node.get()) {
                arrayList.add(node.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    static final class ReplaySizeAndTimeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile TimedNode<T> head;
        final int limit;
        final long maxAgeMillis;
        final Scheduler scheduler;
        int size;
        TimedNode<T> tail;

        public ReplaySizeAndTimeBoundBuffer(int limit, long maxAgeMillis, Scheduler scheduler) {
            this.limit = limit;
            TimedNode<T> n = new TimedNode<>(null, 0L);
            this.tail = n;
            this.head = n;
            this.maxAgeMillis = maxAgeMillis;
            this.scheduler = scheduler;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T value) {
            long now = this.scheduler.now();
            TimedNode<T> n = new TimedNode<>(value, now);
            this.tail.set(n);
            this.tail = n;
            long now2 = now - this.maxAgeMillis;
            int s = this.size;
            TimedNode<T> h0 = this.head;
            TimedNode<T> h = h0;
            if (s == this.limit) {
                h = h.get();
            } else {
                s++;
            }
            while (true) {
                TimedNode<T> n2 = h.get();
                if (n2 == null || n2.timestamp > now2) {
                    break;
                }
                h = n2;
                s--;
            }
            this.size = s;
            if (h != h0) {
                this.head = h;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable ex) {
            evictFinal();
            this.error = ex;
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            evictFinal();
            this.done = true;
        }

        void evictFinal() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> h0 = this.head;
            TimedNode<T> h = h0;
            while (true) {
                TimedNode<T> n = h.get();
                if (n == null || n.timestamp > now) {
                    break;
                } else {
                    h = n;
                }
            }
            if (h0 != h) {
                this.head = h;
            }
        }

        TimedNode<T> latestHead() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> h = this.head;
            while (true) {
                TimedNode<T> n = h.get();
                if (n == null || n.timestamp > now) {
                    break;
                }
                h = n;
            }
            return h;
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0061, code lost:
        
            if (r7 != r5) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0067, code lost:
        
            if (r2.isUnsubscribed() == false) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0069, code lost:
        
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x006b, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006c, code lost:
        
            r10 = r17.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0072, code lost:
        
            if (r9.get() != null) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0074, code lost:
        
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0076, code lost:
        
            if (r10 == false) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0078, code lost:
        
            if (r11 == false) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x007a, code lost:
        
            r18.node = null;
            r3 = r17.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007e, code lost:
        
            if (r3 == null) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0080, code lost:
        
            r2.onError(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0084, code lost:
        
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0087, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x008c, code lost:
        
            if (r7 == 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0095, code lost:
        
            if (r5 == kotlin.jvm.internal.LongCompanionObject.MAX_VALUE) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0097, code lost:
        
            rx.internal.operators.BackpressureUtils.produced(r18.requested, r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x009c, code lost:
        
            r18.node = r9;
            r4 = r18.addAndGet(-r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:?, code lost:
        
            return;
         */
        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain(rx.subjects.ReplaySubject.ReplayProducer<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                r3 = 1
                r4 = r3
            Lf:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                r7 = 0
                java.lang.Object r9 = r1.node
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r9 = (rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.TimedNode) r9
                if (r9 != 0) goto L21
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r9 = r17.latestHead()
            L21:
                int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                r11 = 0
                r12 = 0
                if (r10 == 0) goto L5f
                boolean r10 = r2.isUnsubscribed()
                if (r10 == 0) goto L30
                r1.node = r12
                return
            L30:
                boolean r10 = r0.done
                java.lang.Object r13 = r9.get()
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r13 = (rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.TimedNode) r13
                if (r13 != 0) goto L3c
                r14 = r3
                goto L3d
            L3c:
                r14 = r11
            L3d:
                if (r10 == 0) goto L4f
                if (r14 == 0) goto L4f
                r1.node = r12
                java.lang.Throwable r3 = r0.error
                if (r3 == 0) goto L4b
                r2.onError(r3)
                goto L4e
            L4b:
                r2.onCompleted()
            L4e:
                return
            L4f:
                if (r14 == 0) goto L52
                goto L5f
            L52:
                T r11 = r13.value
                r2.onNext(r11)
                r11 = 1
                long r15 = r7 + r11
                r9 = r13
                r7 = r15
                goto L21
            L5f:
                int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r10 != 0) goto L88
                boolean r10 = r2.isUnsubscribed()
                if (r10 == 0) goto L6c
                r1.node = r12
                return
            L6c:
                boolean r10 = r0.done
                java.lang.Object r13 = r9.get()
                if (r13 != 0) goto L76
                r11 = r3
            L76:
                if (r10 == 0) goto L88
                if (r11 == 0) goto L88
                r1.node = r12
                java.lang.Throwable r3 = r0.error
                if (r3 == 0) goto L84
                r2.onError(r3)
                goto L87
            L84:
                r2.onCompleted()
            L87:
                return
            L88:
                r10 = 0
                int r12 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
                if (r12 == 0) goto L9c
                r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r12 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
                if (r12 == 0) goto L9c
                java.util.concurrent.atomic.AtomicLong r10 = r1.requested
                rx.internal.operators.BackpressureUtils.produced(r10, r7)
            L9c:
                r1.node = r9
                int r10 = -r4
                int r4 = r1.addAndGet(r10)
                if (r4 != 0) goto La6
                return
            La6:
                goto Lf
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.drain(rx.subjects.ReplaySubject$ReplayProducer):void");
        }

        static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final long timestamp;
            final T value;

            public TimedNode(T value, long timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            TimedNode<T> h = latestHead();
            while (true) {
                TimedNode<T> n = h.get();
                if (n != null) {
                    h = n;
                } else {
                    return h.value;
                }
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            int s = 0;
            TimedNode<T> n = latestHead().get();
            while (n != null && s != Integer.MAX_VALUE) {
                n = n.get();
                s++;
            }
            return s;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return latestHead().get() == null;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (TimedNode<T> timedNode = latestHead().get(); timedNode != null; timedNode = timedNode.get()) {
                arrayList.add(timedNode.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    static final class ReplayProducer<T> extends AtomicInteger implements Producer, Subscription {
        private static final long serialVersionUID = -5006209596735204567L;
        final Subscriber<? super T> actual;
        int index;
        Object node;
        final AtomicLong requested = new AtomicLong();
        final ReplayState<T> state;
        int tailIndex;

        public ReplayProducer(Subscriber<? super T> actual, ReplayState<T> state) {
            this.actual = actual;
            this.state = state;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.state.remove(this);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                this.state.buffer.drain(this);
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            }
        }
    }
}
