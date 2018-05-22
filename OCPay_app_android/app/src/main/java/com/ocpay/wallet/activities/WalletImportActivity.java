package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityImportWalletBinding;
import com.ocpay.wallet.fragment.fragmentadapter.MyFragmentAdapter;
import com.ocpay.wallet.fragment.walletimport.AddressWatchFragment;
import com.ocpay.wallet.fragment.walletimport.KeystoreFragment;
import com.ocpay.wallet.fragment.walletimport.MnemonicFragment;
import com.ocpay.wallet.fragment.walletimport.PrivateKeyFragment;

import java.util.ArrayList;
import java.util.List;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_IMPORT_WALLET_KEYSTORE;
import static com.ocpay.wallet.Constans.RXBUS.ACTION_IMPORT_WALLET_MNEMONIC;
import static com.ocpay.wallet.Constans.RXBUS.ACTION_IMPORT_WALLET_PRIVATE_KEY;
import static com.ocpay.wallet.Constans.RXBUS.ACTION_IMPORT_WALLET_WATCH;
import static com.ocpay.wallet.activities.QRReaderActivity.QR_CODE_MODE_READER;

/**
 * Created by y on 2018/4/21.
 */

public class WalletImportActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {


    private ActivityImportWalletBinding binding;
    private ViewPager vpContent;

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, WalletImportActivity.class));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_import_wallet);

        initView();

        initContentFragment();
        initTable();
    }

    private void initView() {
        vpContent = binding.vpContent;
        binding.include.actionBarTitle.setText(R.string.import_wallet);
        binding.include.ivBack.setImageResource(R.mipmap.ic_back);
        binding.include.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.include.ivBack.setOnClickListener(this);
        binding.include.toolbarMenuIcon.setImageResource(R.mipmap.ic_bar_sacn);
        binding.include.toolbarMenuIcon.setOnClickListener(this);


    }

    private void initTable() {
        binding.tabImportWallet.setTabMode(TabLayout.MODE_FIXED);
        binding.tabImportWallet.setupWithViewPager(binding.vpContent);
    }


    private void initContentFragment() {
        //fragment
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new MnemonicFragment());
        mFragmentList.add(new KeystoreFragment());
        mFragmentList.add(new PrivateKeyFragment());
        mFragmentList.add(new AddressWatchFragment());

        //title
        List<String> titleList = new ArrayList<>();
        titleList.add(getResources().getString(R.string.import_wallet_mnemonic));
        titleList.add(getResources().getString(R.string.import_wallet_official_wallet));
        titleList.add(getResources().getString(R.string.import_wallet_private_key));
        titleList.add(getResources().getString(R.string.import_wallet_observed));

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragmentList, titleList);
        vpContent.setAdapter(adapter);
        ViewGroup.LayoutParams layoutParams = vpContent.getLayoutParams();
        layoutParams.height = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        vpContent.setLayoutParams(layoutParams);
        vpContent.setOffscreenPageLimit(2);
        vpContent.addOnPageChangeListener(this);
        vpContent.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.toolbar_menu_icon:
                QRReaderActivity.startQRReaderActivity(WalletImportActivity.this, getQRRequestId(), QR_CODE_MODE_READER);
                break;
        }
    }


    public int getQRRequestId() {
        int id = vpContent.getCurrentItem();
        return id == 0 ? ACTION_IMPORT_WALLET_MNEMONIC :
                id == 1 ? ACTION_IMPORT_WALLET_KEYSTORE :
                        id == 2 ? ACTION_IMPORT_WALLET_PRIVATE_KEY :
                                id == 3 ? ACTION_IMPORT_WALLET_WATCH : -1;

    }
}
