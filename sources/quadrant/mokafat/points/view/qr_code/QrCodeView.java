package quadrant.mokafat.points.view.qr_code;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;

/* JADX INFO: loaded from: classes.dex */
public interface QrCodeView extends BaseView {
    void showCorporateName(String str);

    void showMessage(String str);

    void showMobile(String str);

    void showName(String str);

    void showProfileImage(String str);

    void showQrCodeImage(String str);

    void showResponse(ProfileResponse profileResponse);
}
