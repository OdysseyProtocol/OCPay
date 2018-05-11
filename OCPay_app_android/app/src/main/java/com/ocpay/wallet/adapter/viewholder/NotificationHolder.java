package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.ocpay.wallet.bean.NotificationBean;
import com.ocpay.wallet.databinding.NotificationItemBinding;

import static android.text.format.DateUtils.FORMAT_SHOW_TIME;
import static android.text.format.DateUtils.MINUTE_IN_MILLIS;

/**
 * Created by y on 2018/4/16.
 */

public class NotificationHolder extends RecyclerView.ViewHolder {

    private NotificationItemBinding mBinding;


    public NotificationHolder(NotificationItemBinding binding) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public NotificationHolder(View itemView) {
        super(itemView);

    }

    public void setData(Context context, NotificationBean notificationBean) {
        mBinding.setVariable(BR.notification, notificationBean);
//        DateUtils.
        CharSequence relativeTimeSpanString = DateUtils.getRelativeTimeSpanString(Long.valueOf(notificationBean.getTime()),System.currentTimeMillis(),MINUTE_IN_MILLIS,FORMAT_SHOW_TIME);

        mBinding.tvTime.setText(relativeTimeSpanString);

        mBinding.executePendingBindings();
    }


}
