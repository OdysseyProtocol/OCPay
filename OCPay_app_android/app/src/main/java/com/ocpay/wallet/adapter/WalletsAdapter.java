package com.ocpay.wallet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.WalletItemHolder;
import com.ocpay.wallet.databinding.NavWalletItemBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.snow.commonlibrary.recycleview.BaseAdapter;

/**
 * Created by y on 2018/4/16.
 */

public class WalletsAdapter extends BaseAdapter<WalletInfo, WalletItemHolder> {

    WalletInfo walletInfo;

    public WalletsAdapter(Context ctx) {
        super(ctx);
        walletInfo = OCPWallet.getCurrentWallet();

    }

    @Override
    protected void bindViewHolderData(WalletItemHolder viewHolder, WalletInfo data, int position) {
        String walletAddress = walletInfo == null ? "" : walletInfo.getWalletAddress();
        boolean selected = walletAddress.equals(data.getWalletAddress());
        viewHolder.setData(mCtx, data, selected);
    }

    @NonNull
    @Override
    public WalletItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        NavWalletItemBinding walletItemBinding = DataBindingUtil.inflate(inflater, R.layout.nav_wallet_item, parent, false);
        return new WalletItemHolder(walletItemBinding);
    }

    public void setWalletInfo(WalletInfo walletInfo) {
        this.walletInfo = walletInfo;
    }


    public WalletInfo getWalletInfo() {
        return walletInfo;
    }
}
