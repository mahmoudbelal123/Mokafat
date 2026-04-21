package com.yarolegovich.discretescrollview.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

/* JADX INFO: loaded from: classes.dex */
public class ScrollListenerAdapter<T extends RecyclerView.ViewHolder> implements DiscreteScrollView.ScrollStateChangeListener<T> {
    private DiscreteScrollView.ScrollListener<T> adaptee;

    public ScrollListenerAdapter(@NonNull DiscreteScrollView.ScrollListener<T> adaptee) {
        this.adaptee = adaptee;
    }

    @Override // com.yarolegovich.discretescrollview.DiscreteScrollView.ScrollStateChangeListener
    public void onScrollStart(@NonNull T currentItemHolder, int adapterPosition) {
    }

    @Override // com.yarolegovich.discretescrollview.DiscreteScrollView.ScrollStateChangeListener
    public void onScrollEnd(@NonNull T currentItemHolder, int adapterPosition) {
    }

    @Override // com.yarolegovich.discretescrollview.DiscreteScrollView.ScrollStateChangeListener
    public void onScroll(float scrollPosition, int currentIndex, int newIndex, @Nullable T currentHolder, @Nullable T newCurrentHolder) {
        this.adaptee.onScroll(scrollPosition, currentIndex, newIndex, currentHolder, newCurrentHolder);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ScrollListenerAdapter) {
            return this.adaptee.equals(((ScrollListenerAdapter) obj).adaptee);
        }
        return super.equals(obj);
    }
}
