package quadrant.mokafat.points.view.qr_code;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;

/* JADX INFO: loaded from: classes.dex */
public class QrCodeActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, QrCodeView {
    private ImageView mBackToolbar;
    private TextView mCorporateTxt;
    private TextView mNameTxt;
    private TextView mPhoneTxt;
    private ImageView mProfileImage;
    private ImageView mQrCodeImage;

    @Inject
    QrCodePresenter mQrCodePresenter;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        this.mBackToolbar = (ImageView) findViewById(R.id.toolbar_back);
        this.mBackToolbar.setOnTouchListener(this);
        this.mQrCodeImage = (ImageView) findViewById(R.id.imageView_qr_code_in_qr_code);
        this.mProfileImage = (ImageView) findViewById(R.id.imageView_profile_in_qr_code);
        this.mNameTxt = (TextView) findViewById(R.id.textView_name_in_qr_code);
        this.mPhoneTxt = (TextView) findViewById(R.id.textView_mobile_in_qr_code);
        this.mCorporateTxt = (TextView) findViewById(R.id.textView_corporate_name_in_qr_code);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.mQrCodePresenter.onAttach((QrCodeView) this);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        this.mQrCodePresenter.getProfilePresenter();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.toolbar_back) {
            finish();
            return false;
        }
        return false;
    }

    @Override // quadrant.mokafat.points.view.qr_code.QrCodeView
    public void showName(String name) {
        this.mNameTxt.setText(name);
    }

    @Override // quadrant.mokafat.points.view.qr_code.QrCodeView
    public void showMobile(String mobile) {
        this.mPhoneTxt.setText(mobile);
    }

    @Override // quadrant.mokafat.points.view.qr_code.QrCodeView
    public void showCorporateName(String corporateName) {
        this.mCorporateTxt.setText(corporateName);
    }

    @Override // quadrant.mokafat.points.view.qr_code.QrCodeView
    public void showProfileImage(String profileUrl) {
        GlideApp.with((FragmentActivity) this).load2(profileUrl).into(this.mProfileImage);
    }

    @Override // quadrant.mokafat.points.view.qr_code.QrCodeView
    public void showQrCodeImage(String QrCodeUrl) {
        GlideApp.with((FragmentActivity) this).load2(QrCodeUrl).into(this.mQrCodeImage);
    }

    @Override // quadrant.mokafat.points.view.qr_code.QrCodeView
    public void showResponse(ProfileResponse profileResponse) {
    }

    @Override // quadrant.mokafat.points.view.qr_code.QrCodeView
    public void showMessage(String message) {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }
}
