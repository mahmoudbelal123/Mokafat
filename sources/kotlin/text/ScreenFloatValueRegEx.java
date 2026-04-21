package kotlin.text;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: StringNumberConversions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", FirebaseAnalytics.Param.VALUE, "Lkotlin/text/Regex;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
final class ScreenFloatValueRegEx {
    public static final ScreenFloatValueRegEx INSTANCE = null;

    @JvmField
    @NotNull
    public static final Regex value = null;

    static {
        new ScreenFloatValueRegEx();
    }

    private ScreenFloatValueRegEx() {
        INSTANCE = this;
        String Exp = "[eE][+-]?(\\p{Digit}+)";
        StringBuilder sb = new StringBuilder();
        sb.append("(0[xX](\\p{XDigit}+)(\\.)?)|");
        sb.append("(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+))");
        String HexString = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)(" + Exp + ")?)|");
        sb2.append("(\\.((\\p{Digit}+))(" + Exp + ")?)|");
        sb2.append("((" + HexString + ")[pP][+-]?(\\p{Digit}+))");
        String Number = sb2.toString();
        String fpRegex = "[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + Number + ")[fFdD]?))[\\x00-\\x20]*";
        value = new Regex(fpRegex);
    }
}
