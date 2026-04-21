package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzgh extends zzza<zzgh> {
    public zzgi[] zzawy = zzgi.zzms();

    public zzgh() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgh)) {
            return false;
        }
        zzgh zzghVar = (zzgh) obj;
        if (!zzze.equals(this.zzawy, zzghVar.zzawy)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzghVar.zzcfc == null || zzghVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzghVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode;
        int iHashCode2 = (((527 + getClass().getName().hashCode()) * 31) + zzze.hashCode(this.zzawy)) * 31;
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            iHashCode = 0;
        } else {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzawy != null && this.zzawy.length > 0) {
            for (int i = 0; i < this.zzawy.length; i++) {
                zzgi zzgiVar = this.zzawy[i];
                if (zzgiVar != null) {
                    zzyyVar.zza(1, zzgiVar);
                }
            }
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzawy != null && this.zzawy.length > 0) {
            for (int i = 0; i < this.zzawy.length; i++) {
                zzgi zzgiVar = this.zzawy[i];
                if (zzgiVar != null) {
                    iZzf += zzyy.zzb(1, zzgiVar);
                }
            }
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
            if (iZzug != 10) {
                if (!super.zza(zzyxVar, iZzug)) {
                    return this;
                }
            } else {
                int iZzb = zzzj.zzb(zzyxVar, 10);
                if (this.zzawy != null) {
                    length = this.zzawy.length;
                } else {
                    length = 0;
                }
                zzgi[] zzgiVarArr = new zzgi[iZzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzawy, 0, zzgiVarArr, 0, length);
                }
                while (length < zzgiVarArr.length - 1) {
                    zzgiVarArr[length] = new zzgi();
                    zzyxVar.zza(zzgiVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzgiVarArr[length] = new zzgi();
                zzyxVar.zza(zzgiVarArr[length]);
                this.zzawy = zzgiVarArr;
            }
        }
    }
}
