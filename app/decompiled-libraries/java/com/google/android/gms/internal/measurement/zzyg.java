package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class zzyg implements Iterator<String> {
    private final /* synthetic */ zzye zzcct;
    private Iterator<String> zzccu;

    zzyg(zzye zzyeVar) {
        this.zzcct = zzyeVar;
        this.zzccu = this.zzcct.zzccq.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzccu.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzccu.next();
    }
}
