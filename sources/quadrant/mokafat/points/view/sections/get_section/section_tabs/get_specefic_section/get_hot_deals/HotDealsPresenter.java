package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.get_sectons_by_id.GetSectionsBySectionIdResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class HotDealsPresenter implements BasePresenter<HotDealsView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    HotDealsView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(HotDealsView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public HotDealsPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getSubSections() {
        this.mView.showLoading();
        this.mApiInterface.getSectionsBySectionIdObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), Utilities.SECTION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetSectionsBySectionIdResponse>) new Subscriber<GetSectionsBySectionIdResponse>() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
                HotDealsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                HotDealsPresenter.this.mView.hideLoading();
                HotDealsPresenter.this.mView.showMessage(e.getMessage());
            }

            @Override // rx.Observer
            public final void onNext(GetSectionsBySectionIdResponse response) {
                HotDealsPresenter.this.mView.showSubSections(response);
            }
        });
    }
}
