package com.yarolegovich.discretescrollview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class InfiniteScrollAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    private static final int NOT_INITIALIZED = -1;
    private static final int RESET_BOUND = 100;
    private int currentRangeStart;
    private DiscreteScrollLayoutManager layoutManager;
    private RecyclerView.Adapter<T> wrapped;

    public static <T extends RecyclerView.ViewHolder> InfiniteScrollAdapter<T> wrap(@NonNull RecyclerView.Adapter<T> adapter) {
        return new InfiniteScrollAdapter<>(adapter);
    }

    public InfiniteScrollAdapter(@NonNull RecyclerView.Adapter<T> wrapped) {
        this.wrapped = wrapped;
        this.wrapped.registerAdapterDataObserver(new DataSetChangeDelegate());
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.wrapped.onAttachedToRecyclerView(recyclerView);
        if (recyclerView instanceof DiscreteScrollView) {
            this.layoutManager = (DiscreteScrollLayoutManager) recyclerView.getLayoutManager();
            this.currentRangeStart = -1;
        } else {
            String msg = recyclerView.getContext().getString(R.string.dsv_ex_msg_adapter_wrong_recycler);
            throw new RuntimeException(msg);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.wrapped.onDetachedFromRecyclerView(recyclerView);
        this.layoutManager = null;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public T onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.currentRangeStart == -1) {
            resetRange(0);
        }
        return (T) this.wrapped.onCreateViewHolder(viewGroup, i);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(T holder, int position) {
        this.wrapped.onBindViewHolder(holder, mapPositionToReal(position));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.wrapped.getItemViewType(mapPositionToReal(position));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.wrapped.getItemCount() <= 1) {
            return this.wrapped.getItemCount();
        }
        return Integer.MAX_VALUE;
    }

    public int getRealItemCount() {
        return this.wrapped.getItemCount();
    }

    public int getRealCurrentPosition() {
        return getRealPosition(this.layoutManager.getCurrentPosition());
    }

    public int getRealPosition(int position) {
        return mapPositionToReal(position);
    }

    public int getClosestPosition(int position) {
        if (position >= this.wrapped.getItemCount()) {
            throw new IndexOutOfBoundsException(String.format(Locale.US, "requested position is outside adapter's bounds: position=%d, size=%d", Integer.valueOf(position), Integer.valueOf(this.wrapped.getItemCount())));
        }
        int adapterTarget = this.currentRangeStart + position;
        int adapterCurrent = this.layoutManager.getCurrentPosition();
        if (adapterTarget == adapterCurrent) {
            return adapterCurrent;
        }
        if (adapterTarget < adapterCurrent) {
            int adapterTargetNextSet = this.currentRangeStart + this.wrapped.getItemCount() + position;
            return adapterCurrent - adapterTarget < adapterTargetNextSet - adapterCurrent ? adapterTarget : adapterTargetNextSet;
        }
        int adapterTargetPrevSet = (this.currentRangeStart - this.wrapped.getItemCount()) + position;
        return adapterCurrent - adapterTargetPrevSet < adapterTarget - adapterCurrent ? adapterTargetPrevSet : adapterTarget;
    }

    private int mapPositionToReal(int position) {
        int newPosition = position - this.currentRangeStart;
        if (newPosition >= this.wrapped.getItemCount()) {
            this.currentRangeStart += this.wrapped.getItemCount();
            if (Integer.MAX_VALUE - this.currentRangeStart <= 100) {
                resetRange(0);
            }
            return 0;
        }
        if (newPosition < 0) {
            this.currentRangeStart -= this.wrapped.getItemCount();
            if (this.currentRangeStart <= 100) {
                resetRange(this.wrapped.getItemCount() - 1);
            }
            return this.wrapped.getItemCount() - 1;
        }
        return newPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetRange(int newPosition) {
        if (getItemCount() == 1) {
            this.currentRangeStart = 0;
            this.layoutManager.scrollToPosition(0);
        } else {
            this.currentRangeStart = 1073741823;
            this.layoutManager.scrollToPosition(this.currentRangeStart + newPosition);
        }
    }

    private class DataSetChangeDelegate extends RecyclerView.AdapterDataObserver {
        private DataSetChangeDelegate() {
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            InfiniteScrollAdapter.this.resetRange(0);
            InfiniteScrollAdapter.this.notifyDataSetChanged();
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onChanged();
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount) {
            onChanged();
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            onChanged();
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int positionStart, int itemCount) {
            onChanged();
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            onChanged();
        }
    }
}
