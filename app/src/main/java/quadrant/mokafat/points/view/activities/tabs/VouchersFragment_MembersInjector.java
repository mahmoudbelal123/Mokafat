package quadrant.mokafat.points.view.activities.tabs;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVoucherTabsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class VouchersFragment_MembersInjector implements MembersInjector<VouchersFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<GetVoucherTabsPresenter> getVoucherTabsPresenterProvider;

    public VouchersFragment_MembersInjector(Provider<GetVoucherTabsPresenter> getVoucherTabsPresenterProvider) {
        this.getVoucherTabsPresenterProvider = getVoucherTabsPresenterProvider;
    }

    public static MembersInjector<VouchersFragment> create(Provider<GetVoucherTabsPresenter> getVoucherTabsPresenterProvider) {
        return new VouchersFragment_MembersInjector(getVoucherTabsPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VouchersFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.getVoucherTabsPresenter = this.getVoucherTabsPresenterProvider.get();
    }

    public static void injectGetVoucherTabsPresenter(VouchersFragment instance, Provider<GetVoucherTabsPresenter> getVoucherTabsPresenterProvider) {
        instance.getVoucherTabsPresenter = getVoucherTabsPresenterProvider.get();
    }
}
