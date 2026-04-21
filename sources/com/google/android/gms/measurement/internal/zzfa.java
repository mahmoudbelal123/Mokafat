package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class zzfa implements zzcq {
    private static volatile zzfa zzatc;
    private final zzbt zzadj;
    private zzbn zzatd;
    private zzat zzate;
    private zzq zzatf;
    private zzay zzatg;
    private zzew zzath;
    private zzj zzati;
    private final zzfg zzatj;
    private boolean zzatk;

    @VisibleForTesting
    private long zzatl;
    private List<Runnable> zzatm;
    private int zzatn;
    private int zzato;
    private boolean zzatp;
    private boolean zzatq;
    private boolean zzatr;
    private FileLock zzats;
    private FileChannel zzatt;
    private List<Long> zzatu;
    private List<Long> zzatv;
    private long zzatw;
    private boolean zzvz;

    class zza implements zzs {
        zzgi zzaua;
        List<Long> zzaub;
        List<zzgf> zzauc;
        private long zzaud;

        private zza() {
        }

        @Override // com.google.android.gms.measurement.internal.zzs
        public final void zzb(zzgi zzgiVar) {
            Preconditions.checkNotNull(zzgiVar);
            this.zzaua = zzgiVar;
        }

        @Override // com.google.android.gms.measurement.internal.zzs
        public final boolean zza(long j, zzgf zzgfVar) {
            Preconditions.checkNotNull(zzgfVar);
            if (this.zzauc == null) {
                this.zzauc = new ArrayList();
            }
            if (this.zzaub == null) {
                this.zzaub = new ArrayList();
            }
            if (this.zzauc.size() > 0 && zza(this.zzauc.get(0)) != zza(zzgfVar)) {
                return false;
            }
            long jZzvu = this.zzaud + ((long) zzgfVar.zzvu());
            if (jZzvu >= Math.max(0, zzaf.zzajl.get().intValue())) {
                return false;
            }
            this.zzaud = jZzvu;
            this.zzauc.add(zzgfVar);
            this.zzaub.add(Long.valueOf(j));
            return this.zzauc.size() < Math.max(1, zzaf.zzajm.get().intValue());
        }

        private static long zza(zzgf zzgfVar) {
            return ((zzgfVar.zzawu.longValue() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzfa zzfaVar, zzfb zzfbVar) {
            this();
        }
    }

    public static zzfa zzm(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzatc == null) {
            synchronized (zzfa.class) {
                if (zzatc == null) {
                    zzatc = new zzfa(new zzff(context));
                }
            }
        }
        return zzatc;
    }

    private zzfa(zzff zzffVar) {
        this(zzffVar, null);
    }

    private zzfa(zzff zzffVar, zzbt zzbtVar) {
        this.zzvz = false;
        Preconditions.checkNotNull(zzffVar);
        this.zzadj = zzbt.zza(zzffVar.zzri, (zzak) null);
        this.zzatw = -1L;
        zzfg zzfgVar = new zzfg(this);
        zzfgVar.zzq();
        this.zzatj = zzfgVar;
        zzat zzatVar = new zzat(this);
        zzatVar.zzq();
        this.zzate = zzatVar;
        zzbn zzbnVar = new zzbn(this);
        zzbnVar.zzq();
        this.zzatd = zzbnVar;
        this.zzadj.zzgn().zzc(new zzfb(this, zzffVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzff zzffVar) {
        this.zzadj.zzgn().zzaf();
        zzq zzqVar = new zzq(this);
        zzqVar.zzq();
        this.zzatf = zzqVar;
        this.zzadj.zzgq().zza(this.zzatd);
        zzj zzjVar = new zzj(this);
        zzjVar.zzq();
        this.zzati = zzjVar;
        zzew zzewVar = new zzew(this);
        zzewVar.zzq();
        this.zzath = zzewVar;
        this.zzatg = new zzay(this);
        if (this.zzatn != this.zzato) {
            this.zzadj.zzgo().zzjd().zze("Not all upload components initialized", Integer.valueOf(this.zzatn), Integer.valueOf(this.zzato));
        }
        this.zzvz = true;
    }

    @WorkerThread
    protected final void start() {
        this.zzadj.zzgn().zzaf();
        zzjq().zzif();
        if (this.zzadj.zzgp().zzane.get() == 0) {
            this.zzadj.zzgp().zzane.set(this.zzadj.zzbx().currentTimeMillis());
        }
        zzlv();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzk zzgr() {
        return this.zzadj.zzgr();
    }

    public final zzn zzgq() {
        return this.zzadj.zzgq();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzap zzgo() {
        return this.zzadj.zzgo();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzbo zzgn() {
        return this.zzadj.zzgn();
    }

    private final zzbn zzln() {
        zza(this.zzatd);
        return this.zzatd;
    }

    public final zzat zzlo() {
        zza(this.zzate);
        return this.zzate;
    }

    public final zzq zzjq() {
        zza(this.zzatf);
        return this.zzatf;
    }

    private final zzay zzlp() {
        if (this.zzatg == null) {
            throw new IllegalStateException("Network broadcast receiver not created");
        }
        return this.zzatg;
    }

    private final zzew zzlq() {
        zza(this.zzath);
        return this.zzath;
    }

    public final zzj zzjp() {
        zza(this.zzati);
        return this.zzati;
    }

    public final zzfg zzjo() {
        zza(this.zzatj);
        return this.zzatj;
    }

    public final zzan zzgl() {
        return this.zzadj.zzgl();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final Context getContext() {
        return this.zzadj.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final Clock zzbx() {
        return this.zzadj.zzbx();
    }

    public final zzfk zzgm() {
        return this.zzadj.zzgm();
    }

    @WorkerThread
    private final void zzaf() {
        this.zzadj.zzgn().zzaf();
    }

    final void zzlr() {
        if (!this.zzvz) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zza(zzez zzezVar) {
        if (zzezVar == null) {
            throw new IllegalStateException("Upload Component not created");
        }
        if (!zzezVar.isInitialized()) {
            String strValueOf = String.valueOf(zzezVar.getClass());
            StringBuilder sb = new StringBuilder(27 + String.valueOf(strValueOf).length());
            sb.append("Component not initialized: ");
            sb.append(strValueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    final void zze(zzh zzhVar) {
        zzaf();
        zzlr();
        Preconditions.checkNotEmpty(zzhVar.packageName);
        zzg(zzhVar);
    }

    private final long zzls() {
        long jCurrentTimeMillis = this.zzadj.zzbx().currentTimeMillis();
        zzba zzbaVarZzgp = this.zzadj.zzgp();
        zzbaVarZzgp.zzcl();
        zzbaVarZzgp.zzaf();
        long j = zzbaVarZzgp.zzani.get();
        if (j == 0) {
            long jNextInt = 1 + ((long) zzbaVarZzgp.zzgm().zzmd().nextInt(86400000));
            zzbaVarZzgp.zzani.set(jNextInt);
            j = jNextInt;
        }
        return ((((jCurrentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    @WorkerThread
    final void zzc(zzad zzadVar, String str) {
        zzg zzgVarZzbl = zzjq().zzbl(str);
        if (zzgVarZzbl == null || TextUtils.isEmpty(zzgVarZzbl.zzak())) {
            this.zzadj.zzgo().zzjk().zzg("No app data available; dropping event", str);
            return;
        }
        Boolean boolZzc = zzc(zzgVarZzbl);
        if (boolZzc == null) {
            if (!"_ui".equals(zzadVar.name)) {
                this.zzadj.zzgo().zzjg().zzg("Could not find package. appId", zzap.zzbv(str));
            }
        } else if (!boolZzc.booleanValue()) {
            this.zzadj.zzgo().zzjd().zzg("App version does not match; dropping event. appId", zzap.zzbv(str));
            return;
        }
        zzc(zzadVar, new zzh(str, zzgVarZzbl.getGmpAppId(), zzgVarZzbl.zzak(), zzgVarZzbl.zzha(), zzgVarZzbl.zzhb(), zzgVarZzbl.zzhc(), zzgVarZzbl.zzhd(), (String) null, zzgVarZzbl.isMeasurementEnabled(), false, zzgVarZzbl.getFirebaseInstanceId(), zzgVarZzbl.zzhq(), 0L, 0, zzgVarZzbl.zzhr(), zzgVarZzbl.zzhs(), false, zzgVarZzbl.zzgw()));
    }

    @WorkerThread
    final void zzc(zzad zzadVar, zzh zzhVar) {
        List<zzl> listZzb;
        List<zzl> listZzb2;
        List<zzl> listZzb3;
        Preconditions.checkNotNull(zzhVar);
        Preconditions.checkNotEmpty(zzhVar.packageName);
        zzaf();
        zzlr();
        String str = zzhVar.packageName;
        long j = zzadVar.zzaip;
        if (!zzjo().zze(zzadVar, zzhVar)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
            return;
        }
        zzjq().beginTransaction();
        try {
            zzq zzqVarZzjq = zzjq();
            Preconditions.checkNotEmpty(str);
            zzqVarZzjq.zzaf();
            zzqVarZzjq.zzcl();
            if (j < 0) {
                zzqVarZzjq.zzgo().zzjg().zze("Invalid time querying timed out conditional properties", zzap.zzbv(str), Long.valueOf(j));
                listZzb = Collections.emptyList();
            } else {
                listZzb = zzqVarZzjq.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
            }
            for (zzl zzlVar : listZzb) {
                if (zzlVar != null) {
                    this.zzadj.zzgo().zzjk().zzd("User property timed out", zzlVar.packageName, this.zzadj.zzgl().zzbu(zzlVar.zzahb.name), zzlVar.zzahb.getValue());
                    if (zzlVar.zzahc != null) {
                        zzd(new zzad(zzlVar.zzahc, j), zzhVar);
                    }
                    zzjq().zzk(str, zzlVar.zzahb.name);
                }
            }
            zzq zzqVarZzjq2 = zzjq();
            Preconditions.checkNotEmpty(str);
            zzqVarZzjq2.zzaf();
            zzqVarZzjq2.zzcl();
            if (j < 0) {
                zzqVarZzjq2.zzgo().zzjg().zze("Invalid time querying expired conditional properties", zzap.zzbv(str), Long.valueOf(j));
                listZzb2 = Collections.emptyList();
            } else {
                listZzb2 = zzqVarZzjq2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
            }
            ArrayList arrayList = new ArrayList(listZzb2.size());
            for (zzl zzlVar2 : listZzb2) {
                if (zzlVar2 != null) {
                    this.zzadj.zzgo().zzjk().zzd("User property expired", zzlVar2.packageName, this.zzadj.zzgl().zzbu(zzlVar2.zzahb.name), zzlVar2.zzahb.getValue());
                    zzjq().zzh(str, zzlVar2.zzahb.name);
                    if (zzlVar2.zzahe != null) {
                        arrayList.add(zzlVar2.zzahe);
                    }
                    zzjq().zzk(str, zzlVar2.zzahb.name);
                }
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                zzd(new zzad((zzad) obj, j), zzhVar);
            }
            zzq zzqVarZzjq3 = zzjq();
            String str2 = zzadVar.name;
            Preconditions.checkNotEmpty(str);
            Preconditions.checkNotEmpty(str2);
            zzqVarZzjq3.zzaf();
            zzqVarZzjq3.zzcl();
            if (j < 0) {
                zzqVarZzjq3.zzgo().zzjg().zzd("Invalid time querying triggered conditional properties", zzap.zzbv(str), zzqVarZzjq3.zzgl().zzbs(str2), Long.valueOf(j));
                listZzb3 = Collections.emptyList();
            } else {
                listZzb3 = zzqVarZzjq3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
            }
            ArrayList arrayList3 = new ArrayList(listZzb3.size());
            Iterator<zzl> it = listZzb3.iterator();
            while (it.hasNext()) {
                zzl next = it.next();
                if (next != null) {
                    zzfh zzfhVar = next.zzahb;
                    Iterator<zzl> it2 = it;
                    zzfj zzfjVar = new zzfj(next.packageName, next.origin, zzfhVar.name, j, zzfhVar.getValue());
                    if (zzjq().zza(zzfjVar)) {
                        this.zzadj.zzgo().zzjk().zzd("User property triggered", next.packageName, this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                    } else {
                        this.zzadj.zzgo().zzjd().zzd("Too many active user properties, ignoring", zzap.zzbv(next.packageName), this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                    }
                    if (next.zzahd != null) {
                        arrayList3.add(next.zzahd);
                    }
                    next.zzahb = new zzfh(zzfjVar);
                    next.active = true;
                    zzjq().zza(next);
                    it = it2;
                }
            }
            zzd(zzadVar, zzhVar);
            ArrayList arrayList4 = arrayList3;
            int size2 = arrayList4.size();
            int i2 = 0;
            while (i2 < size2) {
                Object obj2 = arrayList4.get(i2);
                i2++;
                zzd(new zzad((zzad) obj2, j), zzhVar);
            }
            zzjq().setTransactionSuccessful();
        } finally {
            zzjq().endTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x05f5 A[Catch: all -> 0x066f, TryCatch #1 {all -> 0x066f, blocks: (B:33:0x010f, B:35:0x011c, B:42:0x013b, B:44:0x0182, B:46:0x0188, B:47:0x01a1, B:51:0x01b2, B:53:0x01c9, B:55:0x01d0, B:56:0x01e9, B:60:0x020c, B:64:0x0235, B:65:0x024e, B:68:0x025d, B:70:0x027f, B:71:0x029e, B:73:0x02ad, B:74:0x02c4, B:76:0x02e7, B:79:0x02f8, B:82:0x0335, B:84:0x035f, B:88:0x03b3, B:92:0x03d5, B:94:0x03e6, B:96:0x03f0, B:98:0x03f4, B:110:0x0463, B:112:0x04b6, B:114:0x04bc, B:115:0x04be, B:117:0x04ca, B:118:0x052e, B:119:0x054d, B:121:0x0553, B:123:0x0586, B:124:0x058f, B:126:0x0597, B:127:0x059d, B:129:0x05a3, B:139:0x05ef, B:141:0x05f5, B:144:0x0611, B:146:0x0627, B:133:0x05b3, B:135:0x05da, B:143:0x05f9, B:99:0x0401, B:101:0x0415, B:103:0x041a, B:105:0x042d, B:109:0x0461, B:106:0x0445, B:108:0x044c, B:91:0x03cf, B:87:0x03ac, B:83:0x0351, B:37:0x0126, B:39:0x012c), top: B:154:0x010f, inners: #0 }] */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzd(com.google.android.gms.measurement.internal.zzad r25, com.google.android.gms.measurement.internal.zzh r26) {
        /*
            Method dump skipped, instruction units count: 1657
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zzd(com.google.android.gms.measurement.internal.zzad, com.google.android.gms.measurement.internal.zzh):void");
    }

    private final boolean zza(String str, zzad zzadVar) {
        long jLongValue;
        zzfj zzfjVar;
        String string = zzadVar.zzaid.getString(FirebaseAnalytics.Param.CURRENCY);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzadVar.name)) {
            double dDoubleValue = zzadVar.zzaid.zzbq(FirebaseAnalytics.Param.VALUE).doubleValue() * 1000000.0d;
            if (dDoubleValue == 0.0d) {
                dDoubleValue = zzadVar.zzaid.getLong(FirebaseAnalytics.Param.VALUE).longValue() * 1000000.0d;
            }
            if (dDoubleValue <= 9.223372036854776E18d && dDoubleValue >= -9.223372036854776E18d) {
                jLongValue = Math.round(dDoubleValue);
            } else {
                this.zzadj.zzgo().zzjg().zze("Data lost. Currency value is too big. appId", zzap.zzbv(str), Double.valueOf(dDoubleValue));
                return false;
            }
        } else {
            jLongValue = zzadVar.zzaid.getLong(FirebaseAnalytics.Param.VALUE).longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String upperCase = string.toUpperCase(Locale.US);
            if (upperCase.matches("[A-Z]{3}")) {
                String strValueOf = String.valueOf("_ltv_");
                String strValueOf2 = String.valueOf(upperCase);
                String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                zzfj zzfjVarZzi = zzjq().zzi(str, strConcat);
                if (zzfjVarZzi == null || !(zzfjVarZzi.value instanceof Long)) {
                    zzq zzqVarZzjq = zzjq();
                    int iZzb = this.zzadj.zzgq().zzb(str, zzaf.zzakh) - 1;
                    Preconditions.checkNotEmpty(str);
                    zzqVarZzjq.zzaf();
                    zzqVarZzjq.zzcl();
                    try {
                        zzqVarZzjq.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(iZzb)});
                    } catch (SQLiteException e) {
                        zzqVarZzjq.zzgo().zzjd().zze("Error pruning currencies. appId", zzap.zzbv(str), e);
                    }
                    zzfjVar = new zzfj(str, zzadVar.origin, strConcat, this.zzadj.zzbx().currentTimeMillis(), Long.valueOf(jLongValue));
                } else {
                    zzfjVar = new zzfj(str, zzadVar.origin, strConcat, this.zzadj.zzbx().currentTimeMillis(), Long.valueOf(((Long) zzfjVarZzi.value).longValue() + jLongValue));
                }
                if (!zzjq().zza(zzfjVar)) {
                    this.zzadj.zzgo().zzjd().zzd("Too many unique user properties are set. Ignoring user property. appId", zzap.zzbv(str), this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                    this.zzadj.zzgm().zza(str, 9, (String) null, (String) null, 0);
                }
            }
        }
        return true;
    }

    @WorkerThread
    final void zzlt() {
        zzg zzgVarZzbl;
        String str;
        zzaf();
        zzlr();
        this.zzatr = true;
        try {
            this.zzadj.zzgr();
            Boolean boolZzle = this.zzadj.zzgg().zzle();
            if (boolZzle == null) {
                this.zzadj.zzgo().zzjg().zzbx("Upload data called on the client side before use of service was decided");
                return;
            }
            if (boolZzle.booleanValue()) {
                this.zzadj.zzgo().zzjd().zzbx("Upload called in the client side when service should be used");
                return;
            }
            if (this.zzatl > 0) {
                zzlv();
                return;
            }
            zzaf();
            if (this.zzatu != null) {
                this.zzadj.zzgo().zzjl().zzbx("Uploading requested multiple times");
                return;
            }
            if (!zzlo().zzfb()) {
                this.zzadj.zzgo().zzjl().zzbx("Network not connected, ignoring upload request");
                zzlv();
                return;
            }
            long jCurrentTimeMillis = this.zzadj.zzbx().currentTimeMillis();
            String strZzb = null;
            zzd((String) null, jCurrentTimeMillis - zzn.zzhx());
            long j = this.zzadj.zzgp().zzane.get();
            if (j != 0) {
                this.zzadj.zzgo().zzjk().zzg("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(jCurrentTimeMillis - j)));
            }
            String strZzid = zzjq().zzid();
            if (!TextUtils.isEmpty(strZzid)) {
                if (this.zzatw == -1) {
                    this.zzatw = zzjq().zzik();
                }
                List<Pair<zzgi, Long>> listZzb = zzjq().zzb(strZzid, this.zzadj.zzgq().zzb(strZzid, zzaf.zzajj), Math.max(0, this.zzadj.zzgq().zzb(strZzid, zzaf.zzajk)));
                if (!listZzb.isEmpty()) {
                    Iterator<Pair<zzgi, Long>> it = listZzb.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            zzgi zzgiVar = (zzgi) it.next().first;
                            if (!TextUtils.isEmpty(zzgiVar.zzaxo)) {
                                str = zzgiVar.zzaxo;
                                break;
                            }
                        } else {
                            str = null;
                            break;
                        }
                    }
                    if (str != null) {
                        int i = 0;
                        while (true) {
                            if (i >= listZzb.size()) {
                                break;
                            }
                            zzgi zzgiVar2 = (zzgi) listZzb.get(i).first;
                            if (!TextUtils.isEmpty(zzgiVar2.zzaxo) && !zzgiVar2.zzaxo.equals(str)) {
                                listZzb = listZzb.subList(0, i);
                                break;
                            }
                            i++;
                        }
                    }
                    zzgh zzghVar = new zzgh();
                    zzghVar.zzawy = new zzgi[listZzb.size()];
                    ArrayList arrayList = new ArrayList(listZzb.size());
                    boolean z = zzn.zzhz() && this.zzadj.zzgq().zzav(strZzid);
                    for (int i2 = 0; i2 < zzghVar.zzawy.length; i2++) {
                        zzghVar.zzawy[i2] = (zzgi) listZzb.get(i2).first;
                        arrayList.add((Long) listZzb.get(i2).second);
                        zzghVar.zzawy[i2].zzaxn = Long.valueOf(this.zzadj.zzgq().zzhc());
                        zzghVar.zzawy[i2].zzaxd = Long.valueOf(jCurrentTimeMillis);
                        zzgi zzgiVar3 = zzghVar.zzawy[i2];
                        this.zzadj.zzgr();
                        zzgiVar3.zzaxs = false;
                        if (!z) {
                            zzghVar.zzawy[i2].zzaya = null;
                        }
                    }
                    if (this.zzadj.zzgo().isLoggable(2)) {
                        strZzb = zzjo().zzb(zzghVar);
                    }
                    byte[] bArrZza = zzjo().zza(zzghVar);
                    String str2 = zzaf.zzajt.get();
                    try {
                        URL url = new URL(str2);
                        Preconditions.checkArgument(!arrayList.isEmpty());
                        if (this.zzatu != null) {
                            this.zzadj.zzgo().zzjd().zzbx("Set uploading progress before finishing the previous upload");
                        } else {
                            this.zzatu = new ArrayList(arrayList);
                        }
                        this.zzadj.zzgp().zzanf.set(jCurrentTimeMillis);
                        String str3 = "?";
                        if (zzghVar.zzawy.length > 0) {
                            str3 = zzghVar.zzawy[0].zztt;
                        }
                        this.zzadj.zzgo().zzjl().zzd("Uploading data. app, uncompressed size, data", str3, Integer.valueOf(bArrZza.length), strZzb);
                        this.zzatq = true;
                        zzat zzatVarZzlo = zzlo();
                        zzfc zzfcVar = new zzfc(this, strZzid);
                        zzatVarZzlo.zzaf();
                        zzatVarZzlo.zzcl();
                        Preconditions.checkNotNull(url);
                        Preconditions.checkNotNull(bArrZza);
                        Preconditions.checkNotNull(zzfcVar);
                        zzatVarZzlo.zzgn().zzd(new zzax(zzatVarZzlo, strZzid, url, bArrZza, null, zzfcVar));
                    } catch (MalformedURLException e) {
                        this.zzadj.zzgo().zzjd().zze("Failed to parse upload URL. Not uploading. appId", zzap.zzbv(strZzid), str2);
                    }
                }
            } else {
                this.zzatw = -1L;
                String strZzah = zzjq().zzah(jCurrentTimeMillis - zzn.zzhx());
                if (!TextUtils.isEmpty(strZzah) && (zzgVarZzbl = zzjq().zzbl(strZzah)) != null) {
                    zzb(zzgVarZzbl);
                }
            }
        } finally {
            this.zzatr = false;
            zzlw();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0298 A[Catch: all -> 0x0d64, TRY_ENTER, TryCatch #3 {all -> 0x0d64, blocks: (B:3:0x0009, B:26:0x008d, B:126:0x029b, B:128:0x02a0, B:134:0x02b0, B:135:0x02d0, B:137:0x02d9, B:139:0x02f2, B:141:0x0324, B:147:0x0339, B:149:0x0344, B:292:0x070b, B:151:0x0362, B:153:0x0373, B:170:0x03ae, B:235:0x05db, B:238:0x05ed, B:239:0x05f4, B:241:0x05f7, B:247:0x0614, B:244:0x0606, B:250:0x061a, B:252:0x0620, B:254:0x0627, B:275:0x0681, B:276:0x06a2, B:279:0x06a9, B:281:0x06b3, B:283:0x06b7, B:286:0x06bd, B:288:0x06cc, B:289:0x06e4, B:290:0x06ec, B:291:0x0704, B:259:0x0653, B:261:0x0659, B:265:0x0662, B:267:0x0668, B:270:0x0674, B:160:0x038f, B:163:0x0399, B:166:0x03a3, B:176:0x03bd, B:178:0x03c3, B:179:0x03c8, B:181:0x03d2, B:183:0x03e2, B:187:0x0407, B:184:0x03f0, B:186:0x03fc, B:191:0x0417, B:193:0x045a, B:194:0x049a, B:197:0x04ce, B:199:0x04d3, B:201:0x04e1, B:203:0x04ea, B:204:0x04f0, B:206:0x04f3, B:207:0x04fc, B:208:0x04ff, B:211:0x0507, B:214:0x0512, B:216:0x0548, B:218:0x0569, B:224:0x058a, B:221:0x057e, B:228:0x0599, B:230:0x05ac, B:231:0x05b9, B:293:0x0712, B:295:0x071c, B:297:0x0729, B:299:0x0737, B:302:0x073c, B:54:0x0142, B:78:0x01e1, B:86:0x0219, B:93:0x0239, B:125:0x0298, B:103:0x025c, B:45:0x00f2, B:61:0x0161), top: B:521:0x0009, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02a0 A[Catch: all -> 0x0d64, TryCatch #3 {all -> 0x0d64, blocks: (B:3:0x0009, B:26:0x008d, B:126:0x029b, B:128:0x02a0, B:134:0x02b0, B:135:0x02d0, B:137:0x02d9, B:139:0x02f2, B:141:0x0324, B:147:0x0339, B:149:0x0344, B:292:0x070b, B:151:0x0362, B:153:0x0373, B:170:0x03ae, B:235:0x05db, B:238:0x05ed, B:239:0x05f4, B:241:0x05f7, B:247:0x0614, B:244:0x0606, B:250:0x061a, B:252:0x0620, B:254:0x0627, B:275:0x0681, B:276:0x06a2, B:279:0x06a9, B:281:0x06b3, B:283:0x06b7, B:286:0x06bd, B:288:0x06cc, B:289:0x06e4, B:290:0x06ec, B:291:0x0704, B:259:0x0653, B:261:0x0659, B:265:0x0662, B:267:0x0668, B:270:0x0674, B:160:0x038f, B:163:0x0399, B:166:0x03a3, B:176:0x03bd, B:178:0x03c3, B:179:0x03c8, B:181:0x03d2, B:183:0x03e2, B:187:0x0407, B:184:0x03f0, B:186:0x03fc, B:191:0x0417, B:193:0x045a, B:194:0x049a, B:197:0x04ce, B:199:0x04d3, B:201:0x04e1, B:203:0x04ea, B:204:0x04f0, B:206:0x04f3, B:207:0x04fc, B:208:0x04ff, B:211:0x0507, B:214:0x0512, B:216:0x0548, B:218:0x0569, B:224:0x058a, B:221:0x057e, B:228:0x0599, B:230:0x05ac, B:231:0x05b9, B:293:0x0712, B:295:0x071c, B:297:0x0729, B:299:0x0737, B:302:0x073c, B:54:0x0142, B:78:0x01e1, B:86:0x0219, B:93:0x0239, B:125:0x0298, B:103:0x025c, B:45:0x00f2, B:61:0x0161), top: B:521:0x0009, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02b0 A[Catch: all -> 0x0d64, TryCatch #3 {all -> 0x0d64, blocks: (B:3:0x0009, B:26:0x008d, B:126:0x029b, B:128:0x02a0, B:134:0x02b0, B:135:0x02d0, B:137:0x02d9, B:139:0x02f2, B:141:0x0324, B:147:0x0339, B:149:0x0344, B:292:0x070b, B:151:0x0362, B:153:0x0373, B:170:0x03ae, B:235:0x05db, B:238:0x05ed, B:239:0x05f4, B:241:0x05f7, B:247:0x0614, B:244:0x0606, B:250:0x061a, B:252:0x0620, B:254:0x0627, B:275:0x0681, B:276:0x06a2, B:279:0x06a9, B:281:0x06b3, B:283:0x06b7, B:286:0x06bd, B:288:0x06cc, B:289:0x06e4, B:290:0x06ec, B:291:0x0704, B:259:0x0653, B:261:0x0659, B:265:0x0662, B:267:0x0668, B:270:0x0674, B:160:0x038f, B:163:0x0399, B:166:0x03a3, B:176:0x03bd, B:178:0x03c3, B:179:0x03c8, B:181:0x03d2, B:183:0x03e2, B:187:0x0407, B:184:0x03f0, B:186:0x03fc, B:191:0x0417, B:193:0x045a, B:194:0x049a, B:197:0x04ce, B:199:0x04d3, B:201:0x04e1, B:203:0x04ea, B:204:0x04f0, B:206:0x04f3, B:207:0x04fc, B:208:0x04ff, B:211:0x0507, B:214:0x0512, B:216:0x0548, B:218:0x0569, B:224:0x058a, B:221:0x057e, B:228:0x0599, B:230:0x05ac, B:231:0x05b9, B:293:0x0712, B:295:0x071c, B:297:0x0729, B:299:0x0737, B:302:0x073c, B:54:0x0142, B:78:0x01e1, B:86:0x0219, B:93:0x0239, B:125:0x0298, B:103:0x025c, B:45:0x00f2, B:61:0x0161), top: B:521:0x0009, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03c3 A[Catch: all -> 0x0d64, TryCatch #3 {all -> 0x0d64, blocks: (B:3:0x0009, B:26:0x008d, B:126:0x029b, B:128:0x02a0, B:134:0x02b0, B:135:0x02d0, B:137:0x02d9, B:139:0x02f2, B:141:0x0324, B:147:0x0339, B:149:0x0344, B:292:0x070b, B:151:0x0362, B:153:0x0373, B:170:0x03ae, B:235:0x05db, B:238:0x05ed, B:239:0x05f4, B:241:0x05f7, B:247:0x0614, B:244:0x0606, B:250:0x061a, B:252:0x0620, B:254:0x0627, B:275:0x0681, B:276:0x06a2, B:279:0x06a9, B:281:0x06b3, B:283:0x06b7, B:286:0x06bd, B:288:0x06cc, B:289:0x06e4, B:290:0x06ec, B:291:0x0704, B:259:0x0653, B:261:0x0659, B:265:0x0662, B:267:0x0668, B:270:0x0674, B:160:0x038f, B:163:0x0399, B:166:0x03a3, B:176:0x03bd, B:178:0x03c3, B:179:0x03c8, B:181:0x03d2, B:183:0x03e2, B:187:0x0407, B:184:0x03f0, B:186:0x03fc, B:191:0x0417, B:193:0x045a, B:194:0x049a, B:197:0x04ce, B:199:0x04d3, B:201:0x04e1, B:203:0x04ea, B:204:0x04f0, B:206:0x04f3, B:207:0x04fc, B:208:0x04ff, B:211:0x0507, B:214:0x0512, B:216:0x0548, B:218:0x0569, B:224:0x058a, B:221:0x057e, B:228:0x0599, B:230:0x05ac, B:231:0x05b9, B:293:0x0712, B:295:0x071c, B:297:0x0729, B:299:0x0737, B:302:0x073c, B:54:0x0142, B:78:0x01e1, B:86:0x0219, B:93:0x0239, B:125:0x0298, B:103:0x025c, B:45:0x00f2, B:61:0x0161), top: B:521:0x0009, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03d2 A[Catch: all -> 0x0d64, TryCatch #3 {all -> 0x0d64, blocks: (B:3:0x0009, B:26:0x008d, B:126:0x029b, B:128:0x02a0, B:134:0x02b0, B:135:0x02d0, B:137:0x02d9, B:139:0x02f2, B:141:0x0324, B:147:0x0339, B:149:0x0344, B:292:0x070b, B:151:0x0362, B:153:0x0373, B:170:0x03ae, B:235:0x05db, B:238:0x05ed, B:239:0x05f4, B:241:0x05f7, B:247:0x0614, B:244:0x0606, B:250:0x061a, B:252:0x0620, B:254:0x0627, B:275:0x0681, B:276:0x06a2, B:279:0x06a9, B:281:0x06b3, B:283:0x06b7, B:286:0x06bd, B:288:0x06cc, B:289:0x06e4, B:290:0x06ec, B:291:0x0704, B:259:0x0653, B:261:0x0659, B:265:0x0662, B:267:0x0668, B:270:0x0674, B:160:0x038f, B:163:0x0399, B:166:0x03a3, B:176:0x03bd, B:178:0x03c3, B:179:0x03c8, B:181:0x03d2, B:183:0x03e2, B:187:0x0407, B:184:0x03f0, B:186:0x03fc, B:191:0x0417, B:193:0x045a, B:194:0x049a, B:197:0x04ce, B:199:0x04d3, B:201:0x04e1, B:203:0x04ea, B:204:0x04f0, B:206:0x04f3, B:207:0x04fc, B:208:0x04ff, B:211:0x0507, B:214:0x0512, B:216:0x0548, B:218:0x0569, B:224:0x058a, B:221:0x057e, B:228:0x0599, B:230:0x05ac, B:231:0x05b9, B:293:0x0712, B:295:0x071c, B:297:0x0729, B:299:0x0737, B:302:0x073c, B:54:0x0142, B:78:0x01e1, B:86:0x0219, B:93:0x0239, B:125:0x0298, B:103:0x025c, B:45:0x00f2, B:61:0x0161), top: B:521:0x0009, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x045a A[Catch: all -> 0x0d64, TryCatch #3 {all -> 0x0d64, blocks: (B:3:0x0009, B:26:0x008d, B:126:0x029b, B:128:0x02a0, B:134:0x02b0, B:135:0x02d0, B:137:0x02d9, B:139:0x02f2, B:141:0x0324, B:147:0x0339, B:149:0x0344, B:292:0x070b, B:151:0x0362, B:153:0x0373, B:170:0x03ae, B:235:0x05db, B:238:0x05ed, B:239:0x05f4, B:241:0x05f7, B:247:0x0614, B:244:0x0606, B:250:0x061a, B:252:0x0620, B:254:0x0627, B:275:0x0681, B:276:0x06a2, B:279:0x06a9, B:281:0x06b3, B:283:0x06b7, B:286:0x06bd, B:288:0x06cc, B:289:0x06e4, B:290:0x06ec, B:291:0x0704, B:259:0x0653, B:261:0x0659, B:265:0x0662, B:267:0x0668, B:270:0x0674, B:160:0x038f, B:163:0x0399, B:166:0x03a3, B:176:0x03bd, B:178:0x03c3, B:179:0x03c8, B:181:0x03d2, B:183:0x03e2, B:187:0x0407, B:184:0x03f0, B:186:0x03fc, B:191:0x0417, B:193:0x045a, B:194:0x049a, B:197:0x04ce, B:199:0x04d3, B:201:0x04e1, B:203:0x04ea, B:204:0x04f0, B:206:0x04f3, B:207:0x04fc, B:208:0x04ff, B:211:0x0507, B:214:0x0512, B:216:0x0548, B:218:0x0569, B:224:0x058a, B:221:0x057e, B:228:0x0599, B:230:0x05ac, B:231:0x05b9, B:293:0x0712, B:295:0x071c, B:297:0x0729, B:299:0x0737, B:302:0x073c, B:54:0x0142, B:78:0x01e1, B:86:0x0219, B:93:0x0239, B:125:0x0298, B:103:0x025c, B:45:0x00f2, B:61:0x0161), top: B:521:0x0009, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x04cd  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x05d5  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x05eb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:277:0x06a6  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x06ec A[Catch: all -> 0x0d64, TryCatch #3 {all -> 0x0d64, blocks: (B:3:0x0009, B:26:0x008d, B:126:0x029b, B:128:0x02a0, B:134:0x02b0, B:135:0x02d0, B:137:0x02d9, B:139:0x02f2, B:141:0x0324, B:147:0x0339, B:149:0x0344, B:292:0x070b, B:151:0x0362, B:153:0x0373, B:170:0x03ae, B:235:0x05db, B:238:0x05ed, B:239:0x05f4, B:241:0x05f7, B:247:0x0614, B:244:0x0606, B:250:0x061a, B:252:0x0620, B:254:0x0627, B:275:0x0681, B:276:0x06a2, B:279:0x06a9, B:281:0x06b3, B:283:0x06b7, B:286:0x06bd, B:288:0x06cc, B:289:0x06e4, B:290:0x06ec, B:291:0x0704, B:259:0x0653, B:261:0x0659, B:265:0x0662, B:267:0x0668, B:270:0x0674, B:160:0x038f, B:163:0x0399, B:166:0x03a3, B:176:0x03bd, B:178:0x03c3, B:179:0x03c8, B:181:0x03d2, B:183:0x03e2, B:187:0x0407, B:184:0x03f0, B:186:0x03fc, B:191:0x0417, B:193:0x045a, B:194:0x049a, B:197:0x04ce, B:199:0x04d3, B:201:0x04e1, B:203:0x04ea, B:204:0x04f0, B:206:0x04f3, B:207:0x04fc, B:208:0x04ff, B:211:0x0507, B:214:0x0512, B:216:0x0548, B:218:0x0569, B:224:0x058a, B:221:0x057e, B:228:0x0599, B:230:0x05ac, B:231:0x05b9, B:293:0x0712, B:295:0x071c, B:297:0x0729, B:299:0x0737, B:302:0x073c, B:54:0x0142, B:78:0x01e1, B:86:0x0219, B:93:0x0239, B:125:0x0298, B:103:0x025c, B:45:0x00f2, B:61:0x0161), top: B:521:0x0009, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0d46  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0d5d A[Catch: all -> 0x0d61, TRY_ENTER, TryCatch #0 {all -> 0x0d61, blocks: (B:312:0x078c, B:313:0x07ae, B:315:0x07b3, B:317:0x07c1, B:321:0x07cf, B:325:0x07f4, B:329:0x0815, B:331:0x0832, B:333:0x084f, B:335:0x085c, B:337:0x0870, B:338:0x087f, B:340:0x0883, B:342:0x0890, B:343:0x089f, B:345:0x08a3, B:347:0x08ac, B:349:0x08c4, B:435:0x0b3b, B:353:0x08e0, B:357:0x0916, B:359:0x091e, B:361:0x092a, B:363:0x092e, B:380:0x095e, B:383:0x0971, B:385:0x0992, B:387:0x099c, B:389:0x09ad, B:390:0x09e7, B:394:0x09f8, B:396:0x09ff, B:398:0x0a09, B:400:0x0a0d, B:402:0x0a11, B:404:0x0a15, B:406:0x0a26, B:408:0x0a2d, B:410:0x0a4c, B:411:0x0a55, B:412:0x0a67, B:414:0x0a79, B:416:0x0a7d, B:428:0x0ad1, B:430:0x0b04, B:431:0x0b12, B:432:0x0b25, B:434:0x0b2f, B:417:0x0a8a, B:423:0x0aad, B:365:0x0936, B:367:0x093a, B:369:0x0942, B:371:0x0946, B:375:0x0952, B:436:0x0b49, B:438:0x0b52, B:439:0x0b5a, B:440:0x0b62, B:442:0x0b68, B:444:0x0b7e, B:445:0x0b92, B:447:0x0b97, B:449:0x0bab, B:450:0x0baf, B:452:0x0bbf, B:453:0x0bc3, B:454:0x0bc6, B:456:0x0bd7, B:472:0x0c4b, B:474:0x0c51, B:476:0x0c67, B:479:0x0c6c, B:484:0x0c9c, B:480:0x0c71, B:482:0x0c7b, B:483:0x0c84, B:485:0x0ca5, B:486:0x0cbc, B:489:0x0cc4, B:490:0x0cc9, B:491:0x0cd9, B:493:0x0cf3, B:494:0x0d0c, B:495:0x0d14, B:500:0x0d36, B:499:0x0d25, B:457:0x0bef, B:459:0x0bf4, B:461:0x0bfe, B:463:0x0c04, B:469:0x0c16, B:471:0x0c1c, B:318:0x07c9, B:311:0x0778, B:504:0x0d47, B:510:0x0d5d, B:513:0x0d63), top: B:519:0x0025, inners: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:513:0x0d63 A[Catch: all -> 0x0d61, TRY_LEAVE, TryCatch #0 {all -> 0x0d61, blocks: (B:312:0x078c, B:313:0x07ae, B:315:0x07b3, B:317:0x07c1, B:321:0x07cf, B:325:0x07f4, B:329:0x0815, B:331:0x0832, B:333:0x084f, B:335:0x085c, B:337:0x0870, B:338:0x087f, B:340:0x0883, B:342:0x0890, B:343:0x089f, B:345:0x08a3, B:347:0x08ac, B:349:0x08c4, B:435:0x0b3b, B:353:0x08e0, B:357:0x0916, B:359:0x091e, B:361:0x092a, B:363:0x092e, B:380:0x095e, B:383:0x0971, B:385:0x0992, B:387:0x099c, B:389:0x09ad, B:390:0x09e7, B:394:0x09f8, B:396:0x09ff, B:398:0x0a09, B:400:0x0a0d, B:402:0x0a11, B:404:0x0a15, B:406:0x0a26, B:408:0x0a2d, B:410:0x0a4c, B:411:0x0a55, B:412:0x0a67, B:414:0x0a79, B:416:0x0a7d, B:428:0x0ad1, B:430:0x0b04, B:431:0x0b12, B:432:0x0b25, B:434:0x0b2f, B:417:0x0a8a, B:423:0x0aad, B:365:0x0936, B:367:0x093a, B:369:0x0942, B:371:0x0946, B:375:0x0952, B:436:0x0b49, B:438:0x0b52, B:439:0x0b5a, B:440:0x0b62, B:442:0x0b68, B:444:0x0b7e, B:445:0x0b92, B:447:0x0b97, B:449:0x0bab, B:450:0x0baf, B:452:0x0bbf, B:453:0x0bc3, B:454:0x0bc6, B:456:0x0bd7, B:472:0x0c4b, B:474:0x0c51, B:476:0x0c67, B:479:0x0c6c, B:484:0x0c9c, B:480:0x0c71, B:482:0x0c7b, B:483:0x0c84, B:485:0x0ca5, B:486:0x0cbc, B:489:0x0cc4, B:490:0x0cc9, B:491:0x0cd9, B:493:0x0cf3, B:494:0x0d0c, B:495:0x0d14, B:500:0x0d36, B:499:0x0d25, B:457:0x0bef, B:459:0x0bf4, B:461:0x0bfe, B:463:0x0c04, B:469:0x0c16, B:471:0x0c1c, B:318:0x07c9, B:311:0x0778, B:504:0x0d47, B:510:0x0d5d, B:513:0x0d63), top: B:519:0x0025, inners: #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:527:0x0151 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x012f A[Catch: all -> 0x0147, SQLiteException -> 0x014c, TRY_ENTER, TRY_LEAVE, TryCatch #22 {SQLiteException -> 0x014c, all -> 0x0147, blocks: (B:52:0x012f, B:64:0x016b, B:68:0x0186), top: B:545:0x012d }] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v159 */
    /* JADX WARN: Type inference failed for: r6v160 */
    /* JADX WARN: Type inference failed for: r6v161 */
    /* JADX WARN: Type inference failed for: r6v162 */
    /* JADX WARN: Type inference failed for: r6v163 */
    /* JADX WARN: Type inference failed for: r6v164 */
    /* JADX WARN: Type inference failed for: r6v165 */
    /* JADX WARN: Type inference failed for: r6v166 */
    /* JADX WARN: Type inference failed for: r6v167 */
    /* JADX WARN: Type inference failed for: r6v168 */
    /* JADX WARN: Type inference failed for: r6v170, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r6v171 */
    /* JADX WARN: Type inference failed for: r6v176 */
    /* JADX WARN: Type inference failed for: r6v177 */
    /* JADX WARN: Type inference failed for: r6v182, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v183, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v185, types: [com.google.android.gms.measurement.internal.zzar] */
    /* JADX WARN: Type inference failed for: r6v188 */
    /* JADX WARN: Type inference failed for: r6v189 */
    /* JADX WARN: Type inference failed for: r6v190 */
    /* JADX WARN: Type inference failed for: r6v191 */
    /* JADX WARN: Type inference failed for: r6v192 */
    /* JADX WARN: Type inference failed for: r6v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [android.database.Cursor] */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zzd(java.lang.String r57, long r58) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 3450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zzd(java.lang.String, long):boolean");
    }

    @VisibleForTesting
    private static zzgg[] zza(zzgg[] zzggVarArr, @NonNull String str) {
        int i = 0;
        while (true) {
            if (i < zzggVarArr.length) {
                if (str.equals(zzggVarArr[i].name)) {
                    break;
                }
                i++;
            } else {
                i = -1;
                break;
            }
        }
        if (i < 0) {
            return zzggVarArr;
        }
        return zza(zzggVarArr, i);
    }

    @VisibleForTesting
    private static zzgg[] zza(zzgg[] zzggVarArr, int i) {
        zzgg[] zzggVarArr2 = new zzgg[zzggVarArr.length - 1];
        if (i > 0) {
            System.arraycopy(zzggVarArr, 0, zzggVarArr2, 0, i);
        }
        if (i < zzggVarArr2.length) {
            System.arraycopy(zzggVarArr, i + 1, zzggVarArr2, i, zzggVarArr2.length - i);
        }
        return zzggVarArr2;
    }

    @VisibleForTesting
    private static zzgg[] zza(zzgg[] zzggVarArr, int i, String str) {
        for (zzgg zzggVar : zzggVarArr) {
            if ("_err".equals(zzggVar.name)) {
                return zzggVarArr;
            }
        }
        zzgg[] zzggVarArr2 = new zzgg[zzggVarArr.length + 2];
        System.arraycopy(zzggVarArr, 0, zzggVarArr2, 0, zzggVarArr.length);
        zzgg zzggVar2 = new zzgg();
        zzggVar2.name = "_err";
        zzggVar2.zzawx = Long.valueOf(i);
        zzgg zzggVar3 = new zzgg();
        zzggVar3.name = "_ev";
        zzggVar3.zzamp = str;
        zzggVarArr2[zzggVarArr2.length - 2] = zzggVar2;
        zzggVarArr2[zzggVarArr2.length - 1] = zzggVar3;
        return zzggVarArr2;
    }

    private final zzgd[] zza(String str, zzgl[] zzglVarArr, zzgf[] zzgfVarArr) {
        Preconditions.checkNotEmpty(str);
        return zzjp().zza(str, zzgfVarArr, zzglVarArr);
    }

    @WorkerThread
    @VisibleForTesting
    final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzaf();
        zzlr();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzatq = false;
                zzlw();
                throw th2;
            }
        }
        List<Long> list = this.zzatu;
        this.zzatu = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzadj.zzgp().zzane.set(this.zzadj.zzbx().currentTimeMillis());
                this.zzadj.zzgp().zzanf.set(0L);
                zzlv();
                this.zzadj.zzgo().zzjl().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzjq().beginTransaction();
                try {
                    for (Long l : list) {
                        try {
                            zzq zzqVarZzjq = zzjq();
                            long jLongValue = l.longValue();
                            zzqVarZzjq.zzaf();
                            zzqVarZzjq.zzcl();
                            try {
                                if (zzqVarZzjq.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(jLongValue)}) != 1) {
                                    throw new SQLiteException("Deleted fewer rows from queue than expected");
                                }
                                continue;
                            } catch (SQLiteException e) {
                                zzqVarZzjq.zzgo().zzjd().zzg("Failed to delete a bundle in a queue table", e);
                                throw e;
                            }
                        } catch (SQLiteException e2) {
                            if (this.zzatv == null || !this.zzatv.contains(l)) {
                                throw e2;
                            }
                        }
                    }
                    zzjq().setTransactionSuccessful();
                    zzjq().endTransaction();
                    this.zzatv = null;
                    if (zzlo().zzfb() && zzlu()) {
                        zzlt();
                    } else {
                        this.zzatw = -1L;
                        zzlv();
                    }
                    this.zzatl = 0L;
                } catch (Throwable th3) {
                    zzjq().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzadj.zzgo().zzjd().zzg("Database error while trying to delete uploaded bundles", e3);
                this.zzatl = this.zzadj.zzbx().elapsedRealtime();
                this.zzadj.zzgo().zzjl().zzg("Disable upload, time", Long.valueOf(this.zzatl));
            }
        } else {
            this.zzadj.zzgo().zzjl().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzadj.zzgp().zzanf.set(this.zzadj.zzbx().currentTimeMillis());
            if (i != 503 && i != 429) {
                z = false;
            }
            if (z) {
                this.zzadj.zzgp().zzang.set(this.zzadj.zzbx().currentTimeMillis());
            }
            if (this.zzadj.zzgq().zzaz(str)) {
                zzjq().zzc(list);
            }
            zzlv();
        }
        this.zzatq = false;
        zzlw();
    }

    private final boolean zzlu() {
        zzaf();
        zzlr();
        return zzjq().zzii() || !TextUtils.isEmpty(zzjq().zzid());
    }

    @WorkerThread
    private final void zzb(zzg zzgVar) {
        ArrayMap arrayMap;
        zzaf();
        if (TextUtils.isEmpty(zzgVar.getGmpAppId()) && (!zzn.zzic() || TextUtils.isEmpty(zzgVar.zzgw()))) {
            zzb(zzgVar.zzal(), 204, null, null, null);
            return;
        }
        zzn zznVarZzgq = this.zzadj.zzgq();
        Uri.Builder builder = new Uri.Builder();
        String gmpAppId = zzgVar.getGmpAppId();
        if (TextUtils.isEmpty(gmpAppId) && zzn.zzic()) {
            gmpAppId = zzgVar.zzgw();
        }
        Uri.Builder builderEncodedAuthority = builder.scheme(zzaf.zzajh.get()).encodedAuthority(zzaf.zzaji.get());
        String strValueOf = String.valueOf(gmpAppId);
        builderEncodedAuthority.path(strValueOf.length() != 0 ? "config/app/".concat(strValueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", zzgVar.getAppInstanceId()).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", String.valueOf(zznVarZzgq.zzhc()));
        String string = builder.build().toString();
        try {
            URL url = new URL(string);
            this.zzadj.zzgo().zzjl().zzg("Fetching remote configuration", zzgVar.zzal());
            zzgb zzgbVarZzcf = zzln().zzcf(zzgVar.zzal());
            String strZzcg = zzln().zzcg(zzgVar.zzal());
            if (zzgbVarZzcf == null || TextUtils.isEmpty(strZzcg)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put("If-Modified-Since", strZzcg);
                arrayMap = arrayMap2;
            }
            this.zzatp = true;
            zzat zzatVarZzlo = zzlo();
            String strZzal = zzgVar.zzal();
            zzfd zzfdVar = new zzfd(this);
            zzatVarZzlo.zzaf();
            zzatVarZzlo.zzcl();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzfdVar);
            zzatVarZzlo.zzgn().zzd(new zzax(zzatVarZzlo, strZzal, url, null, arrayMap, zzfdVar));
        } catch (MalformedURLException e) {
            this.zzadj.zzgo().zzjd().zze("Failed to parse config URL. Not fetching. appId", zzap.zzbv(zzgVar.zzal()), string);
        }
    }

    @WorkerThread
    @VisibleForTesting
    final void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        String str2;
        zzaf();
        zzlr();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzatp = false;
                zzlw();
                throw th2;
            }
        }
        this.zzadj.zzgo().zzjl().zzg("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zzjq().beginTransaction();
        try {
            zzg zzgVarZzbl = zzjq().zzbl(str);
            boolean z = true;
            boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
            if (zzgVarZzbl == null) {
                this.zzadj.zzgo().zzjg().zzg("App does not exist in onConfigFetched. appId", zzap.zzbv(str));
            } else if (z2 || i == 404) {
                List<String> list = map != null ? map.get("Last-Modified") : null;
                if (list != null && list.size() > 0) {
                    str2 = list.get(0);
                } else {
                    str2 = null;
                }
                if (i == 404 || i == 304) {
                    if (zzln().zzcf(str) == null && !zzln().zza(str, null, null)) {
                        this.zzatp = false;
                        zzlw();
                        return;
                    }
                } else if (!zzln().zza(str, bArr, str2)) {
                    this.zzatp = false;
                    zzlw();
                    return;
                }
                zzgVarZzbl.zzy(this.zzadj.zzbx().currentTimeMillis());
                zzjq().zza(zzgVarZzbl);
                if (i == 404) {
                    this.zzadj.zzgo().zzji().zzg("Config not found. Using empty config. appId", str);
                } else {
                    this.zzadj.zzgo().zzjl().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                }
                if (zzlo().zzfb() && zzlu()) {
                    zzlt();
                } else {
                    zzlv();
                }
            } else {
                zzgVarZzbl.zzz(this.zzadj.zzbx().currentTimeMillis());
                zzjq().zza(zzgVarZzbl);
                this.zzadj.zzgo().zzjl().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzln().zzch(str);
                this.zzadj.zzgp().zzanf.set(this.zzadj.zzbx().currentTimeMillis());
                if (i != 503 && i != 429) {
                    z = false;
                }
                if (z) {
                    this.zzadj.zzgp().zzang.set(this.zzadj.zzbx().currentTimeMillis());
                }
                zzlv();
            }
            zzjq().setTransactionSuccessful();
            this.zzatp = false;
            zzlw();
        } finally {
            zzjq().endTransaction();
        }
    }

    @WorkerThread
    private final void zzlv() {
        long jMax;
        long jMin;
        zzaf();
        zzlr();
        if (!zzlz()) {
            return;
        }
        if (this.zzatl > 0) {
            long jAbs = 3600000 - Math.abs(this.zzadj.zzbx().elapsedRealtime() - this.zzatl);
            if (jAbs > 0) {
                this.zzadj.zzgo().zzjl().zzg("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(jAbs));
                zzlp().unregister();
                zzlq().cancel();
                return;
            }
            this.zzatl = 0L;
        }
        if (!this.zzadj.zzkr() || !zzlu()) {
            this.zzadj.zzgo().zzjl().zzbx("Nothing to upload or uploading impossible");
            zzlp().unregister();
            zzlq().cancel();
            return;
        }
        long jCurrentTimeMillis = this.zzadj.zzbx().currentTimeMillis();
        long jMax2 = Math.max(0L, zzaf.zzakd.get().longValue());
        boolean z = zzjq().zzij() || zzjq().zzie();
        if (z) {
            String strZzhy = this.zzadj.zzgq().zzhy();
            if (!TextUtils.isEmpty(strZzhy) && !".none.".equals(strZzhy)) {
                jMax = Math.max(0L, zzaf.zzajy.get().longValue());
            } else {
                jMax = Math.max(0L, zzaf.zzajx.get().longValue());
            }
        } else {
            jMax = Math.max(0L, zzaf.zzajw.get().longValue());
        }
        long j = this.zzadj.zzgp().zzane.get();
        long j2 = this.zzadj.zzgp().zzanf.get();
        long j3 = jMax;
        long jMax3 = Math.max(zzjq().zzig(), zzjq().zzih());
        if (jMax3 != 0) {
            long jAbs2 = jCurrentTimeMillis - Math.abs(jMax3 - jCurrentTimeMillis);
            long jAbs3 = jCurrentTimeMillis - Math.abs(j - jCurrentTimeMillis);
            long jAbs4 = jCurrentTimeMillis - Math.abs(j2 - jCurrentTimeMillis);
            long jMax4 = Math.max(jAbs3, jAbs4);
            jMin = jAbs2 + jMax2;
            if (z && jMax4 > 0) {
                jMin = Math.min(jAbs2, jMax4) + j3;
            }
            if (!zzjo().zzb(jMax4, j3)) {
                jMin = jMax4 + j3;
            }
            if (jAbs4 != 0 && jAbs4 >= jAbs2) {
                int i = 0;
                while (i < Math.min(20, Math.max(0, zzaf.zzakf.get().intValue()))) {
                    long jMax5 = jMin + (Math.max(0L, zzaf.zzake.get().longValue()) * (1 << i));
                    if (jMax5 <= jAbs4) {
                        i++;
                        jMin = jMax5;
                    } else {
                        jMin = jMax5;
                        break;
                    }
                }
                jMin = 0;
            }
        } else {
            jMin = 0;
        }
        if (jMin == 0) {
            this.zzadj.zzgo().zzjl().zzbx("Next upload time is 0");
            zzlp().unregister();
            zzlq().cancel();
            return;
        }
        if (!zzlo().zzfb()) {
            this.zzadj.zzgo().zzjl().zzbx("No network");
            zzlp().zzey();
            zzlq().cancel();
            return;
        }
        long j4 = this.zzadj.zzgp().zzang.get();
        long jMax6 = Math.max(0L, zzaf.zzaju.get().longValue());
        if (!zzjo().zzb(j4, jMax6)) {
            jMin = Math.max(jMin, j4 + jMax6);
        }
        zzlp().unregister();
        long jCurrentTimeMillis2 = jMin - this.zzadj.zzbx().currentTimeMillis();
        if (jCurrentTimeMillis2 <= 0) {
            jCurrentTimeMillis2 = Math.max(0L, zzaf.zzajz.get().longValue());
            this.zzadj.zzgp().zzane.set(this.zzadj.zzbx().currentTimeMillis());
        }
        this.zzadj.zzgo().zzjl().zzg("Upload scheduled in approximately ms", Long.valueOf(jCurrentTimeMillis2));
        zzlq().zzh(jCurrentTimeMillis2);
    }

    @WorkerThread
    final void zzg(Runnable runnable) {
        zzaf();
        if (this.zzatm == null) {
            this.zzatm = new ArrayList();
        }
        this.zzatm.add(runnable);
    }

    @WorkerThread
    private final void zzlw() {
        zzaf();
        if (this.zzatp || this.zzatq || this.zzatr) {
            this.zzadj.zzgo().zzjl().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzatp), Boolean.valueOf(this.zzatq), Boolean.valueOf(this.zzatr));
            return;
        }
        this.zzadj.zzgo().zzjl().zzbx("Stopping uploading service(s)");
        if (this.zzatm == null) {
            return;
        }
        Iterator<Runnable> it = this.zzatm.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        this.zzatm.clear();
    }

    @WorkerThread
    private final Boolean zzc(zzg zzgVar) {
        try {
            if (zzgVar.zzha() != -2147483648L) {
                if (zzgVar.zzha() == Wrappers.packageManager(this.zzadj.getContext()).getPackageInfo(zzgVar.zzal(), 0).versionCode) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzadj.getContext()).getPackageInfo(zzgVar.zzal(), 0).versionName;
                if (zzgVar.zzak() != null && zzgVar.zzak().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zzlx() {
        zzaf();
        try {
            this.zzatt = new RandomAccessFile(new File(this.zzadj.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzats = this.zzatt.tryLock();
            if (this.zzats != null) {
                this.zzadj.zzgo().zzjl().zzbx("Storage concurrent access okay");
                return true;
            }
            this.zzadj.zzgo().zzjd().zzbx("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            this.zzadj.zzgo().zzjd().zzg("Failed to acquire storage lock", e);
            return false;
        } catch (IOException e2) {
            this.zzadj.zzgo().zzjd().zzg("Failed to access storage lock file", e2);
            return false;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzaf();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzadj.zzgo().zzjd().zzbx("Bad channel to read from");
            return 0;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0L);
            int i = fileChannel.read(byteBufferAllocate);
            if (i != 4) {
                if (i != -1) {
                    this.zzadj.zzgo().zzjg().zzg("Unexpected data length. Bytes read", Integer.valueOf(i));
                }
                return 0;
            }
            byteBufferAllocate.flip();
            return byteBufferAllocate.getInt();
        } catch (IOException e) {
            this.zzadj.zzgo().zzjd().zzg("Failed to read from channel", e);
            return 0;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzaf();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzadj.zzgo().zzjd().zzbx("Bad channel to read from");
            return false;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt(i);
        byteBufferAllocate.flip();
        try {
            fileChannel.truncate(0L);
            fileChannel.write(byteBufferAllocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzadj.zzgo().zzjd().zzg("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzadj.zzgo().zzjd().zzg("Failed to write to channel", e);
            return false;
        }
    }

    @WorkerThread
    final void zzly() {
        zzaf();
        zzlr();
        if (!this.zzatk) {
            this.zzadj.zzgo().zzjj().zzbx("This instance being marked as an uploader");
            zzaf();
            zzlr();
            if (zzlz() && zzlx()) {
                int iZza = zza(this.zzatt);
                int iZzja = this.zzadj.zzgf().zzja();
                zzaf();
                if (iZza > iZzja) {
                    this.zzadj.zzgo().zzjd().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzja));
                } else if (iZza < iZzja) {
                    if (zza(iZzja, this.zzatt)) {
                        this.zzadj.zzgo().zzjl().zze("Storage version upgraded. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzja));
                    } else {
                        this.zzadj.zzgo().zzjd().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzja));
                    }
                }
            }
            this.zzatk = true;
            zzlv();
        }
    }

    @WorkerThread
    private final boolean zzlz() {
        zzaf();
        zzlr();
        return this.zzatk;
    }

    @WorkerThread
    @VisibleForTesting
    final void zzd(zzh zzhVar) {
        if (this.zzatu != null) {
            this.zzatv = new ArrayList();
            this.zzatv.addAll(this.zzatu);
        }
        zzq zzqVarZzjq = zzjq();
        String str = zzhVar.packageName;
        Preconditions.checkNotEmpty(str);
        zzqVarZzjq.zzaf();
        zzqVarZzjq.zzcl();
        try {
            SQLiteDatabase writableDatabase = zzqVarZzjq.getWritableDatabase();
            String[] strArr = {str};
            int iDelete = 0 + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("events", "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (iDelete > 0) {
                zzqVarZzjq.zzgo().zzjl().zze("Reset analytics data. app, records", str, Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e) {
            zzqVarZzjq.zzgo().zzjd().zze("Error resetting analytics data. appId, error", zzap.zzbv(str), e);
        }
        zzh zzhVarZza = zza(this.zzadj.getContext(), zzhVar.packageName, zzhVar.zzafx, zzhVar.zzagg, zzhVar.zzagi, zzhVar.zzagj, zzhVar.zzagx, zzhVar.zzagk);
        if (!this.zzadj.zzgq().zzbd(zzhVar.packageName) || zzhVar.zzagg) {
            zzf(zzhVarZza);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005c A[Catch: NameNotFoundException -> 0x0074, TryCatch #0 {NameNotFoundException -> 0x0074, blocks: (B:17:0x0050, B:19:0x005c, B:21:0x006a, B:22:0x006f), top: B:35:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.google.android.gms.measurement.internal.zzh zza(android.content.Context r28, java.lang.String r29, java.lang.String r30, boolean r31, boolean r32, boolean r33, long r34, java.lang.String r36) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zza(android.content.Context, java.lang.String, java.lang.String, boolean, boolean, boolean, long, java.lang.String):com.google.android.gms.measurement.internal.zzh");
    }

    @WorkerThread
    final void zzb(zzfh zzfhVar, zzh zzhVar) {
        zzfj zzfjVarZzi;
        zzaf();
        zzlr();
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
            return;
        }
        if (this.zzadj.zzgq().zze(zzhVar.packageName, zzaf.zzalj) && "_ap".equals(zzfhVar.name) && (zzfjVarZzi = zzjq().zzi(zzhVar.packageName, "_ap")) != null && "auto".equals(zzfhVar.origin) && !"auto".equals(zzfjVarZzi.origin)) {
            this.zzadj.zzgo().zzjk().zzbx("Not setting lower priority ad personalization property");
            return;
        }
        int iZzcs = this.zzadj.zzgm().zzcs(zzfhVar.name);
        int length = 0;
        if (iZzcs != 0) {
            this.zzadj.zzgm();
            this.zzadj.zzgm().zza(zzhVar.packageName, iZzcs, "_ev", zzfk.zza(zzfhVar.name, 24, true), zzfhVar.name != null ? zzfhVar.name.length() : 0);
            return;
        }
        int iZzi = this.zzadj.zzgm().zzi(zzfhVar.name, zzfhVar.getValue());
        if (iZzi != 0) {
            this.zzadj.zzgm();
            String strZza = zzfk.zza(zzfhVar.name, 24, true);
            Object value = zzfhVar.getValue();
            if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                length = String.valueOf(value).length();
            }
            this.zzadj.zzgm().zza(zzhVar.packageName, iZzi, "_ev", strZza, length);
            return;
        }
        Object objZzj = this.zzadj.zzgm().zzj(zzfhVar.name, zzfhVar.getValue());
        if (objZzj == null) {
            return;
        }
        zzfj zzfjVar = new zzfj(zzhVar.packageName, zzfhVar.origin, zzfhVar.name, zzfhVar.zzaue, objZzj);
        this.zzadj.zzgo().zzjk().zze("Setting user property", this.zzadj.zzgl().zzbu(zzfjVar.name), objZzj);
        zzjq().beginTransaction();
        try {
            zzg(zzhVar);
            boolean zZza = zzjq().zza(zzfjVar);
            zzjq().setTransactionSuccessful();
            if (zZza) {
                this.zzadj.zzgo().zzjk().zze("User property set", this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
            } else {
                this.zzadj.zzgo().zzjd().zze("Too many unique user properties are set. Ignoring user property", this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                this.zzadj.zzgm().zza(zzhVar.packageName, 9, (String) null, (String) null, 0);
            }
        } finally {
            zzjq().endTransaction();
        }
    }

    @WorkerThread
    final void zzc(zzfh zzfhVar, zzh zzhVar) {
        zzfj zzfjVarZzi;
        zzaf();
        zzlr();
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
            return;
        }
        if (this.zzadj.zzgq().zze(zzhVar.packageName, zzaf.zzalj) && "_ap".equals(zzfhVar.name) && (zzfjVarZzi = zzjq().zzi(zzhVar.packageName, "_ap")) != null && "auto".equals(zzfhVar.origin) && !"auto".equals(zzfjVarZzi.origin)) {
            this.zzadj.zzgo().zzjk().zzbx("Not removing higher priority ad personalization property");
            return;
        }
        this.zzadj.zzgo().zzjk().zzg("Removing user property", this.zzadj.zzgl().zzbu(zzfhVar.name));
        zzjq().beginTransaction();
        try {
            zzg(zzhVar);
            zzjq().zzh(zzhVar.packageName, zzfhVar.name);
            zzjq().setTransactionSuccessful();
            this.zzadj.zzgo().zzjk().zzg("User property removed", this.zzadj.zzgl().zzbu(zzfhVar.name));
        } finally {
            zzjq().endTransaction();
        }
    }

    final void zzb(zzez zzezVar) {
        this.zzatn++;
    }

    final void zzma() {
        this.zzato++;
    }

    final zzbt zzmb() {
        return this.zzadj;
    }

    @WorkerThread
    final void zzf(zzh zzhVar) {
        zzz zzzVarZzg;
        long j;
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        boolean z;
        zzaf();
        zzlr();
        Preconditions.checkNotNull(zzhVar);
        Preconditions.checkNotEmpty(zzhVar.packageName);
        if (!TextUtils.isEmpty(zzhVar.zzafx) || !TextUtils.isEmpty(zzhVar.zzagk)) {
            zzg zzgVarZzbl = zzjq().zzbl(zzhVar.packageName);
            if (zzgVarZzbl != null && TextUtils.isEmpty(zzgVarZzbl.getGmpAppId()) && !TextUtils.isEmpty(zzhVar.zzafx)) {
                zzgVarZzbl.zzy(0L);
                zzjq().zza(zzgVarZzbl);
                zzln().zzci(zzhVar.packageName);
            }
            if (!zzhVar.zzagg) {
                zzg(zzhVar);
                return;
            }
            long jCurrentTimeMillis = zzhVar.zzagx;
            if (jCurrentTimeMillis == 0) {
                jCurrentTimeMillis = this.zzadj.zzbx().currentTimeMillis();
            }
            int i = zzhVar.zzagy;
            if (i != 0 && i != 1) {
                this.zzadj.zzgo().zzjg().zze("Incorrect app type, assuming installed app. appId, appType", zzap.zzbv(zzhVar.packageName), Integer.valueOf(i));
                i = 0;
            }
            zzjq().beginTransaction();
            try {
                zzg zzgVarZzbl2 = zzjq().zzbl(zzhVar.packageName);
                if (zzgVarZzbl2 != null) {
                    this.zzadj.zzgm();
                    if (zzfk.zza(zzhVar.zzafx, zzgVarZzbl2.getGmpAppId(), zzhVar.zzagk, zzgVarZzbl2.zzgw())) {
                        this.zzadj.zzgo().zzjg().zzg("New GMP App Id passed in. Removing cached database data. appId", zzap.zzbv(zzgVarZzbl2.zzal()));
                        zzq zzqVarZzjq = zzjq();
                        String strZzal = zzgVarZzbl2.zzal();
                        zzqVarZzjq.zzcl();
                        zzqVarZzjq.zzaf();
                        Preconditions.checkNotEmpty(strZzal);
                        try {
                            SQLiteDatabase writableDatabase = zzqVarZzjq.getWritableDatabase();
                            String[] strArr = {strZzal};
                            int iDelete = writableDatabase.delete("events", "app_id=?", strArr) + 0 + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr);
                            if (iDelete > 0) {
                                zzqVarZzjq.zzgo().zzjl().zze("Deleted application data. app, records", strZzal, Integer.valueOf(iDelete));
                            }
                        } catch (SQLiteException e) {
                            zzqVarZzjq.zzgo().zzjd().zze("Error deleting application data. appId, error", zzap.zzbv(strZzal), e);
                        }
                        zzgVarZzbl2 = null;
                    }
                }
                if (zzgVarZzbl2 != null) {
                    if (zzgVarZzbl2.zzha() != -2147483648L) {
                        if (zzgVarZzbl2.zzha() != zzhVar.zzagd) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_pv", zzgVarZzbl2.zzak());
                            zzc(new zzad("_au", new zzaa(bundle), "auto", jCurrentTimeMillis), zzhVar);
                        }
                    } else if (zzgVarZzbl2.zzak() != null && !zzgVarZzbl2.zzak().equals(zzhVar.zzts)) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("_pv", zzgVarZzbl2.zzak());
                        zzc(new zzad("_au", new zzaa(bundle2), "auto", jCurrentTimeMillis), zzhVar);
                    }
                }
                zzg(zzhVar);
                if (i == 0) {
                    zzzVarZzg = zzjq().zzg(zzhVar.packageName, "_f");
                } else if (i == 1) {
                    zzzVarZzg = zzjq().zzg(zzhVar.packageName, "_v");
                } else {
                    zzzVarZzg = null;
                }
                if (zzzVarZzg != null) {
                    if (zzhVar.zzagw) {
                        zzc(new zzad("_cd", new zzaa(new Bundle()), "auto", jCurrentTimeMillis), zzhVar);
                    }
                } else {
                    long j2 = 3600000 * (1 + (jCurrentTimeMillis / 3600000));
                    if (i == 0) {
                        j = 1;
                        zzb(new zzfh("_fot", jCurrentTimeMillis, Long.valueOf(j2), "auto"), zzhVar);
                        if (this.zzadj.zzgq().zzbg(zzhVar.zzafx)) {
                            zzaf();
                            this.zzadj.zzkg().zzcd(zzhVar.packageName);
                        }
                        zzaf();
                        zzlr();
                        Bundle bundle3 = new Bundle();
                        bundle3.putLong("_c", 1L);
                        bundle3.putLong("_r", 1L);
                        bundle3.putLong("_uwa", 0L);
                        bundle3.putLong("_pfo", 0L);
                        bundle3.putLong("_sys", 0L);
                        bundle3.putLong("_sysu", 0L);
                        if (this.zzadj.zzgq().zzbd(zzhVar.packageName) && zzhVar.zzagz) {
                            bundle3.putLong("_dac", 1L);
                        }
                        if (this.zzadj.getContext().getPackageManager() != null) {
                            try {
                                packageInfo = Wrappers.packageManager(this.zzadj.getContext()).getPackageInfo(zzhVar.packageName, 0);
                            } catch (PackageManager.NameNotFoundException e2) {
                                this.zzadj.zzgo().zzjd().zze("Package info is null, first open report might be inaccurate. appId", zzap.zzbv(zzhVar.packageName), e2);
                                packageInfo = null;
                            }
                            if (packageInfo != null && packageInfo.firstInstallTime != 0) {
                                if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                    bundle3.putLong("_uwa", 1L);
                                    z = false;
                                } else {
                                    z = true;
                                }
                                zzb(new zzfh("_fi", jCurrentTimeMillis, Long.valueOf(z ? 1L : 0L), "auto"), zzhVar);
                            }
                            try {
                                applicationInfo = Wrappers.packageManager(this.zzadj.getContext()).getApplicationInfo(zzhVar.packageName, 0);
                            } catch (PackageManager.NameNotFoundException e3) {
                                this.zzadj.zzgo().zzjd().zze("Application info is null, first open report might be inaccurate. appId", zzap.zzbv(zzhVar.packageName), e3);
                                applicationInfo = null;
                            }
                            if (applicationInfo != null) {
                                if ((applicationInfo.flags & 1) != 0) {
                                    bundle3.putLong("_sys", 1L);
                                }
                                if ((applicationInfo.flags & 128) != 0) {
                                    bundle3.putLong("_sysu", 1L);
                                }
                            }
                        } else {
                            this.zzadj.zzgo().zzjd().zzg("PackageManager is null, first open report might be inaccurate. appId", zzap.zzbv(zzhVar.packageName));
                        }
                        zzq zzqVarZzjq2 = zzjq();
                        String str = zzhVar.packageName;
                        Preconditions.checkNotEmpty(str);
                        zzqVarZzjq2.zzaf();
                        zzqVarZzjq2.zzcl();
                        long jZzn = zzqVarZzjq2.zzn(str, "first_open_count");
                        if (jZzn >= 0) {
                            bundle3.putLong("_pfo", jZzn);
                        }
                        zzc(new zzad("_f", new zzaa(bundle3), "auto", jCurrentTimeMillis), zzhVar);
                    } else {
                        j = 1;
                        if (i == 1) {
                            zzb(new zzfh("_fvt", jCurrentTimeMillis, Long.valueOf(j2), "auto"), zzhVar);
                            zzaf();
                            zzlr();
                            Bundle bundle4 = new Bundle();
                            bundle4.putLong("_c", 1L);
                            bundle4.putLong("_r", 1L);
                            if (this.zzadj.zzgq().zzbd(zzhVar.packageName) && zzhVar.zzagz) {
                                bundle4.putLong("_dac", 1L);
                            }
                            zzc(new zzad("_v", new zzaa(bundle4), "auto", jCurrentTimeMillis), zzhVar);
                        }
                    }
                    Bundle bundle5 = new Bundle();
                    bundle5.putLong("_et", j);
                    zzc(new zzad("_e", new zzaa(bundle5), "auto", jCurrentTimeMillis), zzhVar);
                }
                zzjq().setTransactionSuccessful();
            } finally {
                zzjq().endTransaction();
            }
        }
    }

    @WorkerThread
    private final zzh zzco(String str) {
        zzg zzgVarZzbl = zzjq().zzbl(str);
        if (zzgVarZzbl == null || TextUtils.isEmpty(zzgVarZzbl.zzak())) {
            this.zzadj.zzgo().zzjk().zzg("No app data available; dropping", str);
            return null;
        }
        Boolean boolZzc = zzc(zzgVarZzbl);
        if (boolZzc == null || boolZzc.booleanValue()) {
            return new zzh(str, zzgVarZzbl.getGmpAppId(), zzgVarZzbl.zzak(), zzgVarZzbl.zzha(), zzgVarZzbl.zzhb(), zzgVarZzbl.zzhc(), zzgVarZzbl.zzhd(), (String) null, zzgVarZzbl.isMeasurementEnabled(), false, zzgVarZzbl.getFirebaseInstanceId(), zzgVarZzbl.zzhq(), 0L, 0, zzgVarZzbl.zzhr(), zzgVarZzbl.zzhs(), false, zzgVarZzbl.zzgw());
        }
        this.zzadj.zzgo().zzjd().zzg("App version does not match; dropping. appId", zzap.zzbv(str));
        return null;
    }

    @WorkerThread
    final void zze(zzl zzlVar) {
        zzh zzhVarZzco = zzco(zzlVar.packageName);
        if (zzhVarZzco != null) {
            zzb(zzlVar, zzhVarZzco);
        }
    }

    @WorkerThread
    final void zzb(zzl zzlVar, zzh zzhVar) {
        Preconditions.checkNotNull(zzlVar);
        Preconditions.checkNotEmpty(zzlVar.packageName);
        Preconditions.checkNotNull(zzlVar.origin);
        Preconditions.checkNotNull(zzlVar.zzahb);
        Preconditions.checkNotEmpty(zzlVar.zzahb.name);
        zzaf();
        zzlr();
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
            return;
        }
        zzl zzlVar2 = new zzl(zzlVar);
        boolean z = false;
        zzlVar2.active = false;
        zzjq().beginTransaction();
        try {
            zzl zzlVarZzj = zzjq().zzj(zzlVar2.packageName, zzlVar2.zzahb.name);
            if (zzlVarZzj != null && !zzlVarZzj.origin.equals(zzlVar2.origin)) {
                this.zzadj.zzgo().zzjg().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzadj.zzgl().zzbu(zzlVar2.zzahb.name), zzlVar2.origin, zzlVarZzj.origin);
            }
            if (zzlVarZzj != null && zzlVarZzj.active) {
                zzlVar2.origin = zzlVarZzj.origin;
                zzlVar2.creationTimestamp = zzlVarZzj.creationTimestamp;
                zzlVar2.triggerTimeout = zzlVarZzj.triggerTimeout;
                zzlVar2.triggerEventName = zzlVarZzj.triggerEventName;
                zzlVar2.zzahd = zzlVarZzj.zzahd;
                zzlVar2.active = zzlVarZzj.active;
                zzlVar2.zzahb = new zzfh(zzlVar2.zzahb.name, zzlVarZzj.zzahb.zzaue, zzlVar2.zzahb.getValue(), zzlVarZzj.zzahb.origin);
            } else if (TextUtils.isEmpty(zzlVar2.triggerEventName)) {
                zzlVar2.zzahb = new zzfh(zzlVar2.zzahb.name, zzlVar2.creationTimestamp, zzlVar2.zzahb.getValue(), zzlVar2.zzahb.origin);
                zzlVar2.active = true;
                z = true;
            }
            if (zzlVar2.active) {
                zzfh zzfhVar = zzlVar2.zzahb;
                zzfj zzfjVar = new zzfj(zzlVar2.packageName, zzlVar2.origin, zzfhVar.name, zzfhVar.zzaue, zzfhVar.getValue());
                if (zzjq().zza(zzfjVar)) {
                    this.zzadj.zzgo().zzjk().zzd("User property updated immediately", zzlVar2.packageName, this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                } else {
                    this.zzadj.zzgo().zzjd().zzd("(2)Too many active user properties, ignoring", zzap.zzbv(zzlVar2.packageName), this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                }
                if (z && zzlVar2.zzahd != null) {
                    zzd(new zzad(zzlVar2.zzahd, zzlVar2.creationTimestamp), zzhVar);
                }
            }
            if (zzjq().zza(zzlVar2)) {
                this.zzadj.zzgo().zzjk().zzd("Conditional property added", zzlVar2.packageName, this.zzadj.zzgl().zzbu(zzlVar2.zzahb.name), zzlVar2.zzahb.getValue());
            } else {
                this.zzadj.zzgo().zzjd().zzd("Too many conditional properties, ignoring", zzap.zzbv(zzlVar2.packageName), this.zzadj.zzgl().zzbu(zzlVar2.zzahb.name), zzlVar2.zzahb.getValue());
            }
            zzjq().setTransactionSuccessful();
        } finally {
            zzjq().endTransaction();
        }
    }

    @WorkerThread
    final void zzf(zzl zzlVar) {
        zzh zzhVarZzco = zzco(zzlVar.packageName);
        if (zzhVarZzco != null) {
            zzc(zzlVar, zzhVarZzco);
        }
    }

    @WorkerThread
    final void zzc(zzl zzlVar, zzh zzhVar) {
        Preconditions.checkNotNull(zzlVar);
        Preconditions.checkNotEmpty(zzlVar.packageName);
        Preconditions.checkNotNull(zzlVar.zzahb);
        Preconditions.checkNotEmpty(zzlVar.zzahb.name);
        zzaf();
        zzlr();
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
            return;
        }
        zzjq().beginTransaction();
        try {
            zzg(zzhVar);
            zzl zzlVarZzj = zzjq().zzj(zzlVar.packageName, zzlVar.zzahb.name);
            if (zzlVarZzj != null) {
                this.zzadj.zzgo().zzjk().zze("Removing conditional user property", zzlVar.packageName, this.zzadj.zzgl().zzbu(zzlVar.zzahb.name));
                zzjq().zzk(zzlVar.packageName, zzlVar.zzahb.name);
                if (zzlVarZzj.active) {
                    zzjq().zzh(zzlVar.packageName, zzlVar.zzahb.name);
                }
                if (zzlVar.zzahe != null) {
                    Bundle bundleZziv = null;
                    if (zzlVar.zzahe.zzaid != null) {
                        bundleZziv = zzlVar.zzahe.zzaid.zziv();
                    }
                    zzd(this.zzadj.zzgm().zza(zzlVar.packageName, zzlVar.zzahe.name, bundleZziv, zzlVarZzj.origin, zzlVar.zzahe.zzaip, true, false), zzhVar);
                }
            } else {
                this.zzadj.zzgo().zzjg().zze("Conditional user property doesn't exist", zzap.zzbv(zzlVar.packageName), this.zzadj.zzgl().zzbu(zzlVar.zzahb.name));
            }
            zzjq().setTransactionSuccessful();
        } finally {
            zzjq().endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x016c  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzg zzg(com.google.android.gms.measurement.internal.zzh r9) {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zzg(com.google.android.gms.measurement.internal.zzh):com.google.android.gms.measurement.internal.zzg");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WorkerThread
    public final byte[] zza(@NonNull zzad zzadVar, @Size(min = 1) String str) {
        zzfj zzfjVarZzi;
        zzgi zzgiVar;
        long j;
        zzg zzgVar;
        Bundle bundle;
        zzgh zzghVar;
        byte[] bArr;
        long j2;
        zzlr();
        zzaf();
        this.zzadj.zzga();
        Preconditions.checkNotNull(zzadVar);
        Preconditions.checkNotEmpty(str);
        zzgh zzghVar2 = new zzgh();
        zzjq().beginTransaction();
        try {
            zzg zzgVarZzbl = zzjq().zzbl(str);
            if (zzgVarZzbl == null) {
                this.zzadj.zzgo().zzjk().zzg("Log and bundle not available. package_name", str);
                return new byte[0];
            }
            if (!zzgVarZzbl.isMeasurementEnabled()) {
                this.zzadj.zzgo().zzjk().zzg("Log and bundle disabled. package_name", str);
                return new byte[0];
            }
            if (("_iap".equals(zzadVar.name) || FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzadVar.name)) && !zza(str, zzadVar)) {
                this.zzadj.zzgo().zzjg().zzg("Failed to handle purchase event at single event bundle creation. appId", zzap.zzbv(str));
            }
            boolean zZzax = this.zzadj.zzgq().zzax(str);
            Long l = 0L;
            if (zZzax && "_e".equals(zzadVar.name)) {
                if (zzadVar.zzaid == null || zzadVar.zzaid.size() == 0) {
                    this.zzadj.zzgo().zzjg().zzg("The engagement event does not contain any parameters. appId", zzap.zzbv(str));
                } else if (zzadVar.zzaid.getLong("_et") == null) {
                    this.zzadj.zzgo().zzjg().zzg("The engagement event does not include duration. appId", zzap.zzbv(str));
                } else {
                    l = zzadVar.zzaid.getLong("_et");
                }
            }
            zzgi zzgiVar2 = new zzgi();
            zzghVar2.zzawy = new zzgi[]{zzgiVar2};
            zzgiVar2.zzaxa = 1;
            zzgiVar2.zzaxi = "android";
            zzgiVar2.zztt = zzgVarZzbl.zzal();
            zzgiVar2.zzage = zzgVarZzbl.zzhb();
            zzgiVar2.zzts = zzgVarZzbl.zzak();
            long jZzha = zzgVarZzbl.zzha();
            zzgiVar2.zzaxu = jZzha == -2147483648L ? null : Integer.valueOf((int) jZzha);
            zzgiVar2.zzaxm = Long.valueOf(zzgVarZzbl.zzhc());
            zzgiVar2.zzafx = zzgVarZzbl.getGmpAppId();
            if (TextUtils.isEmpty(zzgiVar2.zzafx)) {
                zzgiVar2.zzawj = zzgVarZzbl.zzgw();
            }
            zzgiVar2.zzaxq = Long.valueOf(zzgVarZzbl.zzhd());
            if (this.zzadj.isEnabled() && zzn.zzhz() && this.zzadj.zzgq().zzav(zzgiVar2.zztt)) {
                zzgiVar2.zzaya = null;
            }
            Pair<String, Boolean> pairZzby = this.zzadj.zzgp().zzby(zzgVarZzbl.zzal());
            if (zzgVarZzbl.zzhr() && pairZzby != null && !TextUtils.isEmpty((CharSequence) pairZzby.first)) {
                zzgiVar2.zzaxo = (String) pairZzby.first;
                zzgiVar2.zzaxp = (Boolean) pairZzby.second;
            }
            this.zzadj.zzgk().zzcl();
            zzgiVar2.zzaxk = Build.MODEL;
            this.zzadj.zzgk().zzcl();
            zzgiVar2.zzaxj = Build.VERSION.RELEASE;
            zzgiVar2.zzaxl = Integer.valueOf((int) this.zzadj.zzgk().zzis());
            zzgiVar2.zzaia = this.zzadj.zzgk().zzit();
            zzgiVar2.zzafw = zzgVarZzbl.getAppInstanceId();
            zzgiVar2.zzafz = zzgVarZzbl.getFirebaseInstanceId();
            List<zzfj> listZzbk = zzjq().zzbk(zzgVarZzbl.zzal());
            zzgiVar2.zzaxc = new zzgl[listZzbk.size()];
            if (zZzax) {
                zzfjVarZzi = zzjq().zzi(zzgiVar2.zztt, "_lte");
                if (zzfjVarZzi == null || zzfjVarZzi.value == null) {
                    zzfjVarZzi = new zzfj(zzgiVar2.zztt, "auto", "_lte", this.zzadj.zzbx().currentTimeMillis(), l);
                } else if (l.longValue() > 0) {
                    zzfjVarZzi = new zzfj(zzgiVar2.zztt, "auto", "_lte", this.zzadj.zzbx().currentTimeMillis(), Long.valueOf(((Long) zzfjVarZzi.value).longValue() + l.longValue()));
                }
            } else {
                zzfjVarZzi = null;
            }
            zzgl zzglVar = null;
            for (int i = 0; i < listZzbk.size(); i++) {
                zzgl zzglVar2 = new zzgl();
                zzgiVar2.zzaxc[i] = zzglVar2;
                zzglVar2.name = listZzbk.get(i).name;
                zzglVar2.zzayl = Long.valueOf(listZzbk.get(i).zzaue);
                zzjo().zza(zzglVar2, listZzbk.get(i).value);
                if (zZzax && "_lte".equals(zzglVar2.name)) {
                    zzglVar2.zzawx = (Long) zzfjVarZzi.value;
                    zzglVar2.zzayl = Long.valueOf(this.zzadj.zzbx().currentTimeMillis());
                    zzglVar = zzglVar2;
                }
            }
            if (zZzax && zzglVar == null) {
                zzgl zzglVar3 = new zzgl();
                zzglVar3.name = "_lte";
                zzglVar3.zzayl = Long.valueOf(this.zzadj.zzbx().currentTimeMillis());
                zzglVar3.zzawx = (Long) zzfjVarZzi.value;
                zzgiVar2.zzaxc = (zzgl[]) Arrays.copyOf(zzgiVar2.zzaxc, zzgiVar2.zzaxc.length + 1);
                zzgiVar2.zzaxc[zzgiVar2.zzaxc.length - 1] = zzglVar3;
            }
            if (l.longValue() > 0) {
                zzjq().zza(zzfjVarZzi);
            }
            Bundle bundleZziv = zzadVar.zzaid.zziv();
            if ("_iap".equals(zzadVar.name)) {
                bundleZziv.putLong("_c", 1L);
                this.zzadj.zzgo().zzjk().zzbx("Marking in-app purchase as real-time");
                bundleZziv.putLong("_r", 1L);
            }
            bundleZziv.putString("_o", zzadVar.origin);
            if (this.zzadj.zzgm().zzcw(zzgiVar2.zztt)) {
                this.zzadj.zzgm().zza(bundleZziv, "_dbg", (Object) 1L);
                this.zzadj.zzgm().zza(bundleZziv, "_r", (Object) 1L);
            }
            zzz zzzVarZzg = zzjq().zzg(str, zzadVar.name);
            if (zzzVarZzg == null) {
                zzgiVar = zzgiVar2;
                j = 0;
                zzgVar = zzgVarZzbl;
                bundle = bundleZziv;
                zzghVar = zzghVar2;
                bArr = null;
                zzjq().zza(new zzz(str, zzadVar.name, 1L, 0L, zzadVar.zzaip, 0L, null, null, null, null));
                j2 = 0;
            } else {
                zzgiVar = zzgiVar2;
                j = 0;
                zzgVar = zzgVarZzbl;
                bundle = bundleZziv;
                zzghVar = zzghVar2;
                bArr = null;
                long j3 = zzzVarZzg.zzaig;
                zzjq().zza(zzzVarZzg.zzai(zzadVar.zzaip).zziu());
                j2 = j3;
            }
            zzy zzyVar = new zzy(this.zzadj, zzadVar.origin, str, zzadVar.name, zzadVar.zzaip, j2, bundle);
            zzgf zzgfVar = new zzgf();
            zzgi zzgiVar3 = zzgiVar;
            zzgiVar3.zzaxb = new zzgf[]{zzgfVar};
            zzgfVar.zzawu = Long.valueOf(zzyVar.timestamp);
            zzgfVar.name = zzyVar.name;
            zzgfVar.zzawv = Long.valueOf(zzyVar.zzaic);
            zzgfVar.zzawt = new zzgg[zzyVar.zzaid.size()];
            int i2 = 0;
            for (String str2 : zzyVar.zzaid) {
                zzgg zzggVar = new zzgg();
                zzgfVar.zzawt[i2] = zzggVar;
                zzggVar.name = str2;
                zzjo().zza(zzggVar, zzyVar.zzaid.get(str2));
                i2++;
            }
            zzg zzgVar2 = zzgVar;
            zzgiVar3.zzaxt = zza(zzgVar2.zzal(), zzgiVar3.zzaxc, zzgiVar3.zzaxb);
            zzgiVar3.zzaxe = zzgfVar.zzawu;
            zzgiVar3.zzaxf = zzgfVar.zzawu;
            long jZzgz = zzgVar2.zzgz();
            zzgiVar3.zzaxh = jZzgz != j ? Long.valueOf(jZzgz) : bArr;
            long jZzgy = zzgVar2.zzgy();
            if (jZzgy != j) {
                jZzgz = jZzgy;
            }
            zzgiVar3.zzaxg = jZzgz != j ? Long.valueOf(jZzgz) : bArr;
            zzgVar2.zzhh();
            zzgiVar3.zzaxr = Integer.valueOf((int) zzgVar2.zzhe());
            zzgiVar3.zzaxn = Long.valueOf(this.zzadj.zzgq().zzhc());
            zzgiVar3.zzaxd = Long.valueOf(this.zzadj.zzbx().currentTimeMillis());
            zzgiVar3.zzaxs = Boolean.TRUE;
            zzgVar2.zzs(zzgiVar3.zzaxe.longValue());
            zzgVar2.zzt(zzgiVar3.zzaxf.longValue());
            zzjq().zza(zzgVar2);
            zzjq().setTransactionSuccessful();
            zzjq().endTransaction();
            zzgh zzghVar3 = zzghVar;
            try {
                byte[] bArr2 = new byte[zzghVar3.zzvu()];
                zzyy zzyyVarZzk = zzyy.zzk(bArr2, 0, bArr2.length);
                zzghVar3.zza(zzyyVarZzk);
                zzyyVarZzk.zzyt();
                return zzjo().zzb(bArr2);
            } catch (IOException e) {
                this.zzadj.zzgo().zzjd().zze("Data loss. Failed to bundle and serialize. appId", zzap.zzbv(str), e);
                return bArr;
            }
        } finally {
            zzjq().endTransaction();
        }
    }

    final String zzh(zzh zzhVar) {
        try {
            return (String) this.zzadj.zzgn().zzb(new zzfe(this, zzhVar)).get(30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzadj.zzgo().zzjd().zze("Failed to get app instance id. appId", zzap.zzbv(zzhVar.packageName), e);
            return null;
        }
    }

    final void zzo(boolean z) {
        zzlv();
    }
}
