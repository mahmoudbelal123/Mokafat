package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subjects.UnicastSubject;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorWindowWithTime<T> implements Observable.Operator<Observable<T>, T> {
    static final Object NEXT_SUBJECT = new Object();
    final Scheduler scheduler;
    final int size;
    final long timeshift;
    final long timespan;
    final TimeUnit unit;

    public OperatorWindowWithTime(long timespan, long timeshift, TimeUnit unit, int size, Scheduler scheduler) {
        this.timespan = timespan;
        this.timeshift = timeshift;
        this.unit = unit;
        this.size = size;
        this.scheduler = scheduler;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super Observable<T>> child) {
        Scheduler.Worker worker = this.scheduler.createWorker();
        if (this.timespan == this.timeshift) {
            OperatorWindowWithTime<T>.ExactSubscriber s = new ExactSubscriber(child, worker);
            s.add(worker);
            s.scheduleExact();
            return s;
        }
        OperatorWindowWithTime<T>.InexactSubscriber s2 = new InexactSubscriber(child, worker);
        s2.add(worker);
        s2.startNewChunk();
        s2.scheduleChunk();
        return s2;
    }

    static final class State<T> {
        static final State<Object> EMPTY = new State<>(null, null, 0);
        final Observer<T> consumer;
        final int count;
        final Observable<T> producer;

        public State(Observer<T> consumer, Observable<T> producer, int count) {
            this.consumer = consumer;
            this.producer = producer;
            this.count = count;
        }

        public State<T> next() {
            return new State<>(this.consumer, this.producer, this.count + 1);
        }

        public State<T> create(Observer<T> consumer, Observable<T> producer) {
            return new State<>(consumer, producer, 0);
        }

        public State<T> clear() {
            return empty();
        }

        public static <T> State<T> empty() {
            return (State<T>) EMPTY;
        }
    }

    final class ExactSubscriber extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        boolean emitting;
        List<Object> queue;
        final Scheduler.Worker worker;
        final Object guard = new Object();
        volatile State<T> state = State.empty();

        public ExactSubscriber(Subscriber<? super Observable<T>> child, Scheduler.Worker worker) {
            this.child = new SerializedSubscriber(child);
            this.worker = worker;
            child.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorWindowWithTime.ExactSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    if (ExactSubscriber.this.state.consumer == null) {
                        ExactSubscriber.this.unsubscribe();
                    }
                }
            }));
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(LongCompanionObject.MAX_VALUE);
        }

        /* JADX WARN: Removed duplicated region for block: B:64:0x006a  */
        @Override // rx.Observer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onNext(T r7) {
            /*
                r6 = this;
                java.lang.Object r0 = r6.guard
                monitor-enter(r0)
                boolean r1 = r6.emitting     // Catch: java.lang.Throwable -> L75
                if (r1 == 0) goto L19
                java.util.List<java.lang.Object> r1 = r6.queue     // Catch: java.lang.Throwable -> L75
                if (r1 != 0) goto L12
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L75
                r1.<init>()     // Catch: java.lang.Throwable -> L75
                r6.queue = r1     // Catch: java.lang.Throwable -> L75
            L12:
                java.util.List<java.lang.Object> r1 = r6.queue     // Catch: java.lang.Throwable -> L75
                r1.add(r7)     // Catch: java.lang.Throwable -> L75
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
                return
            L19:
                r1 = 1
                r6.emitting = r1     // Catch: java.lang.Throwable -> L75
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
                r0 = 0
                r1 = r0
                boolean r2 = r6.emitValue(r7)     // Catch: java.lang.Throwable -> L67
                if (r2 != 0) goto L32
                if (r1 != 0) goto L31
                java.lang.Object r2 = r6.guard
                monitor-enter(r2)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L2e
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L2e
                goto L31
            L2e:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L2e
                throw r0
            L31:
                return
            L32:
                r2 = 0
                r3 = r2
            L34:
                java.lang.Object r4 = r6.guard     // Catch: java.lang.Throwable -> L67
                monitor-enter(r4)     // Catch: java.lang.Throwable -> L67
                java.util.List<java.lang.Object> r5 = r6.queue     // Catch: java.lang.Throwable -> L64
                r3 = r5
                if (r3 != 0) goto L4d
                r6.emitting = r0     // Catch: java.lang.Throwable -> L64
                r1 = 1
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L64
                if (r1 != 0) goto L4c
                java.lang.Object r2 = r6.guard
                monitor-enter(r2)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L49
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L49
                goto L4c
            L49:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L49
                throw r0
            L4c:
                return
            L4d:
                r6.queue = r2     // Catch: java.lang.Throwable -> L64
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L64
                boolean r4 = r6.drain(r3)     // Catch: java.lang.Throwable -> L67
                if (r4 != 0) goto L63
                if (r1 != 0) goto L62
                java.lang.Object r2 = r6.guard
                monitor-enter(r2)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L5f
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L5f
                goto L62
            L5f:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L5f
                throw r0
            L62:
                return
            L63:
                goto L34
            L64:
                r2 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L64
                throw r2     // Catch: java.lang.Throwable -> L67
            L67:
                r2 = move-exception
                if (r1 != 0) goto L74
                java.lang.Object r3 = r6.guard
                monitor-enter(r3)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L71
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L71
                goto L74
            L71:
                r0 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L71
                throw r0
            L74:
                throw r2
            L75:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorWindowWithTime.ExactSubscriber.onNext(java.lang.Object):void");
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
        
            return true;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        boolean drain(java.util.List<java.lang.Object> r7) {
            /*
                r6 = this;
                r0 = 1
                if (r7 != 0) goto L4
                return r0
            L4:
                java.util.Iterator r1 = r7.iterator()
            L8:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L3f
                java.lang.Object r2 = r1.next()
                java.lang.Object r3 = rx.internal.operators.OperatorWindowWithTime.NEXT_SUBJECT
                r4 = 0
                if (r2 != r3) goto L1e
                boolean r3 = r6.replaceSubject()
                if (r3 != 0) goto L3e
                return r4
            L1e:
                boolean r3 = rx.internal.operators.NotificationLite.isError(r2)
                if (r3 == 0) goto L2c
                java.lang.Throwable r3 = rx.internal.operators.NotificationLite.getError(r2)
                r6.error(r3)
                goto L3f
            L2c:
                boolean r3 = rx.internal.operators.NotificationLite.isCompleted(r2)
                if (r3 == 0) goto L36
                r6.complete()
                goto L3f
            L36:
                r3 = r2
                boolean r5 = r6.emitValue(r3)
                if (r5 != 0) goto L3e
                return r4
            L3e:
                goto L8
            L3f:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorWindowWithTime.ExactSubscriber.drain(java.util.List):boolean");
        }

        boolean replaceSubject() {
            Observer<T> s = this.state.consumer;
            if (s != null) {
                s.onCompleted();
            }
            if (this.child.isUnsubscribed()) {
                this.state = this.state.clear();
                unsubscribe();
                return false;
            }
            UnicastSubject<T> bus = UnicastSubject.create();
            this.state = this.state.create(bus, bus);
            this.child.onNext(bus);
            return true;
        }

        boolean emitValue(T t) {
            State<T> s;
            State<T> s2 = this.state;
            if (s2.consumer == null) {
                if (!replaceSubject()) {
                    return false;
                }
                s2 = this.state;
            }
            s2.consumer.onNext(t);
            if (s2.count == OperatorWindowWithTime.this.size - 1) {
                s2.consumer.onCompleted();
                s = s2.clear();
            } else {
                s = s2.next();
            }
            this.state = s;
            return true;
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            synchronized (this.guard) {
                if (this.emitting) {
                    this.queue = Collections.singletonList(NotificationLite.error(e));
                    return;
                }
                this.queue = null;
                this.emitting = true;
                error(e);
            }
        }

        void error(Throwable e) {
            Observer<T> s = this.state.consumer;
            this.state = this.state.clear();
            if (s != null) {
                s.onError(e);
            }
            this.child.onError(e);
            unsubscribe();
        }

        void complete() {
            Observer<T> s = this.state.consumer;
            this.state = this.state.clear();
            if (s != null) {
                s.onCompleted();
            }
            this.child.onCompleted();
            unsubscribe();
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            synchronized (this.guard) {
                try {
                    if (this.emitting) {
                        if (this.queue == null) {
                            this.queue = new ArrayList();
                        }
                        this.queue.add(NotificationLite.completed());
                        return;
                    }
                    List<Object> localQueue = this.queue;
                    try {
                        this.queue = null;
                        this.emitting = true;
                        try {
                            drain(localQueue);
                            complete();
                        } catch (Throwable e) {
                            error(e);
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        void scheduleExact() {
            this.worker.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OperatorWindowWithTime.ExactSubscriber.2
                @Override // rx.functions.Action0
                public void call() {
                    ExactSubscriber.this.nextWindow();
                }
            }, 0L, OperatorWindowWithTime.this.timespan, OperatorWindowWithTime.this.unit);
        }

        /* JADX WARN: Removed duplicated region for block: B:64:0x006c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void nextWindow() {
            /*
                r6 = this;
                java.lang.Object r0 = r6.guard
                monitor-enter(r0)
                boolean r1 = r6.emitting     // Catch: java.lang.Throwable -> L77
                if (r1 == 0) goto L1b
                java.util.List<java.lang.Object> r1 = r6.queue     // Catch: java.lang.Throwable -> L77
                if (r1 != 0) goto L12
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L77
                r1.<init>()     // Catch: java.lang.Throwable -> L77
                r6.queue = r1     // Catch: java.lang.Throwable -> L77
            L12:
                java.util.List<java.lang.Object> r1 = r6.queue     // Catch: java.lang.Throwable -> L77
                java.lang.Object r2 = rx.internal.operators.OperatorWindowWithTime.NEXT_SUBJECT     // Catch: java.lang.Throwable -> L77
                r1.add(r2)     // Catch: java.lang.Throwable -> L77
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
                return
            L1b:
                r1 = 1
                r6.emitting = r1     // Catch: java.lang.Throwable -> L77
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
                r0 = 0
                r1 = r0
                boolean r2 = r6.replaceSubject()     // Catch: java.lang.Throwable -> L69
                if (r2 != 0) goto L34
                if (r1 != 0) goto L33
                java.lang.Object r2 = r6.guard
                monitor-enter(r2)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L30
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L30
                goto L33
            L30:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L30
                throw r0
            L33:
                return
            L34:
                r2 = 0
                r3 = r2
            L36:
                java.lang.Object r4 = r6.guard     // Catch: java.lang.Throwable -> L69
                monitor-enter(r4)     // Catch: java.lang.Throwable -> L69
                java.util.List<java.lang.Object> r5 = r6.queue     // Catch: java.lang.Throwable -> L66
                r3 = r5
                if (r3 != 0) goto L4f
                r6.emitting = r0     // Catch: java.lang.Throwable -> L66
                r1 = 1
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L66
                if (r1 != 0) goto L4e
                java.lang.Object r2 = r6.guard
                monitor-enter(r2)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L4b
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L4b
                goto L4e
            L4b:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L4b
                throw r0
            L4e:
                return
            L4f:
                r6.queue = r2     // Catch: java.lang.Throwable -> L66
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L66
                boolean r4 = r6.drain(r3)     // Catch: java.lang.Throwable -> L69
                if (r4 != 0) goto L65
                if (r1 != 0) goto L64
                java.lang.Object r2 = r6.guard
                monitor-enter(r2)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L61
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L61
                goto L64
            L61:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L61
                throw r0
            L64:
                return
            L65:
                goto L36
            L66:
                r2 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L66
                throw r2     // Catch: java.lang.Throwable -> L69
            L69:
                r2 = move-exception
                if (r1 != 0) goto L76
                java.lang.Object r3 = r6.guard
                monitor-enter(r3)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L73
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L73
                goto L76
            L73:
                r0 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L73
                throw r0
            L76:
                throw r2
            L77:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorWindowWithTime.ExactSubscriber.nextWindow():void");
        }
    }

    static final class CountedSerializedSubject<T> {
        final Observer<T> consumer;
        int count;
        final Observable<T> producer;

        public CountedSerializedSubject(Observer<T> consumer, Observable<T> producer) {
            this.consumer = new SerializedObserver(consumer);
            this.producer = producer;
        }
    }

    final class InexactSubscriber extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        final List<CountedSerializedSubject<T>> chunks;
        boolean done;
        final Object guard;
        final Scheduler.Worker worker;

        public InexactSubscriber(Subscriber<? super Observable<T>> child, Scheduler.Worker worker) {
            super(child);
            this.child = child;
            this.worker = worker;
            this.guard = new Object();
            this.chunks = new LinkedList();
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(LongCompanionObject.MAX_VALUE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                List<CountedSerializedSubject<T>> list = new ArrayList<>(this.chunks);
                Iterator<CountedSerializedSubject<T>> it = this.chunks.iterator();
                while (it.hasNext()) {
                    CountedSerializedSubject<T> cs = it.next();
                    int i = cs.count + 1;
                    cs.count = i;
                    if (i == OperatorWindowWithTime.this.size) {
                        it.remove();
                    }
                }
                for (CountedSerializedSubject<T> cs2 : list) {
                    cs2.consumer.onNext(t);
                    if (cs2.count == OperatorWindowWithTime.this.size) {
                        cs2.consumer.onCompleted();
                    }
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                this.done = true;
                List<CountedSerializedSubject<T>> list = new ArrayList<>(this.chunks);
                this.chunks.clear();
                for (CountedSerializedSubject<T> cs : list) {
                    cs.consumer.onError(e);
                }
                this.child.onError(e);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                this.done = true;
                List<CountedSerializedSubject<T>> list = new ArrayList<>(this.chunks);
                this.chunks.clear();
                for (CountedSerializedSubject<T> cs : list) {
                    cs.consumer.onCompleted();
                }
                this.child.onCompleted();
            }
        }

        void scheduleChunk() {
            this.worker.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OperatorWindowWithTime.InexactSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    InexactSubscriber.this.startNewChunk();
                }
            }, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.unit);
        }

        void startNewChunk() {
            final CountedSerializedSubject<T> chunk = createCountedSerializedSubject();
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                this.chunks.add(chunk);
                try {
                    this.child.onNext(chunk.producer);
                    this.worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorWindowWithTime.InexactSubscriber.2
                        @Override // rx.functions.Action0
                        public void call() {
                            InexactSubscriber.this.terminateChunk(chunk);
                        }
                    }, OperatorWindowWithTime.this.timespan, OperatorWindowWithTime.this.unit);
                } catch (Throwable e) {
                    onError(e);
                }
            }
        }

        void terminateChunk(CountedSerializedSubject<T> chunk) {
            boolean terminate = false;
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                Iterator<CountedSerializedSubject<T>> it = this.chunks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CountedSerializedSubject<T> cs = it.next();
                    if (cs == chunk) {
                        terminate = true;
                        it.remove();
                        break;
                    }
                }
                if (terminate) {
                    chunk.consumer.onCompleted();
                }
            }
        }

        CountedSerializedSubject<T> createCountedSerializedSubject() {
            UnicastSubject<T> bus = UnicastSubject.create();
            return new CountedSerializedSubject<>(bus, bus);
        }
    }
}
