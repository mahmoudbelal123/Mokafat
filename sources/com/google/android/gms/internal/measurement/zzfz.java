package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzfz extends zzza<zzfz> {
    public Integer zzavw = null;
    public String zzavx = null;
    public Boolean zzavy = null;
    public String[] zzavz = zzzj.zzcfv;

    public zzfz() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfz)) {
            return false;
        }
        zzfz zzfzVar = (zzfz) obj;
        if (this.zzavw == null) {
            if (zzfzVar.zzavw != null) {
                return false;
            }
        } else if (!this.zzavw.equals(zzfzVar.zzavw)) {
            return false;
        }
        if (this.zzavx == null) {
            if (zzfzVar.zzavx != null) {
                return false;
            }
        } else if (!this.zzavx.equals(zzfzVar.zzavx)) {
            return false;
        }
        if (this.zzavy == null) {
            if (zzfzVar.zzavy != null) {
                return false;
            }
        } else if (!this.zzavy.equals(zzfzVar.zzavy)) {
            return false;
        }
        if (!zzze.equals(this.zzavz, zzfzVar.zzavz)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzfzVar.zzcfc == null || zzfzVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzfzVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((((((527 + getClass().getName().hashCode()) * 31) + (this.zzavw == null ? 0 : this.zzavw.intValue())) * 31) + (this.zzavx == null ? 0 : this.zzavx.hashCode())) * 31) + (this.zzavy == null ? 0 : this.zzavy.hashCode())) * 31) + zzze.hashCode(this.zzavz)) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzavw != null) {
            zzyyVar.zzd(1, this.zzavw.intValue());
        }
        if (this.zzavx != null) {
            zzyyVar.zzb(2, this.zzavx);
        }
        if (this.zzavy != null) {
            zzyyVar.zzb(3, this.zzavy.booleanValue());
        }
        if (this.zzavz != null && this.zzavz.length > 0) {
            for (int i = 0; i < this.zzavz.length; i++) {
                String str = this.zzavz[i];
                if (str != null) {
                    zzyyVar.zzb(4, str);
                }
            }
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzavw != null) {
            iZzf += zzyy.zzh(1, this.zzavw.intValue());
        }
        if (this.zzavx != null) {
            iZzf += zzyy.zzc(2, this.zzavx);
        }
        if (this.zzavy != null) {
            this.zzavy.booleanValue();
            iZzf += zzyy.zzbb(3) + 1;
        }
        if (this.zzavz != null && this.zzavz.length > 0) {
            int iZzfx = 0;
            int i = 0;
            for (int i2 = 0; i2 < this.zzavz.length; i2++) {
                String str = this.zzavz[i2];
                if (str != null) {
                    i++;
                    iZzfx += zzyy.zzfx(str);
                }
            }
            return iZzf + iZzfx + (1 * i);
        }
        return iZzf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzzg
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzfz zza(zzyx zzyxVar) throws IOException {
        int length;
        while (true) {
            int iZzug = zzyxVar.zzug();
            if (iZzug == 0) {
                return this;
            }
            if (iZzug == 8) {
                int position = zzyxVar.getPosition();
                try {
                    int iZzuy = zzyxVar.zzuy();
                    if (iZzuy < 0 || iZzuy > 6) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append(iZzuy);
                        sb.append(" is not a valid enum MatchType");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.zzavw = Integer.valueOf(iZzuy);
                } catch (IllegalArgumentException e) {
                    zzyxVar.zzby(position);
                    zza(zzyxVar, iZzug);
                }
            } else if (iZzug == 18) {
                this.zzavx = zzyxVar.readString();
            } else if (iZzug == 24) {
                this.zzavy = Boolean.valueOf(zzyxVar.zzum());
            } else if (iZzug != 34) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                int iZzb = zzzj.zzb(zzyxVar, 34);
                if (this.zzavz != null) {
                    length = this.zzavz.length;
                } else {
                    length = 0;
                }
                String[] strArr = new String[iZzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzavz, 0, strArr, 0, length);
                }
                while (length < strArr.length - 1) {
                    strArr[length] = zzyxVar.readString();
                    zzyxVar.zzug();
                    length++;
                }
                strArr[length] = zzyxVar.readString();
                this.zzavz = strArr;
            }
        }
    }
}
