package quadrant.mokafat.points.view.login;

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
import quadrant.mokafat.points.models.objects.login.loginRequest;
import quadrant.mokafat.points.models.objects.login.loginResponse;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class LoginPresenter implements BasePresenter<LoginView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    loginRequest mLoginRequest;
    LoginView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(LoginView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public LoginPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void doLoginPresenter() {
        try {
            if (!Utilities.checkConnection(this.mContext)) {
                this.mView.showErrorMessage(this.mContext.getString(R.string.internet_check));
                checkConnection(false);
                return;
            }
            if (!Utilities.checkConnection(this.mContext)) {
                Toasty.info(this.mContext, this.mContext.getString(R.string.internet_check), 6000, true).show();
                return;
            }
            if (this.mView.getUserName().equals("")) {
                Toasty.error(this.mContext, "Enter Username", 6000, true).show();
                return;
            }
            if (this.mView.getPassword().equals("")) {
                Toasty.error(this.mContext, "Enter Password", 6000, true).show();
                return;
            }
            if (this.mView.getDevice().equals("")) {
                Toasty.error(this.mContext, "can`t Get Device Type", 6000, true).show();
                return;
            }
            if (this.mView.getDeviceIdentifier().equals("")) {
                Toasty.error(this.mContext, "can`t Get Device ID", 6000, true).show();
                return;
            }
            this.mView.showLoading();
            this.mLoginRequest = new loginRequest();
            this.mLoginRequest.setDevice(this.mView.getDevice());
            this.mLoginRequest.setDevice_id(this.mView.getDeviceIdentifier());
            this.mLoginRequest.setPassword(this.mView.getPassword());
            this.mLoginRequest.setUsername(this.mView.getUserName());
            this.mApiInterface.loginObservable(this.mLoginRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super loginResponse>) new Subscriber<loginResponse>() { // from class: quadrant.mokafat.points.view.login.LoginPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                    LoginPresenter.this.mView.hideLoading();
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    ResponseBody responseBody = ((HttpException) e).response().errorBody();
                    LoginPresenter.this.mView.showErrorMessage(LoginPresenter.this.getErrorMessage(responseBody).toString());
                    LoginPresenter.this.mView.hideLoading();
                }

                @Override // rx.Observer
                public final void onNext(loginResponse response) {
                    LoginPresenter.this.isLoaded = true;
                    if (response.getData().getEmail().equals(LoginPresenter.this.mView.getUserName()) || response.getData().getMobile().equals(LoginPresenter.this.mView.getUserName())) {
                        Utilities.saveUserInfoSharedPreferences(LoginPresenter.this.mContext, response);
                        LoginPresenter.this.mView.showActivity();
                    } else {
                        LoginPresenter.this.mView.showErrorMessage(response.getMessage());
                        LoginPresenter.this.mView.hideLoading();
                    }
                }
            });
        } catch (Exception e) {
            this.mView.showErrorMessage("Error, try again");
        }
    }

    public void checkLoggedInUser() {
        loginResponse loginResponse = Utilities.retrieveUserInfoSharedPreferences(this.mContext);
        if (loginResponse != null) {
            this.mView.showActivity();
        }
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            doLoginPresenter();
            this.isLoaded = false;
            this.mView.showErrorMessage("internet connected");
        }
        if (!isConnected && this.isLoaded) {
            this.mView.showErrorMessage("offline");
        } else if (isConnected && this.isLoaded) {
            this.mView.showErrorMessage("connect to internet");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
