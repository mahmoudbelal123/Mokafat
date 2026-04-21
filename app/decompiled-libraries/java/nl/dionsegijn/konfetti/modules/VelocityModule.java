package nl.dionsegijn.konfetti.modules;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.models.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: VelocityModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020\u0006J\u0006\u0010!\u001a\u00020\rJ\u0006\u0010\"\u001a\u00020#R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR*\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0014\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006$"}, d2 = {"Lnl/dionsegijn/konfetti/modules/VelocityModule;", "", "random", "Ljava/util/Random;", "(Ljava/util/Random;)V", "maxAngle", "", "getMaxAngle", "()Ljava/lang/Double;", "setMaxAngle", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", FirebaseAnalytics.Param.VALUE, "", "maxSpeed", "getMaxSpeed", "()Ljava/lang/Float;", "setMaxSpeed", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "minAngle", "getMinAngle", "()D", "setMinAngle", "(D)V", "minSpeed", "getMinSpeed", "()F", "setMinSpeed", "(F)V", "getRandom", "()Ljava/util/Random;", "getRadian", "getSpeed", "getVelocity", "Lnl/dionsegijn/konfetti/models/Vector;", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final class VelocityModule {

    @Nullable
    private Double maxAngle;

    @Nullable
    private Float maxSpeed;
    private double minAngle;
    private float minSpeed;

    @NotNull
    private final Random random;

    public VelocityModule(@NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(random, "random");
        this.random = random;
    }

    @NotNull
    public final Random getRandom() {
        return this.random;
    }

    public final double getMinAngle() {
        return this.minAngle;
    }

    public final void setMinAngle(double d) {
        this.minAngle = d;
    }

    @Nullable
    public final Double getMaxAngle() {
        return this.maxAngle;
    }

    public final void setMaxAngle(@Nullable Double d) {
        this.maxAngle = d;
    }

    public final float getMinSpeed() {
        return this.minSpeed;
    }

    public final void setMinSpeed(float value) {
        if (value < 0) {
            this.minSpeed = 0.0f;
        } else {
            this.minSpeed = value;
        }
    }

    @Nullable
    public final Float getMaxSpeed() {
        return this.maxSpeed;
    }

    public final void setMaxSpeed(@Nullable Float value) {
        if (value == null) {
            Intrinsics.throwNpe();
        }
        if (value.floatValue() < 0) {
            this.maxSpeed = Float.valueOf(0.0f);
        } else {
            this.maxSpeed = value;
        }
    }

    public final float getSpeed() {
        if (this.maxSpeed == null) {
            return this.minSpeed;
        }
        Float f = this.maxSpeed;
        if (f == null) {
            Intrinsics.throwNpe();
        }
        return ((f.floatValue() - this.minSpeed) * this.random.nextFloat()) + this.minSpeed;
    }

    public final double getRadian() {
        if (this.maxAngle == null) {
            return this.minAngle;
        }
        Double d = this.maxAngle;
        if (d == null) {
            Intrinsics.throwNpe();
        }
        return ((d.doubleValue() - this.minAngle) * this.random.nextDouble()) + this.minAngle;
    }

    @NotNull
    public final Vector getVelocity() {
        float speed = getSpeed();
        double radian = getRadian();
        float vx = ((float) Math.cos(radian)) * speed;
        float vy = ((float) Math.sin(radian)) * speed;
        return new Vector(vx, vy);
    }
}
