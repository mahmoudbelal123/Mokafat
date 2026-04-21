package nl.dionsegijn.konfetti;

import android.support.v4.internal.view.SupportMenu;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.emitters.BurstEmitter;
import nl.dionsegijn.konfetti.emitters.Emitter;
import nl.dionsegijn.konfetti.emitters.RenderSystem;
import nl.dionsegijn.konfetti.emitters.StreamEmitter;
import nl.dionsegijn.konfetti.models.ConfettiConfig;
import nl.dionsegijn.konfetti.models.LocationModule;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import nl.dionsegijn.konfetti.modules.VelocityModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ParticleSystem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0012\u0010\u001e\u001a\u00020\u00002\n\u0010\u0005\u001a\u00020\u0006\"\u00020\u001dJ\u001f\u0010\u001f\u001a\u00020\u00002\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015¢\u0006\u0002\u0010 J\u001f\u0010!\u001a\u00020\u00002\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u0014\"\u00020\u0018¢\u0006\u0002\u0010#J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001dJ\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020%2\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-J\u0016\u0010+\u001a\u00020\u00002\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020-J\u000e\u00100\u001a\u00020\u00002\u0006\u00101\u001a\u00020(J\u0016\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000204J3\u00102\u001a\u00020\u00002\u0006\u00106\u001a\u0002042\n\b\u0002\u00107\u001a\u0004\u0018\u0001042\u0006\u00108\u001a\u0002042\n\b\u0002\u00109\u001a\u0004\u0018\u000104¢\u0006\u0002\u0010:J\u000e\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u000204J\u0016\u0010;\u001a\u00020\u00002\u0006\u0010=\u001a\u0002042\u0006\u0010>\u001a\u000204J\u000e\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020AJ\b\u0010B\u001a\u00020%H\u0002J\u0010\u0010C\u001a\u00020%2\u0006\u0010)\u001a\u00020*H\u0002J\u0016\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020\u001d2\u0006\u0010F\u001a\u00020\u001dJ\u0016\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020\u001d2\u0006\u0010G\u001a\u00020AR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lnl/dionsegijn/konfetti/ParticleSystem;", "", "konfettiView", "Lnl/dionsegijn/konfetti/KonfettiView;", "(Lnl/dionsegijn/konfetti/KonfettiView;)V", "colors", "", "confettiConfig", "Lnl/dionsegijn/konfetti/models/ConfettiConfig;", FirebaseAnalytics.Param.LOCATION, "Lnl/dionsegijn/konfetti/models/LocationModule;", "random", "Ljava/util/Random;", "renderSystem", "Lnl/dionsegijn/konfetti/emitters/RenderSystem;", "getRenderSystem$konfetti_release", "()Lnl/dionsegijn/konfetti/emitters/RenderSystem;", "setRenderSystem$konfetti_release", "(Lnl/dionsegijn/konfetti/emitters/RenderSystem;)V", "shapes", "", "Lnl/dionsegijn/konfetti/models/Shape;", "[Lnl/dionsegijn/konfetti/models/Shape;", "sizes", "Lnl/dionsegijn/konfetti/models/Size;", "[Lnl/dionsegijn/konfetti/models/Size;", "velocity", "Lnl/dionsegijn/konfetti/modules/VelocityModule;", "activeParticles", "", "addColors", "addShapes", "([Lnl/dionsegijn/konfetti/models/Shape;)Lnl/dionsegijn/konfetti/ParticleSystem;", "addSizes", "possibleSizes", "([Lnl/dionsegijn/konfetti/models/Size;)Lnl/dionsegijn/konfetti/ParticleSystem;", "burst", "", "amount", "doneEmitting", "", "emitter", "Lnl/dionsegijn/konfetti/emitters/Emitter;", "setDirection", "direction", "", "minDirection", "maxDirection", "setFadeOutEnabled", "fade", "setPosition", "x", "", "y", "minX", "maxX", "minY", "maxY", "(FLjava/lang/Float;FLjava/lang/Float;)Lnl/dionsegijn/konfetti/ParticleSystem;", "setSpeed", "speed", "minSpeed", "maxSpeed", "setTimeToLive", "timeInMs", "", "start", "startRenderSystem", "stream", "particlesPerSecond", "maxParticles", "emittingTime", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final class ParticleSystem {
    private int[] colors;
    private ConfettiConfig confettiConfig;
    private final KonfettiView konfettiView;
    private LocationModule location;
    private final Random random;

    @NotNull
    public RenderSystem renderSystem;
    private Shape[] shapes;
    private Size[] sizes;
    private VelocityModule velocity;

    public ParticleSystem(@NotNull KonfettiView konfettiView) {
        Intrinsics.checkParameterIsNotNull(konfettiView, "konfettiView");
        this.konfettiView = konfettiView;
        this.random = new Random();
        this.location = new LocationModule(this.random);
        this.velocity = new VelocityModule(this.random);
        this.colors = new int[]{SupportMenu.CATEGORY_MASK};
        Object[] elements$iv = {new Size(16, 0.0f, 2, null)};
        this.sizes = (Size[]) elements$iv;
        Object[] elements$iv2 = {Shape.RECT};
        this.shapes = (Shape[]) elements$iv2;
        this.confettiConfig = new ConfettiConfig(false, 0L, 3, null);
    }

    @NotNull
    public final RenderSystem getRenderSystem$konfetti_release() {
        RenderSystem renderSystem = this.renderSystem;
        if (renderSystem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("renderSystem");
        }
        return renderSystem;
    }

    public final void setRenderSystem$konfetti_release(@NotNull RenderSystem renderSystem) {
        Intrinsics.checkParameterIsNotNull(renderSystem, "<set-?>");
        this.renderSystem = renderSystem;
    }

    @NotNull
    public final ParticleSystem setPosition(float x, float y) {
        this.location.setX(x);
        this.location.setY(y);
        return this;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ ParticleSystem setPosition$default(ParticleSystem particleSystem, float f, Float f2, float f3, Float f4, int i, Object obj) {
        if ((i & 2) != 0) {
            f2 = (Float) null;
        }
        if ((i & 8) != 0) {
            f4 = (Float) null;
        }
        return particleSystem.setPosition(f, f2, f3, f4);
    }

    @NotNull
    public final ParticleSystem setPosition(float minX, @Nullable Float maxX, float minY, @Nullable Float maxY) {
        this.location.betweenX(minX, maxX);
        this.location.betweenY(minY, maxY);
        return this;
    }

    @NotNull
    public final ParticleSystem addColors(@NotNull int... colors) {
        Intrinsics.checkParameterIsNotNull(colors, "colors");
        this.colors = colors;
        return this;
    }

    @NotNull
    public final ParticleSystem addSizes(@NotNull Size... possibleSizes) {
        Intrinsics.checkParameterIsNotNull(possibleSizes, "possibleSizes");
        Collection destination$iv$iv = new ArrayList();
        for (Size size : possibleSizes) {
            if (size instanceof Size) {
                destination$iv$iv.add(size);
            }
        }
        Collection $receiver$iv = (List) destination$iv$iv;
        Collection thisCollection$iv = $receiver$iv;
        Object[] array = thisCollection$iv.toArray(new Size[thisCollection$iv.size()]);
        if (array == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        this.sizes = (Size[]) array;
        return this;
    }

    @NotNull
    public final ParticleSystem addShapes(@NotNull Shape... shapes) {
        Intrinsics.checkParameterIsNotNull(shapes, "shapes");
        Collection destination$iv$iv = new ArrayList();
        for (Shape shape : shapes) {
            if (shape instanceof Shape) {
                destination$iv$iv.add(shape);
            }
        }
        Collection $receiver$iv = (List) destination$iv$iv;
        Collection thisCollection$iv = $receiver$iv;
        Object[] array = thisCollection$iv.toArray(new Shape[thisCollection$iv.size()]);
        if (array == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        this.shapes = (Shape[]) array;
        return this;
    }

    @NotNull
    public final ParticleSystem setDirection(double direction) {
        this.velocity.setMinAngle(Math.toRadians(direction));
        return this;
    }

    @NotNull
    public final ParticleSystem setDirection(double minDirection, double maxDirection) {
        this.velocity.setMinAngle(Math.toRadians(minDirection));
        this.velocity.setMaxAngle(Double.valueOf(Math.toRadians(maxDirection)));
        return this;
    }

    @NotNull
    public final ParticleSystem setSpeed(float speed) {
        this.velocity.setMinSpeed(speed);
        return this;
    }

    @NotNull
    public final ParticleSystem setSpeed(float minSpeed, float maxSpeed) {
        this.velocity.setMinSpeed(minSpeed);
        this.velocity.setMaxSpeed(Float.valueOf(maxSpeed));
        return this;
    }

    @NotNull
    public final ParticleSystem setFadeOutEnabled(boolean fade) {
        this.confettiConfig.setFadeOut(fade);
        return this;
    }

    @NotNull
    public final ParticleSystem setTimeToLive(long timeInMs) {
        this.confettiConfig.setTimeToLive(timeInMs);
        return this;
    }

    public final void burst(int amount) {
        startRenderSystem(new BurstEmitter().build(amount));
    }

    public final void stream(int particlesPerSecond, long emittingTime) {
        StreamEmitter stream = StreamEmitter.build$default(new StreamEmitter(), particlesPerSecond, emittingTime, 0, 4, null);
        startRenderSystem(stream);
    }

    public final void stream(int particlesPerSecond, int maxParticles) {
        StreamEmitter stream = StreamEmitter.build$default(new StreamEmitter(), particlesPerSecond, 0L, maxParticles, 2, null);
        startRenderSystem(stream);
    }

    public final void emitter(@NotNull Emitter emitter) {
        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
        startRenderSystem(emitter);
    }

    private final void startRenderSystem(Emitter emitter) {
        this.renderSystem = new RenderSystem(this.location, this.velocity, this.sizes, this.shapes, this.colors, this.confettiConfig, emitter);
        start();
    }

    public final boolean doneEmitting() {
        RenderSystem renderSystem = this.renderSystem;
        if (renderSystem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("renderSystem");
        }
        return renderSystem.isDoneEmitting();
    }

    private final void start() {
        this.konfettiView.start(this);
    }

    public final int activeParticles() {
        RenderSystem renderSystem = this.renderSystem;
        if (renderSystem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("renderSystem");
        }
        return renderSystem.getActiveParticles();
    }
}
