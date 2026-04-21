package com.yarolegovich.discretescrollview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerViewProxy {
    private RecyclerView.LayoutManager layoutManager;

    public RecyclerViewProxy(@NonNull RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void attachView(View view) {
        this.layoutManager.attachView(view);
    }

    public void detachView(View view) {
        this.layoutManager.detachView(view);
    }

    public void detachAndScrapView(View view, RecyclerView.Recycler recycler) {
        this.layoutManager.detachAndScrapView(view, recycler);
    }

    public void detachAndScrapAttachedViews(RecyclerView.Recycler recycler) {
        this.layoutManager.detachAndScrapAttachedViews(recycler);
    }

    public void recycleView(View view, RecyclerView.Recycler recycler) {
        recycler.recycleView(view);
    }

    public void removeAndRecycleAllViews(RecyclerView.Recycler recycler) {
        this.layoutManager.removeAndRecycleAllViews(recycler);
    }

    public int getChildCount() {
        return this.layoutManager.getChildCount();
    }

    public int getItemCount() {
        return this.layoutManager.getItemCount();
    }

    public View getMeasuredChildForAdapterPosition(int position, RecyclerView.Recycler recycler) {
        View view = recycler.getViewForPosition(position);
        this.layoutManager.addView(view);
        this.layoutManager.measureChildWithMargins(view, 0, 0);
        return view;
    }

    public void layoutDecoratedWithMargins(View v, int left, int top, int right, int bottom) {
        this.layoutManager.layoutDecoratedWithMargins(v, left, top, right, bottom);
    }

    public View getChildAt(int index) {
        return this.layoutManager.getChildAt(index);
    }

    public int getPosition(View view) {
        return this.layoutManager.getPosition(view);
    }

    public int getMeasuredWidthWithMargin(View child) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        return this.layoutManager.getDecoratedMeasuredWidth(child) + lp.leftMargin + lp.rightMargin;
    }

    public int getMeasuredHeightWithMargin(View child) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        return this.layoutManager.getDecoratedMeasuredHeight(child) + lp.topMargin + lp.bottomMargin;
    }

    public int getWidth() {
        return this.layoutManager.getWidth();
    }

    public int getHeight() {
        return this.layoutManager.getHeight();
    }

    public void offsetChildrenHorizontal(int amount) {
        this.layoutManager.offsetChildrenHorizontal(amount);
    }

    public void offsetChildrenVertical(int amount) {
        this.layoutManager.offsetChildrenVertical(amount);
    }

    public void requestLayout() {
        this.layoutManager.requestLayout();
    }

    public void startSmoothScroll(RecyclerView.SmoothScroller smoothScroller) {
        this.layoutManager.startSmoothScroll(smoothScroller);
    }

    public void removeAllViews() {
        this.layoutManager.removeAllViews();
    }
}
