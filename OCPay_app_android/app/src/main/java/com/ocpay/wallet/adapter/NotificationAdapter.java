package com.ocpay.wallet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.NotificationHolder;
import com.ocpay.wallet.bean.NotificationBean;
import com.ocpay.wallet.databinding.NotificationItemBinding;
import com.snow.commonlibrary.recycleview.BaseAdapter;

/**
 * Created by y on 2018/4/16.
 */

public class NotificationAdapter extends BaseAdapter<NotificationBean, NotificationHolder> {


    public NotificationAdapter(Context ctx) {
        super(ctx);


    }

    @Override
    protected void bindViewHolderData(NotificationHolder viewHolder, NotificationBean data, int position) {
        viewHolder.setData(mCtx, data);
    }


    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        NotificationItemBinding notificationItemBinding = DataBindingUtil.inflate(inflater, R.layout.notification_item, parent, false);
        return new NotificationHolder(notificationItemBinding);
    }


}
