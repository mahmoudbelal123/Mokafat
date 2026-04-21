package quadrant.mokafat.points.view.sections.inside_items;

import android.content.Context;
import android.widget.Toast;
import javax.inject.Inject;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.favorite.FavoriteResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class ItemFavoritePresenter implements BasePresenter<ItemFavoriteView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    ItemFavoriteView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(ItemFavoriteView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public ItemFavoritePresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void makeItemFavorite() {
        String item_id = this.mView.getItemId();
        if (item_id != null) {
            this.mApiInterface.makeItemFavoriteObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), this.mView.getItemId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super FavoriteResponse>) new Subscriber<FavoriteResponse>() { // from class: quadrant.mokafat.points.view.sections.inside_items.ItemFavoritePresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    ItemFavoritePresenter.this.mView.showErrorMessage("" + e.getMessage());
                }

                @Override // rx.Observer
                public final void onNext(FavoriteResponse response) {
                    ItemFavoritePresenter.this.mView.showFavorResponse(response);
                }
            });
        } else {
            Toast.makeText(this.mContext, "Item_ id = null", 0).show();
        }
    }
}
