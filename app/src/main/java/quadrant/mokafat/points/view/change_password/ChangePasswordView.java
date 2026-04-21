package quadrant.mokafat.points.view.change_password;

import quadrant.mokafat.points.baseClass.BaseView;

/* JADX INFO: loaded from: classes.dex */
public interface ChangePasswordView extends BaseView {
    String getCurrentPassword();

    String getNewPassword();

    String getRetypePassword();

    void hideLoading();

    void setCurrentEditError(String str);

    void setNewEditError(String str);

    void setRetypeEditError(String str);

    void showErrorMessage(String str);

    void showLoading();

    void showSuccessMessage(String str);
}
