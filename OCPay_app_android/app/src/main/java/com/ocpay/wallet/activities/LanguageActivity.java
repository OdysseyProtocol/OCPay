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

public class LanguageActivity extends BaseActivity implements View.OnClickListener {


    private ActivityCurrencyUnitBinding binding;
    private List<TextSelectBean> dataList;
    private TextSelectAdapter textSelectAdapter;
    private String language;
    private String selectLanguage;


    public static void startLanguageActivity(Activity activity) {

        Intent intent = new Intent(activity, LanguageActivity.class);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LanguageActivity.this, R.layout.activity_currency_unit);
        initActionBar();
        initData();
        initView();
        initListener();

    }

    private void initData() {

        dataList = new ArrayList<>();
        String[] languageList = getLanguageList();
        for (String s : languageList) {
            dataList.add(new TextSelectBean(s, false));
        }
        language = OCPPrefUtils.getLanguage(LanguageActivity.this);
        for (int i = 0; i < dataList.size(); i++) {
            if (language.equals(dataList.get(i).getText())) {
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
        textSelectAdapter = new TextSelectAdapter(LanguageActivity.this);
        binding.rlContent.setAdapter(textSelectAdapter);
        binding.rlContent.setLayoutManager(new LinearLayoutManager(LanguageActivity.this));
        textSelectAdapter.setData(dataList);
        textSelectAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {


            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {
                if (data instanceof TextSelectBean) {
                    textSelectAdapter.getmData().get(textSelectAdapter.getSelectPosition()).setSelected(false);
                    ((TextSelectBean) data).setSelected(true);
                    selectLanguage = ((TextSelectBean) data).getText();
                    textSelectAdapter.setSelectPosition(position);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toolbar_menu_icon:
                if (!language.equals(selectLanguage)) {
                    OCPPrefUtils.setLanguage(LanguageActivity.this, selectLanguage);
                }
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;

        }

    }

    private String[] getLanguageList() {
        String[] languages = getResources().getStringArray(R.array.language);

        return languages;
    }


}
