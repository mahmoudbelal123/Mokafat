package quadrant.mokafat.points.view.qr_code;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.login.loginResponse;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class QrCodePresenter implements BasePresenter<QrCodeView> {
    public static String urlForTestList = null;
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    QrCodeView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(QrCodeView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public QrCodePresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getProfilePresenter() {
        if (!Utilities.checkConnection(this.mContext)) {
            this.mView.showMessage(this.mContext.getString(R.string.internet_check));
            checkConnection(false);
        } else {
            this.mApiInterface.getProfileObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super ProfileResponse>) new Subscriber<ProfileResponse>() { // from class: quadrant.mokafat.points.view.qr_code.QrCodePresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    QrCodePresenter.this.mView.showMessage(QrCodePresenter.this.mContext.getString(R.string.internet_check));
                }

                @Override // rx.Observer
                public final void onNext(ProfileResponse response) {
                    QrCodePresenter.this.isLoaded = true;
                    QrCodePresenter.urlForTestList = "http://199.247.4.89/mokafat/dev/public/uploads/large/" + response.getData().getMain_image();
                    if (response.getData() != null) {
                        QrCodePresenter.this.mView.showProfileImage("http://199.247.4.89/mokafat/dev/public/uploads/large/" + response.getData().getMain_image());
                        QrCodePresenter.this.mView.showQrCodeImage("http://199.247.4.89/mokafat/dev/public/uploads/large/" + response.getData().getQr_image());
                        QrCodePresenter.this.mView.showCorporateName(Utilities.retrieveUserInfoSharedPreferences(QrCodePresenter.this.mContext).getData().getVendor().getTitle());
                        QrCodePresenter.this.mView.showName(response.getData().getName());
                        QrCodePresenter.this.mView.showMobile(response.getData().getMobile());
                        loginResponse loginResponse = Utilities.retrieveUserInfoSharedPreferences(QrCodePresenter.this.mContext);
                        loginResponse.getData().setMain_image(response.getData().getMain_image());
                        Utilities.saveUserInfoSharedPreferences(QrCodePresenter.this.mContext, loginResponse);
                    }
                }
            });
        }
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            getProfilePresenter();
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
