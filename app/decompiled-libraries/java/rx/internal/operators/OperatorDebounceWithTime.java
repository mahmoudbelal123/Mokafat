package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorDebounceWithTime<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    public OperatorDebounceWithTime(long timeout, TimeUnit unit, Scheduler scheduler) {
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> child) {
        Scheduler.Worker worker = this.scheduler.createWorker();
        SerializedSubscriber<T> s = new SerializedSubscriber<>(child);
        SerialSubscription serial = new SerialSubscription();
        s.add(worker);
        s.add(serial);
        return new AnonymousClass1(child, serial, worker, s);
    }

    /* JADX INFO: renamed from: rx.internal.operators.OperatorDebounceWithTime$1, reason: invalid class name */
    class AnonymousClass1 extends Subscriber<T> {
        final Subscriber<?> self;
        final DebounceState<T> state;
        final /* synthetic */ SerializedSubscriber val$s;
        final /* synthetic */ SerialSubscription val$serial;
        final /* synthetic */ Scheduler.Worker val$worker;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Subscriber subscriber, SerialSubscription serialSubscription, Scheduler.Worker worker, SerializedSubscriber serializedSubscriber) {
            super(subscriber);
            this.val$serial = serialSubscription;
            this.val$worker = worker;
            this.val$s = serializedSubscriber;
            this.state = new DebounceState<>();
            this.self = this;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(LongCompanionObject.MAX_VALUE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            final int index = this.state.next(t);
            this.val$serial.set(this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDebounceWithTime.1.1
                @Override // rx.functions.Action0
                public void call() throws Throwable {
                    AnonymousClass1.this.state.emit(index, AnonymousClass1.this.val$s, AnonymousClass1.this.self);
                }
            }, OperatorDebounceWithTime.this.timeout, OperatorDebounceWithTime.this.unit));
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.val$s.onError(e);
            unsubscribe();
            this.state.clear();
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            this.state.emitAndComplete(this.val$s, this);
        }
    }

    static final class DebounceState<T> {
        boolean emitting;
        boolean hasValue;
        int index;
        boolean terminate;
        T value;

        DebounceState() {
        }

        public synchronized int next(T value) {
            int i;
            this.value = value;
            this.hasValue = true;
            i = this.index + 1;
            this.index = i;
            return i;
        }

        public void emit(int index, Subscriber<T> onNextAndComplete, Subscriber<?> onError) throws Throwable {
            synchronized (this) {
                try {
                    if (!this.emitting && this.hasValue && index == this.index) {
                        T localValue = this.value;
                        try {
                            this.value = null;
                            this.hasValue = false;
                            this.emitting = true;
                            try {
                                onNextAndComplete.onNext(localValue);
                                synchronized (this) {
                                    if (this.terminate) {
                                        onNextAndComplete.onCompleted();
                                    } else {
                                        this.emitting = false;
                                    }
                                }
                            } catch (Throwable e) {
                                Exceptions.throwOrReport(e, onError, localValue);
                            }
                        } catch (Throwable e2) {
                            th = e2;
                            throw th;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                }
            }
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:27:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
            */
        public void emitAndComplete(rx.Subscriber<T> r7, rx.Subscriber<?> r8) throws java.lang.Throwable {
            /*
                r6 = this;
                monitor-enter(r6)
                r0 = 0
                r1 = 0
                boolean r2 = r6.emitting     // Catch: java.lang.Throwable -> L29
                r3 = 1
                if (r2 == 0) goto Lc
                r6.terminate = r3     // Catch: java.lang.Throwable -> L29
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L29
                return
            Lc:
                T r2 = r6.value     // Catch: java.lang.Throwable -> L29
                boolean r4 = r6.hasValue     // Catch: java.lang.Throwable -> L2f
                r6.value = r0     // Catch: java.lang.Throwable -> L26
                r6.hasValue = r1     // Catch: java.lang.Throwable -> L26
                r6.emitting = r3     // Catch: java.lang.Throwable -> L26
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L26
                if (r4 == 0) goto L22
                r7.onNext(r2)     // Catch: java.lang.Throwable -> L1d
                goto L22
            L1d:
                r0 = move-exception
                rx.exceptions.Exceptions.throwOrReport(r0, r8, r2)
                return
            L22:
                r7.onCompleted()
                return
            L26:
                r0 = move-exception
                r1 = r4
                goto L2d
            L29:
                r2 = move-exception
                r5 = r2
                r2 = r0
                r0 = r5
            L2d:
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L2f
                throw r0
            L2f:
                r0 = move-exception
                goto L2d
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorDebounceWithTime.DebounceState.emitAndComplete(rx.Subscriber, rx.Subscriber):void");
        }

        public synchronized void clear() {
            this.index++;
            this.value = null;
            this.hasValue = false;
        }
    }
}
