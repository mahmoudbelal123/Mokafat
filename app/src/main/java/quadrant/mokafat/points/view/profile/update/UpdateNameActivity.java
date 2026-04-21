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
import quadrant.mokafat.points.models.objects.login.loginResponse;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountView;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity;

/* JADX INFO: loaded from: classes.dex */
public class UpdateNameActivity extends AppCompatActivity implements View.OnClickListener, EditAccountView {
    private ImageView backBtn;
    private ImageView clearTxt;
    loginResponse loginResponse = new loginResponse();

    @Inject
    EditAccountPresenter mEditAccountPresenter;
    ProgressBar progressBarUpdateName;
    private Button updateNameBtn;
    private EditText updateNameEdit;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.mEditAccountPresenter.onAttach((EditAccountView) this);
        this.backBtn = (ImageView) findViewById(R.id.toolbar_back_update_name);
        this.progressBarUpdateName = (ProgressBar) findViewById(R.id.progressBar_update_name);
        this.backBtn.setOnClickListener(this);
        this.clearTxt = (ImageView) findViewById(R.id.imageView8_clear_name);
        this.clearTxt.setOnClickListener(this);
        this.updateNameBtn = (Button) findViewById(R.id.button_update_name);
        this.updateNameBtn.setOnClickListener(this);
        this.updateNameEdit = (EditText) findViewById(R.id.editText2_update_name);
        this.clearTxt.setVisibility(4);
        this.updateNameEdit.addTextChangedListener(new TextWatcher() { // from class: quadrant.mokafat.points.view.profile.update.UpdateNameActivity.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    UpdateNameActivity.this.clearTxt.setVisibility(0);
                } else {
                    UpdateNameActivity.this.clearTxt.setVisibility(4);
                }
                if (s.length() >= 3) {
                    UpdateNameActivity.this.updateNameBtn.setVisibility(0);
                } else {
                    UpdateNameActivity.this.updateNameBtn.setVisibility(4);
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
        if (id != R.id.button_update_name) {
            if (id == R.id.imageView8_clear_name) {
                this.updateNameEdit.setText("");
                return;
            } else {
                if (id == R.id.toolbar_back_update_name) {
                    finish();
                    return;
                }
                return;
            }
        }
        this.updateNameBtn.startAnimation(Utilities.clickEffect);
        if (getIntent().getStringExtra("name") != null) {
            if (!getIntent().getStringExtra("name").equals(this.updateNameEdit.getText().toString())) {
                this.mEditAccountPresenter.editAccountPresenter();
            } else {
                this.updateNameEdit.setError("Like the old, you cannot update");
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
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void hideLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showLoadingProfileInfo() {
        this.progressBarUpdateName.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void hideLoadingProfileInfo() {
        this.progressBarUpdateName.setVisibility(4);
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
        return this.updateNameEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getEmail() {
        if (getIntent().getStringExtra("email") != null) {
            return getIntent().getStringExtra("email");
        }
        return getIntent().getStringExtra("email");
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
