package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideHotDealsSectionPresenterFactory implements Factory<HotDealsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideHotDealsSectionPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public HotDealsPresenter get() {
        return (HotDealsPresenter) Preconditions.checkNotNull(this.module.provideHotDealsSectionPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<HotDealsPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideHotDealsSectionPresenterFactory(module, contextProvider);
    }

    public static HotDealsPresenter proxyProvideHotDealsSectionPresenter(PresenterModule instance, Context context) {
        return instance.provideHotDealsSectionPresenter(context);
    }
}
