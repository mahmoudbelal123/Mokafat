package quadrant.mokafat.points.view.login;

import android.content.Context;
import es.dmoral.toasty.Toasty;
import java.io.IOException;
import java.net.SocketTimeoutException;
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

public class LoginPresenter implements BasePresenter<LoginView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    loginRequest mLoginRequest;
    LoginView mView;

    @Override
    public void onAttach(LoginView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override
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
            this.mApiInterface.loginObservable(this.mLoginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<loginResponse>() {
                    @Override
                    public void onCompleted() {
                        LoginPresenter.this.mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoginPresenter.this.mView.hideLoading();
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            LoginPresenter.this.mView.showErrorMessage(LoginPresenter.this.getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            LoginPresenter.this.mView.showErrorMessage("Connection timed out. Please try again.");
                        } else if (e instanceof IOException) {
                            LoginPresenter.this.mView.showErrorMessage("Network error. Check your connection.");
                        } else {
                            LoginPresenter.this.mView.showErrorMessage("An error occurred. Please try again.");
                        }
                    }

                    @Override
                    public void onNext(loginResponse response) {
                        LoginPresenter.this.isLoaded = true;
                        if (response != null && response.getData() != null && response.getToken() != null) {
                            Utilities.saveUserInfoSharedPreferences(LoginPresenter.this.mContext, response);
                            LoginPresenter.this.mView.showActivity();
                        } else {
                            LoginPresenter.this.mView.showErrorMessage(
                                response != null && response.getMessage() != null
                                    ? response.getMessage()
                                    : "Login failed. Please try again.");
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
        }
        if (!isConnected && this.isLoaded) {
            this.mView.showErrorMessage("offline");
        } else if (isConnected && this.isLoaded) {
            this.mView.showErrorMessage("connect to internet");
        }
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage() != null ? e.getMessage() : "Unknown error";
        }
    }
}
