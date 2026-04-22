package quadrant.mokafat.points.view.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.firebase.iid.FirebaseInstanceId;
import es.dmoral.toasty.Toasty;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordActivity;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity;

/* JADX INFO: loaded from: classes.dex */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener, View.OnTouchListener {
    private AlphaAnimation buttonClick = new AlphaAnimation(2.0f, 0.8f);
    String deviceToken;
    private TextView mForgetPasswordTxt;

    @Inject
    LoginPresenter mLoginPresenter;
    private EditText mPasswordEdit;
    private ProgressDialog mProgressDialog;
    private Button mSignInBtn;
    private EditText mUserNameEdit;
    private ProgressBar progressBar;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.mSignInBtn = (Button) findViewById(R.id.button_sign_in);
        this.mProgressDialog = new ProgressDialog(this);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_login);
        this.mForgetPasswordTxt = (TextView) findViewById(R.id.textView_forget_password);
        this.mSignInBtn.setOnClickListener(this);
        this.mForgetPasswordTxt.setOnClickListener(this);
        this.mUserNameEdit = (EditText) findViewById(R.id.editText_user_name);
        this.mPasswordEdit = (EditText) findViewById(R.id.editText_password);
        try {
            ((DaggerApplication) getApplication()).getAppComponent().inject(this);
            this.mLoginPresenter.onAttach((LoginView) this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.deviceToken = FirebaseInstanceId.getInstance().getToken();
        this.mLoginPresenter.checkLoggedInUser();
    }

    @Override // quadrant.mokafat.points.view.login.LoginView
    public void showLoading() {
        this.progressBar.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.login.LoginView
    public void hideLoading() {
        this.progressBar.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.login.LoginView
    public void showActivity() {
        startActivity(new Intent(this, (Class<?>) ProfileActivity.class));
    }

    @Override // quadrant.mokafat.points.view.login.LoginView
    public void showErrorMessage(String message) {
        Toasty.error(this, message, 5000, true).show();
    }

    @Override // quadrant.mokafat.points.view.login.LoginView
    public void showSuccessMessage(String message) {
        Toasty.success(this, message, 5000, true).show();
    }

    @Override // quadrant.mokafat.points.view.login.LoginView
    public String getUserName() {
        return this.mUserNameEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.login.LoginView
    public String getPassword() {
        return this.mPasswordEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.login.LoginView
    public String getDevice() {
        return Build.MODEL;
    }

    @Override // android.content.ContextWrapper, android.content.Context, quadrant.mokafat.points.view.login.LoginView
    public String getDeviceIdentifier() {
        return this.deviceToken;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_sign_in) {
            this.mSignInBtn.startAnimation(this.buttonClick);
            this.mLoginPresenter.doLoginPresenter();
        } else if (id == R.id.textView_forget_password) {
            startActivity(new Intent(this, (Class<?>) ForgetPasswordActivity.class));
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        event.getAction();
        return false;
    }
}
