package quadrant.mokafat.points.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkModule_ProvideBaseUrlStringFactory implements Factory<String> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final NetworkModule module;

    public NetworkModule_ProvideBaseUrlStringFactory(NetworkModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public String get() {
        return (String) Preconditions.checkNotNull(this.module.provideBaseUrlString(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> create(NetworkModule module) {
        return new NetworkModule_ProvideBaseUrlStringFactory(module);
    }

    public static String proxyProvideBaseUrlString(NetworkModule instance) {
        return instance.provideBaseUrlString();
    }
}
