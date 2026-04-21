package nl.dionsegijn.konfetti;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import nl.dionsegijn.konfetti.models.SizeKt;
import nl.dionsegijn.konfetti.models.Vector;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: Confetti.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0002\u0010\u0010J\u000e\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0003J\u0010\u00102\u001a\u0002002\u0006\u00103\u001a\u000204H\u0002J\b\u0010)\u001a\u00020\u001fH\u0002J\u0006\u00105\u001a\u00020\rJ\u0016\u00106\u001a\u0002002\u0006\u00103\u001a\u0002042\u0006\u00107\u001a\u00020\u001fJ\u0010\u00108\u001a\u0002002\u0006\u00107\u001a\u00020\u001fH\u0002J\u0010\u00109\u001a\u0002002\u0006\u00107\u001a\u00020\u001fH\u0002R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001b\"\u0004\b-\u0010\u001dR\u000e\u0010.\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lnl/dionsegijn/konfetti/Confetti;", "", FirebaseAnalytics.Param.LOCATION, "Lnl/dionsegijn/konfetti/models/Vector;", "color", "", "size", "Lnl/dionsegijn/konfetti/models/Size;", "shape", "Lnl/dionsegijn/konfetti/models/Shape;", "lifespan", "", "fadeOut", "", "acceleration", "velocity", "(Lnl/dionsegijn/konfetti/models/Vector;ILnl/dionsegijn/konfetti/models/Size;Lnl/dionsegijn/konfetti/models/Shape;JZLnl/dionsegijn/konfetti/models/Vector;Lnl/dionsegijn/konfetti/models/Vector;)V", "alpha", "getColor", "()I", "getFadeOut", "()Z", "getLifespan", "()J", "setLifespan", "(J)V", "getLocation", "()Lnl/dionsegijn/konfetti/models/Vector;", "setLocation", "(Lnl/dionsegijn/konfetti/models/Vector;)V", "mass", "", "paint", "Landroid/graphics/Paint;", "rectF", "Landroid/graphics/RectF;", "rotation", "rotationSpeed", "rotationWidth", "getShape", "()Lnl/dionsegijn/konfetti/models/Shape;", "getSize", "()Lnl/dionsegijn/konfetti/models/Size;", "speedF", "getVelocity", "setVelocity", "width", "applyForce", "", "force", "display", "canvas", "Landroid/graphics/Canvas;", "isDead", "render", "deltaTime", "update", "updateAlpha", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final class Confetti {
    private Vector acceleration;
    private int alpha;
    private final int color;
    private final boolean fadeOut;
    private long lifespan;

    @NotNull
    private Vector location;
    private final float mass;
    private final Paint paint;
    private RectF rectF;
    private float rotation;
    private float rotationSpeed;
    private float rotationWidth;

    @NotNull
    private final Shape shape;

    @NotNull
    private final Size size;
    private float speedF;

    @NotNull
    private Vector velocity;
    private float width;

    public Confetti(@NotNull Vector location, int color, @NotNull Size size, @NotNull Shape shape, long lifespan, boolean fadeOut, @NotNull Vector acceleration, @NotNull Vector velocity) {
        Intrinsics.checkParameterIsNotNull(location, "location");
        Intrinsics.checkParameterIsNotNull(size, "size");
        Intrinsics.checkParameterIsNotNull(shape, "shape");
        Intrinsics.checkParameterIsNotNull(acceleration, "acceleration");
        Intrinsics.checkParameterIsNotNull(velocity, "velocity");
        this.location = location;
        this.color = color;
        this.size = size;
        this.shape = shape;
        this.lifespan = lifespan;
        this.fadeOut = fadeOut;
        this.acceleration = acceleration;
        this.velocity = velocity;
        this.mass = this.size.getMass();
        this.width = SizeKt.getSizeDp(this.size);
        this.paint = new Paint();
        this.rotationSpeed = 1.0f;
        this.rotationWidth = this.width;
        this.rectF = new RectF();
        this.speedF = 60.0f;
        this.alpha = 255;
        float minRotationSpeed = 0.29f * Resources.getSystem().getDisplayMetrics().density;
        float maxRotationSpeed = 3 * minRotationSpeed;
        this.rotationSpeed = (new Random().nextFloat() * maxRotationSpeed) + minRotationSpeed;
        this.paint.setColor(this.color);
    }

    @NotNull
    public final Vector getLocation() {
        return this.location;
    }

    public final void setLocation(@NotNull Vector vector) {
        Intrinsics.checkParameterIsNotNull(vector, "<set-?>");
        this.location = vector;
    }

    public final int getColor() {
        return this.color;
    }

    @NotNull
    public final Size getSize() {
        return this.size;
    }

    @NotNull
    public final Shape getShape() {
        return this.shape;
    }

    public /* synthetic */ Confetti(Vector vector, int i, Size size, Shape shape, long j, boolean z, Vector vector2, Vector vector3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(vector, i, size, shape, (i2 & 16) != 0 ? -1L : j, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? new Vector(0.0f, 0.0f) : vector2, (i2 & 128) != 0 ? new Vector(0.0f, 0.0f, 3, null) : vector3);
    }

    public final long getLifespan() {
        return this.lifespan;
    }

    public final void setLifespan(long j) {
        this.lifespan = j;
    }

    public final boolean getFadeOut() {
        return this.fadeOut;
    }

    @NotNull
    public final Vector getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(@NotNull Vector vector) {
        Intrinsics.checkParameterIsNotNull(vector, "<set-?>");
        this.velocity = vector;
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    private final float getWidth() {
        return this.width;
    }

    public final boolean isDead() {
        return ((float) this.alpha) <= 0.0f;
    }

    public final void applyForce(@NotNull Vector force) {
        Intrinsics.checkParameterIsNotNull(force, "force");
        Vector f = Vector.copy$default(force, 0.0f, 0.0f, 3, null);
        f.div(this.mass);
        this.acceleration.add(f);
    }

    public final void render(@NotNull Canvas canvas, float deltaTime) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        update(deltaTime);
        display(canvas);
    }

    private final void update(float deltaTime) {
        this.velocity.add(this.acceleration);
        Vector v = Vector.copy$default(this.velocity, 0.0f, 0.0f, 3, null);
        v.mult(this.speedF * deltaTime);
        this.location.add(v);
        if (this.lifespan <= 0) {
            updateAlpha(deltaTime);
        } else {
            this.lifespan -= (long) (1000 * deltaTime);
        }
        float rSpeed = this.rotationSpeed * deltaTime * this.speedF;
        this.rotation += rSpeed;
        if (this.rotation >= 360) {
            this.rotation = 0.0f;
        }
        this.rotationWidth -= rSpeed;
        if (this.rotationWidth < 0) {
            this.rotationWidth = this.width;
        }
    }

    private final void updateAlpha(float deltaTime) {
        if (this.fadeOut) {
            float f = 5;
            float interval = f * deltaTime * this.speedF;
            if (this.alpha - interval >= 0) {
                this.alpha -= (int) ((f * deltaTime) * this.speedF);
                return;
            } else {
                this.alpha = 0;
                return;
            }
        }
        this.alpha = 0;
    }

    private final void display(Canvas canvas) {
        if (this.location.getY() > canvas.getHeight()) {
            this.lifespan = 0L;
            return;
        }
        if (this.location.getX() <= canvas.getWidth()) {
            float f = 0;
            if (this.location.getX() + getWidth() < f || this.location.getY() + getWidth() < f) {
                return;
            }
            float left = this.location.getX() + (this.width - this.rotationWidth);
            float right = this.location.getX() + this.rotationWidth;
            if (left > right) {
                float left2 = left + right;
                right = left2 - right;
                left = left2 - right;
            }
            this.paint.setAlpha(this.alpha);
            this.rectF.set(left, this.location.getY(), right, this.location.getY() + getWidth());
            canvas.save();
            canvas.rotate(this.rotation, this.rectF.centerX(), this.rectF.centerY());
            switch (this.shape) {
                case CIRCLE:
                    canvas.drawOval(this.rectF, this.paint);
                    break;
                case RECT:
                    canvas.drawRect(this.rectF, this.paint);
                    break;
            }
            canvas.restore();
        }
    }
}
