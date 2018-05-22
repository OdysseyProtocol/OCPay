package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.WalletManageListsAdapter;
import com.ocpay.wallet.databinding.ActivityWalletManageBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.http.rx.RxBus;
import com.snow.commonlibrary.recycleview.BaseAdapter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class WalletManageActivity extends BaseActivity implements View.OnClickListener {


    private ActivityWalletManageBinding binding;
    private LinearLayout expertMode;
    private WalletManageListsAdapter manageListsAdapter;

    public static void startWalletMgActivity(Activity activity) {
        Intent intent = new Intent(activity, WalletManageActivity.class);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(WalletManageActivity.this, R.layout.activity_wallet_manage);
        initActionBar();
        init();
        initRx();


    }

    private void initRx() {
        Disposable disposable = RxBus.getInstance().toObservable(Constans.RXBUS.ACTION_TOKEN_WALLET_MANAGE_UPDATE, String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                List<WalletInfo> walletInfos = WalletInfoDaoUtils.sqlAll(WalletManageActivity.this);
                manageListsAdapter.setData(walletInfos);
            }
        });
        addDisposable(disposable);
    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(R.string.activity_wallet_manager);
        binding.includeActionBar.llToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_back);
        binding.includeActionBar.ivBack.setOnClickListener(this);
        binding.includeActionBar.toolbarMenuIcon.setVisibility(View.GONE);


    }

    private void init() {

        manageListsAdapter = new WalletManageListsAdapter(WalletManageActivity.this);
        List<WalletInfo> walletInfos = WalletInfoDaoUtils.sqlAll(WalletManageActivity.this);
        manageListsAdapter.setData(walletInfos);

        binding.rlWalletManageList.setLayoutManager(new LinearLayoutManager(WalletManageActivity.this));
        binding.rlWalletManageList.setAdapter(manageListsAdapter);
        manageListsAdapter.notifyDataSetChanged();
        binding.includeBottomButton.rlCreateWallet.setOnClickListener(this);
        binding.includeBottomButton.rlImportWallet.setOnClickListener(this);
        manageListsAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {
                if (data instanceof WalletInfo) {
                    WalletDetailActivity.startWalletDetailActivity(WalletManageActivity.this, ((WalletInfo) data).getWalletAddress(), ((WalletInfo) data).getWalletName());
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_create_wallet:
                WalletCreateActivity.startActivity(this);
                break;
            case R.id.rl_import_wallet:
                WalletImportActivity.startActivity(this);
                break;
        }
    }
}
