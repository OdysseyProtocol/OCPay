package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityFirstLauncherBinding;

/**
 * Created by y on 2018/5/21.
 */

public class FirstLauncherActivity extends BaseActivity implements View.OnClickListener {


    private ActivityFirstLauncherBinding binding;

    public static void startFirstLauncherActivity(Activity activity) {
        activity.startActivity(new Intent(activity, FirstLauncherActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(FirstLauncherActivity.this, R.layout.activity_first_launcher);
        binding.tvCreateWallet.setOnClickListener(this);
        binding.tvImportWallet.setOnClickListener(this);
        getPermission();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_wallet:
                WalletCreateActivity.startActivity(FirstLauncherActivity.this);
                break;
            case R.id.tv_import_wallet:
                WalletImportActivity.startActivity(FirstLauncherActivity.this);
                break;
        }

    }
}
