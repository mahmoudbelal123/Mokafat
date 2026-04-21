package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class zzyc {
    private static final zzyc zzcco = new zzyc(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzbtu;
    private int zzbyn;
    private Object[] zzcba;
    private int[] zzccp;

    public static zzyc zzyf() {
        return zzcco;
    }

    static zzyc zzyg() {
        return new zzyc();
    }

    static zzyc zza(zzyc zzycVar, zzyc zzycVar2) {
        int i = zzycVar.count + zzycVar2.count;
        int[] iArrCopyOf = Arrays.copyOf(zzycVar.zzccp, i);
        System.arraycopy(zzycVar2.zzccp, 0, iArrCopyOf, zzycVar.count, zzycVar2.count);
        Object[] objArrCopyOf = Arrays.copyOf(zzycVar.zzcba, i);
        System.arraycopy(zzycVar2.zzcba, 0, objArrCopyOf, zzycVar.count, zzycVar2.count);
        return new zzyc(i, iArrCopyOf, objArrCopyOf, true);
    }

    private zzyc() {
        this(0, new int[8], new Object[8], true);
    }

    private zzyc(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzbyn = -1;
        this.count = i;
        this.zzccp = iArr;
        this.zzcba = objArr;
        this.zzbtu = z;
    }

    public final void zzsm() {
        this.zzbtu = false;
    }

    final void zza(zzyw zzywVar) throws IOException {
        if (zzywVar.zzvj() == zzvm.zze.zzbzf) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzywVar.zza(this.zzccp[i] >>> 3, this.zzcba[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzywVar.zza(this.zzccp[i2] >>> 3, this.zzcba[i2]);
        }
    }

    public final void zzb(zzyw zzywVar) throws IOException {
        if (this.count == 0) {
            return;
        }
        if (zzywVar.zzvj() == zzvm.zze.zzbze) {
            for (int i = 0; i < this.count; i++) {
                zzb(this.zzccp[i], this.zzcba[i], zzywVar);
            }
            return;
        }
        for (int i2 = this.count - 1; i2 >= 0; i2--) {
            zzb(this.zzccp[i2], this.zzcba[i2], zzywVar);
        }
    }

    private static void zzb(int i, Object obj, zzyw zzywVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 != 5) {
            switch (i3) {
                case 0:
                    zzywVar.zzi(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzywVar.zzc(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzywVar.zza(i2, (zzud) obj);
                    return;
                case 3:
                    if (zzywVar.zzvj() == zzvm.zze.zzbze) {
                        zzywVar.zzbk(i2);
                        ((zzyc) obj).zzb(zzywVar);
                        zzywVar.zzbl(i2);
                        return;
                    } else {
                        zzywVar.zzbl(i2);
                        ((zzyc) obj).zzb(zzywVar);
                        zzywVar.zzbk(i2);
                        return;
                    }
                default:
                    throw new RuntimeException(zzvt.zzwo());
            }
        }
        zzywVar.zzg(i2, ((Integer) obj).intValue());
    }

    public final int zzyh() {
        int i = this.zzbyn;
        if (i != -1) {
            return i;
        }
        int iZzd = 0;
        for (int i2 = 0; i2 < this.count; i2++) {
            iZzd += zzut.zzd(this.zzccp[i2] >>> 3, (zzud) this.zzcba[i2]);
        }
        this.zzbyn = iZzd;
        return iZzd;
    }

    public final int zzvu() {
        int iZzk;
        int i = this.zzbyn;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.zzccp[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 != 5) {
                switch (i6) {
                    case 0:
                        iZzk = zzut.zze(i5, ((Long) this.zzcba[i3]).longValue());
                        break;
                    case 1:
                        iZzk = zzut.zzg(i5, ((Long) this.zzcba[i3]).longValue());
                        break;
                    case 2:
                        iZzk = zzut.zzc(i5, (zzud) this.zzcba[i3]);
                        break;
                    case 3:
                        iZzk = (zzut.zzbb(i5) << 1) + ((zzyc) this.zzcba[i3]).zzvu();
                        break;
                    default:
                        throw new IllegalStateException(zzvt.zzwo());
                }
            } else {
                iZzk = zzut.zzk(i5, ((Integer) this.zzcba[i3]).intValue());
            }
            i2 += iZzk;
        }
        this.zzbyn = i2;
        return i2;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzyc)) {
            return false;
        }
        zzyc zzycVar = (zzyc) obj;
        if (this.count == zzycVar.count) {
            int[] iArr = this.zzccp;
            int[] iArr2 = zzycVar.zzccp;
            int i = this.count;
            int i2 = 0;
            while (true) {
                if (i2 < i) {
                    if (iArr[i2] == iArr2[i2]) {
                        i2++;
                    } else {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                Object[] objArr = this.zzcba;
                Object[] objArr2 = zzycVar.zzcba;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 < i3) {
                        if (objArr[i4].equals(objArr2[i4])) {
                            i4++;
                        } else {
                            z2 = false;
                            break;
                        }
                    } else {
                        z2 = true;
                        break;
                    }
                }
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = (527 + this.count) * 31;
        int[] iArr = this.zzccp;
        int i2 = this.count;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i + i3) * 31;
        Object[] objArr = this.zzcba;
        int i6 = this.count;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    final void zzb(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzww.zzb(sb, i, String.valueOf(this.zzccp[i2] >>> 3), this.zzcba[i2]);
        }
    }

    final void zzb(int i, Object obj) {
        if (!this.zzbtu) {
            throw new UnsupportedOperationException();
        }
        if (this.count == this.zzccp.length) {
            int i2 = this.count + (this.count < 4 ? 8 : this.count >> 1);
            this.zzccp = Arrays.copyOf(this.zzccp, i2);
            this.zzcba = Arrays.copyOf(this.zzcba, i2);
        }
        this.zzccp[this.count] = i;
        this.zzcba[this.count] = obj;
        this.count++;
    }
}
