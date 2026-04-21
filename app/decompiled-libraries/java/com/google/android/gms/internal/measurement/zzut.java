package com.google.android.gms.internal.measurement;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzut extends zzuc {
    private static final Logger logger = Logger.getLogger(zzut.class.getName());
    private static final boolean zzbuv = zzyh.zzyi();
    zzuv zzbuw;

    public static zzut zzj(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzud zzudVar) throws IOException;

    public abstract void zza(int i, zzwt zzwtVar) throws IOException;

    abstract void zza(int i, zzwt zzwtVar, zzxj zzxjVar) throws IOException;

    public abstract void zza(zzud zzudVar) throws IOException;

    abstract void zza(zzwt zzwtVar, zzxj zzxjVar) throws IOException;

    public abstract void zzav(long j) throws IOException;

    public abstract void zzax(int i) throws IOException;

    public abstract void zzax(long j) throws IOException;

    public abstract void zzay(int i) throws IOException;

    public abstract void zzb(int i, zzud zzudVar) throws IOException;

    public abstract void zzb(int i, zzwt zzwtVar) throws IOException;

    public abstract void zzb(int i, String str) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(zzwt zzwtVar) throws IOException;

    public abstract void zzba(int i) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zze(int i, int i2) throws IOException;

    abstract void zze(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzfw(String str) throws IOException;

    public abstract void zzg(int i, int i2) throws IOException;

    public abstract int zzvg();

    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARN: Illegal instructions before constructor call */
        zzc(String str) {
            String strValueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String strValueOf2 = String.valueOf(str);
            super(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        zzc(String str, Throwable th) {
            String strValueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String strValueOf2 = String.valueOf(str);
            super(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf), th);
        }
    }

    public static zzut zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (byteBuffer.isDirect() && !byteBuffer.isReadOnly()) {
            if (zzyh.zzyj()) {
                return new zze(byteBuffer);
            }
            return new zzd(byteBuffer);
        }
        throw new IllegalArgumentException("ByteBuffer is read-only");
    }

    static final class zzd extends zzut {
        private final int zzbuy;
        private final ByteBuffer zzbuz;
        private final ByteBuffer zzbva;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzbuz = byteBuffer;
            this.zzbva = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzbuy = byteBuffer.position();
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(int i, int i2) throws IOException {
            zzay((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzax(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zze(int i, int i2) throws IOException {
            zzc(i, 0);
            zzay(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzg(int i, int i2) throws IOException {
            zzc(i, 5);
            zzba(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, long j) throws IOException {
            zzc(i, 0);
            zzav(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(int i, long j) throws IOException {
            zzc(i, 1);
            zzax(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, boolean z) throws IOException {
            zzc(i, 0);
            zzc(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, String str) throws IOException {
            zzc(i, 2);
            zzfw(str);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, zzud zzudVar) throws IOException {
            zzc(i, 2);
            zza(zzudVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, zzwt zzwtVar) throws IOException {
            zzc(i, 2);
            zzb(zzwtVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        final void zza(int i, zzwt zzwtVar, zzxj zzxjVar) throws IOException {
            zzc(i, 2);
            zza(zzwtVar, zzxjVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, zzwt zzwtVar) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzwtVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, zzud zzudVar) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzudVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(zzwt zzwtVar) throws IOException {
            zzay(zzwtVar.zzvu());
            zzwtVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        final void zza(zzwt zzwtVar, zzxj zzxjVar) throws IOException {
            zztw zztwVar = (zztw) zzwtVar;
            int iZztu = zztwVar.zztu();
            if (iZztu == -1) {
                iZztu = zzxjVar.zzae(zztwVar);
                zztwVar.zzah(iZztu);
            }
            zzay(iZztu);
            zzxjVar.zza(zzwtVar, this.zzbuw);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(byte b) throws IOException {
            try {
                this.zzbva.put(b);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(zzud zzudVar) throws IOException {
            zzay(zzudVar.size());
            zzudVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzay(i2);
            write(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzax(int i) throws IOException {
            if (i >= 0) {
                zzay(i);
            } else {
                zzav(i);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzay(int i) throws IOException {
            while ((i & (-128)) != 0) {
                try {
                    this.zzbva.put((byte) ((i & 127) | 128));
                    i >>>= 7;
                } catch (BufferOverflowException e) {
                    throw new zzc(e);
                }
            }
            this.zzbva.put((byte) i);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzba(int i) throws IOException {
            try {
                this.zzbva.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzav(long j) throws IOException {
            while ((j & (-128)) != 0) {
                try {
                    this.zzbva.put((byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                } catch (BufferOverflowException e) {
                    throw new zzc(e);
                }
            }
            this.zzbva.put((byte) j);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzax(long j) throws IOException {
            try {
                this.zzbva.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.zzbva.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            } catch (BufferOverflowException e2) {
                throw new zzc(e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzuc
        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzfw(String str) throws IOException {
            int iPosition = this.zzbva.position();
            try {
                int iZzbd = zzbd(str.length() * 3);
                int iZzbd2 = zzbd(str.length());
                if (iZzbd2 == iZzbd) {
                    int iPosition2 = this.zzbva.position() + iZzbd2;
                    this.zzbva.position(iPosition2);
                    zzfy(str);
                    int iPosition3 = this.zzbva.position();
                    this.zzbva.position(iPosition);
                    zzay(iPosition3 - iPosition2);
                    this.zzbva.position(iPosition3);
                    return;
                }
                zzay(zzyj.zza(str));
                zzfy(str);
            } catch (zzyn e) {
                this.zzbva.position(iPosition);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void flush() {
            this.zzbuz.position(this.zzbva.position());
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final int zzvg() {
            return this.zzbva.remaining();
        }

        private final void zzfy(String str) throws IOException {
            try {
                zzyj.zza(str, this.zzbva);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            }
        }
    }

    static final class zzb extends zza {
        private final ByteBuffer zzbux;
        private int zzbuy;

        zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzbux = byteBuffer;
            this.zzbuy = byteBuffer.position();
        }

        @Override // com.google.android.gms.internal.measurement.zzut.zza, com.google.android.gms.internal.measurement.zzut
        public final void flush() {
            this.zzbux.position(this.zzbuy + zzvi());
        }
    }

    static final class zze extends zzut {
        private final ByteBuffer zzbuz;
        private final ByteBuffer zzbva;
        private final long zzbvb;
        private final long zzbvc;
        private final long zzbvd;
        private final long zzbve;
        private long zzbvf;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzbuz = byteBuffer;
            this.zzbva = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzbvb = zzyh.zzb(byteBuffer);
            this.zzbvc = this.zzbvb + ((long) byteBuffer.position());
            this.zzbvd = this.zzbvb + ((long) byteBuffer.limit());
            this.zzbve = this.zzbvd - 10;
            this.zzbvf = this.zzbvc;
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(int i, int i2) throws IOException {
            zzay((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzax(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zze(int i, int i2) throws IOException {
            zzc(i, 0);
            zzay(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzg(int i, int i2) throws IOException {
            zzc(i, 5);
            zzba(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, long j) throws IOException {
            zzc(i, 0);
            zzav(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(int i, long j) throws IOException {
            zzc(i, 1);
            zzax(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, boolean z) throws IOException {
            zzc(i, 0);
            zzc(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, String str) throws IOException {
            zzc(i, 2);
            zzfw(str);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, zzud zzudVar) throws IOException {
            zzc(i, 2);
            zza(zzudVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, zzwt zzwtVar) throws IOException {
            zzc(i, 2);
            zzb(zzwtVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        final void zza(int i, zzwt zzwtVar, zzxj zzxjVar) throws IOException {
            zzc(i, 2);
            zza(zzwtVar, zzxjVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, zzwt zzwtVar) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzwtVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, zzud zzudVar) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzudVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(zzwt zzwtVar) throws IOException {
            zzay(zzwtVar.zzvu());
            zzwtVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        final void zza(zzwt zzwtVar, zzxj zzxjVar) throws IOException {
            zztw zztwVar = (zztw) zzwtVar;
            int iZztu = zztwVar.zztu();
            if (iZztu == -1) {
                iZztu = zzxjVar.zzae(zztwVar);
                zztwVar.zzah(iZztu);
            }
            zzay(iZztu);
            zzxjVar.zza(zzwtVar, this.zzbuw);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(byte b) throws IOException {
            if (this.zzbvf >= this.zzbvd) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzbvf), Long.valueOf(this.zzbvd), 1));
            }
            long j = this.zzbvf;
            this.zzbvf = j + 1;
            zzyh.zza(j, b);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(zzud zzudVar) throws IOException {
            zzay(zzudVar.size());
            zzudVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzay(i2);
            write(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzax(int i) throws IOException {
            if (i >= 0) {
                zzay(i);
            } else {
                zzav(i);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzay(int i) throws IOException {
            if (this.zzbvf <= this.zzbve) {
                while ((i & (-128)) != 0) {
                    long j = this.zzbvf;
                    this.zzbvf = j + 1;
                    zzyh.zza(j, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                long j2 = this.zzbvf;
                this.zzbvf = j2 + 1;
                zzyh.zza(j2, (byte) i);
                return;
            }
            while (this.zzbvf < this.zzbvd) {
                if ((i & (-128)) == 0) {
                    long j3 = this.zzbvf;
                    this.zzbvf = j3 + 1;
                    zzyh.zza(j3, (byte) i);
                    return;
                } else {
                    long j4 = this.zzbvf;
                    this.zzbvf = j4 + 1;
                    zzyh.zza(j4, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzbvf), Long.valueOf(this.zzbvd), 1));
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzba(int i) throws IOException {
            this.zzbva.putInt((int) (this.zzbvf - this.zzbvb), i);
            this.zzbvf += 4;
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzav(long j) throws IOException {
            if (this.zzbvf <= this.zzbve) {
                while ((j & (-128)) != 0) {
                    long j2 = this.zzbvf;
                    this.zzbvf = j2 + 1;
                    zzyh.zza(j2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                long j3 = this.zzbvf;
                this.zzbvf = j3 + 1;
                zzyh.zza(j3, (byte) j);
                return;
            }
            while (this.zzbvf < this.zzbvd) {
                if ((j & (-128)) == 0) {
                    long j4 = this.zzbvf;
                    this.zzbvf = j4 + 1;
                    zzyh.zza(j4, (byte) j);
                    return;
                } else {
                    long j5 = this.zzbvf;
                    this.zzbvf = j5 + 1;
                    zzyh.zza(j5, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzbvf), Long.valueOf(this.zzbvd), 1));
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzax(long j) throws IOException {
            this.zzbva.putLong((int) (this.zzbvf - this.zzbvb), j);
            this.zzbvf += 8;
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = i2;
                if (this.zzbvd - j >= this.zzbvf) {
                    zzyh.zza(bArr, i, this.zzbvf, j);
                    this.zzbvf += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException(FirebaseAnalytics.Param.VALUE);
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzbvf), Long.valueOf(this.zzbvd), Integer.valueOf(i2)));
        }

        @Override // com.google.android.gms.internal.measurement.zzuc
        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzfw(String str) throws IOException {
            long j = this.zzbvf;
            try {
                int iZzbd = zzbd(str.length() * 3);
                int iZzbd2 = zzbd(str.length());
                if (iZzbd2 == iZzbd) {
                    int i = ((int) (this.zzbvf - this.zzbvb)) + iZzbd2;
                    this.zzbva.position(i);
                    zzyj.zza(str, this.zzbva);
                    int iPosition = this.zzbva.position() - i;
                    zzay(iPosition);
                    this.zzbvf += (long) iPosition;
                    return;
                }
                int iZza = zzyj.zza(str);
                zzay(iZza);
                zzbe(this.zzbvf);
                zzyj.zza(str, this.zzbva);
                this.zzbvf += (long) iZza;
            } catch (zzyn e) {
                this.zzbvf = j;
                zzbe(this.zzbvf);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzc(e3);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void flush() {
            this.zzbuz.position((int) (this.zzbvf - this.zzbvb));
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final int zzvg() {
            return (int) (this.zzbvd - this.zzbvf);
        }

        private final void zzbe(long j) {
            this.zzbva.position((int) (j - this.zzbvb));
        }
    }

    static class zza extends zzut {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i3 = i + i2;
            if ((i | i2 | (bArr.length - i3)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            this.buffer = bArr;
            this.offset = i;
            this.position = i;
            this.limit = i3;
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(int i, int i2) throws IOException {
            zzay((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzax(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zze(int i, int i2) throws IOException {
            zzc(i, 0);
            zzay(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzg(int i, int i2) throws IOException {
            zzc(i, 5);
            zzba(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, long j) throws IOException {
            zzc(i, 0);
            zzav(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(int i, long j) throws IOException {
            zzc(i, 1);
            zzax(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, boolean z) throws IOException {
            zzc(i, 0);
            zzc(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, String str) throws IOException {
            zzc(i, 2);
            zzfw(str);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, zzud zzudVar) throws IOException {
            zzc(i, 2);
            zza(zzudVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(zzud zzudVar) throws IOException {
            zzay(zzudVar.size());
            zzudVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzay(i2);
            write(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zza(int i, zzwt zzwtVar) throws IOException {
            zzc(i, 2);
            zzb(zzwtVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        final void zza(int i, zzwt zzwtVar, zzxj zzxjVar) throws IOException {
            zzc(i, 2);
            zztw zztwVar = (zztw) zzwtVar;
            int iZztu = zztwVar.zztu();
            if (iZztu == -1) {
                iZztu = zzxjVar.zzae(zztwVar);
                zztwVar.zzah(iZztu);
            }
            zzay(iZztu);
            zzxjVar.zza(zzwtVar, this.zzbuw);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, zzwt zzwtVar) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzwtVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(int i, zzud zzudVar) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zza(3, zzudVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzb(zzwt zzwtVar) throws IOException {
            zzay(zzwtVar.zzvu());
            zzwtVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        final void zza(zzwt zzwtVar, zzxj zzxjVar) throws IOException {
            zztw zztwVar = (zztw) zzwtVar;
            int iZztu = zztwVar.zztu();
            if (iZztu == -1) {
                iZztu = zzxjVar.zzae(zztwVar);
                zztwVar.zzah(iZztu);
            }
            zzay(iZztu);
            zzxjVar.zza(zzwtVar, this.zzbuw);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzax(int i) throws IOException {
            if (i >= 0) {
                zzay(i);
            } else {
                zzav(i);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzay(int i) throws IOException {
            if (zzut.zzbuv && zzvg() >= 10) {
                while ((i & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    zzyh.zza(bArr, i2, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                zzyh.zza(bArr2, i3, (byte) i);
                return;
            }
            while ((i & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr3[i4] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 1;
            bArr4[i5] = (byte) i;
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzba(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = i >> 24;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzav(long j) throws IOException {
            if (zzut.zzbuv && zzvg() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    zzyh.zza(bArr, i, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                zzyh.zza(bArr2, i2, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr3[i3] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr4[i4] = (byte) j;
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzax(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) j;
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) (j >> 8);
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) (j >> 16);
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) (j >> 24);
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) (j >> 32);
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) (j >> 40);
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) (j >> 48);
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzuc
        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final void zzfw(String str) throws IOException {
            int i = this.position;
            try {
                int iZzbd = zzbd(str.length() * 3);
                int iZzbd2 = zzbd(str.length());
                if (iZzbd2 == iZzbd) {
                    this.position = i + iZzbd2;
                    int iZza = zzyj.zza(str, this.buffer, this.position, zzvg());
                    this.position = i;
                    zzay((iZza - i) - iZzbd2);
                    this.position = iZza;
                    return;
                }
                zzay(zzyj.zza(str));
                this.position = zzyj.zza(str, this.buffer, this.position, zzvg());
            } catch (zzyn e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc(e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public void flush() {
        }

        @Override // com.google.android.gms.internal.measurement.zzut
        public final int zzvg() {
            return this.limit - this.position;
        }

        public final int zzvi() {
            return this.position - this.offset;
        }
    }

    private zzut() {
    }

    public final void zzf(int i, int i2) throws IOException {
        zze(i, zzbi(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzbd(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzg(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzaz(int i) throws IOException {
        zzay(zzbi(i));
    }

    public final void zzaw(long j) throws IOException {
        zzav(zzbd(j));
    }

    public final void zza(float f) throws IOException {
        zzba(Float.floatToRawIntBits(f));
    }

    public final void zzb(double d) throws IOException {
        zzax(Double.doubleToRawLongBits(d));
    }

    public final void zzu(boolean z) throws IOException {
        zzc(z ? (byte) 1 : (byte) 0);
    }

    public static int zzh(int i, int i2) {
        return zzbb(i) + zzbc(i2);
    }

    public static int zzi(int i, int i2) {
        return zzbb(i) + zzbd(i2);
    }

    public static int zzj(int i, int i2) {
        return zzbb(i) + zzbd(zzbi(i2));
    }

    public static int zzk(int i, int i2) {
        return zzbb(i) + 4;
    }

    public static int zzl(int i, int i2) {
        return zzbb(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zzbb(i) + zzaz(j);
    }

    public static int zze(int i, long j) {
        return zzbb(i) + zzaz(j);
    }

    public static int zzf(int i, long j) {
        return zzbb(i) + zzaz(zzbd(j));
    }

    public static int zzg(int i, long j) {
        return zzbb(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzbb(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzbb(i) + 4;
    }

    public static int zzb(int i, double d) {
        return zzbb(i) + 8;
    }

    public static int zzc(int i, boolean z) {
        return zzbb(i) + 1;
    }

    public static int zzm(int i, int i2) {
        return zzbb(i) + zzbc(i2);
    }

    public static int zzc(int i, String str) {
        return zzbb(i) + zzfx(str);
    }

    public static int zzc(int i, zzud zzudVar) {
        int iZzbb = zzbb(i);
        int size = zzudVar.size();
        return iZzbb + zzbd(size) + size;
    }

    public static int zza(int i, zzwa zzwaVar) {
        int iZzbb = zzbb(i);
        int iZzvu = zzwaVar.zzvu();
        return iZzbb + zzbd(iZzvu) + iZzvu;
    }

    public static int zzc(int i, zzwt zzwtVar) {
        return zzbb(i) + zzc(zzwtVar);
    }

    static int zzb(int i, zzwt zzwtVar, zzxj zzxjVar) {
        return zzbb(i) + zzb(zzwtVar, zzxjVar);
    }

    public static int zzd(int i, zzwt zzwtVar) {
        return (zzbb(1) << 1) + zzi(2, i) + zzc(3, zzwtVar);
    }

    public static int zzd(int i, zzud zzudVar) {
        return (zzbb(1) << 1) + zzi(2, i) + zzc(3, zzudVar);
    }

    public static int zzb(int i, zzwa zzwaVar) {
        return (zzbb(1) << 1) + zzi(2, i) + zza(3, zzwaVar);
    }

    public static int zzbb(int i) {
        return zzbd(i << 3);
    }

    public static int zzbc(int i) {
        if (i >= 0) {
            return zzbd(i);
        }
        return 10;
    }

    public static int zzbd(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        if ((i & (-268435456)) == 0) {
            return 4;
        }
        return 5;
    }

    public static int zzbe(int i) {
        return zzbd(zzbi(i));
    }

    public static int zzbf(int i) {
        return 4;
    }

    public static int zzbg(int i) {
        return 4;
    }

    public static int zzay(long j) {
        return zzaz(j);
    }

    public static int zzaz(long j) {
        int i;
        if ((j & (-128)) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((j & (-34359738368L)) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((j & (-2097152)) != 0) {
            i += 2;
            j >>>= 14;
        }
        if ((j & (-16384)) != 0) {
            return i + 1;
        }
        return i;
    }

    public static int zzba(long j) {
        return zzaz(zzbd(j));
    }

    public static int zzbb(long j) {
        return 8;
    }

    public static int zzbc(long j) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzc(double d) {
        return 8;
    }

    public static int zzv(boolean z) {
        return 1;
    }

    public static int zzbh(int i) {
        return zzbc(i);
    }

    public static int zzfx(String str) {
        int length;
        try {
            length = zzyj.zza(str);
        } catch (zzyn e) {
            length = str.getBytes(zzvo.UTF_8).length;
        }
        return zzbd(length) + length;
    }

    public static int zza(zzwa zzwaVar) {
        int iZzvu = zzwaVar.zzvu();
        return zzbd(iZzvu) + iZzvu;
    }

    public static int zzb(zzud zzudVar) {
        int size = zzudVar.size();
        return zzbd(size) + size;
    }

    public static int zzk(byte[] bArr) {
        int length = bArr.length;
        return zzbd(length) + length;
    }

    public static int zzc(zzwt zzwtVar) {
        int iZzvu = zzwtVar.zzvu();
        return zzbd(iZzvu) + iZzvu;
    }

    static int zzb(zzwt zzwtVar, zzxj zzxjVar) {
        zztw zztwVar = (zztw) zzwtVar;
        int iZztu = zztwVar.zztu();
        if (iZztu == -1) {
            iZztu = zzxjVar.zzae(zztwVar);
            zztwVar.zzah(iZztu);
        }
        return zzbd(iZztu) + iZztu;
    }

    private static int zzbi(int i) {
        return (i >> 31) ^ (i << 1);
    }

    private static long zzbd(long j) {
        return (j << 1) ^ (j >> 63);
    }

    final void zza(String str, zzyn zzynVar) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzynVar);
        byte[] bytes = str.getBytes(zzvo.UTF_8);
        try {
            zzay(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (zzc e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzc(e2);
        }
    }

    @Deprecated
    static int zzc(int i, zzwt zzwtVar, zzxj zzxjVar) {
        int iZzbb = zzbb(i) << 1;
        zztw zztwVar = (zztw) zzwtVar;
        int iZztu = zztwVar.zztu();
        if (iZztu == -1) {
            iZztu = zzxjVar.zzae(zztwVar);
            zztwVar.zzah(iZztu);
        }
        return iZzbb + iZztu;
    }

    @Deprecated
    public static int zzd(zzwt zzwtVar) {
        return zzwtVar.zzvu();
    }

    @Deprecated
    public static int zzbj(int i) {
        return zzbd(i);
    }
}
