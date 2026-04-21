package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzde implements Runnable {
    private final /* synthetic */ String zzaeh;
    private final /* synthetic */ String zzaeo;
    private final /* synthetic */ boolean zzaev;
    private final /* synthetic */ String zzaqq;
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzde(zzcs zzcsVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.zzarc = zzcsVar;
        this.zzarb = atomicReference;
        this.zzaqq = str;
        this.zzaeh = str2;
        this.zzaeo = str3;
        this.zzaev = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarc.zzadj.zzgg().zza(this.zzarb, this.zzaqq, this.zzaeh, this.zzaeo, this.zzaev);
    }
}
