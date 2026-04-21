package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class zzax implements Runnable {
    private final zzam zzak;
    private final zzaz zzan;
    private final long zzde;
    private final PowerManager.WakeLock zzdf = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private final FirebaseInstanceId zzdg;

    @VisibleForTesting
    zzax(FirebaseInstanceId firebaseInstanceId, zzam zzamVar, zzaz zzazVar, long j) {
        this.zzdg = firebaseInstanceId;
        this.zzak = zzamVar;
        this.zzan = zzazVar;
        this.zzde = j;
        this.zzdf.setReferenceCounted(false);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdf.acquire();
        try {
            this.zzdg.zza(true);
            if (!this.zzdg.zzm()) {
                this.zzdg.zza(false);
                return;
            }
            if (!zzan()) {
                new zzay(this).zzao();
                return;
            }
            if (zzal() && zzam() && this.zzan.zzc(this.zzdg)) {
                this.zzdg.zza(false);
            } else {
                this.zzdg.zza(this.zzde);
            }
        } finally {
            this.zzdf.release();
        }
    }

    @VisibleForTesting
    private final boolean zzal() {
        try {
            if (!this.zzdg.zzn()) {
                this.zzdg.zzo();
                return true;
            }
            return true;
        } catch (IOException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.e("FirebaseInstanceId", strValueOf.length() != 0 ? "Build channel failed: ".concat(strValueOf) : new String("Build channel failed: "));
            return false;
        }
    }

    @VisibleForTesting
    private final boolean zzam() {
        zzaw zzawVarZzi = this.zzdg.zzi();
        if (zzawVarZzi != null && !zzawVarZzi.zzj(this.zzak.zzac())) {
            return true;
        }
        try {
            String strZzj = this.zzdg.zzj();
            if (strZzj == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (zzawVarZzi == null || (zzawVarZzi != null && !strZzj.equals(zzawVarZzi.zzbn))) {
                Context context = getContext();
                Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                intent.putExtra("token", strZzj);
                zzau.zzc(context, intent);
                zzau.zzb(context, new Intent("com.google.firebase.iid.TOKEN_REFRESH"));
            }
            return true;
        } catch (IOException | SecurityException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.e("FirebaseInstanceId", strValueOf.length() != 0 ? "Token retrieval failed: ".concat(strValueOf) : new String("Token retrieval failed: "));
            return false;
        }
    }

    final Context getContext() {
        return this.zzdg.zzg().getApplicationContext();
    }

    final boolean zzan() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
