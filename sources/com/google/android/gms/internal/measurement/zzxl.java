package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
final class zzxl {
    private static final Class<?> zzcbw = zzxu();
    private static final zzyb<?, ?> zzcbx = zzx(false);
    private static final zzyb<?, ?> zzcby = zzx(true);
    private static final zzyb<?, ?> zzcbz = new zzyd();

    public static void zzj(Class<?> cls) {
        if (!zzvm.class.isAssignableFrom(cls) && zzcbw != null && !zzcbw.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzyw zzywVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzyw zzywVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzud> list, zzyw zzywVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzyw zzywVar, zzxj zzxjVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zza(i, list, zzxjVar);
        }
    }

    public static void zzb(int i, List<?> list, zzyw zzywVar, zzxj zzxjVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzywVar.zzb(i, list, zzxjVar);
        }
    }

    static int zzx(List<Long> list) {
        int iZzay;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            iZzay = 0;
            while (i < size) {
                iZzay += zzut.zzay(zzwhVar.getLong(i));
                i++;
            }
        } else {
            iZzay = 0;
            while (i < size) {
                iZzay += zzut.zzay(list.get(i).longValue());
                i++;
            }
        }
        return iZzay;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzx(list) + (list.size() * zzut.zzbb(i));
    }

    static int zzy(List<Long> list) {
        int iZzaz;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            iZzaz = 0;
            while (i < size) {
                iZzaz += zzut.zzaz(zzwhVar.getLong(i));
                i++;
            }
        } else {
            iZzaz = 0;
            while (i < size) {
                iZzaz += zzut.zzaz(list.get(i).longValue());
                i++;
            }
        }
        return iZzaz;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzut.zzbb(i));
    }

    static int zzz(List<Long> list) {
        int iZzba;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            iZzba = 0;
            while (i < size) {
                iZzba += zzut.zzba(zzwhVar.getLong(i));
                i++;
            }
        } else {
            iZzba = 0;
            while (i < size) {
                iZzba += zzut.zzba(list.get(i).longValue());
                i++;
            }
        }
        return iZzba;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzz(list) + (size * zzut.zzbb(i));
    }

    static int zzaa(List<Integer> list) {
        int iZzbh;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            iZzbh = 0;
            while (i < size) {
                iZzbh += zzut.zzbh(zzvnVar.getInt(i));
                i++;
            }
        } else {
            iZzbh = 0;
            while (i < size) {
                iZzbh += zzut.zzbh(list.get(i).intValue());
                i++;
            }
        }
        return iZzbh;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaa(list) + (size * zzut.zzbb(i));
    }

    static int zzab(List<Integer> list) {
        int iZzbc;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            iZzbc = 0;
            while (i < size) {
                iZzbc += zzut.zzbc(zzvnVar.getInt(i));
                i++;
            }
        } else {
            iZzbc = 0;
            while (i < size) {
                iZzbc += zzut.zzbc(list.get(i).intValue());
                i++;
            }
        }
        return iZzbc;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzab(list) + (size * zzut.zzbb(i));
    }

    static int zzac(List<Integer> list) {
        int iZzbd;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            iZzbd = 0;
            while (i < size) {
                iZzbd += zzut.zzbd(zzvnVar.getInt(i));
                i++;
            }
        } else {
            iZzbd = 0;
            while (i < size) {
                iZzbd += zzut.zzbd(list.get(i).intValue());
                i++;
            }
        }
        return iZzbd;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzac(list) + (size * zzut.zzbb(i));
    }

    static int zzad(List<Integer> list) {
        int iZzbe;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            iZzbe = 0;
            while (i < size) {
                iZzbe += zzut.zzbe(zzvnVar.getInt(i));
                i++;
            }
        } else {
            iZzbe = 0;
            while (i < size) {
                iZzbe += zzut.zzbe(list.get(i).intValue());
                i++;
            }
        }
        return iZzbe;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzad(list) + (size * zzut.zzbb(i));
    }

    static int zzae(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzut.zzk(i, 0);
    }

    static int zzaf(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzut.zzg(i, 0L);
    }

    static int zzag(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzut.zzc(i, true);
    }

    static int zzc(int i, List<?> list) {
        int iZzfx;
        int iZzfx2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzbb = zzut.zzbb(i) * size;
        if (list instanceof zzwc) {
            zzwc zzwcVar = (zzwc) list;
            while (i2 < size) {
                Object raw = zzwcVar.getRaw(i2);
                if (raw instanceof zzud) {
                    iZzfx2 = zzut.zzb((zzud) raw);
                } else {
                    iZzfx2 = zzut.zzfx((String) raw);
                }
                iZzbb += iZzfx2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzud) {
                    iZzfx = zzut.zzb((zzud) obj);
                } else {
                    iZzfx = zzut.zzfx((String) obj);
                }
                iZzbb += iZzfx;
                i2++;
            }
        }
        return iZzbb;
    }

    static int zzc(int i, Object obj, zzxj zzxjVar) {
        if (obj instanceof zzwa) {
            return zzut.zza(i, (zzwa) obj);
        }
        return zzut.zzb(i, (zzwt) obj, zzxjVar);
    }

    static int zzc(int i, List<?> list, zzxj zzxjVar) {
        int iZzb;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzbb = zzut.zzbb(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzwa) {
                iZzb = zzut.zza((zzwa) obj);
            } else {
                iZzb = zzut.zzb((zzwt) obj, zzxjVar);
            }
            iZzbb += iZzb;
        }
        return iZzbb;
    }

    static int zzd(int i, List<zzud> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzbb = size * zzut.zzbb(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzbb += zzut.zzb(list.get(i2));
        }
        return iZzbb;
    }

    static int zzd(int i, List<zzwt> list, zzxj zzxjVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzc = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzc += zzut.zzc(i, list.get(i2), zzxjVar);
        }
        return iZzc;
    }

    public static zzyb<?, ?> zzxr() {
        return zzcbx;
    }

    public static zzyb<?, ?> zzxs() {
        return zzcby;
    }

    public static zzyb<?, ?> zzxt() {
        return zzcbz;
    }

    private static zzyb<?, ?> zzx(boolean z) {
        try {
            Class<?> clsZzxv = zzxv();
            if (clsZzxv == null) {
                return null;
            }
            return (zzyb) clsZzxv.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzxu() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzxv() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzwo zzwoVar, T t, T t2, long j) {
        zzyh.zza(t, j, zzwoVar.zzc(zzyh.zzp(t, j), zzyh.zzp(t2, j)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T, FT extends zzvf<FT>> void zza(zzva<FT> zzvaVar, T t, T t2) {
        zzvd<T> zzvdVarZzs = zzvaVar.zzs(t2);
        if (!zzvdVarZzs.isEmpty()) {
            zzvaVar.zzt(t).zza(zzvdVarZzs);
        }
    }

    static <T, UT, UB> void zza(zzyb<UT, UB> zzybVar, T t, T t2) {
        zzybVar.zzf(t, zzybVar.zzh(zzybVar.zzah(t), zzybVar.zzah(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzvr zzvrVar, UB ub, zzyb<UT, UB> zzybVar) {
        UB ub2;
        if (zzvrVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzvrVar.zzb(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub2 = (UB) zza(i, iIntValue, ub2, zzybVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            ub2 = ub;
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!zzvrVar.zzb(iIntValue2)) {
                    Object objZza = zza(i, iIntValue2, ub2, zzybVar);
                    it.remove();
                    ub2 = (UB) objZza;
                }
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzyb<UT, UB> zzybVar) {
        if (ub == null) {
            ub = zzybVar.zzye();
        }
        zzybVar.zza(ub, i, i2);
        return ub;
    }
}
