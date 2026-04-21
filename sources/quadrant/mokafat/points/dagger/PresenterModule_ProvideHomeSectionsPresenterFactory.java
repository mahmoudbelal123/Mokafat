package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideHomeSectionsPresenterFactory implements Factory<HomeSectionPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideHomeSectionsPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public HomeSectionPresenter get() {
        return (HomeSectionPresenter) Preconditions.checkNotNull(this.module.provideHomeSectionsPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<HomeSectionPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideHomeSectionsPresenterFactory(module, contextProvider);
    }

    public static HomeSectionPresenter proxyProvideHomeSectionsPresenter(PresenterModule instance, Context context) {
        return instance.provideHomeSectionsPresenter(context);
    }
}
