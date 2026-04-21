package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class zzvd<FieldDescriptorType extends zzvf<FieldDescriptorType>> {
    private static final zzvd zzbvs = new zzvd(true);
    private boolean zzbpo;
    private boolean zzbvr = false;
    private final zzxm<FieldDescriptorType, Object> zzbvq = zzxm.zzbt(16);

    private zzvd() {
    }

    private zzvd(boolean z) {
        zzsm();
    }

    public static <T extends zzvf<T>> zzvd<T> zzvt() {
        return zzbvs;
    }

    final boolean isEmpty() {
        return this.zzbvq.isEmpty();
    }

    public final void zzsm() {
        if (this.zzbpo) {
            return;
        }
        this.zzbvq.zzsm();
        this.zzbpo = true;
    }

    public final boolean isImmutable() {
        return this.zzbpo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzvd)) {
            return false;
        }
        return this.zzbvq.equals(((zzvd) obj).zzbvq);
    }

    public final int hashCode() {
        return this.zzbvq.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzbvr) {
            return new zzvz(this.zzbvq.entrySet().iterator());
        }
        return this.zzbvq.entrySet().iterator();
    }

    final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzbvr) {
            return new zzvz(this.zzbvq.zzxy().iterator());
        }
        return this.zzbvq.zzxy().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzbvq.get(fielddescriptortype);
        if (obj instanceof zzvw) {
            return zzvw.zzwt();
        }
        return obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (fielddescriptortype.zzvy()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzvw(), obj2);
            }
            obj = arrayList;
        } else {
            zza(fielddescriptortype.zzvw(), obj);
        }
        if (obj instanceof zzvw) {
            this.zzbvr = true;
        }
        this.zzbvq.put(fielddescriptortype, obj);
    }

    private static void zza(zzyq zzyqVar, Object obj) {
        zzvo.checkNotNull(obj);
        boolean z = true;
        boolean z2 = false;
        switch (zzve.zzbvt[zzyqVar.zzyp().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                z2 = z;
                break;
            case 2:
                z = obj instanceof Long;
                z2 = z;
                break;
            case 3:
                z = obj instanceof Float;
                z2 = z;
                break;
            case 4:
                z = obj instanceof Double;
                z2 = z;
                break;
            case 5:
                z = obj instanceof Boolean;
                z2 = z;
                break;
            case 6:
                z = obj instanceof String;
                z2 = z;
                break;
            case 7:
                if ((obj instanceof zzud) || (obj instanceof byte[])) {
                    z2 = true;
                }
                break;
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzvp)) {
                    z2 = true;
                }
                break;
            case 9:
                if ((obj instanceof zzwt) || (obj instanceof zzvw)) {
                    z2 = z;
                }
                break;
        }
        if (!z2) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzbvq.zzxw(); i++) {
            if (!zzc(this.zzbvq.zzbu(i))) {
                return false;
            }
        }
        Iterator it = this.zzbvq.zzxx().iterator();
        while (it.hasNext()) {
            if (!zzc((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.zzvx() == zzyv.MESSAGE) {
            if (key.zzvy()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((zzwt) it.next()).isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzwt) {
                    if (!((zzwt) value).isInitialized()) {
                        return false;
                    }
                } else {
                    if (value instanceof zzvw) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzvd<FieldDescriptorType> zzvdVar) {
        for (int i = 0; i < zzvdVar.zzbvq.zzxw(); i++) {
            zzd(zzvdVar.zzbvq.zzbu(i));
        }
        Iterator it = zzvdVar.zzbvq.zzxx().iterator();
        while (it.hasNext()) {
            zzd((Map.Entry) it.next());
        }
    }

    private static Object zzv(Object obj) {
        if (obj instanceof zzwz) {
            return ((zzwz) obj).zzxj();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    private final void zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        zzwt zzwtVarZzwj;
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzvw) {
            value = zzvw.zzwt();
        }
        if (key.zzvy()) {
            Object objZza = zza(key);
            if (objZza == null) {
                objZza = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objZza).add(zzv(it.next()));
            }
            this.zzbvq.put(key, objZza);
            return;
        }
        if (key.zzvx() == zzyv.MESSAGE) {
            Object objZza2 = zza(key);
            if (objZza2 == null) {
                this.zzbvq.put(key, zzv(value));
                return;
            }
            if (objZza2 instanceof zzwz) {
                zzwtVarZzwj = key.zza((zzwz) objZza2, (zzwz) value);
            } else {
                zzwtVarZzwj = key.zza(((zzwt) objZza2).zzwd(), (zzwt) value).zzwj();
            }
            this.zzbvq.put(key, zzwtVarZzwj);
            return;
        }
        this.zzbvq.put(key, zzv(value));
    }

    static void zza(zzut zzutVar, zzyq zzyqVar, int i, Object obj) throws IOException {
        if (zzyqVar == zzyq.zzcdz) {
            zzwt zzwtVar = (zzwt) obj;
            zzvo.zzf(zzwtVar);
            zzutVar.zzc(i, 3);
            zzwtVar.zzb(zzutVar);
            zzutVar.zzc(i, 4);
        }
        zzutVar.zzc(i, zzyqVar.zzyq());
        switch (zzve.zzbuu[zzyqVar.ordinal()]) {
            case 1:
                zzutVar.zzb(((Double) obj).doubleValue());
                break;
            case 2:
                zzutVar.zza(((Float) obj).floatValue());
                break;
            case 3:
                zzutVar.zzav(((Long) obj).longValue());
                break;
            case 4:
                zzutVar.zzav(((Long) obj).longValue());
                break;
            case 5:
                zzutVar.zzax(((Integer) obj).intValue());
                break;
            case 6:
                zzutVar.zzax(((Long) obj).longValue());
                break;
            case 7:
                zzutVar.zzba(((Integer) obj).intValue());
                break;
            case 8:
                zzutVar.zzu(((Boolean) obj).booleanValue());
                break;
            case 9:
                ((zzwt) obj).zzb(zzutVar);
                break;
            case 10:
                zzutVar.zzb((zzwt) obj);
                break;
            case 11:
                if (obj instanceof zzud) {
                    zzutVar.zza((zzud) obj);
                } else {
                    zzutVar.zzfw((String) obj);
                }
                break;
            case 12:
                if (obj instanceof zzud) {
                    zzutVar.zza((zzud) obj);
                } else {
                    byte[] bArr = (byte[]) obj;
                    zzutVar.zze(bArr, 0, bArr.length);
                }
                break;
            case 13:
                zzutVar.zzay(((Integer) obj).intValue());
                break;
            case 14:
                zzutVar.zzba(((Integer) obj).intValue());
                break;
            case 15:
                zzutVar.zzax(((Long) obj).longValue());
                break;
            case 16:
                zzutVar.zzaz(((Integer) obj).intValue());
                break;
            case 17:
                zzutVar.zzaw(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof zzvp) {
                    zzutVar.zzax(((zzvp) obj).zzc());
                } else {
                    zzutVar.zzax(((Integer) obj).intValue());
                }
                break;
        }
    }

    public final int zzvu() {
        int iZzb = 0;
        for (int i = 0; i < this.zzbvq.zzxw(); i++) {
            Map.Entry<K, Object> entryZzbu = this.zzbvq.zzbu(i);
            iZzb += zzb((zzvf<?>) entryZzbu.getKey(), entryZzbu.getValue());
        }
        Iterator it = this.zzbvq.zzxx().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            iZzb += zzb((zzvf<?>) entry.getKey(), entry.getValue());
        }
        return iZzb;
    }

    public final int zzvv() {
        int iZze = 0;
        for (int i = 0; i < this.zzbvq.zzxw(); i++) {
            iZze += zze(this.zzbvq.zzbu(i));
        }
        Iterator it = this.zzbvq.zzxx().iterator();
        while (it.hasNext()) {
            iZze += zze((Map.Entry) it.next());
        }
        return iZze;
    }

    private static int zze(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzvx() == zzyv.MESSAGE && !key.zzvy() && !key.zzvz()) {
            if (value instanceof zzvw) {
                return zzut.zzb(entry.getKey().zzc(), (zzvw) value);
            }
            return zzut.zzd(entry.getKey().zzc(), (zzwt) value);
        }
        return zzb((zzvf<?>) key, value);
    }

    static int zza(zzyq zzyqVar, int i, Object obj) {
        int iZzbb = zzut.zzbb(i);
        if (zzyqVar == zzyq.zzcdz) {
            zzvo.zzf((zzwt) obj);
            iZzbb <<= 1;
        }
        return iZzbb + zzb(zzyqVar, obj);
    }

    private static int zzb(zzyq zzyqVar, Object obj) {
        switch (zzve.zzbuu[zzyqVar.ordinal()]) {
            case 1:
                return zzut.zzc(((Double) obj).doubleValue());
            case 2:
                return zzut.zzb(((Float) obj).floatValue());
            case 3:
                return zzut.zzay(((Long) obj).longValue());
            case 4:
                return zzut.zzaz(((Long) obj).longValue());
            case 5:
                return zzut.zzbc(((Integer) obj).intValue());
            case 6:
                return zzut.zzbb(((Long) obj).longValue());
            case 7:
                return zzut.zzbf(((Integer) obj).intValue());
            case 8:
                return zzut.zzv(((Boolean) obj).booleanValue());
            case 9:
                return zzut.zzd((zzwt) obj);
            case 10:
                if (obj instanceof zzvw) {
                    return zzut.zza((zzvw) obj);
                }
                return zzut.zzc((zzwt) obj);
            case 11:
                if (obj instanceof zzud) {
                    return zzut.zzb((zzud) obj);
                }
                return zzut.zzfx((String) obj);
            case 12:
                if (obj instanceof zzud) {
                    return zzut.zzb((zzud) obj);
                }
                return zzut.zzk((byte[]) obj);
            case 13:
                return zzut.zzbd(((Integer) obj).intValue());
            case 14:
                return zzut.zzbg(((Integer) obj).intValue());
            case 15:
                return zzut.zzbc(((Long) obj).longValue());
            case 16:
                return zzut.zzbe(((Integer) obj).intValue());
            case 17:
                return zzut.zzba(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzvp) {
                    return zzut.zzbh(((zzvp) obj).zzc());
                }
                return zzut.zzbh(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static int zzb(zzvf<?> zzvfVar, Object obj) {
        zzyq zzyqVarZzvw = zzvfVar.zzvw();
        int iZzc = zzvfVar.zzc();
        if (zzvfVar.zzvy()) {
            int iZza = 0;
            if (zzvfVar.zzvz()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iZza += zzb(zzyqVarZzvw, it.next());
                }
                return zzut.zzbb(iZzc) + iZza + zzut.zzbj(iZza);
            }
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                iZza += zza(zzyqVarZzvw, iZzc, it2.next());
            }
            return iZza;
        }
        return zza(zzyqVarZzvw, iZzc, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzvd zzvdVar = new zzvd();
        for (int i = 0; i < this.zzbvq.zzxw(); i++) {
            Map.Entry<K, Object> entryZzbu = this.zzbvq.zzbu(i);
            zzvdVar.zza((zzvf) entryZzbu.getKey(), entryZzbu.getValue());
        }
        Iterator it = this.zzbvq.zzxx().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzvdVar.zza((zzvf) entry.getKey(), entry.getValue());
        }
        zzvdVar.zzbvr = this.zzbvr;
        return zzvdVar;
    }
}
