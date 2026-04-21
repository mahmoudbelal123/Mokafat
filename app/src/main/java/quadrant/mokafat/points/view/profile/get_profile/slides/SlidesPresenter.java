package quadrant.mokafat.points.view.profile.get_profile.slides;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.slides.GetSlidesResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class SlidesPresenter implements BasePresenter<SlidesView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    SlidesView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(SlidesView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public SlidesPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getSlidesPresenter() {
        this.mView.showLadingSlides();
        try {
            if (!Utilities.checkConnection(this.mContext)) {
                this.mView.showMessage(this.mContext.getString(R.string.internet_check));
                checkConnection(false);
            } else {
                this.mApiInterface.getSlidesObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetSlidesResponse>) new Subscriber<GetSlidesResponse>() { // from class: quadrant.mokafat.points.view.profile.get_profile.slides.SlidesPresenter.1
                    @Override // rx.Observer
                    public final void onCompleted() {
                        SlidesPresenter.this.mView.hideLoadingSlides();
                    }

                    @Override // rx.Observer
                    public final void onError(Throwable e) {
                        SlidesPresenter.this.mView.showMessage(e.getMessage());
                    }

                    @Override // rx.Observer
                    public final void onNext(GetSlidesResponse response) {
                        SlidesPresenter.this.mView.showResponse(response.getData());
                    }
                });
            }
        } catch (Exception e) {
            this.mView.showMessage("try, again");
        }
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            getSlidesPresenter();
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
