package rx.internal.operators;

import android.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorSwitch<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayError;

    static final class Holder {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(false);

        Holder() {
        }
    }

    static final class HolderDelayError {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(true);

        HolderDelayError() {
        }
    }

    public static <T> OperatorSwitch<T> instance(boolean z) {
        if (z) {
            return (OperatorSwitch<T>) HolderDelayError.INSTANCE;
        }
        return (OperatorSwitch<T>) Holder.INSTANCE;
    }

    OperatorSwitch(boolean delayError) {
        this.delayError = delayError;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> child) throws Throwable {
        SwitchSubscriber<T> sws = new SwitchSubscriber<>(child, this.delayError);
        child.add(sws);
        sws.init();
        return sws;
    }

    static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final Throwable TERMINAL_ERROR = new Throwable("Terminal error");
        final Subscriber<? super T> child;
        final boolean delayError;
        boolean emitting;
        Throwable error;
        boolean innerActive;
        volatile boolean mainDone;
        boolean missed;
        Producer producer;
        long requested;
        final SerialSubscription serial = new SerialSubscription();
        final AtomicLong index = new AtomicLong();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(RxRingBuffer.SIZE);

        SwitchSubscriber(Subscriber<? super T> child, boolean delayError) {
            this.child = child;
            this.delayError = delayError;
        }

        void init() throws Throwable {
            this.child.add(this.serial);
            this.child.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    SwitchSubscriber.this.clearProducer();
                }
            }));
            this.child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.2
                @Override // rx.Producer
                public void request(long n) throws Throwable {
                    if (n > 0) {
                        SwitchSubscriber.this.childRequested(n);
                    } else if (n < 0) {
                        throw new IllegalArgumentException("n >= 0 expected but it was " + n);
                    }
                }
            });
        }

        void clearProducer() {
            synchronized (this) {
                this.producer = null;
            }
        }

        @Override // rx.Observer
        public void onNext(Observable<? extends T> t) throws Throwable {
            long id = this.index.incrementAndGet();
            Subscription s = this.serial.get();
            if (s != null) {
                s.unsubscribe();
            }
            synchronized (this) {
                try {
                    try {
                        InnerSubscriber<T> inner = new InnerSubscriber<>(id, this);
                        this.innerActive = true;
                        this.producer = null;
                        this.serial.set(inner);
                        t.unsafeSubscribe(inner);
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) throws Throwable {
            synchronized (this) {
                try {
                    try {
                        boolean success = updateError(e);
                        if (!success) {
                            pluginError(e);
                        } else {
                            this.mainDone = true;
                            drain();
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        }

        boolean updateError(Throwable next) {
            Throwable e = this.error;
            if (e == TERMINAL_ERROR) {
                return false;
            }
            if (e == null) {
                this.error = next;
            } else if (e instanceof CompositeException) {
                List<Throwable> list = new ArrayList<>(((CompositeException) e).getExceptions());
                list.add(next);
                this.error = new CompositeException(list);
            } else {
                this.error = new CompositeException(e, next);
            }
            return true;
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            this.mainDone = true;
            drain();
        }

        void emit(T value, InnerSubscriber<T> inner) throws Throwable {
            synchronized (this) {
                if (this.index.get() != ((InnerSubscriber) inner).id) {
                    return;
                }
                this.queue.offer(inner, NotificationLite.next(value));
                drain();
            }
        }

        void error(Throwable e, long id) throws Throwable {
            boolean success;
            synchronized (this) {
                try {
                    if (this.index.get() == id) {
                        success = updateError(e);
                        try {
                            this.innerActive = false;
                            this.producer = null;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    } else {
                        success = true;
                    }
                    boolean success2 = success;
                    if (success2) {
                        drain();
                    } else {
                        pluginError(e);
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        void complete(long id) throws Throwable {
            synchronized (this) {
                if (this.index.get() != id) {
                    return;
                }
                this.innerActive = false;
                this.producer = null;
                drain();
            }
        }

        void pluginError(Throwable e) {
            RxJavaHooks.onError(e);
        }

        void innerProducer(Producer p, long id) {
            synchronized (this) {
                if (this.index.get() != id) {
                    return;
                }
                long n = this.requested;
                this.producer = p;
                p.request(n);
            }
        }

        void childRequested(long n) throws Throwable {
            synchronized (this) {
                try {
                    try {
                        Producer p = this.producer;
                        this.requested = BackpressureUtils.addCap(this.requested, n);
                        if (p != null) {
                            p.request(n);
                        }
                        drain();
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        }

        void drain() throws Throwable {
            long j;
            Subscriber<? super T> subscriber;
            Throwable th;
            Throwable th2;
            synchronized (this) {
                Throwable th3 = null;
                try {
                    if (this.emitting) {
                        this.missed = true;
                        return;
                    }
                    this.emitting = true;
                    boolean z = this.innerActive;
                    try {
                        long j2 = this.requested;
                        try {
                            th3 = this.error;
                            if (th3 != null && th3 != TERMINAL_ERROR && !this.delayError) {
                                this.error = TERMINAL_ERROR;
                            }
                            SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
                            AtomicLong atomicLong = this.index;
                            Subscriber<? super T> subscriber2 = this.child;
                            Throwable th4 = th3;
                            boolean z2 = z;
                            long j3 = j2;
                            boolean z3 = this.mainDone;
                            while (true) {
                                long j4 = 0;
                                while (true) {
                                    j = j4;
                                    if (j == j3) {
                                        break;
                                    }
                                    if (subscriber2.isUnsubscribed()) {
                                        return;
                                    }
                                    boolean zIsEmpty = spscLinkedArrayQueue.isEmpty();
                                    if (checkTerminated(z3, z2, th4, spscLinkedArrayQueue, subscriber2, zIsEmpty)) {
                                        return;
                                    }
                                    if (zIsEmpty) {
                                        break;
                                    }
                                    InnerSubscriber innerSubscriber = (InnerSubscriber) spscLinkedArrayQueue.poll();
                                    R.color colorVar = (Object) NotificationLite.getValue(spscLinkedArrayQueue.poll());
                                    if (atomicLong.get() == innerSubscriber.id) {
                                        subscriber2.onNext(colorVar);
                                        j4 = j + 1;
                                    } else {
                                        j4 = j;
                                    }
                                }
                                if (j != j3) {
                                    subscriber = subscriber2;
                                } else {
                                    if (subscriber2.isUnsubscribed()) {
                                        return;
                                    }
                                    subscriber = subscriber2;
                                    if (checkTerminated(this.mainDone, z2, th4, spscLinkedArrayQueue, subscriber2, spscLinkedArrayQueue.isEmpty())) {
                                        return;
                                    }
                                }
                                synchronized (this) {
                                    try {
                                        long j5 = this.requested;
                                        if (j5 != LongCompanionObject.MAX_VALUE) {
                                            long j6 = j5 - j;
                                            try {
                                                this.requested = j6;
                                                j3 = j6;
                                            } catch (Throwable th5) {
                                                th = th5;
                                                throw th;
                                            }
                                        } else {
                                            j3 = j5;
                                        }
                                        if (!this.missed) {
                                            this.emitting = false;
                                            return;
                                        }
                                        this.missed = false;
                                        z3 = this.mainDone;
                                        z2 = this.innerActive;
                                        th2 = this.error;
                                        if (th2 != null) {
                                            try {
                                                if (th2 != TERMINAL_ERROR && !this.delayError) {
                                                    this.error = TERMINAL_ERROR;
                                                }
                                            } catch (Throwable th6) {
                                                th = th6;
                                                th = th;
                                                throw th;
                                            }
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                    }
                                }
                                th4 = th2;
                                subscriber2 = subscriber;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            while (true) {
                                Throwable th9 = th;
                                try {
                                    throw th9;
                                } catch (Throwable th10) {
                                    th = th10;
                                }
                            }
                        }
                    } catch (Throwable th11) {
                        th = th11;
                    }
                } catch (Throwable th12) {
                    th = th12;
                }
            }
        }

        protected boolean checkTerminated(boolean localMainDone, boolean localInnerActive, Throwable localError, SpscLinkedArrayQueue<Object> localQueue, Subscriber<? super T> localChild, boolean empty) {
            if (this.delayError) {
                if (localMainDone && !localInnerActive && empty) {
                    if (localError != null) {
                        localChild.onError(localError);
                    } else {
                        localChild.onCompleted();
                    }
                    return true;
                }
                return false;
            }
            if (localError != null) {
                localQueue.clear();
                localChild.onError(localError);
                return true;
            }
            if (localMainDone && !localInnerActive && empty) {
                localChild.onCompleted();
                return true;
            }
            return false;
        }
    }

    static final class InnerSubscriber<T> extends Subscriber<T> {
        private final long id;
        private final SwitchSubscriber<T> parent;

        InnerSubscriber(long id, SwitchSubscriber<T> parent) {
            this.id = id;
            this.parent = parent;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) {
            this.parent.innerProducer(p, this.id);
        }

        @Override // rx.Observer
        public void onNext(T t) throws Throwable {
            this.parent.emit(t, this);
        }

        @Override // rx.Observer
        public void onError(Throwable e) throws Throwable {
            this.parent.error(e, this.id);
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            this.parent.complete(this.id);
        }
    }
}
