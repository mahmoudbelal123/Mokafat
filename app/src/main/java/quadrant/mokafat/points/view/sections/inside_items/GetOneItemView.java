package quadrant.mokafat.points.view.sections.inside_items;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_items.get_items_by_id.DataForItem;

/* JADX INFO: loaded from: classes.dex */
public interface GetOneItemView extends BaseView {
    String getItemIdForItem();

    void showFailMessage(String str);

    void showResponse(DataForItem dataForItem);

    void showSuccessMessage(String str);
}
