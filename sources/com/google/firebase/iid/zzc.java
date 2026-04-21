package com.google.firebase.iid;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
final class zzc implements Runnable {
    private final /* synthetic */ Intent zzl;
    private final /* synthetic */ Intent zzm;
    private final /* synthetic */ zzb zzn;

    zzc(zzb zzbVar, Intent intent, Intent intent2) {
        this.zzn = zzbVar;
        this.zzl = intent;
        this.zzm = intent2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzn.zzd(this.zzl);
        this.zzn.zza(this.zzm);
    }
}
