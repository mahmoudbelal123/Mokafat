package rx.internal.observers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Producer;
import rx.Subscriber;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.observers.AssertableSubscriber;
import rx.observers.TestSubscriber;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public class AssertableSubscriberObservable<T> extends Subscriber<T> implements AssertableSubscriber<T> {
    private final TestSubscriber<T> ts;

    public AssertableSubscriberObservable(TestSubscriber<T> ts) {
        this.ts = ts;
    }

    public static <T> AssertableSubscriberObservable<T> create(long initialRequest) {
        TestSubscriber<T> t1 = new TestSubscriber<>(initialRequest);
        AssertableSubscriberObservable<T> t2 = new AssertableSubscriberObservable<>(t1);
        t2.add(t1);
        return t2;
    }

    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
    public void onStart() {
        this.ts.onStart();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.ts.onCompleted();
    }

    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
    public void setProducer(Producer p) {
        this.ts.setProducer(p);
    }

    @Override // rx.observers.AssertableSubscriber
    public final int getCompletions() {
        return this.ts.getCompletions();
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        this.ts.onError(e);
    }

    @Override // rx.observers.AssertableSubscriber
    public List<Throwable> getOnErrorEvents() {
        return this.ts.getOnErrorEvents();
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.ts.onNext(t);
    }

    @Override // rx.observers.AssertableSubscriber
    public final int getValueCount() {
        return this.ts.getValueCount();
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> requestMore(long n) {
        this.ts.requestMore(n);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public List<T> getOnNextEvents() {
        return this.ts.getOnNextEvents();
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertReceivedOnNext(List<T> items) {
        this.ts.assertReceivedOnNext(items);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> awaitValueCount(int expected, long timeout, TimeUnit unit) {
        if (!this.ts.awaitValueCount(expected, timeout, unit)) {
            throw new AssertionError("Did not receive enough values in time. Expected: " + expected + ", Actual: " + this.ts.getValueCount());
        }
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertTerminalEvent() {
        this.ts.assertTerminalEvent();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertUnsubscribed() {
        this.ts.assertUnsubscribed();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoErrors() {
        this.ts.assertNoErrors();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEvent() {
        this.ts.awaitTerminalEvent();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEvent(long timeout, TimeUnit unit) {
        this.ts.awaitTerminalEvent(timeout, unit);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEventAndUnsubscribeOnTimeout(long timeout, TimeUnit unit) {
        this.ts.awaitTerminalEventAndUnsubscribeOnTimeout(timeout, unit);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public Thread getLastSeenThread() {
        return this.ts.getLastSeenThread();
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertCompleted() {
        this.ts.assertCompleted();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNotCompleted() {
        this.ts.assertNotCompleted();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertError(Class<? extends Throwable> clazz) {
        this.ts.assertError(clazz);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertError(Throwable throwable) {
        this.ts.assertError(throwable);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoTerminalEvent() {
        this.ts.assertNoTerminalEvent();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoValues() {
        this.ts.assertNoValues();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValueCount(int count) {
        this.ts.assertValueCount(count);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValues(T... values) {
        this.ts.assertValues(values);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValue(T value) {
        this.ts.assertValue(value);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertValuesAndClear(T expectedFirstValue, T... expectedRestValues) {
        this.ts.assertValuesAndClear(expectedFirstValue, expectedRestValues);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> perform(Action0 action) {
        action.call();
        return this;
    }

    public String toString() {
        return this.ts.toString();
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertResult(T... values) {
        this.ts.assertValues(values);
        this.ts.assertNoErrors();
        this.ts.assertCompleted();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertFailure(Class<? extends Throwable> errorClass, T... values) {
        this.ts.assertValues(values);
        this.ts.assertError(errorClass);
        this.ts.assertNotCompleted();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertFailureAndMessage(Class<? extends Throwable> errorClass, String message, T... values) {
        this.ts.assertValues(values);
        this.ts.assertError(errorClass);
        this.ts.assertNotCompleted();
        String actualMessage = this.ts.getOnErrorEvents().get(0).getMessage();
        if (actualMessage != message && (message == null || !message.equals(actualMessage))) {
            throw new AssertionError("Error message differs. Expected: '" + message + "', Received: '" + actualMessage + "'");
        }
        return this;
    }
}
