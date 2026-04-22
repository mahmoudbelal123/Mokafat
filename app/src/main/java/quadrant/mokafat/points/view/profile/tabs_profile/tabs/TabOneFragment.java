package quadrant.mokafat.points.view.profile.tabs_profile.tabs;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.util.CrashUtils;
import es.dmoral.toasty.Toasty;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.HexagonMaskView;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.edit_account.EditAccountResponse;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;
import quadrant.mokafat.points.view.log_out.LogOutPresenter;
import quadrant.mokafat.points.view.log_out.LogOutView;
import quadrant.mokafat.points.view.login.LoginActivity;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountView;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter;
import quadrant.mokafat.points.view.profile.get_profile.ProfileView;
import quadrant.mokafat.points.view.qr_code.QrCodeActivity;

/* JADX INFO: loaded from: classes.dex */
public class TabOneFragment extends Fragment implements ProfileView, View.OnClickListener, EditAccountView, LogOutView {
    private LinearLayout LinearUpdateEmail;
    private LinearLayout LinearUpdateMobile;
    private LinearLayout LinearUpdatePassword;
    private LinearLayout LinearupdateName;
    int allowNotification;
    private Bitmap bitmap;
    private byte[] bitmapdata;
    private ImageView coverImage;
    EditAccountResponse editAccountResponse;
    File f;
    private FileOutputStream fos;
    private HexagonMaskView hexagonMaskView;

    @Inject
    LogOutPresenter logOutPresenter;

    @Inject
    EditAccountPresenter mEditAccountPresenter;
    private EditText mEmailEdit;
    private EditText mMobileEdit;
    private EditText mNameEdit;
    private EditText mPasswordEdit;
    private ImageView mSecond;
    private ConstraintLayout mSignOut;

