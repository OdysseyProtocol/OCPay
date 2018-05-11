package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityBackupMnemonicBinding;
import com.ocpay.wallet.widget.FlowLayout;
import com.ocpay.wallet.widget.LabelText;
import com.snow.commonlibrary.log.MyLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.ocpay.wallet.Constans.WALLET.ADDRESS_FROM;
import static com.ocpay.wallet.Constans.WALLET.TOKEN_NAME;
import static com.ocpay.wallet.widget.LabelText.MODE_ADDED;
import static com.ocpay.wallet.widget.LabelText.MODE_UN_SELECT;

public class BackupMnemonicActivity extends BaseActivity implements View.OnClickListener {


    private ActivityBackupMnemonicBinding binding;
    private FlowLayout flInput;
    private List<LabelText> labelTexts;
    private FlowLayout flSelect;

    public static void startBackupActivity(Activity activity, String fromAddress, String tokenName) {
        Intent intent = new Intent(activity, BackupMnemonicActivity.class);
        intent.putExtra(TOKEN_NAME, tokenName);
        intent.putExtra(ADDRESS_FROM, fromAddress);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        binding = DataBindingUtil.setContentView(BackupMnemonicActivity.this, R.layout.activity_backup_mnemonic);
        initView();
        initSelect();
        randomShow();


    }

    private void initView() {
        binding.includeShow.llMnemonicShow.setVisibility(View.GONE);
        flInput = binding.includeReview.flMnemonicInput;
        flSelect = binding.includeReview.flMnemonicSelect;
        binding.includeReview.tvActionConfirm.setOnClickListener(this);
    }


    String mnemonic = "box hex one hurry what do you want to see give sky";

    private void initSelect() {
        String[] split = mnemonic.split(" ");
        labelTexts = new ArrayList<>();
        for (String word : split) {
            LabelText labelText = new LabelText(this, word, MODE_UN_SELECT, false);

            labelText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((LabelText) v).isSelected()) return;
                    ((LabelText) v).setSelected(true);
                    addInput(((LabelText) v).getText().toString());
                    randomShow();
                }
            });
            labelTexts.add(labelText);
        }
    }

    private void addInput(String s) {
        LabelText labelText = new LabelText(this, s, MODE_ADDED, false);
        labelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flInput.removeView(flInput.findViewWithTag(v.getTag()));
                updateSelect(((LabelText) v).getText().toString());

            }
        });
        flInput.addView(labelText);

    }

    private void randomShow() {
        flSelect.removeAllViews();
        for (Integer index : getIntegerList()) {
            flSelect.addView(labelTexts.get(index));
        }
    }


    private List<Integer> getIntegerList() {
        List<Integer> integers = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            integers.add(i);
        }
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int i1 = random.nextInt(integers.size());
            integerList.add(integers.get(i1));
            integers.remove(i1);
        }
        return integerList;
    }


    public void updateSelect(String word) {
        for (LabelText text : labelTexts) {
            if (word.equals(text.getText().toString())) {
                text.setSelected(false);
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_action_confirm:
                if (flInput.getChildCount() != 12) {
                    Toast.makeText(this, "input error", Toast.LENGTH_SHORT).show();
                }
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 12; i++) {
                    if (flInput.getChildAt(i) instanceof LabelText) {
                        builder.append(((LabelText) flInput.getChildAt(i)).getText().toString());
                        builder.append(" ");
                    }
                }
                MyLog.i("confirm:__" + builder.toString());
                break;
            case R.id.tv_action_next:

                break;

        }
    }
}
