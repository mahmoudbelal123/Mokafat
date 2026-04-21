package nl.dionsegijn.konfetti.emitters;

import android.graphics.Canvas;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import nl.dionsegijn.konfetti.Confetti;
import nl.dionsegijn.konfetti.models.ConfettiConfig;
import nl.dionsegijn.konfetti.models.LocationModule;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import nl.dionsegijn.konfetti.models.Vector;
import nl.dionsegijn.konfetti.modules.VelocityModule;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: RenderSystem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lnl/dionsegijn/konfetti/emitters/RenderSystem;", "", FirebaseAnalytics.Param.LOCATION, "Lnl/dionsegijn/konfetti/models/LocationModule;", "velocity", "Lnl/dionsegijn/konfetti/modules/VelocityModule;", "sizes", "", "Lnl/dionsegijn/konfetti/models/Size;", "shapes", "Lnl/dionsegijn/konfetti/models/Shape;", "colors", "", "config", "Lnl/dionsegijn/konfetti/models/ConfettiConfig;", "emitter", "Lnl/dionsegijn/konfetti/emitters/Emitter;", "(Lnl/dionsegijn/konfetti/models/LocationModule;Lnl/dionsegijn/konfetti/modules/VelocityModule;[Lnl/dionsegijn/konfetti/models/Size;[Lnl/dionsegijn/konfetti/models/Shape;[ILnl/dionsegijn/konfetti/models/ConfettiConfig;Lnl/dionsegijn/konfetti/emitters/Emitter;)V", "gravity", "Lnl/dionsegijn/konfetti/models/Vector;", "particles", "", "Lnl/dionsegijn/konfetti/Confetti;", "random", "Ljava/util/Random;", "[Lnl/dionsegijn/konfetti/models/Shape;", "[Lnl/dionsegijn/konfetti/models/Size;", "addConfetti", "", "getActiveParticles", "", "isDoneEmitting", "", "render", "canvas", "Landroid/graphics/Canvas;", "deltaTime", "", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final class RenderSystem {
    private final int[] colors;
    private final ConfettiConfig config;
    private final Emitter emitter;
    private Vector gravity;
    private final LocationModule location;
    private final List<Confetti> particles;
    private final Random random;
    private final Shape[] shapes;
    private final Size[] sizes;
    private final VelocityModule velocity;

    /* JADX INFO: renamed from: nl.dionsegijn.konfetti.emitters.RenderSystem$1, reason: invalid class name */
    /* JADX INFO: compiled from: RenderSystem.kt */
    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 5})
    static final class AnonymousClass1 extends FunctionReference implements Function0<Unit> {
        AnonymousClass1(RenderSystem renderSystem) {
            super(0, renderSystem);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "addConfetti";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(RenderSystem.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "addConfetti()V";
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((RenderSystem) this.receiver).addConfetti();
        }
    }

    public RenderSystem(@NotNull LocationModule location, @NotNull VelocityModule velocity, @NotNull Size[] sizes, @NotNull Shape[] shapes, @NotNull int[] colors, @NotNull ConfettiConfig config, @NotNull Emitter emitter) {
        Intrinsics.checkParameterIsNotNull(location, "location");
        Intrinsics.checkParameterIsNotNull(velocity, "velocity");
        Intrinsics.checkParameterIsNotNull(sizes, "sizes");
        Intrinsics.checkParameterIsNotNull(shapes, "shapes");
        Intrinsics.checkParameterIsNotNull(colors, "colors");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
        this.location = location;
        this.velocity = velocity;
        this.sizes = sizes;
        this.shapes = shapes;
        this.colors = colors;
        this.config = config;
        this.emitter = emitter;
        this.random = new Random();
        this.gravity = new Vector(0.0f, 0.01f);
        this.particles = new ArrayList();
        this.emitter.setAddConfettiFunc(new AnonymousClass1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addConfetti() {
        this.particles.add(new Confetti(new Vector(this.location.getX(), this.location.getY()), this.colors[this.random.nextInt(this.colors.length)], this.sizes[this.random.nextInt(this.sizes.length)], this.shapes[this.random.nextInt(this.shapes.length)], this.config.getTimeToLive(), this.config.getFadeOut(), null, this.velocity.getVelocity(), 64, null));
    }

    public final void render(@NotNull Canvas canvas, float deltaTime) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        this.emitter.createConfetti(deltaTime);
        int i = this.particles.size() - 1;
        if (i < 0) {
            return;
        }
        while (true) {
            Confetti particle = this.particles.get(i);
            particle.applyForce(this.gravity);
            particle.render(canvas, deltaTime);
            if (particle.isDead()) {
                this.particles.remove(i);
            }
            if (i == 0) {
                return;
            } else {
                i--;
            }
        }
    }

    public final int getActiveParticles() {
        return this.particles.size();
    }

    public final boolean isDoneEmitting() {
        return this.emitter.getIsStarted() && this.particles.size() == 0;
    }
}
