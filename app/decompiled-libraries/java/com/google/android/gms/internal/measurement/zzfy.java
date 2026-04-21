package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzfy extends zzza<zzfy> {
    private static volatile zzfy[] zzavt;
    public Integer zzave = null;
    public String zzavu = null;
    public zzfw zzavv = null;
    public Boolean zzavb = null;
    public Boolean zzavc = null;

    public static zzfy[] zzml() {
        if (zzavt == null) {
            synchronized (zzze.zzcfl) {
                if (zzavt == null) {
                    zzavt = new zzfy[0];
                }
            }
        }
        return zzavt;
    }

    public zzfy() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfy)) {
            return false;
        }
        zzfy zzfyVar = (zzfy) obj;
        if (this.zzave == null) {
            if (zzfyVar.zzave != null) {
                return false;
            }
        } else if (!this.zzave.equals(zzfyVar.zzave)) {
            return false;
        }
        if (this.zzavu == null) {
            if (zzfyVar.zzavu != null) {
                return false;
            }
        } else if (!this.zzavu.equals(zzfyVar.zzavu)) {
            return false;
        }
        if (this.zzavv == null) {
            if (zzfyVar.zzavv != null) {
                return false;
            }
        } else if (!this.zzavv.equals(zzfyVar.zzavv)) {
            return false;
        }
        if (this.zzavb == null) {
            if (zzfyVar.zzavb != null) {
                return false;
            }
        } else if (!this.zzavb.equals(zzfyVar.zzavb)) {
            return false;
        }
        if (this.zzavc == null) {
            if (zzfyVar.zzavc != null) {
                return false;
            }
        } else if (!this.zzavc.equals(zzfyVar.zzavc)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzfyVar.zzcfc == null || zzfyVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzfyVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((527 + getClass().getName().hashCode()) * 31) + (this.zzave == null ? 0 : this.zzave.hashCode())) * 31) + (this.zzavu == null ? 0 : this.zzavu.hashCode());
        zzfw zzfwVar = this.zzavv;
        int iHashCode3 = ((((((iHashCode2 * 31) + (zzfwVar == null ? 0 : zzfwVar.hashCode())) * 31) + (this.zzavb == null ? 0 : this.zzavb.hashCode())) * 31) + (this.zzavc == null ? 0 : this.zzavc.hashCode())) * 31;
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
        if (this.zzavu != null) {
            zzyyVar.zzb(2, this.zzavu);
        }
        if (this.zzavv != null) {
            zzyyVar.zza(3, this.zzavv);
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
        if (this.zzave != null) {
            iZzf += zzyy.zzh(1, this.zzave.intValue());
        }
        if (this.zzavu != null) {
            iZzf += zzyy.zzc(2, this.zzavu);
        }
        if (this.zzavv != null) {
            iZzf += zzyy.zzb(3, this.zzavv);
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
        while (true) {
            int iZzug = zzyxVar.zzug();
            if (iZzug == 0) {
                return this;
            }
            if (iZzug == 8) {
                this.zzave = Integer.valueOf(zzyxVar.zzuy());
            } else if (iZzug == 18) {
                this.zzavu = zzyxVar.readString();
            } else if (iZzug == 26) {
                if (this.zzavv == null) {
                    this.zzavv = new zzfw();
                }
                zzyxVar.zza(this.zzavv);
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
