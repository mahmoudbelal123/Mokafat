package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class zzu {
    @WorkerThread
    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(22 + String.valueOf(str).length());
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
        try {
            Collections.addAll(hashSet, cursorRawQuery.getColumnNames());
            return hashSet;
        } finally {
            cursorRawQuery.close();
        }
    }

    @WorkerThread
    static void zza(zzap zzapVar, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzapVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(zzapVar, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        try {
            if (zzapVar == null) {
                throw new IllegalArgumentException("Monitor must not be null");
            }
            Set<String> setZzb = zzb(sQLiteDatabase, str);
            for (String str4 : str3.split(",")) {
                if (!setZzb.remove(str4)) {
                    StringBuilder sb = new StringBuilder(35 + String.valueOf(str).length() + String.valueOf(str4).length());
                    sb.append("Table ");
                    sb.append(str);
                    sb.append(" is missing required column: ");
                    sb.append(str4);
                    throw new SQLiteException(sb.toString());
                }
            }
            if (strArr != null) {
                for (int i = 0; i < strArr.length; i += 2) {
                    if (!setZzb.remove(strArr[i])) {
                        sQLiteDatabase.execSQL(strArr[i + 1]);
                    }
                }
            }
            if (!setZzb.isEmpty()) {
                zzapVar.zzjg().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", setZzb));
            }
        } catch (SQLiteException e) {
            zzapVar.zzjd().zzg("Failed to verify columns on table that was just created", str);
            throw e;
        }
    }

    @WorkerThread
    private static boolean zza(zzap zzapVar, SQLiteDatabase sQLiteDatabase, String str) throws Throwable {
        Cursor cursorQuery;
        if (zzapVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Cursor cursor = null;
        try {
            try {
                cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            } catch (SQLiteException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean zMoveToFirst = cursorQuery.moveToFirst();
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return zMoveToFirst;
        } catch (SQLiteException e2) {
            cursor = cursorQuery;
            e = e2;
            zzapVar.zzjg().zze("Error querying for table", str, e);
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    static void zza(zzap zzapVar, SQLiteDatabase sQLiteDatabase) {
        if (zzapVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzapVar.zzjg().zzbx("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzapVar.zzjg().zzbx("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzapVar.zzjg().zzbx("Failed to turn on database read permission for owner");
        }
        if (!file.setWritable(true, true)) {
            zzapVar.zzjg().zzbx("Failed to turn on database write permission for owner");
        }
    }
}
