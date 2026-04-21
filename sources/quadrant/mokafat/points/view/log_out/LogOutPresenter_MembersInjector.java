package quadrant.mokafat.points.view.log_out;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.apiClient.ApiInterface;

/* JADX INFO: loaded from: classes.dex */
public final class LogOutPresenter_MembersInjector implements MembersInjector<LogOutPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ApiInterface> mApiInterfaceProvider;
    private final Provider<Context> mContextProvider;

    public LogOutPresenter_MembersInjector(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        this.mApiInterfaceProvider = mApiInterfaceProvider;
        this.mContextProvider = mContextProvider;
    }

    public static MembersInjector<LogOutPresenter> create(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        return new LogOutPresenter_MembersInjector(mApiInterfaceProvider, mContextProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LogOutPresenter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mApiInterface = this.mApiInterfaceProvider.get();
        instance.mContext = this.mContextProvider.get();
    }

    public static void injectMApiInterface(LogOutPresenter instance, Provider<ApiInterface> mApiInterfaceProvider) {
        instance.mApiInterface = mApiInterfaceProvider.get();
    }

    public static void injectMContext(LogOutPresenter instance, Provider<Context> mContextProvider) {
        instance.mContext = mContextProvider.get();
    }
}
