package com.ocpay.wallet.fragment.mainhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.R;
import com.ocpay.wallet.activities.ContactsActivity;
import com.ocpay.wallet.activities.NotificationActivity;
import com.ocpay.wallet.activities.SystemSettingsActivity;
import com.ocpay.wallet.activities.WalletImportActivity;
import com.ocpay.wallet.activities.WalletManageActivity;
import com.ocpay.wallet.adapter.SettingsAdapter;
import com.ocpay.wallet.bean.SettingsBean;
import com.ocpay.wallet.databinding.FragmentMeBinding;
import com.ocpay.wallet.fragment.BaseFragment;
import com.snow.commonlibrary.recycleview.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.ocpay.wallet.bean.SettingsBean.TYPE.ABOUT_US;
import static com.ocpay.wallet.bean.SettingsBean.TYPE.CONTACT;
import static com.ocpay.wallet.bean.SettingsBean.TYPE.HELP;
import static com.ocpay.wallet.bean.SettingsBean.TYPE.NOTIFICATION;
import static com.ocpay.wallet.bean.SettingsBean.TYPE.SYSTEM;
import static com.ocpay.wallet.bean.SettingsBean.TYPE.USER;

public class MeFragment extends BaseFragment<FragmentMeBinding> implements View.OnClickListener {

    private List<SettingsBean> settingsList;
    private SettingsAdapter settingsAdapter;

    @Override
    public int setContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void loadData() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initActionBar();
        initView();
        initListener();


    }

    private void initListener() {
        bindingView.llImportWallet.setOnClickListener(this);
        bindingView.llManageWallet.setOnClickListener(this);

        settingsAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {
                if (data instanceof SettingsBean) {
                    SettingsBean.TYPE type = ((SettingsBean) data).getType();
                    actionSetting(type);
                }
            }
        });


    }

    private void actionSetting(SettingsBean.TYPE type) {
        if (getActivity() == null) return;
        switch (type) {
            case HELP:
                break;
            case USER:
                break;
            case CONTACT:
                ContactsActivity.startContactsActivity(getActivity());
                break;
            case SYSTEM:
                SystemSettingsActivity.startSystemSettingsActivity(getActivity());
                break;
            case NOTIFICATION:
                NotificationActivity.startNotificationActivity(getActivity());
                break;
            case ABOUT_US:
                break;
        }


    }

    private void initActionBar() {
        bindingView.includeActionBar.ivBack.setVisibility(View.GONE);
        bindingView.includeActionBar.actionBarTitle.setText(R.string.fragment_title_me);
        bindingView.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        bindingView.includeActionBar.toolbarMenuIcon.setVisibility(View.GONE);

    }

    private void initData() {
        settingsList = new ArrayList<>();
        settingsList.add(new SettingsBean(NOTIFICATION, R.mipmap.ic_settings_message, getString(R.string.settings_message_center)));
        settingsList.add(new SettingsBean(CONTACT, R.mipmap.ic_settings_contacts, getString(R.string.settings_contacts)));
        settingsList.add(new SettingsBean(SYSTEM, R.mipmap.ic_settings, getString(R.string.settings_system)));
        settingsList.add(new SettingsBean(USER, R.mipmap.ic_settings_user, getString(R.string.settings_user)));
        settingsList.add(new SettingsBean(HELP, R.mipmap.ic_settings_help, getString(R.string.settings_help)));
        settingsList.add(new SettingsBean(ABOUT_US, R.mipmap.ic_settings_about, getString(R.string.settings_about_us)));
    }

    private void initView() {
        settingsAdapter = new SettingsAdapter(getContext());

        bindingView.rlSettings.setAdapter(settingsAdapter);
        bindingView.rlSettings.setLayoutManager(new LinearLayoutManager(getContext()));
        settingsAdapter.setData(settingsList);


    }

    @Override
    public void onClick(View v) {
        if (getActivity() == null) return;
        switch (v.getId()) {
            case R.id.ll_manage_wallet:
                WalletManageActivity.startWalletMgActivity(getActivity());
                break;
            case R.id.ll_import_wallet:
                WalletImportActivity.startActivity(getActivity());
                break;
        }

    }
}
