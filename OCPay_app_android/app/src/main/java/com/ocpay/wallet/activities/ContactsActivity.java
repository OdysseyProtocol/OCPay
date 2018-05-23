package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.ContactsAdapter;
import com.ocpay.wallet.adapter.ContactsListAdapter;
import com.ocpay.wallet.bean.Contact;
import com.ocpay.wallet.databinding.ActivityContactsBinding;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.OCPPrefUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ContactsActivity extends BaseActivity implements View.OnClickListener {


    private ActivityContactsBinding binding;
    private List<Contact> contactList;
    private ContactsAdapter contactsAdapter;
    private ContactsListAdapter contactsListAdapter;


    public static void startContactsActivity(Activity activity) {

        Intent intent = new Intent(activity, ContactsActivity.class);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ContactsActivity.this, R.layout.activity_contacts);
        initActionBar();
        initData();
//        initView();
        initListView();
        initListener();

    }

    private void initListener() {


        Disposable disposable = RxBus.getInstance()
                .toObservable(Constans.RXBUS.ACTION_CONTACTS_UPDATE, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        List<Contact> contacts = OCPPrefUtils.getContacts();
                        contactList.clear();
                        contactList.addAll(contacts);
                        contactsListAdapter.notifyDataSetChanged();
//                        contactsAdapter.setData(contacts);
//                        contactsAdapter.notifyDataSetChanged();

                    }
                });

        addDisposable(disposable);
    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(R.string.activity_title_contacts);
        binding.includeActionBar.llToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_close_black);
        binding.includeActionBar.toolbarMenuIcon.setImageResource(R.mipmap.ic_add);
        binding.includeActionBar.ivBack.setOnClickListener(this);
        binding.includeActionBar.toolbarMenuIcon.setOnClickListener(this);
    }

    private void initData() {
        contactList = OCPPrefUtils.getContacts();
    }

    private void initView() {
        contactsAdapter = new ContactsAdapter(ContactsActivity.this);
        contactsAdapter.setData(contactList);
        binding.rlContacts.setAdapter(contactsAdapter);
        binding.rlContacts.setLayoutManager(new LinearLayoutManager(ContactsActivity.this));
    }

    private void initListView() {
        contactsListAdapter = new ContactsListAdapter(ContactsActivity.this, contactList);
        binding.slvContacts.setAdapter(contactsListAdapter);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toolbar_menu_icon:
                ContactsCreateActivity.startContactsCreateActivity(ContactsActivity.this);
                break;
            case R.id.iv_back:
                finish();
                break;

        }

    }


}
