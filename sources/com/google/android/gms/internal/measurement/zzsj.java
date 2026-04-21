package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
final class zzsj extends ContentObserver {
    private final /* synthetic */ zzsi zzbqx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzsj(zzsi zzsiVar, Handler handler) {
        super(null);
        this.zzbqx = zzsiVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zzbqx.zzta();
        this.zzbqx.zztc();
    }
}
