package com.ocpay.wallet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.SettingsItemHolder;
import com.ocpay.wallet.bean.SettingsBean;
import com.ocpay.wallet.databinding.ItemSettingsBinding;
import com.snow.commonlibrary.recycleview.BaseAdapter;

/**
 * Created by y on 2017/11/17.
 */

public class SettingsAdapter extends BaseAdapter<SettingsBean, SettingsItemHolder> {


    public SettingsAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected void bindViewHolderData(SettingsItemHolder viewHolder, final SettingsBean data, final int position) {
        viewHolder.setData(mCtx, data, position,getItemCount());

    }


    @Override
    public SettingsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSettingsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mCtx), R.layout.item_settings, null, false);
        SettingsItemHolder dailyCollectionHolder = new SettingsItemHolder(binding);
        return dailyCollectionHolder;

    }
}
