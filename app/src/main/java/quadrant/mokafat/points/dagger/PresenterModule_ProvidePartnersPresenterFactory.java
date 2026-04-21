package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvidePartnersPresenterFactory implements Factory<PartnersPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvidePartnersPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public PartnersPresenter get() {
        return (PartnersPresenter) Preconditions.checkNotNull(this.module.providePartnersPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PartnersPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvidePartnersPresenterFactory(module, contextProvider);
    }

    public static PartnersPresenter proxyProvidePartnersPresenter(PresenterModule instance, Context context) {
        return instance.providePartnersPresenter(context);
    }
}
