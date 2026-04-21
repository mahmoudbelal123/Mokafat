package quadrant.mokafat.points.view.profile.sections_items;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class SectionsHomeItemsFragment_MembersInjector implements MembersInjector<SectionsHomeItemsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<SectionsHomeItemsPresenter> sectionsHomeItemsPresenterProvider;

    public SectionsHomeItemsFragment_MembersInjector(Provider<SectionsHomeItemsPresenter> sectionsHomeItemsPresenterProvider) {
        this.sectionsHomeItemsPresenterProvider = sectionsHomeItemsPresenterProvider;
    }

    public static MembersInjector<SectionsHomeItemsFragment> create(Provider<SectionsHomeItemsPresenter> sectionsHomeItemsPresenterProvider) {
        return new SectionsHomeItemsFragment_MembersInjector(sectionsHomeItemsPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SectionsHomeItemsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.sectionsHomeItemsPresenter = this.sectionsHomeItemsPresenterProvider.get();
    }

    public static void injectSectionsHomeItemsPresenter(SectionsHomeItemsFragment instance, Provider<SectionsHomeItemsPresenter> sectionsHomeItemsPresenterProvider) {
        instance.sectionsHomeItemsPresenter = sectionsHomeItemsPresenterProvider.get();
    }
}
