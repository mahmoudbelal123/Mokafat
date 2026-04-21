package quadrant.mokafat.points.view.forget_password;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.apiClient.ApiInterface;

/* JADX INFO: loaded from: classes.dex */
public final class ForgetPasswordPresenter_MembersInjector implements MembersInjector<ForgetPasswordPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ApiInterface> mApiInterfaceProvider;
    private final Provider<Context> mContextProvider;

    public ForgetPasswordPresenter_MembersInjector(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        this.mApiInterfaceProvider = mApiInterfaceProvider;
        this.mContextProvider = mContextProvider;
    }

    public static MembersInjector<ForgetPasswordPresenter> create(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        return new ForgetPasswordPresenter_MembersInjector(mApiInterfaceProvider, mContextProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ForgetPasswordPresenter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mApiInterface = this.mApiInterfaceProvider.get();
        instance.mContext = this.mContextProvider.get();
    }

    public static void injectMApiInterface(ForgetPasswordPresenter instance, Provider<ApiInterface> mApiInterfaceProvider) {
        instance.mApiInterface = mApiInterfaceProvider.get();
    }

    public static void injectMContext(ForgetPasswordPresenter instance, Provider<Context> mContextProvider) {
        instance.mContext = mContextProvider.get();
    }
}
