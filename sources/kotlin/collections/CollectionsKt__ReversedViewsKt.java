package kotlin.collections;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ReversedViews.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007¢\u0006\u0002\b\u0004\u001a\u001d\u0010\u0005\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\b\u001a\u001d\u0010\t\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"asReversed", "", "T", "", "asReversedMutable", "reverseElementIndex", "", FirebaseAnalytics.Param.INDEX, "reverseElementIndex$CollectionsKt__ReversedViewsKt", "reversePositionIndex", "reversePositionIndex$CollectionsKt__ReversedViewsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(@NotNull List<?> list, int index) {
        if (index >= 0 && index <= list.size() - 1) {
            return (list.size() - index) - 1;
        }
        throw new IndexOutOfBoundsException("Index " + index + " should be in range [" + new IntRange(0, list.size() - 1) + "].");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(@NotNull List<?> list, int index) {
        if (index >= 0 && index <= list.size()) {
            return list.size() - index;
        }
        throw new IndexOutOfBoundsException("Index " + index + " should be in range [" + new IntRange(0, list.size()) + "].");
    }

    @NotNull
    public static final <T> List<T> asReversed(@NotNull List<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ReversedListReadOnly(receiver);
    }

    @JvmName(name = "asReversedMutable")
    @NotNull
    public static final <T> List<T> asReversedMutable(@NotNull List<T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ReversedList(receiver);
    }
}
