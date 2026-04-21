package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* JADX INFO: loaded from: classes.dex */
public abstract class DeferredScalarSubscriber<T, R> extends Subscriber<T> {
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 1;
    static final int NO_REQUEST_HAS_VALUE = 2;
    static final int NO_REQUEST_NO_VALUE = 0;
    protected final Subscriber<? super R> actual;
    protected boolean hasValue;
    final AtomicInteger state = new AtomicInteger();
    protected R value;

    public DeferredScalarSubscriber(Subscriber<? super R> actual) {
        this.actual = actual;
    }

    @Override // rx.Observer
    public void onError(Throwable ex) {
        this.value = null;
        this.actual.onError(ex);
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            complete();
        }
    }

    protected final void complete() {
        this.actual.onCompleted();
    }

    protected final void complete(R value) {
        Subscriber<? super R> a = this.actual;
        do {
            int s = this.state.get();
            if (s == 2 || s == 3 || a.isUnsubscribed()) {
                return;
            }
            if (s == 1) {
                a.onNext(value);
                if (!a.isUnsubscribed()) {
                    a.onCompleted();
                }
                this.state.lazySet(3);
                return;
            }
            this.value = value;
        } while (!this.state.compareAndSet(0, 2));
    }

    final void downstreamRequest(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + j);
        }
        if (j != 0) {
            Subscriber<? super R> subscriber = this.actual;
            do {
                int i = this.state.get();
                if (i == 1 || i == 3 || subscriber.isUnsubscribed()) {
                    return;
                }
                if (i == 2) {
                    if (this.state.compareAndSet(2, 3)) {
                        subscriber.onNext(this.value);
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onCompleted();
                            return;
                        }
                        return;
                    }
                    return;
                }
            } while (!this.state.compareAndSet(0, 1));
        }
    }

    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
    public final void setProducer(Producer p) {
        p.request(LongCompanionObject.MAX_VALUE);
    }

    public final void subscribeTo(Observable<? extends T> source) throws Throwable {
        setupDownstream();
        source.unsafeSubscribe(this);
    }

    final void setupDownstream() throws Throwable {
        Subscriber<? super R> a = this.actual;
        a.add(this);
        a.setProducer(new InnerProducer(this));
    }

    static final class InnerProducer implements Producer {
        final DeferredScalarSubscriber<?, ?> parent;

        public InnerProducer(DeferredScalarSubscriber<?, ?> parent) {
            this.parent = parent;
        }

        @Override // rx.Producer
        public void request(long n) {
            this.parent.downstreamRequest(n);
        }
    }
}
