package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
final class zzyh {
    private static final boolean zzbuv;
    private static final zzd zzccx;
    private static final boolean zzccy;
    private static final long zzccz;
    private static final long zzcda;
    private static final long zzcdb;
    private static final long zzcdc;
    private static final long zzcdd;
    private static final long zzcde;
    private static final long zzcdf;
    private static final long zzcdg;
    private static final long zzcdh;
    private static final long zzcdi;
    private static final long zzcdj;
    private static final long zzcdk;
    private static final long zzcdl;
    private static final long zzcdm;
    private static final boolean zzcdn;
    private static final Logger logger = Logger.getLogger(zzyh.class.getName());
    private static final Unsafe zzcay = zzyk();
    private static final Class<?> zzbtv = zzua.zztz();
    private static final boolean zzccv = zzm(Long.TYPE);
    private static final boolean zzccw = zzm(Integer.TYPE);

    private zzyh() {
    }

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(long j, byte b) {
            Memory.pokeByte((int) ((-1) & j), b);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final byte zzy(Object obj, long j) {
            if (zzyh.zzcdn) {
                return zzyh.zzq(obj, j);
            }
            return zzyh.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzyh.zzcdn) {
                zzyh.zza(obj, j, b);
            } else {
                zzyh.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzyh.zzcdn) {
                return zzyh.zzs(obj, j);
            }
            return zzyh.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, boolean z) {
            if (zzyh.zzcdn) {
                zzyh.zzb(obj, j, z);
            } else {
                zzyh.zzc(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray((int) ((-1) & j2), bArr, (int) j, (int) j3);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(long j, byte b) {
            Memory.pokeByte(j, b);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final byte zzy(Object obj, long j) {
            if (zzyh.zzcdn) {
                return zzyh.zzq(obj, j);
            }
            return zzyh.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzyh.zzcdn) {
                zzyh.zza(obj, j, b);
            } else {
                zzyh.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzyh.zzcdn) {
                return zzyh.zzs(obj, j);
            }
            return zzyh.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, boolean z) {
            if (zzyh.zzcdn) {
                zzyh.zzb(obj, j, z);
            } else {
                zzyh.zzc(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray(j2, bArr, (int) j, (int) j3);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(long j, byte b) {
            this.zzcdo.putByte(j, b);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final byte zzy(Object obj, long j) {
            return this.zzcdo.getByte(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zze(Object obj, long j, byte b) {
            this.zzcdo.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final boolean zzm(Object obj, long j) {
            return this.zzcdo.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, boolean z) {
            this.zzcdo.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final float zzn(Object obj, long j) {
            return this.zzcdo.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, float f) {
            this.zzcdo.putFloat(obj, j, f);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final double zzo(Object obj, long j) {
            return this.zzcdo.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(Object obj, long j, double d) {
            this.zzcdo.putDouble(obj, j, d);
        }

        @Override // com.google.android.gms.internal.measurement.zzyh.zzd
        public final void zza(byte[] bArr, long j, long j2, long j3) {
            this.zzcdo.copyMemory(bArr, zzyh.zzccz + j, (Object) null, j2, j3);
        }
    }

    static boolean zzyi() {
        return zzbuv;
    }

    static abstract class zzd {
        Unsafe zzcdo;

        zzd(Unsafe unsafe) {
            this.zzcdo = unsafe;
        }

        public abstract void zza(long j, byte b);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zza(byte[] bArr, long j, long j2, long j3);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzcdo.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzcdo.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzcdo.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzcdo.putLong(obj, j, j2);
        }
    }

    static boolean zzyj() {
        return zzccy;
    }

    private static int zzk(Class<?> cls) {
        if (zzbuv) {
            return zzccx.zzcdo.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzl(Class<?> cls) {
        if (zzbuv) {
            return zzccx.zzcdo.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzccx.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzccx.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzccx.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzccx.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzccx.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzccx.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzccx.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzccx.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzccx.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzccx.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzccx.zzcdo.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzccx.zzcdo.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzccx.zzy(bArr, zzccz + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzccx.zze(bArr, zzccz + j, b);
    }

    static void zza(byte[] bArr, long j, long j2, long j3) {
        zzccx.zza(bArr, j, j2, j3);
    }

    static void zza(long j, byte b) {
        zzccx.zza(j, b);
    }

    static long zzb(ByteBuffer byteBuffer) {
        return zzccx.zzl(byteBuffer, zzcdm);
    }

    static Unsafe zzyk() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzyi());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzyl() {
        if (zzcay == null) {
            return false;
        }
        try {
            Class<?> cls = zzcay.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            cls.getMethod("getInt", Object.class, Long.TYPE);
            cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            cls.getMethod("getObject", Object.class, Long.TYPE);
            cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (zzua.zzty()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, Long.TYPE);
            cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, Long.TYPE);
            cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, Long.TYPE);
            cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            cls.getMethod("getDouble", Object.class, Long.TYPE);
            cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(71 + String.valueOf(strValueOf).length());
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzym() {
        if (zzcay == null) {
            return false;
        }
        try {
            Class<?> cls = zzcay.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (zzyn() == null) {
                return false;
            }
            if (zzua.zzty()) {
                return true;
            }
            cls.getMethod("getByte", Long.TYPE);
            cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
            cls.getMethod("getInt", Long.TYPE);
            cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Long.TYPE);
            cls.getMethod("putLong", Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(71 + String.valueOf(strValueOf).length());
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzm(Class<?> cls) {
        if (!zzua.zzty()) {
            return false;
        }
        try {
            Class<?> cls2 = zzbtv;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static Field zzyn() {
        Field fieldZzb;
        if (zzua.zzty() && (fieldZzb = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return fieldZzb;
        }
        Field fieldZzb2 = zzb(Buffer.class, "address");
        if (fieldZzb2 == null || fieldZzb2.getType() != Long.TYPE) {
            return null;
        }
        return fieldZzb2;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable th) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, j & (-4)) >>> ((int) (((j ^ (-1)) & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, j & (-4)) >>> ((int) ((j & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = j & (-4);
        int i = ((((int) j) ^ (-1)) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & ((255 << i) ^ (-1))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = j & (-4);
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & ((255 << i) ^ (-1))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : (byte) 0);
    }

    static {
        long jObjectFieldOffset;
        zzd zzcVar = null;
        if (zzcay != null) {
            if (zzua.zzty()) {
                if (zzccv) {
                    zzcVar = new zzb(zzcay);
                } else if (zzccw) {
                    zzcVar = new zza(zzcay);
                }
            } else {
                zzcVar = new zzc(zzcay);
            }
        }
        zzccx = zzcVar;
        zzccy = zzym();
        zzbuv = zzyl();
        zzccz = zzk(byte[].class);
        zzcda = zzk(boolean[].class);
        zzcdb = zzl(boolean[].class);
        zzcdc = zzk(int[].class);
        zzcdd = zzl(int[].class);
        zzcde = zzk(long[].class);
        zzcdf = zzl(long[].class);
        zzcdg = zzk(float[].class);
        zzcdh = zzl(float[].class);
        zzcdi = zzk(double[].class);
        zzcdj = zzl(double[].class);
        zzcdk = zzk(Object[].class);
        zzcdl = zzl(Object[].class);
        Field fieldZzyn = zzyn();
        if (fieldZzyn == null || zzccx == null) {
            jObjectFieldOffset = -1;
        } else {
            jObjectFieldOffset = zzccx.zzcdo.objectFieldOffset(fieldZzyn);
        }
        zzcdm = jObjectFieldOffset;
        zzcdn = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }
}
