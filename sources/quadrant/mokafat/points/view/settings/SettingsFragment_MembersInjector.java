package quadrant.mokafat.points.view.settings;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.log_out.LogOutPresenter;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter;

/* JADX INFO: loaded from: classes.dex */
public final class SettingsFragment_MembersInjector implements MembersInjector<SettingsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<LogOutPresenter> logOutPresenterProvider;
    private final Provider<ProfilePresenter> profilePresenterProvider;

    public SettingsFragment_MembersInjector(Provider<LogOutPresenter> logOutPresenterProvider, Provider<ProfilePresenter> profilePresenterProvider) {
        this.logOutPresenterProvider = logOutPresenterProvider;
        this.profilePresenterProvider = profilePresenterProvider;
    }

    public static MembersInjector<SettingsFragment> create(Provider<LogOutPresenter> logOutPresenterProvider, Provider<ProfilePresenter> profilePresenterProvider) {
        return new SettingsFragment_MembersInjector(logOutPresenterProvider, profilePresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SettingsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.logOutPresenter = this.logOutPresenterProvider.get();
        instance.profilePresenter = this.profilePresenterProvider.get();
    }

    public static void injectLogOutPresenter(SettingsFragment instance, Provider<LogOutPresenter> logOutPresenterProvider) {
        instance.logOutPresenter = logOutPresenterProvider.get();
    }

    public static void injectProfilePresenter(SettingsFragment instance, Provider<ProfilePresenter> profilePresenterProvider) {
        instance.profilePresenter = profilePresenterProvider.get();
    }
}
