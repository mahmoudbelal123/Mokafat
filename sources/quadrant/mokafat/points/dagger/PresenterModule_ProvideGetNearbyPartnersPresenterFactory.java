package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.GetNearbyPartnersPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideGetNearbyPartnersPresenterFactory implements Factory<GetNearbyPartnersPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideGetNearbyPartnersPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public GetNearbyPartnersPresenter get() {
        return (GetNearbyPartnersPresenter) Preconditions.checkNotNull(this.module.provideGetNearbyPartnersPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GetNearbyPartnersPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideGetNearbyPartnersPresenterFactory(module, contextProvider);
    }

    public static GetNearbyPartnersPresenter proxyProvideGetNearbyPartnersPresenter(PresenterModule instance, Context context) {
        return instance.provideGetNearbyPartnersPresenter(context);
    }
}
