package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

/* JADX INFO: loaded from: classes.dex */
abstract class zzat implements Runnable {
    private final /* synthetic */ zzaj zzhv;

    private zzat(zzaj zzajVar) {
        this.zzhv = zzajVar;
    }

    /* synthetic */ zzat(zzaj zzajVar, zzak zzakVar) {
        this(zzajVar);
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public void run() {
        this.zzhv.zzga.lock();
        try {
            try {
                if (!Thread.interrupted()) {
                    zzaq();
                }
            } catch (RuntimeException e) {
                this.zzhv.zzhf.zzb(e);
            }
        } finally {
            this.zzhv.zzga.unlock();
        }
    }

    @WorkerThread
    protected abstract void zzaq();
}
