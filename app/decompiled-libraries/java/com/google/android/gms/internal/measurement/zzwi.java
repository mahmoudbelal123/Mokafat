package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;

/* JADX INFO: loaded from: classes.dex */
final class zzwi implements zzxk {
    private static final zzws zzcap = new zzwj();
    private final zzws zzcao;

    public zzwi() {
        this(new zzwk(zzvl.zzwb(), zzwz()));
    }

    private zzwi(zzws zzwsVar) {
        this.zzcao = (zzws) zzvo.zza(zzwsVar, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.measurement.zzxk
    public final <T> zzxj<T> zzh(Class<T> cls) {
        zzxl.zzj(cls);
        zzwr zzwrVarZzf = this.zzcao.zzf(cls);
        if (zzwrVarZzf.zzxh()) {
            if (zzvm.class.isAssignableFrom(cls)) {
                return zzwy.zza(zzxl.zzxt(), zzvc.zzvr(), zzwrVarZzf.zzxi());
            }
            return zzwy.zza(zzxl.zzxr(), zzvc.zzvs(), zzwrVarZzf.zzxi());
        }
        if (zzvm.class.isAssignableFrom(cls)) {
            if (zza(zzwrVarZzf)) {
                return zzwx.zza(cls, zzwrVarZzf, zzxc.zzxl(), zzwd.zzwy(), zzxl.zzxt(), zzvc.zzvr(), zzwq.zzxe());
            }
            return zzwx.zza(cls, zzwrVarZzf, zzxc.zzxl(), zzwd.zzwy(), zzxl.zzxt(), null, zzwq.zzxe());
        }
        if (zza(zzwrVarZzf)) {
            return zzwx.zza(cls, zzwrVarZzf, zzxc.zzxk(), zzwd.zzwx(), zzxl.zzxr(), zzvc.zzvs(), zzwq.zzxd());
        }
        return zzwx.zza(cls, zzwrVarZzf, zzxc.zzxk(), zzwd.zzwx(), zzxl.zzxs(), null, zzwq.zzxd());
    }

    private static boolean zza(zzwr zzwrVar) {
        return zzwrVar.zzxg() == zzvm.zze.zzbzb;
    }

    private static zzws zzwz() {
        try {
            return (zzws) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return zzcap;
        }
    }
}
