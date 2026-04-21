package quadrant.mokafat.points.view.login;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class LoginActivity_MembersInjector implements MembersInjector<LoginActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<LoginPresenter> mLoginPresenterProvider;

    public LoginActivity_MembersInjector(Provider<LoginPresenter> mLoginPresenterProvider) {
        this.mLoginPresenterProvider = mLoginPresenterProvider;
    }

    public static MembersInjector<LoginActivity> create(Provider<LoginPresenter> mLoginPresenterProvider) {
        return new LoginActivity_MembersInjector(mLoginPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LoginActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mLoginPresenter = this.mLoginPresenterProvider.get();
    }

    public static void injectMLoginPresenter(LoginActivity instance, Provider<LoginPresenter> mLoginPresenterProvider) {
        instance.mLoginPresenter = mLoginPresenterProvider.get();
    }
}
