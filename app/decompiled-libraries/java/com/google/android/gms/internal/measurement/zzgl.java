package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgl extends zzza<zzgl> {
    private static volatile zzgl[] zzayk;
    public Long zzayl = null;
    public String name = null;
    public String zzamp = null;
    public Long zzawx = null;
    private Float zzaug = null;
    public Double zzauh = null;

    public static zzgl[] zzmu() {
        if (zzayk == null) {
            synchronized (zzze.zzcfl) {
                if (zzayk == null) {
                    zzayk = new zzgl[0];
                }
            }
        }
        return zzayk;
    }

    public zzgl() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgl)) {
            return false;
        }
        zzgl zzglVar = (zzgl) obj;
        if (this.zzayl == null) {
            if (zzglVar.zzayl != null) {
                return false;
            }
        } else if (!this.zzayl.equals(zzglVar.zzayl)) {
            return false;
        }
        if (this.name == null) {
            if (zzglVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzglVar.name)) {
            return false;
        }
        if (this.zzamp == null) {
            if (zzglVar.zzamp != null) {
                return false;
            }
        } else if (!this.zzamp.equals(zzglVar.zzamp)) {
            return false;
        }
        if (this.zzawx == null) {
            if (zzglVar.zzawx != null) {
                return false;
            }
        } else if (!this.zzawx.equals(zzglVar.zzawx)) {
            return false;
        }
        if (this.zzaug == null) {
            if (zzglVar.zzaug != null) {
                return false;
            }
        } else if (!this.zzaug.equals(zzglVar.zzaug)) {
            return false;
        }
        if (this.zzauh == null) {
            if (zzglVar.zzauh != null) {
                return false;
            }
        } else if (!this.zzauh.equals(zzglVar.zzauh)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzglVar.zzcfc == null || zzglVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzglVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((((((((((527 + getClass().getName().hashCode()) * 31) + (this.zzayl == null ? 0 : this.zzayl.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzamp == null ? 0 : this.zzamp.hashCode())) * 31) + (this.zzawx == null ? 0 : this.zzawx.hashCode())) * 31) + (this.zzaug == null ? 0 : this.zzaug.hashCode())) * 31) + (this.zzauh == null ? 0 : this.zzauh.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzayl != null) {
            zzyyVar.zzi(1, this.zzayl.longValue());
        }
        if (this.name != null) {
            zzyyVar.zzb(2, this.name);
        }
        if (this.zzamp != null) {
            zzyyVar.zzb(3, this.zzamp);
        }
        if (this.zzawx != null) {
            zzyyVar.zzi(4, this.zzawx.longValue());
        }
        if (this.zzaug != null) {
            zzyyVar.zza(5, this.zzaug.floatValue());
        }
        if (this.zzauh != null) {
            zzyyVar.zza(6, this.zzauh.doubleValue());
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzayl != null) {
            iZzf += zzyy.zzd(1, this.zzayl.longValue());
        }
        if (this.name != null) {
            iZzf += zzyy.zzc(2, this.name);
        }
        if (this.zzamp != null) {
            iZzf += zzyy.zzc(3, this.zzamp);
        }
        if (this.zzawx != null) {
            iZzf += zzyy.zzd(4, this.zzawx.longValue());
        }
        if (this.zzaug != null) {
            this.zzaug.floatValue();
            iZzf += zzyy.zzbb(5) + 4;
        }
        if (this.zzauh != null) {
            this.zzauh.doubleValue();
            return iZzf + zzyy.zzbb(6) + 8;
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
                this.zzayl = Long.valueOf(zzyxVar.zzuz());
            } else if (iZzug == 18) {
                this.name = zzyxVar.readString();
            } else if (iZzug == 26) {
                this.zzamp = zzyxVar.readString();
            } else if (iZzug == 32) {
                this.zzawx = Long.valueOf(zzyxVar.zzuz());
            } else if (iZzug == 45) {
                this.zzaug = Float.valueOf(Float.intBitsToFloat(zzyxVar.zzva()));
            } else if (iZzug != 49) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzauh = Double.valueOf(Double.longBitsToDouble(zzyxVar.zzvb()));
            }
        }
    }
}
