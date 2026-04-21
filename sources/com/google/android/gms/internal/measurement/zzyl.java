package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
abstract class zzyl {
    zzyl() {
    }

    abstract int zzb(int i, byte[] bArr, int i2, int i3);

    abstract int zzb(CharSequence charSequence, byte[] bArr, int i, int i2);

    abstract void zzb(CharSequence charSequence, ByteBuffer byteBuffer);

    abstract String zzh(byte[] bArr, int i, int i2) throws zzvt;

    final boolean zzf(byte[] bArr, int i, int i2) {
        return zzb(0, bArr, i, i2) == 0;
    }

    static void zzc(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int iPosition = byteBuffer.position();
        int i = 0;
        while (i < length) {
            try {
                char cCharAt = charSequence.charAt(i);
                if (cCharAt >= 128) {
                    break;
                }
                byteBuffer.put(iPosition + i, (byte) cCharAt);
                i++;
            } catch (IndexOutOfBoundsException e) {
                int iPosition2 = byteBuffer.position() + Math.max(i, (iPosition - byteBuffer.position()) + 1);
                char cCharAt2 = charSequence.charAt(i);
                StringBuilder sb = new StringBuilder(37);
                sb.append("Failed writing ");
                sb.append(cCharAt2);
                sb.append(" at index ");
                sb.append(iPosition2);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
        }
        if (i == length) {
            byteBuffer.position(iPosition + i);
            return;
        }
        iPosition += i;
        while (i < length) {
            char cCharAt3 = charSequence.charAt(i);
            if (cCharAt3 < 128) {
                byteBuffer.put(iPosition, (byte) cCharAt3);
            } else if (cCharAt3 < 2048) {
                int i2 = iPosition + 1;
                try {
                    byteBuffer.put(iPosition, (byte) (192 | (cCharAt3 >>> 6)));
                    byteBuffer.put(i2, (byte) (('?' & cCharAt3) | 128));
                    iPosition = i2;
                } catch (IndexOutOfBoundsException e2) {
                    iPosition = i2;
                    int iPosition22 = byteBuffer.position() + Math.max(i, (iPosition - byteBuffer.position()) + 1);
                    char cCharAt22 = charSequence.charAt(i);
                    StringBuilder sb2 = new StringBuilder(37);
                    sb2.append("Failed writing ");
                    sb2.append(cCharAt22);
                    sb2.append(" at index ");
                    sb2.append(iPosition22);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
            } else {
                if (cCharAt3 >= 55296 && 57343 >= cCharAt3) {
                    int i3 = i + 1;
                    if (i3 != length) {
                        try {
                            char cCharAt4 = charSequence.charAt(i3);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                int i4 = iPosition + 1;
                                try {
                                    byteBuffer.put(iPosition, (byte) (240 | (codePoint >>> 18)));
                                    int i5 = i4 + 1;
                                    byteBuffer.put(i4, (byte) (((codePoint >>> 12) & 63) | 128));
                                    int i6 = i5 + 1;
                                    byteBuffer.put(i5, (byte) (((codePoint >>> 6) & 63) | 128));
                                    byteBuffer.put(i6, (byte) ((63 & codePoint) | 128));
                                    iPosition = i6;
                                    i = i3;
                                } catch (IndexOutOfBoundsException e3) {
                                    iPosition = i4;
                                    i = i3;
                                    int iPosition222 = byteBuffer.position() + Math.max(i, (iPosition - byteBuffer.position()) + 1);
                                    char cCharAt222 = charSequence.charAt(i);
                                    StringBuilder sb22 = new StringBuilder(37);
                                    sb22.append("Failed writing ");
                                    sb22.append(cCharAt222);
                                    sb22.append(" at index ");
                                    sb22.append(iPosition222);
                                    throw new ArrayIndexOutOfBoundsException(sb22.toString());
                                }
                            } else {
                                i = i3;
                            }
                        } catch (IndexOutOfBoundsException e4) {
                        }
                    }
                    throw new zzyn(i, length);
                }
                int i7 = iPosition + 1;
                byteBuffer.put(iPosition, (byte) (224 | (cCharAt3 >>> '\f')));
                iPosition = i7 + 1;
                byteBuffer.put(i7, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                byteBuffer.put(iPosition, (byte) ((cCharAt3 & '?') | 128));
            }
            i++;
            iPosition++;
        }
        byteBuffer.position(iPosition);
    }
}
