package com.snow.commonlibrary.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y on 17/10/18.
 */


public abstract class BaseAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    public interface OnItemClickListener<T> {
        /**
         *
         * @param adapter  adapter
         * @param data
         * @param position
         */
        void onItemClicked(RecyclerView.Adapter adapter, T data, int position);
    }

    interface OnItemLongClickListener<T> {
        /**
         * 被长按
         *
         * @param adapter  adapter
         * @param data
         * @param position
         */
        boolean onItemLongClicked(RecyclerView.Adapter adapter, T data, int position);
    }

    private List<T> mData = new ArrayList<>();
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    protected Context mCtx;

    public BaseAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     *
     *
     * @param data data
     */
    public void add(T data) {
        this.mData.add(data);
    }

    /**
     *
     *
     * @param position position
     * @param data     data
     */
    public void add(int position, T data) {
        this.mData.add(position, data);
    }

    /**
     *
     *
     * @param datas datas
     */
    public void addAll(List<T> datas) {
        if (datas == null)
            return;
        this.mData.addAll(datas);
    }

    /**
     *
     *
     * @param datas
     */
    public void setData(List<T> datas) {
        if (mData != null)
            mData.clear();
        mData = datas;
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        this.mData.clear();
    }

    /**
     * 设置点击监听器
     *
     * @param listener 监听器
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    /**
     * 设置长按监听器
     *
     * @param listener 监听器
     */
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mLongClickListener = listener;
    }

    /**
     * 绑定数据
     *
     * @param holder   StickerItemViewHolder
     * @param position 位置
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(final V holder, int position) {
        final T data = mData.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null) {
                    mClickListener.onItemClicked(BaseAdapter.this, data, holder.getAdapterPosition());
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mLongClickListener != null) {
                    return mLongClickListener.onItemLongClicked(BaseAdapter.this, data, holder.getAdapterPosition());
                }
                return false;
            }
        });
        bindViewHolderData(holder, data, position);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 绑定viewholder与data的数据.
     *
     * @param viewHolder viewholder
     * @param data       数据
     */
    protected abstract void bindViewHolderData(V viewHolder, T data, int position);


    /**
     * 获取指定位置的item
     *
     * @param position 要获取的位置
     * @return 返回的item
     */
    public T getItemAt(int position) {
        if (position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    /**
     * 获得绑定数据
     *
     * @return
     */
    public List<T> getmData() {
        return this.mData;
    }
}
