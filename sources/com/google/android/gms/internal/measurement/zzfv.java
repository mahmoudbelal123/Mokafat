package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzfv extends zzza<zzfv> {
    private static volatile zzfv[] zzavd;
    public Integer zzave = null;
    public String zzavf = null;
    public zzfw[] zzavg = zzfw.zzmk();
    private Boolean zzavh = null;
    public zzfx zzavi = null;
    public Boolean zzavb = null;
    public Boolean zzavc = null;

    public static zzfv[] zzmj() {
        if (zzavd == null) {
            synchronized (zzze.zzcfl) {
                if (zzavd == null) {
                    zzavd = new zzfv[0];
                }
            }
        }
        return zzavd;
    }

    public zzfv() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfv)) {
            return false;
        }
        zzfv zzfvVar = (zzfv) obj;
        if (this.zzave == null) {
            if (zzfvVar.zzave != null) {
                return false;
            }
        } else if (!this.zzave.equals(zzfvVar.zzave)) {
            return false;
        }
        if (this.zzavf == null) {
            if (zzfvVar.zzavf != null) {
                return false;
            }
        } else if (!this.zzavf.equals(zzfvVar.zzavf)) {
            return false;
        }
        if (!zzze.equals(this.zzavg, zzfvVar.zzavg)) {
            return false;
        }
        if (this.zzavh == null) {
            if (zzfvVar.zzavh != null) {
                return false;
            }
        } else if (!this.zzavh.equals(zzfvVar.zzavh)) {
            return false;
        }
        if (this.zzavi == null) {
            if (zzfvVar.zzavi != null) {
                return false;
            }
        } else if (!this.zzavi.equals(zzfvVar.zzavi)) {
            return false;
        }
        if (this.zzavb == null) {
            if (zzfvVar.zzavb != null) {
                return false;
            }
        } else if (!this.zzavb.equals(zzfvVar.zzavb)) {
            return false;
        }
        if (this.zzavc == null) {
            if (zzfvVar.zzavc != null) {
                return false;
            }
        } else if (!this.zzavc.equals(zzfvVar.zzavc)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzfvVar.zzcfc == null || zzfvVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzfvVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((((((527 + getClass().getName().hashCode()) * 31) + (this.zzave == null ? 0 : this.zzave.hashCode())) * 31) + (this.zzavf == null ? 0 : this.zzavf.hashCode())) * 31) + zzze.hashCode(this.zzavg)) * 31) + (this.zzavh == null ? 0 : this.zzavh.hashCode());
        zzfx zzfxVar = this.zzavi;
        int iHashCode3 = ((((((iHashCode2 * 31) + (zzfxVar == null ? 0 : zzfxVar.hashCode())) * 31) + (this.zzavb == null ? 0 : this.zzavb.hashCode())) * 31) + (this.zzavc == null ? 0 : this.zzavc.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode3 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzave != null) {
            zzyyVar.zzd(1, this.zzave.intValue());
        }
        if (this.zzavf != null) {
            zzyyVar.zzb(2, this.zzavf);
        }
        if (this.zzavg != null && this.zzavg.length > 0) {
            for (int i = 0; i < this.zzavg.length; i++) {
                zzfw zzfwVar = this.zzavg[i];
                if (zzfwVar != null) {
                    zzyyVar.zza(3, zzfwVar);
                }
            }
        }
        if (this.zzavh != null) {
            zzyyVar.zzb(4, this.zzavh.booleanValue());
        }
        if (this.zzavi != null) {
            zzyyVar.zza(5, this.zzavi);
        }
        if (this.zzavb != null) {
            zzyyVar.zzb(6, this.zzavb.booleanValue());
        }
        if (this.zzavc != null) {
            zzyyVar.zzb(7, this.zzavc.booleanValue());
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzave != null) {
            iZzf += zzyy.zzh(1, this.zzave.intValue());
        }
        if (this.zzavf != null) {
            iZzf += zzyy.zzc(2, this.zzavf);
        }
        if (this.zzavg != null && this.zzavg.length > 0) {
            for (int i = 0; i < this.zzavg.length; i++) {
                zzfw zzfwVar = this.zzavg[i];
                if (zzfwVar != null) {
                    iZzf += zzyy.zzb(3, zzfwVar);
                }
            }
        }
        if (this.zzavh != null) {
            this.zzavh.booleanValue();
            iZzf += zzyy.zzbb(4) + 1;
        }
        if (this.zzavi != null) {
            iZzf += zzyy.zzb(5, this.zzavi);
        }
        if (this.zzavb != null) {
            this.zzavb.booleanValue();
            iZzf += zzyy.zzbb(6) + 1;
        }
        if (this.zzavc != null) {
            this.zzavc.booleanValue();
            return iZzf + zzyy.zzbb(7) + 1;
        }
        return iZzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        int length;
        while (true) {
            int iZzug = zzyxVar.zzug();
            if (iZzug == 0) {
                return this;
            }
            if (iZzug == 8) {
                this.zzave = Integer.valueOf(zzyxVar.zzuy());
            } else if (iZzug == 18) {
                this.zzavf = zzyxVar.readString();
            } else if (iZzug == 26) {
                int iZzb = zzzj.zzb(zzyxVar, 26);
                if (this.zzavg != null) {
                    length = this.zzavg.length;
                } else {
                    length = 0;
                }
                zzfw[] zzfwVarArr = new zzfw[iZzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzavg, 0, zzfwVarArr, 0, length);
                }
                while (length < zzfwVarArr.length - 1) {
                    zzfwVarArr[length] = new zzfw();
                    zzyxVar.zza(zzfwVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzfwVarArr[length] = new zzfw();
                zzyxVar.zza(zzfwVarArr[length]);
                this.zzavg = zzfwVarArr;
            } else if (iZzug == 32) {
                this.zzavh = Boolean.valueOf(zzyxVar.zzum());
            } else if (iZzug == 42) {
                if (this.zzavi == null) {
                    this.zzavi = new zzfx();
                }
                zzyxVar.zza(this.zzavi);
            } else if (iZzug == 48) {
                this.zzavb = Boolean.valueOf(zzyxVar.zzum());
            } else if (iZzug != 56) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzavc = Boolean.valueOf(zzyxVar.zzum());
            }
        }
    }
}
