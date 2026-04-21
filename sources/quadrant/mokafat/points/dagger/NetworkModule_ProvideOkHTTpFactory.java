package quadrant.mokafat.points.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkModule_ProvideOkHTTpFactory implements Factory<OkHttpClient> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Interceptor> interceptorProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideOkHTTpFactory(NetworkModule module, Provider<Interceptor> interceptorProvider) {
        this.module = module;
        this.interceptorProvider = interceptorProvider;
    }

    @Override // javax.inject.Provider
    public OkHttpClient get() {
        return (OkHttpClient) Preconditions.checkNotNull(this.module.provideOkHTTp(this.interceptorProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<OkHttpClient> create(NetworkModule module, Provider<Interceptor> interceptorProvider) {
        return new NetworkModule_ProvideOkHTTpFactory(module, interceptorProvider);
    }

    public static OkHttpClient proxyProvideOkHTTp(NetworkModule instance, Interceptor interceptor) {
        return instance.provideOkHTTp(interceptor);
    }
}
