package com.dx.dxloadingbutton.lib;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: loaded from: classes.dex */
public class LoadingButton extends View {
    private static final int STATE_ANIMATION_FAILED = 6;
    private static final int STATE_ANIMATION_LOADING = 3;
    private static final int STATE_ANIMATION_STEP1 = 1;
    private static final int STATE_ANIMATION_STEP2 = 2;
    private static final int STATE_ANIMATION_SUCCESS = 5;
    private static final int STATE_BUTTON = 0;
    private static final int STATE_STOP_LOADING = 4;
    private int height;
    private int mAngle;
    private AnimationEndListener mAnimationEndListener;
    private RectF mArcRectF;
    private float mButtonCorner;
    private RectF mButtonRectF;
    private int mColorPrimary;
    private int mCurrentState;
    private int mDegree;
    private float mDensity;
    private int mDisabledBgColor;
    private int mDisabledTextColor;
    private int mEndAngle;
    private Path mFailedPath;
    private Path mFailedPath2;
    private float[] mFailedPathIntervals;
    private float mFailedPathLength;
    private AnimatorSet mLoadingAnimatorSet;
    private Matrix mMatrix;
    private float mPadding;
    private Paint mPaint;
    private Path mPath;
    private Paint mPathEffectPaint;
    private Paint mPathEffectPaint2;
    private int mRadius;
    private float mRippleAlpha;
    private int mRippleColor;
    private float mRippleRadius;
    private int mScaleHeight;
    private int mScaleWidth;
    private Paint mStrokePaint;
    private Path mSuccessPath;
    private float[] mSuccessPathIntervals;
    private float mSuccessPathLength;
    private String mText;
    private int mTextColor;
    private float mTextHeight;
    private Paint mTextPaint;
    private float mTextWidth;
    private float mTouchX;
    private float mTouchY;
    private boolean resetAfterFailed;
    private Paint ripplePaint;
    private int width;

    public interface AnimationEndListener {
        void onAnimationEnd(AnimationType animationType);
    }

    public enum AnimationType {
        SUCCESSFUL,
        FAILED
    }

    public LoadingButton(Context context) {
        super(context);
        this.mMatrix = new Matrix();
        init(context, null);
    }

