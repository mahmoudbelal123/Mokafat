package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_items.GetItemsResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class GetItemsPresenter implements BasePresenter<GetItemsView> {
    boolean isLoaded = false;
    String lat;
    String lon;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    GetItemsView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(GetItemsView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public GetItemsPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getItems() {
        this.mView.showLoading();
        if (!Utilities.checkConnection(this.mContext)) {
            this.mView.showMessage(this.mContext.getString(R.string.internet_check));
            checkConnection(false);
        } else {
            this.mApiInterface.getItemsObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), this.mView.getVendorIds()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetItemsResponse>) new Subscriber<GetItemsResponse>() { // from class: quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                    GetItemsPresenter.this.mView.hideLoading();
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    GetItemsPresenter.this.mView.showMessage(e.getMessage());
                }

                @Override // rx.Observer
                public final void onNext(GetItemsResponse response) {
                    GetItemsPresenter.this.mView.showResponse(response);
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
