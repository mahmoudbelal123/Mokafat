package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideSectionPresenterFactory implements Factory<SectionPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideSectionPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public SectionPresenter get() {
        return (SectionPresenter) Preconditions.checkNotNull(this.module.provideSectionPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SectionPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideSectionPresenterFactory(module, contextProvider);
    }

    public static SectionPresenter proxyProvideSectionPresenter(PresenterModule instance, Context context) {
        return instance.provideSectionPresenter(context);
    }
}
