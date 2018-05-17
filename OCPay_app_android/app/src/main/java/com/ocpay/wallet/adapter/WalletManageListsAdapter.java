package com.ocpay.wallet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.WalletManageItemHolder;
import com.ocpay.wallet.databinding.WalletManageItemBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.snow.commonlibrary.recycleview.BaseAdapter;

/**
 * Created by y on 2018/4/16.
 */

public class WalletManageListsAdapter extends BaseAdapter<WalletInfo, WalletManageItemHolder> {

    WalletInfo walletInfo;

    public WalletManageListsAdapter(Context ctx) {
        super(ctx);
        walletInfo = OCPWallet.getCurrentWallet();

    }

    @Override
    protected void bindViewHolderData(WalletManageItemHolder viewHolder, WalletInfo data, int position) {
        viewHolder.setData(mCtx, data);
    }

    @NonNull
    @Override
    public WalletManageItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);

        WalletManageItemBinding walletItemBinding = DataBindingUtil.inflate(inflater, R.layout.wallet_manage_item, parent, false);

        return new WalletManageItemHolder(walletItemBinding);
    }

    public void setWalletInfo(WalletInfo walletInfo) {
        this.walletInfo = walletInfo;
    }


    public WalletInfo getWalletInfo() {
        return walletInfo;
    }
}
