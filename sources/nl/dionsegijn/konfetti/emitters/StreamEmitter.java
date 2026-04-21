package nl.dionsegijn.konfetti.emitters;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: StreamEmitter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lnl/dionsegijn/konfetti/emitters/StreamEmitter;", "Lnl/dionsegijn/konfetti/emitters/Emitter;", "()V", "amountPerMs", "", "createParticleMs", "elapsedTime", "emittingTime", "", "maxParticles", "", "particlesCreated", "build", "particlesPerSecond", "createConfetti", "", "deltaTime", "createParticle", "isFinished", "", "isTimeElapsed", "reachedMaxParticles", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final class StreamEmitter extends Emitter {
    private float amountPerMs;
    private float createParticleMs;
    private float elapsedTime;
    private long emittingTime;
    private int maxParticles = -1;
    private int particlesCreated;

    @NotNull
    public static /* bridge */ /* synthetic */ StreamEmitter build$default(StreamEmitter streamEmitter, int i, long j, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            j = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        return streamEmitter.build(i, j, i2);
    }

    @NotNull
    public final StreamEmitter build(int particlesPerSecond, long emittingTime, int maxParticles) {
        this.maxParticles = maxParticles;
        this.emittingTime = emittingTime;
        this.amountPerMs = 1.0f / particlesPerSecond;
        return this;
    }

    @Override // nl.dionsegijn.konfetti.emitters.Emitter
    public void createConfetti(float deltaTime) {
        this.createParticleMs += deltaTime;
        if (this.createParticleMs >= this.amountPerMs && !isTimeElapsed()) {
            int amount = (int) (this.createParticleMs / this.amountPerMs);
            Iterable $receiver$iv = new IntRange(1, amount);
            Iterator<Integer> it = $receiver$iv.iterator();
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                createParticle();
            }
            this.createParticleMs %= this.amountPerMs;
        }
        this.elapsedTime += 1000 * deltaTime;
    }

    private final void createParticle() {
        if (reachedMaxParticles()) {
            return;
        }
        this.particlesCreated++;
        Function0<Unit> addConfettiFunc = getAddConfettiFunc();
        if (addConfettiFunc != null) {
            addConfettiFunc.invoke();
        }
    }

    private final boolean isTimeElapsed() {
        return this.emittingTime != 0 && this.elapsedTime >= ((float) this.emittingTime);
    }

    private final boolean reachedMaxParticles() {
        int i = this.maxParticles;
        return 1 <= i && i <= this.particlesCreated;
    }

    @Override // nl.dionsegijn.konfetti.emitters.Emitter
    /* JADX INFO: renamed from: isFinished */
    public boolean getIsStarted() {
        if (this.emittingTime > 0) {
            if (this.elapsedTime < this.emittingTime) {
                return false;
            }
        } else if (this.maxParticles < this.particlesCreated) {
            return false;
        }
        return true;
    }
}
