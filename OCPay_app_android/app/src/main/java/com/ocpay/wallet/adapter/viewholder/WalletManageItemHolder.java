package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.ocpay.wallet.databinding.WalletManageItemBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.snow.commonlibrary.utils.StringUtil;

/**
 * Created by y on 2018/4/16.
 */

public class WalletManageItemHolder extends RecyclerView.ViewHolder {

    private WalletManageItemBinding mBinding;


    public WalletManageItemHolder(WalletManageItemBinding binding) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public WalletManageItemHolder(View itemView) {
        super(itemView);

    }

    public void setData(Context context, WalletInfo walletInfo) {
        mBinding.setVariable(BR.walletInfo, walletInfo);
        mBinding.executePendingBindings();
        mBinding.tvWalletAddress.setText(OCPWalletUtils.foldWalletAddress(walletInfo.getWalletAddress()));
        mBinding.tvWalletName.setText(walletInfo.getWalletName());
        String amount = StringUtil.isEmpty(walletInfo.getEthBalance()) ? "0" : walletInfo.getEthBalance();
        mBinding.tvEthAmount.setText(amount);
    }


}
