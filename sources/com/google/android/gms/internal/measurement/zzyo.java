package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
final class zzyo extends zzyl {
    zzyo() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0085, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b1, code lost:
    
        return -1;
     */
    @Override // com.google.android.gms.internal.measurement.zzyl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzb(int r12, byte[] r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzyo.zzb(int, byte[], int, int):int");
    }

    @Override // com.google.android.gms.internal.measurement.zzyl
    final String zzh(byte[] bArr, int i, int i2) throws zzvt {
        if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte bZza = zzyh.zza(bArr, i);
            if (!zzyk.zzd(bZza)) {
                break;
            }
            i++;
            zzyk.zza(bZza, cArr, i4);
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte bZza2 = zzyh.zza(bArr, i);
            if (!zzyk.zzd(bZza2)) {
                if (!zzyk.zze(bZza2)) {
                    if (zzyk.zzf(bZza2)) {
                        if (i6 >= i3 - 1) {
                            throw zzvt.zzwr();
                        }
                        int i7 = i6 + 1;
                        zzyk.zza(bZza2, zzyh.zza(bArr, i6), zzyh.zza(bArr, i7), cArr, i5);
                        i = i7 + 1;
                        i5++;
                    } else {
                        if (i6 >= i3 - 2) {
                            throw zzvt.zzwr();
                        }
                        int i8 = i6 + 1;
                        byte bZza3 = zzyh.zza(bArr, i6);
                        int i9 = i8 + 1;
                        zzyk.zza(bZza2, bZza3, zzyh.zza(bArr, i8), zzyh.zza(bArr, i9), cArr, i5);
                        i = i9 + 1;
                        i5 = i5 + 1 + 1;
                    }
                } else {
                    if (i6 >= i3) {
                        throw zzvt.zzwr();
                    }
                    zzyk.zza(bZza2, zzyh.zza(bArr, i6), cArr, i5);
                    i = i6 + 1;
                    i5++;
                }
            } else {
                int i10 = i5 + 1;
                zzyk.zza(bZza2, cArr, i5);
                while (i6 < i3) {
                    byte bZza4 = zzyh.zza(bArr, i6);
                    if (!zzyk.zzd(bZza4)) {
                        break;
                    }
                    i6++;
                    zzyk.zza(bZza4, cArr, i10);
                    i10++;
                }
                i = i6;
                i5 = i10;
            }
        }
        return new String(cArr, 0, i5);
    }

    @Override // com.google.android.gms.internal.measurement.zzyl
    final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        char cCharAt;
        long j = i;
        long j2 = j + ((long) i2);
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char cCharAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(cCharAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (i4 < length && (cCharAt = charSequence.charAt(i4)) < 128) {
            zzyh.zza(bArr, j, (byte) cCharAt);
            i4++;
            j++;
        }
        if (i4 == length) {
            return (int) j;
        }
        while (i4 < length) {
            char cCharAt3 = charSequence.charAt(i4);
            if (cCharAt3 < 128 && j < j2) {
                zzyh.zza(bArr, j, (byte) cCharAt3);
                j++;
            } else if (cCharAt3 < 2048 && j <= j2 - 2) {
                long j3 = j + 1;
                zzyh.zza(bArr, j, (byte) (960 | (cCharAt3 >>> 6)));
                j = j3 + 1;
                zzyh.zza(bArr, j3, (byte) ((cCharAt3 & '?') | 128));
            } else {
                if ((cCharAt3 >= 55296 && 57343 >= cCharAt3) || j > j2 - 3) {
                    if (j <= j2 - 4) {
                        int i5 = i4 + 1;
                        if (i5 != length) {
                            char cCharAt4 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                long j4 = j + 1;
                                zzyh.zza(bArr, j, (byte) (240 | (codePoint >>> 18)));
                                long j5 = j4 + 1;
                                zzyh.zza(bArr, j4, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j6 = j5 + 1;
                                zzyh.zza(bArr, j5, (byte) (((codePoint >>> 6) & 63) | 128));
                                j = j6 + 1;
                                zzyh.zza(bArr, j6, (byte) ((codePoint & 63) | 128));
                                i4 = i5;
                            }
                        } else {
                            i5 = i4;
                        }
                        throw new zzyn(i5 - 1, length);
                    }
                    if (55296 <= cCharAt3 && cCharAt3 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(cCharAt3, charSequence.charAt(i3)))) {
                        throw new zzyn(i4, length);
                    }
                    StringBuilder sb2 = new StringBuilder(46);
                    sb2.append("Failed writing ");
                    sb2.append(cCharAt3);
                    sb2.append(" at index ");
                    sb2.append(j);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
                long j7 = j + 1;
                zzyh.zza(bArr, j, (byte) (480 | (cCharAt3 >>> '\f')));
                long j8 = j7 + 1;
                zzyh.zza(bArr, j7, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                zzyh.zza(bArr, j8, (byte) ((cCharAt3 & '?') | 128));
                j = j8 + 1;
            }
            i4++;
        }
        return (int) j;
    }

    @Override // com.google.android.gms.internal.measurement.zzyl
    final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        long j;
        int i;
        char cCharAt;
        long jZzb = zzyh.zzb(byteBuffer);
        long jPosition = jZzb + ((long) byteBuffer.position());
        long jLimit = jZzb + ((long) byteBuffer.limit());
        int length = charSequence.length();
        if (length > jLimit - jPosition) {
            char cCharAt2 = charSequence.charAt(length - 1);
            int iLimit = byteBuffer.limit();
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(cCharAt2);
            sb.append(" at index ");
            sb.append(iLimit);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i2 = 0;
        while (i2 < length && (cCharAt = charSequence.charAt(i2)) < 128) {
            zzyh.zza(jPosition, (byte) cCharAt);
            i2++;
            jPosition++;
        }
        if (i2 == length) {
            byteBuffer.position((int) (jPosition - jZzb));
            return;
        }
        while (i2 < length) {
            char cCharAt3 = charSequence.charAt(i2);
            if (cCharAt3 >= 128 || jPosition >= jLimit) {
                if (cCharAt3 < 2048 && jPosition <= jLimit - 2) {
                    j = jZzb;
                    long j2 = jPosition + 1;
                    zzyh.zza(jPosition, (byte) (960 | (cCharAt3 >>> 6)));
                    jPosition = j2 + 1;
                    zzyh.zza(j2, (byte) (('?' & cCharAt3) | 128));
                } else {
                    j = jZzb;
                    if ((cCharAt3 >= 55296 && 57343 >= cCharAt3) || jPosition > jLimit - 3) {
                        if (jPosition <= jLimit - 4) {
                            int i3 = i2 + 1;
                            if (i3 != length) {
                                char cCharAt4 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                    int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                    long j3 = jPosition + 1;
                                    zzyh.zza(jPosition, (byte) (240 | (codePoint >>> 18)));
                                    long j4 = j3 + 1;
                                    zzyh.zza(j3, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j5 = j4 + 1;
                                    zzyh.zza(j4, (byte) (((codePoint >>> 6) & 63) | 128));
                                    zzyh.zza(j5, (byte) ((codePoint & 63) | 128));
                                    jPosition = j5 + 1;
                                    i2 = i3;
                                }
                            } else {
                                i3 = i2;
                            }
                            throw new zzyn(i3 - 1, length);
                        }
                        if (55296 <= cCharAt3 && cCharAt3 <= 57343 && ((i = i2 + 1) == length || !Character.isSurrogatePair(cCharAt3, charSequence.charAt(i)))) {
                            throw new zzyn(i2, length);
                        }
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Failed writing ");
                        sb2.append(cCharAt3);
                        sb2.append(" at index ");
                        sb2.append(jPosition);
                        throw new ArrayIndexOutOfBoundsException(sb2.toString());
                    }
                    long j6 = jPosition + 1;
                    zzyh.zza(jPosition, (byte) (480 | (cCharAt3 >>> '\f')));
                    long j7 = j6 + 1;
                    zzyh.zza(j6, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                    zzyh.zza(j7, (byte) (('?' & cCharAt3) | 128));
                    jPosition = j7 + 1;
                }
            } else {
                zzyh.zza(jPosition, (byte) cCharAt3);
                j = jZzb;
                jPosition++;
            }
            i2++;
            jZzb = j;
        }
        byteBuffer.position((int) (jPosition - jZzb));
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzyj.zzbw(i);
            case 1:
                return zzyj.zzq(i, zzyh.zza(bArr, j));
            case 2:
                return zzyj.zzc(i, zzyh.zza(bArr, j), zzyh.zza(bArr, j + 1));
            default:
                throw new AssertionError();
        }
    }
}
