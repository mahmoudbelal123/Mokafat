package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.GetItems;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class GetItemsSubSectionsPresenter implements BasePresenter<GetItemsSubsectionsView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    GetItemsSubsectionsView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(GetItemsSubsectionsView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public GetItemsSubSectionsPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getItemsSubSections() {
        this.mView.showLoading();
        this.mApiInterface.getItemsForSubSection(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), Utilities.SUB_SECTION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetItems>) new Subscriber<GetItems>() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
                GetItemsSubSectionsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                GetItemsSubSectionsPresenter.this.mView.showMessage(e.getMessage());
                GetItemsSubSectionsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onNext(GetItems response) {
                GetItemsSubSectionsPresenter.this.mView.showSItemsSubSections(response.getData());
                GetItemsSubSectionsPresenter.this.mView.showItemsSubSections(response);
            }
        });
    }

    public void getItemsSubSectionsPage(int page) {
        this.mView.showLoading();
        this.mApiInterface.getItemsForSubSectionPage(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), Utilities.SUB_SECTION_ID, "" + page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetItems>) new Subscriber<GetItems>() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter.2
            @Override // rx.Observer
            public final void onCompleted() {
                GetItemsSubSectionsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                GetItemsSubSectionsPresenter.this.mView.showMessage(e.getMessage());
                GetItemsSubSectionsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onNext(GetItems response) {
                GetItemsSubSectionsPresenter.this.mView.showItemsSubSectionsPage(response);
            }
        });
    }

    public void getItemsVendors() {
        this.mView.showLoading();
        this.mApiInterface.getItemsForVendorObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), Utilities.VENDOR_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetItems>) new Subscriber<GetItems>() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter.3
            @Override // rx.Observer
            public final void onCompleted() {
                GetItemsSubSectionsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                GetItemsSubSectionsPresenter.this.mView.showMessage(e.getMessage());
                GetItemsSubSectionsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onNext(GetItems response) {
                GetItemsSubSectionsPresenter.this.mView.showSItemsVendors(response.getData());
            }
        });
    }

    public void getItemsSectionsHome() {
        this.mView.showLoading();
        this.mApiInterface.getItemsForSubSection(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), Utilities.PARENT_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super GetItems>) new Subscriber<GetItems>() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter.4
            @Override // rx.Observer
            public final void onCompleted() {
                GetItemsSubSectionsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                GetItemsSubSectionsPresenter.this.mView.showMessage(e.getMessage());
                GetItemsSubSectionsPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onNext(GetItems response) {
                GetItemsSubSectionsPresenter.this.mView.showSItemsSubSections(response.getData());
            }
        });
    }
}
