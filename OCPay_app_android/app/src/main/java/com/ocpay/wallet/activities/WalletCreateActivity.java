package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityWalletCreateBinding;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.eth.bean.OCPWalletFile;
import com.ocpay.wallet.utils.wallet.WalletStorage;
import com.snow.commonlibrary.log.MyLog;
import com.snow.commonlibrary.utils.RegularExpressionUtils;
import com.snow.commonlibrary.utils.StringUtil;

import org.web3j.crypto.CipherException;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.ocpay.wallet.Constans.WALLET.WALLET_TYPE_LOCAL_GEN;

public class WalletCreateActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    static final String ImToken_SEED_PATH = "m/44'/60'/0'/0/0";

    private ActivityWalletCreateBinding binding;

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
        initListener();

    }


    private void initListener() {

        binding.etWalletPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                binding.tvPwdTip.setVisibility(View.VISIBLE);
                binding.tvPwdLv.setVisibility(View.VISIBLE);
                binding.ivPwdLv.setVisibility(View.VISIBLE);


            }
        });
        binding.etWalletPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final String input = s.toString();
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        boolean l4Valid = RegularExpressionUtils.valid(input, Constans.REGULAR.REGULAR_PWD_LV4);
                        boolean l3Valid = RegularExpressionUtils.valid(input, Constans.REGULAR.REGULAR_PWD_LV3);
                        boolean l2Valid = RegularExpressionUtils.valid(input, Constans.REGULAR.REGULAR_PWD_LV2);
                        int level = l4Valid ? 4 : l3Valid ? 3 : l2Valid ? 2 : 1;
                        setPwdLevel(level, input);
                    }
                });
            }
        });


    }

    private void setPwdLevel(int level, String input) {
        String levelString = level == 4 ? getString(R.string.wallet_create_tip_great) : level == 3 ? getString(R.string.wallet_create_tip_good) : level == 2 ? getString(R.string.wallet_create_tip_so) : getString(R.string.wallet_create_tip_weak);
        int levelIcRes = level == 4 ? R.mipmap.ic_pwd_great : level == 3 ? R.mipmap.ic_pwd_good : level == 2 ? R.mipmap.ic_pwd_so_so : R.mipmap.ic_pwd_weak;
        String ch = getString(R.string.characters);
        String baseTip = level == 1 ? getString(R.string.wallet_create_tip_require) : input.length() + ch;
        int tipColor = level == 1 ? R.color.color_edit_tip_red : R.color.color_edit_tip_main;
        binding.tvPwdLv.setTextColor(getResources().getColor(tipColor));
        binding.tvPwdTip.setTextColor(getResources().getColor(tipColor));
        binding.ivPwdLv.setImageResource(levelIcRes);
        binding.tvPwdLv.setText(levelString);
        binding.tvPwdTip.setText(baseTip);


    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(getResources().getString(R.string.activity_title_create_wallet));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_back);
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setOnClickListener(this);
    }


    //todo test
    private void initDataBinding() {
        binding.etWalletPwd.setText("Qq123456");
        binding.etWalletCheckPwd.setText("Qq123456");
        binding.etWalletName.setText("QT");

        binding.tvGenerate.setOnClickListener(this);
        binding.tvImportWallet.setOnClickListener(this);
        binding.includePrivacy.cbPrivacyPolicy.setOnCheckedChangeListener(this);
        binding.includePrivacy.llCheckPolicy.setOnClickListener(this);

    }


    private void createWallet() {

        //  check input
        if (!checkInput()) return;
        Observable
                .create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                        boolean result = functionCreateWallet();
                        e.onNext(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLoading(false);

                    }

                    @Override
                    public void onNext(Boolean o) {
                        if (o) {
                            Intent backActivity = new Intent(WalletCreateActivity.this, BackupMnemonicActivity.class);

                            Intent mainActivity = new Intent(WalletCreateActivity.this, MainActivity.class);

                            Intent[] list = new Intent[]{  mainActivity,backActivity};

                            startActivities(list);

                            finish();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        MyLog.i("onComplete" + Thread.currentThread().getName());

                    }
                });

    }

    private boolean functionCreateWallet() {
        try {
            //   create wallet
            OCPWalletFile ocpWalletFile = OCPWalletUtils.createWithMnemonic(binding.etWalletPwd.getText().toString(), ImToken_SEED_PATH, getApplication(), true);
            //  save file
            boolean b = WalletStorage.getInstance().saveWalletFile(getApplicationContext(), ocpWalletFile);
            MyLog.i("createWallet:" + b);
            //  insert dao
            WalletInfo walletInfo = new WalletInfo();
            walletInfo.setWalletName(binding.etWalletName.getText().toString());
            walletInfo.setWalletType(WALLET_TYPE_LOCAL_GEN);
            walletInfo.setWalletAddress(ocpWalletFile.getWalletFile().getAddress());
            walletInfo.setBackup(false);
            walletInfo.setPasswordTip(binding.etWalletPwdTp.getText().toString());
            boolean isSuccess = WalletInfoDaoUtils.insertWalletInfo(walletInfo, getApplication());
            MyLog.i("insertWalletInfo:" + isSuccess);

            //todo hide processing

            //todo send  msg  update  resource
            return true;

        } catch (CipherException e) {
            e.printStackTrace();
//            return  false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return false;

    }


    public boolean checkInput() {

        if (StringUtil.isEmpty(binding.etWalletName.getText().toString())) {
            Toast.makeText(WalletCreateActivity.this, "name not null", Toast.LENGTH_SHORT).show();
            return false;
        }

        //todo 正则表达式
        if (binding.etWalletPwd.getText().toString().trim().length() < 8) {
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
        int resTxColor = isChecked ? getResources().getColor(R.color.white) : getResources().getColor(R.color.color_btn_text_un_click);
        binding.tvGenerate.setBackgroundResource(resBgImport);
        binding.tvGenerate.setTextColor(resTxColor);
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
                binding.includePrivacy.cbPrivacyPolicy.setChecked(!binding.includePrivacy.cbPrivacyPolicy.isChecked());
                break;


        }
    }
}
