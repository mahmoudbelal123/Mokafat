package quadrant.mokafat.points.view.vouchers_tiers;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class GetYourVoucherFragment_MembersInjector implements MembersInjector<GetYourVoucherFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<GetYourVoucherPresenter> mGetYourVoucherPresenterProvider;

    public GetYourVoucherFragment_MembersInjector(Provider<GetYourVoucherPresenter> mGetYourVoucherPresenterProvider) {
        this.mGetYourVoucherPresenterProvider = mGetYourVoucherPresenterProvider;
    }

    public static MembersInjector<GetYourVoucherFragment> create(Provider<GetYourVoucherPresenter> mGetYourVoucherPresenterProvider) {
        return new GetYourVoucherFragment_MembersInjector(mGetYourVoucherPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(GetYourVoucherFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mGetYourVoucherPresenter = this.mGetYourVoucherPresenterProvider.get();
    }

    public static void injectMGetYourVoucherPresenter(GetYourVoucherFragment instance, Provider<GetYourVoucherPresenter> mGetYourVoucherPresenterProvider) {
        instance.mGetYourVoucherPresenter = mGetYourVoucherPresenterProvider.get();
    }
}
