package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideGetItemsPresenterFactory implements Factory<GetItemsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideGetItemsPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public GetItemsPresenter get() {
        return (GetItemsPresenter) Preconditions.checkNotNull(this.module.provideGetItemsPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GetItemsPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideGetItemsPresenterFactory(module, contextProvider);
    }

    public static GetItemsPresenter proxyProvideGetItemsPresenter(PresenterModule instance, Context context) {
        return instance.provideGetItemsPresenter(context);
    }
}
