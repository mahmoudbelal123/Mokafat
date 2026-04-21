package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
final class zzuk {
    private final byte[] buffer;
    private final zzut zzbuf;

    private zzuk(int i) {
        this.buffer = new byte[i];
        this.zzbuf = zzut.zzj(this.buffer);
    }

    public final zzud zzue() {
        if (this.zzbuf.zzvg() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
        return new zzum(this.buffer);
    }

    public final zzut zzuf() {
        return this.zzbuf;
    }

    /* synthetic */ zzuk(int i, zzue zzueVar) {
        this(i);
    }
}
