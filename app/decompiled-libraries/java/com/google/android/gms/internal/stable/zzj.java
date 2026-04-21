package com.google.android.gms.internal.stable;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
final class zzj extends ContentObserver {
    zzj(Handler handler) {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        zzi.zzagy.set(true);
    }
}
