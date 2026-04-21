package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: _Sets.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0004\u001a,\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0004¨\u0006\r"}, d2 = {"minus", "", "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/collections/SetsKt")
class SetsKt___SetsKt extends SetsKt__SetsKt {
    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> receiver, T t) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity(receiver.size()));
        Ref.BooleanRef removed = new Ref.BooleanRef();
        removed.element = false;
        Set<? extends T> $receiver$iv = receiver;
        for (T t2 : $receiver$iv) {
            boolean z = true;
            if (!removed.element && Intrinsics.areEqual(t2, t)) {
                removed.element = true;
                z = false;
            }
            if (z) {
                result.add(t2);
            }
        }
        return result;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet result = new LinkedHashSet(receiver);
        CollectionsKt.removeAll(result, elements);
        return result;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> receiver, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Collection<?> collectionConvertToSetForSetOperationWith = CollectionsKt.convertToSetForSetOperationWith(elements, receiver);
        if (collectionConvertToSetForSetOperationWith.isEmpty()) {
            return CollectionsKt.toSet(receiver);
        }
        if (!(collectionConvertToSetForSetOperationWith instanceof Set)) {
            LinkedHashSet result = new LinkedHashSet(receiver);
            result.removeAll(collectionConvertToSetForSetOperationWith);
            return result;
        }
        Set<? extends T> $receiver$iv = receiver;
        Collection destination$iv = new LinkedHashSet();
        for (T t : $receiver$iv) {
            if (!collectionConvertToSetForSetOperationWith.contains(t)) {
                destination$iv.add(t);
            }
        }
        return (Set) destination$iv;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet result = new LinkedHashSet(receiver);
        CollectionsKt.removeAll(result, elements);
        return result;
    }

    @InlineOnly
    private static final <T> Set<T> minusElement(@NotNull Set<? extends T> set, T t) {
        return SetsKt.minus(set, t);
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> receiver, T t) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity(receiver.size() + 1));
        result.addAll(receiver);
        result.add(t);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity(receiver.size() + elements.length));
        result.addAll(receiver);
        CollectionsKt.addAll(result, elements);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> receiver, @NotNull Iterable<? extends T> elements) {
        int size;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Integer numCollectionSizeOrNull = CollectionsKt.collectionSizeOrNull(elements);
        if (numCollectionSizeOrNull != null) {
            int it = numCollectionSizeOrNull.intValue();
            size = receiver.size() + it;
        } else {
            size = receiver.size() * 2;
        }
        LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity(size));
        result.addAll(receiver);
        CollectionsKt.addAll(result, elements);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity(receiver.size() * 2));
        result.addAll(receiver);
        CollectionsKt.addAll(result, elements);
        return result;
    }

    @InlineOnly
    private static final <T> Set<T> plusElement(@NotNull Set<? extends T> set, T t) {
        return SetsKt.plus(set, t);
    }
}
