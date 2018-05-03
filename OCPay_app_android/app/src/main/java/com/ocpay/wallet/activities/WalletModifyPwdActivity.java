package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityWalletModifyPwdBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.eth.bean.OCPWalletFile;
import com.ocpay.wallet.utils.wallet.WalletStorage;

import static com.ocpay.wallet.Constans.WALLET.WALLET_ADDRESS;
import static com.ocpay.wallet.Constans.WALLET.WALLET_NAME;

public class WalletModifyPwdActivity extends BaseActivity implements View.OnClickListener {


    private ActivityWalletModifyPwdBinding binding;
    private LinearLayout expertMode;
    private String walletAddress;

    public static void startWalletModifyActivity(Activity activity, String walletAddress, String walletName) {
        Intent intent = new Intent(activity, WalletModifyPwdActivity.class);
        intent.putExtra(WALLET_NAME, walletName);
        intent.putExtra(WALLET_ADDRESS, walletAddress);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(WalletModifyPwdActivity.this, R.layout.activity_wallet_modify_pwd);
        initActionBar();
        initView();
        initListener();
    }

    private void initListener() {
        binding.includeActionBar.ivBack.setOnClickListener(this);
        binding.includeActionBar.toolbarMenuIcon.setOnClickListener(this);

    }

    private void initView() {
        walletAddress = getIntent().getStringExtra(WALLET_ADDRESS);
        WalletInfo walletInfo = WalletInfoDaoUtils.sqlByAddress(this, walletAddress);


    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(R.string.activity_modify_title);
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_back);
        binding.includeActionBar.toolbarMenuIcon.setImageResource(R.mipmap.ic_complete);
    }


    private boolean checkInput() {
        //pwd
        String pwd = binding.etPwd.getText().toString().trim();
        String currentPwd = binding.etCurrentPwd.getText().toString().trim();
        if (pwd.length() <= 8 || currentPwd.length() <= 8) {
            Toast.makeText(MyApp.getContext(), "password length  is short", Toast.LENGTH_LONG).show();
            return false;
        }
        //rep pwd
        String repPwd = binding.etCheckPwd.getText().toString().trim();
        if (!pwd.equals(repPwd)) {
            Toast.makeText(MyApp.getContext(), "password is different", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.toolbar_menu_icon:
                boolean checkInput = checkInput();
                if (!checkInput) return;
                modifyWalletPwd();
                break;
            case R.id.ll_export_keystore:

                break;

            case R.id.ll_export_private_key:

                break;

            case R.id.ll_change_pwd:
                break;


        }

    }

    private void modifyWalletPwd() {
        try {
            OCPWalletFile ocpWallet = WalletStorage.getInstance().getOCPWallet(walletAddress);
            OCPWalletFile ocpWalletFile = OCPWalletUtils.modifyWalletPwd(binding.etCurrentPwd.getText().toString().trim(),
                    binding.etPwd.getText().toString().trim(),
                    ocpWallet);
            WalletStorage.getInstance().saveWalletFile(WalletModifyPwdActivity.this, ocpWalletFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
