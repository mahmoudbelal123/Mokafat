package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
final class zzyj {
    private static final zzyl zzcdp;

    public static boolean zzl(byte[] bArr) {
        return zzcdp.zzf(bArr, 0, bArr.length);
    }

    public static boolean zzf(byte[] bArr, int i, int i2) {
        return zzcdp.zzf(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzbw(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzq(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzc(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzg(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return zzbw(b);
            case 1:
                return zzq(b, bArr[i]);
            case 2:
                return zzc(b, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt < 2048) {
                i3 += (127 - cCharAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char cCharAt2 = charSequence.charAt(i2);
                    if (cCharAt2 < 2048) {
                        i += (127 - cCharAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new zzyn(i2, length2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 < length) {
            StringBuilder sb = new StringBuilder(54);
            sb.append("UTF-8 length does not fit in int: ");
            sb.append(((long) i3) + 4294967296L);
            throw new IllegalArgumentException(sb.toString());
        }
        return i3;
    }

    static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return zzcdp.zzb(charSequence, bArr, i, i2);
    }

    static String zzh(byte[] bArr, int i, int i2) throws zzvt {
        return zzcdp.zzh(bArr, i, i2);
    }

    static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzyl zzylVar = zzcdp;
        if (byteBuffer.hasArray()) {
            int iArrayOffset = byteBuffer.arrayOffset();
            byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.position() + iArrayOffset, byteBuffer.remaining()) - iArrayOffset);
        } else if (byteBuffer.isDirect()) {
            zzylVar.zzb(charSequence, byteBuffer);
        } else {
            zzyl.zzc(charSequence, byteBuffer);
        }
    }

    static {
        zzyl zzymVar;
        if ((zzyh.zzyi() && zzyh.zzyj()) && !zzua.zzty()) {
            zzymVar = new zzyo();
        } else {
            zzymVar = new zzym();
        }
        zzcdp = zzymVar;
    }
}
