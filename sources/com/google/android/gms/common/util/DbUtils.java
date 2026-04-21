package com.google.android.gms.common.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.stable.zzk;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class DbUtils {
    private DbUtils() {
    }

    public static void clearDatabase(SQLiteDatabase sQLiteDatabase) {
        zza(sQLiteDatabase, "table", "sqlite_sequence", "android_metadata");
        zza(sQLiteDatabase, "trigger", new String[0]);
        zza(sQLiteDatabase, "view", new String[0]);
    }

    public static long countCurrentRowBytes(Cursor cursor) {
        return countCurrentRowBytes(cursor, Charset.forName(Key.STRING_CHARSET_NAME));
    }

    public static long countCurrentRowBytes(Cursor cursor, Charset charset) {
        long j;
        int length;
        long j2 = 0;
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            switch (cursor.getType(i)) {
                case 0:
                case 1:
                case 2:
                    j = 4;
                    j2 += j;
                    break;
                case 3:
                    length = cursor.getString(i).getBytes(charset).length;
                    j = length;
                    j2 += j;
                    break;
                case 4:
                    length = cursor.getBlob(i).length;
                    j = length;
                    j2 += j;
                    break;
            }
        }
        return j2;
    }

    public static long getDatabaseSize(Context context, String str) {
        try {
            File databasePath = context.getDatabasePath(str);
            if (databasePath != null) {
                return databasePath.length();
            }
            return 0L;
        } catch (SecurityException e) {
            String strValueOf = String.valueOf(str);
            Log.w("DbUtils", strValueOf.length() != 0 ? "Failed to get db size for ".concat(strValueOf) : new String("Failed to get db size for "));
            return 0L;
        }
    }

    @Nullable
    public static Integer getIntegerFromCursor(Cursor cursor, int i) {
        return getIntegerFromCursor(cursor, i, null);
    }

    @Nullable
    public static Integer getIntegerFromCursor(Cursor cursor, int i, @Nullable Integer num) {
        return (i < 0 || cursor.isNull(i)) ? num : Integer.valueOf(cursor.getInt(i));
    }

    @Nullable
    public static Long getLongFromCursor(Cursor cursor, int i) {
        return getLongFromCursor(cursor, i, null);
    }

    @Nullable
    public static Long getLongFromCursor(Cursor cursor, int i, @Nullable Long l) {
        return (i < 0 || cursor.isNull(i)) ? l : Long.valueOf(cursor.getLong(i));
    }

    @Nullable
    public static String getStringFromCursor(Cursor cursor, int i) {
        return getStringFromCursor(cursor, i, null);
    }

    @Nullable
    public static String getStringFromCursor(Cursor cursor, int i, @Nullable String str) {
        return (i < 0 || cursor.isNull(i)) ? str : cursor.getString(i);
    }

    public static void putIntegerIntoContentValues(ContentValues contentValues, String str, @Nullable Integer num) {
        if (num != null) {
            contentValues.put(str, num);
        } else {
            contentValues.putNull(str);
        }
    }

    public static void putLongIntoContentValues(ContentValues contentValues, String str, @Nullable Long l) {
        if (l != null) {
            contentValues.put(str, l);
        } else {
            contentValues.putNull(str);
        }
    }

    public static void putStringIntoContentValues(ContentValues contentValues, String str, @Nullable String str2) {
        if (str2 != null) {
            contentValues.put(str, str2);
        } else {
            contentValues.putNull(str);
        }
    }

    private static void zza(SQLiteDatabase sQLiteDatabase, String str, String... strArr) {
        Preconditions.checkArgument("table".equals(str) || "view".equals(str) || "trigger".equals(str));
        Cursor cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "type == ?", new String[]{str}, null, null, null);
        Throwable th = null;
        try {
            try {
                Set of = CollectionUtils.setOf((Object[]) strArr);
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(0);
                    if (!of.contains(string)) {
                        StringBuilder sb = new StringBuilder(8 + String.valueOf(str).length() + String.valueOf(string).length());
                        sb.append("DROP ");
                        sb.append(str);
                        sb.append(" '");
                        sb.append(string);
                        sb.append("'");
                        sQLiteDatabase.execSQL(sb.toString());
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } catch (Throwable th3) {
            if (cursorQuery != null) {
                if (th != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th4) {
                        zzk.zza(th, th4);
                    }
                } else {
                    cursorQuery.close();
                }
            }
            throw th3;
        }
    }
}
