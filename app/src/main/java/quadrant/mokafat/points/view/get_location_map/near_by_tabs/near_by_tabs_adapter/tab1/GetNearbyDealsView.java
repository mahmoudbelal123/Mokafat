package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_branches.test_get_branches.GetBranchesResponse;

/* JADX INFO: loaded from: classes.dex */
public interface GetNearbyDealsView extends BaseView {
    String getLatitude();

    String getLongitute();

    void hideLoading();

    void onComplete();

    void showLoading();

    void showMessage(String str);

    void showResponse(GetBranchesResponse getBranchesResponse);
}
