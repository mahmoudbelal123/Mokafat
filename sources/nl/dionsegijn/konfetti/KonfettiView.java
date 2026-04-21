package nl.dionsegijn.konfetti;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.listeners.OnParticleSystemUpdateListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: KonfettiView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001bB\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u000fJ\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\u0006\u0010\u0018\u001a\u00020\u0015J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u000fR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lnl/dionsegijn/konfetti/KonfettiView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "onParticleSystemUpdateListener", "Lnl/dionsegijn/konfetti/listeners/OnParticleSystemUpdateListener;", "systems", "", "Lnl/dionsegijn/konfetti/ParticleSystem;", "timer", "Lnl/dionsegijn/konfetti/KonfettiView$TimerIntegration;", "build", "getActiveSystems", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "reset", "start", "particleSystem", "TimerIntegration", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final class KonfettiView extends View {
    private OnParticleSystemUpdateListener onParticleSystemUpdateListener;
    private final List<ParticleSystem> systems;
    private TimerIntegration timer;

    public KonfettiView(@Nullable Context context) {
        super(context);
        this.systems = new ArrayList();
        this.timer = new TimerIntegration();
    }

    public KonfettiView(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.systems = new ArrayList();
        this.timer = new TimerIntegration();
    }

    public KonfettiView(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.systems = new ArrayList();
        this.timer = new TimerIntegration();
    }

    @NotNull
    public final List<ParticleSystem> getActiveSystems() {
        return this.systems;
    }

    @NotNull
    public final ParticleSystem build() {
        return new ParticleSystem(this);
    }

    @Override // android.view.View
    protected void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        float deltaTime = this.timer.getDeltaTime();
        int i = this.systems.size() - 1;
        if (i >= 0) {
            while (true) {
                ParticleSystem particleSystem = this.systems.get(i);
                particleSystem.getRenderSystem$konfetti_release().render(canvas, deltaTime);
                if (particleSystem.doneEmitting()) {
                    this.systems.remove(i);
                    OnParticleSystemUpdateListener onParticleSystemUpdateListener = this.onParticleSystemUpdateListener;
                    if (onParticleSystemUpdateListener != null) {
                        onParticleSystemUpdateListener.onParticleSystemEnded(this, particleSystem, this.systems.size());
                    }
                }
                if (i == 0) {
                    break;
                } else {
                    i--;
                }
            }
        }
        if (this.systems.size() != 0) {
            invalidate();
        } else {
            this.timer.reset();
        }
    }

    public final void start(@NotNull ParticleSystem particleSystem) {
        Intrinsics.checkParameterIsNotNull(particleSystem, "particleSystem");
        this.systems.add(particleSystem);
        OnParticleSystemUpdateListener onParticleSystemUpdateListener = this.onParticleSystemUpdateListener;
        if (onParticleSystemUpdateListener != null) {
            onParticleSystemUpdateListener.onParticleSystemStarted(this, particleSystem, this.systems.size());
        }
        invalidate();
    }

    public final void reset() {
        this.systems.clear();
    }

    /* JADX INFO: compiled from: KonfettiView.kt */
    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lnl/dionsegijn/konfetti/KonfettiView$TimerIntegration;", "", "()V", "previousTime", "", "getDeltaTime", "", "reset", "", "konfetti_release"}, k = 1, mv = {1, 1, 5})
    public static final class TimerIntegration {
        private long previousTime = -1;

        public final void reset() {
            this.previousTime = -1L;
        }

        public final float getDeltaTime() {
            if (this.previousTime == -1) {
                this.previousTime = System.nanoTime();
            }
            long currentTime = System.nanoTime();
            long dt = (currentTime - this.previousTime) / ((long) 1000000);
            this.previousTime = currentTime;
            return dt / 1000;
        }
    }
}
