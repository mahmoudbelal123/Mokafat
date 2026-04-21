package quadrant.mokafat.points.view.profile.get_profile;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class ProfilePresenter implements BasePresenter<ProfileView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    ProfileView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(ProfileView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public ProfilePresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getProfilePresenter() {
        try {
            if (!Utilities.checkConnection(this.mContext)) {
                this.mView.showMessage(this.mContext.getString(R.string.internet_check));
                checkConnection(false);
            } else {
                this.mView.showLoading();
                this.mView.showLoadingProfileInfo();
                this.mApiInterface.getProfileObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super ProfileResponse>) new Subscriber<ProfileResponse>() { // from class: quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter.1
                    @Override // rx.Observer
                    public final void onCompleted() {
                        ProfilePresenter.this.mView.hideLoading();
                        ProfilePresenter.this.mView.hideLoadingProfileInfo();
                    }

                    @Override // rx.Observer
                    public final void onError(Throwable e) {
                        ProfilePresenter.this.mView.showMessage(ProfilePresenter.this.mContext.getString(R.string.internet_check));
                        ProfilePresenter.this.mView.hideLoading();
                        ProfilePresenter.this.mView.hideLoadingProfileInfo();
                    }

                    @Override // rx.Observer
                    public final void onNext(ProfileResponse response) {
                        ProfilePresenter.this.isLoaded = true;
                        ProfilePresenter.this.mView.showResponse(response);
                        if (response.getData() != null) {
                            ProfilePresenter.this.mView.setCorporateName(Utilities.retrieveUserInfoSharedPreferences(ProfilePresenter.this.mContext).getData().getVendor().getTitle());
                        }
                    }
                });
            }
        } catch (Exception e) {
            this.mView.showMessage("Error, try again");
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
