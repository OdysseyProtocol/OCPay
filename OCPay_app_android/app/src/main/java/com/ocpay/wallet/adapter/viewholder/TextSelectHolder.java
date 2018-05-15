package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.bean.TextSelectBean;
import com.ocpay.wallet.databinding.ItemTextSelectBinding;

/**
 * Created by y on 2018/4/16.
 */

public class TextSelectHolder extends RecyclerView.ViewHolder {

    private ItemTextSelectBinding mBinding;


    public TextSelectHolder(ItemTextSelectBinding binding) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public TextSelectHolder(View itemView) {
        super(itemView);

    }

    public void setData(Context context, TextSelectBean contact) {
//        mBinding.setVariable(BR.notification, notificationBean);
//        CharSequence relativeTimeSpanString = DateUtils.getRelativeTimeSpanString(Long.valueOf(notificationBean.getTime()),System.currentTimeMillis(),MINUTE_IN_MILLIS,FORMAT_SHOW_TIME);
//
//        mBinding.tvTime.setText(relativeTimeSpanString);
//
//        mBinding.executePendingBindings();
    }

    public ItemTextSelectBinding getmBinding() {
        return mBinding;
    }
}
