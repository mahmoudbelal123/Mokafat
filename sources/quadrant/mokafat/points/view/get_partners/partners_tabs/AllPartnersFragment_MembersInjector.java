package quadrant.mokafat.points.view.get_partners.partners_tabs;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class AllPartnersFragment_MembersInjector implements MembersInjector<AllPartnersFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<AllPartnersPresenter> allPartnersPresenterProvider;

    public AllPartnersFragment_MembersInjector(Provider<AllPartnersPresenter> allPartnersPresenterProvider) {
        this.allPartnersPresenterProvider = allPartnersPresenterProvider;
    }

    public static MembersInjector<AllPartnersFragment> create(Provider<AllPartnersPresenter> allPartnersPresenterProvider) {
        return new AllPartnersFragment_MembersInjector(allPartnersPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AllPartnersFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.allPartnersPresenter = this.allPartnersPresenterProvider.get();
    }

    public static void injectAllPartnersPresenter(AllPartnersFragment instance, Provider<AllPartnersPresenter> allPartnersPresenterProvider) {
        instance.allPartnersPresenter = allPartnersPresenterProvider.get();
    }
}
