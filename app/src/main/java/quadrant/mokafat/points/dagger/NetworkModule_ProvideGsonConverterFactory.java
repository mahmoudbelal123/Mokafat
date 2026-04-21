package quadrant.mokafat.points.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import retrofit2.Converter;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkModule_ProvideGsonConverterFactory implements Factory<Converter.Factory> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final NetworkModule module;

    public NetworkModule_ProvideGsonConverterFactory(NetworkModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public Converter.Factory get() {
        return (Converter.Factory) Preconditions.checkNotNull(this.module.provideGsonConverter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Converter.Factory> create(NetworkModule module) {
        return new NetworkModule_ProvideGsonConverterFactory(module);
    }

    public static Converter.Factory proxyProvideGsonConverter(NetworkModule instance) {
        return instance.provideGsonConverter();
    }
}
