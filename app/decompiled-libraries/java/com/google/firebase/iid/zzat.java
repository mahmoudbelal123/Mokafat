package com.google.firebase.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
final class zzat extends Handler {
    private final /* synthetic */ zzas zzct;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzat(zzas zzasVar, Looper looper) {
        super(looper);
        this.zzct = zzasVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.zzct.zzb(message);
    }
}
