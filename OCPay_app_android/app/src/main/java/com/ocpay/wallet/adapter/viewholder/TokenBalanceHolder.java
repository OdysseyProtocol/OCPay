package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ocpay.wallet.R;
import com.ocpay.wallet.bean.home.TokenBalanceBean;
import com.ocpay.wallet.databinding.ItemTokenBalanceBinding;
import com.ocpay.wallet.utils.TokenUtils;

import java.math.BigDecimal;

import static com.ocpay.wallet.utils.RateUtils.estimateToken;

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

    public void setData(Context context, TokenBalanceBean tokenBalance, boolean showLine) {
        Glide.with(context)
                .load(tokenBalance.getTokenIconUrl())
                .centerCrop()
                .into(mBinding.civWalletIcon);
        mBinding.tvWalletName.setText(tokenBalance.getTokenName());
        int lineColor = showLine ? context.getResources().getColor(R.color.color_item_line) : context.getResources().getColor(R.color.white);
        mBinding.viewLine.setBackgroundColor(lineColor);
        mBinding.tvBalance.setText(tokenBalance.getTokenBalance());

        BigDecimal tokenPrice = TokenUtils.getTokenPrice(tokenBalance.getTokenName());
        BigDecimal multiply = tokenPrice.multiply(new BigDecimal(tokenBalance.getTokenBalance()));
        String value = estimateToken(multiply);
        mBinding.tvValueBalance.setText(value);


    }


}
