package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgk extends zzza<zzgk> {
    private static volatile zzgk[] zzayi;
    public Integer zzawq = null;
    public long[] zzayj = zzzj.zzcfr;

    public static zzgk[] zzmt() {
        if (zzayi == null) {
            synchronized (zzze.zzcfl) {
                if (zzayi == null) {
                    zzayi = new zzgk[0];
                }
            }
        }
        return zzayi;
    }

    public zzgk() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgk)) {
            return false;
        }
        zzgk zzgkVar = (zzgk) obj;
        if (this.zzawq == null) {
            if (zzgkVar.zzawq != null) {
                return false;
            }
        } else if (!this.zzawq.equals(zzgkVar.zzawq)) {
            return false;
        }
        if (!zzze.equals(this.zzayj, zzgkVar.zzayj)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgkVar.zzcfc == null || zzgkVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgkVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((527 + getClass().getName().hashCode()) * 31) + (this.zzawq == null ? 0 : this.zzawq.hashCode())) * 31) + zzze.hashCode(this.zzayj)) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzawq != null) {
            zzyyVar.zzd(1, this.zzawq.intValue());
        }
        if (this.zzayj != null && this.zzayj.length > 0) {
            for (int i = 0; i < this.zzayj.length; i++) {
                zzyyVar.zzi(2, this.zzayj[i]);
            }
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzawq != null) {
            iZzf += zzyy.zzh(1, this.zzawq.intValue());
        }
        if (this.zzayj != null && this.zzayj.length > 0) {
            int iZzbi = 0;
            for (int i = 0; i < this.zzayj.length; i++) {
                iZzbi += zzyy.zzbi(this.zzayj[i]);
            }
            return iZzf + iZzbi + (1 * this.zzayj.length);
        }
        return iZzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        int length;
        int length2;
        while (true) {
            int iZzug = zzyxVar.zzug();
            if (iZzug == 0) {
                return this;
            }
            if (iZzug == 8) {
                this.zzawq = Integer.valueOf(zzyxVar.zzuy());
            } else if (iZzug == 16) {
                int iZzb = zzzj.zzb(zzyxVar, 16);
                if (this.zzayj != null) {
                    length2 = this.zzayj.length;
                } else {
                    length2 = 0;
                }
                long[] jArr = new long[iZzb + length2];
                if (length2 != 0) {
                    System.arraycopy(this.zzayj, 0, jArr, 0, length2);
                }
                while (length2 < jArr.length - 1) {
                    jArr[length2] = zzyxVar.zzuz();
                    zzyxVar.zzug();
                    length2++;
                }
                jArr[length2] = zzyxVar.zzuz();
                this.zzayj = jArr;
            } else if (iZzug != 18) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                int iZzaq = zzyxVar.zzaq(zzyxVar.zzuy());
                int position = zzyxVar.getPosition();
                int i = 0;
                while (zzyxVar.zzyr() > 0) {
                    zzyxVar.zzuz();
                    i++;
                }
                zzyxVar.zzby(position);
                if (this.zzayj != null) {
                    length = this.zzayj.length;
                } else {
                    length = 0;
                }
                long[] jArr2 = new long[i + length];
                if (length != 0) {
                    System.arraycopy(this.zzayj, 0, jArr2, 0, length);
                }
                while (length < jArr2.length) {
                    jArr2[length] = zzyxVar.zzuz();
                    length++;
                }
                this.zzayj = jArr2;
                zzyxVar.zzar(iZzaq);
            }
        }
    }
}
