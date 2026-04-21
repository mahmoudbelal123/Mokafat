package kotlin.coroutines.experimental.intrinsics;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: Intrinsics.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a5\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u0010\b\u0004\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0083\b\u001a5\u0010\r\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\u001c\b\u0004\u0010\u000b\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aD\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\t*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a]\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\u0012\"\u0004\b\u0001\u0010\t*#\b\u0001\u0012\u0004\u0012\u0002H\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013¢\u0006\u0002\b\u00142\u0006\u0010\u0015\u001a\u0002H\u00122\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\"\u001c\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\t¨\u0006\u0017"}, d2 = {"COROUTINE_SUSPENDED", "", "COROUTINE_SUSPENDED$annotations", "()V", "getCOROUTINE_SUSPENDED", "()Ljava/lang/Object;", "buildContinuationByInvokeCall", "Lkotlin/coroutines/experimental/Continuation;", "", "T", "completion", "block", "Lkotlin/Function0;", "suspendCoroutineOrReturn", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "createCoroutineUnchecked", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 5})
@JvmName(name = "IntrinsicsKt")
public final class IntrinsicsKt {

    @NotNull
    private static final Object COROUTINE_SUSPENDED = new Object();

    @SinceKotlin(version = "1.1")
    private static /* synthetic */ void COROUTINE_SUSPENDED$annotations() {
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> Object suspendCoroutineOrReturn(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        Intrinsics.throwNpe();
        return null;
    }

    @NotNull
    public static final Object getCOROUTINE_SUSPENDED() {
        return COROUTINE_SUSPENDED;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> Continuation<Unit> createCoroutineUnchecked(@NotNull final Function1<? super Continuation<? super T>, ? extends Object> receiver, @NotNull final Continuation<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        if (receiver instanceof CoroutineImpl) {
            Continuation<Unit> continuationCreate = ((CoroutineImpl) receiver).create(completion);
            if (continuationCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
            }
            return ((CoroutineImpl) continuationCreate).getFacade();
        }
        return CoroutineIntrinsics.interceptContinuationIfNeeded(completion.getContext(), new Continuation<Unit>() { // from class: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt$createCoroutineUnchecked$$inlined$buildContinuationByInvokeCall$1
            @Override // kotlin.coroutines.experimental.Continuation
            public CoroutineContext getContext() {
                return completion.getContext();
            }

            @Override // kotlin.coroutines.experimental.Continuation
            public void resume(Unit value) {
                Continuation continuation = completion;
                try {
                    Function1 function1 = receiver;
                    if (function1 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
                    }
                    Object objInvoke = ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(completion);
                    if (objInvoke != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        if (continuation == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                        }
                        continuation.resume(objInvoke);
                    }
                } catch (Throwable th) {
                    continuation.resumeWithException(th);
                }
            }

            @Override // kotlin.coroutines.experimental.Continuation
            public void resumeWithException(Throwable exception) {
                completion.resumeWithException(exception);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <R, T> Continuation<Unit> createCoroutineUnchecked(@NotNull final Function2<? super R, ? super Continuation<? super T>, ? extends Object> receiver, final R r, @NotNull final Continuation<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        if (receiver instanceof CoroutineImpl) {
            Continuation<Unit> continuationCreate = ((CoroutineImpl) receiver).create(r, completion);
            if (continuationCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
            }
            return ((CoroutineImpl) continuationCreate).getFacade();
        }
        return CoroutineIntrinsics.interceptContinuationIfNeeded(completion.getContext(), new Continuation<Unit>() { // from class: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt$createCoroutineUnchecked$$inlined$buildContinuationByInvokeCall$2
            @Override // kotlin.coroutines.experimental.Continuation
            public CoroutineContext getContext() {
                return completion.getContext();
            }

            @Override // kotlin.coroutines.experimental.Continuation
            public void resume(Unit value) {
                Continuation continuation = completion;
                try {
                    Function2 function2 = receiver;
                    if (function2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
                    }
                    Object objInvoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, completion);
                    if (objInvoke != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        if (continuation == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                        }
                        continuation.resume(objInvoke);
                    }
                } catch (Throwable th) {
                    continuation.resumeWithException(th);
                }
            }

            @Override // kotlin.coroutines.experimental.Continuation
            public void resumeWithException(Throwable exception) {
                completion.resumeWithException(exception);
            }
        });
    }

    private static final <T> Continuation<Unit> buildContinuationByInvokeCall(final Continuation<? super T> continuation, final Function0<? extends Object> function0) {
        return CoroutineIntrinsics.interceptContinuationIfNeeded(continuation.getContext(), new Continuation<Unit>() { // from class: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt$buildContinuationByInvokeCall$continuation$1
            @Override // kotlin.coroutines.experimental.Continuation
            @NotNull
            public CoroutineContext getContext() {
                return continuation.getContext();
            }

            @Override // kotlin.coroutines.experimental.Continuation
            public void resume(@NotNull Unit value) {
                Intrinsics.checkParameterIsNotNull(value, "value");
                Continuation continuation2 = continuation;
                try {
                    Object objInvoke = function0.invoke();
                    if (objInvoke != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        if (continuation2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                        }
                        continuation2.resume(objInvoke);
                    }
                } catch (Throwable th) {
                    continuation2.resumeWithException(th);
                }
            }

            @Override // kotlin.coroutines.experimental.Continuation
            public void resumeWithException(@NotNull Throwable exception) {
                Intrinsics.checkParameterIsNotNull(exception, "exception");
                continuation.resumeWithException(exception);
            }
        });
    }
}
