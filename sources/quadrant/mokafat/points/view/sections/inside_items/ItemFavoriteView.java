package quadrant.mokafat.points.view.sections.inside_items;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.favorite.FavoriteResponse;

/* JADX INFO: loaded from: classes.dex */
public interface ItemFavoriteView extends BaseView {
    String getItemId();

    void showErrorMessage(String str);

    void showFavorResponse(FavoriteResponse favoriteResponse);
}
