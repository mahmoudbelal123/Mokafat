package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzbm implements Runnable {
    private final /* synthetic */ zzbl zzkm;

    zzbm(zzbl zzblVar) {
        this.zzkm = zzblVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzkm.zzkk.zzka.disconnect();
    }
}
