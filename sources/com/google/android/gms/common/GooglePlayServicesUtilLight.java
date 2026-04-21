package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.internal.MetadataValueReader;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class GooglePlayServicesUtilLight {
    public static final String FEATURE_SIDEWINDER = "cn.google";
    public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";

    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final String GOOGLE_PLAY_STORE_GAMES_URI_STRING = "http://play.google.com/store/apps/category/GAME";
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static final String GOOGLE_PLAY_STORE_URI_STRING = "http://play.google.com/store/apps/";
    public static final boolean HONOR_DEBUG_CERTIFICATES = false;
    public static final String KEY_METADATA_GOOGLE_PLAY_SERVICES_VERSION = "com.google.android.gms.version";
    public static final int MAX_ATTEMPTS_NO_SUCH_ALGORITHM = 2;

    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;

    @VisibleForTesting
    public static boolean sIsTestMode = false;

    @VisibleForTesting
    public static boolean sTestIsUserBuild = false;
    private static boolean zzbr = false;

    @VisibleForTesting
    private static boolean zzbs = false;

    @VisibleForTesting
    static final AtomicBoolean zzbt = new AtomicBoolean();
    private static final AtomicBoolean zzbu = new AtomicBoolean();

    GooglePlayServicesUtilLight() {
    }

    @Deprecated
    public static void cancelAvailabilityErrorNotifications(Context context) {
        if (zzbt.getAndSet(true)) {
            return;
        }
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(10436);
            }
        } catch (SecurityException e) {
        }
    }

    public static void enableUsingApkIndependentContext() {
        zzbu.set(true);
    }

    @Deprecated
    public static void ensurePlayServicesAvailable(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        ensurePlayServicesAvailable(context, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @Deprecated
    public static void ensurePlayServicesAvailable(Context context, int i) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int iIsGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, i);
        if (iIsGooglePlayServicesAvailable != 0) {
            Intent errorResolutionIntent = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context, iIsGooglePlayServicesAvailable, "e");
            StringBuilder sb = new StringBuilder(57);
            sb.append("GooglePlayServices not available due to error ");
            sb.append(iIsGooglePlayServicesAvailable);
            Log.e("GooglePlayServicesUtil", sb.toString());
            if (errorResolutionIntent != null) {
                throw new GooglePlayServicesRepairableException(iIsGooglePlayServicesAvailable, "Google Play Services not available", errorResolutionIntent);
            }
            throw new GooglePlayServicesNotAvailableException(iIsGooglePlayServicesAvailable);
        }
    }

    @Deprecated
    public static int getApkVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @Deprecated
    public static int getClientVersion(Context context) {
        Preconditions.checkState(true);
        return ClientLibraryUtils.getClientVersion(context, context.getPackageName());
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context, i, i2);
    }

    @VisibleForTesting
    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.zza(i);
    }

    @Deprecated
    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int i) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, i, null);
    }

    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(Context context) {
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build());
            try {
                try {
                    return new Scanner(inputStreamOpenInputStream).useDelimiter("\\A").next();
                } catch (NoSuchElementException e) {
                    if (inputStreamOpenInputStream != null) {
                        inputStreamOpenInputStream.close();
                    }
                    return null;
                }
            } finally {
                if (inputStreamOpenInputStream != null) {
                    inputStreamOpenInputStream.close();
                }
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static boolean honorsDebugCertificates(Context context) {
        return isTestKeysBuild(context) || !isUserBuildDevice();
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        return isGooglePlayServicesAvailable(context, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context, int i) {
        try {
            context.getResources().getString(R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName()) && !zzbu.get()) {
            int googlePlayServicesVersion = MetadataValueReader.getGooglePlayServicesVersion(context);
            if (googlePlayServicesVersion == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
            if (googlePlayServicesVersion != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                int i2 = GOOGLE_PLAY_SERVICES_VERSION_CODE;
                StringBuilder sb = new StringBuilder(320);
                sb.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ");
                sb.append(i2);
                sb.append(" but found ");
                sb.append(googlePlayServicesVersion);
                sb.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                throw new IllegalStateException(sb.toString());
            }
        }
        return zza(context, (DeviceProperties.isWearableWithoutPlayStore(context) || DeviceProperties.isIoT(context)) ? false : true, i);
    }

    @Deprecated
    public static boolean isGooglePlayServicesUid(Context context, int i) {
        return UidVerifier.isGooglePlayServicesUid(context, i);
    }

    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return isUninstalledAppPossiblyUpdating(context, "com.google.android.gms");
        }
        return false;
    }

    @Deprecated
    public static boolean isPlayStorePossiblyUpdating(Context context, int i) {
        if (i == 9) {
            return isUninstalledAppPossiblyUpdating(context, "com.android.vending");
        }
        return false;
    }

    @TargetApi(18)
    public static boolean isRestrictedUserProfile(Context context) {
        Bundle applicationRestrictions;
        return PlatformVersion.isAtLeastJellyBeanMR2() && (applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName())) != null && "true".equals(applicationRestrictions.getString("restricted_profile"));
    }

    @VisibleForTesting
    @Deprecated
    public static boolean isSidewinderDevice(Context context) {
        return DeviceProperties.isSidewinder(context);
    }

    public static boolean isTestKeysBuild(Context context) {
        if (!zzbs) {
            try {
                try {
                    PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.google.android.gms", 64);
                    GoogleSignatureVerifier googleSignatureVerifier = GoogleSignatureVerifier.getInstance(context);
                    if (packageInfo == null || googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo, false) || !googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo, true)) {
                        zzbr = false;
                    } else {
                        zzbr = true;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
                }
            } finally {
                zzbs = true;
            }
        }
        return zzbr;
    }

    @TargetApi(21)
    static boolean isUninstalledAppPossiblyUpdating(Context context, String str) {
        boolean zEquals = str.equals("com.google.android.gms");
        if (PlatformVersion.isAtLeastLollipop()) {
            try {
                Iterator<PackageInstaller.SessionInfo> it = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
                while (it.hasNext()) {
                    if (str.equals(it.next().getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            return zEquals ? applicationInfo.enabled : applicationInfo.enabled && !isRestrictedUserProfile(context);
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    @Deprecated
    public static boolean isUserBuildDevice() {
        return DeviceProperties.isUserBuild();
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        if (i == 9) {
            return true;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    @TargetApi(19)
    @Deprecated
    public static boolean uidHasPackageName(Context context, int i, String str) {
        return UidVerifier.uidHasPackageName(context, i, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    @com.google.android.gms.common.util.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int zza(android.content.Context r7, boolean r8, int r9) {
        /*
            r0 = 0
            r1 = 1
            if (r9 < 0) goto L6
            r2 = r1
            goto L7
        L6:
            r2 = r0
        L7:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)
            android.content.pm.PackageManager r2 = r7.getPackageManager()
            r3 = 0
            r4 = 9
            if (r8 == 0) goto L25
            java.lang.String r3 = "com.android.vending"
            r5 = 8256(0x2040, float:1.1569E-41)
            android.content.pm.PackageInfo r3 = r2.getPackageInfo(r3, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1c
            goto L25
        L1c:
            r7 = move-exception
            java.lang.String r7 = "GooglePlayServicesUtil"
            java.lang.String r8 = "Google Play Store is missing."
        L21:
            android.util.Log.w(r7, r8)
            return r4
        L25:
            java.lang.String r5 = "com.google.android.gms"
            r6 = 64
            android.content.pm.PackageInfo r5 = r2.getPackageInfo(r5, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> La2
            com.google.android.gms.common.GoogleSignatureVerifier r7 = com.google.android.gms.common.GoogleSignatureVerifier.getInstance(r7)
            boolean r6 = r7.isGooglePublicSignedPackage(r5, r1)
            if (r6 != 0) goto L3c
            java.lang.String r7 = "GooglePlayServicesUtil"
            java.lang.String r8 = "Google Play services signature invalid."
            goto L21
        L3c:
            if (r8 == 0) goto L57
            boolean r7 = r7.isGooglePublicSignedPackage(r3, r1)
            if (r7 == 0) goto L52
            android.content.pm.Signature[] r7 = r3.signatures
            r7 = r7[r0]
            android.content.pm.Signature[] r8 = r5.signatures
            r8 = r8[r0]
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L57
        L52:
            java.lang.String r7 = "GooglePlayServicesUtil"
            java.lang.String r8 = "Google Play Store signature invalid."
            goto L21
        L57:
            int r7 = r5.versionCode
            int r7 = com.google.android.gms.common.util.GmsVersionParser.parseBuildVersion(r7)
            int r8 = com.google.android.gms.common.util.GmsVersionParser.parseBuildVersion(r9)
            if (r7 >= r8) goto L87
            java.lang.String r7 = "GooglePlayServicesUtil"
            int r8 = r5.versionCode
            r0 = 77
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Google Play services out of date.  Requires "
            r1.append(r0)
            r1.append(r9)
            java.lang.String r9 = " but found "
            r1.append(r9)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            android.util.Log.w(r7, r8)
            r7 = 2
            return r7
        L87:
            android.content.pm.ApplicationInfo r7 = r5.applicationInfo
            if (r7 != 0) goto L9b
            java.lang.String r7 = "com.google.android.gms"
            android.content.pm.ApplicationInfo r7 = r2.getApplicationInfo(r7, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L92
            goto L9b
        L92:
            r7 = move-exception
            java.lang.String r8 = "GooglePlayServicesUtil"
            java.lang.String r9 = "Google Play services missing when getting application info."
            android.util.Log.wtf(r8, r9, r7)
            return r1
        L9b:
            boolean r7 = r7.enabled
            if (r7 != 0) goto La1
            r7 = 3
            return r7
        La1:
            return r0
        La2:
            r7 = move-exception
            java.lang.String r7 = "GooglePlayServicesUtil"
            java.lang.String r8 = "Google Play services is missing."
            android.util.Log.w(r7, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GooglePlayServicesUtilLight.zza(android.content.Context, boolean, int):int");
    }
}
