package quadrant.mokafat.points.view.profile.get_profile.get_home_sections;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.GetSectionsResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class HomeSectionPresenter implements BasePresenter<HomeSectionView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    HomeSectionView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(HomeSectionView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public HomeSectionPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getSections() {
        this.mApiInterface.getSectionsObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetSectionsResponse>) new Subscriber<GetSectionsResponse>() { // from class: quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                HomeSectionPresenter.this.mView.showMessage(e.getMessage());
            }

            @Override // rx.Observer
            public final void onNext(GetSectionsResponse response) {
                HomeSectionPresenter.this.mView.showSections(response.getData());
            }
        });
    }
}
