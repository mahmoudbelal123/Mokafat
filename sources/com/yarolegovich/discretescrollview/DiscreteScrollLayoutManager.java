package com.yarolegovich.discretescrollview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.transform.DiscreteScrollItemTransformer;

/* JADX INFO: loaded from: classes.dex */
class DiscreteScrollLayoutManager extends RecyclerView.LayoutManager {
    private static final int DEFAULT_FLING_THRESHOLD = 2100;
    private static final int DEFAULT_TIME_FOR_ITEM_SETTLE = 300;
    private static final int DEFAULT_TRANSFORM_CLAMP_ITEM_COUNT = 1;
    private static final String EXTRA_POSITION = "extra_position";
    static final int NO_POSITION = -1;
    protected static final float SCROLL_TO_SNAP_TO_ANOTHER_ITEM = 0.6f;
    protected int childHalfHeight;
    protected int childHalfWidth;
    private Context context;
    protected int currentScrollState;
    private boolean dataSetChangeShiftedPosition;
    protected int extraLayoutSpace;
    protected boolean isFirstOrEmptyLayout;
    private DiscreteScrollItemTransformer itemTransformer;
    private int offscreenItems;
    private DSVOrientation.Helper orientationHelper;
    protected int pendingScroll;

    @NonNull
    private final ScrollStateListener scrollStateListener;
    protected int scrollToChangeCurrent;
    protected int scrolled;
    private int viewHeight;
    private int viewWidth;
    private int timeForItemSettle = DEFAULT_TIME_FOR_ITEM_SETTLE;
    protected int pendingPosition = -1;
    protected int currentPosition = -1;
    private int flingThreshold = DEFAULT_FLING_THRESHOLD;
    private boolean shouldSlideOnFling = false;
    protected Point recyclerCenter = new Point();
    protected Point currentViewCenter = new Point();
    protected Point viewCenterIterator = new Point();
    protected SparseArray<View> detachedCache = new SparseArray<>();
    private RecyclerViewProxy recyclerViewProxy = new RecyclerViewProxy(this);
    private int transformClampItemCount = 1;

    public interface ScrollStateListener {
        void onCurrentViewFirstLayout();

        void onDataSetChangeChangedPosition();

        void onIsBoundReachedFlagChange(boolean z);

        void onScroll(float f);

        void onScrollEnd();

        void onScrollStart();
    }

