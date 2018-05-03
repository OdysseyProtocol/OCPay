package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityWalletDetailBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;

import static com.ocpay.wallet.Constans.WALLET.WALLET_ADDRESS;
import static com.ocpay.wallet.Constans.WALLET.WALLET_NAME;
import static com.snow.commonlibrary.utils.ShareUtils.toShare;

public class WalletDetailActivity extends BaseActivity implements View.OnClickListener {


    private ActivityWalletDetailBinding binding;
    private String walletAddress;
    private String walletName;

    public static void startWalletDetailActivity(Activity activity, String walletAddress, String walletName) {
        Intent intent = new Intent(activity, WalletDetailActivity.class);
        intent.putExtra(WALLET_NAME, walletName);
        intent.putExtra(WALLET_ADDRESS, walletAddress);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(WalletDetailActivity.this, R.layout.activity_wallet_detail);
        initActionBar();
        initView();
        initListener();


    }

    private void initListener() {

        binding.tvActionBackupMnemonic.setOnClickListener(this);
        binding.tvActionDeleteWallet.setOnClickListener(this);
        binding.llExportPrivateKey.setOnClickListener(this);
        binding.llExportKeystore.setOnClickListener(this);
        binding.llChangePwd.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
    }

    private void initView() {
        walletAddress = getIntent().getStringExtra(WALLET_ADDRESS);
        walletName = getIntent().getStringExtra(WALLET_NAME);
        WalletInfo walletInfo = WalletInfoDaoUtils.sqlByAddress(this, walletAddress);
        binding.tvWalletAddress.setText(walletInfo.getWalletAddress());
        binding.etWalletName.setText(walletInfo.getWalletName());

    }

    private void initActionBar() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                toShare(WalletDetailActivity.this, walletAddress, walletName);
                break;
            case R.id.ll_export_keystore:

                break;

            case R.id.ll_export_private_key:

                break;

            case R.id.ll_change_pwd:
                WalletModifyPwdActivity.startWalletModifyActivity(WalletDetailActivity.this, walletAddress, walletName);
                break;


        }

    }
}
