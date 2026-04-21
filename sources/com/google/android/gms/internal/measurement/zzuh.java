package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
final class zzuh extends zzum {
    private final int zzbud;
    private final int zzbue;

    zzuh(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzbud = i;
        this.zzbue = i2;
    }

    @Override // com.google.android.gms.internal.measurement.zzum, com.google.android.gms.internal.measurement.zzud
    public final byte zzal(int i) {
        int size = size();
        if (((size - (i + 1)) | i) < 0) {
            if (i < 0) {
                StringBuilder sb = new StringBuilder(22);
                sb.append("Index < 0: ");
                sb.append(i);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Index > length: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(size);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
        return this.zzbug[this.zzbud + i];
    }

    @Override // com.google.android.gms.internal.measurement.zzum, com.google.android.gms.internal.measurement.zzud
    public final int size() {
        return this.zzbue;
    }

    @Override // com.google.android.gms.internal.measurement.zzum
    protected final int zzud() {
        return this.zzbud;
    }
}
