package quadrant.mokafat.points.view.sections.get_all_sections;

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
public class SectionPresenter implements BasePresenter<SectionView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    SectionView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(SectionView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public SectionPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getSections() {
        this.mApiInterface.getSectionsObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetSectionsResponse>) new Subscriber<GetSectionsResponse>() { // from class: quadrant.mokafat.points.view.sections.get_all_sections.SectionPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                SectionPresenter.this.mView.showMessage(e.getMessage());
            }

            @Override // rx.Observer
            public final void onNext(GetSectionsResponse response) {
                SectionPresenter.this.mView.showSections(response.getData());
            }
        });
    }
}
