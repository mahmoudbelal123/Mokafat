package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class zzab implements Iterator<String> {
    private Iterator<String> zzain;
    private final /* synthetic */ zzaa zzaio;

    zzab(zzaa zzaaVar) {
        this.zzaio = zzaaVar;
        this.zzain = this.zzaio.zzaim.keySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzain.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzain.next();
    }
}
