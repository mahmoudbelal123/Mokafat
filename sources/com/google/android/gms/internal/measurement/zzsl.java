package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzsl<T> {
    private final zzsv zzbrb;
    final String zzbrc;
    private final String zzbrd;
    private final T zzbre;
    private T zzbrf;
    private volatile zzsi zzbrg;
    private volatile SharedPreferences zzbrh;
    private static final Object zzbqy = new Object();

    @SuppressLint({"StaticFieldLeak"})
    private static Context zzri = null;
    private static boolean zzbqz = false;
    private static volatile Boolean zzbra = null;

    public static void init(Context context) {
        Context applicationContext;
        synchronized (zzbqy) {
            if ((Build.VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) && (applicationContext = context.getApplicationContext()) != null) {
                context = applicationContext;
            }
            if (zzri != context) {
                zzbra = null;
            }
            zzri = context;
        }
        zzbqz = false;
    }

    protected abstract T zzfj(String str);

    private zzsl(zzsv zzsvVar, String str, T t) {
        this.zzbrf = null;
        this.zzbrg = null;
        this.zzbrh = null;
        if (zzsvVar.zzbrn == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzbrb = zzsvVar;
        String strValueOf = String.valueOf(zzsvVar.zzbro);
        String strValueOf2 = String.valueOf(str);
        this.zzbrd = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        String strValueOf3 = String.valueOf(zzsvVar.zzbrp);
        String strValueOf4 = String.valueOf(str);
        this.zzbrc = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
        this.zzbre = t;
    }

    public final T getDefaultValue() {
        return this.zzbre;
    }

    public final T get() {
        if (zzri == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        zzsv zzsvVar = this.zzbrb;
        T tZzte = zzte();
        if (tZzte != null) {
            return tZzte;
        }
        T tZztf = zztf();
        if (tZztf != null) {
            return tZztf;
        }
        return this.zzbre;
    }

    @Nullable
    @TargetApi(24)
    private final T zzte() {
        String str;
        zzsv zzsvVar = this.zzbrb;
        if (!zzd("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            if (this.zzbrb.zzbrn != null) {
                final zzsi zzsiVarZztg = zztg();
                if (zzsiVarZztg != null && (str = (String) zza(new zzsu(this, zzsiVarZztg) { // from class: com.google.android.gms.internal.measurement.zzsm
                    private final zzsl zzbri;
                    private final zzsi zzbrj;

                    {
                        this.zzbri = this;
                        this.zzbrj = zzsiVarZztg;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzsu
                    public final Object zztj() {
                        return this.zzbrj.zzsz().get(this.zzbri.zzbrc);
                    }
                })) != null) {
                    return zzfj(str);
                }
                return null;
            }
            zzsv zzsvVar2 = this.zzbrb;
            return null;
        }
        String strValueOf = String.valueOf(this.zzbrc);
        Log.w("PhenotypeFlag", strValueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(strValueOf) : new String("Bypass reading Phenotype values for flag: "));
        return null;
    }

    @Nullable
    private final T zztf() {
        zzsv zzsvVar = this.zzbrb;
        if (zzth()) {
            try {
                String str = (String) zza(new zzsu(this) { // from class: com.google.android.gms.internal.measurement.zzsn
                    private final zzsl zzbri;

                    {
                        this.zzbri = this;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzsu
                    public final Object zztj() {
                        return this.zzbri.zzti();
                    }
                });
                if (str != null) {
                    return zzfj(str);
                }
                return null;
            } catch (SecurityException e) {
                String strValueOf = String.valueOf(this.zzbrc);
                Log.e("PhenotypeFlag", strValueOf.length() != 0 ? "Unable to read GServices for flag: ".concat(strValueOf) : new String("Unable to read GServices for flag: "), e);
                return null;
            }
        }
        return null;
    }

    private static <V> V zza(zzsu<V> zzsuVar) {
        try {
            return zzsuVar.zztj();
        } catch (SecurityException e) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return zzsuVar.zztj();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    private final zzsi zztg() {
        if (this.zzbrg == null) {
            try {
                this.zzbrg = zzsi.zza(zzri.getContentResolver(), this.zzbrb.zzbrn);
            } catch (SecurityException e) {
            }
        }
        return this.zzbrg;
    }

    static boolean zzd(final String str, boolean z) {
        final boolean z2 = false;
        try {
            if (!zzth()) {
                return false;
            }
            return ((Boolean) zza(new zzsu(str, z2) { // from class: com.google.android.gms.internal.measurement.zzso
                private final String zzbrk;
                private final boolean zzbrl = false;

                {
                    this.zzbrk = str;
                }

                @Override // com.google.android.gms.internal.measurement.zzsu
                public final Object zztj() {
                    return Boolean.valueOf(zzsg.zza(zzsl.zzri.getContentResolver(), this.zzbrk, this.zzbrl));
                }
            })).booleanValue();
        } catch (SecurityException e) {
            Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", e);
            return false;
        }
    }

    private static boolean zzth() {
        if (zzbra == null) {
            if (zzri == null) {
                return false;
            }
            zzbra = Boolean.valueOf(PermissionChecker.checkSelfPermission(zzri, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return zzbra.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzsl<Long> zza(zzsv zzsvVar, String str, long j) {
        return new zzsp(zzsvVar, str, Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzsl<Integer> zza(zzsv zzsvVar, String str, int i) {
        return new zzsq(zzsvVar, str, Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzsl<Boolean> zza(zzsv zzsvVar, String str, boolean z) {
        return new zzsr(zzsvVar, str, Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzsl<Double> zza(zzsv zzsvVar, String str, double d) {
        return new zzss(zzsvVar, str, Double.valueOf(d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzsl<String> zza(zzsv zzsvVar, String str, String str2) {
        return new zzst(zzsvVar, str, str2);
    }

    final /* synthetic */ String zzti() {
        return zzsg.zza(zzri.getContentResolver(), this.zzbrd, (String) null);
    }

    /* synthetic */ zzsl(zzsv zzsvVar, String str, Object obj, zzsp zzspVar) {
        this(zzsvVar, str, obj);
    }
}
