package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.TokenTransferAdapter;
import com.ocpay.wallet.databinding.ActivityTxRecordsBinding;
import com.ocpay.wallet.http.client.EthScanHttpClientIml;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.OCPPrefUtils;
import com.ocpay.wallet.utils.web3j.response.CustomTransaction;
import com.ocpay.wallet.utils.web3j.response.EtherScanTxListResponse;
import com.snow.commonlibrary.recycleview.BaseAdapter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_TRANSACTION_CENTER_MERGE_LIST;

public class TransactionCenterActivity extends BaseActivity implements View.OnClickListener, BaseAdapter.OnItemClickListener {


    private ActivityTxRecordsBinding binding;
    private TokenTransferAdapter transferAdapter;


    public static void startContactsActivity(Activity activity) {
        Intent intent = new Intent(activity, TransactionCenterActivity.class);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(TransactionCenterActivity.this, R.layout.activity_tx_records);

        initActionBar();
        initView();
        initRxBus();
        showLoading(false);
        pullData();

    }

    private void pullData() {
        String startBlockNo = OCPPrefUtils.getFirstStartBlockNo();
        //todo modify
        //EthScanHttpClientIml.getTransactionList(ACTION_TRANSACTION_CENTER_MERGE_LIST, OCPWallet.getCurrentWallet().getWalletAddress(), startBlockNo, Constans.ETH.DEFAULT_END_BLOCKNO);
        EthScanHttpClientIml.getTransactionList(ACTION_TRANSACTION_CENTER_MERGE_LIST, Constans.TEST.WALLET_ADDRESS, startBlockNo, Constans.ETH.DEFAULT_END_BLOCKNO);
    }

    private void initRxBus() {
        Disposable disposable = RxBus.getInstance().toObservable(ACTION_TRANSACTION_CENTER_MERGE_LIST, EtherScanTxListResponse.class).subscribe(new Consumer<EtherScanTxListResponse>() {
            @Override
            public void accept(EtherScanTxListResponse o) throws Exception {
                transferAdapter.setData(o.getResult());
                dismissLoading();
                if (binding.refresh.isRefreshing()) {
                    binding.refresh.setRefreshing(false);
                }
            }
        });
        addDisposable(disposable);

    }

    private void initView() {
        transferAdapter = new TokenTransferAdapter(this);
        binding.rlTxRecords.setLayoutManager(new LinearLayoutManager(TransactionCenterActivity.this));
        binding.rlTxRecords.setAdapter(transferAdapter);
        transferAdapter.setOnItemClickListener(this);
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullData();
            }
        });
    }


    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(OCPWallet.getCurrentWallet().getWalletName());
        binding.includeActionBar.llToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_close_black);
        binding.includeActionBar.toolbarMenuIcon.setVisibility(View.GONE);
        binding.includeActionBar.ivBack.setOnClickListener(this);
        binding.includeActionBar.viewLine.setVisibility(View.VISIBLE);
        binding.includeActionBar.toolbarMenuIcon.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_menu_icon:
                ContactsCreateActivity.startContactsCreateActivity(TransactionCenterActivity.this);
                break;
            case R.id.iv_back:
                finish();
                break;

        }

    }


    @Override
    public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {
        TransactionDetailActivity.startTxDetailActivity(TransactionCenterActivity.this, (CustomTransaction) data);


    }
}
