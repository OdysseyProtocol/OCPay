package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ocpay.wallet.R;
import com.ocpay.wallet.bean.WalletCreateBean;
import com.ocpay.wallet.databinding.ActivityWalletCreateBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.eth.bean.OCPWalletFile;
import com.ocpay.wallet.utils.wallet.WalletStorage;
import com.ocpay.wallet.viewmodel.WalletCreateVM;
import com.snow.commonlibrary.log.MyLog;
import com.snow.commonlibrary.utils.StringUtil;

import org.web3j.crypto.CipherException;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static com.ocpay.wallet.Constans.WALLET.WALLET_TYPE_LOCAL_GEN;

public class WalletCreateActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    static final String ImToken_SEED_PATH = "m/44'/60'/0'/0/0";

    private ActivityWalletCreateBinding binding;
    private WalletCreateVM walletCreateVM;
    private WalletCreateBean walletCreateBean;

    public static void startActivity(Activity activity) {
        if (activity == null) return;
        Intent intent = new Intent(activity, WalletCreateActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet_create);
        initDataBinding();
        initActionBar();
    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(getResources().getString(R.string.activity_title_create_wallet));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_back);
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setOnClickListener(this);
    }

    private void initDataBinding() {
        walletCreateBean = new WalletCreateBean();
        walletCreateBean.setWalletName("temp wallet name");
        walletCreateVM = new WalletCreateVM();
        walletCreateVM.walletCreateBean.set(walletCreateBean);
        walletCreateVM.password.set("888888888");
        walletCreateVM.confirmPassword.set("888888888");
        binding.setWalletCreateVM(walletCreateVM);
        binding.tvGenerate.setOnClickListener(this);
        binding.tvImportWallet.setOnClickListener(this);
        binding.includePrivacy.cbPrivacyPolicy.setOnCheckedChangeListener(this);
        binding.includePrivacy.llCheckPolicy.setOnClickListener(this);
    }


    private void createWallet() {
        try {
            //todo check input
            if (!checkInput()) return;
            //todo  create wallet
            OCPWalletFile ocpWalletFile = OCPWalletUtils.createWithMnemonic(binding.etWalletPwd.getText().toString(), ImToken_SEED_PATH, getApplication(), true);
            //todo save file
            boolean b = WalletStorage.getInstance().saveWalletFile(getApplicationContext(), ocpWalletFile);
            MyLog.i("createWallet:" + b);
            //todo insert dao
            WalletInfo walletInfo = new WalletInfo();
            walletInfo.setWalletName(binding.etWalletName.getText().toString());
            walletInfo.setWalletType(WALLET_TYPE_LOCAL_GEN);
            walletInfo.setWalletAddress(ocpWalletFile.getWalletFile().getAddress());
            walletInfo.setBackup(false);
            boolean isSuccess = WalletInfoDaoUtils.insertWalletInfo(walletInfo, getApplication());
            MyLog.i("insertWalletInfo:" + isSuccess);

            //todo hide processing


            //todo send  msg  update  resource


        } catch (CipherException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }


    public boolean checkInput() {

        if (StringUtil.isEmpty(binding.etWalletName.getText().toString())) {
            Toast.makeText(WalletCreateActivity.this, "name not null", Toast.LENGTH_SHORT).show();
            return false;
        }

        //todo 正则表达式
        if (binding.etWalletPwd.getText().toString().trim().length() < 9) {
            Toast.makeText(WalletCreateActivity.this, "password length is short", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!binding.etWalletPwd.getText().toString().trim().equals(binding.etWalletCheckPwd.getText().toString().trim())) {
            Toast.makeText(WalletCreateActivity.this, "password is correct", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setCheckBoxStatus(isChecked);
    }

    private void setCheckBoxStatus(boolean isChecked) {
        binding.tvGenerate.setClickable(isChecked);
        int resBgImport = isChecked ? R.drawable.shape_corner_btn_main_r6 : R.drawable.shape_btn_grave;
        binding.tvGenerate.setBackgroundResource(resBgImport);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_generate:
                createWallet();
                break;
            case R.id.tv_import_wallet:
                //todo jump import wallet activity
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_check_policy:
                setCheckBoxStatus(binding.includePrivacy.cbPrivacyPolicy.isChecked());
                break;


        }
    }
}
