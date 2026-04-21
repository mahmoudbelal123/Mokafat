package quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_vouchers_tab.GetVouchersTabResponse;

/* JADX INFO: loaded from: classes.dex */
public interface GetVouchersTabsView extends BaseView {
    void hideLoading();

    void showLoading();

    void showMessage(String str);

    void showResponse(GetVouchersTabResponse getVouchersTabResponse);
}
