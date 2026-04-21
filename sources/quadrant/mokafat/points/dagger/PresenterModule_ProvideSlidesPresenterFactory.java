package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideSlidesPresenterFactory implements Factory<SlidesPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideSlidesPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public SlidesPresenter get() {
        return (SlidesPresenter) Preconditions.checkNotNull(this.module.provideSlidesPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SlidesPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideSlidesPresenterFactory(module, contextProvider);
    }

    public static SlidesPresenter proxyProvideSlidesPresenter(PresenterModule instance, Context context) {
        return instance.provideSlidesPresenter(context);
    }
}
