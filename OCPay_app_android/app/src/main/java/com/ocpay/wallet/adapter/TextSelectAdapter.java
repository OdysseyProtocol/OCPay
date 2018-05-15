package com.ocpay.wallet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.TextSelectHolder;
import com.ocpay.wallet.bean.TextSelectBean;
import com.ocpay.wallet.databinding.ItemTextSelectBinding;
import com.snow.commonlibrary.recycleview.BaseAdapter;

/**
 * Created by y on 2018/4/16.
 */

public class TextSelectAdapter extends BaseAdapter<TextSelectBean, TextSelectHolder> {

    public int selectPosition;

    public TextSelectAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected void bindViewHolderData(TextSelectHolder viewHolder, TextSelectBean data, int position) {
        viewHolder.setData(mCtx, data);
        int lineVisible = (position == (getItemCount() - 1)) ? View.GONE : View.VISIBLE;
        viewHolder.getmBinding().viewLine.setVisibility(lineVisible);
        if (data instanceof TextSelectBean) {
            if (data.isSelected()) {
                selectPosition = position;
            }
        }
    }

    @NonNull
    @Override
    public TextSelectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        ItemTextSelectBinding notificationItemBinding = DataBindingUtil.inflate(inflater, R.layout.item_text_select, parent, false);
        return new TextSelectHolder(notificationItemBinding);
    }


    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }
}
