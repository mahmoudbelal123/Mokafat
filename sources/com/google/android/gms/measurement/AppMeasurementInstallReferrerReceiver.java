package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.measurement.internal.zzbj;
import com.google.android.gms.measurement.internal.zzbm;

/* JADX INFO: loaded from: classes.dex */
public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver implements zzbm {
    private zzbj zzadq;

    @Override // android.content.BroadcastReceiver
    @MainThread
    public final void onReceive(Context context, Intent intent) {
        if (this.zzadq == null) {
            this.zzadq = new zzbj(this);
        }
        this.zzadq.onReceive(context, intent);
    }

    @Override // com.google.android.gms.measurement.internal.zzbm
    public final void doStartService(Context context, Intent intent) {
    }

    @Override // com.google.android.gms.measurement.internal.zzbm
    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }
}
