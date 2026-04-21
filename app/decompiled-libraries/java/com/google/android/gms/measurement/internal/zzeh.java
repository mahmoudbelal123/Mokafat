package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* JADX INFO: loaded from: classes.dex */
final class zzeh implements Runnable {
    private final /* synthetic */ ComponentName val$name;
    private final /* synthetic */ zzef zzasp;

    zzeh(zzef zzefVar, ComponentName componentName) {
        this.zzasp = zzefVar;
        this.val$name = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzasp.zzasg.onServiceDisconnected(this.val$name);
    }
}
