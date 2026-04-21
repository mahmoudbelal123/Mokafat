package com.google.android.gms.common.images.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public final class CrossFadingDrawable extends Drawable implements Drawable.Callback {
    private int mAlpha;
    private int mFrom;
    private boolean zzpl;
    private int zzpw;
    private long zzpx;
    private int zzpy;
    private int zzpz;
    private int zzqa;
    private boolean zzqb;
    private zzb zzqc;
    private Drawable zzqd;
    private Drawable zzqe;
    private boolean zzqf;
    private boolean zzqg;
    private boolean zzqh;
    private int zzqi;

    /* JADX INFO: Access modifiers changed from: private */
    static final class zza extends Drawable {
        private static final zza zzqj = new zza();
        private static final com.google.android.gms.common.images.internal.zzb zzqk = new com.google.android.gms.common.images.internal.zzb();

        private zza() {
        }

        @Override // android.graphics.drawable.Drawable
        public final void draw(Canvas canvas) {
        }

        @Override // android.graphics.drawable.Drawable
        public final Drawable.ConstantState getConstantState() {
            return zzqk;
        }

        @Override // android.graphics.drawable.Drawable
        public final int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public final void setAlpha(int i) {
        }

        @Override // android.graphics.drawable.Drawable
        public final void setColorFilter(ColorFilter colorFilter) {
        }
    }

    static final class zzb extends Drawable.ConstantState {
        int mChangingConfigurations;
        int zzql;

        zzb(zzb zzbVar) {
            if (zzbVar != null) {
                this.mChangingConfigurations = zzbVar.mChangingConfigurations;
                this.zzql = zzbVar.zzql;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return new CrossFadingDrawable(this);
        }
    }

    public CrossFadingDrawable(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zza.zzqj : drawable;
        this.zzqd = drawable;
        drawable.setCallback(this);
        zzb zzbVar = this.zzqc;
        zzbVar.zzql = drawable.getChangingConfigurations() | zzbVar.zzql;
        drawable2 = drawable2 == null ? zza.zzqj : drawable2;
        this.zzqe = drawable2;
        drawable2.setCallback(this);
        zzb zzbVar2 = this.zzqc;
        zzbVar2.zzql = drawable2.getChangingConfigurations() | zzbVar2.zzql;
    }

    CrossFadingDrawable(zzb zzbVar) {
        this.zzpw = 0;
        this.zzpz = 255;
        this.mAlpha = 0;
        this.zzpl = true;
        this.zzqc = new zzb(zzbVar);
    }

    public final boolean canConstantState() {
        if (!this.zzqf) {
            this.zzqg = (this.zzqd.getConstantState() == null || this.zzqe.getConstantState() == null) ? false : true;
            this.zzqf = true;
        }
        return this.zzqg;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        switch (this.zzpw) {
            case 1:
                this.zzpx = SystemClock.uptimeMillis();
                this.zzpw = 2;
                z = false;
                break;
            case 2:
                if (this.zzpx >= 0) {
                    float fUptimeMillis = (SystemClock.uptimeMillis() - this.zzpx) / this.zzqa;
                    z = fUptimeMillis >= 1.0f;
                    if (z) {
                        this.zzpw = 0;
                    }
                    this.mAlpha = (int) (0.0f + (this.zzpy * Math.min(fUptimeMillis, 1.0f)));
                }
                break;
        }
        int i = this.mAlpha;
        boolean z = this.zzpl;
        Drawable drawable = this.zzqd;
        Drawable drawable2 = this.zzqe;
        if (z) {
            if (!z || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zzpz) {
                drawable2.setAlpha(this.zzpz);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z) {
            drawable.setAlpha(this.zzpz - i);
        }
        drawable.draw(canvas);
        if (z) {
            drawable.setAlpha(this.zzpz);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zzpz);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzqc.mChangingConfigurations | this.zzqc.zzql;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zzqc.mChangingConfigurations = getChangingConfigurations();
        return this.zzqc;
    }

    public final Drawable getEndDrawable() {
        return this.zzqe;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.zzqd.getIntrinsicHeight(), this.zzqe.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.zzqd.getIntrinsicWidth(), this.zzqe.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if (!this.zzqh) {
            this.zzqi = Drawable.resolveOpacity(this.zzqd.getOpacity(), this.zzqe.getOpacity());
            this.zzqh = true;
        }
        return this.zzqi;
    }

    public final Drawable getStartDrawable() {
        return this.zzqd;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.zzqb && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zzqd.mutate();
            this.zzqe.mutate();
            this.zzqb = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        this.zzqd.setBounds(rect);
        this.zzqe.setBounds(rect);
    }

    public final void resetTransition() {
        this.mAlpha = 0;
        this.zzpw = 0;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.mAlpha == this.zzpz) {
            this.mAlpha = i;
        }
        this.zzpz = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.zzqd.setColorFilter(colorFilter);
        this.zzqe.setColorFilter(colorFilter);
    }

    public final void setCrossFadeEnabled(boolean z) {
        this.zzpl = z;
    }

    public final void startTransition(int i) {
        this.mFrom = 0;
        this.zzpy = this.zzpz;
        this.mAlpha = 0;
        this.zzqa = i;
        this.zzpw = 1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }
}
