package quadrant.mokafat.points.view.change_password;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class ChangePasswordActivity_MembersInjector implements MembersInjector<ChangePasswordActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ChangePasswordPresenter> mChangePasswordPresenterProvider;

    public ChangePasswordActivity_MembersInjector(Provider<ChangePasswordPresenter> mChangePasswordPresenterProvider) {
        this.mChangePasswordPresenterProvider = mChangePasswordPresenterProvider;
    }

    public static MembersInjector<ChangePasswordActivity> create(Provider<ChangePasswordPresenter> mChangePasswordPresenterProvider) {
        return new ChangePasswordActivity_MembersInjector(mChangePasswordPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ChangePasswordActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mChangePasswordPresenter = this.mChangePasswordPresenterProvider.get();
    }

    public static void injectMChangePasswordPresenter(ChangePasswordActivity instance, Provider<ChangePasswordPresenter> mChangePasswordPresenterProvider) {
        instance.mChangePasswordPresenter = mChangePasswordPresenterProvider.get();
    }
}
