package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class zzuq extends zzuo {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzbum;
    private int zzbun;
    private int zzbuo;
    private int zzbup;
    private int zzbuq;

    private zzuq(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzbuq = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzbuo = this.pos;
        this.zzbum = z;
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzug() throws IOException {
        if (zzuw()) {
            this.zzbup = 0;
            return 0;
        }
        this.zzbup = zzuy();
        if ((this.zzbup >>> 3) == 0) {
            throw new zzvt("Protocol message contained an invalid tag (zero).");
        }
        return this.zzbup;
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final void zzan(int i) throws zzvt {
        if (this.zzbup != i) {
            throw zzvt.zzwn();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final boolean zzao(int i) throws IOException {
        int iZzug;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.limit - this.pos >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        if (bArr[i3] < 0) {
                            i2++;
                        }
                    }
                    throw zzvt.zzwm();
                }
                while (i2 < 10) {
                    if (zzvd() < 0) {
                        i2++;
                    }
                }
                throw zzvt.zzwm();
                return true;
            case 1:
                zzas(8);
                return true;
            case 2:
                zzas(zzuy());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzas(4);
                return true;
            default:
                throw zzvt.zzwo();
        }
        do {
            iZzug = zzug();
            if (iZzug != 0) {
            }
            zzan(((i >>> 3) << 3) | 4);
            return true;
        } while (zzao(iZzug));
        zzan(((i >>> 3) << 3) | 4);
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzvb());
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzva());
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final long zzuh() throws IOException {
        return zzuz();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final long zzui() throws IOException {
        return zzuz();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzuj() throws IOException {
        return zzuy();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final long zzuk() throws IOException {
        return zzvb();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzul() throws IOException {
        return zzva();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final boolean zzum() throws IOException {
        return zzuz() != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final String readString() throws IOException {
        int iZzuy = zzuy();
        if (iZzuy > 0 && iZzuy <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, iZzuy, zzvo.UTF_8);
            this.pos += iZzuy;
            return str;
        }
        if (iZzuy == 0) {
            return "";
        }
        if (iZzuy < 0) {
            throw zzvt.zzwl();
        }
        throw zzvt.zzwk();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final String zzun() throws IOException {
        int iZzuy = zzuy();
        if (iZzuy > 0 && iZzuy <= this.limit - this.pos) {
            String strZzh = zzyj.zzh(this.buffer, this.pos, iZzuy);
            this.pos += iZzuy;
            return strZzh;
        }
        if (iZzuy == 0) {
            return "";
        }
        if (iZzuy <= 0) {
            throw zzvt.zzwl();
        }
        throw zzvt.zzwk();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final <T extends zzwt> T zza(zzxd<T> zzxdVar, zzuz zzuzVar) throws IOException {
        int iZzuy = zzuy();
        if (this.zzbuh >= this.zzbui) {
            throw zzvt.zzwp();
        }
        int iZzaq = zzaq(iZzuy);
        this.zzbuh++;
        T tZza = zzxdVar.zza(this, zzuzVar);
        zzan(0);
        this.zzbuh--;
        zzar(iZzaq);
        return tZza;
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final zzud zzuo() throws IOException {
        byte[] bArrCopyOfRange;
        int iZzuy = zzuy();
        if (iZzuy > 0 && iZzuy <= this.limit - this.pos) {
            zzud zzudVarZzb = zzud.zzb(this.buffer, this.pos, iZzuy);
            this.pos += iZzuy;
            return zzudVarZzb;
        }
        if (iZzuy == 0) {
            return zzud.zzbtz;
        }
        if (iZzuy > 0 && iZzuy <= this.limit - this.pos) {
            int i = this.pos;
            this.pos += iZzuy;
            bArrCopyOfRange = Arrays.copyOfRange(this.buffer, i, this.pos);
        } else if (iZzuy <= 0) {
            if (iZzuy == 0) {
                bArrCopyOfRange = zzvo.zzbzj;
            } else {
                throw zzvt.zzwl();
            }
        } else {
            throw zzvt.zzwk();
        }
        return zzud.zzi(bArrCopyOfRange);
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzup() throws IOException {
        return zzuy();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzuq() throws IOException {
        return zzuy();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzur() throws IOException {
        return zzva();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final long zzus() throws IOException {
        return zzvb();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzut() throws IOException {
        int iZzuy = zzuy();
        return (-(iZzuy & 1)) ^ (iZzuy >>> 1);
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final long zzuu() throws IOException {
        long jZzuz = zzuz();
        return (jZzuz >>> 1) ^ (-(jZzuz & 1));
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
    
        if (r1[r2] >= 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzuy() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L6d
            byte[] r1 = r5.buffer
            int r2 = r0 + 1
            r0 = r1[r0]
            if (r0 < 0) goto L11
            r5.pos = r2
            return r0
        L11:
            int r3 = r5.limit
            int r3 = r3 - r2
            r4 = 9
            if (r3 < r4) goto L6d
            int r3 = r2 + 1
            r2 = r1[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L24
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L6a
        L24:
            int r2 = r3 + 1
            r3 = r1[r3]
            int r3 = r3 << 14
            r0 = r0 ^ r3
            if (r0 < 0) goto L31
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2f:
            r3 = r2
            goto L6a
        L31:
            int r3 = r2 + 1
            r2 = r1[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L3f
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            goto L6a
        L3f:
            int r2 = r3 + 1
            r3 = r1[r3]
            int r4 = r3 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r3 >= 0) goto L2f
            int r3 = r2 + 1
            r2 = r1[r2]
            if (r2 >= 0) goto L6a
            int r2 = r3 + 1
            r3 = r1[r3]
            if (r3 >= 0) goto L2f
            int r3 = r2 + 1
            r2 = r1[r2]
            if (r2 >= 0) goto L6a
            int r2 = r3 + 1
            r3 = r1[r3]
            if (r3 >= 0) goto L2f
            int r3 = r2 + 1
            r1 = r1[r2]
            if (r1 < 0) goto L6d
        L6a:
            r5.pos = r3
            return r0
        L6d:
            long r0 = r5.zzuv()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzuq.zzuy():int");
    }

    private final long zzuz() throws IOException {
        int i;
        long j;
        long j2;
        long j3;
        long j4;
        int i2 = this.pos;
        if (this.limit != i2) {
            byte[] bArr = this.buffer;
            int i3 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.pos = i3;
                return b;
            }
            if (this.limit - i3 >= 9) {
                int i4 = i3 + 1;
                int i5 = b ^ (bArr[i3] << 7);
                if (i5 < 0) {
                    j3 = i5 ^ (-128);
                } else {
                    int i6 = i4 + 1;
                    int i7 = i5 ^ (bArr[i4] << 14);
                    if (i7 >= 0) {
                        j4 = i7 ^ 16256;
                        i = i6;
                        j = j4;
                        this.pos = i;
                        return j;
                    }
                    i4 = i6 + 1;
                    int i8 = i7 ^ (bArr[i6] << 21);
                    if (i8 >= 0) {
                        long j5 = i8;
                        i = i4 + 1;
                        long j6 = j5 ^ (((long) bArr[i4]) << 28);
                        if (j6 < 0) {
                            int i9 = i + 1;
                            long j7 = j6 ^ (((long) bArr[i]) << 35);
                            if (j7 < 0) {
                                j = j7 ^ (-34093383808L);
                            } else {
                                i = i9 + 1;
                                long j8 = j7 ^ (((long) bArr[i9]) << 42);
                                if (j8 >= 0) {
                                    j2 = j8 ^ 4363953127296L;
                                } else {
                                    i9 = i + 1;
                                    long j9 = j8 ^ (((long) bArr[i]) << 49);
                                    if (j9 >= 0) {
                                        i = i9 + 1;
                                        long j10 = (j9 ^ (((long) bArr[i9]) << 56)) ^ 71499008037633920L;
                                        if (j10 < 0) {
                                            int i10 = i + 1;
                                            if (bArr[i] >= 0) {
                                                i = i10;
                                            }
                                        }
                                        j = j10;
                                        this.pos = i;
                                        return j;
                                    }
                                    j = j9 ^ (-558586000294016L);
                                }
                            }
                            i = i9;
                            this.pos = i;
                            return j;
                        }
                        j2 = j6 ^ 266354560;
                        j = j2;
                        this.pos = i;
                        return j;
                    }
                    j3 = i8 ^ (-2080896);
                }
                j4 = j3;
                i = i4;
                j = j4;
                this.pos = i;
                return j;
            }
        }
        return zzuv();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    final long zzuv() throws IOException {
        long j = 0;
        int i = 0;
        while (i < 64) {
            byte bZzvd = zzvd();
            long j2 = j | (((long) (bZzvd & ByteCompanionObject.MAX_VALUE)) << i);
            if ((bZzvd & ByteCompanionObject.MIN_VALUE) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzvt.zzwm();
    }

    private final int zzva() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzvt.zzwk();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private final long zzvb() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzvt.zzwk();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzaq(int i) throws zzvt {
        if (i < 0) {
            throw zzvt.zzwl();
        }
        int iZzux = i + zzux();
        int i2 = this.zzbuq;
        if (iZzux > i2) {
            throw zzvt.zzwk();
        }
        this.zzbuq = iZzux;
        zzvc();
        return i2;
    }

    private final void zzvc() {
        this.limit += this.zzbun;
        int i = this.limit - this.zzbuo;
        if (i > this.zzbuq) {
            this.zzbun = i - this.zzbuq;
            this.limit -= this.zzbun;
        } else {
            this.zzbun = 0;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final void zzar(int i) {
        this.zzbuq = i;
        zzvc();
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final boolean zzuw() throws IOException {
        return this.pos == this.limit;
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final int zzux() {
        return this.pos - this.zzbuo;
    }

    private final byte zzvd() throws IOException {
        if (this.pos == this.limit) {
            throw zzvt.zzwk();
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzuo
    public final void zzas(int i) throws IOException {
        if (i >= 0 && i <= this.limit - this.pos) {
            this.pos += i;
        } else {
            if (i < 0) {
                throw zzvt.zzwl();
            }
            throw zzvt.zzwk();
        }
    }
}
