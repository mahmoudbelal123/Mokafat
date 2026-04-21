package quadrant.mokafat.points.view.sections.inside_items;

import android.content.Context;
import javax.inject.Inject;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_items.get_items_by_id.DataForItem;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class GetOneItemPresenter implements BasePresenter<GetOneItemView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    GetOneItemView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(GetOneItemView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public GetOneItemPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getItem() {
        this.mApiInterface.getItemsByItemIdObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), this.mView.getItemIdForItem()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super DataForItem>) new Subscriber<DataForItem>() { // from class: quadrant.mokafat.points.view.sections.inside_items.GetOneItemPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                GetOneItemPresenter.this.mView.showFailMessage(e.getMessage());
            }

            @Override // rx.Observer
            public final void onNext(DataForItem response) {
                GetOneItemPresenter.this.mView.showResponse(response);
            }
        });
    }
}
