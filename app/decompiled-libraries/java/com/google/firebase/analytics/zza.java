package com.google.firebase.analytics;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
final class zza implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zzbse;

    zza(FirebaseAnalytics firebaseAnalytics) {
        this.zzbse = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        String strZzfx = this.zzbse.zzfx();
        if (strZzfx != null) {
            return strZzfx;
        }
        String strZzaj = this.zzbse.zzadj.zzge().zzaj(120000L);
        if (strZzaj == null) {
            throw new TimeoutException();
        }
        this.zzbse.zzcm(strZzaj);
        return strZzaj;
    }
}
