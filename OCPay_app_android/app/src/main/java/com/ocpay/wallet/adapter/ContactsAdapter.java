package com.ocpay.wallet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.ContactHolder;
import com.ocpay.wallet.bean.Contact;
import com.ocpay.wallet.databinding.ItemContactBinding;
import com.snow.commonlibrary.recycleview.BaseAdapter;

/**
 * Created by y on 2018/4/16.
 */

public class ContactsAdapter extends BaseAdapter<Contact, ContactHolder> {


    public ContactsAdapter(Context ctx) {
        super(ctx);


    }

    @Override
    protected void bindViewHolderData(ContactHolder viewHolder, Contact data, int position) {
        viewHolder.setData(mCtx, data);
    }


    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        ItemContactBinding notificationItemBinding = DataBindingUtil.inflate(inflater, R.layout.item_contact, parent, false);
        return new ContactHolder(notificationItemBinding);
    }


}
