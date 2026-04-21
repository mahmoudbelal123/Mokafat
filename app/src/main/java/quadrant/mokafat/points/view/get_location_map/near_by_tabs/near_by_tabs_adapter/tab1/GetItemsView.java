package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1;

import java.util.List;
import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_items.GetItemsResponse;

/* JADX INFO: loaded from: classes.dex */
public interface GetItemsView extends BaseView {
    List<Integer> getVendorIds();

    void hideLoading();

    void showLoading();

    void showMessage(String str);

    void showResponse(GetItemsResponse getItemsResponse);
}
