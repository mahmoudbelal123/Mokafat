package quadrant.mokafat.points.view.activities.tabs;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PromosFragment_MembersInjector implements MembersInjector<PromosFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<PromosPresenter> promosPresenterProvider;

    public PromosFragment_MembersInjector(Provider<PromosPresenter> promosPresenterProvider) {
        this.promosPresenterProvider = promosPresenterProvider;
    }

    public static MembersInjector<PromosFragment> create(Provider<PromosPresenter> promosPresenterProvider) {
        return new PromosFragment_MembersInjector(promosPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PromosFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.promosPresenter = this.promosPresenterProvider.get();
    }

    public static void injectPromosPresenter(PromosFragment instance, Provider<PromosPresenter> promosPresenterProvider) {
        instance.promosPresenter = promosPresenterProvider.get();
    }
}
