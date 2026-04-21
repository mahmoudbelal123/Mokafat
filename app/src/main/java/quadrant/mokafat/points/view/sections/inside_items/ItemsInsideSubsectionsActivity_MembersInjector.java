package quadrant.mokafat.points.view.sections.inside_items;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class ItemsInsideSubsectionsActivity_MembersInjector implements MembersInjector<ItemsInsideSubsectionsActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<GetOneItemPresenter> getOneItemPresenterProvider;
    private final Provider<ItemFavoritePresenter> itemFavoritePresenterProvider;

    public ItemsInsideSubsectionsActivity_MembersInjector(Provider<ItemFavoritePresenter> itemFavoritePresenterProvider, Provider<GetOneItemPresenter> getOneItemPresenterProvider) {
        this.itemFavoritePresenterProvider = itemFavoritePresenterProvider;
        this.getOneItemPresenterProvider = getOneItemPresenterProvider;
    }

    public static MembersInjector<ItemsInsideSubsectionsActivity> create(Provider<ItemFavoritePresenter> itemFavoritePresenterProvider, Provider<GetOneItemPresenter> getOneItemPresenterProvider) {
        return new ItemsInsideSubsectionsActivity_MembersInjector(itemFavoritePresenterProvider, getOneItemPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ItemsInsideSubsectionsActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.itemFavoritePresenter = this.itemFavoritePresenterProvider.get();
        instance.getOneItemPresenter = this.getOneItemPresenterProvider.get();
    }

    public static void injectItemFavoritePresenter(ItemsInsideSubsectionsActivity instance, Provider<ItemFavoritePresenter> itemFavoritePresenterProvider) {
        instance.itemFavoritePresenter = itemFavoritePresenterProvider.get();
    }

    public static void injectGetOneItemPresenter(ItemsInsideSubsectionsActivity instance, Provider<GetOneItemPresenter> getOneItemPresenterProvider) {
        instance.getOneItemPresenter = getOneItemPresenterProvider.get();
    }
}
