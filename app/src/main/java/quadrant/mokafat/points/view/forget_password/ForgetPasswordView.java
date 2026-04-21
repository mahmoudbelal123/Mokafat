package quadrant.mokafat.points.view.forget_password;

import quadrant.mokafat.points.baseClass.BaseView;

/* JADX INFO: loaded from: classes.dex */
public interface ForgetPasswordView extends BaseView {
    String getUserName();

    void hideLoading();

    void showErrorMessage(String str);

    void showLoading();

    void showSuccessMessage(String str);
}
