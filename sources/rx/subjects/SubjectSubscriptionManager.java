package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.NotificationLite;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
final class SubjectSubscriptionManager<T> extends AtomicReference<State<T>> implements Observable.OnSubscribe<T> {
    private static final long serialVersionUID = 6035251036011671568L;
    boolean active;
    volatile Object latest;
    Action1<SubjectObserver<T>> onAdded;
    Action1<SubjectObserver<T>> onStart;
    Action1<SubjectObserver<T>> onTerminated;

    public SubjectSubscriptionManager() {
        super(State.EMPTY);
        this.active = true;
        this.onStart = Actions.empty();
        this.onAdded = Actions.empty();
        this.onTerminated = Actions.empty();
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> child) {
        SubjectObserver<T> bo = new SubjectObserver<>(child);
        addUnsubscriber(child, bo);
        this.onStart.call(bo);
        if (!child.isUnsubscribed() && add(bo) && child.isUnsubscribed()) {
            remove(bo);
        }
    }

    void addUnsubscriber(Subscriber<? super T> child, final SubjectObserver<T> bo) {
        child.add(Subscriptions.create(new Action0() { // from class: rx.subjects.SubjectSubscriptionManager.1
            @Override // rx.functions.Action0
            public void call() {
                SubjectSubscriptionManager.this.remove(bo);
            }
        }));
    }

    void setLatest(Object value) {
        this.latest = value;
    }

    Object getLatest() {
        return this.latest;
    }

    SubjectObserver<T>[] observers() {
        return get().observers;
    }

    boolean add(SubjectObserver<T> o) {
        State<T> state;
        State newState;
        do {
            state = get();
            if (state.terminated) {
                this.onTerminated.call(o);
                return false;
            }
            newState = state.add(o);
        } while (!compareAndSet(state, newState));
        this.onAdded.call(o);
        return true;
    }

    void remove(SubjectObserver<T> o) {
        State<T> state;
        State<T> stateRemove;
        do {
            state = get();
            if (state.terminated || (stateRemove = state.remove(o)) == state) {
                return;
            }
        } while (!compareAndSet(state, stateRemove));
    }

    SubjectObserver<T>[] next(Object n) {
        setLatest(n);
        return get().observers;
    }

    SubjectObserver<T>[] terminate(Object n) {
        setLatest(n);
        this.active = false;
        State<T> oldState = get();
        if (oldState.terminated) {
            return State.NO_OBSERVERS;
        }
        return getAndSet(State.TERMINATED).observers;
    }

    protected static final class State<T> {
        final SubjectObserver[] observers;
        final boolean terminated;
        static final SubjectObserver[] NO_OBSERVERS = new SubjectObserver[0];
        static final State TERMINATED = new State(true, NO_OBSERVERS);
        static final State EMPTY = new State(false, NO_OBSERVERS);

        public State(boolean terminated, SubjectObserver[] observers) {
            this.terminated = terminated;
            this.observers = observers;
        }

        public State add(SubjectObserver o) {
            SubjectObserver[] a = this.observers;
            int n = a.length;
            SubjectObserver[] b = new SubjectObserver[n + 1];
            System.arraycopy(this.observers, 0, b, 0, n);
            b[n] = o;
            return new State(this.terminated, b);
        }

        public State remove(SubjectObserver o) {
            SubjectObserver[] a = this.observers;
            int n = a.length;
            if (n == 1 && a[0] == o) {
                return EMPTY;
            }
            if (n == 0) {
                return this;
            }
            SubjectObserver[] b = new SubjectObserver[n - 1];
            int j = 0;
            for (SubjectObserver ai : a) {
                if (ai != o) {
                    if (j == n - 1) {
                        return this;
                    }
                    b[j] = ai;
                    j++;
                }
            }
            if (j == 0) {
                return EMPTY;
            }
            if (j < n - 1) {
                SubjectObserver[] c = new SubjectObserver[j];
                System.arraycopy(b, 0, c, 0, j);
                b = c;
            }
            return new State(this.terminated, b);
        }
    }

    protected static final class SubjectObserver<T> implements Observer<T> {
        final Subscriber<? super T> actual;
        volatile boolean caughtUp;
        boolean emitting;
        boolean fastPath;
        boolean first = true;
        private volatile Object index;
        List<Object> queue;

        public SubjectObserver(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.actual.onCompleted();
        }

        void emitNext(Object n) {
            if (!this.fastPath) {
                synchronized (this) {
                    this.first = false;
                    if (this.emitting) {
                        if (this.queue == null) {
                            this.queue = new ArrayList();
                        }
                        this.queue.add(n);
                        return;
                    }
                    this.fastPath = true;
                }
            }
            NotificationLite.accept(this.actual, n);
        }

        void emitFirst(Object n) {
            synchronized (this) {
                if (this.first && !this.emitting) {
                    this.first = false;
                    this.emitting = n != null;
                    if (n != null) {
                        emitLoop(null, n);
                    }
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0038 A[ORIG_RETURN, RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void emitLoop(java.util.List<java.lang.Object> r6, java.lang.Object r7) {
            /*
                r5 = this;
                r0 = 1
                r1 = 0
                r2 = r0
                r0 = r6
                r6 = r1
            L5:
                if (r0 == 0) goto L1b
                java.util.Iterator r3 = r0.iterator()     // Catch: java.lang.Throwable -> L19
            Lb:
                boolean r4 = r3.hasNext()     // Catch: java.lang.Throwable -> L19
                if (r4 == 0) goto L1b
                java.lang.Object r4 = r3.next()     // Catch: java.lang.Throwable -> L19
                r5.accept(r4)     // Catch: java.lang.Throwable -> L19
                goto Lb
            L19:
                r3 = move-exception
                goto L3e
            L1b:
                if (r2 == 0) goto L21
                r2 = 0
                r5.accept(r7)     // Catch: java.lang.Throwable -> L19
            L21:
                monitor-enter(r5)     // Catch: java.lang.Throwable -> L19
                java.util.List<java.lang.Object> r3 = r5.queue     // Catch: java.lang.Throwable -> L3b
                r0 = r3
                r3 = 0
                r5.queue = r3     // Catch: java.lang.Throwable -> L3b
                if (r0 != 0) goto L39
                r5.emitting = r1     // Catch: java.lang.Throwable -> L3b
                r6 = 1
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L3b
                if (r6 != 0) goto L38
                monitor-enter(r5)
                r5.emitting = r1     // Catch: java.lang.Throwable -> L35
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L35
                goto L38
            L35:
                r1 = move-exception
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L35
                throw r1
            L38:
                return
            L39:
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L3b
                goto L5
            L3b:
                r3 = move-exception
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L3b
                throw r3     // Catch: java.lang.Throwable -> L19
            L3e:
                if (r6 != 0) goto L48
                monitor-enter(r5)
                r5.emitting = r1     // Catch: java.lang.Throwable -> L45
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L45
                goto L48
            L45:
                r1 = move-exception
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L45
                throw r1
            L48:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.SubjectSubscriptionManager.SubjectObserver.emitLoop(java.util.List, java.lang.Object):void");
        }

        void accept(Object n) {
            if (n != null) {
                NotificationLite.accept(this.actual, n);
            }
        }

        Observer<? super T> getActual() {
            return this.actual;
        }

        public <I> I index() {
            return (I) this.index;
        }

        public void index(Object newIndex) {
            this.index = newIndex;
        }
    }
}
