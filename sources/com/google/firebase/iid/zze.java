package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
final class zze implements Runnable {
    private final /* synthetic */ Intent zzl;
    private final /* synthetic */ zzd zzr;

    zze(zzd zzdVar, Intent intent) {
        this.zzr = zzdVar;
        this.zzl = intent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String action = this.zzl.getAction();
        StringBuilder sb = new StringBuilder(61 + String.valueOf(action).length());
        sb.append("Service took too long to process intent: ");
        sb.append(action);
        sb.append(" App may get closed.");
        Log.w("EnhancedIntentService", sb.toString());
        this.zzr.finish();
    }
}
