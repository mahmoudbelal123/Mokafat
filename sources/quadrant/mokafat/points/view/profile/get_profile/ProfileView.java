package quadrant.mokafat.points.view.profile.get_profile;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;

/* JADX INFO: loaded from: classes.dex */
public interface ProfileView extends BaseView {
    void hideLoading();

    void hideLoadingProfileInfo();

    void setCorporateName(String str);

    void setMobileNumber(String str);

    void setName(String str);

    void setProfileImage(String str);

    void setQrCodeImage(String str);

    void showLoading();

    void showLoadingProfileInfo();

    void showMessage(String str);

    void showResponse(ProfileResponse profileResponse);
}
