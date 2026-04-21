package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class zzej implements Runnable {
    private final /* synthetic */ zzef zzasp;

    zzej(zzef zzefVar) {
        this.zzasp = zzefVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdr zzdrVar = this.zzasp.zzasg;
        Context context = this.zzasp.zzasg.getContext();
        this.zzasp.zzasg.zzgr();
        zzdrVar.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
