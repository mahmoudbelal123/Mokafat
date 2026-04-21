package quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_branches.DataObject;
import quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id.GetBranchesByBranchIdResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class VendorBranchesPresenter implements BasePresenter<VendorBranchesView> {
    boolean isLoaded = false;
    String lat;
    String lon;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    VendorBranchesView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(VendorBranchesView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public VendorBranchesPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getBranchesOfVendor() {
        this.mView.showLoading();
        if (!Utilities.checkConnection(this.mContext)) {
            this.mView.showMessage(this.mContext.getString(R.string.internet_check));
            checkConnection(false);
        } else {
            this.mApiInterface.getBranchesByVendorIdObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), Utilities.VENDOR_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super DataObject>) new Subscriber<DataObject>() { // from class: quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                    VendorBranchesPresenter.this.mView.onComplete();
                    VendorBranchesPresenter.this.mView.hideLoading();
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    VendorBranchesPresenter.this.mView.showMessage(e.getMessage());
                }

                @Override // rx.Observer
                public final void onNext(DataObject response) {
                    VendorBranchesPresenter.this.mView.showResponse(response);
                }
            });
        }
    }

    public void getBranchesCityOfVendor() {
        this.mView.showLoading();
        if (!Utilities.checkConnection(this.mContext)) {
            this.mView.showMessage(this.mContext.getString(R.string.internet_check));
            checkConnection(false);
        } else {
            this.mApiInterface.getBranchesCityForVendorObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), Utilities.VENDOR_ID, "1").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetBranchesByBranchIdResponse>) new Subscriber<GetBranchesByBranchIdResponse>() { // from class: quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesPresenter.2
                @Override // rx.Observer
                public final void onCompleted() {
                    VendorBranchesPresenter.this.mView.onComplete();
                    VendorBranchesPresenter.this.mView.hideLoading();
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    VendorBranchesPresenter.this.mView.showMessage(e.getMessage());
                }

                @Override // rx.Observer
                public final void onNext(GetBranchesByBranchIdResponse response) {
                    VendorBranchesPresenter.this.mView.showResponseCity(response);
                }
            });
        }
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
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
