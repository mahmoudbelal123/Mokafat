package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.AppMeasurement;

/* JADX INFO: loaded from: classes.dex */
final class zzdb implements Runnable {
    private final /* synthetic */ zzcs zzarc;
    private final /* synthetic */ AppMeasurement.ConditionalUserProperty zzarj;

    zzdb(zzcs zzcsVar, AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        this.zzarc = zzcsVar;
        this.zzarj = conditionalUserProperty;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarc.zzc(this.zzarj);
    }
}
