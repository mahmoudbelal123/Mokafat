package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzza;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzzb<M extends zzza<M>, T> {
    public final int tag;
    private final int type;
    private final zzvm<?, ?> zzbyp;
    protected final Class<T> zzcfd;
    protected final boolean zzcfe;

    public static <M extends zzza<M>, T extends zzzg> zzzb<M, T> zza(int i, Class<T> cls, long j) {
        return new zzzb<>(11, cls, 810, false);
    }

    private zzzb(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, null, 810, false);
    }

    private zzzb(int i, Class<T> cls, zzvm<?, ?> zzvmVar, int i2, boolean z) {
        this.type = i;
        this.zzcfd = cls;
        this.tag = i2;
        this.zzcfe = false;
        this.zzbyp = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzb)) {
            return false;
        }
        zzzb zzzbVar = (zzzb) obj;
        return this.type == zzzbVar.type && this.zzcfd == zzzbVar.zzcfd && this.tag == zzzbVar.tag && this.zzcfe == zzzbVar.zzcfe;
    }

    public final int hashCode() {
        return ((((((1147 + this.type) * 31) + this.zzcfd.hashCode()) * 31) + this.tag) * 31) + (this.zzcfe ? 1 : 0);
    }

    final T zzah(List<zzzi> list) {
        if (list == null) {
            return null;
        }
        if (this.zzcfe) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                zzzi zzziVar = list.get(i);
                if (zzziVar.zzbug.length != 0) {
                    arrayList.add(zze(zzyx.zzn(zzziVar.zzbug)));
                }
            }
            int size = arrayList.size();
            if (size == 0) {
                return null;
            }
            T tCast = this.zzcfd.cast(Array.newInstance(this.zzcfd.getComponentType(), size));
            for (int i2 = 0; i2 < size; i2++) {
                Array.set(tCast, i2, arrayList.get(i2));
            }
            return tCast;
        }
        if (list.isEmpty()) {
            return null;
        }
        return this.zzcfd.cast(zze(zzyx.zzn(list.get(list.size() - 1).zzbug)));
    }

    private final Object zze(zzyx zzyxVar) {
        Class componentType = this.zzcfe ? this.zzcfd.getComponentType() : this.zzcfd;
        try {
            switch (this.type) {
                case 10:
                    zzzg zzzgVar = (zzzg) componentType.newInstance();
                    zzyxVar.zza(zzzgVar, this.tag >>> 3);
                    return zzzgVar;
                case 11:
                    zzzg zzzgVar2 = (zzzg) componentType.newInstance();
                    zzyxVar.zza(zzzgVar2);
                    return zzzgVar2;
                default:
                    int i = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading extension field", e);
        } catch (IllegalAccessException e2) {
            String strValueOf = String.valueOf(componentType);
            StringBuilder sb2 = new StringBuilder(33 + String.valueOf(strValueOf).length());
            sb2.append("Error creating instance of class ");
            sb2.append(strValueOf);
            throw new IllegalArgumentException(sb2.toString(), e2);
        } catch (InstantiationException e3) {
            String strValueOf2 = String.valueOf(componentType);
            StringBuilder sb3 = new StringBuilder(33 + String.valueOf(strValueOf2).length());
            sb3.append("Error creating instance of class ");
            sb3.append(strValueOf2);
            throw new IllegalArgumentException(sb3.toString(), e3);
        }
    }

    protected final void zza(Object obj, zzyy zzyyVar) {
        try {
            zzyyVar.zzca(this.tag);
            switch (this.type) {
                case 10:
                    int i = this.tag >>> 3;
                    ((zzzg) obj).zza(zzyyVar);
                    zzyyVar.zzc(i, 4);
                    return;
                case 11:
                    zzyyVar.zzb((zzzg) obj);
                    return;
                default:
                    int i2 = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i2);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected final int zzak(Object obj) {
        int i = this.tag >>> 3;
        switch (this.type) {
            case 10:
                return (zzyy.zzbb(i) << 1) + ((zzzg) obj).zzvu();
            case 11:
                return zzyy.zzb(i, (zzzg) obj);
            default:
                int i2 = this.type;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Unknown type ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
        }
    }
}
