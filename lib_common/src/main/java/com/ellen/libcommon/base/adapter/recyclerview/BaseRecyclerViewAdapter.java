package com.ellen.libcommon.base.adapter.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerViewAdapter<T extends BaseViewHolder> extends RecyclerView.Adapter<T> {

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T extends BaseViewHolder>{
        void onItemClick(T t, int position);
    }

}
