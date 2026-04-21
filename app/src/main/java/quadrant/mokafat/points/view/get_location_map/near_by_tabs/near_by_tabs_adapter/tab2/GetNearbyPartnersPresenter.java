package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2;

import android.content.Context;
import android.location.Location;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_branches.test_get_branches.GetBranchesResponse;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class GetNearbyPartnersPresenter implements BasePresenter<GetNearbyDealsView> {
    boolean isLoaded = false;
    String lat;
    String lon;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    GetNearbyDealsView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(GetNearbyDealsView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public GetNearbyPartnersPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getLocation(Location location) {
        this.lat = String.valueOf(location.getLatitude());
        this.lon = String.valueOf(location.getLongitude());
    }

    public void getNearbyDealsBranches() {
        this.mView.showLoading();
        if (!Utilities.checkConnection(this.mContext)) {
            this.mView.showMessage(this.mContext.getString(R.string.internet_check));
            checkConnection(false);
        } else {
            this.mApiInterface.getNearestBranchesObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), this.lat, this.lon).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetBranchesResponse>) new Subscriber<GetBranchesResponse>() { // from class: quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.GetNearbyPartnersPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                    GetNearbyPartnersPresenter.this.mView.hideLoading();
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    GetNearbyPartnersPresenter.this.mView.showMessage(e.getMessage());
                }

                @Override // rx.Observer
                public final void onNext(GetBranchesResponse response) {
                    GetNearbyPartnersPresenter.this.mView.showResponse(response);
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
