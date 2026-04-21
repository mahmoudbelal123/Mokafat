package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgj extends zzza<zzgj> {
    public long[] zzaye = zzzj.zzcfr;
    public long[] zzayf = zzzj.zzcfr;
    public zzge[] zzayg = zzge.zzmp();
    public zzgk[] zzayh = zzgk.zzmt();

    public zzgj() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgj)) {
            return false;
        }
        zzgj zzgjVar = (zzgj) obj;
        if (!zzze.equals(this.zzaye, zzgjVar.zzaye) || !zzze.equals(this.zzayf, zzgjVar.zzayf) || !zzze.equals(this.zzayg, zzgjVar.zzayg) || !zzze.equals(this.zzayh, zzgjVar.zzayh)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgjVar.zzcfc == null || zzgjVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgjVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode;
        int iHashCode2 = (((((((((527 + getClass().getName().hashCode()) * 31) + zzze.hashCode(this.zzaye)) * 31) + zzze.hashCode(this.zzayf)) * 31) + zzze.hashCode(this.zzayg)) * 31) + zzze.hashCode(this.zzayh)) * 31;
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            iHashCode = 0;
        } else {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzaye != null && this.zzaye.length > 0) {
            for (int i = 0; i < this.zzaye.length; i++) {
                zzyyVar.zza(1, this.zzaye[i]);
            }
        }
        if (this.zzayf != null && this.zzayf.length > 0) {
            for (int i2 = 0; i2 < this.zzayf.length; i2++) {
                zzyyVar.zza(2, this.zzayf[i2]);
            }
        }
        if (this.zzayg != null && this.zzayg.length > 0) {
            for (int i3 = 0; i3 < this.zzayg.length; i3++) {
                zzge zzgeVar = this.zzayg[i3];
                if (zzgeVar != null) {
                    zzyyVar.zza(3, zzgeVar);
                }
            }
        }
        if (this.zzayh != null && this.zzayh.length > 0) {
            for (int i4 = 0; i4 < this.zzayh.length; i4++) {
                zzgk zzgkVar = this.zzayh[i4];
                if (zzgkVar != null) {
                    zzyyVar.zza(4, zzgkVar);
                }
            }
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzaye != null && this.zzaye.length > 0) {
            int iZzbi = 0;
            for (int i = 0; i < this.zzaye.length; i++) {
                iZzbi += zzyy.zzbi(this.zzaye[i]);
            }
            iZzf = iZzf + iZzbi + (this.zzaye.length * 1);
        }
        if (this.zzayf != null && this.zzayf.length > 0) {
            int iZzbi2 = 0;
            for (int i2 = 0; i2 < this.zzayf.length; i2++) {
                iZzbi2 += zzyy.zzbi(this.zzayf[i2]);
            }
            iZzf = iZzf + iZzbi2 + (1 * this.zzayf.length);
        }
        if (this.zzayg != null && this.zzayg.length > 0) {
            int iZzb = iZzf;
            for (int i3 = 0; i3 < this.zzayg.length; i3++) {
                zzge zzgeVar = this.zzayg[i3];
                if (zzgeVar != null) {
                    iZzb += zzyy.zzb(3, zzgeVar);
                }
            }
            iZzf = iZzb;
        }
        if (this.zzayh != null && this.zzayh.length > 0) {
            for (int i4 = 0; i4 < this.zzayh.length; i4++) {
                zzgk zzgkVar = this.zzayh[i4];
                if (zzgkVar != null) {
                    iZzf += zzyy.zzb(4, zzgkVar);
                }
            }
        }
        return iZzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        int length;
        int length2;
        int length3;
        int length4;
        int length5;
        int length6;
        while (true) {
            int iZzug = zzyxVar.zzug();
            if (iZzug == 0) {
                return this;
            }
            if (iZzug == 8) {
                int iZzb = zzzj.zzb(zzyxVar, 8);
                if (this.zzaye != null) {
                    length6 = this.zzaye.length;
                } else {
                    length6 = 0;
                }
                long[] jArr = new long[iZzb + length6];
                if (length6 != 0) {
                    System.arraycopy(this.zzaye, 0, jArr, 0, length6);
                }
                while (length6 < jArr.length - 1) {
                    jArr[length6] = zzyxVar.zzuz();
                    zzyxVar.zzug();
                    length6++;
                }
                jArr[length6] = zzyxVar.zzuz();
                this.zzaye = jArr;
            } else if (iZzug == 10) {
                int iZzaq = zzyxVar.zzaq(zzyxVar.zzuy());
                int position = zzyxVar.getPosition();
                int i = 0;
                while (zzyxVar.zzyr() > 0) {
                    zzyxVar.zzuz();
                    i++;
                }
                zzyxVar.zzby(position);
                if (this.zzaye != null) {
                    length5 = this.zzaye.length;
                } else {
                    length5 = 0;
                }
                long[] jArr2 = new long[i + length5];
                if (length5 != 0) {
                    System.arraycopy(this.zzaye, 0, jArr2, 0, length5);
                }
                while (length5 < jArr2.length) {
                    jArr2[length5] = zzyxVar.zzuz();
                    length5++;
                }
                this.zzaye = jArr2;
                zzyxVar.zzar(iZzaq);
            } else if (iZzug == 16) {
                int iZzb2 = zzzj.zzb(zzyxVar, 16);
                if (this.zzayf != null) {
                    length4 = this.zzayf.length;
                } else {
                    length4 = 0;
                }
                long[] jArr3 = new long[iZzb2 + length4];
                if (length4 != 0) {
                    System.arraycopy(this.zzayf, 0, jArr3, 0, length4);
                }
                while (length4 < jArr3.length - 1) {
                    jArr3[length4] = zzyxVar.zzuz();
                    zzyxVar.zzug();
                    length4++;
                }
                jArr3[length4] = zzyxVar.zzuz();
                this.zzayf = jArr3;
            } else if (iZzug == 18) {
                int iZzaq2 = zzyxVar.zzaq(zzyxVar.zzuy());
                int position2 = zzyxVar.getPosition();
                int i2 = 0;
                while (zzyxVar.zzyr() > 0) {
                    zzyxVar.zzuz();
                    i2++;
                }
                zzyxVar.zzby(position2);
                if (this.zzayf != null) {
                    length3 = this.zzayf.length;
                } else {
                    length3 = 0;
                }
                long[] jArr4 = new long[i2 + length3];
                if (length3 != 0) {
                    System.arraycopy(this.zzayf, 0, jArr4, 0, length3);
                }
                while (length3 < jArr4.length) {
                    jArr4[length3] = zzyxVar.zzuz();
                    length3++;
                }
                this.zzayf = jArr4;
                zzyxVar.zzar(iZzaq2);
            } else if (iZzug == 26) {
                int iZzb3 = zzzj.zzb(zzyxVar, 26);
                if (this.zzayg != null) {
                    length2 = this.zzayg.length;
                } else {
                    length2 = 0;
                }
                zzge[] zzgeVarArr = new zzge[iZzb3 + length2];
                if (length2 != 0) {
                    System.arraycopy(this.zzayg, 0, zzgeVarArr, 0, length2);
                }
                while (length2 < zzgeVarArr.length - 1) {
                    zzgeVarArr[length2] = new zzge();
                    zzyxVar.zza(zzgeVarArr[length2]);
                    zzyxVar.zzug();
                    length2++;
                }
                zzgeVarArr[length2] = new zzge();
                zzyxVar.zza(zzgeVarArr[length2]);
                this.zzayg = zzgeVarArr;
            } else if (iZzug != 34) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                int iZzb4 = zzzj.zzb(zzyxVar, 34);
                if (this.zzayh != null) {
                    length = this.zzayh.length;
                } else {
                    length = 0;
                }
                zzgk[] zzgkVarArr = new zzgk[iZzb4 + length];
                if (length != 0) {
                    System.arraycopy(this.zzayh, 0, zzgkVarArr, 0, length);
                }
                while (length < zzgkVarArr.length - 1) {
                    zzgkVarArr[length] = new zzgk();
                    zzyxVar.zza(zzgkVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzgkVarArr[length] = new zzgk();
                zzyxVar.zza(zzgkVarArr[length]);
                this.zzayh = zzgkVarArr;
            }
        }
    }
}
