package kotlin;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: UninitializedPropertyAccessException.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlin/UninitializedPropertyAccessException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "()V", "message", "", "(Ljava/lang/String;)V", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/Throwable;)V", "kotlin-runtime"}, k = 1, mv = {1, 1, 5})
public final class UninitializedPropertyAccessException extends RuntimeException {
    public UninitializedPropertyAccessException() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UninitializedPropertyAccessException(@NotNull String message) {
        super(message);
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UninitializedPropertyAccessException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(cause, "cause");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UninitializedPropertyAccessException(@NotNull Throwable cause) {
        super(cause);
        Intrinsics.checkParameterIsNotNull(cause, "cause");
    }
}
