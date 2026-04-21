package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzzg {
    protected volatile int zzcfm = -1;

    public abstract zzzg zza(zzyx zzyxVar) throws IOException;

    public final int zzza() {
        if (this.zzcfm < 0) {
            zzvu();
        }
        return this.zzcfm;
    }

    public final int zzvu() {
        int iZzf = zzf();
        this.zzcfm = iZzf;
        return iZzf;
    }

    protected int zzf() {
        return 0;
    }

    public void zza(zzyy zzyyVar) throws IOException {
    }

    public static final void zza(zzzg zzzgVar, byte[] bArr, int i, int i2) {
        try {
            zzyy zzyyVarZzk = zzyy.zzk(bArr, 0, i2);
            zzzgVar.zza(zzyyVarZzk);
            zzyyVarZzk.zzyt();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzzg> T zza(T t, byte[] bArr) throws zzzf {
        return (T) zzb(t, bArr, 0, bArr.length);
    }

    private static final <T extends zzzg> T zzb(T t, byte[] bArr, int i, int i2) throws zzzf {
        try {
            zzyx zzyxVarZzj = zzyx.zzj(bArr, 0, i2);
            t.zza(zzyxVarZzj);
            zzyxVarZzj.zzan(0);
            return t;
        } catch (zzzf e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public String toString() {
        return zzzh.zzc(this);
    }

    @Override // 
    /* JADX INFO: renamed from: zzyu, reason: merged with bridge method [inline-methods] */
    public zzzg clone() throws CloneNotSupportedException {
        return (zzzg) super.clone();
    }
}
