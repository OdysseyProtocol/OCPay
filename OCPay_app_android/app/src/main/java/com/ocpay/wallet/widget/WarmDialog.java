package com.ocpay.wallet.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.DialogWarmBinding;


public class WarmDialog extends AlertDialog implements View.OnClickListener {
    public static final String TAG = "PasswordConfirmDialog";
    private Context mContext;
    private Activity activity;
    private DialogWarmBinding binding;
    static WarmDialog dialog;


    public static WarmDialog getInstance(Context context, int ResTheme, Activity activity) {
        if (dialog == null) {
            dialog = new WarmDialog(context, ResTheme, activity);
        }
        return dialog;
    }


    private WarmDialog(Context context, Activity activity) {
        super(context);
        this.mContext = context;
        this.activity = activity;
    }

    private WarmDialog(Context context, int ResTheme, Activity activity) {
        super(context, ResTheme);
        this.mContext = context;
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_warm, null, false);
        setContentView(binding.getRoot());

        initView();


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

                break;
        }
    }
}
