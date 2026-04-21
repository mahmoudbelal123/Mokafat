package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class zzvz<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzcac;

    public zzvz(Iterator<Map.Entry<K, Object>> it) {
        this.zzcac = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzcac.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zzcac.remove();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        Map.Entry<K, Object> next = this.zzcac.next();
        if (next.getValue() instanceof zzvw) {
            return new zzvy(next);
        }
        return next;
    }
}