    public DiscreteScrollLayoutManager(@NonNull Context c, @NonNull ScrollStateListener scrollStateListener, @NonNull DSVOrientation orientation) {
        this.context = c;
        this.scrollStateListener = scrollStateListener;
        this.orientationHelper = orientation.createHelper();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() == 0) {
            this.recyclerViewProxy.removeAndRecycleAllViews(recycler);
            this.pendingPosition = -1;
            this.currentPosition = -1;
            this.pendingScroll = 0;
            this.scrolled = 0;
            return;
        }
        ensureValidPosition(state);
        if (!state.isMeasuring()) {
            checkRecyclerViewDimensionsChanged();
        }
        if (!this.isFirstOrEmptyLayout) {
            this.isFirstOrEmptyLayout = this.recyclerViewProxy.getChildCount() == 0;
            if (this.isFirstOrEmptyLayout) {
                initChildDimensions(recycler);
            }
        }
        updateRecyclerDimensions();
        this.recyclerViewProxy.detachAndScrapAttachedViews(recycler);
        fill(recycler);
        applyItemTransformToChildren();
    }

    private void ensureValidPosition(RecyclerView.State state) {
        if (this.currentPosition == -1 || this.currentPosition >= state.getItemCount()) {
            this.currentPosition = 0;
        }
    }

    private void checkRecyclerViewDimensionsChanged() {
        if (this.recyclerViewProxy.getWidth() != this.viewWidth || this.recyclerViewProxy.getHeight() != this.viewHeight) {
            this.viewWidth = this.recyclerViewProxy.getWidth();
            this.viewHeight = this.recyclerViewProxy.getHeight();
            this.recyclerViewProxy.removeAllViews();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        if (this.isFirstOrEmptyLayout) {
            this.scrollStateListener.onCurrentViewFirstLayout();
            this.isFirstOrEmptyLayout = false;
        } else if (this.dataSetChangeShiftedPosition) {
            this.scrollStateListener.onDataSetChangeChangedPosition();
            this.dataSetChangeShiftedPosition = false;
        }
    }

    protected void initChildDimensions(RecyclerView.Recycler recycler) {
        View viewToMeasure = this.recyclerViewProxy.getMeasuredChildForAdapterPosition(0, recycler);
        int childViewWidth = this.recyclerViewProxy.getMeasuredWidthWithMargin(viewToMeasure);
        int childViewHeight = this.recyclerViewProxy.getMeasuredHeightWithMargin(viewToMeasure);
        this.childHalfWidth = childViewWidth / 2;
        this.childHalfHeight = childViewHeight / 2;
        this.scrollToChangeCurrent = this.orientationHelper.getDistanceToChangeCurrent(childViewWidth, childViewHeight);
        this.extraLayoutSpace = this.scrollToChangeCurrent * this.offscreenItems;
        this.recyclerViewProxy.detachAndScrapView(viewToMeasure, recycler);
    }

    protected void updateRecyclerDimensions() {
        this.recyclerCenter.set(this.recyclerViewProxy.getWidth() / 2, this.recyclerViewProxy.getHeight() / 2);
    }

    protected void fill(RecyclerView.Recycler recycler) {
        cacheAndDetachAttachedViews();
        this.orientationHelper.setCurrentViewCenter(this.recyclerCenter, this.scrolled, this.currentViewCenter);
        int endBound = this.orientationHelper.getViewEnd(this.recyclerViewProxy.getWidth(), this.recyclerViewProxy.getHeight());
        if (isViewVisible(this.currentViewCenter, endBound)) {
            layoutView(recycler, this.currentPosition, this.currentViewCenter);
        }
        layoutViews(recycler, Direction.START, endBound);
        layoutViews(recycler, Direction.END, endBound);
        recycleDetachedViewsAndClearCache(recycler);
    }

    private void layoutViews(RecyclerView.Recycler recycler, Direction direction, int endBound) {
        boolean noPredictiveLayoutRequired = true;
        int positionStep = direction.applyTo(1);
        if (this.pendingPosition != -1 && direction.sameAs(this.pendingPosition - this.currentPosition)) {
            noPredictiveLayoutRequired = false;
        }
        this.viewCenterIterator.set(this.currentViewCenter.x, this.currentViewCenter.y);
        int pos = this.currentPosition;
        while (true) {
            pos += positionStep;
            if (isInBounds(pos)) {
                if (pos == this.pendingPosition) {
                    noPredictiveLayoutRequired = true;
                }
                this.orientationHelper.shiftViewCenter(direction, this.scrollToChangeCurrent, this.viewCenterIterator);
                if (isViewVisible(this.viewCenterIterator, endBound)) {
                    layoutView(recycler, pos, this.viewCenterIterator);
                } else if (noPredictiveLayoutRequired) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    protected void layoutView(RecyclerView.Recycler recycler, int position, Point viewCenter) {
        if (position < 0) {
            return;
        }
        View v = this.detachedCache.get(position);
        if (v == null) {
            this.recyclerViewProxy.layoutDecoratedWithMargins(this.recyclerViewProxy.getMeasuredChildForAdapterPosition(position, recycler), viewCenter.x - this.childHalfWidth, viewCenter.y - this.childHalfHeight, viewCenter.x + this.childHalfWidth, viewCenter.y + this.childHalfHeight);
        } else {
            this.recyclerViewProxy.attachView(v);
            this.detachedCache.remove(position);
        }
    }

    protected void cacheAndDetachAttachedViews() {
        this.detachedCache.clear();
        for (int i = 0; i < this.recyclerViewProxy.getChildCount(); i++) {
            View child = this.recyclerViewProxy.getChildAt(i);
            this.detachedCache.put(this.recyclerViewProxy.getPosition(child), child);
        }
        for (int i2 = 0; i2 < this.detachedCache.size(); i2++) {
            this.recyclerViewProxy.detachView(this.detachedCache.valueAt(i2));
        }
    }

    protected void recycleDetachedViewsAndClearCache(RecyclerView.Recycler recycler) {
        for (int i = 0; i < this.detachedCache.size(); i++) {
            View viewToRemove = this.detachedCache.valueAt(i);
            this.recyclerViewProxy.recycleView(viewToRemove, recycler);
        }
        this.detachedCache.clear();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        int newPosition = this.currentPosition;
        if (this.currentPosition == -1) {
            newPosition = 0;
        } else if (this.currentPosition >= positionStart) {
            newPosition = Math.min(this.currentPosition + itemCount, this.recyclerViewProxy.getItemCount() - 1);
        }
        onNewPosition(newPosition);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int positionStart, int itemCount) {
        int newPosition = this.currentPosition;
        if (this.recyclerViewProxy.getItemCount() == 0) {
            newPosition = -1;
        } else if (this.currentPosition >= positionStart) {
            if (this.currentPosition < positionStart + itemCount) {
                this.currentPosition = -1;
            }
            newPosition = Math.max(0, this.currentPosition - itemCount);
        }
        onNewPosition(newPosition);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.currentPosition = Math.min(Math.max(0, this.currentPosition), this.recyclerViewProxy.getItemCount() - 1);
        this.dataSetChangeShiftedPosition = true;
    }

    private void onNewPosition(int position) {
        if (this.currentPosition != position) {
            this.currentPosition = position;
            this.dataSetChangeShiftedPosition = true;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(dx, recycler);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(dy, recycler);
    }

    protected int scrollBy(int amount, RecyclerView.Recycler recycler) {
        Direction direction;
        int leftToScroll;
        if (this.recyclerViewProxy.getChildCount() == 0 || (leftToScroll = calculateAllowedScrollIn((direction = Direction.fromDelta(amount)))) <= 0) {
            return 0;
        }
        int delta = direction.applyTo(Math.min(leftToScroll, Math.abs(amount)));
        this.scrolled += delta;
        if (this.pendingScroll != 0) {
            this.pendingScroll -= delta;
        }
        this.orientationHelper.offsetChildren(-delta, this.recyclerViewProxy);
        if (this.orientationHelper.hasNewBecomeVisible(this)) {
            fill(recycler);
        }
        notifyScroll();
        applyItemTransformToChildren();
        return delta;
    }

    protected void applyItemTransformToChildren() {
        if (this.itemTransformer != null) {
            int clampAfterDistance = this.scrollToChangeCurrent * this.transformClampItemCount;
            for (int i = 0; i < this.recyclerViewProxy.getChildCount(); i++) {
                View child = this.recyclerViewProxy.getChildAt(i);
                float position = getCenterRelativePositionOf(child, clampAfterDistance);
                this.itemTransformer.transformItem(child, position);
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int position) {
        if (this.currentPosition == position) {
            return;
        }
        this.currentPosition = position;
        this.recyclerViewProxy.requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        if (this.currentPosition == position || this.pendingPosition != -1) {
            return;
        }
        startSmoothPendingScroll(position);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.orientationHelper.canScrollHorizontally();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.orientationHelper.canScrollVertically();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int state) {
        if (this.currentScrollState == 0 && this.currentScrollState != state) {
            this.scrollStateListener.onScrollStart();
        }
        if (state == 0) {
            boolean isScrollEnded = onScrollEnd();
            if (isScrollEnded) {
                this.scrollStateListener.onScrollEnd();
            } else {
                return;
            }
        } else if (state == 1) {
            onDragStart();
        }
        this.currentScrollState = state;
    }

    private boolean onScrollEnd() {
        if (this.pendingPosition != -1) {
            this.currentPosition = this.pendingPosition;
            this.pendingPosition = -1;
            this.scrolled = 0;
        }
        Direction scrollDirection = Direction.fromDelta(this.scrolled);
        if (Math.abs(this.scrolled) == this.scrollToChangeCurrent) {
            this.currentPosition += scrollDirection.applyTo(1);
            this.scrolled = 0;
        }
        if (isAnotherItemCloserThanCurrent()) {
            this.pendingScroll = getHowMuchIsLeftToScroll(this.scrolled);
        } else {
            this.pendingScroll = -this.scrolled;
        }
        if (this.pendingScroll == 0) {
            return true;
        }
        startSmoothPendingScroll();
        return false;
    }

    private void onDragStart() {
        boolean isScrollingThroughMultiplePositions = Math.abs(this.scrolled) > this.scrollToChangeCurrent;
        if (isScrollingThroughMultiplePositions) {
            int scrolledPositions = this.scrolled / this.scrollToChangeCurrent;
            this.currentPosition += scrolledPositions;
            this.scrolled -= this.scrollToChangeCurrent * scrolledPositions;
        }
        if (isAnotherItemCloserThanCurrent()) {
            Direction direction = Direction.fromDelta(this.scrolled);
            this.currentPosition += direction.applyTo(1);
            this.scrolled = -getHowMuchIsLeftToScroll(this.scrolled);
        }
        this.pendingPosition = -1;
        this.pendingScroll = 0;
    }

    public void onFling(int velocityX, int velocityY) {
        int velocity = this.orientationHelper.getFlingVelocity(velocityX, velocityY);
        int throttleValue = this.shouldSlideOnFling ? Math.abs(velocity / this.flingThreshold) : 1;
        int newPosition = checkNewOnFlingPositionIsInBounds(this.currentPosition + Direction.fromDelta(velocity).applyTo(throttleValue));
        boolean isInScrollDirection = this.scrolled * velocity >= 0;
        boolean canFling = isInScrollDirection && isInBounds(newPosition);
        if (canFling) {
            startSmoothPendingScroll(newPosition);
        } else {
            returnToCurrentPosition();
        }
    }

    public void returnToCurrentPosition() {
        this.pendingScroll = -this.scrolled;
        if (this.pendingScroll != 0) {
            startSmoothPendingScroll();
        }
    }

    protected int calculateAllowedScrollIn(Direction direction) {
        boolean isBoundReached;
        if (this.pendingScroll != 0) {
            return Math.abs(this.pendingScroll);
        }
        int allowedScroll = 0;
        boolean isScrollDirectionAsBefore = direction.applyTo(this.scrolled) > 0;
        if (direction == Direction.START && this.currentPosition == 0) {
            isBoundReached = this.scrolled == 0;
            if (!isBoundReached) {
                allowedScroll = Math.abs(this.scrolled);
            }
        } else if (direction == Direction.END && this.currentPosition == this.recyclerViewProxy.getItemCount() - 1) {
            isBoundReached = this.scrolled == 0;
            if (!isBoundReached) {
                allowedScroll = Math.abs(this.scrolled);
            }
        } else {
            isBoundReached = false;
            if (isScrollDirectionAsBefore) {
                allowedScroll = this.scrollToChangeCurrent - Math.abs(this.scrolled);
            } else {
                allowedScroll = this.scrollToChangeCurrent + Math.abs(this.scrolled);
            }
        }
        this.scrollStateListener.onIsBoundReachedFlagChange(isBoundReached);
        return allowedScroll;
    }

    private void startSmoothPendingScroll() {
        LinearSmoothScroller scroller = new DiscreteLinearSmoothScroller(this.context);
        scroller.setTargetPosition(this.currentPosition);
        this.recyclerViewProxy.startSmoothScroll(scroller);
    }

    private void startSmoothPendingScroll(int position) {
        if (this.currentPosition == position) {
            return;
        }
        this.pendingScroll = -this.scrolled;
        Direction direction = Direction.fromDelta(position - this.currentPosition);
        int distanceToScroll = Math.abs(position - this.currentPosition) * this.scrollToChangeCurrent;
        this.pendingScroll += direction.applyTo(distanceToScroll);
        this.pendingPosition = position;
        startSmoothPendingScroll();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        int scrollbarSize = computeScrollExtent(state);
        int offset = (int) ((this.scrolled / this.scrollToChangeCurrent) * scrollbarSize);
        return (this.currentPosition * scrollbarSize) + offset;
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getItemCount() == 0) {
            return 0;
        }
        return (int) (computeScrollRange(state) / getItemCount());
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getItemCount() == 0) {
            return 0;
        }
        return this.scrollToChangeCurrent * (getItemCount() - 1);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        this.pendingPosition = -1;
        this.pendingScroll = 0;
        this.scrolled = 0;
        this.currentPosition = 0;
        this.recyclerViewProxy.removeAllViews();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (this.pendingPosition != -1) {
            this.currentPosition = this.pendingPosition;
        }
        bundle.putInt(EXTRA_POSITION, this.currentPosition);
        return bundle;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        this.currentPosition = bundle.getInt(EXTRA_POSITION);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public int getNextPosition() {
        if (this.scrolled == 0) {
            return this.currentPosition;
        }
        if (this.pendingPosition != -1) {
            return this.pendingPosition;
        }
        return this.currentPosition + Direction.fromDelta(this.scrolled).applyTo(1);
    }

    public void setItemTransformer(DiscreteScrollItemTransformer itemTransformer) {
        this.itemTransformer = itemTransformer;
    }

    public void setTimeForItemSettle(int timeForItemSettle) {
        this.timeForItemSettle = timeForItemSettle;
    }

    public void setOffscreenItems(int offscreenItems) {
        this.offscreenItems = offscreenItems;
        this.extraLayoutSpace = this.scrollToChangeCurrent * offscreenItems;
        this.recyclerViewProxy.requestLayout();
    }

    public void setTransformClampItemCount(int transformClampItemCount) {
        this.transformClampItemCount = transformClampItemCount;
        applyItemTransformToChildren();
    }

    public void setOrientation(DSVOrientation orientation) {
        this.orientationHelper = orientation.createHelper();
        this.recyclerViewProxy.removeAllViews();
        this.recyclerViewProxy.requestLayout();
    }

    public void setShouldSlideOnFling(boolean result) {
        this.shouldSlideOnFling = result;
    }

    public void setSlideOnFlingThreshold(int threshold) {
        this.flingThreshold = threshold;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        if (this.recyclerViewProxy.getChildCount() > 0) {
            AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(event);
            record.setFromIndex(getPosition(getFirstChild()));
            record.setToIndex(getPosition(getLastChild()));
        }
    }

    private float getCenterRelativePositionOf(View v, int maxDistance) {
        float distanceFromCenter = this.orientationHelper.getDistanceFromCenter(this.recyclerCenter, getDecoratedLeft(v) + this.childHalfWidth, getDecoratedTop(v) + this.childHalfHeight);
        return Math.min(Math.max(-1.0f, distanceFromCenter / maxDistance), 1.0f);
    }

    private int checkNewOnFlingPositionIsInBounds(int position) {
        int itemCount = this.recyclerViewProxy.getItemCount();
        if (this.currentPosition != 0 && position < 0) {
            return 0;
        }
        if (this.currentPosition != itemCount - 1 && position >= itemCount) {
            return itemCount - 1;
        }
        return position;
    }

    private int getHowMuchIsLeftToScroll(int dx) {
        return Direction.fromDelta(dx).applyTo(this.scrollToChangeCurrent - Math.abs(this.scrolled));
    }

    private boolean isAnotherItemCloserThanCurrent() {
        return ((float) Math.abs(this.scrolled)) >= ((float) this.scrollToChangeCurrent) * SCROLL_TO_SNAP_TO_ANOTHER_ITEM;
    }

    public View getFirstChild() {
        return this.recyclerViewProxy.getChildAt(0);
    }

    public View getLastChild() {
        return this.recyclerViewProxy.getChildAt(this.recyclerViewProxy.getChildCount() - 1);
    }

    public int getExtraLayoutSpace() {
        return this.extraLayoutSpace;
    }

    private void notifyScroll() {
        float amountToScroll = this.pendingPosition != -1 ? Math.abs(this.scrolled + this.pendingScroll) : this.scrollToChangeCurrent;
        float position = -Math.min(Math.max(-1.0f, this.scrolled / amountToScroll), 1.0f);
        this.scrollStateListener.onScroll(position);
    }

    private boolean isInBounds(int itemPosition) {
        return itemPosition >= 0 && itemPosition < this.recyclerViewProxy.getItemCount();
    }

    private boolean isViewVisible(Point viewCenter, int endBound) {
        return this.orientationHelper.isViewVisible(viewCenter, this.childHalfWidth, this.childHalfHeight, endBound, this.extraLayoutSpace);
    }

    protected void setRecyclerViewProxy(RecyclerViewProxy recyclerViewProxy) {
        this.recyclerViewProxy = recyclerViewProxy;
    }

    protected void setOrientationHelper(DSVOrientation.Helper orientationHelper) {
        this.orientationHelper = orientationHelper;
    }

    private class DiscreteLinearSmoothScroller extends LinearSmoothScroller {
        public DiscreteLinearSmoothScroller(Context context) {
            super(context);
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        public int calculateDxToMakeVisible(View view, int snapPreference) {
            return DiscreteScrollLayoutManager.this.orientationHelper.getPendingDx(-DiscreteScrollLayoutManager.this.pendingScroll);
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        public int calculateDyToMakeVisible(View view, int snapPreference) {
            return DiscreteScrollLayoutManager.this.orientationHelper.getPendingDy(-DiscreteScrollLayoutManager.this.pendingScroll);
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        protected int calculateTimeForScrolling(int dx) {
            float dist = Math.min(Math.abs(dx), DiscreteScrollLayoutManager.this.scrollToChangeCurrent);
            return (int) (Math.max(0.01f, dist / DiscreteScrollLayoutManager.this.scrollToChangeCurrent) * DiscreteScrollLayoutManager.this.timeForItemSettle);
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        @Nullable
        public PointF computeScrollVectorForPosition(int targetPosition) {
            return new PointF(DiscreteScrollLayoutManager.this.orientationHelper.getPendingDx(DiscreteScrollLayoutManager.this.pendingScroll), DiscreteScrollLayoutManager.this.orientationHelper.getPendingDy(DiscreteScrollLayoutManager.this.pendingScroll));
        }
    }
}
