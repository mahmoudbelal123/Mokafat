package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class PartnersFragment_MembersInjector implements MembersInjector<PartnersFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<PartnersPresenter> partnersPresenterProvider;

    public PartnersFragment_MembersInjector(Provider<PartnersPresenter> partnersPresenterProvider) {
        this.partnersPresenterProvider = partnersPresenterProvider;
    }

    public static MembersInjector<PartnersFragment> create(Provider<PartnersPresenter> partnersPresenterProvider) {
        return new PartnersFragment_MembersInjector(partnersPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PartnersFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.partnersPresenter = this.partnersPresenterProvider.get();
    }

    public static void injectPartnersPresenter(PartnersFragment instance, Provider<PartnersPresenter> partnersPresenterProvider) {
        instance.partnersPresenter = partnersPresenterProvider.get();
    }
}
