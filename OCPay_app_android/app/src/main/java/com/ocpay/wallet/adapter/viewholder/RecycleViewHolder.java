package com.ocpay.wallet.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ocpay.wallet.R;


/**
 * Created by y on 2017/11/17.
 */

public class RecycleViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView recyclerView;
    public   LinearLayout llHomeItem;

    public RecycleViewHolder(View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.rl_item);
        llHomeItem = itemView.findViewById(R.id.ll_home_item);


    }
}
