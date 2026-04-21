package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideGetYourVouchersPresenterFactory implements Factory<GetYourVoucherPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideGetYourVouchersPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public GetYourVoucherPresenter get() {
        return (GetYourVoucherPresenter) Preconditions.checkNotNull(this.module.provideGetYourVouchersPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GetYourVoucherPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideGetYourVouchersPresenterFactory(module, contextProvider);
    }

    public static GetYourVoucherPresenter proxyProvideGetYourVouchersPresenter(PresenterModule instance, Context context) {
        return instance.provideGetYourVouchersPresenter(context);
    }
}
