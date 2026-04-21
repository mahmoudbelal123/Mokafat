package com.google.android.gms.internal.measurement;

import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class zzvo {
    public static final byte[] zzbzj;
    private static final ByteBuffer zzbzk;
    private static final zzuo zzbzl;
    static final Charset UTF_8 = Charset.forName(Key.STRING_CHARSET_NAME);
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    static <T> T checkNotNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    static <T> T zza(T t, String str) {
        if (t == null) {
            throw new NullPointerException(str);
        }
        return t;
    }

    public static boolean zzl(byte[] bArr) {
        return zzyj.zzl(bArr);
    }

    public static String zzm(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int zzbf(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zzw(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int iZza = zza(length, bArr, 0, length);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    static boolean zzf(zzwt zzwtVar) {
        return false;
    }

    static Object zzb(Object obj, Object obj2) {
        return ((zzwt) obj).zzwd().zza((zzwt) obj2).zzwi();
    }

    static {
        byte[] bArr = new byte[0];
        zzbzj = bArr;
        zzbzk = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzbzj;
        zzbzl = zzuo.zza(bArr2, 0, bArr2.length, false);
    }
}
