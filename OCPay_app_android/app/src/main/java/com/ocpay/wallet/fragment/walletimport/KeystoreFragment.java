package com.ocpay.wallet.fragment.walletimport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.FragmentKeystoreBinding;
import com.ocpay.wallet.fragment.BaseFragment;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.eth.bean.OCPWalletFile;
import com.ocpay.wallet.utils.wallet.WalletStorage;
import com.snow.commonlibrary.utils.StringUtil;

import org.web3j.crypto.WalletFile;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_IMPORT_WALLET_KEYSTORE;
import static com.ocpay.wallet.Constans.WALLET.WALLET_TYPE_IMPORT;

public class KeystoreFragment extends BaseFragment<FragmentKeystoreBinding> implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @Override
    public int setContentView() {
        return R.layout.fragment_keystore;
    }

    @Override
    public void loadData() {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initRXBUS();
    }


    private void initRXBUS() {
        Disposable disposable = RxBus.getInstance()
                .toObservable(ACTION_IMPORT_WALLET_KEYSTORE, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        bindingView.etKeystore.setText(s);
                    }
                });
        addDisposable(disposable);
    }


    private boolean checkInput() {
        //pwd
        String pwd = bindingView.etPwd.getText().toString().trim();
        if (StringUtil.isEmpty(pwd)) {
            Toast.makeText(MyApp.getContext(), "password not null", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }


    private void initListener() {
        bindingView.tvActionImport.setClickable(false);
        bindingView.tvActionImport.setOnClickListener(this);
        bindingView.include.cbPrivacyPolicy.setOnCheckedChangeListener(this);
        bindingView.include.llCheckPolicy.setOnClickListener(this);
        bindingView.include.tvPrivacyPolicy.setOnClickListener(this);
        bindingView.tvAboutKeystore.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_action_import:
                boolean b = checkInput();
                if (!b) return;
                importByKeystore();
                break;
            case R.id.ll_check_policy:
                bindingView.include.cbPrivacyPolicy.setChecked(!bindingView.include.cbPrivacyPolicy.isChecked());
                break;

            case R.id.tv_privacy_policy:
                break;
            case R.id.tv_about_keystore:


                break;


        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setCheckBoxStatus(getContext(), bindingView.include.cbPrivacyPolicy.isChecked(), bindingView.tvActionImport);
    }


    private void importByKeystore() {
        String keystore = bindingView.etKeystore.getText().toString().trim();
        WalletFile walletFile = OCPWalletUtils.createWalletFileByKeystore(keystore);
        boolean valid = OCPWalletUtils.valid(bindingView.etPwd.getText().toString().trim(), walletFile);
        if (walletFile == null || !valid) {
            //todo  show  error
            Toast.makeText(MyApp.getContext(), "keystore & password is error", Toast.LENGTH_LONG).show();
            return;
        }
        OCPWalletFile ocpWalletFile = new OCPWalletFile();
        ocpWalletFile.setWalletFile(walletFile);
        WalletStorage.getInstance().saveWalletFile(MyApp.getContext(), ocpWalletFile);
        WalletInfo walletInfo = new WalletInfo();
        walletInfo.setBackup(true);
        walletInfo.setWalletAddress(walletFile.getAddress());
        walletInfo.setWalletType(WALLET_TYPE_IMPORT);
        walletInfo.setWalletName("New Wallet:" + walletFile.getAddress().subSequence(0, 5) + "...");
        WalletInfoDaoUtils.insertWalletInfo(walletInfo, MyApp.getContext());
        RxBus.getInstance().post(Constans.RXBUS.ACTION_UPDATE_WALLET_LIST, "");
        getActivity().finish();
    }

}
