package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityTransactionDetailBinding;
import com.ocpay.wallet.utils.qr.QRCodeUtils;
import com.ocpay.wallet.utils.web3j.response.BaseTransaction;
import com.ocpay.wallet.utils.web3j.response.CustomTransaction;
import com.ocpay.wallet.utils.web3j.response.EthTransaction;
import com.ocpay.wallet.utils.web3j.response.EventTransaction;
import com.snow.commonlibrary.log.MyLog;
import com.snow.commonlibrary.utils.DateUtils;
import com.snow.commonlibrary.utils.ShareUtils;

import java.text.ParseException;

import static com.ocpay.wallet.Constans.WALLET.TX_CUSTOM;
import static com.ocpay.wallet.Constans.WALLET.TX_ETH;
import static com.ocpay.wallet.Constans.WALLET.TX_EVENT_LOG;
import static com.snow.commonlibrary.utils.DateUtils.dmyhmsDatePattern;

/**
 * Created by y on 2018/4/20.
 */

public class TransactionDetailActivity extends BaseActivity implements View.OnClickListener {

    private ActivityTransactionDetailBinding binding;
    private EventTransaction eventTransaction;
    private EthTransaction ethTransaction;
    private String txHash;
    private String txUrl;
    private CustomTransaction customTransaction;


    public static void startTxDetailActivity(Activity activity, BaseTransaction transaction) {

        Intent intent = new Intent(activity, TransactionDetailActivity.class);
        if (transaction instanceof EthTransaction) {
            intent.putExtra(TX_ETH, (EthTransaction) transaction);

        }
        if (transaction instanceof CustomTransaction) {
            intent.putExtra(TX_CUSTOM, (CustomTransaction) transaction);

        }
        if (transaction instanceof EventTransaction) {
            intent.putExtra(TX_EVENT_LOG, (EventTransaction) transaction);

        }

        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_detail);
        initData();
        initView();
        initListener();
        initQRCode();

    }

    private void initQRCode() {
        txUrl = Constans.HTTP.API_TXHAH + txHash;
        MyLog.i("url:" + txUrl);
        int dimension = binding.ivQrCodeTxUrl.getWidth();
        QRCodeUtils.updateQRCode(binding.ivQrCodeTxUrl, dimension, txUrl);


    }


    private void initData() {
        eventTransaction = (EventTransaction) getIntent().getSerializableExtra(Constans.WALLET.TX_EVENT_LOG);
        ethTransaction = (EthTransaction) getIntent().getSerializableExtra(Constans.WALLET.TX_ETH);
        customTransaction = (CustomTransaction) getIntent().getSerializableExtra(Constans.WALLET.TX_CUSTOM);
    }

    private void initView() {
        initEventTransaction(eventTransaction);
        initEthTransaction(ethTransaction);
        initCustomTransaction(customTransaction);

    }


    private void initListener() {
        binding.tvClipTxUrl.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
        binding.tvTransferTxhash.setOnClickListener(this);
    }

    private void initEthTransaction(EthTransaction ethTransaction) {
        if (ethTransaction == null) return;
        txHash = ethTransaction.getHash();
    }

    private void initEventTransaction(EventTransaction eventTransaction) {
        if (eventTransaction == null) return;
        txHash = eventTransaction.getTransactionHash();
        binding.tvTransferAmount.setText(eventTransaction.getTransferAmount());
        binding.tvTransferFee.setText(eventTransaction.getFee().toString());
        binding.tvTransferFrom.setText(eventTransaction.getTransferFrom());
        binding.tvTransferTo.setText(eventTransaction.getRansferTo());
        binding.tvTransferTxhash.setText(eventTransaction.getTransactionHash());
        binding.tvTransferBlockHigh.setText(eventTransaction.getBlockNumber().toString());
        try {
            binding.tvTimestamp.setText(DateUtils.TimeStamp2Custom(Long.valueOf(eventTransaction.getTimeStamp()),dmyhmsDatePattern));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void initCustomTransaction(CustomTransaction customTransaction) {
        if (customTransaction == null) return;
        txHash = customTransaction.getHash();
        binding.tvTransferAmount.setText(customTransaction.getTransactionAmount());
        binding.tvTransferFee.setText(customTransaction.getTransactionFee().toString());
        binding.tvTransferFrom.setText(customTransaction.getFrom());
        binding.tvTransferTo.setText(customTransaction.getTransferTo());
        binding.tvTransferTxhash.setText(customTransaction.getHash());
        binding.tvTransferBlockHigh.setText(customTransaction.getBlockNumber().toString());
        try {
            binding.tvTimestamp.setText(DateUtils.TimeStamp2Custom(Long.valueOf(customTransaction.getTimeStamp()),dmyhmsDatePattern));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_clip_tx_url:
                ShareUtils.toClipboardData(this, "", txUrl);
                break;
            case R.id.tv_transfer_txhash:
                SimpleWebViewActivity.startWebViewActivity(txUrl, this);
                break;


        }

    }


}
