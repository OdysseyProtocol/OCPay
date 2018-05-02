package com.ocpay.wallet.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.DialogTransDetailsBinding;


public class TransactionDetailDialog extends AlertDialog {
    public static final String TAG = "WalletPathSelectorDialog";

    public static final int Custom = 4;
    public static final int imToken = 3;
    public static final int Ledger = 2;
    public static final int Metamask = 1;

    private Context mContext;

    private OnPathSelectorListener listener;

    private Activity activity;
    private DialogTransDetailsBinding binding;
    private int pathType = -1;
    static TransactionDetailDialog dialog;

    public interface OnPathSelectorListener {
        void onSelect(int pathType, String path);
    }


    public static TransactionDetailDialog getInstance(Context context, int ResTheme, Activity activity) {
        if (dialog == null) {
            dialog = new TransactionDetailDialog(context, ResTheme, activity);
        }
        return dialog;
    }


    private TransactionDetailDialog(Context context, Activity activity) {
        super(context);
        this.mContext = context;
        this.activity = activity;
    }

    private TransactionDetailDialog(Context context, int ResTheme, Activity activity) {
        super(context, ResTheme);
        this.mContext = context;
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_trans_details, null, false);
        setContentView(binding.getRoot());
        initView();
        changeStatus(imToken);
    }

    private void initView() {

//        binding.includeCustom.tvPathContent.setText(Constans.WALLET.PATH_Custom);
//        binding.includeImtoken.tvPathContent.setText(Constans.WALLET.PATH_imToken);
//        binding.includeLedger.tvPathContent.setText(Constans.WALLET.PATH_Ledger);
//        binding.includeMetamask.tvPathContent.setText(Constans.WALLET.PATH_Metamask);
//        binding.includeCustom.llPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeStatus(Custom);
//                dialog.dismiss();
//            }
//        });
//        binding.includeImtoken.llPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeStatus(imToken);
//                dialog.dismiss();
//            }
//        });
//        binding.includeLedger.llPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeStatus(Ledger);
//                dialog.dismiss();
//
//
//            }
//        });
//
//        binding.includeMetamask.llPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeStatus(Metamask);
//                dialog.dismiss();
//
//            }
//        });


    }


    public void changeStatus(int pathType) {
        if (pathType == this.pathType) {
            return;
        }
//        binding.includeCustom.tvPathContent.setTextColor(getContext().getResources().getColor(R.color.color_edit_hint));
//        binding.includeImtoken.tvPathContent.setTextColor(getContext().getResources().getColor(R.color.color_edit_hint));
//        binding.includeLedger.tvPathContent.setTextColor(getContext().getResources().getColor(R.color.color_edit_hint));
//        binding.includeMetamask.tvPathContent.setTextColor(getContext().getResources().getColor(R.color.color_edit_hint));
//
//        binding.includeMetamask.ivSelected.setVisibility(View.GONE);
//        binding.includeImtoken.ivSelected.setVisibility(View.GONE);
//        binding.includeLedger.ivSelected.setVisibility(View.GONE);
//        binding.includeCustom.ivSelected.setVisibility(View.GONE);
//
//
//        switch (pathType) {
//            case Custom:
//                if (listener != null) {
//                    String path = binding.includeCustom.tvPathContent.getText().toString().split(" ")[0];
//                    listener.onSelect(Custom, path);
//                }
//                binding.includeCustom.tvPathContent.setTextColor(getContext().getResources().getColor(R.color.color_text_main));
//                binding.includeCustom.ivSelected.setVisibility(View.VISIBLE);
//                break;
//            case imToken:
//                if (listener != null) {
//                    String path = binding.includeImtoken.tvPathContent.getText().toString().split(" ")[0];
//                    listener.onSelect(Custom, path);
//                }
//                binding.includeImtoken.tvPathContent.setTextColor(getContext().getResources().getColor(R.color.color_text_main));
//                binding.includeImtoken.ivSelected.setVisibility(View.VISIBLE);
//                break;
//            case Ledger:
//                if (listener != null) {
//                    String path = binding.includeLedger.tvPathContent.getText().toString().split(" ")[0];
//                    listener.onSelect(Custom, path);
//                }
//                binding.includeLedger.tvPathContent.setTextColor(getContext().getResources().getColor(R.color.color_text_main));
//                binding.includeLedger.ivSelected.setVisibility(View.VISIBLE);
//                break;
//            case Metamask:
//                if (listener != null) {
//                    String path = binding.includeMetamask.tvPathContent.getText().toString().split(" ")[0];
//                    listener.onSelect(Custom, path);
//                }
//                binding.includeMetamask.tvPathContent.setTextColor(getContext().getResources().getColor(R.color.color_text_main));
//                binding.includeMetamask.ivSelected.setVisibility(View.VISIBLE);
//
//                break;
//
//        }
//        binding.executePendingBindings();

    }


    @Override
    public void show() {
        if (dialog != null && !isShowing()) {
            super.show();
        }
    }


    public void setListener(OnPathSelectorListener listener) {
        this.listener = listener;
    }
}
