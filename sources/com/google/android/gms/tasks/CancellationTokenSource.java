package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes.dex */
public class CancellationTokenSource {
    private final zza zzafj = new zza();

    public void cancel() {
        this.zzafj.cancel();
    }

    public CancellationToken getToken() {
        return this.zzafj;
    }
}
