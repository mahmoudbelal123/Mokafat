package quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class SingleAllBranchFragment_MembersInjector implements MembersInjector<SingleAllBranchFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<VendorBranchesPresenter> vendorBranchesPresenterProvider;

    public SingleAllBranchFragment_MembersInjector(Provider<VendorBranchesPresenter> vendorBranchesPresenterProvider) {
        this.vendorBranchesPresenterProvider = vendorBranchesPresenterProvider;
    }

    public static MembersInjector<SingleAllBranchFragment> create(Provider<VendorBranchesPresenter> vendorBranchesPresenterProvider) {
        return new SingleAllBranchFragment_MembersInjector(vendorBranchesPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SingleAllBranchFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.vendorBranchesPresenter = this.vendorBranchesPresenterProvider.get();
    }

    public static void injectVendorBranchesPresenter(SingleAllBranchFragment instance, Provider<VendorBranchesPresenter> vendorBranchesPresenterProvider) {
        instance.vendorBranchesPresenter = vendorBranchesPresenterProvider.get();
    }
}
