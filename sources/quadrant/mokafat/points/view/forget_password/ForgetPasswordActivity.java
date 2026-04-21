package quadrant.mokafat.points.view.forget_password;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.view.reset_password.ResetPasswordActivity;

/* JADX INFO: loaded from: classes.dex */
public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener, ForgetPasswordView {
    private AlphaAnimation buttonClick = new AlphaAnimation(2.0f, 0.8f);
    private EditText forgetPasswordEdit;

    @Inject
    ForgetPasswordPresenter forgetPasswordPresenter;
    private TextView mBackTxt;
    private Button mResetPassword;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        this.mResetPassword = (Button) findViewById(R.id.button_reset_password);
        this.forgetPasswordEdit = (EditText) findViewById(R.id.editText);
        this.mBackTxt = (TextView) findViewById(R.id.textView_forget_password);
        this.mBackTxt.setOnClickListener(this);
        this.mResetPassword.setOnClickListener(this);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.forgetPasswordPresenter.onAttach((ForgetPasswordView) this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id != R.id.button_reset_password) {
            if (id == R.id.textView_forget_password) {
                onBackPressed();
            }
        } else {
            this.mResetPassword.startAnimation(this.buttonClick);
            if (this.forgetPasswordEdit.getText().toString().length() > 0) {
                this.forgetPasswordPresenter.forgetPasswordPresenter();
            } else {
                Toasty.error(this, "Enter Username", 6000, true).show();
            }
        }
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }

    @Override // quadrant.mokafat.points.view.forget_password.ForgetPasswordView
    public void showLoading() {
    }

    @Override // quadrant.mokafat.points.view.forget_password.ForgetPasswordView
    public void hideLoading() {
        startActivity(new Intent(this, (Class<?>) ResetPasswordActivity.class));
    }

    @Override // quadrant.mokafat.points.view.forget_password.ForgetPasswordView
    public String getUserName() {
        return this.forgetPasswordEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.forget_password.ForgetPasswordView
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.forget_password.ForgetPasswordView
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }
}
