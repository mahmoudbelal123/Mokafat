package quadrant.mokafat.points.view.login;

import quadrant.mokafat.points.baseClass.BaseView;

/* JADX INFO: loaded from: classes.dex */
public interface LoginView extends BaseView {
    String getDevice();

    String getDeviceIdentifier();

    String getPassword();

    String getUserName();

    void hideLoading();

    void showActivity();

    void showErrorMessage(String str);

    void showLoading();

    void showSuccessMessage(String str);
}
