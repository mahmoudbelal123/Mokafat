package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgd extends zzza<zzgd> {
    private static volatile zzgd[] zzawl;
    public Integer zzauy = null;
    public zzgj zzawm = null;
    public zzgj zzawn = null;
    public Boolean zzawo = null;

    public static zzgd[] zzmo() {
        if (zzawl == null) {
            synchronized (zzze.zzcfl) {
                if (zzawl == null) {
                    zzawl = new zzgd[0];
                }
            }
        }
        return zzawl;
    }

    public zzgd() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgd)) {
            return false;
        }
        zzgd zzgdVar = (zzgd) obj;
        if (this.zzauy == null) {
            if (zzgdVar.zzauy != null) {
                return false;
            }
        } else if (!this.zzauy.equals(zzgdVar.zzauy)) {
            return false;
        }
        if (this.zzawm == null) {
            if (zzgdVar.zzawm != null) {
                return false;
            }
        } else if (!this.zzawm.equals(zzgdVar.zzawm)) {
            return false;
        }
        if (this.zzawn == null) {
            if (zzgdVar.zzawn != null) {
                return false;
            }
        } else if (!this.zzawn.equals(zzgdVar.zzawn)) {
            return false;
        }
        if (this.zzawo == null) {
            if (zzgdVar.zzawo != null) {
                return false;
            }
        } else if (!this.zzawo.equals(zzgdVar.zzawo)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgdVar.zzcfc == null || zzgdVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgdVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((527 + getClass().getName().hashCode()) * 31) + (this.zzauy == null ? 0 : this.zzauy.hashCode());
        zzgj zzgjVar = this.zzawm;
        int iHashCode3 = (iHashCode2 * 31) + (zzgjVar == null ? 0 : zzgjVar.hashCode());
        zzgj zzgjVar2 = this.zzawn;
        int iHashCode4 = ((((iHashCode3 * 31) + (zzgjVar2 == null ? 0 : zzgjVar2.hashCode())) * 31) + (this.zzawo == null ? 0 : this.zzawo.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode4 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzauy != null) {
            zzyyVar.zzd(1, this.zzauy.intValue());
        }
        if (this.zzawm != null) {
            zzyyVar.zza(2, this.zzawm);
        }
        if (this.zzawn != null) {
            zzyyVar.zza(3, this.zzawn);
        }
        if (this.zzawo != null) {
            zzyyVar.zzb(4, this.zzawo.booleanValue());
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzauy != null) {
            iZzf += zzyy.zzh(1, this.zzauy.intValue());
        }
        if (this.zzawm != null) {
            iZzf += zzyy.zzb(2, this.zzawm);
        }
        if (this.zzawn != null) {
            iZzf += zzyy.zzb(3, this.zzawn);
        }
        if (this.zzawo != null) {
            this.zzawo.booleanValue();
            return iZzf + zzyy.zzbb(4) + 1;
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
                this.zzauy = Integer.valueOf(zzyxVar.zzuy());
            } else if (iZzug == 18) {
                if (this.zzawm == null) {
                    this.zzawm = new zzgj();
                }
                zzyxVar.zza(this.zzawm);
            } else if (iZzug == 26) {
                if (this.zzawn == null) {
                    this.zzawn = new zzgj();
                }
                zzyxVar.zza(this.zzawn);
            } else if (iZzug != 32) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzawo = Boolean.valueOf(zzyxVar.zzum());
            }
        }
    }
}