    @Inject
    ProfilePresenter profilePresenter;
    ProgressBar progressBarLoadImage;
    ProgressBar progressBarLoadingProfileInfo;
    ProgressBar progressBarOut;
    private ImageView qrCodeImage;
    private Dialog signOutDialog;
    private ImageView updateCoverImage;
    private ImageView uploadProfileImage;
    private View view;
    TextView yesTxt;
    private final int PICK_FROM_GALLERY = 2018;
    private final int PICK_FROM_GALLERY_COVER = 2030;
    private AlphaAnimation updateImageClick = new AlphaAnimation(5.0f, 0.1f);
    int receiveNewOffers = 1;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_tab_one, container, false);
        initViews();
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.mEditAccountPresenter.onAttach((EditAccountView) this);
        this.logOutPresenter.onAttach((LogOutView) this);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.profilePresenter.onAttach((ProfileView) this);
        return this.view;
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.profilePresenter.getProfilePresenter();
    }

    private void initViews() {
        this.progressBarLoadImage = (ProgressBar) this.view.findViewById(R.id.progressBar_profile_image);
        this.progressBarLoadingProfileInfo = (ProgressBar) this.view.findViewById(R.id.progressBar_profile_image);
        this.hexagonMaskView = (HexagonMaskView) this.view.findViewById(R.id.hexagonMaskView);
        this.uploadProfileImage = (ImageView) this.view.findViewById(R.id.imageView3_edit_profile_image);
        this.coverImage = (ImageView) this.view.findViewById(R.id.imageView2_cover);
        this.updateCoverImage = (ImageView) this.view.findViewById(R.id.imageView7_update_cover);
        this.qrCodeImage = (ImageView) this.view.findViewById(R.id.imageView5_qr_code);
        this.qrCodeImage.setOnClickListener(this);
        this.updateCoverImage.setOnClickListener(this);
        this.uploadProfileImage.setOnClickListener(this);
        this.mSignOut = (ConstraintLayout) this.view.findViewById(R.id.constraintLayout_sign_out_profile);
        this.mSignOut.setOnClickListener(this);
        this.mNameEdit = (EditText) this.view.findViewById(R.id.editText2_name);
        this.mMobileEdit = (EditText) this.view.findViewById(R.id.editText3_mobile);
        this.mMobileEdit.setOnClickListener(this);
        this.mEmailEdit = (EditText) this.view.findViewById(R.id.editText4_email);
        this.mEmailEdit.setOnClickListener(this);
        this.mPasswordEdit = (EditText) this.view.findViewById(R.id.editText5_password);
        this.mPasswordEdit.setOnClickListener(this);
        this.LinearUpdateMobile = (LinearLayout) this.view.findViewById(R.id.Linear_update_mobile);
        this.LinearupdateName = (LinearLayout) this.view.findViewById(R.id.Linear_update_name);
        this.LinearUpdateEmail = (LinearLayout) this.view.findViewById(R.id.Linear_update_email);
        this.LinearUpdatePassword = (LinearLayout) this.view.findViewById(R.id.Linear_update_password);
        this.LinearupdateName.setOnTouchListener(new View.OnTouchListener() { // from class: quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                TabOneFragment.this.mEditAccountPresenter.startUpdateNameActivity(TabOneFragment.this.getActivity());
                return false;
            }
        });
        this.LinearUpdateMobile.setOnTouchListener(new View.OnTouchListener() { // from class: quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                TabOneFragment.this.mEditAccountPresenter.startUpdateMobile(TabOneFragment.this.getActivity());
                return false;
            }
        });
        this.LinearUpdateEmail.setOnTouchListener(new View.OnTouchListener() { // from class: quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                TabOneFragment.this.mEditAccountPresenter.startUpdateEmailActivity(TabOneFragment.this.getActivity());
                return false;
            }
        });
        this.LinearUpdatePassword.setOnTouchListener(new View.OnTouchListener() { // from class: quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                TabOneFragment.this.mEditAccountPresenter.startUpdatePasswordActivity(TabOneFragment.this.getActivity());
                return false;
            }
        });
        this.signOutDialog = new Dialog(getActivity());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.constraintLayout_sign_out_profile) {
            this.signOutDialog = Utilities.signOutDialog(getActivity());
            this.progressBarOut = (ProgressBar) this.signOutDialog.findViewById(R.id.dialog_progress_log_out);
            this.yesTxt = (TextView) this.signOutDialog.findViewById(R.id.dialog_yes);
            this.signOutDialog.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    TabOneFragment.this.signOutDialog.dismiss();
                }
            });
            this.yesTxt.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment.6
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    TabOneFragment.this.logOutPresenter.doLogOutPresenter();
                }
            });
            return;
        }
        if (id == R.id.imageView3_edit_profile_image) {
            this.uploadProfileImage.startAnimation(this.updateImageClick);
            try {
                if (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") != 0) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 2018);
                } else {
                    Intent galleryIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 2018);
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (id == R.id.imageView5_qr_code) {
            startActivity(new Intent(getActivity(), (Class<?>) QrCodeActivity.class));
            return;
        }
        if (id == R.id.imageView7_update_cover) {
            this.updateCoverImage.startAnimation(this.updateImageClick);
            try {
                if (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") != 0) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 2018);
                } else {
                    Intent galleryIntent2 = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent2, 2030);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2018) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                Toasty.warning(getActivity(), "Not Allowed to Access the Gallery", 8000, true).show();
                return;
            } else {
                Intent galleryIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 2018);
                return;
            }
        }
        if (requestCode == 2030) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                Toasty.warning(getActivity(), "Not Allowed to Access the Gallery", 8000, true).show();
            } else {
                Intent galleryIntent2 = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent2, 2030);
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2018 && resultCode == -1 && data != null) {
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {"_data"};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                this.f = new File(getActivity().getCacheDir(), "account_image");
                this.f.createNewFile();
                this.bitmap = BitmapFactory.decodeFile(picturePath);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                this.bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                this.bitmapdata = bos.toByteArray();
                this.fos = new FileOutputStream(this.f);
                this.fos.write(this.bitmapdata);
                this.fos.flush();
                this.fos.close();
                this.hexagonMaskView.setImageBitmap(this.bitmap);
                this.mEditAccountPresenter.editAccountPresenter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 2030 && resultCode == -1 && data != null) {
            try {
                Uri selectedImage2 = data.getData();
                String[] filePathColumn2 = {"_data"};
                Cursor cursor2 = getActivity().getContentResolver().query(selectedImage2, filePathColumn2, null, null, null);
                cursor2.moveToFirst();
                int columnIndex2 = cursor2.getColumnIndex(filePathColumn2[0]);
                String picturePath2 = cursor2.getString(columnIndex2);
                cursor2.close();
                this.f = new File(getActivity().getCacheDir(), "account_image");
                this.f.createNewFile();
                this.bitmap = BitmapFactory.decodeFile(picturePath2);
                ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
                this.bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos2);
                this.bitmapdata = bos2.toByteArray();
                this.fos = new FileOutputStream(this.f);
                this.fos.write(this.bitmapdata);
                this.fos.flush();
                this.fos.close();
                this.coverImage.setImageBitmap(this.bitmap);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showLoading() {
        this.progressBarLoadImage.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void hideLoading() {
        this.progressBarLoadImage.setVisibility(8);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showLoadingProfileInfo() {
        this.progressBarLoadingProfileInfo.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void hideLoadingProfileInfo() {
        this.progressBarLoadingProfileInfo.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showResponse(ProfileResponse profileResponse) {
        GlideApp.with(getActivity()).load("http://199.247.4.89/mokafat/dev/public/uploads/large/" + profileResponse.getData().getMain_image()).into(this.hexagonMaskView);
        this.mEmailEdit.setText(profileResponse.getData().getEmail());
        this.mMobileEdit.setText(profileResponse.getData().getMobile());
        this.mNameEdit.setText(profileResponse.getData().getName());
        this.receiveNewOffers = profileResponse.getData().getReceive_new_offers();
        this.allowNotification = profileResponse.getData().getPush_notification();
        Utilities.ALLOW_NOTIFICATION = this.allowNotification;
        Utilities.RECEIVE_NOTIFICATION = this.receiveNewOffers;
    }

    @Override // quadrant.mokafat.points.view.log_out.LogOutView
    public void showOutLoading() {
        this.progressBarOut.setVisibility(0);
        this.yesTxt.setVisibility(8);
    }

    @Override // quadrant.mokafat.points.view.log_out.LogOutView
    public void hideOutLoading() {
        this.progressBarOut.setVisibility(8);
        this.yesTxt.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.log_out.LogOutView
    public void showActivity() {
        showLoginActivity();
    }

    private void showLoginActivity() {
        getActivity().finish();
        Intent intent = new Intent(getActivity(), (Class<?>) LoginActivity.class);
        intent.setFlags(CrashUtils.ErrorDialogData.DYNAMITE_CRASH);
        startActivity(intent);
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showResponse(EditAccountResponse editAccountResponse) {
        GlideApp.with(getActivity()).load("http://199.247.4.89/mokafat/dev/public/uploads/large/" + editAccountResponse.getData().getMain_image()).into(this.hexagonMaskView);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 1).show();
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setProfileImage(String urlImage) {
        GlideApp.with(getActivity()).load("http://199.247.4.89/mokafat/dev/public/uploads/large/" + urlImage).into(this.hexagonMaskView);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setQrCodeImage(String urlImage) {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setName(String name) {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setMobileNumber(String mobile) {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setCorporateName(String corporateName) {
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, 1).show();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public void showSuccessMessage(String message) {
        Toast.makeText(getActivity(), message, 1).show();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getName() {
        return this.mNameEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getEmail() {
        return this.mEmailEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public String getMobile() {
        return this.mMobileEdit.getText().toString();
    }

    @Override // quadrant.mokafat.points.view.profile.edit_account.EditAccountView
    public File getMainImage() {
        return this.f;
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
