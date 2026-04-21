package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzyx;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class zzq extends zzez {
    private static final String[] zzahi = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    private static final String[] zzahj = {FirebaseAnalytics.Param.ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzahk = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;"};
    private static final String[] zzahl = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzahm = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzahn = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzt zzaho;
    private final zzev zzahp;

    zzq(zzfa zzfaVar) {
        super(zzfaVar);
        this.zzahp = new zzev(zzbx());
        this.zzaho = new zzt(this, getContext(), "google_app_measurement.db");
    }

    @Override // com.google.android.gms.measurement.internal.zzez
    protected final boolean zzgt() {
        return false;
    }

    @WorkerThread
    public final void beginTransaction() {
        zzcl();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        zzcl();
        getWritableDatabase().setTransactionSuccessful();
    }

    @WorkerThread
    public final void endTransaction() {
        zzcl();
        getWritableDatabase().endTransaction();
    }

    @WorkerThread
    private final long zza(String str, String[] strArr) throws Throwable {
        Cursor cursorRawQuery;
        try {
            try {
                cursorRawQuery = getWritableDatabase().rawQuery(str, strArr);
            } catch (Throwable th) {
                th = th;
                cursorRawQuery = null;
            }
        } catch (SQLiteException e) {
            e = e;
        }
        try {
            if (cursorRawQuery.moveToFirst()) {
                long j = cursorRawQuery.getLong(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e2) {
            e = e2;
            zzgo().zzjd().zze("Database error", str, e);
            throw e;
        } catch (Throwable th2) {
            th = th2;
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor = null;
        try {
            try {
                cursorRawQuery = getWritableDatabase().rawQuery(str, strArr);
            } catch (SQLiteException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (cursorRawQuery.moveToFirst()) {
                long j2 = cursorRawQuery.getLong(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j2;
            }
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return j;
        } catch (SQLiteException e2) {
            e = e2;
            cursor = cursorRawQuery;
            zzgo().zzjd().zze("Database error", str, e);
            throw e;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorRawQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    @VisibleForTesting
    final SQLiteDatabase getWritableDatabase() {
        zzaf();
        try {
            return this.zzaho.getWritableDatabase();
        } catch (SQLiteException e) {
            zzgo().zzjg().zzg("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:86:? A[SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzz zzg(java.lang.String r25, java.lang.String r26) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzg(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzz");
    }

    @WorkerThread
    public final void zza(zzz zzzVar) {
        Long l;
        Preconditions.checkNotNull(zzzVar);
        zzaf();
        zzcl();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzzVar.zztt);
        contentValues.put("name", zzzVar.name);
        contentValues.put("lifetime_count", Long.valueOf(zzzVar.zzaie));
        contentValues.put("current_bundle_count", Long.valueOf(zzzVar.zzaif));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzzVar.zzaig));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzzVar.zzaih));
        contentValues.put("last_bundled_day", zzzVar.zzaii);
        contentValues.put("last_sampled_complex_event_id", zzzVar.zzaij);
        contentValues.put("last_sampling_rate", zzzVar.zzaik);
        if (zzzVar.zzail != null && zzzVar.zzail.booleanValue()) {
            l = 1L;
        } else {
            l = null;
        }
        contentValues.put("last_exempt_from_sampling", l);
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzgo().zzjd().zzg("Failed to insert/update event aggregates (got -1). appId", zzap.zzbv(zzzVar.zztt));
            }
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error storing event aggregates. appId", zzap.zzbv(zzzVar.zztt), e);
        }
    }

    @WorkerThread
    public final void zzh(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        try {
            zzgo().zzjl().zzg("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzgo().zzjd().zzd("Error deleting user attribute. appId", zzap.zzbv(str), zzgl().zzbu(str2), e);
        }
    }

    @WorkerThread
    public final boolean zza(zzfj zzfjVar) throws Throwable {
        Preconditions.checkNotNull(zzfjVar);
        zzaf();
        zzcl();
        if (zzi(zzfjVar.zztt, zzfjVar.name) == null) {
            if (!zzfk.zzcq(zzfjVar.name)) {
                long jZza = zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzfjVar.zztt, zzfjVar.origin});
                if (zzgq().zze(zzfjVar.zztt, zzaf.zzalj)) {
                    if (!"_ap".equals(zzfjVar.name) && jZza >= 25) {
                        return false;
                    }
                } else if (jZza >= 25) {
                    return false;
                }
            } else if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzfjVar.zztt}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzfjVar.zztt);
        contentValues.put(FirebaseAnalytics.Param.ORIGIN, zzfjVar.origin);
        contentValues.put("name", zzfjVar.name);
        contentValues.put("set_timestamp", Long.valueOf(zzfjVar.zzaue));
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzfjVar.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzgo().zzjd().zzg("Failed to insert/update user property (got -1). appId", zzap.zzbv(zzfjVar.zztt));
            }
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error storing user property. appId", zzap.zzbv(zzfjVar.zztt), e);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzfj zzi(java.lang.String r20, java.lang.String r21) {
        /*
            r19 = this;
            r8 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21)
            r19.zzaf()
            r19.zzcl()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r19.getWritableDatabase()     // Catch: java.lang.Throwable -> L80 android.database.sqlite.SQLiteException -> L86
            java.lang.String r11 = "user_attributes"
            java.lang.String r1 = "set_timestamp"
            java.lang.String r2 = "value"
            java.lang.String r3 = "origin"
            java.lang.String[] r12 = new java.lang.String[]{r1, r2, r3}     // Catch: java.lang.Throwable -> L80 android.database.sqlite.SQLiteException -> L86
            java.lang.String r13 = "app_id=? and name=?"
            r1 = 2
            java.lang.String[] r14 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L80 android.database.sqlite.SQLiteException -> L86
            r2 = 0
            r14[r2] = r20     // Catch: java.lang.Throwable -> L80 android.database.sqlite.SQLiteException -> L86
            r3 = 1
            r14[r3] = r8     // Catch: java.lang.Throwable -> L80 android.database.sqlite.SQLiteException -> L86
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch: java.lang.Throwable -> L80 android.database.sqlite.SQLiteException -> L86
            boolean r4 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L77 android.database.sqlite.SQLiteException -> L7b
            if (r4 != 0) goto L40
            if (r10 == 0) goto L3f
            r10.close()
        L3f:
            return r9
        L40:
            long r5 = r10.getLong(r2)     // Catch: java.lang.Throwable -> L77 android.database.sqlite.SQLiteException -> L7b
            r11 = r19
            java.lang.Object r7 = r11.zza(r10, r3)     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
            java.lang.String r3 = r10.getString(r1)     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
            com.google.android.gms.measurement.internal.zzfj r12 = new com.google.android.gms.measurement.internal.zzfj     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
            r1 = r12
            r2 = r20
            r4 = r8
            r1.<init>(r2, r3, r4, r5, r7)     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
            boolean r1 = r10.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
            if (r1 == 0) goto L6e
            com.google.android.gms.measurement.internal.zzap r1 = r19.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzap.zzbv(r20)     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
            r1.zzg(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L75 java.lang.Throwable -> Laa
        L6e:
            if (r10 == 0) goto L74
            r10.close()
        L74:
            return r12
        L75:
            r0 = move-exception
            goto L7e
        L77:
            r0 = move-exception
            r11 = r19
            goto Lab
        L7b:
            r0 = move-exception
            r11 = r19
        L7e:
            r1 = r0
            goto L8b
        L80:
            r0 = move-exception
            r11 = r19
            r1 = r0
            r10 = r9
            goto Lac
        L86:
            r0 = move-exception
            r11 = r19
            r1 = r0
            r10 = r9
        L8b:
            com.google.android.gms.measurement.internal.zzap r2 = r19.zzgo()     // Catch: java.lang.Throwable -> Laa
            com.google.android.gms.measurement.internal.zzar r2 = r2.zzjd()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzap.zzbv(r20)     // Catch: java.lang.Throwable -> Laa
            com.google.android.gms.measurement.internal.zzan r5 = r19.zzgl()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r5 = r5.zzbu(r8)     // Catch: java.lang.Throwable -> Laa
            r2.zzd(r3, r4, r5, r1)     // Catch: java.lang.Throwable -> Laa
            if (r10 == 0) goto La9
            r10.close()
        La9:
            return r9
        Laa:
            r0 = move-exception
        Lab:
            r1 = r0
        Lac:
            if (r10 == 0) goto Lb1
            r10.close()
        Lb1:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzi(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzfj");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzfj> zzbk(java.lang.String r14) throws java.lang.Throwable {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            r13.zzaf()
            r13.zzcl()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r13.getWritableDatabase()     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            java.lang.String r3 = "user_attributes"
            java.lang.String r4 = "name"
            java.lang.String r5 = "origin"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7}     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            java.lang.String r5 = "app_id=?"
            r11 = 1
            java.lang.String[] r6 = new java.lang.String[r11]     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            r12 = 0
            r6[r12] = r14     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1000"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            boolean r3 = r2.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            if (r3 != 0) goto L41
        L3b:
            if (r2 == 0) goto L40
            r2.close()
        L40:
            return r0
        L41:
            java.lang.String r7 = r2.getString(r12)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            java.lang.String r3 = r2.getString(r11)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            if (r3 != 0) goto L4d
            java.lang.String r3 = ""
        L4d:
            r6 = r3
            r3 = 2
            long r8 = r2.getLong(r3)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            r3 = 3
            java.lang.Object r10 = r13.zza(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            if (r10 != 0) goto L6c
            com.google.android.gms.measurement.internal.zzap r3 = r13.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            java.lang.String r4 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzap.zzbv(r14)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            r3.zzg(r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            goto L76
        L6c:
            com.google.android.gms.measurement.internal.zzfj r3 = new com.google.android.gms.measurement.internal.zzfj     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            r4 = r3
            r5 = r14
            r4.<init>(r5, r6, r7, r8, r10)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            r0.add(r3)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
        L76:
            boolean r3 = r2.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            if (r3 != 0) goto L41
        L7d:
            if (r2 == 0) goto L82
            r2.close()
        L82:
            return r0
        L83:
            r0 = move-exception
            goto L8a
        L85:
            r14 = move-exception
            r2 = r1
            goto La2
        L88:
            r0 = move-exception
            r2 = r1
        L8a:
            com.google.android.gms.measurement.internal.zzap r3 = r13.zzgo()     // Catch: java.lang.Throwable -> La1
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: java.lang.Throwable -> La1
            java.lang.String r4 = "Error querying user properties. appId"
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzap.zzbv(r14)     // Catch: java.lang.Throwable -> La1
            r3.zze(r4, r14, r0)     // Catch: java.lang.Throwable -> La1
            if (r2 == 0) goto La0
            r2.close()
        La0:
            return r1
        La1:
            r14 = move-exception
        La2:
            if (r2 == 0) goto La7
            r2.close()
        La7:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzbk(java.lang.String):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0097, code lost:
    
        zzgo().zzjd().zzg("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzfj> zzb(java.lang.String r24, java.lang.String r25, java.lang.String r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public final boolean zza(zzl zzlVar) {
        Preconditions.checkNotNull(zzlVar);
        zzaf();
        zzcl();
        if (zzi(zzlVar.packageName, zzlVar.zzahb.name) == null && zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzlVar.packageName}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzlVar.packageName);
        contentValues.put(FirebaseAnalytics.Param.ORIGIN, zzlVar.origin);
        contentValues.put("name", zzlVar.zzahb.name);
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzlVar.zzahb.getValue());
        contentValues.put("active", Boolean.valueOf(zzlVar.active));
        contentValues.put("trigger_event_name", zzlVar.triggerEventName);
        contentValues.put("trigger_timeout", Long.valueOf(zzlVar.triggerTimeout));
        zzgm();
        contentValues.put("timed_out_event", zzfk.zza(zzlVar.zzahc));
        contentValues.put("creation_timestamp", Long.valueOf(zzlVar.creationTimestamp));
        zzgm();
        contentValues.put("triggered_event", zzfk.zza(zzlVar.zzahd));
        contentValues.put("triggered_timestamp", Long.valueOf(zzlVar.zzahb.zzaue));
        contentValues.put("time_to_live", Long.valueOf(zzlVar.timeToLive));
        zzgm();
        contentValues.put("expired_event", zzfk.zza(zzlVar.zzahe));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzgo().zzjd().zzg("Failed to insert/update conditional user property (got -1)", zzap.zzbv(zzlVar.packageName));
            }
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error storing conditional user property", zzap.zzbv(zzlVar.packageName), e);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzl zzj(java.lang.String r31, java.lang.String r32) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzj(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzl");
    }

    @WorkerThread
    public final int zzk(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzgo().zzjd().zzd("Error deleting conditional property", zzap.zzbv(str), zzgl().zzbu(str2), e);
            return 0;
        }
    }

    @WorkerThread
    public final List<zzl> zzc(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzb(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0054, code lost:
    
        zzgo().zzjd().zzg("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzl> zzb(java.lang.String r26, java.lang.String[] r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzb(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzg zzbl(java.lang.String r32) {
        /*
            Method dump skipped, instruction units count: 485
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzbl(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    @WorkerThread
    public final void zza(zzg zzgVar) {
        Preconditions.checkNotNull(zzgVar);
        zzaf();
        zzcl();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzgVar.zzal());
        contentValues.put("app_instance_id", zzgVar.getAppInstanceId());
        contentValues.put("gmp_app_id", zzgVar.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzgVar.zzgx());
        contentValues.put("last_bundle_index", Long.valueOf(zzgVar.zzhe()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzgVar.zzgy()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzgVar.zzgz()));
        contentValues.put("app_version", zzgVar.zzak());
        contentValues.put("app_store", zzgVar.zzhb());
        contentValues.put("gmp_version", Long.valueOf(zzgVar.zzhc()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzgVar.zzhd()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzgVar.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzgVar.zzhi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzgVar.zzhj()));
        contentValues.put("daily_events_count", Long.valueOf(zzgVar.zzhk()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzgVar.zzhl()));
        contentValues.put("config_fetched_time", Long.valueOf(zzgVar.zzhf()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzgVar.zzhg()));
        contentValues.put("app_version_int", Long.valueOf(zzgVar.zzha()));
        contentValues.put("firebase_instance_id", zzgVar.getFirebaseInstanceId());
        contentValues.put("daily_error_events_count", Long.valueOf(zzgVar.zzhn()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzgVar.zzhm()));
        contentValues.put("health_monitor_sample", zzgVar.zzho());
        contentValues.put("android_id", Long.valueOf(zzgVar.zzhq()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzgVar.zzhr()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzgVar.zzhs()));
        contentValues.put("admob_app_id", zzgVar.zzgw());
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzgVar.zzal()}) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzgo().zzjd().zzg("Failed to insert/update app (got -1). appId", zzap.zzbv(zzgVar.zzal()));
            }
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error storing app. appId", zzap.zzbv(zzgVar.zzal()), e);
        }
    }

    public final long zzbm(String str) {
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        try {
            return getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzgq().zzb(str, zzaf.zzajs))))});
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error deleting over the limit events. appId", zzap.zzbv(str), e);
            return 0L;
        }
    }

    @WorkerThread
    public final zzr zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        SQLiteException sQLiteException;
        SQLiteDatabase writableDatabase;
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        String[] strArr = {str};
        zzr zzrVar = new zzr();
        Cursor cursor = null;
        try {
            try {
                writableDatabase = getWritableDatabase();
                cursorQuery = writableDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            } catch (SQLiteException e) {
                sQLiteException = e;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = cursor;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                zzgo().zzjg().zzg("Not updating daily counts, app is not known. appId", zzap.zzbv(str));
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return zzrVar;
            }
            if (cursorQuery.getLong(0) == j) {
                zzrVar.zzahr = cursorQuery.getLong(1);
                zzrVar.zzahq = cursorQuery.getLong(2);
                zzrVar.zzahs = cursorQuery.getLong(3);
                zzrVar.zzaht = cursorQuery.getLong(4);
                zzrVar.zzahu = cursorQuery.getLong(5);
            }
            if (z) {
                zzrVar.zzahr++;
            }
            if (z2) {
                zzrVar.zzahq++;
            }
            if (z3) {
                zzrVar.zzahs++;
            }
            if (z4) {
                zzrVar.zzaht++;
            }
            if (z5) {
                zzrVar.zzahu++;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j));
            contentValues.put("daily_public_events_count", Long.valueOf(zzrVar.zzahq));
            contentValues.put("daily_events_count", Long.valueOf(zzrVar.zzahr));
            contentValues.put("daily_conversions_count", Long.valueOf(zzrVar.zzahs));
            contentValues.put("daily_error_events_count", Long.valueOf(zzrVar.zzaht));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzrVar.zzahu));
            writableDatabase.update("apps", contentValues, "app_id=?", strArr);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return zzrVar;
        } catch (SQLiteException e2) {
            sQLiteException = e2;
            cursor = cursorQuery;
            zzgo().zzjd().zze("Error updating daily counts. appId", zzap.zzbv(str), sQLiteException);
            if (cursor != null) {
                cursor.close();
            }
            return zzrVar;
        } catch (Throwable th3) {
            th = th3;
            if (cursorQuery == null) {
                throw th;
            }
            cursorQuery.close();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0075  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] zzbn(java.lang.String r11) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            r10.zzaf()
            r10.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.getWritableDatabase()     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L59
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L59
            java.lang.String r4 = "app_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L59
            r9 = 0
            r5[r9] = r11     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L59
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L59
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L72
            if (r2 != 0) goto L32
            if (r1 == 0) goto L31
            r1.close()
        L31:
            return r0
        L32:
            byte[] r2 = r1.getBlob(r9)     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L72
            boolean r3 = r1.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L72
            if (r3 == 0) goto L4d
            com.google.android.gms.measurement.internal.zzap r3 = r10.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L72
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L72
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzap.zzbv(r11)     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L72
            r3.zzg(r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L54 java.lang.Throwable -> L72
        L4d:
            if (r1 == 0) goto L53
            r1.close()
        L53:
            return r2
        L54:
            r2 = move-exception
            goto L5b
        L56:
            r11 = move-exception
            r1 = r0
            goto L73
        L59:
            r2 = move-exception
            r1 = r0
        L5b:
            com.google.android.gms.measurement.internal.zzap r3 = r10.zzgo()     // Catch: java.lang.Throwable -> L72
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: java.lang.Throwable -> L72
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzap.zzbv(r11)     // Catch: java.lang.Throwable -> L72
            r3.zze(r4, r11, r2)     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L71
            r1.close()
        L71:
            return r0
        L72:
            r11 = move-exception
        L73:
            if (r1 == 0) goto L78
            r1.close()
        L78:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzbn(java.lang.String):byte[]");
    }

    @WorkerThread
    public final boolean zza(zzgi zzgiVar, boolean z) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzgiVar);
        Preconditions.checkNotEmpty(zzgiVar.zztt);
        Preconditions.checkNotNull(zzgiVar.zzaxf);
        zzif();
        long jCurrentTimeMillis = zzbx().currentTimeMillis();
        if (zzgiVar.zzaxf.longValue() < jCurrentTimeMillis - zzn.zzhw() || zzgiVar.zzaxf.longValue() > jCurrentTimeMillis + zzn.zzhw()) {
            zzgo().zzjg().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzap.zzbv(zzgiVar.zztt), Long.valueOf(jCurrentTimeMillis), zzgiVar.zzaxf);
        }
        try {
            byte[] bArr = new byte[zzgiVar.zzvu()];
            zzyy zzyyVarZzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgiVar.zza(zzyyVarZzk);
            zzyyVarZzk.zzyt();
            byte[] bArrZzb = zzjo().zzb(bArr);
            zzgo().zzjl().zzg("Saving bundle, size", Integer.valueOf(bArrZzb.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzgiVar.zztt);
            contentValues.put("bundle_end_timestamp", zzgiVar.zzaxf);
            contentValues.put(DataBufferSafeParcelable.DATA_FIELD, bArrZzb);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzgiVar.zzayc != null) {
                contentValues.put("retry_count", zzgiVar.zzayc);
            }
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) == -1) {
                    zzgo().zzjd().zzg("Failed to insert bundle (got -1). appId", zzap.zzbv(zzgiVar.zztt));
                    return false;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing bundle. appId", zzap.zzbv(zzgiVar.zztt), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Data loss. Failed to serialize bundle. appId", zzap.zzbv(zzgiVar.zztt), e2);
            return false;
        }
    }

    @WorkerThread
    public final String zzid() throws Throwable {
        Throwable th;
        Cursor cursorRawQuery;
        try {
            cursorRawQuery = getWritableDatabase().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        String string = cursorRawQuery.getString(0);
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        return string;
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                } catch (SQLiteException e) {
                    e = e;
                    zzgo().zzjd().zzg("Database error getting next bundle app id", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            th = th2;
        } catch (SQLiteException e2) {
            e = e2;
            cursorRawQuery = null;
        } catch (Throwable th3) {
            th = th3;
            cursorRawQuery = null;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        throw th;
    }

    public final boolean zzie() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    @WorkerThread
    public final List<Pair<zzgi, Long>> zzb(String str, int i, int i2) throws Throwable {
        Cursor cursorQuery;
        byte[] bArrZza;
        zzaf();
        zzcl();
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            try {
                cursorQuery = getWritableDatabase().query("queue", new String[]{"rowid", DataBufferSafeParcelable.DATA_FIELD, "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            } catch (Throwable th) {
                th = th;
                cursorQuery = cursor;
            }
        } catch (SQLiteException e) {
            e = e;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                List<Pair<zzgi, Long>> listEmptyList = Collections.emptyList();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return listEmptyList;
            }
            ArrayList arrayList = new ArrayList();
            int length = 0;
            do {
                long j = cursorQuery.getLong(0);
                try {
                    bArrZza = zzjo().zza(cursorQuery.getBlob(1));
                } catch (IOException e2) {
                    zzgo().zzjd().zze("Failed to unzip queued bundle. appId", zzap.zzbv(str), e2);
                }
                if (!arrayList.isEmpty() && bArrZza.length + length > i2) {
                    break;
                }
                zzyx zzyxVarZzj = zzyx.zzj(bArrZza, 0, bArrZza.length);
                zzgi zzgiVar = new zzgi();
                try {
                    zzgiVar.zza(zzyxVarZzj);
                    if (!cursorQuery.isNull(2)) {
                        zzgiVar.zzayc = Integer.valueOf(cursorQuery.getInt(2));
                    }
                    length += bArrZza.length;
                    arrayList.add(Pair.create(zzgiVar, Long.valueOf(j)));
                } catch (IOException e3) {
                    zzgo().zzjd().zze("Failed to merge queued bundle. appId", zzap.zzbv(str), e3);
                }
                if (!cursorQuery.moveToNext()) {
                    break;
                }
            } while (length <= i2);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        } catch (SQLiteException e4) {
            e = e4;
            cursor = cursorQuery;
            zzgo().zzjd().zze("Error querying bundles. appId", zzap.zzbv(str), e);
            List<Pair<zzgi, Long>> listEmptyList2 = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return listEmptyList2;
        } catch (Throwable th2) {
            th = th2;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    @WorkerThread
    final void zzif() {
        int iDelete;
        zzaf();
        zzcl();
        if (!zzil()) {
            return;
        }
        long j = zzgp().zzanh.get();
        long jElapsedRealtime = zzbx().elapsedRealtime();
        if (Math.abs(jElapsedRealtime - j) > zzaf.zzakb.get().longValue()) {
            zzgp().zzanh.set(jElapsedRealtime);
            zzaf();
            zzcl();
            if (zzil() && (iDelete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzbx().currentTimeMillis()), String.valueOf(zzn.zzhw())})) > 0) {
                zzgo().zzjl().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(iDelete));
            }
        }
    }

    @WorkerThread
    @VisibleForTesting
    final void zzc(List<Long> list) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (!zzil()) {
            return;
        }
        String strJoin = TextUtils.join(",", list);
        StringBuilder sb = new StringBuilder(2 + String.valueOf(strJoin).length());
        sb.append("(");
        sb.append(strJoin);
        sb.append(")");
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder(80 + String.valueOf(string).length());
        sb2.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
        sb2.append(string);
        sb2.append(" AND retry_count =  2147483647 LIMIT 1");
        if (zza(sb2.toString(), (String[]) null) > 0) {
            zzgo().zzjg().zzbx("The number of upload retries exceeds the limit. Will remain unchanged.");
        }
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            StringBuilder sb3 = new StringBuilder(127 + String.valueOf(string).length());
            sb3.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
            sb3.append(string);
            sb3.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
            writableDatabase.execSQL(sb3.toString());
        } catch (SQLiteException e) {
            zzgo().zzjd().zzg("Error incrementing retry count. error", e);
        }
    }

    @WorkerThread
    final void zza(String str, zzfu[] zzfuVarArr) {
        boolean z;
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfuVarArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzcl();
            zzaf();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzfu zzfuVar : zzfuVarArr) {
                zzcl();
                zzaf();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzfuVar);
                Preconditions.checkNotNull(zzfuVar.zzava);
                Preconditions.checkNotNull(zzfuVar.zzauz);
                if (zzfuVar.zzauy == null) {
                    zzgo().zzjg().zzg("Audience with no ID. appId", zzap.zzbv(str));
                } else {
                    int iIntValue = zzfuVar.zzauy.intValue();
                    zzfv[] zzfvVarArr = zzfuVar.zzava;
                    int length = zzfvVarArr.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            if (zzfvVarArr[i].zzave == null) {
                                zzgo().zzjg().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzap.zzbv(str), zzfuVar.zzauy);
                                break;
                            }
                            i++;
                        } else {
                            zzfy[] zzfyVarArr = zzfuVar.zzauz;
                            int length2 = zzfyVarArr.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 < length2) {
                                    if (zzfyVarArr[i2].zzave == null) {
                                        zzgo().zzjg().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzap.zzbv(str), zzfuVar.zzauy);
                                        break;
                                    }
                                    i2++;
                                } else {
                                    zzfv[] zzfvVarArr2 = zzfuVar.zzava;
                                    int length3 = zzfvVarArr2.length;
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 < length3) {
                                            if (zza(str, iIntValue, zzfvVarArr2[i3])) {
                                                i3++;
                                            } else {
                                                z = false;
                                                break;
                                            }
                                        } else {
                                            z = true;
                                            break;
                                        }
                                    }
                                    if (z) {
                                        zzfy[] zzfyVarArr2 = zzfuVar.zzauz;
                                        int length4 = zzfyVarArr2.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= length4) {
                                                break;
                                            }
                                            if (zza(str, iIntValue, zzfyVarArr2[i4])) {
                                                i4++;
                                            } else {
                                                z = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzcl();
                                        zzaf();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase writableDatabase3 = getWritableDatabase();
                                        writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iIntValue)});
                                        writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iIntValue)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzfu zzfuVar2 : zzfuVarArr) {
                arrayList.add(zzfuVar2.zzauy);
            }
            zza(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzfv zzfvVar) {
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfvVar);
        if (TextUtils.isEmpty(zzfvVar.zzavf)) {
            zzgo().zzjg().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzap.zzbv(str), Integer.valueOf(i), String.valueOf(zzfvVar.zzave));
            return false;
        }
        try {
            byte[] bArr = new byte[zzfvVar.zzvu()];
            zzyy zzyyVarZzk = zzyy.zzk(bArr, 0, bArr.length);
            zzfvVar.zza(zzyyVarZzk);
            zzyyVarZzk.zzyt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzfvVar.zzave);
            contentValues.put("event_name", zzfvVar.zzavf);
            contentValues.put(DataBufferSafeParcelable.DATA_FIELD, bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzgo().zzjd().zzg("Failed to insert event filter (got -1). appId", zzap.zzbv(str));
                    return true;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing event filter. appId", zzap.zzbv(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Configuration loss. Failed to serialize event filter. appId", zzap.zzbv(str), e2);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzfy zzfyVar) {
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfyVar);
        if (TextUtils.isEmpty(zzfyVar.zzavu)) {
            zzgo().zzjg().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzap.zzbv(str), Integer.valueOf(i), String.valueOf(zzfyVar.zzave));
            return false;
        }
        try {
            byte[] bArr = new byte[zzfyVar.zzvu()];
            zzyy zzyyVarZzk = zzyy.zzk(bArr, 0, bArr.length);
            zzfyVar.zza(zzyyVarZzk);
            zzyyVarZzk.zzyt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzfyVar.zzave);
            contentValues.put("property_name", zzfyVar.zzavu);
            contentValues.put(DataBufferSafeParcelable.DATA_FIELD, bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) == -1) {
                    zzgo().zzjd().zzg("Failed to insert property filter (got -1). appId", zzap.zzbv(str));
                    return false;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing property filter. appId", zzap.zzbv(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Configuration loss. Failed to serialize property filter. appId", zzap.zzbv(str), e2);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b7  */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzfv>> zzl(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzcl()
            r12.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "event_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            java.lang.String r4 = "app_id=? AND event_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r10 = 0
            r5[r10] = r13     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r11 = 1
            r5[r11] = r14     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            boolean r1 = r14.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r1 != 0) goto L43
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r14 == 0) goto L42
            r14.close()
        L42:
            return r0
        L43:
            byte[] r1 = r14.getBlob(r11)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            int r2 = r1.length     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzyx r1 = com.google.android.gms.internal.measurement.zzyx.zzj(r1, r10, r2)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzfv r2 = new com.google.android.gms.internal.measurement.zzfv     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r2.<init>()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r2.zza(r1)     // Catch: java.io.IOException -> L77 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            int r1 = r14.getInt(r10)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Object r3 = r0.get(r3)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.util.List r3 = (java.util.List) r3     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r3 != 0) goto L73
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r3.<init>()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r0.put(r1, r3)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
        L73:
            r3.add(r2)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            goto L8a
        L77:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzap r2 = r12.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.measurement.internal.zzar r2 = r2.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzap.zzbv(r13)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r2.zze(r3, r4, r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
        L8a:
            boolean r1 = r14.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r1 != 0) goto L43
            if (r14 == 0) goto L95
            r14.close()
        L95:
            return r0
        L96:
            r0 = move-exception
            goto L9d
        L98:
            r13 = move-exception
            r14 = r9
            goto Lb5
        L9b:
            r0 = move-exception
            r14 = r9
        L9d:
            com.google.android.gms.measurement.internal.zzap r1 = r12.zzgo()     // Catch: java.lang.Throwable -> Lb4
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzap.zzbv(r13)     // Catch: java.lang.Throwable -> Lb4
            r1.zze(r2, r13, r0)     // Catch: java.lang.Throwable -> Lb4
            if (r14 == 0) goto Lb3
            r14.close()
        Lb3:
            return r9
        Lb4:
            r13 = move-exception
        Lb5:
            if (r14 == 0) goto Lba
            r14.close()
        Lba:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzl(java.lang.String, java.lang.String):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b7  */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzfy>> zzm(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzcl()
            r12.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "property_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            java.lang.String r4 = "app_id=? AND property_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r10 = 0
            r5[r10] = r13     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r11 = 1
            r5[r11] = r14     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            boolean r1 = r14.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r1 != 0) goto L43
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r14 == 0) goto L42
            r14.close()
        L42:
            return r0
        L43:
            byte[] r1 = r14.getBlob(r11)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            int r2 = r1.length     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzyx r1 = com.google.android.gms.internal.measurement.zzyx.zzj(r1, r10, r2)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzfy r2 = new com.google.android.gms.internal.measurement.zzfy     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r2.<init>()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r2.zza(r1)     // Catch: java.io.IOException -> L77 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            int r1 = r14.getInt(r10)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Object r3 = r0.get(r3)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.util.List r3 = (java.util.List) r3     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r3 != 0) goto L73
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r3.<init>()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r0.put(r1, r3)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
        L73:
            r3.add(r2)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            goto L8a
        L77:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzap r2 = r12.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.measurement.internal.zzar r2 = r2.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzap.zzbv(r13)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r2.zze(r3, r4, r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
        L8a:
            boolean r1 = r14.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r1 != 0) goto L43
            if (r14 == 0) goto L95
            r14.close()
        L95:
            return r0
        L96:
            r0 = move-exception
            goto L9d
        L98:
            r13 = move-exception
            r14 = r9
            goto Lb5
        L9b:
            r0 = move-exception
            r14 = r9
        L9d:
            com.google.android.gms.measurement.internal.zzap r1 = r12.zzgo()     // Catch: java.lang.Throwable -> Lb4
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzap.zzbv(r13)     // Catch: java.lang.Throwable -> Lb4
            r1.zze(r2, r13, r0)     // Catch: java.lang.Throwable -> Lb4
            if (r14 == 0) goto Lb3
            r14.close()
        Lb3:
            return r9
        Lb4:
            r13 = move-exception
        Lb5:
            if (r14 == 0) goto Lba
            r14.close()
        Lba:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzm(java.lang.String, java.lang.String):java.util.Map");
    }

    private final boolean zza(String str, List<Integer> list) throws Throwable {
        Preconditions.checkNotEmpty(str);
        zzcl();
        zzaf();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long jZza = zza("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int iMax = Math.max(0, Math.min(2000, zzgq().zzb(str, zzaf.zzaki)));
            if (jZza <= iMax) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String strJoin = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(strJoin).length() + 2);
            sb.append("(");
            sb.append(strJoin);
            sb.append(")");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder(140 + String.valueOf(string).length());
            sb2.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb2.append(string);
            sb2.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb2.toString(), new String[]{str, Integer.toString(iMax)}) > 0;
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Database error querying filters. appId", zzap.zzbv(str), e);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009e  */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzgj> zzbo(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzcl()
            r11.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            java.lang.String r2 = "audience_id"
            java.lang.String r3 = "current_results"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            java.lang.String r3 = "app_id=?"
            r9 = 1
            java.lang.String[] r4 = new java.lang.String[r9]     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            r10 = 0
            r4[r10] = r12     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            boolean r1 = r0.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            if (r1 != 0) goto L34
            if (r0 == 0) goto L33
            r0.close()
        L33:
            return r8
        L34:
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            r1.<init>()     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
        L39:
            int r2 = r0.getInt(r10)     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            byte[] r3 = r0.getBlob(r9)     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            int r4 = r3.length     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            com.google.android.gms.internal.measurement.zzyx r3 = com.google.android.gms.internal.measurement.zzyx.zzj(r3, r10, r4)     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            com.google.android.gms.internal.measurement.zzgj r4 = new com.google.android.gms.internal.measurement.zzgj     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            r4.<init>()     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            r4.zza(r3)     // Catch: java.io.IOException -> L59 android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            r1.put(r2, r4)     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            goto L70
        L59:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzap r4 = r11.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            com.google.android.gms.measurement.internal.zzar r4 = r4.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzap.zzbv(r12)     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            r4.zzd(r5, r6, r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
        L70:
            boolean r2 = r0.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L7d java.lang.Throwable -> L9b
            if (r2 != 0) goto L39
        L77:
            if (r0 == 0) goto L7c
            r0.close()
        L7c:
            return r1
        L7d:
            r1 = move-exception
            goto L84
        L7f:
            r12 = move-exception
            r0 = r8
            goto L9c
        L82:
            r1 = move-exception
            r0 = r8
        L84:
            com.google.android.gms.measurement.internal.zzap r2 = r11.zzgo()     // Catch: java.lang.Throwable -> L9b
            com.google.android.gms.measurement.internal.zzar r2 = r2.zzjd()     // Catch: java.lang.Throwable -> L9b
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzap.zzbv(r12)     // Catch: java.lang.Throwable -> L9b
            r2.zze(r3, r12, r1)     // Catch: java.lang.Throwable -> L9b
            if (r0 == 0) goto L9a
            r0.close()
        L9a:
            return r8
        L9b:
            r12 = move-exception
        L9c:
            if (r0 == 0) goto La1
            r0.close()
        La1:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzbo(java.lang.String):java.util.Map");
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else {
            if (obj instanceof Double) {
                contentValues.put(str, (Double) obj);
                return;
            }
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzgo().zzjd().zzbx("Loaded invalid null value from database");
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                zzgo().zzjd().zzbx("Loaded invalid blob type value, ignoring it");
                break;
            default:
                zzgo().zzjd().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                break;
        }
        return null;
    }

    @WorkerThread
    public final long zzig() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0L);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(5:37|3|4|38|5)|(5:7|(3:9|10|11)(1:12)|30|31|32)|40|13|(3:15|16|17)(3:18|19|32)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00d0, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00d1, code lost:
    
        r4 = r0;
     */
    @android.support.annotation.WorkerThread
    @com.google.android.gms.common.util.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final long zzn(java.lang.String r19, java.lang.String r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzn(java.lang.String, java.lang.String):long");
    }

    @WorkerThread
    public final long zzih() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0L);
    }

    public final long zza(zzgi zzgiVar) throws IOException {
        long jZzc;
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzgiVar);
        Preconditions.checkNotEmpty(zzgiVar.zztt);
        try {
            byte[] bArr = new byte[zzgiVar.zzvu()];
            zzyy zzyyVarZzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgiVar.zza(zzyyVarZzk);
            zzyyVarZzk.zzyt();
            zzfg zzfgVarZzjo = zzjo();
            Preconditions.checkNotNull(bArr);
            zzfgVarZzjo.zzgm().zzaf();
            MessageDigest messageDigest = zzfk.getMessageDigest();
            if (messageDigest == null) {
                zzfgVarZzjo.zzgo().zzjd().zzbx("Failed to get MD5");
                jZzc = 0;
            } else {
                jZzc = zzfk.zzc(messageDigest.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzgiVar.zztt);
            contentValues.put("metadata_fingerprint", Long.valueOf(jZzc));
            contentValues.put("metadata", bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return jZzc;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing raw event metadata. appId", zzap.zzbv(zzgiVar.zztt), e);
                throw e;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Data loss. Failed to serialize event metadata. appId", zzap.zzbv(zzgiVar.zztt), e2);
            throw e2;
        }
    }

    public final boolean zzii() {
        return zza("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzij() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzbp(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
    /* JADX WARN: Type inference failed for: r5v0, types: [long] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zzah(long r5) throws java.lang.Throwable {
        /*
            r4 = this;
            r4.zzaf()
            r4.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch: java.lang.Throwable -> L41 android.database.sqlite.SQLiteException -> L44
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L41 android.database.sqlite.SQLiteException -> L44
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L41 android.database.sqlite.SQLiteException -> L44
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L41 android.database.sqlite.SQLiteException -> L44
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L41 android.database.sqlite.SQLiteException -> L44
            boolean r1 = r5.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L59
            if (r1 != 0) goto L35
            com.google.android.gms.measurement.internal.zzap r6 = r4.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L59
            com.google.android.gms.measurement.internal.zzar r6 = r6.zzjl()     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L59
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zzbx(r1)     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L59
            if (r5 == 0) goto L34
            r5.close()
        L34:
            return r0
        L35:
            java.lang.String r6 = r5.getString(r6)     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L59
            if (r5 == 0) goto L3e
            r5.close()
        L3e:
            return r6
        L3f:
            r6 = move-exception
            goto L46
        L41:
            r6 = move-exception
            r5 = r0
            goto L5a
        L44:
            r6 = move-exception
            r5 = r0
        L46:
            com.google.android.gms.measurement.internal.zzap r1 = r4.zzgo()     // Catch: java.lang.Throwable -> L59
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: java.lang.Throwable -> L59
            java.lang.String r2 = "Error selecting expired configs"
            r1.zzg(r2, r6)     // Catch: java.lang.Throwable -> L59
            if (r5 == 0) goto L58
            r5.close()
        L58:
            return r0
        L59:
            r6 = move-exception
        L5a:
            if (r5 == 0) goto L5f
            r5.close()
        L5f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzah(long):java.lang.String");
    }

    public final long zzik() throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor = null;
        try {
            try {
                cursorRawQuery = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            } catch (SQLiteException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (!cursorRawQuery.moveToFirst()) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return -1L;
            }
            long j = cursorRawQuery.getLong(0);
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return j;
        } catch (SQLiteException e2) {
            e = e2;
            cursor = cursorRawQuery;
            zzgo().zzjd().zzg("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1L;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorRawQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzgf, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzaf()
            r7.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            r4 = 0
            r3[r4] = r8     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            r6 = 1
            r3[r6] = r5     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            if (r2 != 0) goto L38
            com.google.android.gms.measurement.internal.zzap r8 = r7.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            com.google.android.gms.measurement.internal.zzar r8 = r8.zzjl()     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            java.lang.String r9 = "Main event not found"
            r8.zzbx(r9)     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            if (r1 == 0) goto L37
            r1.close()
        L37:
            return r0
        L38:
            byte[] r2 = r1.getBlob(r4)     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            long r5 = r1.getLong(r6)     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            int r5 = r2.length     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            com.google.android.gms.internal.measurement.zzyx r2 = com.google.android.gms.internal.measurement.zzyx.zzj(r2, r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            com.google.android.gms.internal.measurement.zzgf r4 = new com.google.android.gms.internal.measurement.zzgf     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            r4.<init>()     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            r4.zza(r2)     // Catch: java.io.IOException -> L5e android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            android.util.Pair r8 = android.util.Pair.create(r4, r3)     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            if (r1 == 0) goto L5d
            r1.close()
        L5d:
            return r8
        L5e:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzap r3 = r7.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzap.zzbv(r8)     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            r3.zzd(r4, r8, r9, r2)     // Catch: android.database.sqlite.SQLiteException -> L76 java.lang.Throwable -> L90
            if (r1 == 0) goto L75
            r1.close()
        L75:
            return r0
        L76:
            r8 = move-exception
            goto L7d
        L78:
            r8 = move-exception
            r1 = r0
            goto L91
        L7b:
            r8 = move-exception
            r1 = r0
        L7d:
            com.google.android.gms.measurement.internal.zzap r9 = r7.zzgo()     // Catch: java.lang.Throwable -> L90
            com.google.android.gms.measurement.internal.zzar r9 = r9.zzjd()     // Catch: java.lang.Throwable -> L90
            java.lang.String r2 = "Error selecting main event"
            r9.zzg(r2, r8)     // Catch: java.lang.Throwable -> L90
            if (r1 == 0) goto L8f
            r1.close()
        L8f:
            return r0
        L90:
            r8 = move-exception
        L91:
            if (r1 == 0) goto L96
            r1.close()
        L96:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    public final boolean zza(String str, Long l, long j, zzgf zzgfVar) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzgfVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        try {
            byte[] bArr = new byte[zzgfVar.zzvu()];
            zzyy zzyyVarZzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgfVar.zza(zzyyVarZzk);
            zzyyVarZzk.zzyt();
            zzgo().zzjl().zze("Saving complex main event, appId, data size", zzgl().zzbs(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("main_event_params", null, contentValues, 5) == -1) {
                    zzgo().zzjd().zzg("Failed to insert complex main event (got -1). appId", zzap.zzbv(str));
                    return false;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing complex main event. appId", zzap.zzbv(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzap.zzbv(str), l, e2);
            return false;
        }
    }

    public final boolean zza(zzy zzyVar, long j, boolean z) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzyVar);
        Preconditions.checkNotEmpty(zzyVar.zztt);
        zzgf zzgfVar = new zzgf();
        zzgfVar.zzawv = Long.valueOf(zzyVar.zzaic);
        zzgfVar.zzawt = new zzgg[zzyVar.zzaid.size()];
        int i = 0;
        for (String str : zzyVar.zzaid) {
            zzgg zzggVar = new zzgg();
            zzgfVar.zzawt[i] = zzggVar;
            zzggVar.name = str;
            zzjo().zza(zzggVar, zzyVar.zzaid.get(str));
            i++;
        }
        try {
            byte[] bArr = new byte[zzgfVar.zzvu()];
            zzyy zzyyVarZzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgfVar.zza(zzyyVarZzk);
            zzyyVarZzk.zzyt();
            zzgo().zzjl().zze("Saving event, name, data size", zzgl().zzbs(zzyVar.name), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzyVar.zztt);
            contentValues.put("name", zzyVar.name);
            contentValues.put(AppMeasurement.Param.TIMESTAMP, Long.valueOf(zzyVar.timestamp));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(DataBufferSafeParcelable.DATA_FIELD, bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) == -1) {
                    zzgo().zzjd().zzg("Failed to insert raw event (got -1). appId", zzap.zzbv(zzyVar.zztt));
                    return false;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing raw event. appId", zzap.zzbv(zzyVar.zztt), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Data loss. Failed to serialize event params/data. appId", zzap.zzbv(zzyVar.zztt), e2);
            return false;
        }
    }

    private final boolean zzil() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }
}
