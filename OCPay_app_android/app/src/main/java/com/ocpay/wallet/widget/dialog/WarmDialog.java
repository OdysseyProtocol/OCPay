package com.ocpay.wallet.widget.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.DialogWarmBinding;


public class WarmDialog extends AlertDialog implements View.OnClickListener {
    public static final String TAG = "WarmDialog";
    private Activity activity;
    private DialogWarmBinding binding;
    static WarmDialog dialog;


    public static WarmDialog getInstance(Activity activity) {
        if (dialog == null) {
            dialog = new WarmDialog(R.style.PasswordConfirmDialog, activity);
        }
        return dialog;
    }


    private WarmDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    private WarmDialog(int ResTheme, Activity activity) {
        super(activity, ResTheme);
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_warm, null, false);

        setContentView(binding.getRoot());

        initView();


    }


    public void setTip(String tip) {
        if (binding == null) return;
        binding.tvWarmTitle.setText(tip);
    }


    private void initView() {
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
                dismiss();
                break;
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

    public static void showTip(Activity activity,String tip){
        WarmDialog.getInstance(activity).show();
        WarmDialog.getInstance(activity).setTip(tip);

    }
}
