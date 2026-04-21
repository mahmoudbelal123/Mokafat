package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgf extends zzza<zzgf> {
    private static volatile zzgf[] zzaws;
    public zzgg[] zzawt = zzgg.zzmr();
    public String name = null;
    public Long zzawu = null;
    public Long zzawv = null;
    public Integer count = null;

    public static zzgf[] zzmq() {
        if (zzaws == null) {
            synchronized (zzze.zzcfl) {
                if (zzaws == null) {
                    zzaws = new zzgf[0];
                }
            }
        }
        return zzaws;
    }

    public zzgf() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgf)) {
            return false;
        }
        zzgf zzgfVar = (zzgf) obj;
        if (!zzze.equals(this.zzawt, zzgfVar.zzawt)) {
            return false;
        }
        if (this.name == null) {
            if (zzgfVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzgfVar.name)) {
            return false;
        }
        if (this.zzawu == null) {
            if (zzgfVar.zzawu != null) {
                return false;
            }
        } else if (!this.zzawu.equals(zzgfVar.zzawu)) {
            return false;
        }
        if (this.zzawv == null) {
            if (zzgfVar.zzawv != null) {
                return false;
            }
        } else if (!this.zzawv.equals(zzgfVar.zzawv)) {
            return false;
        }
        if (this.count == null) {
            if (zzgfVar.count != null) {
                return false;
            }
        } else if (!this.count.equals(zzgfVar.count)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgfVar.zzcfc == null || zzgfVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgfVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((((((((527 + getClass().getName().hashCode()) * 31) + zzze.hashCode(this.zzawt)) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzawu == null ? 0 : this.zzawu.hashCode())) * 31) + (this.zzawv == null ? 0 : this.zzawv.hashCode())) * 31) + (this.count == null ? 0 : this.count.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzawt != null && this.zzawt.length > 0) {
            for (int i = 0; i < this.zzawt.length; i++) {
                zzgg zzggVar = this.zzawt[i];
                if (zzggVar != null) {
                    zzyyVar.zza(1, zzggVar);
                }
            }
        }
        if (this.name != null) {
            zzyyVar.zzb(2, this.name);
        }
        if (this.zzawu != null) {
            zzyyVar.zzi(3, this.zzawu.longValue());
        }
        if (this.zzawv != null) {
            zzyyVar.zzi(4, this.zzawv.longValue());
        }
        if (this.count != null) {
            zzyyVar.zzd(5, this.count.intValue());
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzawt != null && this.zzawt.length > 0) {
            for (int i = 0; i < this.zzawt.length; i++) {
                zzgg zzggVar = this.zzawt[i];
                if (zzggVar != null) {
                    iZzf += zzyy.zzb(1, zzggVar);
                }
            }
        }
        if (this.name != null) {
            iZzf += zzyy.zzc(2, this.name);
        }
        if (this.zzawu != null) {
            iZzf += zzyy.zzd(3, this.zzawu.longValue());
        }
        if (this.zzawv != null) {
            iZzf += zzyy.zzd(4, this.zzawv.longValue());
        }
        if (this.count != null) {
            return iZzf + zzyy.zzh(5, this.count.intValue());
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
            if (iZzug == 10) {
                int iZzb = zzzj.zzb(zzyxVar, 10);
                if (this.zzawt != null) {
                    length = this.zzawt.length;
                } else {
                    length = 0;
                }
                zzgg[] zzggVarArr = new zzgg[iZzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzawt, 0, zzggVarArr, 0, length);
                }
                while (length < zzggVarArr.length - 1) {
                    zzggVarArr[length] = new zzgg();
                    zzyxVar.zza(zzggVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzggVarArr[length] = new zzgg();
                zzyxVar.zza(zzggVarArr[length]);
                this.zzawt = zzggVarArr;
            } else if (iZzug == 18) {
                this.name = zzyxVar.readString();
            } else if (iZzug == 24) {
                this.zzawu = Long.valueOf(zzyxVar.zzuz());
            } else if (iZzug == 32) {
                this.zzawv = Long.valueOf(zzyxVar.zzuz());
            } else if (iZzug != 40) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                this.count = Integer.valueOf(zzyxVar.zzuy());
            }
        }
    }
}
