package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVoucherTabsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideVoucherTabsPresenterFactory implements Factory<GetVoucherTabsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideVoucherTabsPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public GetVoucherTabsPresenter get() {
        return (GetVoucherTabsPresenter) Preconditions.checkNotNull(this.module.provideVoucherTabsPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GetVoucherTabsPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideVoucherTabsPresenterFactory(module, contextProvider);
    }

    public static GetVoucherTabsPresenter proxyProvideVoucherTabsPresenter(PresenterModule instance, Context context) {
        return instance.provideVoucherTabsPresenter(context);
    }
}
