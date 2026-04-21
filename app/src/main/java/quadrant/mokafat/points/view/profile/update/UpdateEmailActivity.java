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
import java.io.File;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.edit_account.EditAccountResponse;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountView;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity;

/* JADX INFO: loaded from: classes.dex */
public class UpdateEmailActivity extends AppCompatActivity implements View.OnClickListener, EditAccountView {
    private ImageView backBtn;
    private ImageView clearTxt;

    @Inject
    EditAccountPresenter mEditAccountPresenter;
    ProgressBar progressBarUpdateEmail;
    private Button updateEmailBtn;
    private EditText updateEmailEdit;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.progressBarUpdateEmail = (ProgressBar) findViewById(R.id.progressBar_update_email);
        this.mEditAccountPresenter.onAttach((EditAccountView) this);
        this.backBtn = (ImageView) findViewById(R.id.toolbar_back_update_email);
        this.backBtn.setOnClickListener(this);
        this.clearTxt = (ImageView) findViewById(R.id.imageView8_clear_email);
        this.clearTxt.setOnClickListener(this);
        this.updateEmailBtn = (Button) findViewById(R.id.button_update_email);
        this.updateEmailBtn.setOnClickListener(this);
        this.updateEmailEdit = (EditText) findViewById(R.id.editText2_update_email);
        this.clearTxt.setVisibility(4);
        this.updateEmailEdit.addTextChangedListener(new TextWatcher() { // from class: quadrant.mokafat.points.view.profile.update.UpdateEmailActivity.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    UpdateEmailActivity.this.clearTxt.setVisibility(0);
                } else {
                    UpdateEmailActivity.this.clearTxt.setVisibility(4);
                }
                if (s.length() > 12) {
                    UpdateEmailActivity.this.updateEmailBtn.setVisibility(0);
                } else {
                    UpdateEmailActivity.this.updateEmailBtn.setVisibility(4);
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

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id != R.id.button_update_email) {
            if (id == R.id.imageView8_clear_email) {
                this.updateEmailEdit.setText("");
                return;
            } else {
                if (id == R.id.toolbar_back_update_email) {
                    finish();
                    return;
                }
                return;
            }
        }
        this.updateEmailBtn.startAnimation(Utilities.clickEffect);
        if (getIntent().getStringExtra("email") != null) {
            if (!getIntent().getStringExtra("email").equals(this.updateEmailEdit.getText().toString())) {
                this.mEditAccountPresenter.editAccountPresenter();
            } else {
                this.updateEmailEdit.setError("Like the old, you cannot update");
                this.clearTxt.setVisibility(4);
            }
        }
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showLoading() {
        this.progressBarUpdateEmail.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void hideLoading() {
        this.progressBarUpdateEmail.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showLoadingProfileInfo() {
        this.progressBarUpdateEmail.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void hideLoadingProfileInfo() {
        this.progressBarUpdateEmail.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showResponse(EditAccountResponse editAccountResponse) {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, 0).show();
        Utilities.UPDATE = 1;
        startActivity(new Intent(this, (Class<?>) ProfileActivity.class));
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getName() {
        if (getIntent().getStringExtra("name") != null) {
            return getIntent().getStringExtra("name");
        }
        return getIntent().getStringExtra("name");
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getEmail() {
        return this.updateEmailEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getMobile() {
        if (getIntent().getStringExtra("mobile") != null) {
            return getIntent().getStringExtra("mobile");
        }
        return getIntent().getStringExtra("mobile");
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public File getMainImage() {
        return null;
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public int getReceiveNewOffersNotification() {
        return getIntent().getIntExtra("receive_not", 0);
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public int getAllowNotification() {
        return getIntent().getIntExtra("allow_not", 0);
    }
}
