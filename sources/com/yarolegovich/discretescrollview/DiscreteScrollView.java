package com.yarolegovich.discretescrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.yarolegovich.discretescrollview.DiscreteScrollLayoutManager;
import com.yarolegovich.discretescrollview.transform.DiscreteScrollItemTransformer;
import com.yarolegovich.discretescrollview.util.ScrollListenerAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DiscreteScrollView extends RecyclerView {
    private static final int DEFAULT_ORIENTATION = DSVOrientation.HORIZONTAL.ordinal();
    public static final int NO_POSITION = -1;
    private boolean isOverScrollEnabled;
    private DiscreteScrollLayoutManager layoutManager;
    private List<OnItemChangedListener> onItemChangedListeners;
    private List<ScrollStateChangeListener> scrollStateChangeListeners;

    public interface OnItemChangedListener<T extends RecyclerView.ViewHolder> {
        void onCurrentItemChanged(@Nullable T t, int i);
    }

    public interface ScrollListener<T extends RecyclerView.ViewHolder> {
        void onScroll(float f, int i, int i2, @Nullable T t, @Nullable T t2);
    }

    public interface ScrollStateChangeListener<T extends RecyclerView.ViewHolder> {
        void onScroll(float f, int i, int i2, @Nullable T t, @Nullable T t2);

        void onScrollEnd(@NonNull T t, int i);

        void onScrollStart(@NonNull T t, int i);
    }

    public DiscreteScrollView(Context context) {
        super(context);
        init(null);
    }

    public DiscreteScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DiscreteScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.scrollStateChangeListeners = new ArrayList();
        this.onItemChangedListeners = new ArrayList();
        int orientation = DEFAULT_ORIENTATION;
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DiscreteScrollView);
            orientation = ta.getInt(R.styleable.DiscreteScrollView_dsv_orientation, DEFAULT_ORIENTATION);
            ta.recycle();
        }
        this.isOverScrollEnabled = getOverScrollMode() != 2;
        this.layoutManager = new DiscreteScrollLayoutManager(getContext(), new ScrollStateListener(), DSVOrientation.values()[orientation]);
        setLayoutManager(this.layoutManager);
    }

    @Override // android.support.v7.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        if (layout instanceof DiscreteScrollLayoutManager) {
            super.setLayoutManager(layout);
            return;
        }
        throw new IllegalArgumentException(getContext().getString(R.string.dsv_ex_msg_dont_set_lm));
    }

    @Override // android.support.v7.widget.RecyclerView
    public boolean fling(int velocityX, int velocityY) {
        boolean isFling = super.fling(velocityX, velocityY);
        if (isFling) {
            this.layoutManager.onFling(velocityX, velocityY);
        } else {
            this.layoutManager.returnToCurrentPosition();
        }
        return isFling;
    }

    @Nullable
    public RecyclerView.ViewHolder getViewHolder(int position) {
        View view = this.layoutManager.findViewByPosition(position);
        if (view != null) {
            return getChildViewHolder(view);
        }
        return null;
    }

    public int getCurrentItem() {
        return this.layoutManager.getCurrentPosition();
    }

    public void setItemTransformer(DiscreteScrollItemTransformer transformer) {
        this.layoutManager.setItemTransformer(transformer);
    }

    public void setItemTransitionTimeMillis(@IntRange(from = 10) int millis) {
        this.layoutManager.setTimeForItemSettle(millis);
    }

    public void setSlideOnFling(boolean result) {
        this.layoutManager.setShouldSlideOnFling(result);
    }

    public void setSlideOnFlingThreshold(int threshold) {
        this.layoutManager.setSlideOnFlingThreshold(threshold);
    }

    public void setOrientation(DSVOrientation orientation) {
        this.layoutManager.setOrientation(orientation);
    }

    public void setOffscreenItems(int items) {
        this.layoutManager.setOffscreenItems(items);
    }

    public void setClampTransformProgressAfter(@IntRange(from = 1) int itemCount) {
        if (itemCount <= 1) {
            throw new IllegalArgumentException("must be >= 1");
        }
        this.layoutManager.setTransformClampItemCount(itemCount);
    }

    public void setOverScrollEnabled(boolean overScrollEnabled) {
        this.isOverScrollEnabled = overScrollEnabled;
        setOverScrollMode(2);
    }

    public void addScrollStateChangeListener(@NonNull ScrollStateChangeListener<?> scrollStateChangeListener) {
        this.scrollStateChangeListeners.add(scrollStateChangeListener);
    }

    public void addScrollListener(@NonNull ScrollListener<?> scrollListener) {
        addScrollStateChangeListener(new ScrollListenerAdapter(scrollListener));
    }

    public void addOnItemChangedListener(@NonNull OnItemChangedListener<?> onItemChangedListener) {
        this.onItemChangedListeners.add(onItemChangedListener);
    }

    public void removeScrollStateChangeListener(@NonNull ScrollStateChangeListener<?> scrollStateChangeListener) {
        this.scrollStateChangeListeners.remove(scrollStateChangeListener);
    }

    public void removeScrollListener(@NonNull ScrollListener<?> scrollListener) {
        removeScrollStateChangeListener(new ScrollListenerAdapter(scrollListener));
    }

    public void removeItemChangedListener(@NonNull OnItemChangedListener<?> onItemChangedListener) {
        this.onItemChangedListeners.remove(onItemChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyScrollStart(RecyclerView.ViewHolder holder, int current) {
        for (ScrollStateChangeListener listener : this.scrollStateChangeListeners) {
            listener.onScrollStart(holder, current);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyScrollEnd(RecyclerView.ViewHolder holder, int current) {
        for (ScrollStateChangeListener listener : this.scrollStateChangeListeners) {
            listener.onScrollEnd(holder, current);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyScroll(float position, int currentIndex, int newIndex, RecyclerView.ViewHolder currentHolder, RecyclerView.ViewHolder newHolder) {
        for (ScrollStateChangeListener listener : this.scrollStateChangeListeners) {
            listener.onScroll(position, currentIndex, newIndex, currentHolder, newHolder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCurrentItemChanged(RecyclerView.ViewHolder holder, int current) {
        for (OnItemChangedListener listener : this.onItemChangedListeners) {
            listener.onCurrentItemChanged(holder, current);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCurrentItemChanged() {
        if (this.onItemChangedListeners.isEmpty()) {
            return;
        }
        int current = this.layoutManager.getCurrentPosition();
        RecyclerView.ViewHolder currentHolder = getViewHolder(current);
        notifyCurrentItemChanged(currentHolder, current);
    }

    private class ScrollStateListener implements DiscreteScrollLayoutManager.ScrollStateListener {
        private ScrollStateListener() {
        }

        @Override // com.yarolegovich.discretescrollview.DiscreteScrollLayoutManager.ScrollStateListener
        public void onIsBoundReachedFlagChange(boolean isBoundReached) {
            if (DiscreteScrollView.this.isOverScrollEnabled) {
                DiscreteScrollView.this.setOverScrollMode(isBoundReached ? 0 : 2);
            }
        }

        @Override // com.yarolegovich.discretescrollview.DiscreteScrollLayoutManager.ScrollStateListener
        public void onScrollStart() {
            int current;
            RecyclerView.ViewHolder holder;
            if (!DiscreteScrollView.this.scrollStateChangeListeners.isEmpty() && (holder = DiscreteScrollView.this.getViewHolder((current = DiscreteScrollView.this.layoutManager.getCurrentPosition()))) != null) {
                DiscreteScrollView.this.notifyScrollStart(holder, current);
            }
        }

        @Override // com.yarolegovich.discretescrollview.DiscreteScrollLayoutManager.ScrollStateListener
        public void onScrollEnd() {
            int current;
            RecyclerView.ViewHolder holder;
            if ((!DiscreteScrollView.this.onItemChangedListeners.isEmpty() || !DiscreteScrollView.this.scrollStateChangeListeners.isEmpty()) && (holder = DiscreteScrollView.this.getViewHolder((current = DiscreteScrollView.this.layoutManager.getCurrentPosition()))) != null) {
                DiscreteScrollView.this.notifyScrollEnd(holder, current);
                DiscreteScrollView.this.notifyCurrentItemChanged(holder, current);
            }
        }

        @Override // com.yarolegovich.discretescrollview.DiscreteScrollLayoutManager.ScrollStateListener
        public void onScroll(float currentViewPosition) {
            int currentIndex;
            int newIndex;
            if (!DiscreteScrollView.this.scrollStateChangeListeners.isEmpty() && (currentIndex = DiscreteScrollView.this.getCurrentItem()) != (newIndex = DiscreteScrollView.this.layoutManager.getNextPosition())) {
                DiscreteScrollView.this.notifyScroll(currentViewPosition, currentIndex, newIndex, DiscreteScrollView.this.getViewHolder(currentIndex), DiscreteScrollView.this.getViewHolder(newIndex));
            }
        }

        @Override // com.yarolegovich.discretescrollview.DiscreteScrollLayoutManager.ScrollStateListener
        public void onCurrentViewFirstLayout() {
            DiscreteScrollView.this.post(new Runnable() { // from class: com.yarolegovich.discretescrollview.DiscreteScrollView.ScrollStateListener.1
                @Override // java.lang.Runnable
                public void run() {
                    DiscreteScrollView.this.notifyCurrentItemChanged();
                }
            });
        }

        @Override // com.yarolegovich.discretescrollview.DiscreteScrollLayoutManager.ScrollStateListener
        public void onDataSetChangeChangedPosition() {
            DiscreteScrollView.this.notifyCurrentItemChanged();
        }
    }
}
