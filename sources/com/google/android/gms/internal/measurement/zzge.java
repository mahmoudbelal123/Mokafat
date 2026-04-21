package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzge extends zzza<zzge> {
    private static volatile zzge[] zzawp;
    public Integer zzawq = null;
    public Long zzawr = null;

    public static zzge[] zzmp() {
        if (zzawp == null) {
            synchronized (zzze.zzcfl) {
                if (zzawp == null) {
                    zzawp = new zzge[0];
                }
            }
        }
        return zzawp;
    }

    public zzge() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzge)) {
            return false;
        }
        zzge zzgeVar = (zzge) obj;
        if (this.zzawq == null) {
            if (zzgeVar.zzawq != null) {
                return false;
            }
        } else if (!this.zzawq.equals(zzgeVar.zzawq)) {
            return false;
        }
        if (this.zzawr == null) {
            if (zzgeVar.zzawr != null) {
                return false;
            }
        } else if (!this.zzawr.equals(zzgeVar.zzawr)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgeVar.zzcfc == null || zzgeVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgeVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((527 + getClass().getName().hashCode()) * 31) + (this.zzawq == null ? 0 : this.zzawq.hashCode())) * 31) + (this.zzawr == null ? 0 : this.zzawr.hashCode())) * 31;
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
        if (this.zzawr != null) {
            zzyyVar.zzi(2, this.zzawr.longValue());
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzawq != null) {
            iZzf += zzyy.zzh(1, this.zzawq.intValue());
        }
        if (this.zzawr != null) {
            return iZzf + zzyy.zzd(2, this.zzawr.longValue());
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
                this.zzawq = Integer.valueOf(zzyxVar.zzuy());
            } else if (iZzug != 16) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzawr = Long.valueOf(zzyxVar.zzuz());
            }
        }
    }
}
