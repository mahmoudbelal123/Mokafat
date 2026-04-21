package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzga;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzyx;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzbn extends zzez implements zzp {

    @VisibleForTesting
    private static int zzaon = SupportMenu.USER_MASK;

    @VisibleForTesting
    private static int zzaoo = 2;
    private final Map<String, Map<String, String>> zzaop;
    private final Map<String, Map<String, Boolean>> zzaoq;
    private final Map<String, Map<String, Boolean>> zzaor;
    private final Map<String, zzgb> zzaos;
    private final Map<String, Map<String, Integer>> zzaot;
    private final Map<String, String> zzaou;

    zzbn(zzfa zzfaVar) {
        super(zzfaVar);
        this.zzaop = new ArrayMap();
        this.zzaoq = new ArrayMap();
        this.zzaor = new ArrayMap();
        this.zzaos = new ArrayMap();
        this.zzaou = new ArrayMap();
        this.zzaot = new ArrayMap();
    }

    @WorkerThread
    private final void zzce(String str) {
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        if (this.zzaos.get(str) == null) {
            byte[] bArrZzbn = zzjq().zzbn(str);
            if (bArrZzbn == null) {
                this.zzaop.put(str, null);
                this.zzaoq.put(str, null);
                this.zzaor.put(str, null);
                this.zzaos.put(str, null);
                this.zzaou.put(str, null);
                this.zzaot.put(str, null);
                return;
            }
            zzgb zzgbVarZza = zza(str, bArrZzbn);
            this.zzaop.put(str, zza(zzgbVarZza));
            zza(str, zzgbVarZza);
            this.zzaos.put(str, zzgbVarZza);
            this.zzaou.put(str, null);
        }
    }

    @WorkerThread
    protected final zzgb zzcf(String str) {
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        zzce(str);
        return this.zzaos.get(str);
    }

    @WorkerThread
    protected final String zzcg(String str) {
        zzaf();
        return this.zzaou.get(str);
    }

    @WorkerThread
    protected final void zzch(String str) {
        zzaf();
        this.zzaou.put(str, null);
    }

    @WorkerThread
    final void zzci(String str) {
        zzaf();
        this.zzaos.remove(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzp
    @WorkerThread
    public final String zzf(String str, String str2) {
        zzaf();
        zzce(str);
        Map<String, String> map = this.zzaop.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    private static Map<String, String> zza(zzgb zzgbVar) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzgbVar != null && zzgbVar.zzawg != null) {
            for (zzgc zzgcVar : zzgbVar.zzawg) {
                if (zzgcVar != null) {
                    arrayMap.put(zzgcVar.zzoj, zzgcVar.value);
                }
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzgb zzgbVar) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zzgbVar != null && zzgbVar.zzawh != null) {
            for (zzga zzgaVar : zzgbVar.zzawh) {
                if (TextUtils.isEmpty(zzgaVar.name)) {
                    zzgo().zzjg().zzbx("EventConfig contained null event name");
                } else {
                    String strZzal = AppMeasurement.Event.zzal(zzgaVar.name);
                    if (!TextUtils.isEmpty(strZzal)) {
                        zzgaVar.name = strZzal;
                    }
                    arrayMap.put(zzgaVar.name, zzgaVar.zzawb);
                    arrayMap2.put(zzgaVar.name, zzgaVar.zzawc);
                    if (zzgaVar.zzawd != null) {
                        if (zzgaVar.zzawd.intValue() < zzaoo || zzgaVar.zzawd.intValue() > zzaon) {
                            zzgo().zzjg().zze("Invalid sampling rate. Event name, sample rate", zzgaVar.name, zzgaVar.zzawd);
                        } else {
                            arrayMap3.put(zzgaVar.name, zzgaVar.zzawd);
                        }
                    }
                }
            }
        }
        this.zzaoq.put(str, arrayMap);
        this.zzaor.put(str, arrayMap2);
        this.zzaot.put(str, arrayMap3);
    }

    @WorkerThread
    protected final boolean zza(String str, byte[] bArr, String str2) {
        byte[] bArr2;
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        zzgb zzgbVarZza = zza(str, bArr);
        int i = 0;
        if (zzgbVarZza == null) {
            return false;
        }
        zza(str, zzgbVarZza);
        this.zzaos.put(str, zzgbVarZza);
        this.zzaou.put(str, str2);
        this.zzaop.put(str, zza(zzgbVarZza));
        zzj zzjVarZzjp = zzjp();
        zzfu[] zzfuVarArr = zzgbVarZza.zzawi;
        Preconditions.checkNotNull(zzfuVarArr);
        int length = zzfuVarArr.length;
        int i2 = 0;
        while (i2 < length) {
            zzfu zzfuVar = zzfuVarArr[i2];
            zzfv[] zzfvVarArr = zzfuVar.zzava;
            int length2 = zzfvVarArr.length;
            int i3 = i;
            while (i3 < length2) {
                zzfv zzfvVar = zzfvVarArr[i3];
                String strZzal = AppMeasurement.Event.zzal(zzfvVar.zzavf);
                if (strZzal != null) {
                    zzfvVar.zzavf = strZzal;
                }
                zzfw[] zzfwVarArr = zzfvVar.zzavg;
                int length3 = zzfwVarArr.length;
                int i4 = i;
                while (i4 < length3) {
                    zzfw zzfwVar = zzfwVarArr[i4];
                    int i5 = length;
                    String strZzal2 = AppMeasurement.Param.zzal(zzfwVar.zzavn);
                    if (strZzal2 != null) {
                        zzfwVar.zzavn = strZzal2;
                    }
                    i4++;
                    length = i5;
                }
                i3++;
                i = 0;
            }
            int i6 = length;
            zzfy[] zzfyVarArr = zzfuVar.zzauz;
            for (zzfy zzfyVar : zzfyVarArr) {
                String strZzal3 = AppMeasurement.UserProperty.zzal(zzfyVar.zzavu);
                if (strZzal3 != null) {
                    zzfyVar.zzavu = strZzal3;
                }
            }
            i2++;
            length = i6;
            i = 0;
        }
        zzjVarZzjp.zzjq().zza(str, zzfuVarArr);
        try {
            zzgbVarZza.zzawi = null;
            bArr2 = new byte[zzgbVarZza.zzvu()];
            zzgbVarZza.zza(zzyy.zzk(bArr2, 0, bArr2.length));
        } catch (IOException e) {
            zzgo().zzjg().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzap.zzbv(str), e);
            bArr2 = bArr;
        }
        zzq zzqVarZzjq = zzjq();
        Preconditions.checkNotEmpty(str);
        zzqVarZzjq.zzaf();
        zzqVarZzjq.zzcl();
        new ContentValues().put("remote_config", bArr2);
        try {
            if (zzqVarZzjq.getWritableDatabase().update("apps", r5, "app_id = ?", new String[]{str}) == 0) {
                zzqVarZzjq.zzgo().zzjd().zzg("Failed to update remote config (got 0). appId", zzap.zzbv(str));
            }
        } catch (SQLiteException e2) {
            zzqVarZzjq.zzgo().zzjd().zze("Error storing remote config. appId", zzap.zzbv(str), e2);
        }
        return true;
    }

    @WorkerThread
    final boolean zzo(String str, String str2) {
        Boolean bool;
        zzaf();
        zzce(str);
        if (zzck(str) && zzfk.zzcv(str2)) {
            return true;
        }
        if (zzcl(str) && zzfk.zzcq(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zzaoq.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @WorkerThread
    final boolean zzp(String str, String str2) {
        Boolean bool;
        zzaf();
        zzce(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zzaor.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @WorkerThread
    final int zzq(String str, String str2) {
        Integer num;
        zzaf();
        zzce(str);
        Map<String, Integer> map = this.zzaot.get(str);
        if (map == null || (num = map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    @WorkerThread
    final long zzcj(String str) {
        String strZzf = zzf(str, "measurement.account.time_zone_offset_minutes");
        if (!TextUtils.isEmpty(strZzf)) {
            try {
                return Long.parseLong(strZzf);
            } catch (NumberFormatException e) {
                zzgo().zzjg().zze("Unable to parse timezone offset. appId", zzap.zzbv(str), e);
                return 0L;
            }
        }
        return 0L;
    }

    @WorkerThread
    private final zzgb zza(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzgb();
        }
        zzyx zzyxVarZzj = zzyx.zzj(bArr, 0, bArr.length);
        zzgb zzgbVar = new zzgb();
        try {
            zzgbVar.zza(zzyxVarZzj);
            zzgo().zzjl().zze("Parsed config. version, gmp_app_id", zzgbVar.zzawe, zzgbVar.zzafx);
            return zzgbVar;
        } catch (IOException e) {
            zzgo().zzjg().zze("Unable to merge remote config. appId", zzap.zzbv(str), e);
            return new zzgb();
        }
    }

    final boolean zzck(String str) {
        return "1".equals(zzf(str, "measurement.upload.blacklist_internal"));
    }

    final boolean zzcl(String str) {
        return "1".equals(zzf(str, "measurement.upload.blacklist_public"));
    }

    @Override // com.google.android.gms.measurement.internal.zzez
    protected final boolean zzgt() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzey
    public final /* bridge */ /* synthetic */ zzfg zzjo() {
        return super.zzjo();
    }

    @Override // com.google.android.gms.measurement.internal.zzey
    public final /* bridge */ /* synthetic */ zzj zzjp() {
        return super.zzjp();
    }

    @Override // com.google.android.gms.measurement.internal.zzey
    public final /* bridge */ /* synthetic */ zzq zzjq() {
        return super.zzjq();
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
