package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ReadWrite.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\u0087\b\u001a2\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\u0086\b¢\u0006\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 5})
@JvmName(name = "TextStreamsKt")
public final class TextStreamsKt {
    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedReader buffered$default(Reader $receiver, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            bufferSize = 8192;
        }
        return $receiver instanceof BufferedReader ? (BufferedReader) $receiver : new BufferedReader($receiver, bufferSize);
    }

    @InlineOnly
    private static final BufferedReader buffered(@NotNull Reader $receiver, int bufferSize) {
        return $receiver instanceof BufferedReader ? (BufferedReader) $receiver : new BufferedReader($receiver, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedWriter buffered$default(Writer $receiver, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            bufferSize = 8192;
        }
        return $receiver instanceof BufferedWriter ? (BufferedWriter) $receiver : new BufferedWriter($receiver, bufferSize);
    }

    @InlineOnly
    private static final BufferedWriter buffered(@NotNull Writer $receiver, int bufferSize) {
        return $receiver instanceof BufferedWriter ? (BufferedWriter) $receiver : new BufferedWriter($receiver, bufferSize);
    }

    public static final void forEachLine(@NotNull Reader receiver, @NotNull Function1<? super String, Unit> action) throws Throwable {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        BufferedReader bufferedReader = receiver instanceof BufferedReader ? (BufferedReader) receiver : new BufferedReader(receiver, 8192);
        boolean z = false;
        try {
            BufferedReader it$iv = bufferedReader;
            for (Object element$iv : lineSequence(it$iv)) {
                action.invoke(element$iv);
            }
            Unit unit = Unit.INSTANCE;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Exception e) {
            if (bufferedReader != null) {
                try {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th) {
                        th = th;
                        z = true;
                        if (!z && bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                }
            }
            throw e;
        } catch (Throwable th2) {
            th = th2;
            if (!z) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    @NotNull
    public static final List<String> readLines(@NotNull Reader receiver) throws Throwable {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        final ArrayList result = new ArrayList();
        forEachLine(receiver, new Function1<String, Unit>() { // from class: kotlin.io.TextStreamsKt.readLines.1
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

    public static final <T> T useLines(@NotNull Reader receiver, @NotNull Function1<? super Sequence<String>, ? extends T> block) throws Throwable {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(block, "block");
        BufferedReader bufferedReader = receiver instanceof BufferedReader ? (BufferedReader) receiver : new BufferedReader(receiver, 8192);
        boolean z = false;
        try {
            BufferedReader it = bufferedReader;
            T tInvoke = block.invoke(lineSequence(it));
            InlineMarker.finallyStart(1);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Exception e) {
            if (bufferedReader != null) {
                try {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th) {
                        th = th;
                        z = true;
                        InlineMarker.finallyStart(1);
                        if (!z && bufferedReader != null) {
                            bufferedReader.close();
                        }
                        InlineMarker.finallyEnd(1);
                        throw th;
                    }
                } catch (Exception e2) {
                }
            }
            throw e;
        } catch (Throwable th2) {
            th = th2;
            InlineMarker.finallyStart(1);
            if (!z) {
                bufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    @InlineOnly
    private static final StringReader reader(@NotNull String $receiver) {
        return new StringReader($receiver);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull BufferedReader receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return SequencesKt.constrainOnce(new LinesSequence(receiver));
    }

    @NotNull
    public static final String readText(@NotNull Reader receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        StringWriter buffer = new StringWriter();
        copyTo$default(receiver, buffer, 0, 2, null);
        String string = buffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "buffer.toString()");
        return string;
    }

    public static /* bridge */ /* synthetic */ long copyTo$default(Reader reader, Writer writer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(reader, writer, i);
    }

    public static final long copyTo(@NotNull Reader receiver, @NotNull Writer out, int bufferSize) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(out, "out");
        long charsCopied = 0;
        char[] buffer = new char[bufferSize];
        int chars = receiver.read(buffer);
        while (chars >= 0) {
            out.write(buffer, 0, chars);
            long charsCopied2 = charsCopied + ((long) chars);
            chars = receiver.read(buffer);
            charsCopied = charsCopied2;
        }
        return charsCopied;
    }

    @InlineOnly
    private static final String readText(@NotNull URL $receiver, Charset charset) {
        return new String(readBytes($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ String readText$default(URL $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new String(readBytes($receiver), charset);
    }

    @NotNull
    public static final byte[] readBytes(@NotNull URL receiver) throws Throwable {
        Throwable th;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        InputStream inputStreamOpenStream = receiver.openStream();
        boolean z = true;
        try {
            InputStream it = inputStreamOpenStream;
            byte[] bytes$default = ByteStreamsKt.readBytes$default(it, 0, 1, null);
            if (inputStreamOpenStream != null) {
                inputStreamOpenStream.close();
            }
            return bytes$default;
        } catch (Exception e) {
            if (inputStreamOpenStream != null) {
                try {
                    try {
                        inputStreamOpenStream.close();
                    } catch (Exception e2) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (!z && inputStreamOpenStream != null) {
                        inputStreamOpenStream.close();
                    }
                    throw th;
                }
            }
            throw e;
        } catch (Throwable th3) {
            th = th3;
            z = false;
            if (!z) {
                inputStreamOpenStream.close();
            }
            throw th;
        }
    }
}
