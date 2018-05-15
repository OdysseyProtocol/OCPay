package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.TextSelectAdapter;
import com.ocpay.wallet.bean.TextSelectBean;
import com.ocpay.wallet.databinding.ActivityCurrencyUnitBinding;
import com.ocpay.wallet.utils.OCPPrefUtils;
import com.snow.commonlibrary.recycleview.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CurrencyUnitActivity extends BaseActivity implements View.OnClickListener {


    private ActivityCurrencyUnitBinding binding;
    public static final int ITEM_USE_AGREE = 0;
    public static final int ITEM_PRIVACY_POLICY = 1;
    public static final int ITEM_VERSION_LOG = 2;
    public static final int ITEM_PRODUCT_GUIDE = 3;
    public static final int ITEM_CHECK_VERSION = 4;
    private List<TextSelectBean> dataList;
    private TextSelectAdapter textSelectAdapter;
    private String currency;
    private String selectCurrency;


    public static void startAboutActivity(Activity activity) {

        Intent intent = new Intent(activity, CurrencyUnitActivity.class);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(CurrencyUnitActivity.this, R.layout.activity_currency_unit);
        initActionBar();
        initData();
        initView();
        initListener();

    }

    private void initData() {

        dataList = new ArrayList<>();
        dataList.add(new TextSelectBean("CNY", false, TextSelectBean.CURRENCY.CNY));
        dataList.add(new TextSelectBean("USD", false, TextSelectBean.CURRENCY.USD));

        currency = OCPPrefUtils.getCurrency();
        for (int i = 0; i < dataList.size(); i++) {
            if (currency.equals(dataList.get(i).getText())) {
                dataList.get(i).setSelected(true);
            }
        }

    }

    private void initListener() {

    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(R.string.activity_currency_unit);
        binding.includeActionBar.llToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_back);
        binding.includeActionBar.toolbarMenuIcon.setVisibility(View.VISIBLE);
        binding.includeActionBar.toolbarMenuIcon.setImageResource(R.mipmap.ic_complete);
        binding.includeActionBar.toolbarMenuIcon.setOnClickListener(this);
        binding.includeActionBar.ivBack.setOnClickListener(this);
    }


    private void initView() {
        textSelectAdapter = new TextSelectAdapter(CurrencyUnitActivity.this);
        binding.rlContent.setAdapter(textSelectAdapter);
        binding.rlContent.setLayoutManager(new LinearLayoutManager(CurrencyUnitActivity.this));
        textSelectAdapter.setData(dataList);
        textSelectAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {


            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {
                if (data instanceof TextSelectBean) {
                    textSelectAdapter.getmData().get(textSelectAdapter.getSelectPosition()).setSelected(false);
                    ((TextSelectBean) data).setSelected(true);
                    selectCurrency = ((TextSelectBean) data).getText();
                    textSelectAdapter.setSelectPosition(position);
                }
            }
        });
    }


    public static class AboutUsListener implements View.OnClickListener {

        private int item;

        public AboutUsListener(int item) {
            this.item = item;
        }

        @Override
        public void onClick(View v) {
            switch (item) {
                case ITEM_USE_AGREE:
                    break;
                case ITEM_CHECK_VERSION:
                    break;
                case ITEM_PRIVACY_POLICY:
                    break;
                case ITEM_PRODUCT_GUIDE:
                    break;
                case ITEM_VERSION_LOG:
                    break;

            }

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toolbar_menu_icon:
                if (!currency.equals(selectCurrency)) {
                    OCPPrefUtils.setCurrency(selectCurrency);
                }
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;

        }

    }


}
