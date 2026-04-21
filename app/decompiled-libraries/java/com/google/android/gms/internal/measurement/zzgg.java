package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgg extends zzza<zzgg> {
    private static volatile zzgg[] zzaww;
    public String name = null;
    public String zzamp = null;
    public Long zzawx = null;
    private Float zzaug = null;
    public Double zzauh = null;

    public static zzgg[] zzmr() {
        if (zzaww == null) {
            synchronized (zzze.zzcfl) {
                if (zzaww == null) {
                    zzaww = new zzgg[0];
                }
            }
        }
        return zzaww;
    }

    public zzgg() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgg)) {
            return false;
        }
        zzgg zzggVar = (zzgg) obj;
        if (this.name == null) {
            if (zzggVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzggVar.name)) {
            return false;
        }
        if (this.zzamp == null) {
            if (zzggVar.zzamp != null) {
                return false;
            }
        } else if (!this.zzamp.equals(zzggVar.zzamp)) {
            return false;
        }
        if (this.zzawx == null) {
            if (zzggVar.zzawx != null) {
                return false;
            }
        } else if (!this.zzawx.equals(zzggVar.zzawx)) {
            return false;
        }
        if (this.zzaug == null) {
            if (zzggVar.zzaug != null) {
                return false;
            }
        } else if (!this.zzaug.equals(zzggVar.zzaug)) {
            return false;
        }
        if (this.zzauh == null) {
            if (zzggVar.zzauh != null) {
                return false;
            }
        } else if (!this.zzauh.equals(zzggVar.zzauh)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzggVar.zzcfc == null || zzggVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzggVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((((((((527 + getClass().getName().hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzamp == null ? 0 : this.zzamp.hashCode())) * 31) + (this.zzawx == null ? 0 : this.zzawx.hashCode())) * 31) + (this.zzaug == null ? 0 : this.zzaug.hashCode())) * 31) + (this.zzauh == null ? 0 : this.zzauh.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.name != null) {
            zzyyVar.zzb(1, this.name);
        }
        if (this.zzamp != null) {
            zzyyVar.zzb(2, this.zzamp);
        }
        if (this.zzawx != null) {
            zzyyVar.zzi(3, this.zzawx.longValue());
        }
        if (this.zzaug != null) {
            zzyyVar.zza(4, this.zzaug.floatValue());
        }
        if (this.zzauh != null) {
            zzyyVar.zza(5, this.zzauh.doubleValue());
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.name != null) {
            iZzf += zzyy.zzc(1, this.name);
        }
        if (this.zzamp != null) {
            iZzf += zzyy.zzc(2, this.zzamp);
        }
        if (this.zzawx != null) {
            iZzf += zzyy.zzd(3, this.zzawx.longValue());
        }
        if (this.zzaug != null) {
            this.zzaug.floatValue();
            iZzf += zzyy.zzbb(4) + 4;
        }
        if (this.zzauh != null) {
            this.zzauh.doubleValue();
            return iZzf + zzyy.zzbb(5) + 8;
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
            if (iZzug == 10) {
                this.name = zzyxVar.readString();
            } else if (iZzug == 18) {
                this.zzamp = zzyxVar.readString();
            } else if (iZzug == 24) {
                this.zzawx = Long.valueOf(zzyxVar.zzuz());
            } else if (iZzug == 37) {
                this.zzaug = Float.valueOf(Float.intBitsToFloat(zzyxVar.zzva()));
            } else if (iZzug != 41) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzauh = Double.valueOf(Double.longBitsToDouble(zzyxVar.zzvb()));
            }
        }
    }
}
