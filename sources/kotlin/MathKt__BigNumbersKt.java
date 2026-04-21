package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: BigNumbers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\n\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0004\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\n\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\r\u0010\t\u001a\u00020\u0003*\u00020\u0003H\u0087\n¨\u0006\n"}, d2 = {"div", "Ljava/math/BigDecimal;", "other", "Ljava/math/BigInteger;", "minus", "mod", "plus", "rem", "times", "unaryMinus", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/MathKt")
class MathKt__BigNumbersKt {
    @InlineOnly
    private static final BigInteger plus(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger bigIntegerAdd = $receiver.add(other);
        Intrinsics.checkExpressionValueIsNotNull(bigIntegerAdd, "this.add(other)");
        return bigIntegerAdd;
    }

    @InlineOnly
    private static final BigInteger minus(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger bigIntegerSubtract = $receiver.subtract(other);
        Intrinsics.checkExpressionValueIsNotNull(bigIntegerSubtract, "this.subtract(other)");
        return bigIntegerSubtract;
    }

    @InlineOnly
    private static final BigInteger times(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger bigIntegerMultiply = $receiver.multiply(other);
        Intrinsics.checkExpressionValueIsNotNull(bigIntegerMultiply, "this.multiply(other)");
        return bigIntegerMultiply;
    }

    @InlineOnly
    private static final BigInteger div(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger bigIntegerDivide = $receiver.divide(other);
        Intrinsics.checkExpressionValueIsNotNull(bigIntegerDivide, "this.divide(other)");
        return bigIntegerDivide;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final BigInteger rem(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger bigIntegerRemainder = $receiver.remainder(other);
        Intrinsics.checkExpressionValueIsNotNull(bigIntegerRemainder, "this.remainder(other)");
        return bigIntegerRemainder;
    }

    @InlineOnly
    private static final BigInteger unaryMinus(@NotNull BigInteger $receiver) {
        BigInteger bigIntegerNegate = $receiver.negate();
        Intrinsics.checkExpressionValueIsNotNull(bigIntegerNegate, "this.negate()");
        return bigIntegerNegate;
    }

    @InlineOnly
    private static final BigDecimal plus(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal bigDecimalAdd = $receiver.add(other);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimalAdd, "this.add(other)");
        return bigDecimalAdd;
    }

    @InlineOnly
    private static final BigDecimal minus(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal bigDecimalSubtract = $receiver.subtract(other);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimalSubtract, "this.subtract(other)");
        return bigDecimalSubtract;
    }

    @InlineOnly
    private static final BigDecimal times(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal bigDecimalMultiply = $receiver.multiply(other);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimalMultiply, "this.multiply(other)");
        return bigDecimalMultiply;
    }

    @InlineOnly
    private static final BigDecimal div(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal bigDecimalDivide = $receiver.divide(other, RoundingMode.HALF_EVEN);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimalDivide, "this.divide(other, RoundingMode.HALF_EVEN)");
        return bigDecimalDivide;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use rem(other) instead", replaceWith = @ReplaceWith(expression = "rem(other)", imports = {}))
    @InlineOnly
    private static final BigDecimal mod(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal bigDecimalRemainder = $receiver.remainder(other);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimalRemainder, "this.remainder(other)");
        return bigDecimalRemainder;
    }

    @InlineOnly
    private static final BigDecimal rem(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal bigDecimalRemainder = $receiver.remainder(other);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimalRemainder, "this.remainder(other)");
        return bigDecimalRemainder;
    }

    @InlineOnly
    private static final BigDecimal unaryMinus(@NotNull BigDecimal $receiver) {
        BigDecimal bigDecimalNegate = $receiver.negate();
        Intrinsics.checkExpressionValueIsNotNull(bigDecimalNegate, "this.negate()");
        return bigDecimalNegate;
    }
}
