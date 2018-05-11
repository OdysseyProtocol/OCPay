package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.bean.Contact;
import com.ocpay.wallet.databinding.ItemContactBinding;

/**
 * Created by y on 2018/4/16.
 */

public class ContactHolder extends RecyclerView.ViewHolder {

    private ItemContactBinding mBinding;


    public ContactHolder(ItemContactBinding binding) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public ContactHolder(View itemView) {
        super(itemView);

    }

    public void setData(Context context, Contact contact) {
//        mBinding.setVariable(BR.notification, notificationBean);
//        CharSequence relativeTimeSpanString = DateUtils.getRelativeTimeSpanString(Long.valueOf(notificationBean.getTime()),System.currentTimeMillis(),MINUTE_IN_MILLIS,FORMAT_SHOW_TIME);
//
//        mBinding.tvTime.setText(relativeTimeSpanString);
//
//        mBinding.executePendingBindings();
    }


}
