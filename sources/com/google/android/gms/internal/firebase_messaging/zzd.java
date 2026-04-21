package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
final class zzd extends WeakReference<Throwable> {
    private final int zzf;

    public zzd(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzf = System.identityHashCode(th);
    }

    public final int hashCode() {
        return this.zzf;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzd zzdVar = (zzd) obj;
        if (this.zzf != zzdVar.zzf || get() != zzdVar.get()) {
            return false;
        }
        return true;
    }
}
