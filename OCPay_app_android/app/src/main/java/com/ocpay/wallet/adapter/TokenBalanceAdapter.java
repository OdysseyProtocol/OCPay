package com.ocpay.wallet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.TokenBalanceHolder;
import com.ocpay.wallet.bean.home.TokenBalanceBean;
import com.ocpay.wallet.databinding.ItemTokenBalanceBinding;
import com.snow.commonlibrary.recycleview.BaseAdapter;


/**
 * Created by y on 2017/11/17.
 */

public class TokenBalanceAdapter extends BaseAdapter<TokenBalanceBean, TokenBalanceHolder> {

    private ItemTokenBalanceBinding binding;

    public TokenBalanceAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected void bindViewHolderData(TokenBalanceHolder viewHolder, final TokenBalanceBean data, int position) {
        viewHolder.setData(mCtx, data, !(position == getItemCount() - 1));
    }


    @Override
    public TokenBalanceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTokenBalanceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mCtx), R.layout.item_token_balance, null, false);
        return new TokenBalanceHolder(binding);
    }
}
