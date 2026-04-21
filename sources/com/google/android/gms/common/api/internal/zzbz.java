package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes.dex */
final class zzbz implements Runnable {
    private final /* synthetic */ zzby zzlx;

    zzbz(zzby zzbyVar) {
        this.zzlx = zzbyVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzlx.zzlw.zzg(new ConnectionResult(4));
    }
}
