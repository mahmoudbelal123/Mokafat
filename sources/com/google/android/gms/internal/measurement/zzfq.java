package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;

/* JADX INFO: loaded from: classes.dex */
public final class zzfq {

    public static final class zza extends zzvm<zza, C0006zza> implements zzwv {
        private static final zza zzauq = new zza();
        private static volatile zzxd<zza> zznw;
        private String zzauo = "";
        private long zzaup;
        private int zznr;

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.measurement.zzfq$zza$zza, reason: collision with other inner class name */
        public static final class C0006zza extends zzvm.zza<zza, C0006zza> implements zzwv {
            private C0006zza() {
                super(zza.zzauq);
            }

            /* synthetic */ C0006zza(zzfr zzfrVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzvm
        protected final Object zza(int i, Object obj, Object obj2) {
            zzfr zzfrVar = null;
            switch (zzfr.zznq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0006zza(zzfrVar);
                case 3:
                    return zza(zzauq, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001", new Object[]{"zznr", "zzauo", "zzaup"});
                case 4:
                    return zzauq;
                case 5:
                    zzxd<zza> zzbVar = zznw;
                    if (zzbVar == null) {
                        synchronized (zza.class) {
                            zzbVar = zznw;
                            if (zzbVar == null) {
                                zzbVar = new zzvm.zzb<>(zzauq);
                                zznw = zzbVar;
                            }
                            break;
                        }
                    }
                    return zzbVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzvm.zza((Class<zza>) zza.class, zzauq);
        }
    }

    public static final class zzb extends zzvm<zzb, zza> implements zzwv {
        private static final zzb zzaut = new zzb();
        private static volatile zzxd<zzb> zznw;
        private int zzaur = 1;
        private zzvs<zza> zzaus = zzwc();
        private int zznr;

        /* JADX INFO: renamed from: com.google.android.gms.internal.measurement.zzfq$zzb$zzb, reason: collision with other inner class name */
        public enum EnumC0007zzb implements zzvp {
            RADS(1),
            PROVISIONING(2);

            private static final zzvq<EnumC0007zzb> zzoa = new zzfs();
            private final int value;

            @Override // com.google.android.gms.internal.measurement.zzvp
            public final int zzc() {
                return this.value;
            }

            public static EnumC0007zzb zzs(int i) {
                switch (i) {
                    case 1:
                        return RADS;
                    case 2:
                        return PROVISIONING;
                    default:
                        return null;
                }
            }

            public static zzvr zzd() {
                return zzft.zzoc;
            }

            EnumC0007zzb(int i) {
                this.value = i;
            }
        }

        private zzb() {
        }

        public static final class zza extends zzvm.zza<zzb, zza> implements zzwv {
            private zza() {
                super(zzb.zzaut);
            }

            /* synthetic */ zza(zzfr zzfrVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzvm
        protected final Object zza(int i, Object obj, Object obj2) {
            zzfr zzfrVar = null;
            switch (zzfr.zznq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzfrVar);
                case 3:
                    return zza(zzaut, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u001b", new Object[]{"zznr", "zzaur", EnumC0007zzb.zzd(), "zzaus", zza.class});
                case 4:
                    return zzaut;
                case 5:
                    zzxd<zzb> zzbVar = zznw;
                    if (zzbVar == null) {
                        synchronized (zzb.class) {
                            zzbVar = zznw;
                            if (zzbVar == null) {
                                zzbVar = new zzvm.zzb<>(zzaut);
                                zznw = zzbVar;
                            }
                            break;
                        }
                    }
                    return zzbVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzxd<zzb> zza() {
            return (zzxd) zzaut.zza(zzvm.zze.zzbyz, (Object) null, (Object) null);
        }

        static {
            zzvm.zza((Class<zzb>) zzb.class, zzaut);
        }
    }
}
