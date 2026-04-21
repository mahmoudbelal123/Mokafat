package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.providers.PooledExecutorsProvider;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
public class WakeLock {
    private static ScheduledExecutorService zzaeg;
    private static Configuration zzaeh = new zza();
    private final PowerManager.WakeLock zzadv;
    private WorkSource zzadw;
    private String zzadx;
    private final int zzady;
    private final String zzadz;
    private final String zzaea;
    private final String zzaeb;
    private boolean zzaec;
    private final Map<String, Integer[]> zzaed;
    private int zzaee;
    private AtomicInteger zzaef;
    private final Context zzjp;

    public interface Configuration {
        long getMaximumTimeout(String str, String str2);

        boolean isWorkChainsEnabled();
    }

    public class HeldLock {
        private boolean zzaek;
        private Future zzael;
        private final String zzaem;

        private HeldLock(String str) {
            this.zzaek = true;
            this.zzaem = str;
        }

        /* synthetic */ HeldLock(WakeLock wakeLock, String str, zza zzaVar) {
            this(str);
        }

        public void finalize() {
            if (this.zzaek) {
                String strValueOf = String.valueOf(this.zzaem);
                Log.e("WakeLock", strValueOf.length() != 0 ? "HeldLock finalized while still holding the WakeLock! Reason: ".concat(strValueOf) : new String("HeldLock finalized while still holding the WakeLock! Reason: "));
                release(0);
            }
        }

        public void release() {
            release(0);
        }

        public synchronized void release(int i) {
            if (this.zzaek) {
                this.zzaek = false;
                if (this.zzael != null) {
                    this.zzael.cancel(false);
                    this.zzael = null;
                }
                WakeLock.this.zzc(this.zzaem, i);
            }
        }
    }

