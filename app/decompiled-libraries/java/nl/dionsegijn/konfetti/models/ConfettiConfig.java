package nl.dionsegijn.konfetti.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ConfettiConfig.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lnl/dionsegijn/konfetti/models/ConfettiConfig;", "", "fadeOut", "", "timeToLive", "", "(ZJ)V", "getFadeOut", "()Z", "setFadeOut", "(Z)V", "getTimeToLive", "()J", "setTimeToLive", "(J)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "konfetti_release"}, k = 1, mv = {1, 1, 5})
public final /* data */ class ConfettiConfig {
    private boolean fadeOut;
    private long timeToLive;

    public ConfettiConfig() {
        this(false, 0L, 3, null);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ ConfettiConfig copy$default(ConfettiConfig confettiConfig, boolean z, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            z = confettiConfig.fadeOut;
        }
        if ((i & 2) != 0) {
            j = confettiConfig.timeToLive;
        }
        return confettiConfig.copy(z, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getFadeOut() {
        return this.fadeOut;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTimeToLive() {
        return this.timeToLive;
    }

    @NotNull
    public final ConfettiConfig copy(boolean fadeOut, long timeToLive) {
        return new ConfettiConfig(fadeOut, timeToLive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof ConfettiConfig) {
            ConfettiConfig confettiConfig = (ConfettiConfig) other;
            if (this.fadeOut == confettiConfig.fadeOut) {
                if (this.timeToLive == confettiConfig.timeToLive) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.fadeOut;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        long j = this.timeToLive;
        return (r0 * 31) + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "ConfettiConfig(fadeOut=" + this.fadeOut + ", timeToLive=" + this.timeToLive + ")";
    }

    public ConfettiConfig(boolean fadeOut, long timeToLive) {
        this.fadeOut = fadeOut;
        this.timeToLive = timeToLive;
    }

    public /* synthetic */ ConfettiConfig(boolean z, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? 2000L : j);
    }

    public final boolean getFadeOut() {
        return this.fadeOut;
    }

    public final void setFadeOut(boolean z) {
        this.fadeOut = z;
    }

    public final long getTimeToLive() {
        return this.timeToLive;
    }

    public final void setTimeToLive(long j) {
        this.timeToLive = j;
    }
}
