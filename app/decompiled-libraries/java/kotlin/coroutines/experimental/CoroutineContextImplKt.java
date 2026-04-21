package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¨\u0006\u0003"}, d2 = {"plusImpl", "Lkotlin/coroutines/experimental/CoroutineContext;", "context", "kotlin-stdlib"}, k = 2, mv = {1, 1, 5})
public final class CoroutineContextImplKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final CoroutineContext plusImpl(@NotNull CoroutineContext $receiver, CoroutineContext context) {
        return context == EmptyCoroutineContext.INSTANCE ? $receiver : (CoroutineContext) context.fold($receiver, new Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext>() { // from class: kotlin.coroutines.experimental.CoroutineContextImplKt.plusImpl.1
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final CoroutineContext invoke(@NotNull CoroutineContext acc, @NotNull CoroutineContext.Element element) {
                CombinedContext combinedContext;
                Intrinsics.checkParameterIsNotNull(acc, "acc");
                Intrinsics.checkParameterIsNotNull(element, "element");
                CoroutineContext removed = acc.minusKey(element.getKey());
                if (removed == EmptyCoroutineContext.INSTANCE) {
                    return element;
                }
                ContinuationInterceptor interceptor = (ContinuationInterceptor) removed.get(ContinuationInterceptor.INSTANCE);
                if (interceptor != null) {
                    CoroutineContext left = removed.minusKey(ContinuationInterceptor.INSTANCE);
                    combinedContext = left == EmptyCoroutineContext.INSTANCE ? new CombinedContext(element, interceptor) : new CombinedContext(new CombinedContext(left, element), interceptor);
                } else {
                    combinedContext = new CombinedContext(removed, element);
                }
                return combinedContext;
            }
        });
    }
}
