package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
final class zzxf {
    private static final zzxf zzcbs = new zzxf();
    private final zzxk zzcbt;
    private final ConcurrentMap<Class<?>, zzxj<?>> zzcbu = new ConcurrentHashMap();

    public static zzxf zzxn() {
        return zzcbs;
    }

    public final <T> zzxj<T> zzi(Class<T> cls) {
        zzvo.zza(cls, "messageType");
        zzxj<T> zzxjVar = (zzxj) this.zzcbu.get(cls);
        if (zzxjVar != null) {
            return zzxjVar;
        }
        zzxj<T> zzxjVarZzh = this.zzcbt.zzh(cls);
        zzvo.zza(cls, "messageType");
        zzvo.zza(zzxjVarZzh, "schema");
        zzxj<T> zzxjVar2 = (zzxj) this.zzcbu.putIfAbsent(cls, zzxjVarZzh);
        return zzxjVar2 != null ? zzxjVar2 : zzxjVarZzh;
    }

    public final <T> zzxj<T> zzag(T t) {
        return zzi(t.getClass());
    }

    private zzxf() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzxk zzxkVarZzgb = null;
        for (int i = 0; i <= 0; i++) {
            zzxkVarZzgb = zzgb(strArr[0]);
            if (zzxkVarZzgb != null) {
                break;
            }
        }
        this.zzcbt = zzxkVarZzgb == null ? new zzwi() : zzxkVarZzgb;
    }

    private static zzxk zzgb(String str) {
        try {
            return (zzxk) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }
}
