package quadrant.mokafat.points.view.vouchers_tiers;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_vouchers.GetYourVouchersResponse;

/* JADX INFO: loaded from: classes.dex */
public interface GetYourVoucherView extends BaseView {
    void hideLoading();

    void showLoading();

    void showMessage(String str);

    void showResponse(GetYourVouchersResponse getYourVouchersResponse);
}
