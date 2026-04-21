package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzfu extends zzza<zzfu> {
    private static volatile zzfu[] zzaux;
    public Integer zzauy = null;
    public zzfy[] zzauz = zzfy.zzml();
    public zzfv[] zzava = zzfv.zzmj();
    private Boolean zzavb = null;
    private Boolean zzavc = null;

    public static zzfu[] zzmi() {
        if (zzaux == null) {
            synchronized (zzze.zzcfl) {
                if (zzaux == null) {
                    zzaux = new zzfu[0];
                }
            }
        }
        return zzaux;
    }

    public zzfu() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfu)) {
            return false;
        }
        zzfu zzfuVar = (zzfu) obj;
        if (this.zzauy == null) {
            if (zzfuVar.zzauy != null) {
                return false;
            }
        } else if (!this.zzauy.equals(zzfuVar.zzauy)) {
            return false;
        }
        if (!zzze.equals(this.zzauz, zzfuVar.zzauz) || !zzze.equals(this.zzava, zzfuVar.zzava)) {
            return false;
        }
        if (this.zzavb == null) {
            if (zzfuVar.zzavb != null) {
                return false;
            }
        } else if (!this.zzavb.equals(zzfuVar.zzavb)) {
            return false;
        }
        if (this.zzavc == null) {
            if (zzfuVar.zzavc != null) {
                return false;
            }
        } else if (!this.zzavc.equals(zzfuVar.zzavc)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzfuVar.zzcfc == null || zzfuVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzfuVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((((((((527 + getClass().getName().hashCode()) * 31) + (this.zzauy == null ? 0 : this.zzauy.hashCode())) * 31) + zzze.hashCode(this.zzauz)) * 31) + zzze.hashCode(this.zzava)) * 31) + (this.zzavb == null ? 0 : this.zzavb.hashCode())) * 31) + (this.zzavc == null ? 0 : this.zzavc.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzauy != null) {
            zzyyVar.zzd(1, this.zzauy.intValue());
        }
        if (this.zzauz != null && this.zzauz.length > 0) {
            for (int i = 0; i < this.zzauz.length; i++) {
                zzfy zzfyVar = this.zzauz[i];
                if (zzfyVar != null) {
                    zzyyVar.zza(2, zzfyVar);
                }
            }
        }
        if (this.zzava != null && this.zzava.length > 0) {
            for (int i2 = 0; i2 < this.zzava.length; i2++) {
                zzfv zzfvVar = this.zzava[i2];
                if (zzfvVar != null) {
                    zzyyVar.zza(3, zzfvVar);
                }
            }
        }
        if (this.zzavb != null) {
            zzyyVar.zzb(4, this.zzavb.booleanValue());
        }
        if (this.zzavc != null) {
            zzyyVar.zzb(5, this.zzavc.booleanValue());
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzauy != null) {
            iZzf += zzyy.zzh(1, this.zzauy.intValue());
        }
        if (this.zzauz != null && this.zzauz.length > 0) {
            int iZzb = iZzf;
            for (int i = 0; i < this.zzauz.length; i++) {
                zzfy zzfyVar = this.zzauz[i];
                if (zzfyVar != null) {
                    iZzb += zzyy.zzb(2, zzfyVar);
                }
            }
            iZzf = iZzb;
        }
        if (this.zzava != null && this.zzava.length > 0) {
            for (int i2 = 0; i2 < this.zzava.length; i2++) {
                zzfv zzfvVar = this.zzava[i2];
                if (zzfvVar != null) {
                    iZzf += zzyy.zzb(3, zzfvVar);
                }
            }
        }
        if (this.zzavb != null) {
            this.zzavb.booleanValue();
            iZzf += zzyy.zzbb(4) + 1;
        }
        if (this.zzavc != null) {
            this.zzavc.booleanValue();
            return iZzf + zzyy.zzbb(5) + 1;
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
                this.zzauy = Integer.valueOf(zzyxVar.zzuy());
            } else if (iZzug == 18) {
                int iZzb = zzzj.zzb(zzyxVar, 18);
                if (this.zzauz != null) {
                    length2 = this.zzauz.length;
                } else {
                    length2 = 0;
                }
                zzfy[] zzfyVarArr = new zzfy[iZzb + length2];
                if (length2 != 0) {
                    System.arraycopy(this.zzauz, 0, zzfyVarArr, 0, length2);
                }
                while (length2 < zzfyVarArr.length - 1) {
                    zzfyVarArr[length2] = new zzfy();
                    zzyxVar.zza(zzfyVarArr[length2]);
                    zzyxVar.zzug();
                    length2++;
                }
                zzfyVarArr[length2] = new zzfy();
                zzyxVar.zza(zzfyVarArr[length2]);
                this.zzauz = zzfyVarArr;
            } else if (iZzug == 26) {
                int iZzb2 = zzzj.zzb(zzyxVar, 26);
                if (this.zzava != null) {
                    length = this.zzava.length;
                } else {
                    length = 0;
                }
                zzfv[] zzfvVarArr = new zzfv[iZzb2 + length];
                if (length != 0) {
                    System.arraycopy(this.zzava, 0, zzfvVarArr, 0, length);
                }
                while (length < zzfvVarArr.length - 1) {
                    zzfvVarArr[length] = new zzfv();
                    zzyxVar.zza(zzfvVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzfvVarArr[length] = new zzfv();
                zzyxVar.zza(zzfvVarArr[length]);
                this.zzava = zzfvVarArr;
            } else if (iZzug == 32) {
                this.zzavb = Boolean.valueOf(zzyxVar.zzum());
            } else if (iZzug != 40) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzavc = Boolean.valueOf(zzyxVar.zzum());
            }
        }
    }
}
