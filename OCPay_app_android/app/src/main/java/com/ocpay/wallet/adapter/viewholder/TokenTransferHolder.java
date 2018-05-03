package com.ocpay.wallet.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.databinding.LayoutTokenTransferItemBinding;
import com.ocpay.wallet.utils.web3j.response.BaseTransaction;
import com.ocpay.wallet.utils.web3j.response.EventLogTransactionResponse;

/**
 * Created by y on 2018/4/16.
 */

public class TokenTransferHolder extends RecyclerView.ViewHolder {

    private LayoutTokenTransferItemBinding mBinding;


    public TokenTransferHolder(LayoutTokenTransferItemBinding binding) {
        this(binding.getRoot());
        mBinding = binding;
    }

    public TokenTransferHolder(View itemView) {
        super(itemView);

    }

    public void setData(Context context, BaseTransaction transaction, int position) {
        int visible = position == 0 ? View.VISIBLE : View.GONE;
        mBinding.tvTitle.setVisibility(visible);
        if (transaction instanceof EventLogTransactionResponse.EventTransaction) {
//            mBinding.tvTransferTime.setText(((EventLogTransactionResponse.EventTransaction) transaction).getTimeFormat_dMy());
//            boolean send = ((EventLogTransactionResponse.EventTransaction) transaction).isSend();
//            MyLog.i("send"+send);
//            boolean status = ((EventLogTransactionResponse.EventTransaction) transaction).getTransactionStatus();
//            String amount = ((EventLogTransactionResponse.EventTransaction) transaction).getTransferAmount();
//            String txAmount = send ? "-" + amount : "+" + amount;
//            String statusDetail = status ? "SUCCESS" : "FAIL";
//            int resColor = status ? MyApp.getContext().getResources().getColor(R.color.color_transfer_text_green) : MyApp.getContext().getResources().getColor(R.color.color_transfer_text_red);
//            mBinding.tvTransferAmount.setText(txAmount);
//            mBinding.tvTransferAmount.setTextColor(resColor);
//            mBinding.tvTransferStatus.setText(statusDetail);
        }


    }


}
