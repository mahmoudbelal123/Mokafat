package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.FirebaseApp;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public final class zzam {

    @GuardedBy("this")
    private String zzcf;

    @GuardedBy("this")
    private String zzcg;

    @GuardedBy("this")
    private int zzch;

    @GuardedBy("this")
    private int zzci = 0;
    private final Context zzv;

    public zzam(Context context) {
        this.zzv = context;
    }

    public final synchronized int zzab() {
        if (this.zzci != 0) {
            return this.zzci;
        }
        PackageManager packageManager = this.zzv.getPackageManager();
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1) {
            Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
            return 0;
        }
        if (!PlatformVersion.isAtLeastO()) {
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (listQueryIntentServices != null && listQueryIntentServices.size() > 0) {
                this.zzci = 1;
                return this.zzci;
            }
        }
        Intent intent2 = new Intent("com.google.iid.TOKEN_REQUEST");
        intent2.setPackage("com.google.android.gms");
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 0);
        if (listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0) {
            this.zzci = 2;
            return this.zzci;
        }
        Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
        if (PlatformVersion.isAtLeastO()) {
            this.zzci = 2;
        } else {
            this.zzci = 1;
        }
        return this.zzci;
    }

    public static String zza(FirebaseApp firebaseApp) {
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] strArrSplit = applicationId.split(":");
        if (strArrSplit.length < 2) {
            return null;
        }
        String str = strArrSplit[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    public static String zza(KeyPair keyPair) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1).digest(keyPair.getPublic().getEncoded());
            bArrDigest[0] = (byte) (112 + (bArrDigest[0] & 15));
            return Base64.encodeToString(bArrDigest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    public final synchronized String zzac() {
        if (this.zzcf == null) {
            zzaf();
        }
        return this.zzcf;
    }

    public final synchronized String zzad() {
        if (this.zzcg == null) {
            zzaf();
        }
        return this.zzcg;
    }

    public final synchronized int zzae() {
        PackageInfo packageInfoZze;
        if (this.zzch == 0 && (packageInfoZze = zze("com.google.android.gms")) != null) {
            this.zzch = packageInfoZze.versionCode;
        }
        return this.zzch;
    }

    private final synchronized void zzaf() {
        PackageInfo packageInfoZze = zze(this.zzv.getPackageName());
        if (packageInfoZze != null) {
            this.zzcf = Integer.toString(packageInfoZze.versionCode);
            this.zzcg = packageInfoZze.versionName;
        }
    }

    private final PackageInfo zze(String str) {
        try {
            return this.zzv.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(23 + String.valueOf(strValueOf).length());
            sb.append("Failed to find package ");
            sb.append(strValueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }
}
