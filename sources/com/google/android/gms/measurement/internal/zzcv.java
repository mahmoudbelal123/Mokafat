package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzcv implements Runnable {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzaeh;
    private final /* synthetic */ zzcs zzarc;
    private final /* synthetic */ long zzard;
    private final /* synthetic */ Object zzarh;

    zzcv(zzcs zzcsVar, String str, String str2, Object obj, long j) {
        this.zzarc = zzcsVar;
        this.zzaeh = str;
        this.val$name = str2;
        this.zzarh = obj;
        this.zzard = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarc.zza(this.zzaeh, this.val$name, this.zzarh, this.zzard);
    }
}
