package quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic;

import android.content.Context;
import es.dmoral.toasty.Toasty;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.redemptions.GetRedemptionsResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class PromosPresenter implements BasePresenter<PromosView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    PromosView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(PromosView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public PromosPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getPromos() {
        if (!Utilities.checkConnection(this.mContext)) {
            Toasty.info(this.mContext, this.mContext.getString(R.string.internet_check), 6000, true).show();
        } else {
            this.mApiInterface.getRedemptionsObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetRedemptionsResponse>) new Subscriber<GetRedemptionsResponse>() { // from class: quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    PromosPresenter.this.mView.showMessage(e.getMessage());
                }

                @Override // rx.Observer
                public final void onNext(GetRedemptionsResponse response) {
                    PromosPresenter.this.mView.showResponse(response);
                }
            });
        }
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            getPromos();
            this.isLoaded = false;
        }
        if ((isConnected || !this.isLoaded) && isConnected) {
            boolean z = this.isLoaded;
        }
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("password");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
