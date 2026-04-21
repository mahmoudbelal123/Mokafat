package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgc extends zzza<zzgc> {
    private static volatile zzgc[] zzawk;
    public String zzoj = null;
    public String value = null;

    public static zzgc[] zzmn() {
        if (zzawk == null) {
            synchronized (zzze.zzcfl) {
                if (zzawk == null) {
                    zzawk = new zzgc[0];
                }
            }
        }
        return zzawk;
    }

    public zzgc() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgc)) {
            return false;
        }
        zzgc zzgcVar = (zzgc) obj;
        if (this.zzoj == null) {
            if (zzgcVar.zzoj != null) {
                return false;
            }
        } else if (!this.zzoj.equals(zzgcVar.zzoj)) {
            return false;
        }
        if (this.value == null) {
            if (zzgcVar.value != null) {
                return false;
            }
        } else if (!this.value.equals(zzgcVar.value)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgcVar.zzcfc == null || zzgcVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgcVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((527 + getClass().getName().hashCode()) * 31) + (this.zzoj == null ? 0 : this.zzoj.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzoj != null) {
            zzyyVar.zzb(1, this.zzoj);
        }
        if (this.value != null) {
            zzyyVar.zzb(2, this.value);
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzoj != null) {
            iZzf += zzyy.zzc(1, this.zzoj);
        }
        if (this.value != null) {
            return iZzf + zzyy.zzc(2, this.value);
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
                this.zzoj = zzyxVar.readString();
            } else if (iZzug != 18) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.value = zzyxVar.readString();
            }
        }
    }
}
