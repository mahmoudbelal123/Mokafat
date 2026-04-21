package quadrant.mokafat.points.view.change_password;

import android.content.Context;
import es.dmoral.toasty.Toasty;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.change_password.ChangePasswordRequest;
import quadrant.mokafat.points.models.objects.change_password.ChangePasswordResponse;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class ChangePasswordPresenter implements BasePresenter<ChangePasswordView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;
    ChangePasswordRequest mChangePasswordRequest;

    @Inject
    Context mContext;
    ChangePasswordView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(ChangePasswordView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public ChangePasswordPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void doChangePasswordPresenter() {
        if (!Utilities.checkConnection(this.mContext)) {
            Toasty.info(this.mContext, this.mContext.getString(R.string.internet_check), 6000, true).show();
            return;
        }
        if (this.mView.getCurrentPassword().length() < 6) {
            this.mView.setCurrentEditError("enter valid old password (at least 6 digits)");
            return;
        }
        if (this.mView.getNewPassword().length() < 6) {
            this.mView.setNewEditError("enter valid new password (at least 6 digits)");
            return;
        }
        if (!this.mView.getNewPassword().equals(this.mView.getRetypePassword())) {
            this.mView.setRetypeEditError("not match with the new entered password");
            return;
        }
        this.mView.showLoading();
        this.mChangePasswordRequest = new ChangePasswordRequest();
        this.mChangePasswordRequest.setPassword(this.mView.getNewPassword());
        this.mChangePasswordRequest.setOld_password(this.mView.getCurrentPassword());
        this.mApiInterface.changePasswordObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), this.mChangePasswordRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super ChangePasswordResponse>) new Subscriber<ChangePasswordResponse>() { // from class: quadrant.mokafat.points.view.change_password.ChangePasswordPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
                ChangePasswordPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                ChangePasswordPresenter.this.mView.showErrorMessage(ChangePasswordPresenter.this.getErrorMessageForOldPassword(responseBody).toString());
                ChangePasswordPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onNext(ChangePasswordResponse response) {
                ChangePasswordPresenter.this.isLoaded = true;
                ChangePasswordPresenter.this.mView.showSuccessMessage(response.getData().toString());
            }
        });
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            doChangePasswordPresenter();
            this.isLoaded = false;
            this.mView.showErrorMessage("internet connected");
        }
        if (!isConnected && this.isLoaded) {
            this.mView.showErrorMessage("offline");
        } else if (isConnected && this.isLoaded) {
            this.mView.showErrorMessage("connect to internet");
        }
    }

    private JSONObject getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getErrorMessageForOldPassword(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
