package nl.dionsegijn.konfetti.models;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: LocationModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0012J\u001d\u0010\u0013\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u0006J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u0006R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0016"}, d2 = {"Lnl/dionsegijn/konfetti/models/LocationModule;", "", "random", "Ljava/util/Random;", "(Ljava/util/Random;)V", "maxX", "", "Ljava/lang/Float;", "maxY", "minX", "minY", "x", "getX", "()F", "y", "getY", "betweenX", "", "(FLjava/lang/Float;)V", "betweenY", "setX", "setY", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final class LocationModule {
    private Float maxX;
    private Float maxY;
    private float minX;
    private float minY;
    private final Random random;

    public LocationModule(@NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(random, "random");
        this.random = random;
    }

    public final float getX() {
        if (this.maxX == null) {
            return this.minX;
        }
        float fNextFloat = this.random.nextFloat();
        Float f = this.maxX;
        if (f == null) {
            Intrinsics.throwNpe();
        }
        return (fNextFloat * (f.floatValue() - this.minX)) + this.minX;
    }

    public final float getY() {
        if (this.maxY == null) {
            return this.minY;
        }
        float fNextFloat = this.random.nextFloat();
        Float f = this.maxY;
        if (f == null) {
            Intrinsics.throwNpe();
        }
        return (fNextFloat * (f.floatValue() - this.minY)) + this.minY;
    }

    public final void betweenX(float minX, @Nullable Float maxX) {
        this.minX = minX;
        this.maxX = maxX;
    }

    public final void setX(float x) {
        this.minX = x;
    }

    public final void betweenY(float minY, @Nullable Float maxY) {
        this.minY = minY;
        this.maxY = maxY;
    }

    public final void setY(float x) {
        this.minY = x;
    }
}
