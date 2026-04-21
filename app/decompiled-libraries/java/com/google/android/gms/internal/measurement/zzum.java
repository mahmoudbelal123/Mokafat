package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
class zzum extends zzul {
    protected final byte[] zzbug;

    zzum(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzbug = bArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzud
    public byte zzal(int i) {
        return this.zzbug[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzud
    public int size() {
        return this.zzbug.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzud
    public final zzud zzb(int i, int i2) {
        int iZzb = zzb(0, i2, size());
        if (iZzb == 0) {
            return zzud.zzbtz;
        }
        return new zzuh(this.zzbug, zzud(), iZzb);
    }

    @Override // com.google.android.gms.internal.measurement.zzud
    final void zza(zzuc zzucVar) throws IOException {
        zzucVar.zza(this.zzbug, zzud(), size());
    }

    @Override // com.google.android.gms.internal.measurement.zzud
    protected final String zza(Charset charset) {
        return new String(this.zzbug, zzud(), size(), charset);
    }

    @Override // com.google.android.gms.internal.measurement.zzud
    public final boolean zzub() {
        int iZzud = zzud();
        return zzyj.zzf(this.zzbug, iZzud, size() + iZzud);
    }

    @Override // com.google.android.gms.internal.measurement.zzud
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzud) || size() != ((zzud) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof zzum) {
            zzum zzumVar = (zzum) obj;
            int iZzuc = zzuc();
            int iZzuc2 = zzumVar.zzuc();
            if (iZzuc == 0 || iZzuc2 == 0 || iZzuc == iZzuc2) {
                return zza(zzumVar, 0, size());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzul
    final boolean zza(zzud zzudVar, int i, int i2) {
        if (i2 > zzudVar.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 > zzudVar.size()) {
            int size2 = zzudVar.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (zzudVar instanceof zzum) {
            zzum zzumVar = (zzum) zzudVar;
            byte[] bArr = this.zzbug;
            byte[] bArr2 = zzumVar.zzbug;
            int iZzud = zzud() + i2;
            int iZzud2 = zzud();
            int iZzud3 = zzumVar.zzud();
            while (iZzud2 < iZzud) {
                if (bArr[iZzud2] != bArr2[iZzud3]) {
                    return false;
                }
                iZzud2++;
                iZzud3++;
            }
            return true;
        }
        return zzudVar.zzb(0, i2).equals(zzb(0, i2));
    }

    @Override // com.google.android.gms.internal.measurement.zzud
    protected final int zza(int i, int i2, int i3) {
        return zzvo.zza(i, this.zzbug, zzud(), i3);
    }

    protected int zzud() {
        return 0;
    }
}
