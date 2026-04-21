package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideSectionsHomeItemsPresenterFactory implements Factory<SectionsHomeItemsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideSectionsHomeItemsPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public SectionsHomeItemsPresenter get() {
        return (SectionsHomeItemsPresenter) Preconditions.checkNotNull(this.module.provideSectionsHomeItemsPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SectionsHomeItemsPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideSectionsHomeItemsPresenterFactory(module, contextProvider);
    }

    public static SectionsHomeItemsPresenter proxyProvideSectionsHomeItemsPresenter(PresenterModule instance, Context context) {
        return instance.provideSectionsHomeItemsPresenter(context);
    }
}
