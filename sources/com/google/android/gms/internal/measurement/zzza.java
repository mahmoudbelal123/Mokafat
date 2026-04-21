package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzza;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzza<M extends zzza<M>> extends zzzg {
    protected zzzc zzcfc;

    @Override // com.google.android.gms.internal.measurement.zzzg
    protected int zzf() {
        if (this.zzcfc == null) {
            return 0;
        }
        int iZzf = 0;
        for (int i = 0; i < this.zzcfc.size(); i++) {
            iZzf += this.zzcfc.zzcc(i).zzf();
        }
        return iZzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public void zza(zzyy zzyyVar) throws IOException {
        if (this.zzcfc == null) {
            return;
        }
        for (int i = 0; i < this.zzcfc.size(); i++) {
            this.zzcfc.zzcc(i).zza(zzyyVar);
        }
    }

    public final <T> T zza(zzzb<M, T> zzzbVar) {
        zzzd zzzdVarZzcb;
        if (this.zzcfc == null || (zzzdVarZzcb = this.zzcfc.zzcb(zzzbVar.tag >>> 3)) == null) {
            return null;
        }
        return (T) zzzdVarZzcb.zzb(zzzbVar);
    }

    protected final boolean zza(zzyx zzyxVar, int i) throws IOException {
        int position = zzyxVar.getPosition();
        if (!zzyxVar.zzao(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzzi zzziVar = new zzzi(i, zzyxVar.zzs(position, zzyxVar.getPosition() - position));
        zzzd zzzdVarZzcb = null;
        if (this.zzcfc == null) {
            this.zzcfc = new zzzc();
        } else {
            zzzdVarZzcb = this.zzcfc.zzcb(i2);
        }
        if (zzzdVarZzcb == null) {
            zzzdVarZzcb = new zzzd();
            this.zzcfc.zza(i2, zzzdVarZzcb);
        }
        zzzdVarZzcb.zza(zzziVar);
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    /* JADX INFO: renamed from: zzyu */
    public final /* synthetic */ zzzg clone() throws CloneNotSupportedException {
        return (zzza) clone();
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzza zzzaVar = (zzza) super.clone();
        zzze.zza(this, zzzaVar);
        return zzzaVar;
    }
}
