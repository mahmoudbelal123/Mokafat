package quadrant.mokafat.points.view.profile.favorite;

import android.content.Context;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.favorite.AllFavoriteResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class FavoritesPresenter implements BasePresenter<FavoritesView> {
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    FavoritesView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(FavoritesView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public FavoritesPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void getFavorites(int page) {
        this.mApiInterface.getAllFavoritesObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), "" + page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super AllFavoriteResponse>) new Subscriber<AllFavoriteResponse>() { // from class: quadrant.mokafat.points.view.profile.favorite.FavoritesPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                FavoritesPresenter.this.mView.showMessage(e.getMessage());
            }

            @Override // rx.Observer
            public final void onNext(AllFavoriteResponse response) {
                FavoritesPresenter.this.mView.showResponse(response);
            }
        });
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            this.isLoaded = false;
        }
        if ((isConnected || !this.isLoaded) && isConnected) {
            boolean z = this.isLoaded;
        }
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("password");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
