package quadrant.mokafat.points.view.get_partners.partners_tabs;

import android.content.Context;
import es.dmoral.toasty.Toasty;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.GetSectionsResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class AllPartnersPresenter implements BasePresenter<AllPartnersView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    AllPartnersView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(AllPartnersView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public AllPartnersPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getSectionsObservable() {
        if (!Utilities.checkConnection(this.mContext)) {
            Toasty.info(this.mContext, this.mContext.getString(R.string.internet_check), 6000, true).show();
        } else {
            this.mApiInterface.getPartnersForSections(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetSectionsResponse>) new Subscriber<GetSectionsResponse>() { // from class: quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    AllPartnersPresenter.this.mView.showMessage(e.getMessage());
                }

                @Override // rx.Observer
                public final void onNext(GetSectionsResponse response) {
                    AllPartnersPresenter.this.mView.showResponse(response);
                }
            });
        }
    }
}
