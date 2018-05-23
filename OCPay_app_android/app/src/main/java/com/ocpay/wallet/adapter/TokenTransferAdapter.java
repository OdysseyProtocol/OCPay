package com.ocpay.wallet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.TokenTransferHolder;
import com.ocpay.wallet.databinding.LayoutTokenTransferItemBinding;
import com.ocpay.wallet.utils.web3j.response.BaseResponse;
import com.ocpay.wallet.utils.web3j.response.BaseTransaction;
import com.snow.commonlibrary.recycleview.BaseAdapter;

/**
 * Created by y on 2018/4/16.
 */

public class TokenTransferAdapter<T extends BaseResponse> extends BaseAdapter<BaseTransaction, TokenTransferHolder> {

    TYPE type;

    public enum TYPE {
        TOTAL, SINGLE
    }


    public TokenTransferAdapter(Context ctx, TYPE type) {
        super(ctx);
        this.type = type;
    }


    @Override
    protected void bindViewHolderData(TokenTransferHolder viewHolder, BaseTransaction data, int position) {

        BaseTransaction last = position == 0 ? null : getItemAt(position - 1);
        viewHolder.setData(data, last, position);
    }

    @NonNull
    @Override
    public TokenTransferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        LayoutTokenTransferItemBinding tokenTransferItemBinding = DataBindingUtil.inflate(inflater, R.layout.layout_token_transfer_item, parent, false);
        return new TokenTransferHolder(tokenTransferItemBinding, type);
    }


    public void setType(TYPE type) {
        this.type = type;
    }
}
