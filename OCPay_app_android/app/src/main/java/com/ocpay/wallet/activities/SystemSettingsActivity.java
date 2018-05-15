package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivitySystemSettingsBinding;

public class SystemSettingsActivity extends BaseActivity implements View.OnClickListener {


    private ActivitySystemSettingsBinding binding;
    public static final int ITEM_LANGUAGE = 0;
    public static final int ITEM_CURRENCY = 1;


    public static void startSystemSettingsActivity(Activity activity) {
        Intent intent = new Intent(activity, SystemSettingsActivity.class);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SystemSettingsActivity.this, R.layout.activity_system_settings);
        initActionBar();
        initView();
        initListener();

    }

    private void initListener() {


    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(R.string.activity_system_settings);
        binding.includeActionBar.llToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.icon_close);
        binding.includeActionBar.toolbarMenuIcon.setVisibility(View.GONE);
        binding.includeActionBar.ivBack.setOnClickListener(this);

    }


    private void initView() {
        binding.includeLanguage.tvSettingsName.setText(R.string.activity_settings_language);
        binding.includeUnit.tvSettingsName.setText(R.string.activity_settings_unit);
        binding.includeUnit.llSettingsItem.setOnClickListener(new SettingsListener(ITEM_LANGUAGE, this));
        binding.includeUnit.llSettingsItem.setOnClickListener(new SettingsListener(ITEM_CURRENCY, this));
        binding.shTouch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //todo verify touch
            }
        });
    }


    public static class SettingsListener implements View.OnClickListener {

        private Activity mActivity;
        private int item;

        public SettingsListener(int item, Activity mActivity) {
            this.mActivity = mActivity;
            this.item = item;
        }

        @Override
        public void onClick(View v) {
            switch (item) {
                case ITEM_LANGUAGE:
                    LanguageActivity.startLanguageActivity(mActivity);

                    break;
                case ITEM_CURRENCY:
                    CurrencyUnitActivity.startAboutActivity(mActivity);
                    break;
            }

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toolbar_menu_icon:
                ContactsCreateActivity.startContactsCreateActivity(SystemSettingsActivity.this);
                break;
            case R.id.iv_back:
                finish();
                break;

        }

    }


}
