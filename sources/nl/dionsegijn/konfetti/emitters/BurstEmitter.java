package nl.dionsegijn.konfetti.emitters;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: BurstEmitter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lnl/dionsegijn/konfetti/emitters/BurstEmitter;", "Lnl/dionsegijn/konfetti/emitters/Emitter;", "()V", FirebaseAnalytics.Param.VALUE, "", "amountOfParticles", "setAmountOfParticles", "(I)V", "isStarted", "", "build", "createConfetti", "", "deltaTime", "", "isFinished", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final class BurstEmitter extends Emitter {
    private int amountOfParticles;
    private boolean isStarted;

    private final void setAmountOfParticles(int value) {
        this.amountOfParticles = value <= 1000 ? value : 1000;
    }

    @NotNull
    public final Emitter build(int amountOfParticles) {
        setAmountOfParticles(amountOfParticles);
        this.isStarted = false;
        return this;
    }

    @Override // nl.dionsegijn.konfetti.emitters.Emitter
    public void createConfetti(float deltaTime) {
        if (!this.isStarted) {
            int i = 1;
            this.isStarted = true;
            int i2 = this.amountOfParticles;
            if (1 > i2) {
                return;
            }
            while (true) {
                Function0<Unit> addConfettiFunc = getAddConfettiFunc();
                if (addConfettiFunc != null) {
                    addConfettiFunc.invoke();
                }
                if (i == i2) {
                    return;
                } else {
                    i++;
                }
            }
        }
    }

    @Override // nl.dionsegijn.konfetti.emitters.Emitter
    /* JADX INFO: renamed from: isFinished, reason: from getter */
    public boolean getIsStarted() {
        return this.isStarted;
    }
}
