package com.google.android.gms.common.util;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MapUtils {
    public static <K, V> K getKeyFromMap(Map<K, V> map, K k) {
        if (!map.containsKey(k)) {
            return null;
        }
        for (K k2 : map.keySet()) {
            if (k2.equals(k)) {
                return k2;
            }
        }
        return null;
    }

    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> map) {
        String str;
        sb.append("{");
        boolean z = true;
        for (String str2 : map.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            String str3 = map.get(str2);
            sb.append("\"");
            sb.append(str2);
            sb.append("\":");
            if (str3 == null) {
                str = "null";
            } else {
                sb.append("\"");
                sb.append(str3);
                str = "\"";
            }
            sb.append(str);
        }
        sb.append("}");
    }
}
