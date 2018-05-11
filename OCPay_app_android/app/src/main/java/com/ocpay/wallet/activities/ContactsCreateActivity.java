package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ocpay.wallet.R;
import com.ocpay.wallet.bean.Contact;
import com.ocpay.wallet.databinding.ActivityCreateContactsBinding;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.OCPPrefUtils;
import com.snow.commonlibrary.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_CONTACTS_ADDRESS;
import static com.ocpay.wallet.Constans.RXBUS.ACTION_CONTACTS_UPDATE;
import static com.ocpay.wallet.activities.QRReaderActivity.QR_CODE_MODE_READER;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.isEthAddress;

public class ContactsCreateActivity extends BaseActivity implements View.OnClickListener {


    private ActivityCreateContactsBinding binding;


    public static void startContactsCreateActivity(Activity activity) {
        Intent intent = new Intent(activity, ContactsCreateActivity.class);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ContactsCreateActivity.this, R.layout.activity_create_contacts);
        initActionBar();
        initView();
        initListener();

    }

    private void initListener() {

        binding.ivScan.setOnClickListener(this);

        RxBus.getInstance()
                .toObservable(ACTION_CONTACTS_ADDRESS, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        binding.etContactsAddress.setText(s);
                    }
                });


    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(R.string.activity_title_contacts);
        binding.includeActionBar.llToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.icon_close);
        binding.includeActionBar.ivBack.setOnClickListener(this);
        binding.includeActionBar.toolbarMenuIcon.setImageResource(R.mipmap.ic_complete);
        binding.includeActionBar.toolbarMenuIcon.setOnClickListener(this);
    }


    private void initView() {


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toolbar_menu_icon:
                addContacts();
                break;
            case R.id.iv_back:
                finish();
                break;


            case R.id.iv_scan:
                QRReaderActivity.startQRReaderActivity(ContactsCreateActivity.this, ACTION_CONTACTS_ADDRESS, QR_CODE_MODE_READER);
                break;


        }

    }

    private void addContacts() {
        String firstName = binding.etFirstName.getText().toString().trim();
        if (StringUtil.isEmpty(firstName)) {
            Toast.makeText(ContactsCreateActivity.this, "First Name not null", Toast.LENGTH_SHORT).show();
            return;
        }
        String address = binding.etContactsAddress.getText().toString().trim();

        if (!isEthAddress(address)) {
            Toast.makeText(ContactsCreateActivity.this, "address is error", Toast.LENGTH_SHORT).show();
            return;
        }


        List<Contact> contacts = OCPPrefUtils.getContacts();
        if (contacts == null) {
            contacts = new ArrayList<Contact>();
        }
        for (Contact c : contacts) {
            if (address.equals(c.getWalletAddress())) {
                Toast.makeText(ContactsCreateActivity.this, "address has exits", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Contact contact = new Contact(firstName, address);
        String familyName = binding.etFamilyName.getText().toString().trim();
        if (!StringUtil.isEmpty(familyName)) contact.setFamilyName(familyName);


        String phoneNumber = binding.etContactsPhoneNumber.getText().toString().trim();
        if (!StringUtil.isEmpty(phoneNumber)) contact.setPhoneName(phoneNumber);
        String email = binding.etContactsEmail.getText().toString().trim();
        if (!StringUtil.isEmpty(email)) contact.setEmail(email);
        String note = binding.etContactsNote.getText().toString().trim();
        if (!StringUtil.isEmpty(note)) contact.setNote(note);


        contacts.add(contact);

        OCPPrefUtils.putContacts(contacts);

        RxBus.getInstance().post(ACTION_CONTACTS_UPDATE,"");

        finish();
    }


}
