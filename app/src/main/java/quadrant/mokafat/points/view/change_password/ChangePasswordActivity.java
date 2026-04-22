package quadrant.mokafat.points.view.change_password;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import es.dmoral.toasty.Toasty;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;

/* JADX INFO: loaded from: classes.dex */
public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordView, View.OnClickListener {
    private Button mChangePasswordBtn;
    private EditText mChangePasswordEdit;

    @Inject
    ChangePasswordPresenter mChangePasswordPresenter;
    ProgressDialog mProgressDialog;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        this.mProgressDialog = new ProgressDialog(this);
        this.mChangePasswordBtn = (Button) findViewById(R.id.button_change_password);
        this.mChangePasswordEdit = (EditText) findViewById(R.id.editText_change_password);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.mChangePasswordPresenter.onAttach((ChangePasswordView) this);
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void showLoading() {
        Utilities.showProgressDialog(this.mProgressDialog, "Waiting Change Password ...", true);
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void hideLoading() {
        Utilities.showProgressDialog(this.mProgressDialog, "", false);
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void showErrorMessage(String message) {
        Toasty.error(this, "" + message, 6000, true).show();
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void showSuccessMessage(String message) {
        Toasty.success(this, "" + message, 6000, true).show();
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void setCurrentEditError(String error) {
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void setNewEditError(String error) {
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void setRetypeEditError(String error) {
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public String getNewPassword() {
        return null;
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public String getCurrentPassword() {
        return null;
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public String getRetypePassword() {
        return null;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.button_change_password) {
            this.mChangePasswordPresenter.doChangePasswordPresenter();
        }
    }
}
