package quadrant.mokafat.points.view.log_out;

import quadrant.mokafat.points.baseClass.BaseView;

/* JADX INFO: loaded from: classes.dex */
public interface LogOutView extends BaseView {
    void hideOutLoading();

    void showActivity();

    void showErrorMessage(String str);

    void showOutLoading();

    void showSuccessMessage(String str);
}
