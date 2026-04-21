package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class zzwb extends zztz<String> implements zzwc, RandomAccess {
    private static final zzwb zzcag;
    private static final zzwc zzcah;
    private final List<Object> zzcai;

    public zzwb() {
        this(10);
    }

    public zzwb(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzwb(ArrayList<Object> arrayList) {
        this.zzcai = arrayList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzcai.size();
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zztx();
        if (collection instanceof zzwc) {
            collection = ((zzwc) collection).zzwv();
        }
        boolean zAddAll = this.zzcai.addAll(i, collection);
        this.modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zztx();
        this.zzcai.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzwc
    public final void zzc(zzud zzudVar) {
        zztx();
        this.zzcai.add(zzudVar);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzwc
    public final Object getRaw(int i) {
        return this.zzcai.get(i);
    }

    private static String zzw(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzud) {
            return ((zzud) obj).zzua();
        }
        return zzvo.zzm((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzwc
    public final List<?> zzwv() {
        return Collections.unmodifiableList(this.zzcai);
    }

    @Override // com.google.android.gms.internal.measurement.zzwc
    public final zzwc zzww() {
        if (zztw()) {
            return new zzye(this);
        }
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        zztx();
        return zzw(this.zzcai.set(i, (String) obj));
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zztx();
        Object objRemove = this.zzcai.remove(i);
        this.modCount++;
        return zzw(objRemove);
    }

    @Override // com.google.android.gms.internal.measurement.zztz, com.google.android.gms.internal.measurement.zzvs
    public final /* bridge */ /* synthetic */ boolean zztw() {
        return super.zztw();
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zztx();
        this.zzcai.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.measurement.zztz, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzvs
    public final /* synthetic */ zzvs zzak(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzcai);
        return new zzwb((ArrayList<Object>) arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzcai.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzud) {
            zzud zzudVar = (zzud) obj;
            String strZzua = zzudVar.zzua();
            if (zzudVar.zzub()) {
                this.zzcai.set(i, strZzua);
            }
            return strZzua;
        }
        byte[] bArr = (byte[]) obj;
        String strZzm = zzvo.zzm(bArr);
        if (zzvo.zzl(bArr)) {
            this.zzcai.set(i, strZzm);
        }
        return strZzm;
    }

    static {
        zzwb zzwbVar = new zzwb();
        zzcag = zzwbVar;
        zzwbVar.zzsm();
        zzcah = zzcag;
    }
}
