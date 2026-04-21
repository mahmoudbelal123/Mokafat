package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideAllPartnersPresenterFactory implements Factory<AllPartnersPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideAllPartnersPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public AllPartnersPresenter get() {
        return (AllPartnersPresenter) Preconditions.checkNotNull(this.module.provideAllPartnersPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AllPartnersPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideAllPartnersPresenterFactory(module, contextProvider);
    }

    public static AllPartnersPresenter proxyProvideAllPartnersPresenter(PresenterModule instance, Context context) {
        return instance.provideAllPartnersPresenter(context);
    }
}
