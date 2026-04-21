package quadrant.mokafat.points.view.profile.edit_account;

import java.io.File;
import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.edit_account.EditAccountResponse;

/* JADX INFO: loaded from: classes.dex */
public interface EditAccountView extends BaseView {
    int getAllowNotification();

    String getEmail();

    File getMainImage();

    String getMobile();

    String getName();

    int getReceiveNewOffersNotification();

    void hideLoading();

    void hideLoadingProfileInfo();

    void showErrorMessage(String str);

    void showLoading();

    void showLoadingProfileInfo();

    void showMessage(String str);

    void showResponse(EditAccountResponse editAccountResponse);

    void showSuccessMessage(String str);
}
