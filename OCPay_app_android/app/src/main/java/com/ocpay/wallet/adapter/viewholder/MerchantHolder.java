package com.ocpay.wallet.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocpay.wallet.R;


/**
 * Created by y on 2017/11/17.
 */

public class MerchantHolder extends RecyclerView.ViewHolder {

    public ImageView ivGoods;
    public TextView tvGoodsName;
    //    public final TextView tvHighAmount;
    public TextView tvAmount;

    public MerchantHolder(View itemView) {
        super(itemView);
        ivGoods = itemView.findViewById(R.id.iv_goods);
        tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
//        tvHighAmount = itemView.findViewById(R.id.tv_high_amount);
//        tvAmount = itemView.findViewById(R.id.tv_amount);
    }
}
