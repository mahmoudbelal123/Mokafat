package nl.dionsegijn.konfetti.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lnl/dionsegijn/konfetti/models/Size;", "", "size", "", "mass", "", "(IF)V", "getMass", "()F", "getSize", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final /* data */ class Size {
    private final float mass;
    private final int size;

    @NotNull
    public static /* bridge */ /* synthetic */ Size copy$default(Size size, int i, float f, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = size.size;
        }
        if ((i2 & 2) != 0) {
            f = size.mass;
        }
        return size.copy(i, f);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getMass() {
        return this.mass;
    }

    @NotNull
    public final Size copy(int size, float mass) {
        return new Size(size, mass);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Size) {
            Size size = (Size) other;
            if ((this.size == size.size) && Float.compare(this.mass, size.mass) == 0) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.size * 31) + Float.floatToIntBits(this.mass);
    }

    public String toString() {
        return "Size(size=" + this.size + ", mass=" + this.mass + ")";
    }

    public Size(int size, float mass) {
        this.size = size;
        this.mass = mass;
    }

    public /* synthetic */ Size(int i, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? 5.0f : f);
    }

    public final float getMass() {
        return this.mass;
    }

    public final int getSize() {
        return this.size;
    }
}
