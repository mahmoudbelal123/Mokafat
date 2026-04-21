package quadrant.mokafat.points.view.forget_password;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class ForgetPasswordActivity_MembersInjector implements MembersInjector<ForgetPasswordActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ForgetPasswordPresenter> forgetPasswordPresenterProvider;

    public ForgetPasswordActivity_MembersInjector(Provider<ForgetPasswordPresenter> forgetPasswordPresenterProvider) {
        this.forgetPasswordPresenterProvider = forgetPasswordPresenterProvider;
    }

    public static MembersInjector<ForgetPasswordActivity> create(Provider<ForgetPasswordPresenter> forgetPasswordPresenterProvider) {
        return new ForgetPasswordActivity_MembersInjector(forgetPasswordPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ForgetPasswordActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.forgetPasswordPresenter = this.forgetPasswordPresenterProvider.get();
    }

    public static void injectForgetPasswordPresenter(ForgetPasswordActivity instance, Provider<ForgetPasswordPresenter> forgetPasswordPresenterProvider) {
        instance.forgetPasswordPresenter = forgetPasswordPresenterProvider.get();
    }
}
