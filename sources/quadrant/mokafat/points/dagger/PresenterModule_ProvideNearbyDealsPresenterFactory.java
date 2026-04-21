package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideNearbyDealsPresenterFactory implements Factory<GetNearbyDealsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideNearbyDealsPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public GetNearbyDealsPresenter get() {
        return (GetNearbyDealsPresenter) Preconditions.checkNotNull(this.module.provideNearbyDealsPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GetNearbyDealsPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideNearbyDealsPresenterFactory(module, contextProvider);
    }

    public static GetNearbyDealsPresenter proxyProvideNearbyDealsPresenter(PresenterModule instance, Context context) {
        return instance.provideNearbyDealsPresenter(context);
    }
}
