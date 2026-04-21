package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class zze {

    public static class zza implements BaseColumns {
        private static HashMap<Uri, zzh> zzagq = new HashMap<>();

        private static zzh zza(ContentResolver contentResolver, Uri uri) {
            zzh zzhVar = zzagq.get(uri);
            if (zzhVar == null) {
                zzh zzhVar2 = new zzh();
                zzagq.put(uri, zzhVar2);
                contentResolver.registerContentObserver(uri, true, new zzf(null, zzhVar2));
                return zzhVar2;
            }
            if (!zzhVar.zzagu.getAndSet(false)) {
                return zzhVar;
            }
            synchronized (zzhVar) {
                zzhVar.zzags.clear();
                zzhVar.zzagt = new Object();
            }
            return zzhVar;
        }

        protected static String zza(ContentResolver contentResolver, Uri uri, String str) throws Throwable {
            zzh zzhVarZza;
            String string;
            Cursor cursorQuery;
            synchronized (zza.class) {
                zzhVarZza = zza(contentResolver, uri);
            }
            synchronized (zzhVarZza) {
                Object obj = zzhVarZza.zzagt;
                if (zzhVarZza.zzags.containsKey(str)) {
                    return zzhVarZza.zzags.get(str);
                }
                Cursor cursor = null;
                try {
                    try {
                        cursorQuery = contentResolver.query(uri, new String[]{FirebaseAnalytics.Param.VALUE}, "name=?", new String[]{str}, null);
                    } catch (SQLException e) {
                        e = e;
                        string = null;
                    }
                    if (cursorQuery != null) {
                        try {
                            try {
                            } catch (SQLException e2) {
                                e = e2;
                                string = null;
                            }
                            if (cursorQuery.moveToFirst()) {
                                string = cursorQuery.getString(0);
                                try {
                                    zza(zzhVarZza, obj, str, string);
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                        return string;
                                    }
                                } catch (SQLException e3) {
                                    e = e3;
                                    cursor = cursorQuery;
                                    Log.e("GoogleSettings", "Can't get key " + str + " from " + uri, e);
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                }
                                return string;
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    zza(zzhVarZza, obj, str, null);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        private static void zza(zzh zzhVar, Object obj, String str, String str2) {
            synchronized (zzhVar) {
                if (obj == zzhVar.zzagt) {
                    zzhVar.zzags.put(str, str2);
                }
            }
        }
    }
}