    public LoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMatrix = new Matrix();
        init(context, attrs);
    }

    public LoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMatrix = new Matrix();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0);
            this.mColorPrimary = ta.getInt(R.styleable.LoadingButton_lb_btnColor, -16776961);
            this.mDisabledBgColor = ta.getColor(R.styleable.LoadingButton_lb_btnDisabledColor, -3355444);
            this.mDisabledTextColor = ta.getColor(R.styleable.LoadingButton_lb_disabledTextColor, -12303292);
            String text = ta.getString(R.styleable.LoadingButton_lb_btnText);
            this.mText = text == null ? "" : text;
            this.mTextColor = ta.getColor(R.styleable.LoadingButton_lb_textColor, -1);
            this.resetAfterFailed = ta.getBoolean(R.styleable.LoadingButton_lb_resetAfterFailed, true);
            this.mRippleColor = ta.getColor(R.styleable.LoadingButton_lb_btnRippleColor, ViewCompat.MEASURED_STATE_MASK);
            this.mRippleAlpha = ta.getFloat(R.styleable.LoadingButton_lb_btnRippleAlpha, 0.3f);
            ta.recycle();
        }
        this.mCurrentState = 0;
        this.mScaleWidth = 0;
        this.mScaleHeight = 0;
        this.mDegree = 0;
        this.mAngle = 0;
        this.mDensity = getResources().getDisplayMetrics().density;
        this.mButtonCorner = this.mDensity * 2.0f;
        this.mPadding = 6.0f * this.mDensity;
        this.mPaint = new Paint();
        setLayerType(1, this.mPaint);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mColorPrimary);
        this.mPaint.setStyle(Paint.Style.FILL);
        setShadowDepth1();
        this.ripplePaint = new Paint();
        this.ripplePaint.setAntiAlias(true);
        this.ripplePaint.setColor(this.mRippleColor);
        this.ripplePaint.setAlpha((int) (this.mRippleAlpha * 255.0f));
        this.ripplePaint.setStyle(Paint.Style.FILL);
        this.mStrokePaint = new Paint();
        this.mStrokePaint.setAntiAlias(true);
        this.mStrokePaint.setColor(this.mColorPrimary);
        this.mStrokePaint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setStrokeWidth(this.mDensity * 2.0f);
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setTextSize(16.0f * this.mDensity);
        this.mTextPaint.setFakeBoldText(true);
        this.mTextWidth = this.mTextPaint.measureText(this.mText);
        Rect bounds = new Rect();
        this.mTextPaint.getTextBounds(this.mText, 0, this.mText.length(), bounds);
        this.mTextHeight = bounds.height();
        this.mPathEffectPaint = new Paint();
        this.mPathEffectPaint.setAntiAlias(true);
        this.mPathEffectPaint.setColor(this.mColorPrimary);
        this.mPathEffectPaint.setStyle(Paint.Style.STROKE);
        this.mPathEffectPaint.setStrokeWidth(this.mDensity * 2.0f);
        this.mPathEffectPaint2 = new Paint();
        this.mPathEffectPaint2.setAntiAlias(true);
        this.mPathEffectPaint2.setColor(this.mColorPrimary);
        this.mPathEffectPaint2.setStyle(Paint.Style.STROKE);
        this.mPathEffectPaint2.setStrokeWidth(2.0f * this.mDensity);
        this.mPath = new Path();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureDimension((int) (88.0f * this.mDensity), widthMeasureSpec);
        int height = measureDimension((int) (56.0f * this.mDensity), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        this.mRadius = ((int) (this.height - (this.mPadding * 2.0f))) / 2;
        if (this.mButtonRectF == null) {
            this.mButtonRectF = new RectF();
            this.mButtonRectF.top = this.mPadding;
            this.mButtonRectF.bottom = this.height - this.mPadding;
            this.mArcRectF = new RectF((this.width / 2) - this.mRadius, this.mPadding, (this.width / 2) + this.mRadius, this.height - this.mPadding);
        }
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return true;
        }
        switch (event.getAction()) {
            case 0:
                this.mTouchX = event.getX();
                this.mTouchY = event.getY();
                playRippleAnimation(true);
                return true;
            case 1:
                if (event.getX() > this.mButtonRectF.left && event.getX() < this.mButtonRectF.right && event.getY() > this.mButtonRectF.top && event.getY() < this.mButtonRectF.bottom) {
                    playRippleAnimation(false);
                } else {
                    this.mTouchX = 0.0f;
                    this.mTouchY = 0.0f;
                    this.mRippleRadius = 0.0f;
                    invalidate();
                }
                return true;
            default:
                return true;
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (this.mCurrentState == 0) {
            checkEnabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEnabled() {
        if (isEnabled()) {
            this.mPaint.setColor(this.mColorPrimary);
            this.mTextPaint.setColor(this.mTextColor);
        } else {
            this.mPaint.setColor(this.mDisabledBgColor);
            this.mTextPaint.setColor(this.mDisabledTextColor);
        }
        invalidate();
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == 1073741824) {
            return specSize;
        }
        if (specMode == Integer.MIN_VALUE) {
            int result = Math.min(defaultSize, specSize);
            return result;
        }
        return defaultSize;
    }

    public void startLoading() {
        if (this.mCurrentState == 6 && !this.resetAfterFailed) {
            scaleFailedPath();
        } else if (this.mCurrentState == 0) {
            this.mCurrentState = 1;
            removeShadow();
            playStartAnimation(false);
        }
    }

    public void loadingSuccessful() {
        if (this.mLoadingAnimatorSet != null && this.mLoadingAnimatorSet.isStarted()) {
            this.mLoadingAnimatorSet.end();
            this.mCurrentState = 4;
            playSuccessAnimation();
        }
    }

    public void loadingFailed() {
        if (this.mLoadingAnimatorSet != null && this.mLoadingAnimatorSet.isStarted()) {
            this.mLoadingAnimatorSet.end();
            this.mCurrentState = 4;
            playFailedAnimation();
        }
    }

    public void cancelLoading() {
        if (this.mCurrentState != 3) {
            return;
        }
        cancel();
    }

    public void reset() {
        if (this.mCurrentState == 5) {
            scaleSuccessPath();
        }
        if (this.mCurrentState == 6) {
            scaleFailedPath();
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != null) {
            this.mTextPaint.setTypeface(typeface);
            invalidate();
        }
    }

    public void setText(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        this.mText = text;
        this.mTextWidth = this.mTextPaint.measureText(this.mText);
        Rect bounds = new Rect();
        this.mTextPaint.getTextBounds(this.mText, 0, this.mText.length(), bounds);
        this.mTextHeight = bounds.height();
        invalidate();
    }

    public void setTextSize(int size) {
        this.mTextPaint.setTextSize(size * this.mDensity);
        this.mTextWidth = this.mTextPaint.measureText(this.mText);
        invalidate();
    }

    public void setTextColor(int color) {
        this.mTextPaint.setColor(color);
        invalidate();
    }

    public void setResetAfterFailed(boolean resetAfterFailed) {
        this.resetAfterFailed = resetAfterFailed;
    }

    public boolean isResetAfterFailed() {
        return this.resetAfterFailed;
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public void setAnimationEndListener(AnimationEndListener animationEndListener) {
        this.mAnimationEndListener = animationEndListener;
    }

    private void createSuccessPath() {
        if (this.mSuccessPath != null) {
            this.mSuccessPath.reset();
        } else {
            this.mSuccessPath = new Path();
        }
        float mLineWith = this.mDensity * 2.0f;
        float left = ((this.width / 2) - this.mRadius) + (this.mRadius / 3) + mLineWith;
        float top = this.mPadding + (this.mRadius / 2) + mLineWith;
        float right = (((this.width / 2) + this.mRadius) - mLineWith) - (this.mRadius / 3);
        float bottom = ((this.mRadius + mLineWith) * 1.5f) + (this.mPadding / 2.0f);
        float xPoint = (this.width / 2) - (this.mRadius / 6);
        this.mSuccessPath = new Path();
        this.mSuccessPath.moveTo(left, this.mPadding + this.mRadius + mLineWith);
        this.mSuccessPath.lineTo(xPoint, bottom);
        this.mSuccessPath.lineTo(right, top);
        PathMeasure measure = new PathMeasure(this.mSuccessPath, false);
        this.mSuccessPathLength = measure.getLength();
        this.mSuccessPathIntervals = new float[]{this.mSuccessPathLength, this.mSuccessPathLength};
    }

    private void createFailedPath() {
        if (this.mFailedPath != null) {
            this.mFailedPath.reset();
            this.mFailedPath2.reset();
        } else {
            this.mFailedPath = new Path();
            this.mFailedPath2 = new Path();
        }
        float left = ((this.width / 2) - this.mRadius) + (this.mRadius / 2);
        float top = (this.mRadius / 2) + this.mPadding;
        this.mFailedPath.moveTo(left, top);
        this.mFailedPath.lineTo(this.mRadius + left, this.mRadius + top);
        this.mFailedPath2.moveTo((this.width / 2) + (this.mRadius / 2), top);
        this.mFailedPath2.lineTo(((this.width / 2) - this.mRadius) + (this.mRadius / 2), this.mRadius + top);
        PathMeasure measure = new PathMeasure(this.mFailedPath, false);
        this.mFailedPathLength = measure.getLength();
        this.mFailedPathIntervals = new float[]{this.mFailedPathLength, this.mFailedPathLength};
        PathEffect PathEffect = new DashPathEffect(this.mFailedPathIntervals, this.mFailedPathLength);
        this.mPathEffectPaint2.setPathEffect(PathEffect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShadowDepth1() {
        this.mPaint.setShadowLayer(this.mDensity * 1.0f, 0.0f, 1.0f * this.mDensity, 520093696);
    }

    private void setShadowDepth2() {
        this.mPaint.setShadowLayer(this.mDensity * 2.0f, 0.0f, 2.0f * this.mDensity, 520093696);
    }

    private void removeShadow() {
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mColorPrimary);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (this.mCurrentState) {
            case 0:
            case 1:
                float cornerRadius = ((this.mRadius - this.mButtonCorner) * (this.mScaleWidth / ((this.width / 2) - (this.height / 2)))) + this.mButtonCorner;
                this.mButtonRectF.left = this.mScaleWidth;
                this.mButtonRectF.right = this.width - this.mScaleWidth;
                canvas.drawRoundRect(this.mButtonRectF, cornerRadius, cornerRadius, this.mPaint);
                if (this.mCurrentState == 0) {
                    canvas.drawText(this.mText, (this.width - this.mTextWidth) / 2.0f, ((this.height - this.mTextHeight) / 2.0f) + (this.mPadding * 2.0f), this.mTextPaint);
                    if (this.mTouchX > 0.0f || this.mTouchY > 0.0f) {
                        canvas.clipRect(0.0f, this.mPadding, this.width, this.height - this.mPadding);
                        canvas.drawCircle(this.mTouchX, this.mTouchY, this.mRippleRadius, this.ripplePaint);
                    }
                }
                break;
            case 2:
                canvas.drawCircle(this.width / 2, this.height / 2, this.mRadius - this.mScaleHeight, this.mPaint);
                canvas.drawCircle(this.width / 2, this.height / 2, this.mRadius - this.mDensity, this.mStrokePaint);
                break;
            case 3:
                this.mPath.reset();
                this.mPath.addArc(this.mArcRectF, 270 + (this.mAngle / 2), 360 - this.mAngle);
                if (this.mAngle != 0) {
                    this.mMatrix.setRotate(this.mDegree, this.width / 2, this.height / 2);
                    this.mPath.transform(this.mMatrix);
                    this.mDegree += 10;
                }
                canvas.drawPath(this.mPath, this.mStrokePaint);
                break;
            case 4:
                this.mPath.reset();
                this.mPath.addArc(this.mArcRectF, 270 + (this.mAngle / 2), this.mEndAngle);
                if (this.mEndAngle != 360) {
                    this.mMatrix.setRotate(this.mDegree, this.width / 2, this.height / 2);
                    this.mPath.transform(this.mMatrix);
                    this.mDegree += 10;
                }
                canvas.drawPath(this.mPath, this.mStrokePaint);
                break;
            case 5:
                canvas.drawPath(this.mSuccessPath, this.mPathEffectPaint);
                canvas.drawCircle(this.width / 2, this.height / 2, this.mRadius - this.mDensity, this.mStrokePaint);
                break;
            case 6:
                canvas.drawPath(this.mFailedPath, this.mPathEffectPaint);
                canvas.drawPath(this.mFailedPath2, this.mPathEffectPaint2);
                canvas.drawCircle(this.width / 2, this.height / 2, this.mRadius - this.mDensity, this.mStrokePaint);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playStartAnimation(final boolean isReverse) {
        int[] iArr = new int[2];
        iArr[0] = isReverse ? (this.width / 2) - (this.height / 2) : 0;
        iArr[1] = isReverse ? 0 : (this.width / 2) - (this.height / 2);
        ValueAnimator animator = ValueAnimator.ofInt(iArr);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadingButton.this.mScaleWidth = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LoadingButton.this.invalidate();
            }
        });
        animator.setDuration(400L);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setStartDelay(100L);
        animator.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                LoadingButton.this.mCurrentState = isReverse ? 0 : 2;
                if (LoadingButton.this.mCurrentState == 0) {
                    LoadingButton.this.setShadowDepth1();
                    LoadingButton.this.invalidate();
                }
            }
        });
        int[] iArr2 = new int[2];
        iArr2[0] = isReverse ? this.mRadius : 0;
        iArr2[1] = isReverse ? 0 : this.mRadius;
        ValueAnimator animator2 = ValueAnimator.ofInt(iArr2);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadingButton.this.mScaleHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LoadingButton.this.invalidate();
            }
        });
        animator2.setDuration(240L);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator3) {
                LoadingButton.this.mCurrentState = isReverse ? 1 : 3;
                if (!isReverse) {
                    LoadingButton.this.checkEnabled();
                }
            }
        });
        ValueAnimator loadingAnimator = ValueAnimator.ofInt(30, 300);
        loadingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadingButton.this.mAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LoadingButton.this.invalidate();
            }
        });
        loadingAnimator.setDuration(1000L);
        loadingAnimator.setRepeatCount(-1);
        loadingAnimator.setRepeatMode(2);
        loadingAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (this.mLoadingAnimatorSet != null) {
            this.mLoadingAnimatorSet.cancel();
        }
        this.mLoadingAnimatorSet = new AnimatorSet();
        if (isReverse) {
            this.mLoadingAnimatorSet.playSequentially(animator2, animator);
            checkEnabled();
        } else {
            this.mLoadingAnimatorSet.playSequentially(animator, animator2, loadingAnimator);
        }
        this.mLoadingAnimatorSet.start();
    }

    private void playSuccessAnimation() {
        createSuccessPath();
        ValueAnimator animator = ValueAnimator.ofInt(360 - this.mAngle, 360);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadingButton.this.mEndAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LoadingButton.this.invalidate();
            }
        });
        animator.setDuration(240L);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.7
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                LoadingButton.this.mCurrentState = 5;
            }
        });
        ValueAnimator successAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        successAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PathEffect PathEffect = new DashPathEffect(LoadingButton.this.mSuccessPathIntervals, LoadingButton.this.mSuccessPathLength - (LoadingButton.this.mSuccessPathLength * value));
                LoadingButton.this.mPathEffectPaint.setPathEffect(PathEffect);
                LoadingButton.this.invalidate();
            }
        });
        successAnimator.setDuration(500L);
        successAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animator, successAnimator);
        set.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.9
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                if (LoadingButton.this.mAnimationEndListener != null) {
                    LoadingButton.this.mAnimationEndListener.onAnimationEnd(AnimationType.SUCCESSFUL);
                }
            }
        });
        set.start();
    }

    private void playFailedAnimation() {
        createFailedPath();
        ValueAnimator animator = ValueAnimator.ofInt(360 - this.mAngle, 360);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.10
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadingButton.this.mEndAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LoadingButton.this.invalidate();
            }
        });
        animator.setDuration(240L);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.11
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                LoadingButton.this.mCurrentState = 6;
            }
        });
        ValueAnimator failedAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        failedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.12
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PathEffect PathEffect = new DashPathEffect(LoadingButton.this.mFailedPathIntervals, LoadingButton.this.mFailedPathLength - (LoadingButton.this.mFailedPathLength * value));
                LoadingButton.this.mPathEffectPaint.setPathEffect(PathEffect);
                LoadingButton.this.invalidate();
            }
        });
        failedAnimator.setDuration(300L);
        failedAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        ValueAnimator failedAnimator2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        failedAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.13
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PathEffect PathEffect = new DashPathEffect(LoadingButton.this.mFailedPathIntervals, LoadingButton.this.mFailedPathLength - (LoadingButton.this.mFailedPathLength * value));
                LoadingButton.this.mPathEffectPaint2.setPathEffect(PathEffect);
                LoadingButton.this.invalidate();
            }
        });
        failedAnimator2.setDuration(300L);
        failedAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animator, failedAnimator, failedAnimator2);
        set.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.14
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                if (!LoadingButton.this.resetAfterFailed) {
                    if (LoadingButton.this.mAnimationEndListener != null) {
                        LoadingButton.this.mAnimationEndListener.onAnimationEnd(AnimationType.FAILED);
                        return;
                    }
                    return;
                }
                LoadingButton.this.postDelayed(new Runnable() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.14.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LoadingButton.this.scaleFailedPath();
                    }
                }, 1000L);
            }
        });
        set.start();
    }

    private void cancel() {
        this.mCurrentState = 4;
        ValueAnimator animator = ValueAnimator.ofInt(360 - this.mAngle, 360);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.15
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadingButton.this.mEndAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LoadingButton.this.invalidate();
            }
        });
        animator.setDuration(240L);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.16
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                LoadingButton.this.mCurrentState = 2;
                LoadingButton.this.playStartAnimation(true);
            }
        });
        animator.start();
    }

    private void scaleSuccessPath() {
        final Matrix scaleMatrix = new Matrix();
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1.0f, 0.0f);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.17
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                scaleMatrix.setScale(value, value, LoadingButton.this.width / 2, LoadingButton.this.height / 2);
                LoadingButton.this.mSuccessPath.transform(scaleMatrix);
                LoadingButton.this.invalidate();
            }
        });
        scaleAnimator.setDuration(300L);
        scaleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimator.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.18
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LoadingButton.this.mCurrentState = 2;
                LoadingButton.this.playStartAnimation(true);
            }
        });
        scaleAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scaleFailedPath() {
        final Matrix scaleMatrix = new Matrix();
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1.0f, 0.0f);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.19
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                scaleMatrix.setScale(value, value, LoadingButton.this.width / 2, LoadingButton.this.height / 2);
                LoadingButton.this.mFailedPath.transform(scaleMatrix);
                LoadingButton.this.mFailedPath2.transform(scaleMatrix);
                LoadingButton.this.invalidate();
            }
        });
        scaleAnimator.setDuration(300L);
        scaleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimator.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.20
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LoadingButton.this.mCurrentState = 2;
                LoadingButton.this.playStartAnimation(true);
            }
        });
        scaleAnimator.start();
    }

    private void playRippleAnimation(boolean isTouchDown) {
        setShadowDepth2();
        float[] fArr = new float[2];
        fArr[0] = isTouchDown ? 0.0f : this.width / 2;
        fArr[1] = isTouchDown ? this.width / 2 : this.width;
        ValueAnimator rippleAnimator = ValueAnimator.ofFloat(fArr);
        rippleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.21
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadingButton.this.mRippleRadius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LoadingButton.this.invalidate();
            }
        });
        rippleAnimator.setDuration(240L);
        rippleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (!isTouchDown) {
            rippleAnimator.addListener(new AnimatorEndListener() { // from class: com.dx.dxloadingbutton.lib.LoadingButton.22
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    LoadingButton.this.performClick();
                    LoadingButton.this.mTouchX = 0.0f;
                    LoadingButton.this.mTouchY = 0.0f;
                    LoadingButton.this.mRippleRadius = 0.0f;
                    LoadingButton.this.invalidate();
                }
            });
        }
        rippleAnimator.start();
    }

    private abstract class AnimatorEndListener implements Animator.AnimatorListener {
        private AnimatorEndListener() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }
    }
}
