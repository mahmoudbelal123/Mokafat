package com.google.android.gms.internal.measurement;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
final class zzyf implements ListIterator<String> {
    private ListIterator<String> zzccr;
    private final /* synthetic */ int zzccs;
    private final /* synthetic */ zzye zzcct;

    zzyf(zzye zzyeVar, int i) {
        this.zzcct = zzyeVar;
        this.zzccs = i;
        this.zzccr = this.zzcct.zzccq.listIterator(this.zzccs);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.zzccr.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzccr.hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzccr.nextIndex();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzccr.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.zzccr.previous();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.zzccr.next();
    }
}
