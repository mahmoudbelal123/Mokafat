package nl.dionsegijn.konfetti.models;

import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"sizeDp", "", "Lnl/dionsegijn/konfetti/models/Size;", "getSizeDp", "(Lnl/dionsegijn/konfetti/models/Size;)I", "konfetti_release"}, k = 2, mv = {1, 1, 5})
public final class SizeKt {
    public static final int getSizeDp(@NotNull Size receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (int) (receiver.getSize() * Resources.getSystem().getDisplayMetrics().density);
    }
}
