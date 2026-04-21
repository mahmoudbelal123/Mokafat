package com.google.firebase.iid;

import android.content.Intent;
import android.support.annotation.WorkerThread;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FirebaseInstanceIdService extends zzb {
    @Override // com.google.firebase.iid.zzb
    protected final Intent zzb(Intent intent) {
        return zzau.zzah().zzcx.poll();
    }

    @Override // com.google.firebase.iid.zzb
    public final void zzd(Intent intent) {
        if ("com.google.firebase.iid.TOKEN_REFRESH".equals(intent.getAction())) {
            onTokenRefresh();
            return;
        }
        String stringExtra = intent.getStringExtra("CMD");
        if (stringExtra != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(intent.getExtras());
                StringBuilder sb = new StringBuilder(21 + String.valueOf(stringExtra).length() + String.valueOf(strValueOf).length());
                sb.append("Received command: ");
                sb.append(stringExtra);
                sb.append(" - ");
                sb.append(strValueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
                FirebaseInstanceId.getInstance().zzl();
            } else if ("SYNC".equals(stringExtra)) {
                FirebaseInstanceId.getInstance().zzp();
            }
        }
    }

    @WorkerThread
    @Deprecated
    public void onTokenRefresh() {
    }
}
