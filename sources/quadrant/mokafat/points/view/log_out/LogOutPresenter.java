package quadrant.mokafat.points.view.log_out;

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
import quadrant.mokafat.points.models.objects.log_out.LogOutResponse;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class LogOutPresenter implements BasePresenter<LogOutView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    LogOutView mView;

    public LogOutPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void doLogOutPresenter() {
        if (!Utilities.checkConnection(this.mContext)) {
            Toasty.info(this.mContext, this.mContext.getString(R.string.internet_check), 6000, true).show();
        } else {
            this.mView.showOutLoading();
            this.mApiInterface.logoutObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super LogOutResponse>) new Subscriber<LogOutResponse>() { // from class: quadrant.mokafat.points.view.log_out.LogOutPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                    LogOutPresenter.this.mView.hideOutLoading();
                    LogOutPresenter.this.mView.showActivity();
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    ((HttpException) e).response().errorBody();
                    LogOutPresenter.this.mView.hideOutLoading();
                }

                @Override // rx.Observer
                public final void onNext(LogOutResponse response) {
                    LogOutPresenter.this.isLoaded = true;
                }
            });
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

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(LogOutView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }
}
