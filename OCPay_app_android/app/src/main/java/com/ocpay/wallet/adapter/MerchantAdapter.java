package com.ocpay.wallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.MerchantHolder;
import com.ocpay.wallet.bean.home.Merchant;
import com.snow.commonlibrary.recycleview.BaseAdapter;

/**
 * Created by y on 2017/11/17.
 */

public class MerchantAdapter extends BaseAdapter<Merchant, MerchantHolder> {


    public MerchantAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected void bindViewHolderData(MerchantHolder viewHolder, final Merchant data, final int position) {

        Glide.with(mCtx)
                .load(data.getMainImage())
                .centerCrop()
                .into(viewHolder.ivGoods);

        final String link = data.getLink();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    @Override
    public MerchantHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mCtx).inflate(R.layout.item_merchant, null, false);
        MerchantHolder dailyCollectionHolder = new MerchantHolder(itemView);
        return dailyCollectionHolder;

    }
}
