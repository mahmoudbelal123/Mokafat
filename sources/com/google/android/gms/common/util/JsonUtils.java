package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class JsonUtils {
    private static final Pattern zzaae = Pattern.compile("\\\\.");
    private static final Pattern zzaaf = Pattern.compile("[\\\\\"/\b\f\n\r\t]");

    private JsonUtils() {
    }

    public static boolean areJsonStringsEquivalent(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        try {
            return areJsonValuesEquivalent(new JSONObject(str), new JSONObject(str2));
        } catch (JSONException e) {
            try {
                return areJsonValuesEquivalent(new JSONArray(str), new JSONArray(str2));
            } catch (JSONException e2) {
                return false;
            }
        }
    }

    public static boolean areJsonValuesEquivalent(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if ((obj instanceof JSONObject) && (obj2 instanceof JSONObject)) {
            JSONObject jSONObject = (JSONObject) obj;
            JSONObject jSONObject2 = (JSONObject) obj2;
            if (jSONObject.length() != jSONObject2.length()) {
                return false;
            }
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!jSONObject2.has(next)) {
                    return false;
                }
                try {
                    if (!areJsonValuesEquivalent(jSONObject.get(next), jSONObject2.get(next))) {
                        return false;
                    }
                } catch (JSONException e) {
                    return false;
                }
            }
            return true;
        }
        if (!(obj instanceof JSONArray) || !(obj2 instanceof JSONArray)) {
            return obj.equals(obj2);
        }
        JSONArray jSONArray = (JSONArray) obj;
        JSONArray jSONArray2 = (JSONArray) obj2;
        if (jSONArray.length() != jSONArray2.length()) {
            return false;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (!areJsonValuesEquivalent(jSONArray.get(i), jSONArray2.get(i))) {
                    return false;
                }
            } catch (JSONException e2) {
                return false;
            }
        }
        return true;
    }

    public static String escapeString(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = zzaaf.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            char cCharAt = matcher.group().charAt(0);
            if (cCharAt == '\"') {
                str2 = "\\\\\\\"";
            } else if (cCharAt == '/') {
                str2 = "\\\\/";
            } else if (cCharAt != '\\') {
                switch (cCharAt) {
                    case '\b':
                        str2 = "\\\\b";
                        break;
                    case '\t':
                        str2 = "\\\\t";
                        break;
                    case '\n':
                        str2 = "\\\\n";
                        break;
                    default:
                        switch (cCharAt) {
                            case '\f':
                                str2 = "\\\\f";
                                break;
                            case '\r':
                                str2 = "\\\\r";
                                break;
                        }
                        break;
                }
            } else {
                str2 = "\\\\\\\\";
            }
            matcher.appendReplacement(stringBuffer, str2);
        }
        if (stringBuffer == null) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String unescapeString(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strUnescape = UnicodeUtils.unescape(str);
        Matcher matcher = zzaae.matcher(strUnescape);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            char cCharAt = matcher.group().charAt(1);
            if (cCharAt == '\"') {
                str2 = "\"";
            } else if (cCharAt == '/') {
                str2 = "/";
            } else if (cCharAt == '\\') {
                str2 = "\\\\";
            } else if (cCharAt == 'b') {
                str2 = "\b";
            } else if (cCharAt == 'f') {
                str2 = "\f";
            } else if (cCharAt == 'n') {
                str2 = "\n";
            } else if (cCharAt == 'r') {
                str2 = "\r";
            } else {
                if (cCharAt != 't') {
                    throw new IllegalStateException("Found an escaped character that should never be.");
                }
                str2 = "\t";
            }
            matcher.appendReplacement(stringBuffer, str2);
        }
        if (stringBuffer == null) {
            return strUnescape;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
