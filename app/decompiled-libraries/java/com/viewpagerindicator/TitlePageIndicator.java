package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TitlePageIndicator extends View implements PageIndicator {
    private static final float BOLD_FADE_PERCENTAGE = 0.05f;
    private static final String EMPTY_TITLE = "";
    private static final int INVALID_POINTER = -1;
    private static final float SELECTION_FADE_PERCENTAGE = 0.25f;
    private int mActivePointerId;
    private boolean mBoldText;
    private final Rect mBounds;
    private OnCenterItemClickListener mCenterItemClickListener;
    private float mClipPadding;
    private int mColorSelected;
    private int mColorText;
    private int mCurrentPage;
    private float mFooterIndicatorHeight;
    private IndicatorStyle mFooterIndicatorStyle;
    private float mFooterIndicatorUnderlinePadding;
    private float mFooterLineHeight;
    private float mFooterPadding;
    private boolean mIsDragging;
    private float mLastMotionX;
    private LinePosition mLinePosition;
    private ViewPager.OnPageChangeListener mListener;
    private float mPageOffset;
    private final Paint mPaintFooterIndicator;
    private final Paint mPaintFooterLine;
    private final Paint mPaintText;
    private Path mPath;
    private int mScrollState;
    private float mTitlePadding;
    private float mTopPadding;
    private int mTouchSlop;
    private ViewPager mViewPager;

    public interface OnCenterItemClickListener {
        void onCenterItemClick(int i);
    }

    public enum IndicatorStyle {
        None(0),
        Triangle(1),
        Underline(2);

        public final int value;

        IndicatorStyle(int value) {
            this.value = value;
        }

        public static IndicatorStyle fromValue(int value) {
            for (IndicatorStyle style : values()) {
                if (style.value == value) {
                    return style;
                }
            }
            return null;
        }
    }

    public enum LinePosition {
        Bottom(0),
        Top(1);

        public final int value;

        LinePosition(int value) {
            this.value = value;
        }

        public static LinePosition fromValue(int value) {
            for (LinePosition position : values()) {
                if (position.value == value) {
                    return position;
                }
            }
            return null;
        }
    }

    public TitlePageIndicator(Context context) {
        this(context, null);
    }

    public TitlePageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.vpiTitlePageIndicatorStyle);
    }

    public TitlePageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCurrentPage = -1;
        this.mPaintText = new Paint();
        this.mPath = new Path();
        this.mBounds = new Rect();
        this.mPaintFooterLine = new Paint();
        this.mPaintFooterIndicator = new Paint();
        this.mLastMotionX = -1.0f;
        this.mActivePointerId = -1;
        if (isInEditMode()) {
            return;
        }
        Resources res = getResources();
        int defaultFooterColor = res.getColor(R.color.default_title_indicator_footer_color);
        float defaultFooterLineHeight = res.getDimension(R.dimen.default_title_indicator_footer_line_height);
        int defaultFooterIndicatorStyle = res.getInteger(R.integer.default_title_indicator_footer_indicator_style);
        float defaultFooterIndicatorHeight = res.getDimension(R.dimen.default_title_indicator_footer_indicator_height);
        float defaultFooterIndicatorUnderlinePadding = res.getDimension(R.dimen.default_title_indicator_footer_indicator_underline_padding);
        float defaultFooterPadding = res.getDimension(R.dimen.default_title_indicator_footer_padding);
        int defaultLinePosition = res.getInteger(R.integer.default_title_indicator_line_position);
        int defaultSelectedColor = res.getColor(R.color.default_title_indicator_selected_color);
        boolean defaultSelectedBold = res.getBoolean(R.bool.default_title_indicator_selected_bold);
        int defaultTextColor = res.getColor(R.color.default_title_indicator_text_color);
        float defaultTextSize = res.getDimension(R.dimen.default_title_indicator_text_size);
        float defaultTitlePadding = res.getDimension(R.dimen.default_title_indicator_title_padding);
        float defaultClipPadding = res.getDimension(R.dimen.default_title_indicator_clip_padding);
        float defaultTopPadding = res.getDimension(R.dimen.default_title_indicator_top_padding);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitlePageIndicator, defStyle, 0);
        this.mFooterLineHeight = a.getDimension(R.styleable.TitlePageIndicator_footerLineHeight, defaultFooterLineHeight);
        this.mFooterIndicatorStyle = IndicatorStyle.fromValue(a.getInteger(R.styleable.TitlePageIndicator_footerIndicatorStyle, defaultFooterIndicatorStyle));
        this.mFooterIndicatorHeight = a.getDimension(R.styleable.TitlePageIndicator_footerIndicatorHeight, defaultFooterIndicatorHeight);
        this.mFooterIndicatorUnderlinePadding = a.getDimension(R.styleable.TitlePageIndicator_footerIndicatorUnderlinePadding, defaultFooterIndicatorUnderlinePadding);
        this.mFooterPadding = a.getDimension(R.styleable.TitlePageIndicator_footerPadding, defaultFooterPadding);
        this.mLinePosition = LinePosition.fromValue(a.getInteger(R.styleable.TitlePageIndicator_linePosition, defaultLinePosition));
        this.mTopPadding = a.getDimension(R.styleable.TitlePageIndicator_topPadding, defaultTopPadding);
        this.mTitlePadding = a.getDimension(R.styleable.TitlePageIndicator_titlePadding, defaultTitlePadding);
        this.mClipPadding = a.getDimension(R.styleable.TitlePageIndicator_clipPadding, defaultClipPadding);
        this.mColorSelected = a.getColor(R.styleable.TitlePageIndicator_selectedColor, defaultSelectedColor);
        this.mColorText = a.getColor(R.styleable.TitlePageIndicator_android_textColor, defaultTextColor);
        this.mBoldText = a.getBoolean(R.styleable.TitlePageIndicator_selectedBold, defaultSelectedBold);
        float textSize = a.getDimension(R.styleable.TitlePageIndicator_android_textSize, defaultTextSize);
        int footerColor = a.getColor(R.styleable.TitlePageIndicator_footerColor, defaultFooterColor);
        this.mPaintText.setTextSize(textSize);
        this.mPaintText.setAntiAlias(true);
        this.mPaintFooterLine.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaintFooterLine.setStrokeWidth(this.mFooterLineHeight);
        this.mPaintFooterLine.setColor(footerColor);
        this.mPaintFooterIndicator.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaintFooterIndicator.setColor(footerColor);
        Drawable background = a.getDrawable(R.styleable.TitlePageIndicator_android_background);
        if (background != null) {
            setBackgroundDrawable(background);
        }
        a.recycle();
        ViewConfiguration configuration = ViewConfiguration.get(context);
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    public int getFooterColor() {
        return this.mPaintFooterLine.getColor();
    }

    public void setFooterColor(int footerColor) {
        this.mPaintFooterLine.setColor(footerColor);
        this.mPaintFooterIndicator.setColor(footerColor);
        invalidate();
    }

    public float getFooterLineHeight() {
        return this.mFooterLineHeight;
    }

    public void setFooterLineHeight(float footerLineHeight) {
        this.mFooterLineHeight = footerLineHeight;
        this.mPaintFooterLine.setStrokeWidth(this.mFooterLineHeight);
        invalidate();
    }

    public float getFooterIndicatorHeight() {
        return this.mFooterIndicatorHeight;
    }

    public void setFooterIndicatorHeight(float footerTriangleHeight) {
        this.mFooterIndicatorHeight = footerTriangleHeight;
        invalidate();
    }

    public float getFooterIndicatorPadding() {
        return this.mFooterPadding;
    }

    public void setFooterIndicatorPadding(float footerIndicatorPadding) {
        this.mFooterPadding = footerIndicatorPadding;
        invalidate();
    }

    public IndicatorStyle getFooterIndicatorStyle() {
        return this.mFooterIndicatorStyle;
    }

    public void setFooterIndicatorStyle(IndicatorStyle indicatorStyle) {
        this.mFooterIndicatorStyle = indicatorStyle;
        invalidate();
    }

    public LinePosition getLinePosition() {
        return this.mLinePosition;
    }

    public void setLinePosition(LinePosition linePosition) {
        this.mLinePosition = linePosition;
        invalidate();
    }

    public int getSelectedColor() {
        return this.mColorSelected;
    }

    public void setSelectedColor(int selectedColor) {
        this.mColorSelected = selectedColor;
        invalidate();
    }

    public boolean isSelectedBold() {
        return this.mBoldText;
    }

    public void setSelectedBold(boolean selectedBold) {
        this.mBoldText = selectedBold;
        invalidate();
    }

    public int getTextColor() {
        return this.mColorText;
    }

    public void setTextColor(int textColor) {
        this.mPaintText.setColor(textColor);
        this.mColorText = textColor;
        invalidate();
    }

    public float getTextSize() {
        return this.mPaintText.getTextSize();
    }

    public void setTextSize(float textSize) {
        this.mPaintText.setTextSize(textSize);
        invalidate();
    }

    public float getTitlePadding() {
        return this.mTitlePadding;
    }

    public void setTitlePadding(float titlePadding) {
        this.mTitlePadding = titlePadding;
        invalidate();
    }

    public float getTopPadding() {
        return this.mTopPadding;
    }

    public void setTopPadding(float topPadding) {
        this.mTopPadding = topPadding;
        invalidate();
    }

    public float getClipPadding() {
        return this.mClipPadding;
    }

    public void setClipPadding(float clipPadding) {
        this.mClipPadding = clipPadding;
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        this.mPaintText.setTypeface(typeface);
        invalidate();
    }

    public Typeface getTypeface() {
        return this.mPaintText.getTypeface();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int count;
        float offsetPercent;
        int page;
        int right;
        int count2;
        int countMinusOne;
        int countMinusOne2;
        int width;
        int page2;
        float curPageWidth;
        Rect curPageBound;
        int width2;
        super.onDraw(canvas);
        if (this.mViewPager == null || (count = this.mViewPager.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.mCurrentPage == -1 && this.mViewPager != null) {
            this.mCurrentPage = this.mViewPager.getCurrentItem();
        }
        ArrayList<Rect> bounds = calculateAllBounds(this.mPaintText);
        int boundsSize = bounds.size();
        if (this.mCurrentPage >= boundsSize) {
            setCurrentItem(boundsSize - 1);
            return;
        }
        int width3 = count - 1;
        float halfWidth = getWidth() / 2.0f;
        int left = getLeft();
        float leftClip = left + this.mClipPadding;
        int width4 = getWidth();
        int height = getHeight();
        int right2 = left + width4;
        float rightClip = right2 - this.mClipPadding;
        int page3 = this.mCurrentPage;
        if (this.mPageOffset <= 0.5d) {
            offsetPercent = this.mPageOffset;
        } else {
            page3++;
            offsetPercent = 1.0f - this.mPageOffset;
        }
        int page4 = page3;
        float offsetPercent2 = offsetPercent;
        boolean currentSelected = offsetPercent2 <= SELECTION_FADE_PERCENTAGE;
        boolean currentBold = offsetPercent2 <= BOLD_FADE_PERCENTAGE;
        float selectedPercent = (SELECTION_FADE_PERCENTAGE - offsetPercent2) / SELECTION_FADE_PERCENTAGE;
        Rect curPageBound2 = bounds.get(this.mCurrentPage);
        float curPageWidth2 = curPageBound2.right - curPageBound2.left;
        if (curPageBound2.left < leftClip) {
            clipViewOnTheLeft(curPageBound2, curPageWidth2, left);
        }
        if (curPageBound2.right > rightClip) {
            clipViewOnTheRight(curPageBound2, curPageWidth2, right2);
        }
        if (this.mCurrentPage > 0) {
            int i = this.mCurrentPage - 1;
            while (i >= 0) {
                Rect bound = bounds.get(i);
                if (bound.left < leftClip) {
                    curPageWidth = curPageWidth2;
                    int w = bound.right - bound.left;
                    clipViewOnTheLeft(bound, w, left);
                    Rect rightBound = bounds.get(i + 1);
                    curPageBound = curPageBound2;
                    width2 = width4;
                    if (bound.right + this.mTitlePadding > rightBound.left) {
                        bound.left = (int) ((rightBound.left - w) - this.mTitlePadding);
                        bound.right = bound.left + w;
                    }
                } else {
                    curPageWidth = curPageWidth2;
                    curPageBound = curPageBound2;
                    width2 = width4;
                }
                i--;
                curPageWidth2 = curPageWidth;
                curPageBound2 = curPageBound;
                width4 = width2;
            }
        }
        int i2 = width4;
        if (this.mCurrentPage < width3) {
            for (int i3 = this.mCurrentPage + 1; i3 < count; i3++) {
                Rect bound2 = bounds.get(i3);
                if (bound2.right > rightClip) {
                    int w2 = bound2.right - bound2.left;
                    clipViewOnTheRight(bound2, w2, right2);
                    Rect leftBound = bounds.get(i3 - 1);
                    if (bound2.left - this.mTitlePadding < leftBound.right) {
                        bound2.left = (int) (leftBound.right + this.mTitlePadding);
                        bound2.right = bound2.left + w2;
                    }
                }
            }
        }
        int i4 = this.mColorText;
        int colorTextAlpha = i4 >>> 24;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= count) {
                break;
            }
            Rect bound3 = bounds.get(i6);
            if ((bound3.left <= left || bound3.left >= right2) && (bound3.right <= left || bound3.right >= right2)) {
                page = page4;
                right = right2;
                count2 = count;
                countMinusOne = width3;
                countMinusOne2 = i2;
                width = i6;
            } else {
                boolean currentPage = i6 == page4;
                CharSequence pageTitle = getTitle(i6);
                this.mPaintText.setFakeBoldText(currentPage && currentBold && this.mBoldText);
                this.mPaintText.setColor(this.mColorText);
                if (currentPage && currentSelected) {
                    this.mPaintText.setAlpha(colorTextAlpha - ((int) (colorTextAlpha * selectedPercent)));
                }
                if (i6 < boundsSize - 1) {
                    Rect rightBound2 = bounds.get(i6 + 1);
                    page2 = page4;
                    if (bound3.right + this.mTitlePadding > rightBound2.left) {
                        int w3 = bound3.right - bound3.left;
                        bound3.left = (int) ((rightBound2.left - w3) - this.mTitlePadding);
                        bound3.right = bound3.left + w3;
                    }
                } else {
                    page2 = page4;
                }
                count2 = count;
                page = page2;
                right = right2;
                countMinusOne = width3;
                countMinusOne2 = i2;
                width = i6;
                canvas.drawText(pageTitle, 0, pageTitle.length(), bound3.left, bound3.bottom + this.mTopPadding, this.mPaintText);
                if (currentPage && currentSelected) {
                    this.mPaintText.setColor(this.mColorSelected);
                    this.mPaintText.setAlpha((int) ((this.mColorSelected >>> 24) * selectedPercent));
                    canvas.drawText(pageTitle, 0, pageTitle.length(), bound3.left, bound3.bottom + this.mTopPadding, this.mPaintText);
                }
            }
            i5 = width + 1;
            i2 = countMinusOne2;
            right2 = right;
            count = count2;
            page4 = page;
            width3 = countMinusOne;
        }
        int page5 = page4;
        int countMinusOne3 = i2;
        float footerLineHeight = this.mFooterLineHeight;
        float footerIndicatorLineHeight = this.mFooterIndicatorHeight;
        if (this.mLinePosition == LinePosition.Top) {
            height = 0;
            footerLineHeight = -footerLineHeight;
            footerIndicatorLineHeight = -footerIndicatorLineHeight;
        }
        float footerIndicatorLineHeight2 = footerIndicatorLineHeight;
        float footerLineHeight2 = footerLineHeight;
        int height2 = height;
        this.mPath.reset();
        this.mPath.moveTo(0.0f, height2 - (footerLineHeight2 / 2.0f));
        this.mPath.lineTo(countMinusOne3, height2 - (footerLineHeight2 / 2.0f));
        this.mPath.close();
        canvas.drawPath(this.mPath, this.mPaintFooterLine);
        float heightMinusLine = height2 - footerLineHeight2;
        switch (this.mFooterIndicatorStyle) {
            case Triangle:
                this.mPath.reset();
                this.mPath.moveTo(halfWidth, heightMinusLine - footerIndicatorLineHeight2);
                this.mPath.lineTo(halfWidth + footerIndicatorLineHeight2, heightMinusLine);
                this.mPath.lineTo(halfWidth - footerIndicatorLineHeight2, heightMinusLine);
                this.mPath.close();
                canvas.drawPath(this.mPath, this.mPaintFooterIndicator);
                return;
            case Underline:
                if (currentSelected) {
                    if (page5 >= boundsSize) {
                        return;
                    }
                    Rect underlineBounds = bounds.get(page5);
                    float rightPlusPadding = underlineBounds.right + this.mFooterIndicatorUnderlinePadding;
                    float leftMinusPadding = underlineBounds.left - this.mFooterIndicatorUnderlinePadding;
                    float heightMinusLineMinusIndicator = heightMinusLine - footerIndicatorLineHeight2;
                    this.mPath.reset();
                    this.mPath.moveTo(leftMinusPadding, heightMinusLine);
                    this.mPath.lineTo(rightPlusPadding, heightMinusLine);
                    this.mPath.lineTo(rightPlusPadding, heightMinusLineMinusIndicator);
                    this.mPath.lineTo(leftMinusPadding, heightMinusLineMinusIndicator);
                    this.mPath.close();
                    this.mPaintFooterIndicator.setAlpha((int) (255.0f * selectedPercent));
                    canvas.drawPath(this.mPath, this.mPaintFooterIndicator);
                    this.mPaintFooterIndicator.setAlpha(255);
                    return;
                }
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (super.onTouchEvent(ev)) {
            return true;
        }
        if (this.mViewPager == null || this.mViewPager.getAdapter().getCount() == 0) {
            return false;
        }
        int action = ev.getAction() & 255;
        switch (action) {
            case 0:
                this.mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                this.mLastMotionX = ev.getX();
                return true;
            case 1:
            case 3:
                if (!this.mIsDragging) {
                    int count = this.mViewPager.getAdapter().getCount();
                    int width = getWidth();
                    float halfWidth = width / 2.0f;
                    float sixthWidth = width / 6.0f;
                    float leftThird = halfWidth - sixthWidth;
                    float rightThird = halfWidth + sixthWidth;
                    float eventX = ev.getX();
                    if (eventX < leftThird) {
                        if (this.mCurrentPage > 0) {
                            if (action != 3) {
                                this.mViewPager.setCurrentItem(this.mCurrentPage - 1);
                            }
                            return true;
                        }
                    } else if (eventX > rightThird) {
                        if (this.mCurrentPage < count - 1) {
                            if (action != 3) {
                                this.mViewPager.setCurrentItem(this.mCurrentPage + 1);
                            }
                            return true;
                        }
                    } else if (this.mCenterItemClickListener != null && action != 3) {
                        this.mCenterItemClickListener.onCenterItemClick(this.mCurrentPage);
                    }
                }
                this.mIsDragging = false;
                this.mActivePointerId = -1;
                if (this.mViewPager.isFakeDragging()) {
                    this.mViewPager.endFakeDrag();
                }
                return true;
            case 2:
                int activePointerIndex = MotionEventCompat.findPointerIndex(ev, this.mActivePointerId);
                float x = MotionEventCompat.getX(ev, activePointerIndex);
                float deltaX = x - this.mLastMotionX;
                if (!this.mIsDragging && Math.abs(deltaX) > this.mTouchSlop) {
                    this.mIsDragging = true;
                }
                if (this.mIsDragging) {
                    this.mLastMotionX = x;
                    if (this.mViewPager.isFakeDragging() || this.mViewPager.beginFakeDrag()) {
                        this.mViewPager.fakeDragBy(deltaX);
                    }
                }
                return true;
            case 4:
            default:
                return true;
            case 5:
                int index = MotionEventCompat.getActionIndex(ev);
                this.mLastMotionX = MotionEventCompat.getX(ev, index);
                this.mActivePointerId = MotionEventCompat.getPointerId(ev, index);
                return true;
            case 6:
                int pointerIndex = MotionEventCompat.getActionIndex(ev);
                int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
                if (pointerId == this.mActivePointerId) {
                    int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    this.mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
                }
                int newPointerIndex2 = this.mActivePointerId;
                this.mLastMotionX = MotionEventCompat.getX(ev, MotionEventCompat.findPointerIndex(ev, newPointerIndex2));
                return true;
        }
    }

    private void clipViewOnTheRight(Rect curViewBound, float curViewWidth, int right) {
        curViewBound.right = (int) (right - this.mClipPadding);
        curViewBound.left = (int) (curViewBound.right - curViewWidth);
    }

    private void clipViewOnTheLeft(Rect curViewBound, float curViewWidth, int left) {
        curViewBound.left = (int) (left + this.mClipPadding);
        curViewBound.right = (int) (this.mClipPadding + curViewWidth);
    }

    private ArrayList<Rect> calculateAllBounds(Paint paint) {
        ArrayList<Rect> list = new ArrayList<>();
        int count = this.mViewPager.getAdapter().getCount();
        int width = getWidth();
        int halfWidth = width / 2;
        for (int i = 0; i < count; i++) {
            Rect bounds = calcBounds(i, paint);
            int w = bounds.right - bounds.left;
            int h = bounds.bottom - bounds.top;
            bounds.left = (int) ((halfWidth - (w / 2.0f)) + (((i - this.mCurrentPage) - this.mPageOffset) * width));
            bounds.right = bounds.left + w;
            bounds.top = 0;
            bounds.bottom = h;
            list.add(bounds);
        }
        return list;
    }

    private Rect calcBounds(int index, Paint paint) {
        Rect bounds = new Rect();
        CharSequence title = getTitle(index);
        bounds.right = (int) paint.measureText(title, 0, title.length());
        bounds.bottom = (int) (paint.descent() - paint.ascent());
        return bounds;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager view) {
        if (this.mViewPager == view) {
            return;
        }
        if (this.mViewPager != null) {
            this.mViewPager.setOnPageChangeListener(null);
        }
        if (view.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        this.mViewPager = view;
        this.mViewPager.setOnPageChangeListener(this);
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        invalidate();
    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener listener) {
        this.mCenterItemClickListener = listener;
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int item) {
        if (this.mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.mViewPager.setCurrentItem(item);
        this.mCurrentPage = item;
        invalidate();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
        this.mScrollState = state;
        if (this.mListener != null) {
            this.mListener.onPageScrollStateChanged(state);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.mCurrentPage = position;
        this.mPageOffset = positionOffset;
        invalidate();
        if (this.mListener != null) {
            this.mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        if (this.mScrollState == 0) {
            this.mCurrentPage = position;
            invalidate();
        }
        if (this.mListener != null) {
            this.mListener.onPageSelected(position);
        }
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.mListener = listener;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float height;
        int measuredWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = View.MeasureSpec.getMode(heightMeasureSpec);
        if (heightSpecMode == 1073741824) {
            height = View.MeasureSpec.getSize(heightMeasureSpec);
        } else {
            this.mBounds.setEmpty();
            this.mBounds.bottom = (int) (this.mPaintText.descent() - this.mPaintText.ascent());
            height = (this.mBounds.bottom - this.mBounds.top) + this.mFooterLineHeight + this.mFooterPadding + this.mTopPadding;
            if (this.mFooterIndicatorStyle != IndicatorStyle.None) {
                height += this.mFooterIndicatorHeight;
            }
        }
        int measuredHeight = (int) height;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCurrentPage = savedState.currentPage;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPage = this.mCurrentPage;
        return savedState;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.viewpagerindicator.TitlePageIndicator.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int currentPage;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.currentPage = in.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.currentPage);
        }
    }

    private CharSequence getTitle(int i) {
        CharSequence title = this.mViewPager.getAdapter().getPageTitle(i);
        if (title == null) {
            return "";
        }
        return title;
    }
}
