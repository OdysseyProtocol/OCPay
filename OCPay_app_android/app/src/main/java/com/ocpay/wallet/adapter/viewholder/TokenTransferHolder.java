package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.ocpay.wallet.databinding.LayoutTokenTransferItemBinding;
import com.ocpay.wallet.greendao.WalletInfo;

/**
 * Created by y on 2018/4/16.
 */

public class TokenTransferHolder extends RecyclerView.ViewHolder {

    private LayoutTokenTransferItemBinding mBinding;


    public TokenTransferHolder(LayoutTokenTransferItemBinding binding) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public TokenTransferHolder(View itemView) {
        super(itemView);

    }

    public void setData(Context context, WalletInfo walletInfo, boolean isSelected) {
        mBinding.setVariable(BR.walletInfo, walletInfo);
        mBinding.executePendingBindings();
//
//        int colorBg = isSelected ? MyApp.getContext().getResources().getColor(R.color.color_wallet_item_selected)
//                : MyApp.getContext().getResources().getColor(R.color.white);
//        MyLog.i("color:"+colorBg);
//        mBinding.llWalletItem.setBackgroundColor(colorBg);
    }


}
