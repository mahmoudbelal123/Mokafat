package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzbc {
    private boolean value;
    private final boolean zzanw;
    private boolean zzanx;
    private final /* synthetic */ zzba zzany;
    private final String zzoj;

    public zzbc(zzba zzbaVar, String str, boolean z) {
        this.zzany = zzbaVar;
        Preconditions.checkNotEmpty(str);
        this.zzoj = str;
        this.zzanw = true;
    }

    @WorkerThread
    public final boolean get() {
        if (!this.zzanx) {
            this.zzanx = true;
            this.value = this.zzany.zzjr().getBoolean(this.zzoj, this.zzanw);
        }
        return this.value;
    }

    @WorkerThread
    public final void set(boolean z) {
        SharedPreferences.Editor editorEdit = this.zzany.zzjr().edit();
        editorEdit.putBoolean(this.zzoj, z);
        editorEdit.apply();
        this.value = z;
    }
}
