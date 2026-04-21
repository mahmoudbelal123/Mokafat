package quadrant.mokafat.points.view.profile.get_profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.EndPoints;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.HexagonMaskView;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.edit_account.EditAccountResponse;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.ActivitiesFragment;
import quadrant.mokafat.points.view.get_location_map.MapsActivity;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersFragment;
import quadrant.mokafat.points.view.help.HelpMainFragment;
import quadrant.mokafat.points.view.profile.edit_account.EditProfileFragment;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment;
import quadrant.mokafat.points.view.qr_code.QrCodeActivity;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionsFragment;
import quadrant.mokafat.points.view.settings.SettingsFragment;
import quadrant.mokafat.points.view.start_tour.StartTourActivity;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherFragment;

/* JADX INFO: loaded from: classes.dex */
public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ProfileView, View.OnClickListener, DrawerLayout.DrawerListener {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    DrawerLayout drawer;
    EditAccountResponse editAccountResponse;
    LinearLayout linearLayoutActivities;
    LinearLayout linearLayoutGetYourVoucher;
    LinearLayout linearLayoutHelp;
    LinearLayout linearLayoutHome;
    LinearLayout linearLayoutPartners;
    LinearLayout linearLayoutSections;
    LinearLayout linearLayoutSettings;
    private TextView mCorporateNameTxt;
    private ImageView mEditAccountImage;
    private TextView mMobileNumberTxt;
    private TextView mNameTxt;
    private ImageView mNearbyImage;
    private TextView mOpenSiteTxt;
    private HexagonMaskView mProfileImage;

    @Inject
    ProfilePresenter mProfilePresenter;
    private ImageView mQrCodeImage;
    private SearchView mSearchView;
    private TextView mTitleToolbarTxt;
    private RelativeLayout relativeLayoutDrawer;
    ActionBarDrawerToggle toggle;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initViews();
        this.mSearchView = (SearchView) toolbar.findViewById(R.id.search_view);
        this.mNearbyImage = (ImageView) toolbar.findViewById(R.id.imageView_nearby_toolbar);
        this.editAccountResponse = new EditAccountResponse();
        this.mSearchView.setOnSearchClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.ProfileActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ProfileActivity.this.mTitleToolbarTxt.setVisibility(8);
            }
        });
        this.mSearchView.setOnCloseListener(new SearchView.OnCloseListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.ProfileActivity.2
            @Override // android.support.v7.widget.SearchView.OnCloseListener
            public boolean onClose() {
                ProfileActivity.this.mTitleToolbarTxt.setVisibility(0);
                return false;
            }
        });
        this.mNearbyImage.setOnClickListener(this);
        this.mSearchView.setVisibility(4);
        this.mNearbyImage.setVisibility(4);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.mProfilePresenter.onAttach((ProfileView) this);
        this.mProfilePresenter.getProfilePresenter();
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toggle = new ActionBarDrawerToggle(this, this.drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.addDrawerListener(this.toggle);
        this.toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        this.drawer.setDrawerListener(this);
        if (getIntent().getStringExtra("x") == null) {
            addHome();
            return;
        }
        if (getIntent().getStringExtra("x") != null && Utilities.Notification == 2) {
            addHome();
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getIntent().getStringExtra("title"));
                builder.setMessage(getIntent().getStringExtra("message") + "\nyour code is  " + getIntent().getStringExtra("code") + "\nFrom  " + getIntent().getStringExtra("provider") + "\nValue is  " + getIntent().getStringExtra(FirebaseAnalytics.Param.VALUE));
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.ProfileActivity.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } catch (Exception e) {
            }
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (this.editAccountResponse.getData() != null) {
            GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.editAccountResponse.getData().getMain_image()).into(this.mProfileImage);
            GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.editAccountResponse.getData().getQr_image()).into(this.mQrCodeImage);
            this.mNameTxt.setText(this.editAccountResponse.getData().getName());
            this.mMobileNumberTxt.setText(this.editAccountResponse.getData().getMobile());
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.editAccountResponse.getData() != null) {
            GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.editAccountResponse.getData().getMain_image()).into(this.mProfileImage);
            GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.editAccountResponse.getData().getQr_image()).into(this.mQrCodeImage);
            this.mNameTxt.setText(this.editAccountResponse.getData().getName());
            this.mMobileNumberTxt.setText(this.editAccountResponse.getData().getMobile());
        }
    }

    private void initViews() {
        this.linearLayoutHome = (LinearLayout) findViewById(R.id.linear_layout_home);
        this.linearLayoutHome.setOnClickListener(this);
        this.linearLayoutPartners = (LinearLayout) findViewById(R.id.linear_partners);
        this.linearLayoutPartners.setOnClickListener(this);
        this.linearLayoutActivities = (LinearLayout) findViewById(R.id.linear_activities);
        this.linearLayoutActivities.setOnClickListener(this);
        this.linearLayoutHelp = (LinearLayout) findViewById(R.id.linear_help);
        this.linearLayoutHelp.setOnClickListener(this);
        this.linearLayoutSettings = (LinearLayout) findViewById(R.id.linear_settings);
        this.linearLayoutSettings.setOnClickListener(this);
        this.linearLayoutSections = (LinearLayout) findViewById(R.id.linear_sections);
        this.linearLayoutSections.setOnClickListener(this);
        this.linearLayoutGetYourVoucher = (LinearLayout) findViewById(R.id.linear_layout_get_your_voucher);
        this.linearLayoutGetYourVoucher.setOnClickListener(this);
        this.mOpenSiteTxt = (TextView) findViewById(R.id.textView_open_site_from_navigation);
        this.mOpenSiteTxt.setOnClickListener(this);
        this.mQrCodeImage = (ImageView) findViewById(R.id.imageView_qr_code);
        this.mNameTxt = (TextView) findViewById(R.id.textView_name);
        this.mMobileNumberTxt = (TextView) findViewById(R.id.textView_mobile);
        this.mCorporateNameTxt = (TextView) findViewById(R.id.textView_corporate_name);
        this.mProfileImage = (HexagonMaskView) findViewById(R.id.imageView_account_image);
        this.mProfileImage.setOnClickListener(this);
        this.mEditAccountImage = (ImageView) findViewById(R.id.imageView_edit_account);
        this.mTitleToolbarTxt = (TextView) findViewById(R.id.toolbar_title_profile);
        this.mQrCodeImage.setOnClickListener(this);
        this.mEditAccountImage.setOnClickListener(this);
    }

    private void setBackgroundGetVoucher(LinearLayout linearLayoutHome, LinearLayout linearLayoutPartners, LinearLayout linearLayoutActivities, LinearLayout linearLayoutHelp, LinearLayout linearLayoutSettings, LinearLayout linearLayoutSections, LinearLayout linearLayoutGetYourVoucher) {
        linearLayoutHome.setBackground(null);
        linearLayoutPartners.setBackground(null);
        linearLayoutActivities.setBackground(null);
        linearLayoutHelp.setBackground(null);
        linearLayoutSettings.setBackground(null);
        linearLayoutSections.setBackground(null);
        linearLayoutGetYourVoucher.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.linear_bg));
    }

    private void setBackgroundHome(LinearLayout linearLayoutHome, LinearLayout linearLayoutPartners, LinearLayout linearLayoutActivities, LinearLayout linearLayoutHelp, LinearLayout linearLayoutSettings, LinearLayout linearLayoutSections, LinearLayout linearLayoutGetYourVoucher) {
        linearLayoutHome.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.linear_bg));
        linearLayoutPartners.setBackground(null);
        linearLayoutActivities.setBackground(null);
        linearLayoutHelp.setBackground(null);
        linearLayoutSettings.setBackground(null);
        linearLayoutSections.setBackground(null);
        linearLayoutGetYourVoucher.setBackground(null);
    }

    private void setBackgroundPartners(LinearLayout linearLayoutHome, LinearLayout linearLayoutPartners, LinearLayout linearLayoutActivities, LinearLayout linearLayoutHelp, LinearLayout linearLayoutSettings, LinearLayout linearLayoutSections, LinearLayout linearLayoutGetYourVoucher) {
        linearLayoutHome.setBackground(null);
        linearLayoutPartners.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.linear_bg));
        linearLayoutActivities.setBackground(null);
        linearLayoutHelp.setBackground(null);
        linearLayoutSettings.setBackground(null);
        linearLayoutSections.setBackground(null);
        linearLayoutGetYourVoucher.setBackground(null);
    }

    private void setBackgroundActivities(LinearLayout linearLayoutHome, LinearLayout linearLayoutPartners, LinearLayout linearLayoutActivities, LinearLayout linearLayoutHelp, LinearLayout linearLayoutSettings, LinearLayout linearLayoutSections, LinearLayout linearLayoutGetYourVoucher) {
        linearLayoutHome.setBackground(null);
        linearLayoutPartners.setBackground(null);
        linearLayoutActivities.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.linear_bg));
        linearLayoutHelp.setBackground(null);
        linearLayoutSettings.setBackground(null);
        linearLayoutSections.setBackground(null);
        linearLayoutGetYourVoucher.setBackground(null);
    }

    private void setBackgroundHelp(LinearLayout linearLayoutHome, LinearLayout linearLayoutPartners, LinearLayout linearLayoutActivities, LinearLayout linearLayoutHelp, LinearLayout linearLayoutSettings, LinearLayout linearLayoutSections, LinearLayout linearLayoutGetYourVoucher) {
        linearLayoutHome.setBackground(null);
        linearLayoutPartners.setBackground(null);
        linearLayoutActivities.setBackground(null);
        linearLayoutHelp.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.linear_bg));
        linearLayoutSettings.setBackground(null);
        linearLayoutSections.setBackground(null);
        linearLayoutGetYourVoucher.setBackground(null);
    }

    private void setBackgroundSettings(LinearLayout linearLayoutHome, LinearLayout linearLayoutPartners, LinearLayout linearLayoutActivities, LinearLayout linearLayoutHelp, LinearLayout linearLayoutSettings, LinearLayout linearLayoutSections, LinearLayout linearLayoutGetYourVoucher) {
        linearLayoutHome.setBackground(null);
        linearLayoutPartners.setBackground(null);
        linearLayoutActivities.setBackground(null);
        linearLayoutHelp.setBackground(null);
        linearLayoutSections.setBackground(null);
        linearLayoutGetYourVoucher.setBackground(null);
        linearLayoutSettings.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.linear_bg));
    }

    private void setBackgroundSections(LinearLayout linearLayoutHome, LinearLayout linearLayoutPartners, LinearLayout linearLayoutActivities, LinearLayout linearLayoutHelp, LinearLayout linearLayoutSettings, LinearLayout linearLayoutSections, LinearLayout linearLayoutGetYourVoucher) {
        linearLayoutHome.setBackground(null);
        linearLayoutPartners.setBackground(null);
        linearLayoutActivities.setBackground(null);
        linearLayoutHelp.setBackground(null);
        linearLayoutSettings.setBackground(null);
        linearLayoutSections.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.linear_bg));
        linearLayoutGetYourVoucher.setBackground(null);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (Utilities.back) {
            showExitDialog();
        } else {
            addHome();
        }
    }

    private void showExitDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_exit);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(3);
        dialog.getWindow().setLayout(-1, -2);
        dialog.setCancelable(true);
        TextView yesExit = (TextView) dialog.findViewById(R.id.dialog_yes);
        TextView cancelExit = (TextView) dialog.findViewById(R.id.dialog_cancel);
        yesExit.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.ProfileActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                dialog.dismiss();
                ProfileActivity.this.finish();
                Intent intent = new Intent(ProfileActivity.this, (Class<?>) StartTourActivity.class);
                intent.setFlags(603979776);
                ProfileActivity.this.startActivity(intent);
            }
        });
        cancelExit.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.ProfileActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem item) {
        item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + profileResponse.getData().getMain_image()).into(this.mProfileImage);
        GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + profileResponse.getData().getQr_image()).into(this.mQrCodeImage);
        this.mNameTxt.setText(profileResponse.getData().getName());
        this.mMobileNumberTxt.setText(profileResponse.getData().getMobile());
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void showMessage(String message) {
        Toast.makeText(this, "" + message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.ProfileView
    public void setProfileImage(String urlImage) {
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
        this.mCorporateNameTxt.setText(corporateName);
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }

    private void addHome() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        }
        setBackgroundHome(this.linearLayoutHome, this.linearLayoutPartners, this.linearLayoutActivities, this.linearLayoutHelp, this.linearLayoutSettings, this.linearLayoutSections, this.linearLayoutGetYourVoucher);
        this.mSearchView.setVisibility(4);
        this.mNearbyImage.setVisibility(4);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new SlidesFragment());
        ft.addToBackStack(EndPoints.GET_SLIDES);
        ft.commit();
        this.mTitleToolbarTxt.setVisibility(0);
        this.mTitleToolbarTxt.setText("Mokafat");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_account_image /* 2131296435 */:
                this.mTitleToolbarTxt.setText("Account");
                this.mSearchView.setVisibility(4);
                this.mNearbyImage.setVisibility(4);
                addEditProfileFragment();
                if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                    this.drawer.closeDrawer(GravityCompat.START);
                }
                Utilities.back = false;
                break;
            case R.id.imageView_edit_account /* 2131296436 */:
                this.mTitleToolbarTxt.setText("Account");
                this.mSearchView.setVisibility(4);
                this.mNearbyImage.setVisibility(4);
                addEditProfileFragment();
                if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                    this.drawer.closeDrawer(GravityCompat.START);
                }
                Utilities.back = false;
                break;
            case R.id.imageView_nearby_toolbar /* 2131296438 */:
                checkLocationPermission();
                break;
            case R.id.imageView_qr_code /* 2131296443 */:
                startActivity(new Intent(this, (Class<?>) QrCodeActivity.class));
                Utilities.back = false;
                break;
            case R.id.linear_activities /* 2131296465 */:
                addActivitiesFragment();
                Utilities.back = false;
                break;
            case R.id.linear_help /* 2131296468 */:
                setBackgroundHelp(this.linearLayoutHome, this.linearLayoutPartners, this.linearLayoutActivities, this.linearLayoutHelp, this.linearLayoutSettings, this.linearLayoutSections, this.linearLayoutGetYourVoucher);
                addHelpFragment();
                this.mSearchView.setVisibility(0);
                this.mTitleToolbarTxt.setText("Help");
                this.mNearbyImage.setVisibility(4);
                if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                    this.drawer.closeDrawer(GravityCompat.START);
                }
                Utilities.back = false;
                break;
            case R.id.linear_layout_get_your_voucher /* 2131296469 */:
                setBackgroundGetVoucher(this.linearLayoutHome, this.linearLayoutPartners, this.linearLayoutActivities, this.linearLayoutHelp, this.linearLayoutSettings, this.linearLayoutSections, this.linearLayoutGetYourVoucher);
                if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                    this.drawer.closeDrawer(GravityCompat.START);
                }
                this.mSearchView.setVisibility(4);
                this.mNearbyImage.setVisibility(0);
                this.mTitleToolbarTxt.setText("Get Your Voucher");
                addGetYourVoucherFragment();
                Utilities.back = false;
                break;
            case R.id.linear_layout_home /* 2131296470 */:
                addHome();
                break;
            case R.id.linear_partners /* 2131296471 */:
                if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                    this.drawer.closeDrawer(GravityCompat.START);
                }
                setBackgroundPartners(this.linearLayoutHome, this.linearLayoutPartners, this.linearLayoutActivities, this.linearLayoutHelp, this.linearLayoutSettings, this.linearLayoutSections, this.linearLayoutGetYourVoucher);
                this.mSearchView.setVisibility(4);
                this.mNearbyImage.setVisibility(0);
                this.mTitleToolbarTxt.setText("Partners");
                addPartnersContainerFragment();
                Utilities.back = false;
                break;
            case R.id.linear_sections /* 2131296473 */:
                this.mSearchView.setVisibility(4);
                this.mNearbyImage.setVisibility(0);
                this.mTitleToolbarTxt.setText("Sections");
                addSectionsFragment();
                setBackgroundSections(this.linearLayoutHome, this.linearLayoutPartners, this.linearLayoutActivities, this.linearLayoutHelp, this.linearLayoutSettings, this.linearLayoutSections, this.linearLayoutGetYourVoucher);
                if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                    this.drawer.closeDrawer(GravityCompat.START);
                }
                Utilities.back = false;
                break;
            case R.id.linear_settings /* 2131296474 */:
                addSettingFragment();
                if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                    this.drawer.closeDrawer(GravityCompat.START);
                }
                this.mSearchView.setVisibility(4);
                this.mTitleToolbarTxt.setText("Settings");
                this.mNearbyImage.setVisibility(4);
                setBackgroundSettings(this.linearLayoutHome, this.linearLayoutPartners, this.linearLayoutActivities, this.linearLayoutHelp, this.linearLayoutSettings, this.linearLayoutSections, this.linearLayoutGetYourVoucher);
                Utilities.back = false;
                break;
            case R.id.textView_open_site_from_navigation /* 2131296675 */:
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(EndPoints.MOKAFAT_URL));
                startActivity(intent);
                break;
        }
    }

    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        } else {
            turnGPSOn();
        }
    }

    public void turnGPSOn() {
        LocationManager service = (LocationManager) getSystemService(FirebaseAnalytics.Param.LOCATION);
        boolean enabled = service.isProviderEnabled("gps");
        if (!enabled) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("GPS Permission").setCancelable(false).setPositiveButton("Allow", new DialogInterface.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.ProfileActivity.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int id) {
                    Intent callGPSSettingIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                    ProfileActivity.this.startActivity(callGPSSettingIntent);
                }
            });
            alertDialogBuilder.setNegativeButton("Deny", new DialogInterface.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.ProfileActivity.7
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();
            return;
        }
        startActivity(new Intent(this, (Class<?>) MapsActivity.class));
        Utilities.back = false;
    }

    private void addEditProfileFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new EditProfileFragment());
        ft.addToBackStack("edit_profile");
        ft.commit();
    }

    private void addSettingFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new SettingsFragment());
        ft.addToBackStack("settings");
        ft.commit();
    }

    private void addHelpFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new HelpMainFragment());
        ft.commit();
    }

    private void addSectionsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new SectionsFragment());
        ft.addToBackStack(EndPoints.GET_SECTIONS);
        ft.commit();
    }

    private void addActivitiesFragment() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        }
        setBackgroundActivities(this.linearLayoutHome, this.linearLayoutPartners, this.linearLayoutActivities, this.linearLayoutHelp, this.linearLayoutSettings, this.linearLayoutSections, this.linearLayoutGetYourVoucher);
        this.mSearchView.setVisibility(4);
        this.mNearbyImage.setVisibility(0);
        this.mTitleToolbarTxt.setText("Activities");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new ActivitiesFragment());
        ft.addToBackStack("activities");
        ft.commit();
        Utilities.Notification = 0;
    }

    private void addGetYourVoucherFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new GetYourVoucherFragment());
        ft.addToBackStack("get_your_voucher");
        ft.commit();
    }

    private void addPartnersContainerFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new AllPartnersFragment());
        ft.addToBackStack("partners");
        ft.commit();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                startActivity(new Intent(this, (Class<?>) MapsActivity.class));
                Utilities.back = false;
            } else {
                turnGPSOn();
            }
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        if (this.editAccountResponse.getData() != null) {
            GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.editAccountResponse.getData().getMain_image()).into(this.mProfileImage);
            GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.editAccountResponse.getData().getQr_image()).into(this.mQrCodeImage);
            this.mNameTxt.setText(this.editAccountResponse.getData().getName());
            this.mMobileNumberTxt.setText(this.editAccountResponse.getData().getMobile());
        }
    }

    @Override // android.support.v4.app.FragmentActivity
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (this.editAccountResponse.getData() != null) {
            GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.editAccountResponse.getData().getMain_image()).into(this.mProfileImage);
            GlideApp.with((FragmentActivity) this).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.editAccountResponse.getData().getQr_image()).into(this.mQrCodeImage);
            this.mNameTxt.setText(this.editAccountResponse.getData().getName());
            this.mMobileNumberTxt.setText(this.editAccountResponse.getData().getMobile());
        }
    }

    @Override // android.support.v4.widget.DrawerLayout.DrawerListener
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
    }

    @Override // android.support.v4.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(@NonNull View drawerView) {
    }

    @Override // android.support.v4.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(@NonNull View drawerView) {
    }

    @Override // android.support.v4.widget.DrawerLayout.DrawerListener
    public void onDrawerStateChanged(int newState) {
    }
}
