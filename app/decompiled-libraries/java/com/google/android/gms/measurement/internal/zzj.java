package com.google.android.gms.measurement.internal;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzge;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: classes.dex */
final class zzj extends zzez {
    zzj(zzfa zzfaVar) {
        super(zzfaVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzez
    protected final boolean zzgt() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02c7  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final com.google.android.gms.internal.measurement.zzgd[] zza(java.lang.String r90, com.google.android.gms.internal.measurement.zzgf[] r91, com.google.android.gms.internal.measurement.zzgl[] r92) {
        /*
            Method dump skipped, instruction units count: 3370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzj.zza(java.lang.String, com.google.android.gms.internal.measurement.zzgf[], com.google.android.gms.internal.measurement.zzgl[]):com.google.android.gms.internal.measurement.zzgd[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Boolean zza(zzfv zzfvVar, String str, zzgg[] zzggVarArr, long j) {
        Boolean boolZza;
        if (zzfvVar.zzavi != null) {
            Boolean boolZza2 = zza(j, zzfvVar.zzavi);
            if (boolZza2 == null) {
                return null;
            }
            if (!boolZza2.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        for (zzfw zzfwVar : zzfvVar.zzavg) {
            if (TextUtils.isEmpty(zzfwVar.zzavn)) {
                zzgo().zzjg().zzg("null or empty param name in filter. event", zzgl().zzbs(str));
                return null;
            }
            hashSet.add(zzfwVar.zzavn);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzgg zzggVar : zzggVarArr) {
            if (hashSet.contains(zzggVar.name)) {
                if (zzggVar.zzawx != null) {
                    arrayMap.put(zzggVar.name, zzggVar.zzawx);
                } else if (zzggVar.zzauh != null) {
                    arrayMap.put(zzggVar.name, zzggVar.zzauh);
                } else if (zzggVar.zzamp != null) {
                    arrayMap.put(zzggVar.name, zzggVar.zzamp);
                } else {
                    zzgo().zzjg().zze("Unknown value for param. event, param", zzgl().zzbs(str), zzgl().zzbt(zzggVar.name));
                    return null;
                }
            }
        }
        for (zzfw zzfwVar2 : zzfvVar.zzavg) {
            boolean zEquals = Boolean.TRUE.equals(zzfwVar2.zzavm);
            String str2 = zzfwVar2.zzavn;
            if (TextUtils.isEmpty(str2)) {
                zzgo().zzjg().zzg("Event has empty param name. event", zzgl().zzbs(str));
                return null;
            }
            V v = arrayMap.get(str2);
            if (v instanceof Long) {
                if (zzfwVar2.zzavl == null) {
                    zzgo().zzjg().zze("No number filter for long param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                Boolean boolZza3 = zza(((Long) v).longValue(), zzfwVar2.zzavl);
                if (boolZza3 == null) {
                    return null;
                }
                if ((true ^ boolZza3.booleanValue()) ^ zEquals) {
                    return false;
                }
            } else if (v instanceof Double) {
                if (zzfwVar2.zzavl == null) {
                    zzgo().zzjg().zze("No number filter for double param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                Boolean boolZza4 = zza(((Double) v).doubleValue(), zzfwVar2.zzavl);
                if (boolZza4 == null) {
                    return null;
                }
                if ((true ^ boolZza4.booleanValue()) ^ zEquals) {
                    return false;
                }
            } else if (v instanceof String) {
                if (zzfwVar2.zzavk != null) {
                    boolZza = zza((String) v, zzfwVar2.zzavk);
                } else if (zzfwVar2.zzavl != null) {
                    String str3 = (String) v;
                    if (zzfg.zzcp(str3)) {
                        boolZza = zza(str3, zzfwVar2.zzavl);
                    } else {
                        zzgo().zzjg().zze("Invalid param value for number filter. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                        return null;
                    }
                } else {
                    zzgo().zzjg().zze("No filter for String param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                if (boolZza == null) {
                    return null;
                }
                if ((true ^ boolZza.booleanValue()) ^ zEquals) {
                    return false;
                }
            } else {
                if (v == 0) {
                    zzgo().zzjl().zze("Missing param for filter. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return false;
                }
                zzgo().zzjg().zze("Unknown param type. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                return null;
            }
        }
        return true;
    }

    private final Boolean zza(zzfy zzfyVar, zzgl zzglVar) {
        zzfw zzfwVar = zzfyVar.zzavv;
        if (zzfwVar == null) {
            zzgo().zzjg().zzg("Missing property filter. property", zzgl().zzbu(zzglVar.name));
            return null;
        }
        boolean zEquals = Boolean.TRUE.equals(zzfwVar.zzavm);
        if (zzglVar.zzawx != null) {
            if (zzfwVar.zzavl == null) {
                zzgo().zzjg().zzg("No number filter for long property. property", zzgl().zzbu(zzglVar.name));
                return null;
            }
            return zza(zza(zzglVar.zzawx.longValue(), zzfwVar.zzavl), zEquals);
        }
        if (zzglVar.zzauh != null) {
            if (zzfwVar.zzavl == null) {
                zzgo().zzjg().zzg("No number filter for double property. property", zzgl().zzbu(zzglVar.name));
                return null;
            }
            return zza(zza(zzglVar.zzauh.doubleValue(), zzfwVar.zzavl), zEquals);
        }
        if (zzglVar.zzamp != null) {
            if (zzfwVar.zzavk == null) {
                if (zzfwVar.zzavl == null) {
                    zzgo().zzjg().zzg("No string or number filter defined. property", zzgl().zzbu(zzglVar.name));
                } else {
                    if (zzfg.zzcp(zzglVar.zzamp)) {
                        return zza(zza(zzglVar.zzamp, zzfwVar.zzavl), zEquals);
                    }
                    zzgo().zzjg().zze("Invalid user property value for Numeric number filter. property, value", zzgl().zzbu(zzglVar.name), zzglVar.zzamp);
                }
                return null;
            }
            return zza(zza(zzglVar.zzamp, zzfwVar.zzavk), zEquals);
        }
        zzgo().zzjg().zzg("User property has no value, property", zzgl().zzbu(zzglVar.name));
        return null;
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzfz zzfzVar) {
        String upperCase;
        List<String> listAsList;
        String str2;
        Preconditions.checkNotNull(zzfzVar);
        if (str == null || zzfzVar.zzavw == null || zzfzVar.zzavw.intValue() == 0) {
            return null;
        }
        if (zzfzVar.zzavw.intValue() == 6) {
            if (zzfzVar.zzavz == null || zzfzVar.zzavz.length == 0) {
                return null;
            }
        } else if (zzfzVar.zzavx == null) {
            return null;
        }
        int iIntValue = zzfzVar.zzavw.intValue();
        boolean z = zzfzVar.zzavy != null && zzfzVar.zzavy.booleanValue();
        if (z || iIntValue == 1 || iIntValue == 6) {
            upperCase = zzfzVar.zzavx;
        } else {
            upperCase = zzfzVar.zzavx.toUpperCase(Locale.ENGLISH);
        }
        String str3 = upperCase;
        if (zzfzVar.zzavz != null) {
            String[] strArr = zzfzVar.zzavz;
            if (z) {
                listAsList = Arrays.asList(strArr);
            } else {
                ArrayList arrayList = new ArrayList();
                for (String str4 : strArr) {
                    arrayList.add(str4.toUpperCase(Locale.ENGLISH));
                }
                listAsList = arrayList;
            }
        } else {
            listAsList = null;
        }
        if (iIntValue != 1) {
            str2 = null;
        } else {
            str2 = str3;
        }
        return zza(str, iIntValue, z, str3, listAsList, str2);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                } catch (PatternSyntaxException e) {
                    zzgo().zzjg().zzg("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
                break;
        }
        return null;
    }

    private final Boolean zza(long j, zzfx zzfxVar) {
        try {
            return zza(new BigDecimal(j), zzfxVar, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(double d, zzfx zzfxVar) {
        try {
            return zza(new BigDecimal(d), zzfxVar, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(String str, zzfx zzfxVar) {
        if (!zzfg.zzcp(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzfxVar, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean zza(BigDecimal bigDecimal, zzfx zzfxVar, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzfxVar);
        if (zzfxVar.zzavo == null || zzfxVar.zzavo.intValue() == 0) {
            return null;
        }
        if (zzfxVar.zzavo.intValue() == 4) {
            if (zzfxVar.zzavr == null || zzfxVar.zzavs == null) {
                return null;
            }
        } else if (zzfxVar.zzavq == null) {
            return null;
        }
        int iIntValue = zzfxVar.zzavo.intValue();
        if (zzfxVar.zzavo.intValue() == 4) {
            if (!zzfg.zzcp(zzfxVar.zzavr) || !zzfg.zzcp(zzfxVar.zzavs)) {
                return null;
            }
            try {
                BigDecimal bigDecimal5 = new BigDecimal(zzfxVar.zzavr);
                bigDecimal4 = new BigDecimal(zzfxVar.zzavs);
                bigDecimal3 = bigDecimal5;
                bigDecimal2 = null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            if (!zzfg.zzcp(zzfxVar.zzavq)) {
                return null;
            }
            try {
                bigDecimal2 = new BigDecimal(zzfxVar.zzavq);
                bigDecimal3 = null;
                bigDecimal4 = null;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        if (iIntValue == 4) {
            if (bigDecimal3 == null) {
                return null;
            }
        } else {
            if (bigDecimal2 != null) {
            }
            return null;
        }
        switch (iIntValue) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                if (d != 0.0d) {
                    if (bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1) {
                        z = true;
                    }
                }
                break;
            case 4:
                if (bigDecimal.compareTo(bigDecimal3) != -1 && bigDecimal.compareTo(bigDecimal4) != 1) {
                    z = true;
                }
                break;
        }
        return null;
    }

    private static zzge[] zzd(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        int i = 0;
        zzge[] zzgeVarArr = new zzge[map.size()];
        for (Integer num : map.keySet()) {
            zzge zzgeVar = new zzge();
            zzgeVar.zzawq = num;
            zzgeVar.zzawr = map.get(num);
            zzgeVarArr[i] = zzgeVar;
            i++;
        }
        return zzgeVarArr;
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = map.get(Integer.valueOf(i));
        long j2 = j / 1000;
        if (l == null || j2 > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j2));
        }
    }

    private static void zzb(Map<Integer, List<Long>> map, int i, long j) {
        List<Long> arrayList = map.get(Integer.valueOf(i));
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            map.put(Integer.valueOf(i), arrayList);
        }
        arrayList.add(Long.valueOf(j / 1000));
    }
}
