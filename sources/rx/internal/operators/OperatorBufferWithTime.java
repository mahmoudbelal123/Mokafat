package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorBufferWithTime<T> implements Observable.Operator<List<T>, T> {
    final int count;
    final Scheduler scheduler;
    final long timeshift;
    final long timespan;
    final TimeUnit unit;

    public OperatorBufferWithTime(long timespan, long timeshift, TimeUnit unit, int count, Scheduler scheduler) {
        this.timespan = timespan;
        this.timeshift = timeshift;
        this.unit = unit;
        this.count = count;
        this.scheduler = scheduler;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super List<T>> child) {
        Scheduler.Worker inner = this.scheduler.createWorker();
        SerializedSubscriber<List<T>> serialized = new SerializedSubscriber<>(child);
        if (this.timespan == this.timeshift) {
            OperatorBufferWithTime<T>.ExactSubscriber parent = new ExactSubscriber(serialized, inner);
            parent.add(inner);
            child.add(parent);
            parent.scheduleExact();
            return parent;
        }
        OperatorBufferWithTime<T>.InexactSubscriber parent2 = new InexactSubscriber(serialized, inner);
        parent2.add(inner);
        child.add(parent2);
        parent2.startNewChunk();
        parent2.scheduleChunk();
        return parent2;
    }

    final class InexactSubscriber extends Subscriber<T> {
        final Subscriber<? super List<T>> child;
        final List<List<T>> chunks = new LinkedList();
        boolean done;
        final Scheduler.Worker inner;

        public InexactSubscriber(Subscriber<? super List<T>> child, Scheduler.Worker inner) {
            this.child = child;
            this.inner = inner;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            List<List<T>> sizeReached = null;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                Iterator<List<T>> it = this.chunks.iterator();
                while (it.hasNext()) {
                    List<T> chunk = it.next();
                    chunk.add(t);
                    if (chunk.size() == OperatorBufferWithTime.this.count) {
                        it.remove();
                        if (sizeReached == null) {
                            sizeReached = new LinkedList<>();
                        }
                        sizeReached.add(chunk);
                    }
                }
                if (sizeReached != null) {
                    Iterator<List<T>> it2 = sizeReached.iterator();
                    while (it2.hasNext()) {
                        this.child.onNext(it2.next());
                    }
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunks.clear();
                this.child.onError(e);
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    List<List<T>> sizeReached = new LinkedList<>(this.chunks);
                    this.chunks.clear();
                    for (List<T> chunk : sizeReached) {
                        this.child.onNext(chunk);
                    }
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable t) {
                Exceptions.throwOrReport(t, this.child);
            }
        }

        void scheduleChunk() {
            this.inner.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OperatorBufferWithTime.InexactSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    InexactSubscriber.this.startNewChunk();
                }
            }, OperatorBufferWithTime.this.timeshift, OperatorBufferWithTime.this.timeshift, OperatorBufferWithTime.this.unit);
        }

        void startNewChunk() {
            final List<T> chunk = new ArrayList<>();
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunks.add(chunk);
                this.inner.schedule(new Action0() { // from class: rx.internal.operators.OperatorBufferWithTime.InexactSubscriber.2
                    @Override // rx.functions.Action0
                    public void call() {
                        InexactSubscriber.this.emitChunk(chunk);
                    }
                }, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.unit);
            }
        }

        void emitChunk(List<T> chunkToEmit) {
            boolean emit = false;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                Iterator<List<T>> it = this.chunks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    List<T> chunk = it.next();
                    if (chunk == chunkToEmit) {
                        it.remove();
                        emit = true;
                        break;
                    }
                }
                if (emit) {
                    try {
                        this.child.onNext(chunkToEmit);
                    } catch (Throwable t) {
                        Exceptions.throwOrReport(t, this);
                    }
                }
            }
        }
    }

    final class ExactSubscriber extends Subscriber<T> {
        final Subscriber<? super List<T>> child;
        List<T> chunk = new ArrayList();
        boolean done;
        final Scheduler.Worker inner;

        public ExactSubscriber(Subscriber<? super List<T>> child, Scheduler.Worker inner) {
            this.child = child;
            this.inner = inner;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            List<T> toEmit = null;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunk.add(t);
                if (this.chunk.size() == OperatorBufferWithTime.this.count) {
                    toEmit = this.chunk;
                    this.chunk = new ArrayList();
                }
                if (toEmit != null) {
                    this.child.onNext(toEmit);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunk = null;
                this.child.onError(e);
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                this.inner.unsubscribe();
                synchronized (this) {
                    try {
                        if (this.done) {
                            return;
                        }
                        this.done = true;
                        List<T> toEmit = this.chunk;
                        try {
                            this.chunk = null;
                            this.child.onNext(toEmit);
                            this.child.onCompleted();
                            unsubscribe();
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Throwable t) {
                Exceptions.throwOrReport(t, this.child);
            }
        }

        void scheduleExact() {
            this.inner.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OperatorBufferWithTime.ExactSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    ExactSubscriber.this.emit();
                }
            }, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.unit);
        }

        void emit() {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                List<T> toEmit = this.chunk;
                this.chunk = new ArrayList();
                try {
                    this.child.onNext(toEmit);
                } catch (Throwable t) {
                    Exceptions.throwOrReport(t, this);
                }
            }
        }
    }
}