    public WakeLock(Context context, int i, @Nonnull String str) {
        this(context, i, str, null, context == null ? null : context.getPackageName());
    }

    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2) {
        this(context, i, str, str2, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2, @Nonnull String str3) {
        this(context, i, str, str2, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nullable String str4) {
        WorkSource workSourceFromPackage;
        this.zzaec = true;
        this.zzaed = new HashMap();
        this.zzaef = new AtomicInteger(0);
        Preconditions.checkNotEmpty(str, "Wake lock name can NOT be empty");
        this.zzady = i;
        this.zzaea = str2;
        this.zzaeb = str4;
        this.zzjp = context.getApplicationContext();
        if ("com.google.android.gms".equals(context.getPackageName())) {
            this.zzadz = str;
        } else {
            String strValueOf = String.valueOf("*gcore*:");
            String strValueOf2 = String.valueOf(str);
            this.zzadz = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        }
        this.zzadv = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (WorkSourceUtil.hasWorkSourcePermission(context)) {
            str3 = Strings.isEmptyOrWhitespace(str3) ? context.getPackageName() : str3;
            if (!zzaeh.isWorkChainsEnabled() || str3 == null || str4 == null) {
                workSourceFromPackage = WorkSourceUtil.fromPackage(context, str3);
            } else {
                StringBuilder sb = new StringBuilder(42 + String.valueOf(str3).length() + String.valueOf(str4).length());
                sb.append("Using experimental Pi WorkSource chains: ");
                sb.append(str3);
                sb.append(",");
                sb.append(str4);
                Log.d("WakeLock", sb.toString());
                this.zzadx = str3;
                workSourceFromPackage = WorkSourceUtil.fromPackageAndModuleExperimentalPi(context, str3, str4);
            }
            this.zzadw = workSourceFromPackage;
            addWorkSource(this.zzadw);
        }
        if (zzaeg == null) {
            zzaeg = PooledExecutorsProvider.getInstance().newSingleThreadScheduledExecutor();
        }
    }

    public static void setConfiguration(Configuration configuration) {
        zzaeh = configuration;
    }

    private final void zza(WorkSource workSource) {
        try {
            this.zzadv.setWorkSource(workSource);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            Log.wtf("WakeLock", e.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004e A[Catch: all -> 0x00b7, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000e, B:11:0x0021, B:13:0x0026, B:15:0x0030, B:22:0x0056, B:23:0x0076, B:16:0x003f, B:18:0x004e, B:20:0x0052, B:8:0x0012, B:10:0x001a), top: B:39:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0056 A[Catch: all -> 0x00b7, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000e, B:11:0x0021, B:13:0x0026, B:15:0x0030, B:22:0x0056, B:23:0x0076, B:16:0x003f, B:18:0x004e, B:20:0x0052, B:8:0x0012, B:10:0x001a), top: B:39:0x0005 }] */
    @android.annotation.SuppressLint({"WakelockTimeout"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zza(java.lang.String r13, long r14) {
        /*
            r12 = this;
            java.lang.String r5 = r12.zzn(r13)
            monitor-enter(r12)
            java.util.Map<java.lang.String, java.lang.Integer[]> r13 = r12.zzaed     // Catch: java.lang.Throwable -> Lb7
            boolean r13 = r13.isEmpty()     // Catch: java.lang.Throwable -> Lb7
            r0 = 0
            if (r13 == 0) goto L12
            int r13 = r12.zzaee     // Catch: java.lang.Throwable -> Lb7
            if (r13 <= 0) goto L21
        L12:
            android.os.PowerManager$WakeLock r13 = r12.zzadv     // Catch: java.lang.Throwable -> Lb7
            boolean r13 = r13.isHeld()     // Catch: java.lang.Throwable -> Lb7
            if (r13 != 0) goto L21
            java.util.Map<java.lang.String, java.lang.Integer[]> r13 = r12.zzaed     // Catch: java.lang.Throwable -> Lb7
            r13.clear()     // Catch: java.lang.Throwable -> Lb7
            r12.zzaee = r0     // Catch: java.lang.Throwable -> Lb7
        L21:
            boolean r13 = r12.zzaec     // Catch: java.lang.Throwable -> Lb7
            r11 = 1
            if (r13 == 0) goto L4e
            java.util.Map<java.lang.String, java.lang.Integer[]> r13 = r12.zzaed     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r13 = r13.get(r5)     // Catch: java.lang.Throwable -> Lb7
            java.lang.Integer[] r13 = (java.lang.Integer[]) r13     // Catch: java.lang.Throwable -> Lb7
            if (r13 != 0) goto L3f
            java.util.Map<java.lang.String, java.lang.Integer[]> r13 = r12.zzaed     // Catch: java.lang.Throwable -> Lb7
            java.lang.Integer[] r1 = new java.lang.Integer[r11]     // Catch: java.lang.Throwable -> Lb7
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Throwable -> Lb7
            r1[r0] = r2     // Catch: java.lang.Throwable -> Lb7
            r13.put(r5, r1)     // Catch: java.lang.Throwable -> Lb7
            r0 = r11
            goto L4c
        L3f:
            r1 = r13[r0]     // Catch: java.lang.Throwable -> Lb7
            int r1 = r1.intValue()     // Catch: java.lang.Throwable -> Lb7
            int r1 = r1 + r11
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> Lb7
            r13[r0] = r1     // Catch: java.lang.Throwable -> Lb7
        L4c:
            if (r0 != 0) goto L56
        L4e:
            boolean r13 = r12.zzaec     // Catch: java.lang.Throwable -> Lb7
            if (r13 != 0) goto L76
            int r13 = r12.zzaee     // Catch: java.lang.Throwable -> Lb7
            if (r13 != 0) goto L76
        L56:
            com.google.android.gms.common.stats.WakeLockTracker r0 = com.google.android.gms.common.stats.WakeLockTracker.getInstance()     // Catch: java.lang.Throwable -> Lb7
            android.content.Context r1 = r12.zzjp     // Catch: java.lang.Throwable -> Lb7
            android.os.PowerManager$WakeLock r13 = r12.zzadv     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r2 = com.google.android.gms.common.stats.StatsUtils.getEventKey(r13, r5)     // Catch: java.lang.Throwable -> Lb7
            r3 = 7
            java.lang.String r4 = r12.zzadz     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r6 = r12.zzaeb     // Catch: java.lang.Throwable -> Lb7
            int r7 = r12.zzady     // Catch: java.lang.Throwable -> Lb7
            java.util.List r8 = r12.zzdo()     // Catch: java.lang.Throwable -> Lb7
            r9 = r14
            r0.registerEvent(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> Lb7
            int r13 = r12.zzaee     // Catch: java.lang.Throwable -> Lb7
            int r13 = r13 + r11
            r12.zzaee = r13     // Catch: java.lang.Throwable -> Lb7
        L76:
            monitor-exit(r12)     // Catch: java.lang.Throwable -> Lb7
            android.os.PowerManager$WakeLock r13 = r12.zzadv
            r13.acquire()
            r0 = 0
            int r13 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r13 <= 0) goto Lb6
            java.util.concurrent.ScheduledExecutorService r13 = com.google.android.gms.stats.WakeLock.zzaeg
            com.google.android.gms.stats.zzb r0 = new com.google.android.gms.stats.zzb
            r0.<init>(r12)
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
            r13.schedule(r0, r14, r1)
            boolean r13 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()
            if (r13 != 0) goto Lb6
            boolean r13 = r12.zzaec
            if (r13 == 0) goto Lb6
            java.lang.String r13 = "WakeLock"
            java.lang.String r14 = "Do not acquire with timeout on reference counted wakeLocks before ICS. wakelock: "
            java.lang.String r15 = r12.zzadz
            java.lang.String r15 = java.lang.String.valueOf(r15)
            int r0 = r15.length()
            if (r0 == 0) goto Lad
            java.lang.String r14 = r14.concat(r15)
            goto Lb3
        Lad:
            java.lang.String r15 = new java.lang.String
            r15.<init>(r14)
            r14 = r15
        Lb3:
            android.util.Log.wtf(r13, r14)
        Lb6:
            return
        Lb7:
            r13 = move-exception
            monitor-exit(r12)     // Catch: java.lang.Throwable -> Lb7
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.stats.WakeLock.zza(java.lang.String, long):void");
    }

    private final void zzb(String str, int i) {
        if (this.zzaef.decrementAndGet() < 0) {
            Log.e("WakeLock", "release without a matched acquire!");
        }
        zzc(str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034 A[Catch: all -> 0x0061, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000a, B:18:0x003c, B:19:0x005c, B:9:0x0016, B:11:0x001e, B:12:0x0025, B:14:0x0034, B:16:0x0038), top: B:25:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c A[Catch: all -> 0x0061, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000a, B:18:0x003c, B:19:0x005c, B:9:0x0016, B:11:0x001e, B:12:0x0025, B:14:0x0034, B:16:0x0038), top: B:25:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzc(java.lang.String r11, int r12) {
        /*
            r10 = this;
            java.lang.String r5 = r10.zzn(r11)
            monitor-enter(r10)
            boolean r11 = r10.zzaec     // Catch: java.lang.Throwable -> L61
            r9 = 1
            if (r11 == 0) goto L34
            java.util.Map<java.lang.String, java.lang.Integer[]> r11 = r10.zzaed     // Catch: java.lang.Throwable -> L61
            java.lang.Object r11 = r11.get(r5)     // Catch: java.lang.Throwable -> L61
            java.lang.Integer[] r11 = (java.lang.Integer[]) r11     // Catch: java.lang.Throwable -> L61
            r0 = 0
            if (r11 != 0) goto L16
            goto L32
        L16:
            r1 = r11[r0]     // Catch: java.lang.Throwable -> L61
            int r1 = r1.intValue()     // Catch: java.lang.Throwable -> L61
            if (r1 != r9) goto L25
            java.util.Map<java.lang.String, java.lang.Integer[]> r11 = r10.zzaed     // Catch: java.lang.Throwable -> L61
            r11.remove(r5)     // Catch: java.lang.Throwable -> L61
            r0 = r9
            goto L32
        L25:
            r1 = r11[r0]     // Catch: java.lang.Throwable -> L61
            int r1 = r1.intValue()     // Catch: java.lang.Throwable -> L61
            int r1 = r1 - r9
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L61
            r11[r0] = r1     // Catch: java.lang.Throwable -> L61
        L32:
            if (r0 != 0) goto L3c
        L34:
            boolean r11 = r10.zzaec     // Catch: java.lang.Throwable -> L61
            if (r11 != 0) goto L5c
            int r11 = r10.zzaee     // Catch: java.lang.Throwable -> L61
            if (r11 != r9) goto L5c
        L3c:
            com.google.android.gms.common.stats.WakeLockTracker r0 = com.google.android.gms.common.stats.WakeLockTracker.getInstance()     // Catch: java.lang.Throwable -> L61
            android.content.Context r1 = r10.zzjp     // Catch: java.lang.Throwable -> L61
            android.os.PowerManager$WakeLock r11 = r10.zzadv     // Catch: java.lang.Throwable -> L61
            java.lang.String r2 = com.google.android.gms.common.stats.StatsUtils.getEventKey(r11, r5)     // Catch: java.lang.Throwable -> L61
            r3 = 8
            java.lang.String r4 = r10.zzadz     // Catch: java.lang.Throwable -> L61
            java.lang.String r6 = r10.zzaeb     // Catch: java.lang.Throwable -> L61
            int r7 = r10.zzady     // Catch: java.lang.Throwable -> L61
            java.util.List r8 = r10.zzdo()     // Catch: java.lang.Throwable -> L61
            r0.registerEvent(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L61
            int r11 = r10.zzaee     // Catch: java.lang.Throwable -> L61
            int r11 = r11 - r9
            r10.zzaee = r11     // Catch: java.lang.Throwable -> L61
        L5c:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L61
            r10.zzn(r12)
            return
        L61:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L61
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.stats.WakeLock.zzc(java.lang.String, int):void");
    }

    private final List<String> zzdo() {
        List<String> names = WorkSourceUtil.getNames(this.zzadw);
        if (this.zzadx == null) {
            return names;
        }
        ArrayList arrayList = new ArrayList(names);
        arrayList.add(this.zzadx);
        return arrayList;
    }

    private final String zzn(String str) {
        return (!this.zzaec || TextUtils.isEmpty(str)) ? this.zzaea : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzn(int i) {
        if (this.zzadv.isHeld()) {
            try {
                if (Build.VERSION.SDK_INT < 21 || i <= 0) {
                    this.zzadv.release();
                } else {
                    this.zzadv.release(i);
                }
            } catch (RuntimeException e) {
                if (!e.getClass().equals(RuntimeException.class)) {
                    throw e;
                }
                Log.e("WakeLock", String.valueOf(this.zzadz).concat(" was already released!"), e);
            }
        }
    }

    public void acquire() {
        this.zzaef.incrementAndGet();
        zza((String) null, 0L);
    }

    public void acquire(long j) {
        this.zzaef.incrementAndGet();
        zza((String) null, j);
    }

    public void acquire(String str) {
        this.zzaef.incrementAndGet();
        zza(str, 0L);
    }

    public void acquire(String str, long j) {
        this.zzaef.incrementAndGet();
        zza(str, j);
    }

    public HeldLock acquireLock(long j, String str) {
        long jMin = Math.min(j, zzaeh.getMaximumTimeout(this.zzadz, str));
        HeldLock heldLock = new HeldLock(this, str, null);
        zza(str, 0L);
        heldLock.zzael = zzaeg.schedule(new zzc(new WeakReference(heldLock)), jMin, TimeUnit.MILLISECONDS);
        return heldLock;
    }

    public void addWorkSource(WorkSource workSource) {
        if (workSource == null || !WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            return;
        }
        if (this.zzadw != null) {
            this.zzadw.add(workSource);
        } else {
            this.zzadw = workSource;
        }
        zza(this.zzadw);
    }

    public PowerManager.WakeLock getWakeLock() {
        return this.zzadv;
    }

    public boolean isHeld() {
        return this.zzadv.isHeld();
    }

    public void release() {
        zzb(null, 0);
    }

    public void release(int i) {
        zzb(null, i);
    }

    public void release(String str) {
        zzb(str, 0);
    }

    public void release(String str, int i) {
        zzb(str, i);
    }

    public void removeWorkSource(WorkSource workSource) {
        if (workSource == null || !WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            return;
        }
        try {
            if (this.zzadw != null) {
                this.zzadw.remove(workSource);
            }
            zza(this.zzadw);
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("WakeLock", e.toString());
        }
    }

    public void setReferenceCounted(boolean z) {
        this.zzadv.setReferenceCounted(z);
        this.zzaec = z;
    }

    public void setWorkSource(WorkSource workSource) {
        if (WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            zza(workSource);
            this.zzadw = workSource;
            this.zzadx = null;
        }
    }
}
