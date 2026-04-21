package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Iterators.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u001a\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\u0002\u001a\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\n\u001a\"\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t0\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\n"}, d2 = {"forEach", "", "T", "", "operation", "Lkotlin/Function1;", "iterator", "Ljava/util/Enumeration;", "withIndex", "Lkotlin/collections/IndexedValue;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/collections/CollectionsKt")
public class CollectionsKt__IteratorsKt extends CollectionsKt__IterablesKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlin.collections.CollectionsKt__IteratorsKt$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: Iterators.kt */
    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0096\u0002J\u000e\u0010\u0005\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlin/collections/CollectionsKt__IteratorsKt$iterator$1", "", "(Ljava/util/Enumeration;)V", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
    public static final class AnonymousClass1<T> implements Iterator<T>, KMappedMarker {
        final /* synthetic */ Enumeration receiver$0;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1(Enumeration<T> enumeration) {
            this.receiver$0 = enumeration;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.receiver$0.hasMoreElements();
        }

        @Override // java.util.Iterator
        public T next() {
            return (T) this.receiver$0.nextElement();
        }
    }

    @NotNull
    public static final <T> Iterator<T> iterator(@NotNull Enumeration<T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new AnonymousClass1(receiver);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> Iterator<T> iterator(@NotNull Iterator<? extends T> it) {
        return it;
    }

    @NotNull
    public static final <T> Iterator<IndexedValue<T>> withIndex(@NotNull Iterator<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new IndexingIterator(receiver);
    }

    public static final <T> void forEach(@NotNull Iterator<? extends T> receiver, @NotNull Function1<? super T, Unit> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        while (receiver.hasNext()) {
            Object element = receiver.next();
            operation.invoke(element);
        }
    }
}
