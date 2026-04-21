package retrofit2.adapter.rxjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes.dex */
public final class RxJavaCallAdapterFactory extends CallAdapter.Factory {
    private final Scheduler scheduler;

    public static RxJavaCallAdapterFactory create() {
        return new RxJavaCallAdapterFactory(null);
    }

    public static RxJavaCallAdapterFactory createWithScheduler(Scheduler scheduler) {
        if (scheduler == null) {
            throw new NullPointerException("scheduler == null");
        }
        return new RxJavaCallAdapterFactory(scheduler);
    }

    private RxJavaCallAdapterFactory(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        String canonicalName = rawType.getCanonicalName();
        boolean isSingle = "rx.Single".equals(canonicalName);
        boolean isCompletable = "rx.Completable".equals(canonicalName);
        if (rawType != Observable.class && !isSingle && !isCompletable) {
            return null;
        }
        if (!isCompletable && !(returnType instanceof ParameterizedType)) {
            String name = isSingle ? "Single" : "Observable";
            throw new IllegalStateException(name + " return type must be parameterized as " + name + "<Foo> or " + name + "<? extends Foo>");
        }
        if (isCompletable) {
            return CompletableHelper.createCallAdapter(this.scheduler);
        }
        CallAdapter<Observable<?>> callAdapter = getCallAdapter(returnType, this.scheduler);
        if (isSingle) {
            return SingleHelper.makeSingle(callAdapter);
        }
        return callAdapter;
    }

    private CallAdapter<Observable<?>> getCallAdapter(Type returnType, Scheduler scheduler) {
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);
        if (rawObservableType == Response.class) {
            if (!(observableType instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            Type responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
            return new ResponseCallAdapter(responseType, scheduler);
        }
        if (rawObservableType == Result.class) {
            if (!(observableType instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            Type responseType2 = getParameterUpperBound(0, (ParameterizedType) observableType);
            return new ResultCallAdapter(responseType2, scheduler);
        }
        return new SimpleCallAdapter(observableType, scheduler);
    }

    static final class CallOnSubscribe<T> implements Observable.OnSubscribe<Response<T>> {
        private final Call<T> originalCall;

        CallOnSubscribe(Call<T> originalCall) {
            this.originalCall = originalCall;
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super Response<T>> subscriber) throws Throwable {
            Call<T> call = this.originalCall.clone();
            RequestArbiter<T> requestArbiter = new RequestArbiter<>(call, subscriber);
            subscriber.add(requestArbiter);
            subscriber.setProducer(requestArbiter);
        }
    }

    static final class RequestArbiter<T> extends AtomicBoolean implements Subscription, Producer {
        private final Call<T> call;
        private final Subscriber<? super Response<T>> subscriber;

        RequestArbiter(Call<T> call, Subscriber<? super Response<T>> subscriber) {
            this.call = call;
            this.subscriber = subscriber;
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n < 0: " + n);
            }
            if (n != 0 && compareAndSet(false, true)) {
                try {
                    Response<T> response = this.call.execute();
                    if (!this.subscriber.isUnsubscribed()) {
                        this.subscriber.onNext(response);
                    }
                    if (!this.subscriber.isUnsubscribed()) {
                        this.subscriber.onCompleted();
                    }
                } catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    if (!this.subscriber.isUnsubscribed()) {
                        this.subscriber.onError(t);
                    }
                }
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.call.cancel();
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.call.isCanceled();
        }
    }

    static final class ResponseCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        ResponseCallAdapter(Type responseType, Scheduler scheduler) {
            this.responseType = responseType;
            this.scheduler = scheduler;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.responseType;
        }

        @Override // retrofit2.CallAdapter
        public <R> Observable<Response<R>> adapt(Call<R> call) {
            Observable<Response<R>> observable = Observable.create(new CallOnSubscribe(call));
            if (this.scheduler != null) {
                return observable.subscribeOn(this.scheduler);
            }
            return observable;
        }
    }

    static final class SimpleCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        SimpleCallAdapter(Type responseType, Scheduler scheduler) {
            this.responseType = responseType;
            this.scheduler = scheduler;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.responseType;
        }

        @Override // retrofit2.CallAdapter
        public <R> Observable<R> adapt(Call<R> call) {
            Observable<R> observable = Observable.create(new CallOnSubscribe(call)).lift(OperatorMapResponseToBodyOrError.instance());
            if (this.scheduler != null) {
                return observable.subscribeOn(this.scheduler);
            }
            return observable;
        }
    }

    static final class ResultCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        ResultCallAdapter(Type responseType, Scheduler scheduler) {
            this.responseType = responseType;
            this.scheduler = scheduler;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.responseType;
        }

        @Override // retrofit2.CallAdapter
        public <R> Observable<Result<R>> adapt(Call<R> call) {
            Observable<R> observableOnErrorReturn = Observable.create(new CallOnSubscribe(call)).map(new Func1<Response<R>, Result<R>>() { // from class: retrofit2.adapter.rxjava.RxJavaCallAdapterFactory.ResultCallAdapter.2
                @Override // rx.functions.Func1
                public Result<R> call(Response<R> response) {
                    return Result.response(response);
                }
            }).onErrorReturn(new Func1<Throwable, Result<R>>() { // from class: retrofit2.adapter.rxjava.RxJavaCallAdapterFactory.ResultCallAdapter.1
                @Override // rx.functions.Func1
                public Result<R> call(Throwable throwable) {
                    return Result.error(throwable);
                }
            });
            if (this.scheduler != null) {
                return observableOnErrorReturn.subscribeOn(this.scheduler);
            }
            return observableOnErrorReturn;
        }
    }
}
