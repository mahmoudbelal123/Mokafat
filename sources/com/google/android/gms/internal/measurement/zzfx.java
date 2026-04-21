package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzfx extends zzza<zzfx> {
    public Integer zzavo = null;
    public Boolean zzavp = null;
    public String zzavq = null;
    public String zzavr = null;
    public String zzavs = null;

    public zzfx() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfx)) {
            return false;
        }
        zzfx zzfxVar = (zzfx) obj;
        if (this.zzavo == null) {
            if (zzfxVar.zzavo != null) {
                return false;
            }
        } else if (!this.zzavo.equals(zzfxVar.zzavo)) {
            return false;
        }
        if (this.zzavp == null) {
            if (zzfxVar.zzavp != null) {
                return false;
            }
        } else if (!this.zzavp.equals(zzfxVar.zzavp)) {
            return false;
        }
        if (this.zzavq == null) {
            if (zzfxVar.zzavq != null) {
                return false;
            }
        } else if (!this.zzavq.equals(zzfxVar.zzavq)) {
            return false;
        }
        if (this.zzavr == null) {
            if (zzfxVar.zzavr != null) {
                return false;
            }
        } else if (!this.zzavr.equals(zzfxVar.zzavr)) {
            return false;
        }
        if (this.zzavs == null) {
            if (zzfxVar.zzavs != null) {
                return false;
            }
        } else if (!this.zzavs.equals(zzfxVar.zzavs)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzfxVar.zzcfc == null || zzfxVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzfxVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((((((((527 + getClass().getName().hashCode()) * 31) + (this.zzavo == null ? 0 : this.zzavo.intValue())) * 31) + (this.zzavp == null ? 0 : this.zzavp.hashCode())) * 31) + (this.zzavq == null ? 0 : this.zzavq.hashCode())) * 31) + (this.zzavr == null ? 0 : this.zzavr.hashCode())) * 31) + (this.zzavs == null ? 0 : this.zzavs.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzavo != null) {
            zzyyVar.zzd(1, this.zzavo.intValue());
        }
        if (this.zzavp != null) {
            zzyyVar.zzb(2, this.zzavp.booleanValue());
        }
        if (this.zzavq != null) {
            zzyyVar.zzb(3, this.zzavq);
        }
        if (this.zzavr != null) {
            zzyyVar.zzb(4, this.zzavr);
        }
        if (this.zzavs != null) {
            zzyyVar.zzb(5, this.zzavs);
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzavo != null) {
            iZzf += zzyy.zzh(1, this.zzavo.intValue());
        }
        if (this.zzavp != null) {
            this.zzavp.booleanValue();
            iZzf += zzyy.zzbb(2) + 1;
        }
        if (this.zzavq != null) {
            iZzf += zzyy.zzc(3, this.zzavq);
        }
        if (this.zzavr != null) {
            iZzf += zzyy.zzc(4, this.zzavr);
        }
        if (this.zzavs != null) {
            return iZzf + zzyy.zzc(5, this.zzavs);
        }
        return iZzf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzzg
    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final zzfx zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int iZzug = zzyxVar.zzug();
            if (iZzug == 0) {
                return this;
            }
            if (iZzug == 8) {
                int position = zzyxVar.getPosition();
                try {
                    int iZzuy = zzyxVar.zzuy();
                    if (iZzuy < 0 || iZzuy > 4) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(iZzuy);
                        sb.append(" is not a valid enum ComparisonType");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.zzavo = Integer.valueOf(iZzuy);
                } catch (IllegalArgumentException e) {
                    zzyxVar.zzby(position);
                    zza(zzyxVar, iZzug);
                }
            } else if (iZzug == 16) {
                this.zzavp = Boolean.valueOf(zzyxVar.zzum());
            } else if (iZzug == 26) {
                this.zzavq = zzyxVar.readString();
            } else if (iZzug == 34) {
                this.zzavr = zzyxVar.readString();
            } else if (iZzug != 42) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzavs = zzyxVar.readString();
            }
        }
    }
}
