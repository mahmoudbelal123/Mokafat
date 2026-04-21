package quadrant.mokafat.points.view.profile.update;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.view.change_password.ChangePasswordPresenter;
import quadrant.mokafat.points.view.change_password.ChangePasswordView;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity;

/* JADX INFO: loaded from: classes.dex */
public class UpdatePasswordActivity extends AppCompatActivity implements ChangePasswordView, View.OnClickListener {
    ImageView backBtn;

    @Inject
    ChangePasswordPresenter changePasswordPresenter;
    EditText currentPasswordEdit;
    EditText newPasswordEdit;
    ProgressBar progressBarUpdatePassword;
    EditText reTypePasswordEdit;
    Button updatePasswordBtn;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        this.currentPasswordEdit = (EditText) findViewById(R.id.edit_current_password);
        this.newPasswordEdit = (EditText) findViewById(R.id.edit_new_password);
        this.reTypePasswordEdit = (EditText) findViewById(R.id.edit_re_type_password);
        this.updatePasswordBtn = (Button) findViewById(R.id.button_update_password);
        this.updatePasswordBtn.setOnClickListener(this);
        this.backBtn = (ImageView) findViewById(R.id.toolbar_back_verify_mobile);
        this.backBtn.setOnClickListener(this);
        this.progressBarUpdatePassword = (ProgressBar) findViewById(R.id.progressbar_update_password);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.changePasswordPresenter.onAttach((ChangePasswordView) this);
        this.currentPasswordEdit.addTextChangedListener(new TextWatcher() { // from class: quadrant.mokafat.points.view.profile.update.UpdatePasswordActivity.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 6 && UpdatePasswordActivity.this.newPasswordEdit.getText().toString().length() >= 6 && UpdatePasswordActivity.this.reTypePasswordEdit.getText().toString().length() >= 6) {
                    UpdatePasswordActivity.this.updatePasswordBtn.setVisibility(0);
                } else {
                    UpdatePasswordActivity.this.updatePasswordBtn.setVisibility(4);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.newPasswordEdit.addTextChangedListener(new TextWatcher() { // from class: quadrant.mokafat.points.view.profile.update.UpdatePasswordActivity.2
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 6 && UpdatePasswordActivity.this.currentPasswordEdit.getText().toString().length() >= 6 && UpdatePasswordActivity.this.reTypePasswordEdit.getText().toString().length() >= 6) {
                    UpdatePasswordActivity.this.updatePasswordBtn.setVisibility(0);
                } else {
                    UpdatePasswordActivity.this.updatePasswordBtn.setVisibility(4);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.reTypePasswordEdit.addTextChangedListener(new TextWatcher() { // from class: quadrant.mokafat.points.view.profile.update.UpdatePasswordActivity.3
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 6 && UpdatePasswordActivity.this.newPasswordEdit.getText().toString().length() >= 6 && UpdatePasswordActivity.this.currentPasswordEdit.getText().toString().length() >= 6) {
                    UpdatePasswordActivity.this.updatePasswordBtn.setVisibility(0);
                } else {
                    UpdatePasswordActivity.this.updatePasswordBtn.setVisibility(4);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void showLoading() {
        this.progressBarUpdatePassword.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void hideLoading() {
        this.progressBarUpdatePassword.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, 0).show();
        Utilities.UPDATE = 1;
        startActivity(new Intent(this, (Class<?>) ProfileActivity.class));
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void setCurrentEditError(String error) {
        this.currentPasswordEdit.setError(error);
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void setNewEditError(String error) {
        this.newPasswordEdit.setError(error);
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public void setRetypeEditError(String error) {
        this.reTypePasswordEdit.setError(error);
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public String getNewPassword() {
        return this.newPasswordEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public String getCurrentPassword() {
        return this.currentPasswordEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.change_password.ChangePasswordView
    public String getRetypePassword() {
        return this.reTypePasswordEdit.getText().toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_update_password) {
            this.updatePasswordBtn.startAnimation(Utilities.clickEffect);
            this.changePasswordPresenter.doChangePasswordPresenter();
        } else if (id == R.id.toolbar_back_verify_mobile) {
            finish();
        }
    }
}
