package com.google.firebase.iid;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
final class zzg implements Runnable {
    private final /* synthetic */ zzd zzt;
    private final /* synthetic */ zzf zzu;

    zzg(zzf zzfVar, zzd zzdVar) {
        this.zzu = zzfVar;
        this.zzt = zzdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.zzu.zzs.zzd(this.zzt.intent);
        this.zzt.finish();
    }
}
