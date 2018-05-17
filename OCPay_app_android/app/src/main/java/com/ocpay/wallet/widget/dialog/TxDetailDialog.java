package com.ocpay.wallet.widget.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.DialogTransDetailsBinding;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.snow.commonlibrary.log.MyLog;

import java.math.BigDecimal;
import java.math.BigInteger;


public class TxDetailDialog extends AlertDialog implements View.OnClickListener {
    public static final String TAG = "TxDetailDialog";


    private OnNextListener listener;

    private Activity activity;
    private DialogTransDetailsBinding binding;
    static TxDetailDialog dialog;


    public interface OnNextListener {
        void onNext();
    }


    public static synchronized TxDetailDialog getInstance(Activity activity) {
        if (dialog == null) {
            synchronized (TxDetailDialog.class) {
                MyLog.i("dialog:getInstance");

                if (dialog == null) {
                    dialog = new TxDetailDialog(R.style.PasswordConfirmDialog, activity);
                }
            }
        }
        return dialog;
    }


    private TxDetailDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    private TxDetailDialog(int ResTheme, Activity activity) {
        super(activity, ResTheme);
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyLog.i("dialog:onCreate");
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_trans_details, null, false);
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        ViewGroup.LayoutParams layoutParams = binding.getRoot().getLayoutParams();
        layoutParams.width = MyApp.getContext().getResources().getDisplayMetrics().widthPixels;
        binding.ivClose.setOnClickListener(this);
        binding.tvNextStep.setOnClickListener(this);
    }


    @Override
    public void show() {
        MyLog.i("dialog:show");
        if (dialog != null && !isShowing() && !activity.isFinishing()) {
            super.show();
        }
    }


    public void destroy() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        binding = null;
        activity = null;
    }

    public void setListener(OnNextListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_next_step:
                dismiss();
                if (listener != null) listener.onNext();
                break;

        }
    }


    public void setData(String orderInfo, String from, String to, BigInteger gasPrice, BigInteger gasLimit, String amount) {
        MyLog.i("dialog:setData");
        if (binding == null) return;
        MyLog.i("gasPrice:" + gasPrice);
        MyLog.i("gasLimit:" + gasLimit);
        binding.tvOrderInfo.setText(orderInfo);
        binding.tvOrderFrom.setText(OCPWalletUtils.foldWalletAddress(from));
        binding.tvOrderTo.setText(OCPWalletUtils.foldWalletAddress(to));
        binding.tvTransferAmount.setText(amount);
        String feeDetail = "Gas(%s)*Gas Price(%s gwei)";
        String format = String.format(feeDetail, gasLimit, OCPWallet.wei2Gwei(gasPrice).toString());
        BigDecimal eth = OCPWalletUtils.getTransactionFee(new BigDecimal(gasPrice), new BigDecimal(gasLimit));
        binding.tvFeeDetail.setText(format);
        binding.tvTransferFee.setText(eth + " ETH");
    }
}
