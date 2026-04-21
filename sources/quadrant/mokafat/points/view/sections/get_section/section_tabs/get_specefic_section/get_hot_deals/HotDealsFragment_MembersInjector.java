package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class HotDealsFragment_MembersInjector implements MembersInjector<HotDealsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<HotDealsPresenter> hotDealsPresenterProvider;

    public HotDealsFragment_MembersInjector(Provider<HotDealsPresenter> hotDealsPresenterProvider) {
        this.hotDealsPresenterProvider = hotDealsPresenterProvider;
    }

    public static MembersInjector<HotDealsFragment> create(Provider<HotDealsPresenter> hotDealsPresenterProvider) {
        return new HotDealsFragment_MembersInjector(hotDealsPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HotDealsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.hotDealsPresenter = this.hotDealsPresenterProvider.get();
    }

    public static void injectHotDealsPresenter(HotDealsFragment instance, Provider<HotDealsPresenter> hotDealsPresenterProvider) {
        instance.hotDealsPresenter = hotDealsPresenterProvider.get();
    }
}
