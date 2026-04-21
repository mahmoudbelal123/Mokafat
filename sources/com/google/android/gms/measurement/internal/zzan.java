package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class zzan extends zzcp {
    private static final AtomicReference<String[]> zzalt = new AtomicReference<>();
    private static final AtomicReference<String[]> zzalu = new AtomicReference<>();
    private static final AtomicReference<String[]> zzalv = new AtomicReference<>();

    zzan(zzbt zzbtVar) {
        super(zzbtVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzcp
    protected final boolean zzgt() {
        return false;
    }

    private final boolean zzjc() {
        zzgr();
        return this.zzadj.zzkj() && this.zzadj.zzgo().isLoggable(3);
    }

    @Nullable
    protected final String zzbs(String str) {
        if (str == null) {
            return null;
        }
        if (!zzjc()) {
            return str;
        }
        return zza(str, AppMeasurement.Event.zzadl, AppMeasurement.Event.zzadk, zzalt);
    }

    @Nullable
    protected final String zzbt(String str) {
        if (str == null) {
            return null;
        }
        if (!zzjc()) {
            return str;
        }
        return zza(str, AppMeasurement.Param.zzadn, AppMeasurement.Param.zzadm, zzalu);
    }

    @Nullable
    protected final String zzbu(String str) {
        if (str == null) {
            return null;
        }
        if (!zzjc()) {
            return str;
        }
        if (str.startsWith("_exp_")) {
            return "experiment_id(" + str + ")";
        }
        return zza(str, AppMeasurement.UserProperty.zzadp, AppMeasurement.UserProperty.zzado, zzalv);
    }

    @Nullable
    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzfk.zzu(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        strArr3[i] = strArr2[i] + "(" + strArr[i] + ")";
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    @Nullable
    protected final String zzb(zzad zzadVar) {
        if (zzadVar == null) {
            return null;
        }
        if (!zzjc()) {
            return zzadVar.toString();
        }
        return "origin=" + zzadVar.origin + ",name=" + zzbs(zzadVar.name) + ",params=" + zzb(zzadVar.zzaid);
    }

    @Nullable
    protected final String zza(zzy zzyVar) {
        if (zzyVar == null) {
            return null;
        }
        if (!zzjc()) {
            return zzyVar.toString();
        }
        return "Event{appId='" + zzyVar.zztt + "', name='" + zzbs(zzyVar.name) + "', params=" + zzb(zzyVar.zzaid) + "}";
    }

    @Nullable
    private final String zzb(zzaa zzaaVar) {
        if (zzaaVar == null) {
            return null;
        }
        if (!zzjc()) {
            return zzaaVar.toString();
        }
        return zzd(zzaaVar.zziv());
    }

    @Nullable
    protected final String zzd(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzjc()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (sb.length() != 0) {
                sb.append(", ");
            } else {
                sb.append("Bundle[{");
            }
            sb.append(zzbt(str));
            sb.append("=");
            sb.append(bundle.get(str));
        }
        sb.append("}]");
        return sb.toString();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzga() {
        super.zzga();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzgb() {
        super.zzgb();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzgc() {
        super.zzgc();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzx zzgk() {
        return super.zzgk();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzan zzgl() {
        return super.zzgl();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzfk zzgm() {
        return super.zzgm();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzbo zzgn() {
        return super.zzgn();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzap zzgo() {
        return super.zzgo();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzba zzgp() {
        return super.zzgp();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzn zzgq() {
        return super.zzgq();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzk zzgr() {
        return super.zzgr();
    }
}
