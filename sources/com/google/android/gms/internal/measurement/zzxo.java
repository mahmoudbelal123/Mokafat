package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes.dex */
final class zzxo<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzccg;
    private final /* synthetic */ zzxm zzcch;

    private zzxo(zzxm zzxmVar) {
        this.zzcch = zzxmVar;
        this.pos = this.zzcch.zzccb.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return (this.pos > 0 && this.pos <= this.zzcch.zzccb.size()) || zzyb().hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzyb() {
        if (this.zzccg == null) {
            this.zzccg = this.zzcch.zzcce.entrySet().iterator();
        }
        return this.zzccg;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (zzyb().hasNext()) {
            return zzyb().next();
        }
        List list = this.zzcch.zzccb;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) list.get(i);
    }

    /* synthetic */ zzxo(zzxm zzxmVar, zzxn zzxnVar) {
        this(zzxmVar);
    }
}
