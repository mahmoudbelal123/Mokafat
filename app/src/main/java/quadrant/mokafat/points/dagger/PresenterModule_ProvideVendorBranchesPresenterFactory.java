package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideVendorBranchesPresenterFactory implements Factory<VendorBranchesPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideVendorBranchesPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public VendorBranchesPresenter get() {
        return (VendorBranchesPresenter) Preconditions.checkNotNull(this.module.provideVendorBranchesPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<VendorBranchesPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideVendorBranchesPresenterFactory(module, contextProvider);
    }

    public static VendorBranchesPresenter proxyProvideVendorBranchesPresenter(PresenterModule instance, Context context) {
        return instance.provideVendorBranchesPresenter(context);
    }
}
