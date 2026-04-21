package quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_branches.DataObject;
import quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id.GetBranchesByBranchIdResponse;

/* JADX INFO: loaded from: classes.dex */
public interface VendorBranchesView extends BaseView {
    void hideLoading();

    void onComplete();

    void showLoading();

    void showMessage(String str);

    void showResponse(DataObject dataObject);

    void showResponseCity(GetBranchesByBranchIdResponse getBranchesByBranchIdResponse);
}
