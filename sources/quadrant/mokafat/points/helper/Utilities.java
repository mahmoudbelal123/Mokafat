package quadrant.mokafat.points.helper;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.animation.AlphaAnimation;
import com.google.gson.Gson;
import java.util.Random;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.models.objects.login.loginResponse;

/* JADX INFO: loaded from: classes.dex */
public class Utilities {
    public static String LAT;
    public static String LONGITUTE;
    public static String Offers;
    public static String PARENT_ID;
    public static String SECTION_ID;
    public static String SUB_SECTION_ID;
    public static String VENDOR_ID;
    public static String Vendors;
    public static SharedPreferences mPrefs;
    public static String USER_SHARED_PREFERENCES_NAME = "user_info";
    public static String USER_INFO_SHARED_PREFERENCES_KEY = "user_info_object";
    public static String PARTNER_SHARED_PREFERENCES_NAME = "partner";
    public static String PARTNER_SHARED_PREFERENCES_KEY_image = "partner_image";
    public static String PARTNER_SHARED_PREFERENCES_KEY_title = "partner_title";
    public static String PARTNER_SHARED_PREFERENCES_KEY_Offers = "partner_offers";
    public static String PARTNER_SHARED_PREFERENCES_KEY_Vendors = "partner_vendors";
    public static boolean back = false;
    public static int UPDATE = 0;
    public static int ALLOW_NOTIFICATION = 0;
    public static int RECEIVE_NOTIFICATION = 0;
    public static int Notification = 0;
    public static int FLAG_SECTIONS_HOME = 0;
    public static int Pages = 1;
    public static AlphaAnimation clickEffect = new AlphaAnimation(2.0f, 0.8f);

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static boolean checkConnection(Context mContext) {
        if (ConnectivityReceiver.isConnected()) {
            return true;
        }
        return false;
    }

    public static void showProgressDialog(ProgressDialog progressDialog, String message, boolean show) {
        progressDialog.setMessage(message);
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    public static void savePartner(Context context, String imageUrl, String title, String offers, String vendors) {
        mPrefs = context.getSharedPreferences(PARTNER_SHARED_PREFERENCES_NAME, 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(PARTNER_SHARED_PREFERENCES_KEY_image, imageUrl);
        prefsEditor.putString(PARTNER_SHARED_PREFERENCES_KEY_title, title);
        prefsEditor.putString(PARTNER_SHARED_PREFERENCES_KEY_Offers, offers);
        prefsEditor.putString(PARTNER_SHARED_PREFERENCES_KEY_Vendors, vendors);
        prefsEditor.commit();
    }

    public static String retrievePartnerImage(Context context) {
        mPrefs = context.getSharedPreferences(PARTNER_SHARED_PREFERENCES_NAME, 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String imageUrl = mPrefs.getString(PARTNER_SHARED_PREFERENCES_KEY_image, null);
        prefsEditor.commit();
        return imageUrl;
    }

    public static String retrievePartnerOffers(Context context) {
        mPrefs = context.getSharedPreferences(PARTNER_SHARED_PREFERENCES_NAME, 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String offers = mPrefs.getString(PARTNER_SHARED_PREFERENCES_KEY_Offers, null);
        prefsEditor.commit();
        return offers;
    }

    public static String retrievePartnerVendors(Context context) {
        mPrefs = context.getSharedPreferences(PARTNER_SHARED_PREFERENCES_NAME, 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String vendors = mPrefs.getString(PARTNER_SHARED_PREFERENCES_KEY_Vendors, null);
        prefsEditor.commit();
        return vendors;
    }

    public static String retrievePartnerTitle(Context context) {
        mPrefs = context.getSharedPreferences(PARTNER_SHARED_PREFERENCES_NAME, 0);
        mPrefs.edit();
        String title = mPrefs.getString(PARTNER_SHARED_PREFERENCES_KEY_title, null);
        return title;
    }

    public static void saveUserInfoSharedPreferences(Context context, loginResponse loginResponse) {
        mPrefs = context.getSharedPreferences(USER_SHARED_PREFERENCES_NAME, 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(loginResponse);
        prefsEditor.putString(USER_INFO_SHARED_PREFERENCES_KEY, json);
        prefsEditor.commit();
    }

    public static loginResponse retrieveUserInfoSharedPreferences(Context context) {
        mPrefs = context.getSharedPreferences(USER_SHARED_PREFERENCES_NAME, 0);
        mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString(USER_INFO_SHARED_PREFERENCES_KEY, "");
        loginResponse obj = (loginResponse) gson.fromJson(json, loginResponse.class);
        return obj;
    }

    public static Dialog signOutDialog(Context mContext) {
        Dialog dialog = new Dialog(mContext);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_sign_out);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(3);
        dialog.getWindow().setLayout(-1, -2);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }
}
