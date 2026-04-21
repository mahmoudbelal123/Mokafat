package quadrant.mokafat.points.view.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.HexagonMaskView;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;
import quadrant.mokafat.points.view.allow_notification.AllowNotificationActivity;
import quadrant.mokafat.points.view.log_out.LogOutPresenter;
import quadrant.mokafat.points.view.log_out.LogOutView;
import quadrant.mokafat.points.view.login.LoginActivity;
import quadrant.mokafat.points.view.profile.edit_account.EditProfileFragment;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter;
import quadrant.mokafat.points.view.profile.get_profile.ProfileView;

/* JADX INFO: loaded from: classes.dex */
public class SettingsFragment extends Fragment implements View.OnClickListener, LogOutView, ProfileView, OnMapReadyCallback {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private ConstraintLayout constraintLayoutNotification;
    private TextView corporateTxt;
    private String email;
    private HexagonMaskView hexagonMaskView;
    LinearLayout linearLayoutEditProfile;

    @Inject
    LogOutPresenter logOutPresenter;
    private ConstraintLayout mConstrainLayoutSignOut;
    private View mView;
    private String mobile;
    private TextView mobileTxt;
    private String name;
    private TextView nameTxt;

    @Inject
    ProfilePresenter profilePresenter;
    ProgressBar progressOut;
    Dialog signOutDialog;
    private Switch switchLocation;
    TextView yesTxt;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_setting_cards, container, false);
        this.constraintLayoutNotification = (ConstraintLayout) this.mView.findViewById(R.id.constraintLayout_notification);
        this.signOutDialog = new Dialog(getActivity());
        this.mConstrainLayoutSignOut = (ConstraintLayout) this.mView.findViewById(R.id.constraintLayout_sign_out_settings);
        this.switchLocation = (Switch) this.mView.findViewById(R.id.switch2_location);
        this.mConstrainLayoutSignOut.setOnClickListener(this);
        this.hexagonMaskView = (HexagonMaskView) this.mView.findViewById(R.id.imageView_account_image);
        this.nameTxt = (TextView) this.mView.findViewById(R.id.textView_setting_name);
        this.mobileTxt = (TextView) this.mView.findViewById(R.id.textView_setting_mobile);
        this.corporateTxt = (TextView) this.mView.findViewById(R.id.textView_setting_corporate);
        this.linearLayoutEditProfile = (LinearLayout) this.mView.findViewById(R.id.linear_edit_profile);
        this.linearLayoutEditProfile.setOnClickListener(this);
        this.constraintLayoutNotification.setOnClickListener(this);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.logOutPresenter.onAttach((LogOutView) this);
        this.profilePresenter.onAttach((ProfileView) this);
        this.profilePresenter.getProfilePresenter();
        this.switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: quadrant.mokafat.points.view.settings.SettingsFragment.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    SettingsFragment.this.switchLocation.setChecked(false);
                    Toast.makeText(SettingsFragment.this.getActivity(), "GPS Off", 0).show();
                    String provider = Settings.Secure.getString(SettingsFragment.this.getActivity().getContentResolver(), "location_providers_allowed");
                    if (provider.contains("gps")) {
                        Intent poke = new Intent();
                        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
                        poke.addCategory("android.intent.category.ALTERNATIVE");
                        poke.setData(Uri.parse("3"));
                        SettingsFragment.this.getActivity().sendBroadcast(poke);
                        return;
                    }
                    return;
                }
                SettingsFragment.this.checkLocationPermission();
            }
        });
        return this.mView;
    }

    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        } else {
            turnGPSOn();
        }
    }

    public void turnGPSOn() {
        LocationManager service = (LocationManager) getActivity().getSystemService(FirebaseAnalytics.Param.LOCATION);
        boolean enabled = service.isProviderEnabled("gps");
        if (!enabled) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setMessage("GPS Permission").setCancelable(false).setPositiveButton("Allow", new DialogInterface.OnClickListener() { // from class: quadrant.mokafat.points.view.settings.SettingsFragment.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int id) {
                    Intent callGPSSettingIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                    SettingsFragment.this.startActivity(callGPSSettingIntent);
                }
            });
            alertDialogBuilder.setNegativeButton("Deny", new DialogInterface.OnClickListener() { // from class: quadrant.mokafat.points.view.settings.SettingsFragment.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();
            return;
        }
        this.switchLocation.setChecked(true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.constraintLayout_notification) {
            Intent intent = new Intent(getActivity(), (Class<?>) AllowNotificationActivity.class);
            intent.putExtra("name", this.name);
            intent.putExtra("email", this.email);
            intent.putExtra("mobile", this.mobile);
            intent.putExtra("receive_not", Utilities.RECEIVE_NOTIFICATION);
            intent.putExtra("allow_not", Utilities.ALLOW_NOTIFICATION);
            startActivity(intent);
            return;
        }
        if (id != R.id.constraintLayout_sign_out_settings) {
            if (id == R.id.linear_edit_profile) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new EditProfileFragment());
                ft.addToBackStack("edit_profile");
                ft.commit();
                return;
            }
            return;
        }
        this.signOutDialog = Utilities.signOutDialog(getActivity());
        this.yesTxt = (TextView) this.signOutDialog.findViewById(R.id.dialog_yes);
        this.progressOut = (ProgressBar) this.signOutDialog.findViewById(R.id.dialog_progress_log_out);
        this.signOutDialog.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.settings.SettingsFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v2) {
                SettingsFragment.this.signOutDialog.dismiss();
            }
        });
        this.yesTxt.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.settings.SettingsFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v2) {
                SettingsFragment.this.logOutPresenter.doLogOutPresenter();
            }
        });
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void hideLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showLoadingProfileInfo() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void hideLoadingProfileInfo() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showResponse(ProfileResponse profileResponse) {
        GlideApp.with(getActivity()).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + profileResponse.getData().getMain_image()).into(this.hexagonMaskView);
        this.nameTxt.setText(profileResponse.getData().getName());
        this.mobileTxt.setText(profileResponse.getData().getMobile());
        this.corporateTxt.setText(Utilities.retrieveUserInfoSharedPreferences(getActivity()).getData().getVendor().getTitle());
        Utilities.ALLOW_NOTIFICATION = profileResponse.getData().getPush_notification();
        Utilities.RECEIVE_NOTIFICATION = profileResponse.getData().getReceive_new_offers();
        this.name = profileResponse.getData().getName();
        this.mobile = profileResponse.getData().getMobile();
        this.email = profileResponse.getData().getEmail();
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setProfileImage(String urlImage) {
        GlideApp.with(getActivity()).load2(urlImage).into(this.hexagonMaskView);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setQrCodeImage(String urlImage) {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setName(String name) {
        this.nameTxt.setText(name);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setMobileNumber(String mobile) {
        this.mobileTxt.setText(mobile);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setCorporateName(String corporateName) {
        this.corporateTxt.setText(corporateName);
    }

    @Override // quadrant.mokafat.points.view.log_out.LogOutView
    public void showOutLoading() {
        this.yesTxt.setVisibility(8);
        this.progressOut.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.log_out.LogOutView
    public void hideOutLoading() {
        this.yesTxt.setVisibility(0);
        this.progressOut.setVisibility(8);
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

    @Override // quadrant.mokafat.points.view.log_out.LogOutView
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.log_out.LogOutView
    public void showSuccessMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) {
    }

    @Override // android.support.v4.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                this.switchLocation.setChecked(true);
            } else {
                turnGPSOn();
            }
        }
    }
}
