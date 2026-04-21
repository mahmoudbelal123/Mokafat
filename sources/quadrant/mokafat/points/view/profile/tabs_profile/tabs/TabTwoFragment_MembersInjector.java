package quadrant.mokafat.points.view.profile.tabs_profile.tabs;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.favorite.FavoritesPresenter;
import quadrant.mokafat.points.view.sections.inside_items.ItemFavoritePresenter;

/* JADX INFO: loaded from: classes.dex */
public final class TabTwoFragment_MembersInjector implements MembersInjector<TabTwoFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<FavoritesPresenter> favoritesPresenterProvider;
    private final Provider<ItemFavoritePresenter> itemFavoritePresenterProvider;

    public TabTwoFragment_MembersInjector(Provider<FavoritesPresenter> favoritesPresenterProvider, Provider<ItemFavoritePresenter> itemFavoritePresenterProvider) {
        this.favoritesPresenterProvider = favoritesPresenterProvider;
        this.itemFavoritePresenterProvider = itemFavoritePresenterProvider;
    }

    public static MembersInjector<TabTwoFragment> create(Provider<FavoritesPresenter> favoritesPresenterProvider, Provider<ItemFavoritePresenter> itemFavoritePresenterProvider) {
        return new TabTwoFragment_MembersInjector(favoritesPresenterProvider, itemFavoritePresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TabTwoFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.favoritesPresenter = this.favoritesPresenterProvider.get();
        instance.itemFavoritePresenter = this.itemFavoritePresenterProvider.get();
    }

    public static void injectFavoritesPresenter(TabTwoFragment instance, Provider<FavoritesPresenter> favoritesPresenterProvider) {
        instance.favoritesPresenter = favoritesPresenterProvider.get();
    }

    public static void injectItemFavoritePresenter(TabTwoFragment instance, Provider<ItemFavoritePresenter> itemFavoritePresenterProvider) {
        instance.itemFavoritePresenter = itemFavoritePresenterProvider.get();
    }
}
