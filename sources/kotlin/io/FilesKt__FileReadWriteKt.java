package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: FileReadWrite.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\u0087\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001a<\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\u0086\b¢\u0006\u0002\u0010+\u001a\u0012\u0010,\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010.\u001a\u00020/*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b¨\u00060"}, d2 = {"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/io/FilesKt")
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    @InlineOnly
    private static final InputStreamReader reader(@NotNull File $receiver, Charset charset) {
        return new InputStreamReader(new FileInputStream($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ InputStreamReader reader$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(new FileInputStream($receiver), charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(@NotNull File $receiver, Charset charset, int bufferSize) {
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedReader bufferedReader$default(File $receiver, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(@NotNull File $receiver, Charset charset) {
        return new OutputStreamWriter(new FileOutputStream($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ OutputStreamWriter writer$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(new FileOutputStream($receiver), charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(@NotNull File $receiver, Charset charset, int bufferSize) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedWriter bufferedWriter$default(File $receiver, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    private static final PrintWriter printWriter(@NotNull File $receiver, Charset charset) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ PrintWriter printWriter$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007e  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final byte[] readBytes(@org.jetbrains.annotations.NotNull java.io.File r12) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r12)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            r2 = r0
            java.io.FileInputStream r2 = (java.io.FileInputStream) r2     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r3 = r1
            r4 = 0
            long r5 = r12.length()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r7 = r1
            r8 = 2147483647(0x7fffffff, float:NaN)
            long r8 = (long) r8     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 <= 0) goto L47
            java.lang.OutOfMemoryError r8 = new java.lang.OutOfMemoryError     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r9.<init>()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.String r10 = "File "
            r9.append(r10)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r9.append(r12)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.String r10 = " is too big ("
            r9.append(r10)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r9.append(r5)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.String r10 = " bytes) to fit in memory."
            r9.append(r10)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.Throwable r8 = (java.lang.Throwable) r8     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            throw r8     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
        L47:
            int r5 = (int) r5     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            byte[] r6 = new byte[r5]     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
        L4c:
            if (r5 <= 0) goto L58
            int r7 = r2.read(r6, r4, r5)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            if (r7 >= 0) goto L55
            goto L58
        L55:
            int r5 = r5 - r7
            int r4 = r4 + r7
            goto L4c
        L58:
            if (r5 != 0) goto L5b
            goto L65
        L5b:
            byte[] r7 = java.util.Arrays.copyOf(r6, r4)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.String r8 = "java.util.Arrays.copyOf(this, newSize)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r6 = r7
        L65:
            r0.close()
            byte[] r6 = (byte[]) r6
            return r6
        L6b:
            r2 = move-exception
            goto L7c
        L6d:
            r1 = move-exception
            r2 = 1
            r0.close()     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L78
            goto L79
        L73:
            r1 = move-exception
            r11 = r2
            r2 = r1
            r1 = r11
            goto L7c
        L78:
            r3 = move-exception
        L79:
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch: java.lang.Throwable -> L73
            throw r1     // Catch: java.lang.Throwable -> L73
        L7c:
            if (r1 != 0) goto L81
            r0.close()
        L81:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.readBytes(java.io.File):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void writeBytes(@org.jetbrains.annotations.NotNull java.io.File r5, @org.jetbrains.annotations.NotNull byte[] r6) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "array"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r5)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            r2 = r0
            java.io.FileOutputStream r2 = (java.io.FileOutputStream) r2     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L24
            r3 = r1
            r2.write(r6)     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L24
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L24
            r0.close()
            return
        L1f:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
            goto L30
        L24:
            r1 = move-exception
            r2 = 1
            r0.close()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            goto L2d
        L2a:
            r1 = move-exception
            goto L30
        L2c:
            r3 = move-exception
        L2d:
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch: java.lang.Throwable -> L2a
            throw r1     // Catch: java.lang.Throwable -> L2a
        L30:
            if (r2 != 0) goto L35
            r0.close()
        L35:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.writeBytes(java.io.File, byte[]):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void appendBytes(@org.jetbrains.annotations.NotNull java.io.File r6, @org.jetbrains.annotations.NotNull byte[] r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.lang.String r0 = "array"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r1 = 1
            r0.<init>(r6, r1)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = 0
            r3 = r0
            java.io.FileOutputStream r3 = (java.io.FileOutputStream) r3     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            r4 = r2
            r3.write(r7)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            r0.close()
            return
        L20:
            r1 = move-exception
            goto L30
        L22:
            r2 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2c
            goto L2d
        L27:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L30
        L2c:
            r3 = move-exception
        L2d:
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch: java.lang.Throwable -> L27
            throw r2     // Catch: java.lang.Throwable -> L27
        L30:
            if (r2 != 0) goto L35
            r0.close()
        L35:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.appendBytes(java.io.File, byte[]):void");
    }

    @NotNull
    public static final String readText(@NotNull File receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return new String(FilesKt.readBytes(receiver), charset);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String readText$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readText(file, charset);
    }

    public static final void writeText(@NotNull File receiver, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (text == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = text.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        FilesKt.writeBytes(receiver, bytes);
    }

    public static /* bridge */ /* synthetic */ void writeText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.writeText(file, str, charset);
    }

    public static final void appendText(@NotNull File receiver, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (text == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = text.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        FilesKt.appendBytes(receiver, bytes);
    }

    public static /* bridge */ /* synthetic */ void appendText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.appendText(file, str, charset);
    }

    public static final void forEachBlock(@NotNull File receiver, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        FilesKt.forEachBlock(receiver, 4096, action);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [byte[], java.lang.Object] */
    public static final void forEachBlock(@NotNull File receiver, int blockSize, @NotNull Function2<? super byte[], ? super Integer, Unit> action) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        ?? r0 = new byte[RangesKt.coerceAtLeast(blockSize, 512)];
        FileInputStream fileInputStream = new FileInputStream(receiver);
        while (true) {
            try {
                int size = fileInputStream.read(r0);
                if (size > 0) {
                    action.invoke(r0, Integer.valueOf(size));
                } else {
                    return;
                }
            } finally {
                fileInputStream.close();
            }
        }
    }

    public static /* bridge */ /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.forEachLine(file, charset, function1);
    }

    public static final void forEachLine(@NotNull File receiver, @NotNull Charset charset, @NotNull Function1<? super String, Unit> action) throws Throwable {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        Intrinsics.checkParameterIsNotNull(action, "action");
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream(receiver), charset)), action);
    }

    @InlineOnly
    private static final FileInputStream inputStream(@NotNull File $receiver) {
        return new FileInputStream($receiver);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(@NotNull File $receiver) {
        return new FileOutputStream($receiver);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List readLines$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readLines(file, charset);
    }

    @NotNull
    public static final List<String> readLines(@NotNull File receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        final ArrayList result = new ArrayList();
        FilesKt.forEachLine(receiver, charset, new Function1<String, Unit>() { // from class: kotlin.io.FilesKt__FileReadWriteKt.readLines.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                result.add(it);
            }
        });
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* bridge */ /* synthetic */ java.lang.Object useLines$default(java.io.File r5, java.nio.charset.Charset r6, kotlin.jvm.functions.Function1 r7, int r8, java.lang.Object r9) throws java.lang.Throwable {
        /*
            r9 = r8 & 1
            if (r9 == 0) goto L6
            java.nio.charset.Charset r6 = kotlin.text.Charsets.UTF_8
        L6:
            java.lang.String r9 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r9)
            java.lang.String r9 = "charset"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r9)
            java.lang.String r9 = "block"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r9)
            r9 = 8192(0x2000, float:1.148E-41)
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r5)
            java.io.InputStream r0 = (java.io.InputStream) r0
            java.io.InputStreamReader r1 = new java.io.InputStreamReader
            r1.<init>(r0, r6)
            java.io.Reader r1 = (java.io.Reader) r1
            boolean r0 = r1 instanceof java.io.BufferedReader
            if (r0 == 0) goto L2d
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1
            r0 = r1
            goto L32
        L2d:
            java.io.BufferedReader r0 = new java.io.BufferedReader
            r0.<init>(r1, r9)
        L32:
            java.io.Closeable r0 = (java.io.Closeable) r0
            r9 = 0
            r1 = 1
            r2 = r0
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            r3 = r9
            kotlin.sequences.Sequence r4 = kotlin.io.TextStreamsKt.lineSequence(r2)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            java.lang.Object r2 = r7.invoke(r4)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r0.close()
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r2
        L4c:
            r2 = move-exception
            goto L5a
        L4e:
            r9 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L56
            goto L57
        L53:
            r2 = move-exception
            r9 = r1
            goto L5a
        L56:
            r2 = move-exception
        L57:
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch: java.lang.Throwable -> L53
            throw r9     // Catch: java.lang.Throwable -> L53
        L5a:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            if (r9 != 0) goto L62
            r0.close()
        L62:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.useLines$default(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> T useLines(@org.jetbrains.annotations.NotNull java.io.File r7, @org.jetbrains.annotations.NotNull java.nio.charset.Charset r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<java.lang.String>, ? extends T> r9) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.String r1 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r1)
            java.lang.String r1 = "charset"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r1)
            java.lang.String r1 = "block"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r1)
            r1 = 8192(0x2000, float:1.148E-41)
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r7)
            java.io.InputStream r2 = (java.io.InputStream) r2
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            r3.<init>(r2, r8)
            java.io.Reader r3 = (java.io.Reader) r3
            boolean r2 = r3 instanceof java.io.BufferedReader
            if (r2 == 0) goto L28
            java.io.BufferedReader r3 = (java.io.BufferedReader) r3
            r2 = r3
            goto L2d
        L28:
            java.io.BufferedReader r2 = new java.io.BufferedReader
            r2.<init>(r3, r1)
        L2d:
            java.io.Closeable r2 = (java.io.Closeable) r2
            r1 = 0
            r3 = 1
            r4 = r2
            java.io.BufferedReader r4 = (java.io.BufferedReader) r4     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r5 = r1
            kotlin.sequences.Sequence r6 = kotlin.io.TextStreamsKt.lineSequence(r4)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.lang.Object r4 = r9.invoke(r6)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r2.close()
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r4
        L47:
            r4 = move-exception
            goto L55
        L49:
            r1 = move-exception
            r2.close()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L51
            goto L52
        L4e:
            r4 = move-exception
            r1 = r3
            goto L55
        L51:
            r4 = move-exception
        L52:
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch: java.lang.Throwable -> L4e
            throw r1     // Catch: java.lang.Throwable -> L4e
        L55:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            if (r1 != 0) goto L5d
            r2.close()
        L5d:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.useLines(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1):java.lang.Object");
    }
}
