package quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.redemptions.GetRedemptionsResponse;

/* JADX INFO: loaded from: classes.dex */
public interface PromosView extends BaseView {
    void showMessage(String str);

    void showResponse(GetRedemptionsResponse getRedemptionsResponse);
}
