package quadrant.mokafat.points.view.allow_notification;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import java.io.File;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.edit_account.EditAccountResponse;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountView;

/* JADX INFO: loaded from: classes.dex */
public class AllowNotificationActivity extends AppCompatActivity implements EditAccountView {
    int allowNotification;
    private ImageView backBtn;

    @Inject
    EditAccountPresenter editAccountPresenter;
    int receiveNewOffers;
    private Switch switchAllowNotification;
    private Switch switchReceiveNewOffers;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_notification);
        this.switchAllowNotification = (Switch) findViewById(R.id.switch_push_notification);
        this.switchReceiveNewOffers = (Switch) findViewById(R.id.switch_receive_new_offers);
        this.backBtn = (ImageView) findViewById(R.id.toolbar_back_single_partner);
        this.backBtn.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.allow_notification.AllowNotificationActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AllowNotificationActivity.this.finish();
            }
        });
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.editAccountPresenter.onAttach((EditAccountView) this);
        this.allowNotification = getIntent().getIntExtra("allow_not", 0);
        if (this.allowNotification == 1) {
            this.switchAllowNotification.setChecked(true);
        } else {
            this.switchAllowNotification.setChecked(false);
        }
        this.receiveNewOffers = getIntent().getIntExtra("receive_not", 0);
        if (this.receiveNewOffers == 1) {
            this.switchReceiveNewOffers.setChecked(true);
        } else {
            this.switchReceiveNewOffers.setChecked(false);
        }
        this.switchReceiveNewOffers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: quadrant.mokafat.points.view.allow_notification.AllowNotificationActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AllowNotificationActivity.this.receiveNewOffers = 1;
                    AllowNotificationActivity.this.editAccountPresenter.editAccountPresenter();
                } else {
                    AllowNotificationActivity.this.receiveNewOffers = 0;
                    AllowNotificationActivity.this.editAccountPresenter.editAccountPresenter();
                }
            }
        });
        this.switchAllowNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: quadrant.mokafat.points.view.allow_notification.AllowNotificationActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AllowNotificationActivity.this.allowNotification = 1;
                    AllowNotificationActivity.this.editAccountPresenter.editAccountPresenter();
                } else {
                    AllowNotificationActivity.this.allowNotification = 0;
                    AllowNotificationActivity.this.editAccountPresenter.editAccountPresenter();
                }
            }
        });
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void hideLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showLoadingProfileInfo() {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void hideLoadingProfileInfo() {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showResponse(EditAccountResponse editAccountResponse) {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showMessage(String message) {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showErrorMessage(String message) {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getName() {
        return getIntent().getStringExtra("name");
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getEmail() {
        return getIntent().getStringExtra("email");
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getMobile() {
        return getIntent().getStringExtra("mobile");
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public File getMainImage() {
        return null;
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public int getReceiveNewOffersNotification() {
        return this.receiveNewOffers;
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public int getAllowNotification() {
        return this.allowNotification;
    }
}
