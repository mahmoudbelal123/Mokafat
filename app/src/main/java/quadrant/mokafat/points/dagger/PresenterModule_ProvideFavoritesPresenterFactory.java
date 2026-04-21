package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.favorite.FavoritesPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideFavoritesPresenterFactory implements Factory<FavoritesPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideFavoritesPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public FavoritesPresenter get() {
        return (FavoritesPresenter) Preconditions.checkNotNull(this.module.provideFavoritesPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<FavoritesPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideFavoritesPresenterFactory(module, contextProvider);
    }

    public static FavoritesPresenter proxyProvideFavoritesPresenter(PresenterModule instance, Context context) {
        return instance.provideFavoritesPresenter(context);
    }
}
