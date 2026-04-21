package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;

/* JADX INFO: loaded from: classes.dex */
class ShadowDrawableWrapper extends DrawableWrapper {
    static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean mAddPaddingForCorners;
    final RectF mContentBounds;
    float mCornerRadius;
    final Paint mCornerShadowPaint;
    Path mCornerShadowPath;
    private boolean mDirty;
    final Paint mEdgeShadowPaint;
    float mMaxShadowSize;
    private boolean mPrintedShadowClipWarning;
    float mRawMaxShadowSize;
    float mRawShadowSize;
    private float mRotation;
    private final int mShadowEndColor;
    private final int mShadowMiddleColor;
    float mShadowSize;
    private final int mShadowStartColor;

    public ShadowDrawableWrapper(Context context, Drawable content, float radius, float shadowSize, float maxShadowSize) {
        super(content);
        this.mDirty = true;
        this.mAddPaddingForCorners = true;
        this.mPrintedShadowClipWarning = false;
        this.mShadowStartColor = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.mShadowMiddleColor = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.mShadowEndColor = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        this.mCornerShadowPaint = new Paint(5);
        this.mCornerShadowPaint.setStyle(Paint.Style.FILL);
        this.mCornerRadius = Math.round(radius);
        this.mContentBounds = new RectF();
        this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint);
        this.mEdgeShadowPaint.setAntiAlias(false);
        setShadowSize(shadowSize, maxShadowSize);
    }

    private static int toEven(float value) {
        int i = Math.round(value);
        return i % 2 == 1 ? i - 1 : i;
    }

    public void setAddPaddingForCorners(boolean addPaddingForCorners) {
        this.mAddPaddingForCorners = addPaddingForCorners;
        invalidateSelf();
    }

    @Override // android.support.v7.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        super.setAlpha(alpha);
        this.mCornerShadowPaint.setAlpha(alpha);
        this.mEdgeShadowPaint.setAlpha(alpha);
    }

    @Override // android.support.v7.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        this.mDirty = true;
    }

    void setShadowSize(float shadowSize, float maxShadowSize) {
        if (shadowSize < 0.0f || maxShadowSize < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float shadowSize2 = toEven(shadowSize);
        float maxShadowSize2 = toEven(maxShadowSize);
        if (shadowSize2 > maxShadowSize2) {
            shadowSize2 = maxShadowSize2;
            if (!this.mPrintedShadowClipWarning) {
                this.mPrintedShadowClipWarning = true;
            }
        }
        if (this.mRawShadowSize == shadowSize2 && this.mRawMaxShadowSize == maxShadowSize2) {
            return;
        }
        this.mRawShadowSize = shadowSize2;
        this.mRawMaxShadowSize = maxShadowSize2;
        this.mShadowSize = Math.round(SHADOW_MULTIPLIER * shadowSize2);
        this.mMaxShadowSize = maxShadowSize2;
        this.mDirty = true;
        invalidateSelf();
    }

    @Override // android.support.v7.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean getPadding(Rect padding) {
        int vOffset = (int) Math.ceil(calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        int hOffset = (int) Math.ceil(calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        padding.set(hOffset, vOffset, hOffset, vOffset);
        return true;
    }

    public static float calculateVerticalPadding(float maxShadowSize, float cornerRadius, boolean addPaddingForCorners) {
        if (addPaddingForCorners) {
            return (float) (((double) (SHADOW_MULTIPLIER * maxShadowSize)) + ((1.0d - COS_45) * ((double) cornerRadius)));
        }
        return SHADOW_MULTIPLIER * maxShadowSize;
    }

    public static float calculateHorizontalPadding(float maxShadowSize, float cornerRadius, boolean addPaddingForCorners) {
        if (addPaddingForCorners) {
            return (float) (((double) maxShadowSize) + ((1.0d - COS_45) * ((double) cornerRadius)));
        }
        return maxShadowSize;
    }

    @Override // android.support.v7.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public void setCornerRadius(float radius) {
        float radius2 = Math.round(radius);
        if (this.mCornerRadius == radius2) {
            return;
        }
        this.mCornerRadius = radius2;
        this.mDirty = true;
        invalidateSelf();
    }

    @Override // android.support.v7.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mDirty) {
            buildComponents(getBounds());
            this.mDirty = false;
        }
        drawShadow(canvas);
        super.draw(canvas);
    }

    final void setRotation(float rotation) {
        if (this.mRotation != rotation) {
            this.mRotation = rotation;
            invalidateSelf();
        }
    }

    private void drawShadow(Canvas canvas) {
        float shadowScaleBottom;
        float shadowScaleTop;
        float shadowScaleHorizontal;
        int saved;
        float shadowOffsetHorizontal;
        float shadowScaleHorizontal2;
        int rotateSaved = canvas.save();
        canvas.rotate(this.mRotation, this.mContentBounds.centerX(), this.mContentBounds.centerY());
        float edgeShadowTop = (-this.mCornerRadius) - this.mShadowSize;
        float shadowOffset = this.mCornerRadius;
        boolean drawHorizontalEdges = this.mContentBounds.width() - (2.0f * shadowOffset) > 0.0f;
        boolean drawVerticalEdges = this.mContentBounds.height() - (2.0f * shadowOffset) > 0.0f;
        float shadowOffsetTop = this.mRawShadowSize - (this.mRawShadowSize * SHADOW_TOP_SCALE);
        float shadowOffsetHorizontal2 = this.mRawShadowSize - (this.mRawShadowSize * SHADOW_HORIZ_SCALE);
        float shadowOffsetBottom = this.mRawShadowSize - (this.mRawShadowSize * SHADOW_BOTTOM_SCALE);
        float shadowScaleHorizontal3 = shadowOffset / (shadowOffset + shadowOffsetHorizontal2);
        float shadowScaleTop2 = shadowOffset / (shadowOffset + shadowOffsetTop);
        float shadowScaleBottom2 = shadowOffset / (shadowOffset + shadowOffsetBottom);
        int saved2 = canvas.save();
        canvas.translate(this.mContentBounds.left + shadowOffset, this.mContentBounds.top + shadowOffset);
        canvas.scale(shadowScaleHorizontal3, shadowScaleTop2);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas.scale(SHADOW_BOTTOM_SCALE / shadowScaleHorizontal3, SHADOW_BOTTOM_SCALE);
            float fWidth = this.mContentBounds.width() - (2.0f * shadowOffset);
            float f = -this.mCornerRadius;
            Paint paint = this.mEdgeShadowPaint;
            saved = saved2;
            shadowScaleBottom = shadowScaleBottom2;
            shadowScaleTop = shadowScaleTop2;
            shadowScaleHorizontal = shadowScaleHorizontal3;
            shadowOffsetHorizontal = SHADOW_BOTTOM_SCALE;
            canvas.drawRect(0.0f, edgeShadowTop, fWidth, f, paint);
        } else {
            shadowScaleBottom = shadowScaleBottom2;
            shadowScaleTop = shadowScaleTop2;
            shadowScaleHorizontal = shadowScaleHorizontal3;
            saved = saved2;
            shadowOffsetHorizontal = 1.0f;
        }
        canvas.restoreToCount(saved);
        int saved3 = canvas.save();
        canvas.translate(this.mContentBounds.right - shadowOffset, this.mContentBounds.bottom - shadowOffset);
        float shadowScaleHorizontal4 = shadowScaleHorizontal;
        canvas.scale(shadowScaleHorizontal4, shadowScaleBottom);
        canvas.rotate(180.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas.scale(shadowOffsetHorizontal / shadowScaleHorizontal4, shadowOffsetHorizontal);
            shadowScaleHorizontal2 = shadowScaleHorizontal4;
            canvas.drawRect(0.0f, edgeShadowTop, this.mContentBounds.width() - (2.0f * shadowOffset), (-this.mCornerRadius) + this.mShadowSize, this.mEdgeShadowPaint);
        } else {
            shadowScaleHorizontal2 = shadowScaleHorizontal4;
        }
        canvas.restoreToCount(saved3);
        int saved4 = canvas.save();
        canvas.translate(this.mContentBounds.left + shadowOffset, this.mContentBounds.bottom - shadowOffset);
        canvas.scale(shadowScaleHorizontal2, shadowScaleBottom);
        canvas.rotate(270.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawVerticalEdges) {
            canvas.scale(SHADOW_BOTTOM_SCALE / shadowScaleBottom, SHADOW_BOTTOM_SCALE);
            canvas.drawRect(0.0f, edgeShadowTop, this.mContentBounds.height() - (2.0f * shadowOffset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved4);
        int saved5 = canvas.save();
        canvas.translate(this.mContentBounds.right - shadowOffset, this.mContentBounds.top + shadowOffset);
        float shadowScaleTop3 = shadowScaleTop;
        canvas.scale(shadowScaleHorizontal2, shadowScaleTop3);
        canvas.rotate(90.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawVerticalEdges) {
            canvas.scale(SHADOW_BOTTOM_SCALE / shadowScaleTop3, SHADOW_BOTTOM_SCALE);
            canvas.drawRect(0.0f, edgeShadowTop, this.mContentBounds.height() - (2.0f * shadowOffset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved5);
        canvas.restoreToCount(rotateSaved);
    }

    private void buildShadowCorners() {
        int i;
        RectF innerBounds = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
        RectF outerBounds = new RectF(innerBounds);
        outerBounds.inset(-this.mShadowSize, -this.mShadowSize);
        if (this.mCornerShadowPath == null) {
            this.mCornerShadowPath = new Path();
        } else {
            this.mCornerShadowPath.reset();
        }
        this.mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(outerBounds, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(innerBounds, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        float shadowRadius = -outerBounds.top;
        if (shadowRadius > 0.0f) {
            float startRatio = this.mCornerRadius / shadowRadius;
            float midRatio = startRatio + ((SHADOW_BOTTOM_SCALE - startRatio) / 2.0f);
            i = 3;
            this.mCornerShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, shadowRadius, new int[]{0, this.mShadowStartColor, this.mShadowMiddleColor, this.mShadowEndColor}, new float[]{0.0f, startRatio, midRatio, SHADOW_BOTTOM_SCALE}, Shader.TileMode.CLAMP));
        } else {
            i = 3;
        }
        Paint paint = this.mEdgeShadowPaint;
        float f = innerBounds.top;
        float f2 = outerBounds.top;
        int[] iArr = new int[i];
        iArr[0] = this.mShadowStartColor;
        iArr[1] = this.mShadowMiddleColor;
        iArr[2] = this.mShadowEndColor;
        float[] fArr = new float[i];
        // fill-array-data instruction
        fArr[0] = 0.0f;
        fArr[1] = 0.5f;
        fArr[2] = 1.0f;
        paint.setShader(new LinearGradient(0.0f, f, 0.0f, f2, iArr, fArr, Shader.TileMode.CLAMP));
        this.mEdgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect bounds) {
        float verticalOffset = this.mRawMaxShadowSize * SHADOW_MULTIPLIER;
        this.mContentBounds.set(bounds.left + this.mRawMaxShadowSize, bounds.top + verticalOffset, bounds.right - this.mRawMaxShadowSize, bounds.bottom - verticalOffset);
        getWrappedDrawable().setBounds((int) this.mContentBounds.left, (int) this.mContentBounds.top, (int) this.mContentBounds.right, (int) this.mContentBounds.bottom);
        buildShadowCorners();
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public void setShadowSize(float size) {
        setShadowSize(size, this.mRawMaxShadowSize);
    }

    public void setMaxShadowSize(float size) {
        setShadowSize(this.mRawShadowSize, size);
    }

    public float getShadowSize() {
        return this.mRawShadowSize;
    }

    public float getMaxShadowSize() {
        return this.mRawMaxShadowSize;
    }

    public float getMinWidth() {
        float content = Math.max(this.mRawMaxShadowSize, this.mCornerRadius + (this.mRawMaxShadowSize / 2.0f)) * 2.0f;
        return (this.mRawMaxShadowSize * 2.0f) + content;
    }

    public float getMinHeight() {
        float content = Math.max(this.mRawMaxShadowSize, this.mCornerRadius + ((this.mRawMaxShadowSize * SHADOW_MULTIPLIER) / 2.0f)) * 2.0f;
        return (this.mRawMaxShadowSize * SHADOW_MULTIPLIER * 2.0f) + content;
    }
}
