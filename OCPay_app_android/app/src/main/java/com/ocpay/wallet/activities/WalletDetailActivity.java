package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityWalletDetailBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.widget.dialog.WarmDialog;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_TOKEN_WALLET_MANAGE_UPDATE;
import static com.ocpay.wallet.Constans.WALLET.WALLET_ADDRESS;
import static com.ocpay.wallet.Constans.WALLET.WALLET_NAME;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.foldWalletAddress;

public class WalletDetailActivity extends BaseActivity implements View.OnClickListener {


    private ActivityWalletDetailBinding binding;
    private String walletAddress;
    private String walletName;
    private WalletInfo walletInfo;
    private boolean hintIsShow;

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
        binding.ivComplete.setOnClickListener(this);
        binding.ivShowHint.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
    }

    private void initView() {
        hintIsShow = false;
        walletAddress = getIntent().getStringExtra(WALLET_ADDRESS);
        walletName = getIntent().getStringExtra(WALLET_NAME);
        walletInfo = WalletInfoDaoUtils.sqlByAddress(this, walletAddress);
        binding.tvWalletAddress.setText(foldWalletAddress(walletInfo.getWalletAddress()));
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
            case R.id.iv_complete:
                String newWalletName = binding.etWalletName.getText().toString().trim();
                if (walletInfo == null || walletInfo.getWalletName().equals(newWalletName)) {
                    finish();
                    return;
                }

                if (WalletInfoDaoUtils.sqlByWalletName(MyApp.getContext(), newWalletName) != null) {
                    //todo toast
                    WarmDialog.getInstance(WalletDetailActivity.this).show();
                    WarmDialog.getInstance(WalletDetailActivity.this).setTip(getString(R.string.dialog_tip_name_token));
                    return;
                }
                walletInfo.setWalletName(newWalletName);
                WalletInfoDaoUtils.update(MyApp.getContext(), walletInfo);
                RxBus.getInstance().post(ACTION_TOKEN_WALLET_MANAGE_UPDATE, "");

                break;
            case R.id.ll_export_keystore:

                break;

            case R.id.ll_export_private_key:

                break;

            case R.id.ll_change_pwd:
                WalletModifyPwdActivity.startWalletModifyActivity(WalletDetailActivity.this, walletAddress, walletName);
                break;


            case R.id.iv_show_hint:
                hintIsShow = !hintIsShow;
                int icRes = hintIsShow ? R.mipmap.ic_show_pwd_hint : R.mipmap.ic_hide_pwd_hint;
                String tipContent = !hintIsShow ? "***********" : walletInfo.getPasswordTip() == null ? "" : walletInfo.getPasswordTip();
                binding.ivShowHint.setImageResource(icRes);
                binding.tvPwdHint.setText(tipContent);
                break;


        }

    }


    @Override
    protected void onDestroy() {
        WarmDialog.getInstance(WalletDetailActivity.this).destroy();
        super.onDestroy();
    }
}
