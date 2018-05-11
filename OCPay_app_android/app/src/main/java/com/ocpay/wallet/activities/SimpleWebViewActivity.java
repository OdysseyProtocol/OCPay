package com.ocpay.wallet.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityWebViewBinding;

public class SimpleWebViewActivity extends BaseActivity implements View.OnClickListener {


    WebView webContent;
    private ActivityWebViewBinding binding;

    public static void startWebViewActivity(String url, Context context) {
        if (context == null) return;
        Intent intent = new Intent(context, SimpleWebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        initActionBar();
        initLoading();

    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(R.string.activity_title_tx_query);
        binding.includeActionBar.ivBack.setOnClickListener(this);
    }

    private void initLoading() {
        //enable  js
        webContent = binding.webContent;
        webContent.getSettings().setJavaScriptEnabled(true);
        //web clickable
        webContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }
        });
        if (getIntent() != null) {
            String url = getIntent().getStringExtra("url");
            webContent.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        if (webContent != null && webContent.canGoBack()) {
            webContent.goBack();
            return;
        }
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
        }
    }
}
