package quadrant.mokafat.points.view.profile.favorite;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.favorite.AllFavoriteResponse;

/* JADX INFO: loaded from: classes.dex */
public interface FavoritesView extends BaseView {
    void showMessage(String str);

    void showResponse(AllFavoriteResponse allFavoriteResponse);
}
