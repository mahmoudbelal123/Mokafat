package quadrant.mokafat.points.view.vouchers_tiers;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_vouchers.GetYourVouchersResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class GetYourVoucherPresenter implements BasePresenter<GetYourVoucherView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    GetYourVoucherView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(GetYourVoucherView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public GetYourVoucherPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getVouchersPresenter() {
        if (!Utilities.checkConnection(this.mContext)) {
            this.mView.showMessage(this.mContext.getString(R.string.internet_check));
            checkConnection(false);
        } else {
            this.mView.showLoading();
            this.mApiInterface.getYourVouchersObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetYourVouchersResponse>) new Subscriber<GetYourVouchersResponse>() { // from class: quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                    GetYourVoucherPresenter.this.mView.hideLoading();
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    GetYourVoucherPresenter.this.mView.showMessage(GetYourVoucherPresenter.this.mContext.getString(R.string.internet_check));
                    GetYourVoucherPresenter.this.mView.hideLoading();
                }

                @Override // rx.Observer
                public final void onNext(GetYourVouchersResponse response) {
                    GetYourVoucherPresenter.this.mView.showResponse(response);
                }
            });
        }
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            getVouchersPresenter();
            this.isLoaded = false;
            this.mView.showMessage("internet connected");
        }
        if (!isConnected && this.isLoaded) {
            this.mView.showMessage("offline");
        } else if (isConnected && this.isLoaded) {
            this.mView.showMessage("connect to internet");
        }
    }
}
