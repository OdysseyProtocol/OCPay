package com.ocpay.wallet.widget.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.DialogPasswordConfirmBinding;
import com.ocpay.wallet.http.client.EthScanHttpClientIml;
import com.ocpay.wallet.http.rx.RxBus;

import static com.ocpay.wallet.Constans.TEST.WALLET_ADDRESS;


public class PasswordConfirmDialog extends AlertDialog implements View.OnClickListener {
    public static final String TAG = "PasswordConfirmDialog";
    private Activity activity;
    private DialogPasswordConfirmBinding binding;
    static PasswordConfirmDialog dialog;
    public ConfirmListener listener;

    public interface ConfirmListener {
        void onConfirm();
    }


    public static PasswordConfirmDialog getInstance(Activity activity) {
        if (dialog == null) {
            dialog = new PasswordConfirmDialog(R.style.PasswordConfirmDialog, activity);
        }
        return dialog;
    }


    private PasswordConfirmDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    private PasswordConfirmDialog(int ResTheme, Activity activity) {
        super(activity, ResTheme);
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_password_confirm, null, false);
        setContentView(binding.getRoot());
        initView();


    }

    private void initView() {
        binding.tvActionCancel.setOnClickListener(this);
        binding.tvActionConfirm.setOnClickListener(this);
    }

    @Override
    public void show() {
        if (dialog != null && !isShowing() && !activity.isFinishing()) {
            super.show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_action_cancel:
                dismiss();
                break;
            case R.id.tv_action_confirm:
                RxBus.getInstance().post(Constans.RXBUS.ACTION_TRANSACTION_CONFIRM_KEYSTORE, binding.tvPassword.getText().toString().trim());
                EthScanHttpClientIml.getAddressNonce(Constans.RXBUS.ACTION_TRANSACTION_GET_NONCE, WALLET_ADDRESS);
                dismiss();
                if (listener != null) listener.onConfirm();
                break;
        }
    }


    public void destroy() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        activity = null;
        binding = null;
        if (listener != null) listener = null;
    }


    public ConfirmListener getListener() {
        return listener;
    }

    public void setListener(ConfirmListener listener) {
        this.listener = listener;
    }
}
