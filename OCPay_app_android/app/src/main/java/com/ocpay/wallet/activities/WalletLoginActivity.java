package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.bean.PickerData;
import com.ocpay.wallet.databinding.ActivityWalletLoginBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.utils.OCPPrefUtils;
import com.ocpay.wallet.utils.PickerUtil;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.snow.commonlibrary.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static com.ocpay.wallet.Constans.WALLET.ADDRESS_FROM;
import static com.ocpay.wallet.Constans.WALLET.TOKEN_NAME;

public class WalletLoginActivity extends BaseActivity implements View.OnClickListener, PickerUtil.OnWheelViewClick {


    private ActivityWalletLoginBinding binding;
    private LinearLayout expertMode;
    private List<PickerData> pickerData;
    private int selected;
    private FingerprintManagerCompat fingerprintManager;
    private List<WalletInfo> walletInfos;
    private String currentAddress;

    public static void startWalletLoginActivity(Activity activity, String fromAddress, String tokenName) {

        Intent intent = new Intent(activity, WalletLoginActivity.class);
        intent.putExtra(TOKEN_NAME, tokenName);
        intent.putExtra(ADDRESS_FROM, fromAddress);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(WalletLoginActivity.this, R.layout.activity_wallet_login);
        initData();
        initView();

    }

    private void initView() {
        binding.llLoginWalletAddress.setOnClickListener(this);
        binding.tvCreateWallet.setOnClickListener(this);
        binding.tvLoginWalletAddress.setText(currentAddress);

    }


    public void alertOption(ArrayList<?> list, int selectIndex, PickerUtil.OnWheelViewClick onWheelViewClick) {
        PickerUtil.alertBottomWheelOption(this, list, selectIndex, onWheelViewClick);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_login_wallet_address:
                alertOption((ArrayList<PickerData>) pickerData, selected, this);
                break;
            case R.id.tv_create_wallet:
                actionLogin();
                break;
        }
    }

    private void actionLogin() {
        boolean success = false;
        try {
            success = OCPWalletUtils.loginWallet(walletInfos.get(selected).getWalletAddress(), binding.etWalletPassword.getText().toString());
        } catch (Exception e) {
            Toast.makeText(MyApp.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }
        if (!success) {
            Toast.makeText(MyApp.getContext(), "password is error", Toast.LENGTH_SHORT).show();
            return;
        }
        finish();

    }


    public void initData() {
        Object bean = OCPPrefUtils.getCurrentWallet();
        walletInfos = WalletInfoDaoUtils.sqlAll(MyApp.getContext());
        currentAddress = "";

        if (bean != null && (bean instanceof WalletInfo)) {
            currentAddress = ((WalletInfo) bean).getWalletAddress();
        } else {
            if (walletInfos != null && walletInfos.size() > 0) {
                currentAddress = walletInfos.get(0).getWalletAddress();
            }
        }

        if (StringUtil.isEmpty(currentAddress)) {
            //todo 创建wallet
            return;
        }

        pickerData = new ArrayList<>();
        selected = -1;
        for (int i = 0; i < walletInfos.size(); i++) {
            if (currentAddress.equals(walletInfos.get(i).getWalletAddress())) {
                selected = i;
            }
            pickerData.add(new PickerData(i + "", OCPWalletUtils.foldWalletAddress(walletInfos.get(i).getWalletAddress())));
        }

    }


    @Override
    public void onClick(View view, int position) {
        selected = position;
        binding.tvLoginWalletAddress.setText(OCPWalletUtils.foldWalletAddress(walletInfos.get(position).getWalletAddress()));

    }


}
