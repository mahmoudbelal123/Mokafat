package com.google.android.gms.internal.measurement;

import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes.dex */
final class zzxt<K, V> implements Comparable<zzxt>, Map.Entry<K, V> {
    private V value;
    private final /* synthetic */ zzxm zzcch;

    /* JADX INFO: Incorrect field signature: TK; */
    private final Comparable zzcck;

    zzxt(zzxm zzxmVar, Map.Entry<K, V> entry) {
        this(zzxmVar, (Comparable) entry.getKey(), entry.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    zzxt(zzxm zzxmVar, K k, V v) {
        this.zzcch = zzxmVar;
        this.zzcck = k;
        this.value = v;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        this.zzcch.zzxz();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzcck, entry.getKey()) && equals(this.value, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        int iHashCode;
        if (this.zzcck != null) {
            iHashCode = this.zzcck.hashCode();
        } else {
            iHashCode = 0;
        }
        return iHashCode ^ (this.value != null ? this.value.hashCode() : 0);
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzcck);
        String strValueOf2 = String.valueOf(this.value);
        StringBuilder sb = new StringBuilder(1 + String.valueOf(strValueOf).length() + String.valueOf(strValueOf2).length());
        sb.append(strValueOf);
        sb.append("=");
        sb.append(strValueOf2);
        return sb.toString();
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzcck;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzxt zzxtVar) {
        return ((Comparable) getKey()).compareTo((Comparable) zzxtVar.getKey());
    }
}
