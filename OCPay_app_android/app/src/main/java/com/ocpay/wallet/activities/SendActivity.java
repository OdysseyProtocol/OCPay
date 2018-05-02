package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivitySendBinding;
import com.ocpay.wallet.view.CollapseAnimator;

import static com.ocpay.wallet.Constans.WALLET.ADDRESS_FROM;
import static com.ocpay.wallet.Constans.WALLET.TOKEN_NAME;

public class SendActivity extends BaseActivity {


    private ActivitySendBinding binding;
    private LinearLayout expertMode;

    public static void startSendActivity(Activity activity, String fromAddress, String tokenName) {

        Intent intent = new Intent(activity, SendActivity.class);
        intent.putExtra(TOKEN_NAME, tokenName);
        intent.putExtra(ADDRESS_FROM, fromAddress);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SendActivity.this, R.layout.activity_send);
        expertMode = binding.expendMode;
        binding.btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (expertMode.getVisibility() == View.GONE) {
                    CollapseAnimator.expand(expertMode);
                } else {
                    CollapseAnimator.collapse(expertMode);
                }
            }
        });
    }
}
