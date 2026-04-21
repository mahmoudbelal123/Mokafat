package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
final class zzay extends BroadcastReceiver {

    @Nullable
    private zzax zzdh;

    public zzay(zzax zzaxVar) {
        this.zzdh = zzaxVar;
    }

    public final void zzao() {
        if (FirebaseInstanceId.zzk()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.zzdh.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (this.zzdh == null || !this.zzdh.zzan()) {
            return;
        }
        if (FirebaseInstanceId.zzk()) {
            Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
        }
        FirebaseInstanceId.zza(this.zzdh, 0L);
        this.zzdh.getContext().unregisterReceiver(this);
        this.zzdh = null;
    }
}
