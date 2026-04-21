package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzzc implements Cloneable {
    private static final zzzd zzcff = new zzzd();
    private int mSize;
    private boolean zzcfg;
    private int[] zzcfh;
    private zzzd[] zzcfi;

    zzzc() {
        this(10);
    }

    private zzzc(int i) {
        this.zzcfg = false;
        int iIdealIntArraySize = idealIntArraySize(i);
        this.zzcfh = new int[iIdealIntArraySize];
        this.zzcfi = new zzzd[iIdealIntArraySize];
        this.mSize = 0;
    }

    final zzzd zzcb(int i) {
        int iZzcd = zzcd(i);
        if (iZzcd < 0 || this.zzcfi[iZzcd] == zzcff) {
            return null;
        }
        return this.zzcfi[iZzcd];
    }

    final void zza(int i, zzzd zzzdVar) {
        int iZzcd = zzcd(i);
        if (iZzcd >= 0) {
            this.zzcfi[iZzcd] = zzzdVar;
            return;
        }
        int i2 = iZzcd ^ (-1);
        if (i2 < this.mSize && this.zzcfi[i2] == zzcff) {
            this.zzcfh[i2] = i;
            this.zzcfi[i2] = zzzdVar;
            return;
        }
        if (this.mSize >= this.zzcfh.length) {
            int iIdealIntArraySize = idealIntArraySize(this.mSize + 1);
            int[] iArr = new int[iIdealIntArraySize];
            zzzd[] zzzdVarArr = new zzzd[iIdealIntArraySize];
            System.arraycopy(this.zzcfh, 0, iArr, 0, this.zzcfh.length);
            System.arraycopy(this.zzcfi, 0, zzzdVarArr, 0, this.zzcfi.length);
            this.zzcfh = iArr;
            this.zzcfi = zzzdVarArr;
        }
        if (this.mSize - i2 != 0) {
            int i3 = i2 + 1;
            System.arraycopy(this.zzcfh, i2, this.zzcfh, i3, this.mSize - i2);
            System.arraycopy(this.zzcfi, i2, this.zzcfi, i3, this.mSize - i2);
        }
        this.zzcfh[i2] = i;
        this.zzcfi[i2] = zzzdVar;
        this.mSize++;
    }

    final int size() {
        return this.mSize;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final zzzd zzcc(int i) {
        return this.zzcfi[i];
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzzc)) {
            return false;
        }
        zzzc zzzcVar = (zzzc) obj;
        if (this.mSize != zzzcVar.mSize) {
            return false;
        }
        int[] iArr = this.zzcfh;
        int[] iArr2 = zzzcVar.zzcfh;
        int i = this.mSize;
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
            zzzd[] zzzdVarArr = this.zzcfi;
            zzzd[] zzzdVarArr2 = zzzcVar.zzcfi;
            int i3 = this.mSize;
            int i4 = 0;
            while (true) {
                if (i4 < i3) {
                    if (zzzdVarArr[i4].equals(zzzdVarArr2[i4])) {
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
        return false;
    }

    public final int hashCode() {
        int iHashCode = 17;
        for (int i = 0; i < this.mSize; i++) {
            iHashCode = (((iHashCode * 31) + this.zzcfh[i]) * 31) + this.zzcfi[i].hashCode();
        }
        return iHashCode;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (i2 > i4) {
                i3++;
            } else {
                i2 = i4;
                break;
            }
        }
        return i2 / 4;
    }

    private final int zzcd(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzcfh[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else if (i5 > i) {
                i2 = i4 - 1;
            } else {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzzc zzzcVar = new zzzc(i);
        System.arraycopy(this.zzcfh, 0, zzzcVar.zzcfh, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzcfi[i2] != null) {
                zzzcVar.zzcfi[i2] = (zzzd) this.zzcfi[i2].clone();
            }
        }
        zzzcVar.mSize = i;
        return zzzcVar;
    }
}
