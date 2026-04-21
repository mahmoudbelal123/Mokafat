package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzap extends zzcp {
    private long zzadt;
    private char zzalw;

    @GuardedBy("this")
    private String zzalx;
    private final zzar zzaly;
    private final zzar zzalz;
    private final zzar zzama;
    private final zzar zzamb;
    private final zzar zzamc;
    private final zzar zzamd;
    private final zzar zzame;
    private final zzar zzamf;
    private final zzar zzamg;

    zzap(zzbt zzbtVar) {
        super(zzbtVar);
        this.zzalw = (char) 0;
        this.zzadt = -1L;
        this.zzaly = new zzar(this, 6, false, false);
        this.zzalz = new zzar(this, 6, true, false);
        this.zzama = new zzar(this, 6, false, true);
        this.zzamb = new zzar(this, 5, false, false);
        this.zzamc = new zzar(this, 5, true, false);
        this.zzamd = new zzar(this, 5, false, true);
        this.zzame = new zzar(this, 4, false, false);
        this.zzamf = new zzar(this, 3, false, false);
        this.zzamg = new zzar(this, 2, false, false);
    }

    public final zzar zzjd() {
        return this.zzaly;
    }

    public final zzar zzje() {
        return this.zzalz;
    }

    public final zzar zzjf() {
        return this.zzama;
    }

    public final zzar zzjg() {
        return this.zzamb;
    }

    public final zzar zzjh() {
        return this.zzamc;
    }

    public final zzar zzji() {
        return this.zzamd;
    }

    public final zzar zzjj() {
        return this.zzame;
    }

    public final zzar zzjk() {
        return this.zzamf;
    }

    public final zzar zzjl() {
        return this.zzamg;
    }

    @Override // com.google.android.gms.measurement.internal.zzcp
    protected final boolean zzgt() {
        return false;
    }

    protected static Object zzbv(String str) {
        if (str == null) {
            return null;
        }
        return new zzas(str);
    }

    protected final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && isLoggable(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzbo zzboVarZzkh = this.zzadj.zzkh();
            if (zzboVarZzkh == null) {
                zza(6, "Scheduler not set. Not logging error/warn");
                return;
            }
            if (!zzboVarZzkh.isInitialized()) {
                zza(6, "Scheduler not initialized. Not logging error/warn");
                return;
            }
            if (i < 0) {
                i = 0;
            }
            if (i >= 9) {
                i = 8;
            }
            zzboVarZzkh.zzc(new zzaq(this, i, str, obj, obj2, obj3));
        }
    }

    @VisibleForTesting
    protected final boolean isLoggable(int i) {
        return Log.isLoggable(zzjm(), i);
    }

    @VisibleForTesting
    protected final void zza(int i, String str) {
        Log.println(i, zzjm(), str);
    }

    @VisibleForTesting
    private final String zzjm() {
        String str;
        synchronized (this) {
            if (this.zzalx == null) {
                if (this.zzadj.zzkm() != null) {
                    this.zzalx = this.zzadj.zzkm();
                } else {
                    this.zzalx = zzn.zzht();
                }
            }
            str = this.zzalx;
        }
        return str;
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String strZza = zza(z, obj);
        String strZza2 = zza(z, obj2);
        String strZza3 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(strZza)) {
            sb.append(str2);
            sb.append(strZza);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZza2)) {
            sb.append(str2);
            sb.append(strZza2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZza3)) {
            sb.append(str2);
            sb.append(strZza3);
        }
        return sb.toString();
    }

    @VisibleForTesting
    private static String zza(boolean z, Object obj) {
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
            String strValueOf = String.valueOf(Math.abs(l.longValue()));
            long jRound = Math.round(Math.pow(10.0d, strValueOf.length() - 1));
            long jRound2 = Math.round(Math.pow(10.0d, strValueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(43 + String.valueOf(str).length() + String.valueOf(str).length());
            sb.append(str);
            sb.append(jRound);
            sb.append("...");
            sb.append(str);
            sb.append(jRound2);
            return sb.toString();
        }
        if (obj instanceof Boolean) {
            return String.valueOf(obj);
        }
        if (obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String strZzbw = zzbw(AppMeasurement.class.getCanonicalName());
            String strZzbw2 = zzbw(zzbt.class.getCanonicalName());
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null) {
                    String strZzbw3 = zzbw(className);
                    if (strZzbw3.equals(strZzbw) || strZzbw3.equals(strZzbw2)) {
                        sb2.append(": ");
                        sb2.append(stackTraceElement);
                        break;
                    }
                }
            }
            return sb2.toString();
        }
        if (obj instanceof zzas) {
            return ((zzas) obj).zzamp;
        }
        if (z) {
            return "-";
        }
        return String.valueOf(obj);
    }

    private static String zzbw(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf == -1) {
            return str;
        }
        return str.substring(0, iLastIndexOf);
    }

    public final String zzjn() {
        Pair<String, Long> pairZzfm = zzgp().zzand.zzfm();
        if (pairZzfm == null || pairZzfm == zzba.zzanc) {
            return null;
        }
        String strValueOf = String.valueOf(pairZzfm.second);
        String str = (String) pairZzfm.first;
        StringBuilder sb = new StringBuilder(1 + String.valueOf(strValueOf).length() + String.valueOf(str).length());
        sb.append(strValueOf);
        sb.append(":");
        sb.append(str);
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
