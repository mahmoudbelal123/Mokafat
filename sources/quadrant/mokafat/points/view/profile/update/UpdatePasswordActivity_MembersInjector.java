package quadrant.mokafat.points.view.profile.update;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.change_password.ChangePasswordPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class UpdatePasswordActivity_MembersInjector implements MembersInjector<UpdatePasswordActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ChangePasswordPresenter> changePasswordPresenterProvider;

    public UpdatePasswordActivity_MembersInjector(Provider<ChangePasswordPresenter> changePasswordPresenterProvider) {
        this.changePasswordPresenterProvider = changePasswordPresenterProvider;
    }

    public static MembersInjector<UpdatePasswordActivity> create(Provider<ChangePasswordPresenter> changePasswordPresenterProvider) {
        return new UpdatePasswordActivity_MembersInjector(changePasswordPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UpdatePasswordActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.changePasswordPresenter = this.changePasswordPresenterProvider.get();
    }

    public static void injectChangePasswordPresenter(UpdatePasswordActivity instance, Provider<ChangePasswordPresenter> changePasswordPresenterProvider) {
        instance.changePasswordPresenter = changePasswordPresenterProvider.get();
    }
}
