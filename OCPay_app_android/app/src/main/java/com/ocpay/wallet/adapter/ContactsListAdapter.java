package com.ocpay.wallet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ocpay.wallet.R;
import com.ocpay.wallet.activities.ContactsCreateActivity;
import com.ocpay.wallet.bean.Contact;
import com.ocpay.wallet.utils.OCPPrefUtils;
import com.ocpay.wallet.widget.customview.CircleImageView;

import java.util.List;


public class ContactsListAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> list;

    public ContactsListAdapter(Context context, List<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Contact getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        Contact contact = (Contact) list.get(position);
        ContactViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_list_contact, null);
            viewHolder = new ContactViewHolder(context, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ContactViewHolder) convertView.getTag();
        }
        viewHolder.setContact(contact);
        viewHolder.tvContactEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsCreateActivity.startContactsCreateActivity((Activity) context, ContactsCreateActivity.EDIT, list.get(position).getFirstName());

            }
        });

        viewHolder.tvContactDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                OCPPrefUtils.setContacts(list);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public void setData(List<Contact> data) {
        list = data;
    }


    private static class ContactViewHolder {
        private View mView;
        private Context mContext;
        private CircleImageView circleImageView;
        private TextView tvWalletName;
        private TextView tvWalletAddress;
        public TextView tvContactEdit;
        public TextView tvContactDel;

        public ContactViewHolder(Context mContext, View view) {
            this.mContext = mContext;
            this.mView = view;
            init();
        }

        private void init() {
            circleImageView = mView.findViewById(R.id.civ_wallet_icon);
            tvWalletName = mView.findViewById(R.id.tv_wallet_name);
            tvWalletAddress = mView.findViewById(R.id.tv_wallet_address);
            tvContactEdit = mView.findViewById(R.id.tv_edit);
            tvContactDel = mView.findViewById(R.id.tv_del);
        }


        public void setContact(Contact contact) {
//            circleImageView.setim
            tvWalletName.setText(contact.getFirstName() + " " + contact.getFamilyName());
            tvWalletAddress.setText(contact.getWalletAddress());
        }
    }
}
