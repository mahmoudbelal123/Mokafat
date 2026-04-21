package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes.dex */
final class zzxu<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzccg;
    private final /* synthetic */ zzxm zzcch;
    private boolean zzccl;

    private zzxu(zzxm zzxmVar) {
        this.zzcch = zzxmVar;
        this.pos = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.pos + 1 < this.zzcch.zzccb.size() || (!this.zzcch.zzccc.isEmpty() && zzyb().hasNext());
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzccl) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzccl = false;
        this.zzcch.zzxz();
        if (this.pos < this.zzcch.zzccb.size()) {
            zzxm zzxmVar = this.zzcch;
            int i = this.pos;
            this.pos = i - 1;
            zzxmVar.zzbv(i);
            return;
        }
        zzyb().remove();
    }

    private final Iterator<Map.Entry<K, V>> zzyb() {
        if (this.zzccg == null) {
            this.zzccg = this.zzcch.zzccc.entrySet().iterator();
        }
        return this.zzccg;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        this.zzccl = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.zzcch.zzccb.size()) {
            return zzyb().next();
        }
        return (Map.Entry) this.zzcch.zzccb.get(this.pos);
    }

    /* synthetic */ zzxu(zzxm zzxmVar, zzxn zzxnVar) {
        this(zzxmVar);
    }
}
