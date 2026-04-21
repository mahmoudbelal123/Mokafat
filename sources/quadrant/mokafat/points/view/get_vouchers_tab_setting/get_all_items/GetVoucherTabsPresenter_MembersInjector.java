package quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.apiClient.ApiInterface;

/* JADX INFO: loaded from: classes.dex */
public final class GetVoucherTabsPresenter_MembersInjector implements MembersInjector<GetVoucherTabsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ApiInterface> mApiInterfaceProvider;
    private final Provider<Context> mContextProvider;

    public GetVoucherTabsPresenter_MembersInjector(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        this.mApiInterfaceProvider = mApiInterfaceProvider;
        this.mContextProvider = mContextProvider;
    }

    public static MembersInjector<GetVoucherTabsPresenter> create(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        return new GetVoucherTabsPresenter_MembersInjector(mApiInterfaceProvider, mContextProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(GetVoucherTabsPresenter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mApiInterface = this.mApiInterfaceProvider.get();
        instance.mContext = this.mContextProvider.get();
    }

    public static void injectMApiInterface(GetVoucherTabsPresenter instance, Provider<ApiInterface> mApiInterfaceProvider) {
        instance.mApiInterface = mApiInterfaceProvider.get();
    }

    public static void injectMContext(GetVoucherTabsPresenter instance, Provider<Context> mContextProvider) {
        instance.mContext = mContextProvider.get();
    }
}
