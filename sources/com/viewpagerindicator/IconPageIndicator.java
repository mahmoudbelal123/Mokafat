package com.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class IconPageIndicator extends HorizontalScrollView implements PageIndicator {
    private Runnable mIconSelector;
    private final IcsLinearLayout mIconsLayout;
    private ViewPager.OnPageChangeListener mListener;
    private int mSelectedIndex;
    private ViewPager mViewPager;

    public IconPageIndicator(Context context) {
        this(context, null);
    }

    public IconPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);
        this.mIconsLayout = new IcsLinearLayout(context, R.attr.vpiIconPageIndicatorStyle);
        addView(this.mIconsLayout, new FrameLayout.LayoutParams(-2, -1, 17));
    }

    private void animateToIcon(int position) {
        final View iconView = this.mIconsLayout.getChildAt(position);
        if (this.mIconSelector != null) {
            removeCallbacks(this.mIconSelector);
        }
        this.mIconSelector = new Runnable() { // from class: com.viewpagerindicator.IconPageIndicator.1
            @Override // java.lang.Runnable
            public void run() {
                int scrollPos = iconView.getLeft() - ((IconPageIndicator.this.getWidth() - iconView.getWidth()) / 2);
                IconPageIndicator.this.smoothScrollTo(scrollPos, 0);
                IconPageIndicator.this.mIconSelector = null;
            }
        };
        post(this.mIconSelector);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIconSelector != null) {
            post(this.mIconSelector);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIconSelector != null) {
            removeCallbacks(this.mIconSelector);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int arg0) {
        if (this.mListener != null) {
            this.mListener.onPageScrollStateChanged(arg0);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        if (this.mListener != null) {
            this.mListener.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int arg0) {
        setCurrentItem(arg0);
        if (this.mListener != null) {
            this.mListener.onPageSelected(arg0);
        }
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager view) {
        if (this.mViewPager == view) {
            return;
        }
        if (this.mViewPager != null) {
            this.mViewPager.setOnPageChangeListener(null);
        }
        PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        this.mViewPager = view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        this.mIconsLayout.removeAllViews();
        IconPagerAdapter iconAdapter = (IconPagerAdapter) this.mViewPager.getAdapter();
        int count = iconAdapter.getCount();
        for (int i = 0; i < count; i++) {
            ImageView view = new ImageView(getContext(), null, R.attr.vpiIconPageIndicatorStyle);
            view.setImageResource(iconAdapter.getIconResId(i));
            this.mIconsLayout.addView(view);
        }
        int i2 = this.mSelectedIndex;
        if (i2 > count) {
            this.mSelectedIndex = count - 1;
        }
        setCurrentItem(this.mSelectedIndex);
        requestLayout();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int item) {
        if (this.mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.mSelectedIndex = item;
        this.mViewPager.setCurrentItem(item);
        int tabCount = this.mIconsLayout.getChildCount();
        int i = 0;
        while (i < tabCount) {
            View child = this.mIconsLayout.getChildAt(i);
            boolean isSelected = i == item;
            child.setSelected(isSelected);
            if (isSelected) {
                animateToIcon(item);
            }
            i++;
        }
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.mListener = listener;
    }
}
