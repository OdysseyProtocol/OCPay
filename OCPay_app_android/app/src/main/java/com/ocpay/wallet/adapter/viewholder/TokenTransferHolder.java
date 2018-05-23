package com.ocpay.wallet.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.TokenTransferAdapter;
import com.ocpay.wallet.databinding.LayoutTokenTransferItemBinding;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.web3j.response.BaseTransaction;
import com.ocpay.wallet.utils.web3j.response.CustomTransaction;
import com.ocpay.wallet.utils.web3j.response.EventTransaction;
import com.snow.commonlibrary.utils.DateUtils;

import java.text.ParseException;

import static com.snow.commonlibrary.utils.DateUtils.ydDatePattern;

/**
 * Created by y on 2018/4/16.
 */

public class TokenTransferHolder extends RecyclerView.ViewHolder {

    private LayoutTokenTransferItemBinding mBinding;


    public TokenTransferHolder(LayoutTokenTransferItemBinding binding , TokenTransferAdapter.TYPE type) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public TokenTransferHolder(View itemView) {
        super(itemView);

    }

    public void setData(BaseTransaction transaction, BaseTransaction lastData, int position) {
        if (transaction instanceof EventTransaction) {
            eventTXInit((EventTransaction) transaction, position);
        }

        if (transaction instanceof CustomTransaction) {
            walletTxInit((CustomTransaction) transaction, lastData == null ? null : (CustomTransaction) lastData, position);
        }


    }

    private void walletTxInit(CustomTransaction transaction, CustomTransaction lastData, int position) {
        String walletAddress = OCPWallet.getCurrentWallet().getWalletAddress();
        mBinding.tvTitle.setTextColor(MyApp.getContext().getResources().getColor(R.color.transaction_center_date_title));
        mBinding.tvWalletName.setText(OCPWalletUtils.foldWalletAddress(walletAddress));
        String date = "";
        String dateYM = "";
        String lastDateYM = "";
        try {
            date = DateUtils.TimeStamp2Date(Long.valueOf(transaction.getTimeStamp()));
            dateYM = DateUtils.TimeStamp2Custom(Long.valueOf(transaction.getTimeStamp()), ydDatePattern);
            lastDateYM = lastData == null ? "" : DateUtils.TimeStamp2Custom(Long.valueOf(lastData.getTimeStamp()), ydDatePattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String amount = transaction.getTransactionAmount();

        initView(amount, transaction.isSend(), transaction.isSuccess(), date);
        boolean isNewMonth = !dateYM.equals(lastDateYM);
        int spaceVisible = isNewMonth && position != 0 ? View.VISIBLE : View.GONE;
        int titleVisible = isNewMonth ? View.VISIBLE : View.GONE;

        mBinding.viewSpace.setVisibility(spaceVisible);
        mBinding.tvTitle.setVisibility(titleVisible);
        mBinding.tvTitle.setText(dateYM);

    }



    private void eventTXInit(EventTransaction transaction, int position) {
        int visible = position == 0 ? View.VISIBLE : View.GONE;
        mBinding.tvTitle.setVisibility(visible);
        mBinding.tvTitle.setTextColor(MyApp.getContext().getResources().getColor(R.color.color_token_transaction_title));

        String time = ((EventTransaction) transaction).getTimeFormat_dMy();
        boolean send = ((EventTransaction) transaction).isSend();
        boolean status = ((EventTransaction) transaction).getTransactionStatus();
        String amount = ((EventTransaction) transaction).getTransferAmount();
        initView(amount, send, status, time);

    }

    private void initView(String amount, boolean isSend, boolean status, String time) {
        String txAmount = isSend ? "-" + amount : "+" + amount;
        String statusDetail = status ? "SUCCESS" : "FAIL";
        int resColor = status ? MyApp.getContext().getResources().getColor(R.color.color_transfer_text_green) : MyApp.getContext().getResources().getColor(R.color.color_transfer_text_red);
        mBinding.tvTransferTime.setText(time);
        mBinding.tvTransferAmount.setText(txAmount);
        mBinding.tvTransferAmount.setTextColor(resColor);
        mBinding.tvTransferStatus.setText(statusDetail);
    }


}
