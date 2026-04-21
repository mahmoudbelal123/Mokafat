package com.google.android.gms.internal.measurement;

import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.internal.measurement.zzvm;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class zzuz {
    private static volatile zzuz zzbvl;
    private final Map<zza, zzvm.zzd<?, ?>> zzbvn;
    private static volatile boolean zzbvj = false;
    private static final Class<?> zzbvk = zzvn();
    static final zzuz zzbvm = new zzuz(true);

    private static Class<?> zzvn() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * SupportMenu.USER_MASK) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            return this.object == zzaVar.object && this.number == zzaVar.number;
        }
    }

    public static zzuz zzvo() {
        return zzuy.zzvl();
    }

    public static zzuz zzvp() {
        zzuz zzuzVarZzvm = zzbvl;
        if (zzuzVarZzvm == null) {
            synchronized (zzuz.class) {
                zzuzVarZzvm = zzbvl;
                if (zzuzVarZzvm == null) {
                    zzuzVarZzvm = zzuy.zzvm();
                    zzbvl = zzuzVarZzvm;
                }
            }
        }
        return zzuzVarZzvm;
    }

    static zzuz zzvm() {
        return zzvk.zzd(zzuz.class);
    }

    public final <ContainingType extends zzwt> zzvm.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzvm.zzd) this.zzbvn.get(new zza(containingtype, i));
    }

    zzuz() {
        this.zzbvn = new HashMap();
    }

    private zzuz(boolean z) {
        this.zzbvn = Collections.emptyMap();
    }
}
