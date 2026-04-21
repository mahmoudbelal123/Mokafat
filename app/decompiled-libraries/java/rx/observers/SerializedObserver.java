package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;

/* JADX INFO: loaded from: classes.dex */
public class SerializedObserver<T> implements Observer<T> {
    private final Observer<? super T> actual;
    private boolean emitting;
    private FastList queue;
    private volatile boolean terminated;

    static final class FastList {
        Object[] array;
        int size;

        FastList() {
        }

        public void add(Object o) {
            int s = this.size;
            Object[] a = this.array;
            if (a == null) {
                a = new Object[16];
                this.array = a;
            } else if (s == a.length) {
                Object[] array2 = new Object[(s >> 2) + s];
                System.arraycopy(a, 0, array2, 0, s);
                a = array2;
                this.array = a;
            }
            a[s] = o;
            this.size = s + 1;
        }
    }

    public SerializedObserver(Observer<? super T> s) {
        this.actual = s;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0030, code lost:
    
        continue;
     */
    @Override // rx.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onNext(T r9) {
        /*
            r8 = this;
            boolean r0 = r8.terminated
            if (r0 == 0) goto L5
            return
        L5:
            monitor-enter(r8)
            boolean r0 = r8.terminated     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto Lc
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L75
            return
        Lc:
            boolean r0 = r8.emitting     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L25
            rx.observers.SerializedObserver$FastList r0 = r8.queue     // Catch: java.lang.Throwable -> L75
            if (r0 != 0) goto L1c
            rx.observers.SerializedObserver$FastList r1 = new rx.observers.SerializedObserver$FastList     // Catch: java.lang.Throwable -> L75
            r1.<init>()     // Catch: java.lang.Throwable -> L75
            r0 = r1
            r8.queue = r0     // Catch: java.lang.Throwable -> L75
        L1c:
            java.lang.Object r1 = rx.internal.operators.NotificationLite.next(r9)     // Catch: java.lang.Throwable -> L75
            r0.add(r1)     // Catch: java.lang.Throwable -> L75
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L75
            return
        L25:
            r0 = 1
            r8.emitting = r0     // Catch: java.lang.Throwable -> L75
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L75
            rx.Observer<? super T> r1 = r8.actual     // Catch: java.lang.Throwable -> L6c
            r1.onNext(r9)     // Catch: java.lang.Throwable -> L6c
            r1 = r8
        L30:
            monitor-enter(r8)
            rx.observers.SerializedObserver$FastList r2 = r8.queue     // Catch: java.lang.Throwable -> L69
            r1 = r2
            r2 = 0
            if (r1 != 0) goto L3b
            r8.emitting = r2     // Catch: java.lang.Throwable -> L69
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L69
            return
        L3b:
            r3 = 0
            r8.queue = r3     // Catch: java.lang.Throwable -> L69
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L69
            java.lang.Object[] r3 = r1.array
            int r4 = r3.length
        L42:
            if (r2 >= r4) goto L68
            r5 = r3[r2]
            if (r5 != 0) goto L49
            goto L68
        L49:
            rx.Observer<? super T> r6 = r8.actual     // Catch: java.lang.Throwable -> L58
            boolean r6 = rx.internal.operators.NotificationLite.accept(r6, r5)     // Catch: java.lang.Throwable -> L58
            if (r6 == 0) goto L54
            r8.terminated = r0     // Catch: java.lang.Throwable -> L58
            return
        L54:
            int r2 = r2 + 1
            goto L42
        L58:
            r6 = move-exception
            r8.terminated = r0
            rx.exceptions.Exceptions.throwIfFatal(r6)
            rx.Observer<? super T> r0 = r8.actual
            java.lang.Throwable r7 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r6, r9)
            r0.onError(r7)
            return
        L68:
            goto L30
        L69:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L69
            throw r0
        L6c:
            r1 = move-exception
            r8.terminated = r0
            rx.Observer<? super T> r0 = r8.actual
            rx.exceptions.Exceptions.throwOrReport(r1, r0, r9)
            return
        L75:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L75
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.observers.SerializedObserver.onNext(java.lang.Object):void");
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        Exceptions.throwIfFatal(e);
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            if (this.terminated) {
                return;
            }
            this.terminated = true;
            if (this.emitting) {
                FastList list = this.queue;
                if (list == null) {
                    list = new FastList();
                    this.queue = list;
                }
                list.add(NotificationLite.error(e));
                return;
            }
            this.emitting = true;
            this.actual.onError(e);
        }
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            if (this.terminated) {
                return;
            }
            this.terminated = true;
            if (this.emitting) {
                FastList list = this.queue;
                if (list == null) {
                    list = new FastList();
                    this.queue = list;
                }
                list.add(NotificationLite.completed());
                return;
            }
            this.emitting = true;
            this.actual.onCompleted();
        }
    }
}
