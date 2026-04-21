package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
final class zzue implements zzuj {
    private final int limit;
    private int position = 0;
    private final /* synthetic */ zzud zzbuc;

    zzue(zzud zzudVar) {
        this.zzbuc = zzudVar;
        this.limit = this.zzbuc.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override // com.google.android.gms.internal.measurement.zzuj
    public final byte nextByte() {
        try {
            zzud zzudVar = this.zzbuc;
            int i = this.position;
            this.position = i + 1;
            return zzudVar.zzal(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Byte next() {
        return Byte.valueOf(nextByte());
    }
}
