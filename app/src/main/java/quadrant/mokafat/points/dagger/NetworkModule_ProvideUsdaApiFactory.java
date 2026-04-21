package quadrant.mokafat.points.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.apiClient.ApiInterface;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkModule_ProvideUsdaApiFactory implements Factory<ApiInterface> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final NetworkModule module;
    private final Provider<Retrofit> retrofitProvider;

    public NetworkModule_ProvideUsdaApiFactory(NetworkModule module, Provider<Retrofit> retrofitProvider) {
        this.module = module;
        this.retrofitProvider = retrofitProvider;
    }

    @Override // javax.inject.Provider
    public ApiInterface get() {
        return (ApiInterface) Preconditions.checkNotNull(this.module.provideUsdaApi(this.retrofitProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ApiInterface> create(NetworkModule module, Provider<Retrofit> retrofitProvider) {
        return new NetworkModule_ProvideUsdaApiFactory(module, retrofitProvider);
    }

    public static ApiInterface proxyProvideUsdaApi(NetworkModule instance, Retrofit retrofit) {
        return instance.provideUsdaApi(retrofit);
    }
}
