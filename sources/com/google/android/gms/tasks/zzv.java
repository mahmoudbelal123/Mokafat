package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class zzv implements Runnable {
    private final /* synthetic */ Callable val$callable;
    private final /* synthetic */ zzu zzagj;

    zzv(zzu zzuVar, Callable callable) {
        this.zzagj = zzuVar;
        this.val$callable = callable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzagj.setResult(this.val$callable.call());
        } catch (Exception e) {
            this.zzagj.setException(e);
        }
    }
}
