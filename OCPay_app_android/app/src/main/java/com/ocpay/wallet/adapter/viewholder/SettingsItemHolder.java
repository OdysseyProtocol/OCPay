package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.R;
import com.ocpay.wallet.bean.SettingsBean;
import com.ocpay.wallet.databinding.ItemSettingsBinding;

import static com.ocpay.wallet.bean.SettingsBean.TYPE.NOTIFICATION;
import static com.ocpay.wallet.bean.SettingsBean.TYPE.USER;

/**
 * Created by y on 2018/4/16.
 */

public class SettingsItemHolder extends RecyclerView.ViewHolder {

    private ItemSettingsBinding mBinding;


    public SettingsItemHolder(ItemSettingsBinding binding) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public SettingsItemHolder(View itemView) {
        super(itemView);

    }

    public void setData(Context context, SettingsBean data, int position, int size) {
        mBinding.tvSettingsName.setText(data.getTitle());
        mBinding.ivSettingsIcon.setImageResource(data.getIcRes());
        int viewSpaceVisible = data.getType() == USER ? View.VISIBLE : View.GONE;
        int ivGoVisible = data.getType() == NOTIFICATION ? View.GONE : View.VISIBLE;
        int tvCountVisible = data.getType() == NOTIFICATION && data.getMessageCount() > 0 ? View.VISIBLE : View.GONE;
        mBinding.tvMessageCount.setText(data.getMessageCount() + "");
        mBinding.tvMessageCount.setVisibility(tvCountVisible);
        mBinding.ivGo.setVisibility(ivGoVisible);
        if (data.getType() == USER) {
            mBinding.viewSpace.setVisibility(viewSpaceVisible);
        }

        int lineColor = size == position + 1 ? R.color.white : R.color.color_wallet_item_selected;
        mBinding.viewLine.setBackgroundColor(context.getResources().getColor(lineColor));
    }


}
