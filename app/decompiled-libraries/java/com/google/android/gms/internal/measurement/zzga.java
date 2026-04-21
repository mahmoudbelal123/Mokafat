package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzga extends zzza<zzga> {
    private static volatile zzga[] zzawa;
    public String name = null;
    public Boolean zzawb = null;
    public Boolean zzawc = null;
    public Integer zzawd = null;

    public static zzga[] zzmm() {
        if (zzawa == null) {
            synchronized (zzze.zzcfl) {
                if (zzawa == null) {
                    zzawa = new zzga[0];
                }
            }
        }
        return zzawa;
    }

    public zzga() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzga)) {
            return false;
        }
        zzga zzgaVar = (zzga) obj;
        if (this.name == null) {
            if (zzgaVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzgaVar.name)) {
            return false;
        }
        if (this.zzawb == null) {
            if (zzgaVar.zzawb != null) {
                return false;
            }
        } else if (!this.zzawb.equals(zzgaVar.zzawb)) {
            return false;
        }
        if (this.zzawc == null) {
            if (zzgaVar.zzawc != null) {
                return false;
            }
        } else if (!this.zzawc.equals(zzgaVar.zzawc)) {
            return false;
        }
        if (this.zzawd == null) {
            if (zzgaVar.zzawd != null) {
                return false;
            }
        } else if (!this.zzawd.equals(zzgaVar.zzawd)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgaVar.zzcfc == null || zzgaVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgaVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((((((527 + getClass().getName().hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzawb == null ? 0 : this.zzawb.hashCode())) * 31) + (this.zzawc == null ? 0 : this.zzawc.hashCode())) * 31) + (this.zzawd == null ? 0 : this.zzawd.hashCode())) * 31;
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
        if (this.zzawb != null) {
            zzyyVar.zzb(2, this.zzawb.booleanValue());
        }
        if (this.zzawc != null) {
            zzyyVar.zzb(3, this.zzawc.booleanValue());
        }
        if (this.zzawd != null) {
            zzyyVar.zzd(4, this.zzawd.intValue());
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.name != null) {
            iZzf += zzyy.zzc(1, this.name);
        }
        if (this.zzawb != null) {
            this.zzawb.booleanValue();
            iZzf += zzyy.zzbb(2) + 1;
        }
        if (this.zzawc != null) {
            this.zzawc.booleanValue();
            iZzf += zzyy.zzbb(3) + 1;
        }
        if (this.zzawd != null) {
            return iZzf + zzyy.zzh(4, this.zzawd.intValue());
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
            } else if (iZzug == 16) {
                this.zzawb = Boolean.valueOf(zzyxVar.zzum());
            } else if (iZzug == 24) {
                this.zzawc = Boolean.valueOf(zzyxVar.zzum());
            } else if (iZzug != 32) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.zzawd = Integer.valueOf(zzyxVar.zzuy());
            }
        }
    }
}
