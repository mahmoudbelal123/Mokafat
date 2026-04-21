package quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_points.GetPointsResponse;

/* JADX INFO: loaded from: classes.dex */
public interface PointsView extends BaseView {
    void showMessage(String str);

    void showResponse(GetPointsResponse getPointsResponse);
}
