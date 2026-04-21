package nl.dionsegijn.konfetti.emitters;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: Emitter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lnl/dionsegijn/konfetti/emitters/Emitter;", "", "()V", "addConfettiFunc", "Lkotlin/Function0;", "", "getAddConfettiFunc", "()Lkotlin/jvm/functions/Function0;", "setAddConfettiFunc", "(Lkotlin/jvm/functions/Function0;)V", "createConfetti", "deltaTime", "", "isFinished", "", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public abstract class Emitter {

    @Nullable
    private Function0<Unit> addConfettiFunc;

    public abstract void createConfetti(float deltaTime);

    public abstract boolean isFinished();

    @Nullable
    public final Function0<Unit> getAddConfettiFunc() {
        return this.addConfettiFunc;
    }

    public final void setAddConfettiFunc(@Nullable Function0<Unit> function0) {
        this.addConfettiFunc = function0;
    }
}
