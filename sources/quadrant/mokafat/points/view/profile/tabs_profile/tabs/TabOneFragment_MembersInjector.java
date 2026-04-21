package quadrant.mokafat.points.view.profile.tabs_profile.tabs;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.log_out.LogOutPresenter;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter;

/* JADX INFO: loaded from: classes.dex */
public final class TabOneFragment_MembersInjector implements MembersInjector<TabOneFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<LogOutPresenter> logOutPresenterProvider;
    private final Provider<EditAccountPresenter> mEditAccountPresenterProvider;
    private final Provider<ProfilePresenter> profilePresenterProvider;

    public TabOneFragment_MembersInjector(Provider<EditAccountPresenter> mEditAccountPresenterProvider, Provider<LogOutPresenter> logOutPresenterProvider, Provider<ProfilePresenter> profilePresenterProvider) {
        this.mEditAccountPresenterProvider = mEditAccountPresenterProvider;
        this.logOutPresenterProvider = logOutPresenterProvider;
        this.profilePresenterProvider = profilePresenterProvider;
    }

    public static MembersInjector<TabOneFragment> create(Provider<EditAccountPresenter> mEditAccountPresenterProvider, Provider<LogOutPresenter> logOutPresenterProvider, Provider<ProfilePresenter> profilePresenterProvider) {
        return new TabOneFragment_MembersInjector(mEditAccountPresenterProvider, logOutPresenterProvider, profilePresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TabOneFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mEditAccountPresenter = this.mEditAccountPresenterProvider.get();
        instance.logOutPresenter = this.logOutPresenterProvider.get();
        instance.profilePresenter = this.profilePresenterProvider.get();
    }

    public static void injectMEditAccountPresenter(TabOneFragment instance, Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        instance.mEditAccountPresenter = mEditAccountPresenterProvider.get();
    }

    public static void injectLogOutPresenter(TabOneFragment instance, Provider<LogOutPresenter> logOutPresenterProvider) {
        instance.logOutPresenter = logOutPresenterProvider.get();
    }

    public static void injectProfilePresenter(TabOneFragment instance, Provider<ProfilePresenter> profilePresenterProvider) {
        instance.profilePresenter = profilePresenterProvider.get();
    }
}
