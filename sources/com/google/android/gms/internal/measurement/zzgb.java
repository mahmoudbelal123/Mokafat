package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgb extends zzza<zzgb> {
    public Long zzawe = null;
    public String zzafx = null;
    private Integer zzawf = null;
    public zzgc[] zzawg = zzgc.zzmn();
    public zzga[] zzawh = zzga.zzmm();
    public zzfu[] zzawi = zzfu.zzmi();
    private String zzawj = null;

    public zzgb() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgb)) {
            return false;
        }
        zzgb zzgbVar = (zzgb) obj;
        if (this.zzawe == null) {
            if (zzgbVar.zzawe != null) {
                return false;
            }
        } else if (!this.zzawe.equals(zzgbVar.zzawe)) {
            return false;
        }
        if (this.zzafx == null) {
            if (zzgbVar.zzafx != null) {
                return false;
            }
        } else if (!this.zzafx.equals(zzgbVar.zzafx)) {
            return false;
        }
        if (this.zzawf == null) {
            if (zzgbVar.zzawf != null) {
                return false;
            }
        } else if (!this.zzawf.equals(zzgbVar.zzawf)) {
            return false;
        }
        if (!zzze.equals(this.zzawg, zzgbVar.zzawg) || !zzze.equals(this.zzawh, zzgbVar.zzawh) || !zzze.equals(this.zzawi, zzgbVar.zzawi)) {
            return false;
        }
        if (this.zzawj == null) {
            if (zzgbVar.zzawj != null) {
                return false;
            }
        } else if (!this.zzawj.equals(zzgbVar.zzawj)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgbVar.zzcfc == null || zzgbVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgbVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((((((((((((527 + getClass().getName().hashCode()) * 31) + (this.zzawe == null ? 0 : this.zzawe.hashCode())) * 31) + (this.zzafx == null ? 0 : this.zzafx.hashCode())) * 31) + (this.zzawf == null ? 0 : this.zzawf.hashCode())) * 31) + zzze.hashCode(this.zzawg)) * 31) + zzze.hashCode(this.zzawh)) * 31) + zzze.hashCode(this.zzawi)) * 31) + (this.zzawj == null ? 0 : this.zzawj.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzawe != null) {
            zzyyVar.zzi(1, this.zzawe.longValue());
        }
        if (this.zzafx != null) {
            zzyyVar.zzb(2, this.zzafx);
        }
        if (this.zzawf != null) {
            zzyyVar.zzd(3, this.zzawf.intValue());
        }
        if (this.zzawg != null && this.zzawg.length > 0) {
            for (int i = 0; i < this.zzawg.length; i++) {
                zzgc zzgcVar = this.zzawg[i];
                if (zzgcVar != null) {
                    zzyyVar.zza(4, zzgcVar);
                }
            }
        }
        if (this.zzawh != null && this.zzawh.length > 0) {
            for (int i2 = 0; i2 < this.zzawh.length; i2++) {
                zzga zzgaVar = this.zzawh[i2];
                if (zzgaVar != null) {
                    zzyyVar.zza(5, zzgaVar);
                }
            }
        }
        if (this.zzawi != null && this.zzawi.length > 0) {
            for (int i3 = 0; i3 < this.zzawi.length; i3++) {
                zzfu zzfuVar = this.zzawi[i3];
                if (zzfuVar != null) {
                    zzyyVar.zza(6, zzfuVar);
                }
            }
        }
        if (this.zzawj != null) {
            zzyyVar.zzb(7, this.zzawj);
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzawe != null) {
            iZzf += zzyy.zzd(1, this.zzawe.longValue());
        }
        if (this.zzafx != null) {
            iZzf += zzyy.zzc(2, this.zzafx);
        }
        if (this.zzawf != null) {
            iZzf += zzyy.zzh(3, this.zzawf.intValue());
        }
        if (this.zzawg != null && this.zzawg.length > 0) {
            int iZzb = iZzf;
            for (int i = 0; i < this.zzawg.length; i++) {
                zzgc zzgcVar = this.zzawg[i];
                if (zzgcVar != null) {
                    iZzb += zzyy.zzb(4, zzgcVar);
                }
            }
            iZzf = iZzb;
        }
        if (this.zzawh != null && this.zzawh.length > 0) {
            int iZzb2 = iZzf;
            for (int i2 = 0; i2 < this.zzawh.length; i2++) {
                zzga zzgaVar = this.zzawh[i2];
                if (zzgaVar != null) {
                    iZzb2 += zzyy.zzb(5, zzgaVar);
                }
            }
            iZzf = iZzb2;
        }
        if (this.zzawi != null && this.zzawi.length > 0) {
            for (int i3 = 0; i3 < this.zzawi.length; i3++) {
                zzfu zzfuVar = this.zzawi[i3];
                if (zzfuVar != null) {
                    iZzf += zzyy.zzb(6, zzfuVar);
                }
            }
        }
        if (this.zzawj != null) {
            return iZzf + zzyy.zzc(7, this.zzawj);
        }
        return iZzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        int length;
        int length2;
        int length3;
        while (true) {
            int iZzug = zzyxVar.zzug();
            if (iZzug == 0) {
                return this;
            }
            if (iZzug == 8) {
                this.zzawe = Long.valueOf(zzyxVar.zzuz());
            } else if (iZzug == 18) {
                this.zzafx = zzyxVar.readString();
            } else if (iZzug == 24) {
                this.zzawf = Integer.valueOf(zzyxVar.zzuy());
            } else if (iZzug == 34) {
                int iZzb = zzzj.zzb(zzyxVar, 34);
                if (this.zzawg != null) {
                    length3 = this.zzawg.length;
                } else {
                    length3 = 0;
                }
                zzgc[] zzgcVarArr = new zzgc[iZzb + length3];
                if (length3 != 0) {
                    System.arraycopy(this.zzawg, 0, zzgcVarArr, 0, length3);
                }
                while (length3 < zzgcVarArr.length - 1) {
                    zzgcVarArr[length3] = new zzgc();
                    zzyxVar.zza(zzgcVarArr[length3]);
                    zzyxVar.zzug();
                    length3++;
                }
                zzgcVarArr[length3] = new zzgc();
                zzyxVar.zza(zzgcVarArr[length3]);
                this.zzawg = zzgcVarArr;
            } else if (iZzug == 42) {
                int iZzb2 = zzzj.zzb(zzyxVar, 42);
                if (this.zzawh != null) {
                    length2 = this.zzawh.length;
                } else {
                    length2 = 0;
                }
                zzga[] zzgaVarArr = new zzga[iZzb2 + length2];
                if (length2 != 0) {
                    System.arraycopy(this.zzawh, 0, zzgaVarArr, 0, length2);
                }
                while (length2 < zzgaVarArr.length - 1) {
                    zzgaVarArr[length2] = new zzga();
                    zzyxVar.zza(zzgaVarArr[length2]);
                    zzyxVar.zzug();
                    length2++;
                }
                zzgaVarArr[length2] = new zzga();
                zzyxVar.zza(zzgaVarArr[length2]);
                this.zzawh = zzgaVarArr;
            } else if (iZzug == 50) {
                int iZzb3 = zzzj.zzb(zzyxVar, 50);
                if (this.zzawi != null) {
                    length = this.zzawi.length;
                } else {
                    length = 0;
                }
                zzfu[] zzfuVarArr = new zzfu[iZzb3 + length];
                if (length != 0) {
                    System.arraycopy(this.zzawi, 0, zzfuVarArr, 0, length);
                }
                while (length < zzfuVarArr.length - 1) {
                    zzfuVarArr[length] = new zzfu();
                    zzyxVar.zza(zzfuVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzfuVarArr[length] = new zzfu();
                zzyxVar.zza(zzfuVarArr[length]);
                this.zzawi = zzfuVarArr;
            } else if (iZzug != 58) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzawj = zzyxVar.readString();
            }
        }
    }
}
