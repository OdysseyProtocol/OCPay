package com.ocpay.wallet.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.greendao.WalletInfo;

/**
 * Created by y on 2018/5/21.
 */

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                enterActivity();
            }
        });

    }

    private void enterActivity() {
        WalletInfo currentWallet = OCPWallet.getCurrentWallet();
        if (currentWallet == null) {
            FirstLauncherActivity.startFirstLauncherActivity(SplashActivity.this);
        } else {
            MainActivity.startMainActivity(SplashActivity.this);
        }
        finish();
    }
}
