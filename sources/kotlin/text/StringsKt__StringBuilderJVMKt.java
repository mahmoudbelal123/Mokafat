package kotlin.text;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: StringBuilderJVM.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001f\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005H\u0087\b\u001a\u0012\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\tH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\nH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\rH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000eH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0010H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0011H\u0087\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0012H\u0087\b\u001a%\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u000e\u0010\u0003\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\u0087\b¨\u0006\u0013"}, d2 = {"appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", FirebaseAnalytics.Param.VALUE, "", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "", "", "", "", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/text/StringsKt")
class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    @NotNull
    public static final Appendable appendln(@NotNull Appendable receiver) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Appendable appendableAppend = receiver.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(appendableAppend, "append(SystemProperties.LINE_SEPARATOR)");
        return appendableAppend;
    }

    @InlineOnly
    private static final Appendable appendln(@NotNull Appendable $receiver, CharSequence value) {
        return StringsKt.appendln($receiver.append(value));
    }

    @InlineOnly
    private static final Appendable appendln(@NotNull Appendable $receiver, char value) {
        return StringsKt.appendln($receiver.append(value));
    }

    @NotNull
    public static final StringBuilder appendln(@NotNull StringBuilder receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(receiver, "append(SystemProperties.LINE_SEPARATOR)");
        return receiver;
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, StringBuffer value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, CharSequence value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, String value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, Object value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, StringBuilder value) {
        $receiver.append((CharSequence) value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, char[] value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, char value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, boolean value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, int value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, short value) {
        $receiver.append((int) value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, byte value) {
        $receiver.append((int) value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, long value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, float value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder $receiver, double value) {
        $receiver.append(value);
        return StringsKt.appendln($receiver);
    }
}
