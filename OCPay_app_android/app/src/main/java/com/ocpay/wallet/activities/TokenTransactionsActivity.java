package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.TokenTransferAdapter;
import com.ocpay.wallet.adapter.WalletManageListsAdapter;
import com.ocpay.wallet.databinding.ActivityTokenDetailsBinding;
import com.ocpay.wallet.http.client.HttpClient;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.RateUtils;
import com.ocpay.wallet.utils.web3j.response.EthTransaction;
import com.ocpay.wallet.utils.web3j.response.EthTransactionResponse;
import com.ocpay.wallet.utils.web3j.response.EventLogTransactionResponse;
import com.ocpay.wallet.utils.web3j.response.EventTransaction;
import com.snow.commonlibrary.log.MyLog;
import com.snow.commonlibrary.recycleview.BaseAdapter;

import java.math.BigDecimal;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_UPDATE_TRANSACTION_LIST;
import static com.ocpay.wallet.Constans.TEST.OCN_TOKEN_ADDRESS;
import static com.ocpay.wallet.Constans.WALLET.TOKEN_BALANCE;
import static com.ocpay.wallet.Constans.WALLET.TOKEN_NAME;

public class TokenTransactionsActivity extends BaseActivity implements View.OnClickListener, BaseAdapter.OnItemClickListener {


    private ActivityTokenDetailsBinding binding;
    private WalletManageListsAdapter manageListsAdapter;
    private String tokenName;
    private TokenTransferAdapter transferAdapter;
    private BigDecimal tokenBalance = new BigDecimal(0);

    public static void startTokenTransactionActivity(Activity activity, String tokenName, String tokenBalance) {
        Intent intent = new Intent(activity, TokenTransactionsActivity.class);
        intent.putExtra(TOKEN_NAME, tokenName);
        intent.putExtra(TOKEN_BALANCE, tokenBalance);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(TokenTransactionsActivity.this, R.layout.activity_token_details);
        initActionBar();
        showLoading(false);

        initData();

        initView();

        initRxbus();

        getTokenTrList();
    }

    private void initData() {

        tokenName = getIntent().getStringExtra(TOKEN_NAME);
        String strTokenBalance = getIntent().getStringExtra(TOKEN_BALANCE);
        if (strTokenBalance != null) {
            tokenBalance = new BigDecimal(strTokenBalance);
        }
    }


    private void initRxbus() {
        Disposable disposable = null;
        if (tokenName.equals("ETH")) {
            disposable = RxBus.getInstance()
                    .toObservable(ACTION_UPDATE_TRANSACTION_LIST, EthTransactionResponse.class)
                    .subscribe(new Consumer<EthTransactionResponse>() {
                        @Override
                        public void accept(EthTransactionResponse ethTransactionResponse) throws Exception {
                            dismissLoading();

                        }
                    });

        } else {
            disposable = RxBus.getInstance()
                    .toObservable(ACTION_UPDATE_TRANSACTION_LIST, EventLogTransactionResponse.class)
                    .subscribe(new Consumer<EventLogTransactionResponse>() {
                        @Override
                        public void accept(EventLogTransactionResponse ethTransactionResponse) throws Exception {
                            dismissLoading();
                            MyLog.i("ß --------ß");
                            transferAdapter.setData(ethTransactionResponse.getResult());
                            transferAdapter.notifyDataSetChanged();
                        }
                    });
        }
        addDisposable(disposable);


    }

    private void initActionBar() {
        binding.tvTitle.setText(tokenName);
        binding.ivBack.setOnClickListener(this);
    }

    private void initView() {
        transferAdapter = new TokenTransferAdapter(this);

        binding.rlTokenTransactions.setLayoutManager(new LinearLayoutManager(TokenTransactionsActivity.this));
        binding.rlTokenTransactions.setAdapter(transferAdapter);
        transferAdapter.setOnItemClickListener(this);
        binding.tvTokenBalance.setText(tokenBalance.toString());
        String estimateToken = RateUtils.estimateToken(tokenName, tokenBalance);
        binding.tvTokenValue.setText(estimateToken);
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


    public void getTokenTrList() {
        String walletAddress = OCPWallet.getCurrentWallet().getWalletAddress();
        HttpClient.Builder
                .getEthScanServer()
                .getTokenTransactionList(OCN_TOKEN_ADDRESS, walletAddress, walletAddress, "1000", "last")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<EventLogTransactionResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(EventLogTransactionResponse o) {
                                RxBus.getInstance().post(Constans.RXBUS.ACTION_UPDATE_TRANSACTION_LIST, o);
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }

    @Override
    public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {

        EthTransaction ethTransaction = null;
        EventTransaction eventTransaction = null;
        if (data instanceof EthTransaction) {
            ethTransaction = (EthTransaction) data;
        }
        if (data instanceof EventTransaction) {
            eventTransaction = (EventTransaction) data;
        }

        TransactionDetailActivity.startTxDetailActivity(this, ethTransaction, eventTransaction);


    }
}
