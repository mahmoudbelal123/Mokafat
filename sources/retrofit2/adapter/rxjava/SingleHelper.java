package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import rx.Observable;
import rx.Single;

/* JADX INFO: loaded from: classes.dex */
final class SingleHelper {
    SingleHelper() {
    }

    static CallAdapter<Single<?>> makeSingle(final CallAdapter<Observable<?>> callAdapter) {
        return new CallAdapter<Single<?>>() { // from class: retrofit2.adapter.rxjava.SingleHelper.1
            @Override // retrofit2.CallAdapter
            public Type responseType() {
                return callAdapter.responseType();
            }

            @Override // retrofit2.CallAdapter
            public <R> Single<?> adapt(Call<R> call) {
                Observable<?> observable = (Observable) callAdapter.adapt(call);
                return observable.toSingle();
            }
        };
    }
}
