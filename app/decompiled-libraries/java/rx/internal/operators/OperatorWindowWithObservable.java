package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;
import rx.subjects.UnicastSubject;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorWindowWithObservable<T, U> implements Observable.Operator<Observable<T>, T> {
    static final Object NEXT_SUBJECT = new Object();
    final Observable<U> other;

    public OperatorWindowWithObservable(Observable<U> other) {
        this.other = other;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super Observable<T>> child) throws Throwable {
        SourceSubscriber<T> sub = new SourceSubscriber<>(child);
        BoundarySubscriber<T, U> bs = new BoundarySubscriber<>(sub);
        child.add(sub);
        child.add(bs);
        sub.replaceWindow();
        this.other.unsafeSubscribe(bs);
        return sub;
    }

    static final class SourceSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        Observer<T> consumer;
        boolean emitting;
        final Object guard = new Object();
        Observable<T> producer;
        List<Object> queue;

        public SourceSubscriber(Subscriber<? super Observable<T>> child) {
            this.child = new SerializedSubscriber(child);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(LongCompanionObject.MAX_VALUE);
        }

        /* JADX WARN: Removed duplicated region for block: B:54:0x0066  */
        @Override // rx.Observer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onNext(T r9) throws java.lang.Throwable {
            /*
                r8 = this;
                java.lang.Object r0 = r8.guard
                monitor-enter(r0)
                r1 = 0
                boolean r2 = r8.emitting     // Catch: java.lang.Throwable -> L76
                if (r2 == 0) goto L1a
                java.util.List<java.lang.Object> r2 = r8.queue     // Catch: java.lang.Throwable -> L76
                if (r2 != 0) goto L13
                java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L76
                r2.<init>()     // Catch: java.lang.Throwable -> L76
                r8.queue = r2     // Catch: java.lang.Throwable -> L76
            L13:
                java.util.List<java.lang.Object> r2 = r8.queue     // Catch: java.lang.Throwable -> L76
                r2.add(r9)     // Catch: java.lang.Throwable -> L76
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L76
                return
            L1a:
                java.util.List<java.lang.Object> r2 = r8.queue     // Catch: java.lang.Throwable -> L76
                r8.queue = r1     // Catch: java.lang.Throwable -> L71
                r3 = 1
                r8.emitting = r3     // Catch: java.lang.Throwable -> L71
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L71
                r0 = 1
                r3 = 0
                r4 = r0
                r0 = r3
            L26:
                r8.drain(r2)     // Catch: java.lang.Throwable -> L63
                if (r4 == 0) goto L2f
                r4 = 0
                r8.emitValue(r9)     // Catch: java.lang.Throwable -> L63
            L2f:
                java.lang.Object r5 = r8.guard     // Catch: java.lang.Throwable -> L63
                monitor-enter(r5)     // Catch: java.lang.Throwable -> L63
                java.util.List<java.lang.Object> r6 = r8.queue     // Catch: java.lang.Throwable -> L60
                r2 = r6
                r8.queue = r1     // Catch: java.lang.Throwable -> L60
                if (r2 != 0) goto L4a
                r8.emitting = r3     // Catch: java.lang.Throwable -> L60
                r0 = 1
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L60
                if (r0 != 0) goto L49
                java.lang.Object r1 = r8.guard
                monitor-enter(r1)
                r8.emitting = r3     // Catch: java.lang.Throwable -> L46
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L46
                goto L49
            L46:
                r3 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L46
                throw r3
            L49:
                return
            L4a:
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L60
                rx.Subscriber<? super rx.Observable<T>> r5 = r8.child     // Catch: java.lang.Throwable -> L63
                boolean r5 = r5.isUnsubscribed()     // Catch: java.lang.Throwable -> L63
                if (r5 == 0) goto L26
                if (r0 != 0) goto L5f
                java.lang.Object r1 = r8.guard
                monitor-enter(r1)
                r8.emitting = r3     // Catch: java.lang.Throwable -> L5c
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L5c
                goto L5f
            L5c:
                r3 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L5c
                throw r3
            L5f:
                return
            L60:
                r1 = move-exception
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L60
                throw r1     // Catch: java.lang.Throwable -> L63
            L63:
                r1 = move-exception
                if (r0 != 0) goto L70
                java.lang.Object r5 = r8.guard
                monitor-enter(r5)
                r8.emitting = r3     // Catch: java.lang.Throwable -> L6d
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L6d
                goto L70
            L6d:
                r1 = move-exception
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L6d
                throw r1
            L70:
                throw r1
            L71:
                r1 = move-exception
                r7 = r2
                r2 = r1
                r1 = r7
                goto L77
            L76:
                r2 = move-exception
            L77:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L76
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorWindowWithObservable.SourceSubscriber.onNext(java.lang.Object):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain(List<Object> queue) {
            if (queue == null) {
                return;
            }
            for (Object o : queue) {
                if (o == OperatorWindowWithObservable.NEXT_SUBJECT) {
                    replaceSubject();
                } else if (NotificationLite.isError(o)) {
                    error(NotificationLite.getError(o));
                    return;
                } else {
                    if (NotificationLite.isCompleted(o)) {
                        complete();
                        return;
                    }
                    emitValue(o);
                }
            }
        }

        void replaceSubject() {
            Observer<T> s = this.consumer;
            if (s != null) {
                s.onCompleted();
            }
            createNewWindow();
            this.child.onNext(this.producer);
        }

        void createNewWindow() {
            UnicastSubject<T> bus = UnicastSubject.create();
            this.consumer = bus;
            this.producer = bus;
        }

        void emitValue(T t) {
            Observer<T> s = this.consumer;
            if (s != null) {
                s.onNext(t);
            }
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

        /* JADX WARN: Removed duplicated region for block: B:54:0x0068  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void replaceWindow() throws java.lang.Throwable {
            /*
                r8 = this;
                java.lang.Object r0 = r8.guard
                monitor-enter(r0)
                r1 = 0
                boolean r2 = r8.emitting     // Catch: java.lang.Throwable -> L78
                if (r2 == 0) goto L1c
                java.util.List<java.lang.Object> r2 = r8.queue     // Catch: java.lang.Throwable -> L78
                if (r2 != 0) goto L13
                java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L78
                r2.<init>()     // Catch: java.lang.Throwable -> L78
                r8.queue = r2     // Catch: java.lang.Throwable -> L78
            L13:
                java.util.List<java.lang.Object> r2 = r8.queue     // Catch: java.lang.Throwable -> L78
                java.lang.Object r3 = rx.internal.operators.OperatorWindowWithObservable.NEXT_SUBJECT     // Catch: java.lang.Throwable -> L78
                r2.add(r3)     // Catch: java.lang.Throwable -> L78
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L78
                return
            L1c:
                java.util.List<java.lang.Object> r2 = r8.queue     // Catch: java.lang.Throwable -> L78
                r8.queue = r1     // Catch: java.lang.Throwable -> L73
                r3 = 1
                r8.emitting = r3     // Catch: java.lang.Throwable -> L73
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L73
                r0 = 1
                r3 = 0
                r4 = r0
                r0 = r3
            L28:
                r8.drain(r2)     // Catch: java.lang.Throwable -> L65
                if (r4 == 0) goto L31
                r4 = 0
                r8.replaceSubject()     // Catch: java.lang.Throwable -> L65
            L31:
                java.lang.Object r5 = r8.guard     // Catch: java.lang.Throwable -> L65
                monitor-enter(r5)     // Catch: java.lang.Throwable -> L65
                java.util.List<java.lang.Object> r6 = r8.queue     // Catch: java.lang.Throwable -> L62
                r2 = r6
                r8.queue = r1     // Catch: java.lang.Throwable -> L62
                if (r2 != 0) goto L4c
                r8.emitting = r3     // Catch: java.lang.Throwable -> L62
                r0 = 1
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L62
                if (r0 != 0) goto L4b
                java.lang.Object r1 = r8.guard
                monitor-enter(r1)
                r8.emitting = r3     // Catch: java.lang.Throwable -> L48
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L48
                goto L4b
            L48:
                r3 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L48
                throw r3
            L4b:
                return
            L4c:
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L62
                rx.Subscriber<? super rx.Observable<T>> r5 = r8.child     // Catch: java.lang.Throwable -> L65
                boolean r5 = r5.isUnsubscribed()     // Catch: java.lang.Throwable -> L65
                if (r5 == 0) goto L28
                if (r0 != 0) goto L61
                java.lang.Object r1 = r8.guard
                monitor-enter(r1)
                r8.emitting = r3     // Catch: java.lang.Throwable -> L5e
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L5e
                goto L61
            L5e:
                r3 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L5e
                throw r3
            L61:
                return
            L62:
                r1 = move-exception
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L62
                throw r1     // Catch: java.lang.Throwable -> L65
            L65:
                r1 = move-exception
                if (r0 != 0) goto L72
                java.lang.Object r5 = r8.guard
                monitor-enter(r5)
                r8.emitting = r3     // Catch: java.lang.Throwable -> L6f
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L6f
                goto L72
            L6f:
                r1 = move-exception
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L6f
                throw r1
            L72:
                throw r1
            L73:
                r1 = move-exception
                r7 = r2
                r2 = r1
                r1 = r7
                goto L79
            L78:
                r2 = move-exception
            L79:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L78
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorWindowWithObservable.SourceSubscriber.replaceWindow():void");
        }

        void complete() {
            Observer<T> s = this.consumer;
            this.consumer = null;
            this.producer = null;
            if (s != null) {
                s.onCompleted();
            }
            this.child.onCompleted();
            unsubscribe();
        }

        void error(Throwable e) {
            Observer<T> s = this.consumer;
            this.consumer = null;
            this.producer = null;
            if (s != null) {
                s.onError(e);
            }
            this.child.onError(e);
            unsubscribe();
        }
    }

    static final class BoundarySubscriber<T, U> extends Subscriber<U> {
        final SourceSubscriber<T> sub;

        public BoundarySubscriber(SourceSubscriber<T> sub) {
            this.sub = sub;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(LongCompanionObject.MAX_VALUE);
        }

        @Override // rx.Observer
        public void onNext(U t) throws Throwable {
            this.sub.replaceWindow();
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.sub.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            this.sub.onCompleted();
        }
    }
}
