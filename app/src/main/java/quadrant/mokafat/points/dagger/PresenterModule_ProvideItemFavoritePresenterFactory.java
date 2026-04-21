package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.sections.inside_items.ItemFavoritePresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideItemFavoritePresenterFactory implements Factory<ItemFavoritePresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideItemFavoritePresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public ItemFavoritePresenter get() {
        return (ItemFavoritePresenter) Preconditions.checkNotNull(this.module.provideItemFavoritePresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ItemFavoritePresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideItemFavoritePresenterFactory(module, contextProvider);
    }

    public static ItemFavoritePresenter proxyProvideItemFavoritePresenter(PresenterModule instance, Context context) {
        return instance.provideItemFavoritePresenter(context);
    }
}
