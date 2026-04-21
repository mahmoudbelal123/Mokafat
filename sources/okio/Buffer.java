package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.common.util.AndroidUtilsLight;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable {
    private static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    static final int REPLACEMENT_CHARACTER = 65533;

    @Nullable
    Segment head;
    long size;

    public long size() {
        return this.size;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.Buffer.1
            @Override // java.io.OutputStream
            public void write(int b) {
                Buffer.this.writeByte((int) ((byte) b));
            }

            @Override // java.io.OutputStream
            public void write(byte[] data, int offset, int byteCount) {
                Buffer.this.write(data, offset, byteCount);
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }
        };
    }

    @Override // okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // okio.BufferedSource
    public void require(long byteCount) throws EOFException {
        if (this.size < byteCount) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public boolean request(long byteCount) {
        return this.size >= byteCount;
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.Buffer.2
            @Override // java.io.InputStream
            public int read() {
                if (Buffer.this.size > 0) {
                    return Buffer.this.readByte() & 255;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(byte[] sink, int offset, int byteCount) {
                return Buffer.this.read(sink, offset, byteCount);
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.size, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    public Buffer copyTo(OutputStream out) throws IOException {
        return copyTo(out, 0L, this.size);
    }

    public Buffer copyTo(OutputStream out, long offset, long byteCount) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, offset, byteCount);
        if (byteCount == 0) {
            return this;
        }
        Segment s = this.head;
        while (offset >= s.limit - s.pos) {
            long offset2 = offset - ((long) (s.limit - s.pos));
            s = s.next;
            offset = offset2;
        }
        while (byteCount > 0) {
            int pos = (int) (((long) s.pos) + offset);
            int toCopy = (int) Math.min(s.limit - pos, byteCount);
            out.write(s.data, pos, toCopy);
            offset = 0;
            s = s.next;
            byteCount -= (long) toCopy;
        }
        return this;
    }

    public Buffer copyTo(Buffer out, long offset, long byteCount) {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, offset, byteCount);
        if (byteCount == 0) {
            return this;
        }
        out.size += byteCount;
        Segment s = this.head;
        while (offset >= s.limit - s.pos) {
            long offset2 = offset - ((long) (s.limit - s.pos));
            s = s.next;
            offset = offset2;
        }
        while (byteCount > 0) {
            Segment copy = new Segment(s);
            copy.pos = (int) (((long) copy.pos) + offset);
            copy.limit = Math.min(copy.pos + ((int) byteCount), copy.limit);
            if (out.head == null) {
                copy.prev = copy;
                copy.next = copy;
                out.head = copy;
            } else {
                out.head.prev.push(copy);
            }
            long byteCount2 = byteCount - ((long) (copy.limit - copy.pos));
            offset = 0;
            s = s.next;
            byteCount = byteCount2;
        }
        return this;
    }

    public Buffer writeTo(OutputStream out) throws IOException {
        return writeTo(out, this.size);
    }

    public Buffer writeTo(OutputStream out, long byteCount) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, 0L, byteCount);
        Segment s = this.head;
        while (byteCount > 0) {
            int toCopy = (int) Math.min(byteCount, s.limit - s.pos);
            out.write(s.data, s.pos, toCopy);
            s.pos += toCopy;
            this.size -= (long) toCopy;
            long byteCount2 = byteCount - ((long) toCopy);
            if (s.pos == s.limit) {
                Segment toRecycle = s;
                Segment segmentPop = toRecycle.pop();
                s = segmentPop;
                this.head = segmentPop;
                SegmentPool.recycle(toRecycle);
            }
            byteCount = byteCount2;
        }
        return this;
    }

    public Buffer readFrom(InputStream in) throws IOException {
        readFrom(in, LongCompanionObject.MAX_VALUE, true);
        return this;
    }

    public Buffer readFrom(InputStream in, long byteCount) throws IOException {
        if (byteCount >= 0) {
            readFrom(in, byteCount, false);
            return this;
        }
        throw new IllegalArgumentException("byteCount < 0: " + byteCount);
    }

    private void readFrom(InputStream in, long byteCount, boolean forever) throws IOException {
        if (in == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (byteCount > 0 || forever) {
                Segment tail = writableSegment(1);
                int maxToCopy = (int) Math.min(byteCount, 8192 - tail.limit);
                int bytesRead = in.read(tail.data, tail.limit, maxToCopy);
                if (bytesRead == -1) {
                    if (!forever) {
                        throw new EOFException();
                    }
                    return;
                } else {
                    tail.limit += bytesRead;
                    this.size += (long) bytesRead;
                    byteCount -= (long) bytesRead;
                }
            } else {
                return;
            }
        }
    }

    public long completeSegmentByteCount() {
        long result = this.size;
        if (result == 0) {
            return 0L;
        }
        Segment tail = this.head.prev;
        if (tail.limit < 8192 && tail.owner) {
            return result - ((long) (tail.limit - tail.pos));
        }
        return result;
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        if (this.size == 0) {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        byte[] data = segment.data;
        int pos2 = pos + 1;
        byte b = data[pos];
        this.size--;
        if (pos2 == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos2;
        }
        return b;
    }

    public byte getByte(long pos) {
        Util.checkOffsetAndCount(this.size, pos, 1L);
        Segment s = this.head;
        while (true) {
            int segmentByteCount = s.limit - s.pos;
            if (pos < segmentByteCount) {
                return s.data[s.pos + ((int) pos)];
            }
            s = s.next;
            pos -= (long) segmentByteCount;
        }
    }

    @Override // okio.BufferedSource
    public short readShort() {
        if (this.size < 2) {
            throw new IllegalStateException("size < 2: " + this.size);
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 2) {
            int s = ((readByte() & 255) << 8) | (readByte() & 255);
            return (short) s;
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        int pos3 = pos2 + 1;
        int s2 = ((data[pos] & 255) << 8) | (data[pos2] & 255);
        this.size -= 2;
        if (pos3 == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos3;
        }
        return (short) s2;
    }

    @Override // okio.BufferedSource
    public int readInt() {
        if (this.size < 4) {
            throw new IllegalStateException("size < 4: " + this.size);
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        int pos3 = pos2 + 1;
        int i = ((data[pos] & 255) << 24) | ((data[pos2] & 255) << 16);
        int pos4 = pos3 + 1;
        int i2 = i | ((data[pos3] & 255) << 8);
        int pos5 = pos4 + 1;
        int i3 = i2 | (data[pos4] & 255);
        this.size -= 4;
        if (pos5 == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos5;
        }
        return i3;
    }

    @Override // okio.BufferedSource
    public long readLong() {
        if (this.size < 8) {
            throw new IllegalStateException("size < 8: " + this.size);
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 8) {
            return ((((long) readInt()) & 4294967295L) << 32) | (((long) readInt()) & 4294967295L);
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        long j = (((long) data[pos]) & 255) << 56;
        int pos3 = pos2 + 1;
        long j2 = j | ((((long) data[pos2]) & 255) << 48);
        int pos4 = pos3 + 1;
        long j3 = j2 | ((((long) data[pos3]) & 255) << 40);
        int pos5 = pos4 + 1;
        int pos6 = pos5 + 1;
        long j4 = j3 | ((((long) data[pos4]) & 255) << 32) | ((((long) data[pos5]) & 255) << 24);
        int pos7 = pos6 + 1;
        long j5 = j4 | ((((long) data[pos6]) & 255) << 16);
        int pos8 = pos7 + 1;
        long j6 = j5 | ((((long) data[pos7]) & 255) << 8);
        int pos9 = pos8 + 1;
        long v = j6 | (((long) data[pos8]) & 255);
        this.size -= 8;
        if (pos9 == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos9;
        }
        return v;
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005a, code lost:
    
        r5 = new okio.Buffer().writeDecimalLong(r1).writeByte((int) r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0067, code lost:
    
        if (r4 != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0069, code lost:
    
        r5.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0088, code lost:
    
        throw new java.lang.NumberFormatException("Number too large: " + r5.readUtf8());
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d7  */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long readDecimalLong() {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b6 A[EDGE_INSN: B:44:0x00b6->B:40:0x00b6 BREAK  A[LOOP:0: B:7:0x0016->B:46:?], SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long readHexadecimalUnsignedLong() {
        /*
            r17 = this;
            r0 = r17
            long r1 = r0.size
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L12
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "size == 0"
            r1.<init>(r2)
            throw r1
        L12:
            r1 = 0
            r5 = 0
            r6 = 0
        L16:
            okio.Segment r7 = r0.head
            byte[] r8 = r7.data
            int r9 = r7.pos
            int r10 = r7.limit
        L1e:
            if (r9 >= r10) goto La2
            r11 = r8[r9]
            r12 = 48
            if (r11 < r12) goto L2d
            r12 = 57
            if (r11 > r12) goto L2d
            int r12 = r11 + (-48)
        L2c:
            goto L47
        L2d:
            r12 = 97
            if (r11 < r12) goto L3a
            r12 = 102(0x66, float:1.43E-43)
            if (r11 > r12) goto L3a
            int r12 = r11 + (-97)
            int r12 = r12 + 10
            goto L2c
        L3a:
            r12 = 65
            if (r11 < r12) goto L83
            r12 = 70
            if (r11 > r12) goto L83
            int r12 = r11 + (-65)
            int r12 = r12 + 10
            goto L2c
        L47:
            r13 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r15 = r1 & r13
            int r13 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r13 == 0) goto L78
            okio.Buffer r3 = new okio.Buffer
            r3.<init>()
            okio.Buffer r3 = r3.writeHexadecimalUnsignedLong(r1)
            okio.Buffer r3 = r3.writeByte(r11)
            java.lang.NumberFormatException r4 = new java.lang.NumberFormatException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "Number too large: "
            r13.append(r14)
            java.lang.String r14 = r3.readUtf8()
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r4.<init>(r13)
            throw r4
        L78:
            r13 = 4
            long r1 = r1 << r13
            long r13 = (long) r12
            long r11 = r1 | r13
            int r9 = r9 + 1
            int r5 = r5 + 1
            r1 = r11
            goto L1e
        L83:
            if (r5 != 0) goto La0
            java.lang.NumberFormatException r3 = new java.lang.NumberFormatException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r12 = "Expected leading [0-9a-fA-F] character but was 0x"
            r4.append(r12)
            java.lang.String r12 = java.lang.Integer.toHexString(r11)
            r4.append(r12)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        La0:
            r6 = 1
        La2:
            if (r9 != r10) goto Lae
            okio.Segment r11 = r7.pop()
            r0.head = r11
            okio.SegmentPool.recycle(r7)
            goto Lb0
        Lae:
            r7.pos = r9
        Lb0:
            if (r6 != 0) goto Lb6
            okio.Segment r7 = r0.head
            if (r7 != 0) goto L16
        Lb6:
            long r3 = r0.size
            long r7 = (long) r5
            long r9 = r3 - r7
            r0.size = r9
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long byteCount) throws EOFException {
        return new ByteString(readByteArray(byteCount));
    }

    @Override // okio.BufferedSource
    public int select(Options options) {
        Segment s = this.head;
        if (s == null) {
            return options.indexOf(ByteString.EMPTY);
        }
        ByteString[] byteStrings = options.byteStrings;
        int listSize = byteStrings.length;
        int i = 0;
        while (true) {
            int listSize2 = listSize;
            if (i >= listSize2) {
                return -1;
            }
            ByteString b = byteStrings[i];
            if (this.size >= b.size() && rangeEquals(s, s.pos, b, 0, b.size())) {
                try {
                    skip(b.size());
                    return i;
                } catch (EOFException e) {
                    throw new AssertionError(e);
                }
            }
            i++;
            listSize = listSize2;
        }
    }

    int selectPrefix(Options options) {
        Segment s = this.head;
        ByteString[] byteStrings = options.byteStrings;
        int listSize = byteStrings.length;
        int i = 0;
        while (true) {
            int listSize2 = listSize;
            if (i >= listSize2) {
                return -1;
            }
            ByteString b = byteStrings[i];
            int bytesLimit = (int) Math.min(this.size, b.size());
            if (bytesLimit == 0 || rangeEquals(s, s.pos, b, 0, bytesLimit)) {
                break;
            }
            i++;
            listSize = listSize2;
        }
        return i;
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer sink, long byteCount) throws EOFException {
        if (this.size < byteCount) {
            sink.write(this, this.size);
            throw new EOFException();
        }
        sink.write(this, byteCount);
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        long byteCount = this.size;
        if (byteCount > 0) {
            sink.write(this, byteCount);
        }
        return byteCount;
    }

    @Override // okio.BufferedSource
    public String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.BufferedSource
    public String readUtf8(long byteCount) throws EOFException {
        return readString(byteCount, Util.UTF_8);
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.BufferedSource
    public String readString(long byteCount, Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, byteCount);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (byteCount > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
        }
        if (byteCount == 0) {
            return "";
        }
        Segment s = this.head;
        if (((long) s.pos) + byteCount > s.limit) {
            return new String(readByteArray(byteCount), charset);
        }
        String result = new String(s.data, s.pos, (int) byteCount, charset);
        s.pos = (int) (((long) s.pos) + byteCount);
        this.size -= byteCount;
        if (s.pos == s.limit) {
            this.head = s.pop();
            SegmentPool.recycle(s);
        }
        return result;
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() throws EOFException {
        long newline = indexOf((byte) 10);
        if (newline != -1) {
            return readUtf8Line(newline);
        }
        if (this.size != 0) {
            return readUtf8(this.size);
        }
        return null;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(LongCompanionObject.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long limit) throws EOFException {
        if (limit < 0) {
            throw new IllegalArgumentException("limit < 0: " + limit);
        }
        long scanLength = LongCompanionObject.MAX_VALUE;
        if (limit != LongCompanionObject.MAX_VALUE) {
            scanLength = limit + 1;
        }
        long newline = indexOf((byte) 10, 0L, scanLength);
        if (newline != -1) {
            return readUtf8Line(newline);
        }
        if (scanLength < size() && getByte(scanLength - 1) == 13 && getByte(scanLength) == 10) {
            return readUtf8Line(scanLength);
        }
        Buffer data = new Buffer();
        copyTo(data, 0L, Math.min(32L, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), limit) + " content=" + data.readByteString().hex() + Typography.ellipsis);
    }

    String readUtf8Line(long newline) throws EOFException {
        if (newline > 0 && getByte(newline - 1) == 13) {
            String result = readUtf8(newline - 1);
            skip(2L);
            return result;
        }
        String result2 = readUtf8(newline);
        skip(1L);
        return result2;
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        int codePoint;
        int byteCount;
        int min;
        if (this.size == 0) {
            throw new EOFException();
        }
        int b0 = getByte(0L);
        if ((b0 & 128) == 0) {
            codePoint = b0 & 127;
            byteCount = 1;
            min = 0;
        } else {
            int codePoint2 = b0 & 224;
            if (codePoint2 == 192) {
                codePoint = b0 & 31;
                byteCount = 2;
                min = 128;
            } else {
                int codePoint3 = b0 & 240;
                if (codePoint3 == 224) {
                    codePoint = b0 & 15;
                    byteCount = 3;
                    min = 2048;
                } else {
                    int codePoint4 = b0 & 248;
                    if (codePoint4 == 240) {
                        codePoint = b0 & 7;
                        byteCount = 4;
                        min = 65536;
                    } else {
                        skip(1L);
                        return REPLACEMENT_CHARACTER;
                    }
                }
            }
        }
        if (this.size < byteCount) {
            throw new EOFException("size < " + byteCount + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(b0) + ")");
        }
        for (int i = 1; i < byteCount; i++) {
            byte b = getByte(i);
            if ((b & 192) == 128) {
                codePoint = (codePoint << 6) | (b & 63);
            } else {
                skip(i);
                return REPLACEMENT_CHARACTER;
            }
        }
        skip(byteCount);
        return codePoint > 1114111 ? REPLACEMENT_CHARACTER : ((codePoint < 55296 || codePoint > 57343) && codePoint >= min) ? codePoint : REPLACEMENT_CHARACTER;
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long byteCount) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, byteCount);
        if (byteCount > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
        }
        byte[] result = new byte[(int) byteCount];
        readFully(result);
        return result;
    }

    @Override // okio.BufferedSource
    public int read(byte[] sink) {
        return read(sink, 0, sink.length);
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] sink) throws EOFException {
        int offset = 0;
        while (offset < sink.length) {
            int read = read(sink, offset, sink.length - offset);
            if (read == -1) {
                throw new EOFException();
            }
            offset += read;
        }
    }

    @Override // okio.BufferedSource
    public int read(byte[] sink, int offset, int byteCount) {
        Util.checkOffsetAndCount(sink.length, offset, byteCount);
        Segment s = this.head;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(byteCount, s.limit - s.pos);
        System.arraycopy(s.data, s.pos, sink, offset, toCopy);
        s.pos += toCopy;
        this.size -= (long) toCopy;
        if (s.pos == s.limit) {
            this.head = s.pop();
            SegmentPool.recycle(s);
        }
        return toCopy;
    }

    public void clear() {
        try {
            skip(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.BufferedSource
    public void skip(long byteCount) throws EOFException {
        while (byteCount > 0) {
            if (this.head == null) {
                throw new EOFException();
            }
            int toSkip = (int) Math.min(byteCount, this.head.limit - this.head.pos);
            this.size -= (long) toSkip;
            long byteCount2 = byteCount - ((long) toSkip);
            this.head.pos += toSkip;
            if (this.head.pos == this.head.limit) {
                Segment toRecycle = this.head;
                this.head = toRecycle.pop();
                SegmentPool.recycle(toRecycle);
            }
            byteCount = byteCount2;
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String string) {
        return writeUtf8(string, 0, string.length());
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String string, int beginIndex, int endIndex) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + beginIndex);
        }
        if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        }
        if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        }
        int i = beginIndex;
        while (i < endIndex) {
            int c = string.charAt(i);
            if (c < 128) {
                Segment tail = writableSegment(1);
                byte[] data = tail.data;
                int segmentOffset = tail.limit - i;
                int runLimit = Math.min(endIndex, 8192 - segmentOffset);
                int i2 = i + 1;
                data[i + segmentOffset] = (byte) c;
                while (i2 < runLimit) {
                    c = string.charAt(i2);
                    if (c >= 128) {
                        break;
                    }
                    data[i2 + segmentOffset] = (byte) c;
                    i2++;
                }
                int runSize = (i2 + segmentOffset) - tail.limit;
                tail.limit += runSize;
                this.size += (long) runSize;
                i = i2;
            } else if (c < 2048) {
                writeByte((c >> 6) | 192);
                writeByte(128 | (c & 63));
                i++;
            } else if (c >= 55296 && c <= 57343) {
                int low = i + 1 < endIndex ? string.charAt(i + 1) : 0;
                if (c > 56319 || low < 56320 || low > 57343) {
                    writeByte(63);
                    i++;
                } else {
                    int codePoint = 65536 + ((((-55297) & c) << 10) | ((-56321) & low));
                    writeByte((codePoint >> 18) | 240);
                    writeByte(((codePoint >> 12) & 63) | 128);
                    writeByte((63 & (codePoint >> 6)) | 128);
                    writeByte(128 | (codePoint & 63));
                    i += 2;
                }
            } else {
                writeByte((c >> 12) | 224);
                writeByte(((c >> 6) & 63) | 128);
                writeByte(128 | (c & 63));
                i++;
            }
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8CodePoint(int codePoint) {
        if (codePoint < 128) {
            writeByte(codePoint);
        } else if (codePoint < 2048) {
            writeByte((codePoint >> 6) | 192);
            writeByte(128 | (codePoint & 63));
        } else if (codePoint < 65536) {
            if (codePoint >= 55296 && codePoint <= 57343) {
                writeByte(63);
            } else {
                writeByte((codePoint >> 12) | 224);
                writeByte(((codePoint >> 6) & 63) | 128);
                writeByte(128 | (codePoint & 63));
            }
        } else if (codePoint <= 1114111) {
            writeByte((codePoint >> 18) | 240);
            writeByte(((codePoint >> 12) & 63) | 128);
            writeByte(((codePoint >> 6) & 63) | 128);
            writeByte(128 | (codePoint & 63));
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(codePoint));
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String string, Charset charset) {
        return writeString(string, 0, string.length(), charset);
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String string, int beginIndex, int endIndex, Charset charset) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (beginIndex < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + beginIndex);
        }
        if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        }
        if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        }
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (charset.equals(Util.UTF_8)) {
            return writeUtf8(string, beginIndex, endIndex);
        }
        byte[] data = string.substring(beginIndex, endIndex).getBytes(charset);
        return write(data, 0, data.length);
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        return write(source, 0, source.length);
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] source, int offset, int byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        Util.checkOffsetAndCount(source.length, offset, byteCount);
        int limit = offset + byteCount;
        while (offset < limit) {
            Segment tail = writableSegment(1);
            int toCopy = Math.min(limit - offset, 8192 - tail.limit);
            System.arraycopy(source, offset, tail.data, tail.limit, toCopy);
            offset += toCopy;
            tail.limit += toCopy;
        }
        this.size += (long) byteCount;
        return this;
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long totalBytesRead = 0;
        while (true) {
            long readCount = source.read(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (readCount != -1) {
                totalBytesRead += readCount;
            } else {
                return totalBytesRead;
            }
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long byteCount) throws IOException {
        while (byteCount > 0) {
            long read = source.read(this, byteCount);
            if (read == -1) {
                throw new EOFException();
            }
            byteCount -= read;
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int b) {
        Segment tail = writableSegment(1);
        byte[] bArr = tail.data;
        int i = tail.limit;
        tail.limit = i + 1;
        bArr[i] = (byte) b;
        this.size++;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int s) {
        Segment tail = writableSegment(2);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((s >>> 8) & 255);
        data[limit2] = (byte) (s & 255);
        tail.limit = limit2 + 1;
        this.size += 2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShortLe(int s) {
        return writeShort((int) Util.reverseBytesShort((short) s));
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int i) {
        Segment tail = writableSegment(4);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((i >>> 24) & 255);
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((i >>> 16) & 255);
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((i >>> 8) & 255);
        data[limit4] = (byte) (i & 255);
        tail.limit = limit4 + 1;
        this.size += 4;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeIntLe(int i) {
        return writeInt(Util.reverseBytesInt(i));
    }

    @Override // okio.BufferedSink
    public Buffer writeLong(long v) {
        Segment tail = writableSegment(8);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((v >>> 56) & 255);
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((v >>> 48) & 255);
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((v >>> 40) & 255);
        int limit5 = limit4 + 1;
        data[limit4] = (byte) ((v >>> 32) & 255);
        int limit6 = limit5 + 1;
        data[limit5] = (byte) ((v >>> 24) & 255);
        int limit7 = limit6 + 1;
        data[limit6] = (byte) ((v >>> 16) & 255);
        int limit8 = limit7 + 1;
        data[limit7] = (byte) ((v >>> 8) & 255);
        data[limit8] = (byte) (v & 255);
        tail.limit = limit8 + 1;
        this.size += 8;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeLongLe(long v) {
        return writeLong(Util.reverseBytesLong(v));
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long v) {
        int width;
        if (v == 0) {
            return writeByte(48);
        }
        boolean negative = false;
        if (v < 0) {
            v = -v;
            if (v < 0) {
                return writeUtf8("-9223372036854775808");
            }
            negative = true;
        }
        if (v >= 100000000) {
            width = v < 1000000000000L ? v < 10000000000L ? v < 1000000000 ? 9 : 10 : v < 100000000000L ? 11 : 12 : v < 1000000000000000L ? v < 10000000000000L ? 13 : v < 100000000000000L ? 14 : 15 : v < 100000000000000000L ? v < 10000000000000000L ? 16 : 17 : v < 1000000000000000000L ? 18 : 19;
        } else if (v >= 10000) {
            width = v < 1000000 ? v < 100000 ? 5 : 6 : v < 10000000 ? 7 : 8;
        } else if (v >= 100) {
            width = v < 1000 ? 3 : 4;
        } else if (v >= 10) {
            width = 2;
        } else {
            width = 1;
        }
        if (negative) {
            width++;
        }
        Segment tail = writableSegment(width);
        byte[] data = tail.data;
        int pos = tail.limit + width;
        while (v != 0) {
            int digit = (int) (v % 10);
            pos--;
            data[pos] = DIGITS[digit];
            v /= 10;
        }
        if (negative) {
            data[pos - 1] = 45;
        }
        tail.limit += width;
        this.size += (long) width;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long v) {
        if (v == 0) {
            return writeByte(48);
        }
        int width = (Long.numberOfTrailingZeros(Long.highestOneBit(v)) / 4) + 1;
        Segment tail = writableSegment(width);
        byte[] data = tail.data;
        int start = tail.limit;
        for (int pos = (tail.limit + width) - 1; pos >= start; pos--) {
            data[pos] = DIGITS[(int) (v & 15)];
            v >>>= 4;
        }
        tail.limit += width;
        this.size += (long) width;
        return this;
    }

    Segment writableSegment(int minimumCapacity) {
        if (minimumCapacity < 1 || minimumCapacity > 8192) {
            throw new IllegalArgumentException();
        }
        if (this.head == null) {
            this.head = SegmentPool.take();
            Segment segment = this.head;
            Segment segment2 = this.head;
            Segment segment3 = this.head;
            segment2.prev = segment3;
            segment.next = segment3;
            return segment3;
        }
        Segment tail = this.head.prev;
        if (tail.limit + minimumCapacity > 8192 || !tail.owner) {
            return tail.push(SegmentPool.take());
        }
        return tail;
    }

    @Override // okio.Sink
    public void write(Buffer source, long byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (source == this) {
            throw new IllegalArgumentException("source == this");
        }
        Util.checkOffsetAndCount(source.size, 0L, byteCount);
        while (byteCount > 0) {
            if (byteCount < source.head.limit - source.head.pos) {
                Segment tail = this.head != null ? this.head.prev : null;
                if (tail != null && tail.owner) {
                    if ((byteCount + ((long) tail.limit)) - ((long) (tail.shared ? 0 : tail.pos)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        source.head.writeTo(tail, (int) byteCount);
                        source.size -= byteCount;
                        this.size += byteCount;
                        return;
                    }
                }
                source.head = source.head.split((int) byteCount);
            }
            Segment tail2 = source.head;
            long movedByteCount = tail2.limit - tail2.pos;
            source.head = tail2.pop();
            if (this.head == null) {
                this.head = tail2;
                Segment segment = this.head;
                Segment segment2 = this.head;
                Segment segment3 = this.head;
                segment2.prev = segment3;
                segment.next = segment3;
            } else {
                this.head.prev.push(tail2).compact();
            }
            source.size -= movedByteCount;
            this.size += movedByteCount;
            byteCount -= movedByteCount;
        }
    }

    @Override // okio.Source
    public long read(Buffer sink, long byteCount) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        if (this.size == 0) {
            return -1L;
        }
        if (byteCount > this.size) {
            byteCount = this.size;
        }
        sink.write(this, byteCount);
        return byteCount;
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0L, LongCompanionObject.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long fromIndex) {
        return indexOf(b, fromIndex, LongCompanionObject.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long fromIndex, long toIndex) {
        Segment s;
        long offset = 0;
        if (fromIndex < 0 || toIndex < fromIndex) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.size), Long.valueOf(fromIndex), Long.valueOf(toIndex)));
        }
        long toIndex2 = toIndex > this.size ? this.size : toIndex;
        if (fromIndex == toIndex2 || (s = this.head) == null) {
            return -1L;
        }
        if (this.size - fromIndex >= fromIndex) {
            while (true) {
                long nextOffset = offset + ((long) (s.limit - s.pos));
                if (nextOffset >= fromIndex) {
                    break;
                }
                s = s.next;
                offset = nextOffset;
            }
        } else {
            offset = this.size;
            while (offset > fromIndex) {
                s = s.prev;
                offset -= (long) (s.limit - s.pos);
            }
        }
        long fromIndex2 = fromIndex;
        while (offset < toIndex2) {
            byte[] data = s.data;
            int limit = (int) Math.min(s.limit, (((long) s.pos) + toIndex2) - offset);
            for (int pos = (int) ((((long) s.pos) + fromIndex2) - offset); pos < limit; pos++) {
                if (data[pos] == b) {
                    return ((long) (pos - s.pos)) + offset;
                }
            }
            long offset2 = offset + ((long) (s.limit - s.pos));
            fromIndex2 = offset2;
            s = s.next;
            offset = offset2;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString bytes) throws IOException {
        return indexOf(bytes, 0L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00bf, code lost:
    
        r7 = r5;
        r2 = r12 + ((long) (r7.limit - r7.pos));
        r16 = r2;
        r5 = r7.next;
        r12 = r2;
     */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long indexOf(okio.ByteString r24, long r25) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.indexOf(okio.ByteString, long):long");
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString targetBytes) {
        return indexOfElement(targetBytes, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString targetBytes, long fromIndex) {
        Buffer buffer = this;
        long offset = 0;
        if (fromIndex < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment s = buffer.head;
        if (s == null) {
            return -1L;
        }
        if (buffer.size - fromIndex >= fromIndex) {
            while (true) {
                long nextOffset = offset + ((long) (s.limit - s.pos));
                if (nextOffset >= fromIndex) {
                    break;
                }
                s = s.next;
                offset = nextOffset;
            }
        } else {
            offset = buffer.size;
            while (offset > fromIndex) {
                s = s.prev;
                offset -= (long) (s.limit - s.pos);
            }
        }
        int i = 0;
        if (targetBytes.size() == 2) {
            int b0 = targetBytes.getByte(0);
            int b1 = targetBytes.getByte(1);
            long fromIndex2 = fromIndex;
            while (offset < buffer.size) {
                byte[] data = s.data;
                int limit = s.limit;
                for (int pos = (int) ((((long) s.pos) + fromIndex2) - offset); pos < limit; pos++) {
                    int b = data[pos];
                    if (b == b0 || b == b1) {
                        return ((long) (pos - s.pos)) + offset;
                    }
                }
                int pos2 = s.limit;
                long offset2 = offset + ((long) (pos2 - s.pos));
                fromIndex2 = offset2;
                s = s.next;
                offset = offset2;
            }
            return -1L;
        }
        byte[] targetByteArray = targetBytes.internalArray();
        long fromIndex3 = fromIndex;
        while (offset < buffer.size) {
            byte[] data2 = s.data;
            int pos3 = (int) ((((long) s.pos) + fromIndex3) - offset);
            int limit2 = s.limit;
            while (pos3 < limit2) {
                byte b2 = data2[pos3];
                int length = targetByteArray.length;
                while (i < length) {
                    byte t = targetByteArray[i];
                    if (b2 == t) {
                        return ((long) (pos3 - s.pos)) + offset;
                    }
                    i++;
                }
                pos3++;
                i = 0;
            }
            long offset3 = offset + ((long) (s.limit - s.pos));
            fromIndex3 = offset3;
            s = s.next;
            offset = offset3;
            buffer = this;
            i = 0;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, ByteString bytes) {
        return rangeEquals(offset, bytes, 0, bytes.size());
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount) {
        if (offset < 0 || bytesOffset < 0 || byteCount < 0 || this.size - offset < byteCount || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        for (int i = 0; i < byteCount; i++) {
            if (getByte(offset + ((long) i)) != bytes.getByte(bytesOffset + i)) {
                return false;
            }
        }
        return true;
    }

    private boolean rangeEquals(Segment segment, int segmentPos, ByteString bytes, int bytesOffset, int bytesLimit) {
        int segmentLimit = segment.limit;
        byte[] data = segment.data;
        byte[] data2 = data;
        Segment segment2 = segment;
        for (int i = bytesOffset; i < bytesLimit; i++) {
            if (segmentPos == segmentLimit) {
                segment2 = segment2.next;
                data2 = segment2.data;
                segmentPos = segment2.pos;
                segmentLimit = segment2.limit;
            }
            if (data2[segmentPos] != bytes.getByte(i)) {
                return false;
            }
            segmentPos++;
        }
        return true;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.Source
    public Timeout timeout() {
        return Timeout.NONE;
    }

    List<Integer> segmentSizes() {
        if (this.head == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        result.add(Integer.valueOf(this.head.limit - this.head.pos));
        Segment s = this.head;
        while (true) {
            s = s.next;
            if (s != this.head) {
                result.add(Integer.valueOf(s.limit - s.pos));
            } else {
                return result;
            }
        }
    }

    public ByteString md5() {
        return digest("MD5");
    }

    public ByteString sha1() {
        return digest("SHA-1");
    }

    public ByteString sha256() {
        return digest("SHA-256");
    }

    public ByteString sha512() {
        return digest(AndroidUtilsLight.DIGEST_ALGORITHM_SHA512);
    }

    private ByteString digest(String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            if (this.head != null) {
                messageDigest.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
                for (Segment s = this.head.next; s != this.head; s = s.next) {
                    messageDigest.update(s.data, s.pos, s.limit - s.pos);
                }
            }
            return ByteString.of(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public ByteString hmacSha1(ByteString key) {
        return hmac("HmacSHA1", key);
    }

    public ByteString hmacSha256(ByteString key) {
        return hmac("HmacSHA256", key);
    }

    public ByteString hmacSha512(ByteString key) {
        return hmac("HmacSHA512", key);
    }

    private ByteString hmac(String algorithm, ByteString key) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            if (this.head != null) {
                mac.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
                for (Segment s = this.head.next; s != this.head; s = s.next) {
                    mac.update(s.data, s.pos, s.limit - s.pos);
                }
            }
            return ByteString.of(mac.doFinal());
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError();
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Buffer)) {
            return false;
        }
        Buffer that = (Buffer) o;
        if (this.size != that.size) {
            return false;
        }
        long pos = 0;
        if (this.size == 0) {
            return true;
        }
        Segment sa = this.head;
        Segment sb = that.head;
        int posA = sa.pos;
        int posB = sb.pos;
        while (pos < this.size) {
            long count = Math.min(sa.limit - posA, sb.limit - posB);
            int posB2 = posB;
            int posB3 = posA;
            int posA2 = 0;
            while (posA2 < count) {
                int posA3 = posB3 + 1;
                int posA4 = sa.data[posB3];
                int posB4 = posB2 + 1;
                if (posA4 != sb.data[posB2]) {
                    return false;
                }
                posA2++;
                posB3 = posA3;
                posB2 = posB4;
            }
            int i = sa.limit;
            if (posB3 == i) {
                sa = sa.next;
                posA = sa.pos;
            } else {
                posA = posB3;
            }
            int posA5 = sb.limit;
            if (posB2 == posA5) {
                sb = sb.next;
                posB = sb.pos;
            } else {
                posB = posB2;
            }
            pos += count;
        }
        return true;
    }

    public int hashCode() {
        Segment s = this.head;
        if (s == null) {
            return 0;
        }
        int result = 1;
        do {
            int limit = s.limit;
            for (int pos = s.pos; pos < limit; pos++) {
                result = (31 * result) + s.data[pos];
            }
            s = s.next;
        } while (s != this.head);
        return result;
    }

    public String toString() {
        return snapshot().toString();
    }

    public Buffer clone() {
        Buffer result = new Buffer();
        if (this.size == 0) {
            return result;
        }
        result.head = new Segment(this.head);
        Segment segment = result.head;
        Segment segment2 = result.head;
        Segment segment3 = result.head;
        segment2.prev = segment3;
        segment.next = segment3;
        Segment s = this.head;
        while (true) {
            s = s.next;
            if (s != this.head) {
                result.head.prev.push(new Segment(s));
            } else {
                result.size = this.size;
                return result;
            }
        }
    }

    public ByteString snapshot() {
        if (this.size > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
        }
        return snapshot((int) this.size);
    }

    public ByteString snapshot(int byteCount) {
        return byteCount == 0 ? ByteString.EMPTY : new SegmentedByteString(this, byteCount);
    }
}
