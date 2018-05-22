package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityGatheringBinding;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.qr.QRCodeUtils;
import com.snow.commonlibrary.log.MyLog;
import com.snow.commonlibrary.utils.RegularExpressionUtils;
import com.snow.commonlibrary.utils.ViewUtils;

import static com.ocpay.wallet.Constans.REGULAR.REGULAR_FLOAT;
import static com.ocpay.wallet.Constans.WALLET.TOKEN_NAME;
import static com.ocpay.wallet.Constans.WALLET.WALLET_ADDRESS;
import static com.snow.commonlibrary.utils.ShareUtils.toClipboardData;
import static com.snow.commonlibrary.utils.ShareUtils.toShare;

/**
 * Created by y on 2018/4/20.
 */

public class GatheringActivity extends BaseActivity implements View.OnClickListener {

    private ActivityGatheringBinding shareBinding;

    private String tokenName;
    private String walletAddress;
    private Handler handler;


    public static void startGatheringActivity(Activity activity, String tokenName, String address) {
        Intent intent = new Intent(activity, GatheringActivity.class);
        intent.putExtra(TOKEN_NAME, tokenName);
        intent.putExtra(WALLET_ADDRESS, address);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shareBinding = DataBindingUtil.setContentView(this, R.layout.activity_gathering);

        initView();


        initQRCode();
    }

    private void initQRCode() {
        handler = new Handler();
        shareBinding.etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        String amount = shareBinding.etAmount.getText().toString();
                        if (TextUtils.isEmpty(amount)) return;
                        MyLog.i("amount:" + amount);
                        boolean valid = RegularExpressionUtils.valid(amount, REGULAR_FLOAT);
                        if (!valid) {
                            shareBinding.etAmount.setText("");
                            Toast.makeText(MyApp.getContext(), "Input ErrorInfo", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        updateQRCode(walletAddress + ":amount:" + shareBinding.etAmount.getText().toString());
                    }
                };
                handler.postDelayed(runnable, 1000);
            }
        });
        updateQRCode(walletAddress);
    }

    private void initView() {
        tokenName = getIntent().getStringExtra(TOKEN_NAME);
        walletAddress = getIntent().getStringExtra(WALLET_ADDRESS);
        shareBinding.tvWalletAddress.setText(OCPWalletUtils.foldWalletAddress(walletAddress));
        shareBinding.includeActionBar.ivBack.setOnClickListener(this);
        shareBinding.includeActionBar.actionBarTitle.setText(R.string.activity_qr_code);
        shareBinding.includeActionBar.toolbarMenuIcon.setImageResource(R.mipmap.ic_share);
        shareBinding.includeActionBar.toolbarMenuIcon.setOnClickListener(this);
        shareBinding.btnAddressToClipboard.setOnClickListener(this);
    }

    private void updateQRCode(String code) {
        int qrCodeDimension = ViewUtils.dp2px(MyApp.getContext(), 260);
        QRCodeUtils.updateQRCode(shareBinding.qrCode, qrCodeDimension, code);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_address_to_clipboard:
                addressToClipboard();
                break;
            case R.id.iv_back:
                finish();
                break;

            case R.id.toolbar_menu_icon:
                String title = getResources().getString(R.string.activity_qr_code);
                toShare(GatheringActivity.this, walletAddress, title);
                break;
        }
    }


    private void addressToClipboard() {
        toClipboardData(MyApp.getContext(), "", walletAddress);
        Toast.makeText(MyApp.getContext(), R.string.address_copied_to_clipboard, Toast.LENGTH_SHORT).show();
    }
}
