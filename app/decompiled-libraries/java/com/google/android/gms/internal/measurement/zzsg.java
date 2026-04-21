package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class zzsg {
    private static HashMap<String, String> zzbqh;
    private static Object zzbqm;
    private static boolean zzbqn;
    private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri zzbqd = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzbqe = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzbqf = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean zzbqg = new AtomicBoolean();
    private static final HashMap<String, Boolean> zzbqi = new HashMap<>();
    private static final HashMap<String, Integer> zzbqj = new HashMap<>();
    private static final HashMap<String, Long> zzbqk = new HashMap<>();
    private static final HashMap<String, Float> zzbql = new HashMap<>();
    private static String[] zzbqo = new String[0];

    private static void zza(ContentResolver contentResolver) {
        if (zzbqh == null) {
            zzbqg.set(false);
            zzbqh = new HashMap<>();
            zzbqm = new Object();
            zzbqn = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new zzsh(null));
            return;
        }
        if (zzbqg.getAndSet(false)) {
            zzbqh.clear();
            zzbqi.clear();
            zzbqj.clear();
            zzbqk.clear();
            zzbql.clear();
            zzbqm = new Object();
            zzbqn = false;
        }
    }

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzsg.class) {
            zza(contentResolver);
            Object obj = zzbqm;
            if (zzbqh.containsKey(str)) {
                String str3 = zzbqh.get(str);
                if (str3 == null) {
                    str3 = null;
                }
                return str3;
            }
            for (String str4 : zzbqo) {
                if (str.startsWith(str4)) {
                    if (!zzbqn || zzbqh.isEmpty()) {
                        zzbqh.putAll(zza(contentResolver, zzbqo));
                        zzbqn = true;
                        if (zzbqh.containsKey(str)) {
                            String str5 = zzbqh.get(str);
                            if (str5 == null) {
                                str5 = null;
                            }
                            return str5;
                        }
                    }
                    return null;
                }
            }
            Cursor cursorQuery = contentResolver.query(CONTENT_URI, null, null, new String[]{str}, null);
            if (cursorQuery == null) {
                return null;
            }
            try {
                if (!cursorQuery.moveToFirst()) {
                    zza(obj, str, (String) null);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                String string = cursorQuery.getString(1);
                if (string != null && string.equals(null)) {
                    string = null;
                }
                zza(obj, str, string);
                if (string == null) {
                    string = null;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return string;
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzsg.class) {
            if (obj == zzbqm) {
                zzbqh.put(str, str2);
            }
        }
    }

    public static boolean zza(ContentResolver contentResolver, String str, boolean z) {
        Object objZzb = zzb(contentResolver);
        Boolean bool = (Boolean) zza(zzbqi, str, Boolean.valueOf(z));
        if (bool != null) {
            return bool.booleanValue();
        }
        String strZza = zza(contentResolver, str, (String) null);
        if (strZza != null && !strZza.equals("")) {
            if (zzbqe.matcher(strZza).matches()) {
                bool = true;
                z = true;
            } else if (zzbqf.matcher(strZza).matches()) {
                bool = false;
                z = false;
            } else {
                Log.w("Gservices", "attempt to read gservices key " + str + " (value \"" + strZza + "\") as boolean");
            }
        }
        zza(objZzb, zzbqi, str, bool);
        return z;
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor cursorQuery = contentResolver.query(zzbqd, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (cursorQuery == null) {
            return treeMap;
        }
        while (cursorQuery.moveToNext()) {
            try {
                treeMap.put(cursorQuery.getString(0), cursorQuery.getString(1));
            } finally {
                cursorQuery.close();
            }
        }
        return treeMap;
    }

    private static Object zzb(ContentResolver contentResolver) {
        Object obj;
        synchronized (zzsg.class) {
            zza(contentResolver);
            obj = zzbqm;
        }
        return obj;
    }

    private static <T> T zza(HashMap<String, T> map, String str, T t) {
        synchronized (zzsg.class) {
            if (map.containsKey(str)) {
                T t2 = map.get(str);
                if (t2 == null) {
                    t2 = t;
                }
                return t2;
            }
            return null;
        }
    }

    private static <T> void zza(Object obj, HashMap<String, T> map, String str, T t) {
        synchronized (zzsg.class) {
            if (obj == zzbqm) {
                map.put(str, t);
                zzbqh.remove(str);
            }
        }
    }
}
