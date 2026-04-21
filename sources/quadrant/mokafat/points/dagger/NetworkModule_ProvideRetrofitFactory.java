package quadrant.mokafat.points.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkModule_ProvideRetrofitFactory implements Factory<Retrofit> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<String> baseUrlProvider;
    private final Provider<Converter.Factory> converterProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideRetrofitFactory(NetworkModule module, Provider<String> baseUrlProvider, Provider<Converter.Factory> converterProvider) {
        this.module = module;
        this.baseUrlProvider = baseUrlProvider;
        this.converterProvider = converterProvider;
    }

    @Override // javax.inject.Provider
    public Retrofit get() {
        return (Retrofit) Preconditions.checkNotNull(this.module.provideRetrofit(this.baseUrlProvider.get(), this.converterProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Retrofit> create(NetworkModule module, Provider<String> baseUrlProvider, Provider<Converter.Factory> converterProvider) {
        return new NetworkModule_ProvideRetrofitFactory(module, baseUrlProvider, converterProvider);
    }

    public static Retrofit proxyProvideRetrofit(NetworkModule instance, String baseUrl, Converter.Factory converter) {
        return instance.provideRetrofit(baseUrl, converter);
    }
}
