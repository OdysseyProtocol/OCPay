package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ocpay.wallet.R;
import com.ocpay.wallet.bean.home.TokenBalanceBean;
import com.ocpay.wallet.databinding.ItemTokenBalanceBinding;

/**
 * Created by y on 2018/4/16.
 */

public class TokenBalanceHolder extends RecyclerView.ViewHolder {

    private ItemTokenBalanceBinding mBinding;


    public TokenBalanceHolder(ItemTokenBalanceBinding binding) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public TokenBalanceHolder(View itemView) {
        super(itemView);

    }

    public void setData(Context context, TokenBalanceBean transaction, boolean showLine) {
        Glide.with(context)
                .load(transaction.getTokenIconUrl())
                .centerCrop()
                .into(mBinding.civWalletIcon);
        mBinding.tvWalletName.setText(transaction.getTokenName());
        int lineColor = showLine ? context.getResources().getColor(R.color.color_item_line) : context.getResources().getColor(R.color.white);
        mBinding.viewLine.setBackgroundColor(lineColor);
        mBinding.tvBalance.setText(transaction.getTokenBalance());
//        if(transaction.getTokenBalance())
//        mBinding.tvValueBalance.setText();
    }


}
