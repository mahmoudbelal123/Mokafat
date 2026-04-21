package com.google.android.gms.common.internal;

import android.support.v4.util.LruCache;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes.dex */
final class zze<K, V> extends LruCache<K, V> {
    private final /* synthetic */ ExpirableLruCache zzss;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zze(ExpirableLruCache expirableLruCache, int i) {
        super(i);
        this.zzss = expirableLruCache;
    }

    @Override // android.support.v4.util.LruCache
    protected final V create(K k) {
        return (V) this.zzss.create(k);
    }

    @Override // android.support.v4.util.LruCache
    protected final void entryRemoved(boolean z, K k, V v, V v2) {
        this.zzss.entryRemoved(z, k, v, v2);
        synchronized (this.zzss.mLock) {
            if (v2 == null) {
                try {
                    if (this.zzss.zzct()) {
                        this.zzss.zzsq.remove(k);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (v2 == null && this.zzss.zzcu()) {
                this.zzss.zzsr.remove(k);
            }
        }
    }

    @Override // android.support.v4.util.LruCache
    protected final int sizeOf(K k, V v) {
        return this.zzss.sizeOf(k, v);
    }
}
