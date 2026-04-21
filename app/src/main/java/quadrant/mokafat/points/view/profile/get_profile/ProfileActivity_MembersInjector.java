package quadrant.mokafat.points.view.profile.get_profile;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class ProfileActivity_MembersInjector implements MembersInjector<ProfileActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ProfilePresenter> mProfilePresenterProvider;

    public ProfileActivity_MembersInjector(Provider<ProfilePresenter> mProfilePresenterProvider) {
        this.mProfilePresenterProvider = mProfilePresenterProvider;
    }

    public static MembersInjector<ProfileActivity> create(Provider<ProfilePresenter> mProfilePresenterProvider) {
        return new ProfileActivity_MembersInjector(mProfilePresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ProfileActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mProfilePresenter = this.mProfilePresenterProvider.get();
    }

    public static void injectMProfilePresenter(ProfileActivity instance, Provider<ProfilePresenter> mProfilePresenterProvider) {
        instance.mProfilePresenter = mProfilePresenterProvider.get();
    }
}
