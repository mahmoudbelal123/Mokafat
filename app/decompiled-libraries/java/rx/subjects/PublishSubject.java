package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.BackpressureUtils;

/* JADX INFO: loaded from: classes.dex */
public final class PublishSubject<T> extends Subject<T, T> {
    final PublishSubjectState<T> state;

    public static <T> PublishSubject<T> create() {
        return new PublishSubject<>(new PublishSubjectState());
    }

    protected PublishSubject(PublishSubjectState<T> state) {
        super(state);
        this.state = state;
    }

    @Override // rx.Observer
    public void onNext(T v) {
        this.state.onNext(v);
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        this.state.onError(e);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.get().length != 0;
    }

    public boolean hasThrowable() {
        return this.state.get() == PublishSubjectState.TERMINATED && this.state.error != null;
    }

    public boolean hasCompleted() {
        return this.state.get() == PublishSubjectState.TERMINATED && this.state.error == null;
    }

    public Throwable getThrowable() {
        if (this.state.get() == PublishSubjectState.TERMINATED) {
            return this.state.error;
        }
        return null;
    }

    static final class PublishSubjectState<T> extends AtomicReference<PublishSubjectProducer<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        static final PublishSubjectProducer[] EMPTY = new PublishSubjectProducer[0];
        static final PublishSubjectProducer[] TERMINATED = new PublishSubjectProducer[0];
        private static final long serialVersionUID = -7568940796666027140L;
        Throwable error;

        public PublishSubjectState() {
            lazySet(EMPTY);
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super T> t) throws Throwable {
            PublishSubjectProducer<T> pp = new PublishSubjectProducer<>(this, t);
            t.add(pp);
            t.setProducer(pp);
            if (add(pp)) {
                if (pp.isUnsubscribed()) {
                    remove(pp);
                }
            } else {
                Throwable ex = this.error;
                if (ex != null) {
                    t.onError(ex);
                } else {
                    t.onCompleted();
                }
            }
        }

        boolean add(PublishSubjectProducer<T> inner) {
            PublishSubjectProducer<T>[] curr;
            PublishSubjectProducer<T>[] next;
            do {
                curr = get();
                if (curr == TERMINATED) {
                    return false;
                }
                int n = curr.length;
                next = new PublishSubjectProducer[n + 1];
                System.arraycopy(curr, 0, next, 0, n);
                next[n] = inner;
            } while (!compareAndSet(curr, next));
            return true;
        }

        void remove(PublishSubjectProducer<T> inner) {
            PublishSubjectProducer<T>[] curr;
            PublishSubjectProducer<T>[] next;
            do {
                curr = get();
                if (curr == TERMINATED || curr == EMPTY) {
                    return;
                }
                int n = curr.length;
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    }
                    if (curr[i] != inner) {
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
                    next = EMPTY;
                } else {
                    PublishSubjectProducer<T>[] next2 = new PublishSubjectProducer[n - 1];
                    System.arraycopy(curr, 0, next2, 0, j);
                    System.arraycopy(curr, j + 1, next2, j, (n - j) - 1);
                    next = next2;
                }
            } while (!compareAndSet(curr, next));
        }

        @Override // rx.Observer
        public void onNext(T t) {
            for (PublishSubjectProducer<T> pp : get()) {
                pp.onNext(t);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.error = e;
            List<Throwable> errors = null;
            for (PublishSubjectProducer<T> pp : getAndSet(TERMINATED)) {
                try {
                    pp.onError(e);
                } catch (Throwable ex) {
                    if (errors == null) {
                        errors = new ArrayList<>(1);
                    }
                    errors.add(ex);
                }
            }
            Exceptions.throwIfAny(errors);
        }

        @Override // rx.Observer
        public void onCompleted() {
            for (PublishSubjectProducer<T> pp : getAndSet(TERMINATED)) {
                pp.onCompleted();
            }
        }
    }

    static final class PublishSubjectProducer<T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        private static final long serialVersionUID = 6451806817170721536L;
        final Subscriber<? super T> actual;
        final PublishSubjectState<T> parent;
        long produced;

        public PublishSubjectProducer(PublishSubjectState<T> parent, Subscriber<? super T> actual) {
            this.parent = parent;
            this.actual = actual;
        }

        @Override // rx.Producer
        public void request(long n) {
            long r;
            long u;
            if (BackpressureUtils.validate(n)) {
                do {
                    r = get();
                    if (r == Long.MIN_VALUE) {
                        return;
                    } else {
                        u = BackpressureUtils.addCap(r, n);
                    }
                } while (!compareAndSet(r, u));
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long r = get();
            if (r != Long.MIN_VALUE) {
                long p = this.produced;
                if (r != p) {
                    this.produced = p + 1;
                    this.actual.onNext(t);
                } else {
                    unsubscribe();
                    this.actual.onError(new MissingBackpressureException("PublishSubject: could not emit value due to lack of requests"));
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (get() != Long.MIN_VALUE) {
                this.actual.onError(e);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (get() != Long.MIN_VALUE) {
                this.actual.onCompleted();
            }
        }
    }
}
