package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_vendors.GetVendorsResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class PartnersPresenter implements BasePresenter<PartnersView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    PartnersView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(PartnersView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public PartnersPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getVendorsForSection() {
        this.mView.showLoading();
        this.mApiInterface.getPartnersForSection(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), Utilities.SECTION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetVendorsResponse>) new Subscriber<GetVendorsResponse>() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
                PartnersPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                PartnersPresenter.this.mView.showMessage(e.getMessage());
                PartnersPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public void onNext(GetVendorsResponse response) {
                for (int i = 0; i < response.getData().size(); i++) {
                    PartnersPresenter.this.mView.showPartners(response.getData().get(i).getRelationships().getPartners());
                }
            }
        });
    }
}
