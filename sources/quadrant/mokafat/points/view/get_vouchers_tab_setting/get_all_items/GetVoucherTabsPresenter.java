package quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_vouchers_tab.GetVouchersTabResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class GetVoucherTabsPresenter implements BasePresenter<GetVouchersTabsView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    GetVouchersTabsView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(GetVouchersTabsView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    public GetVoucherTabsPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public void getItemsPresenter() {
        if (!Utilities.checkConnection(this.mContext)) {
            this.mView.showMessage(this.mContext.getString(R.string.internet_check));
            checkConnection(false);
        } else {
            this.mView.showLoading();
            this.mApiInterface.getVouchersObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetVouchersTabResponse>) new Subscriber<GetVouchersTabResponse>() { // from class: quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVoucherTabsPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                }

                @Override // rx.Observer
                public final void onNext(GetVouchersTabResponse response) {
                    GetVoucherTabsPresenter.this.mView.showResponse(response);
                }
            });
        }
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            getItemsPresenter();
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
