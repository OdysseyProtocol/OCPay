package com.ocpay.wallet.fragment.walletimport;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.FragmentMnemonicBinding;
import com.ocpay.wallet.fragment.BaseFragment;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.eth.bean.OCPWalletFile;
import com.ocpay.wallet.utils.wallet.WalletStorage;
import com.ocpay.wallet.widget.WalletPathSelectorDialog;
import com.snow.commonlibrary.utils.RegularExpressionUtils;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletFile;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_IMPORT_WALLET_MNEMONIC;
import static com.ocpay.wallet.Constans.WALLET.WALLET_TYPE_IMPORT;
import static com.snow.commonlibrary.utils.ViewUtils.dp2px;

public class MnemonicFragment extends BaseFragment<FragmentMnemonicBinding> implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private WalletPathSelectorDialog mDialog;

    @Override
    public int setContentView() {
        return R.layout.fragment_mnemonic;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initView();
        initRXBUS();
    }

    private void initRXBUS() {
        Disposable disposable = RxBus.getInstance()
                .toObservable(ACTION_IMPORT_WALLET_MNEMONIC, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        bindingView.etMnemonic.setText(s);
                    }
                });
        addDisposable(disposable);
    }

    private void initView() {
        mDialog = WalletPathSelectorDialog.getInstance(getActivity(), R.style.CustomDialog, getActivity());
        bindingView.etPath.setClickable(false);
        bindingView.tvActionImport.setClickable(false);
        mDialog.setListener(new WalletPathSelectorDialog.OnPathSelectorListener() {
            @Override
            public void onSelect(int pathType, String path) {
                bindingView.etPath.setClickable(pathType == WalletPathSelectorDialog.Custom);
                bindingView.etPath.setText(path);
            }
        });
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bindingView.ivBtnShowPath.setBackgroundResource(R.mipmap.ic_list_close);
            }
        });


    }


    private boolean checkInput() {
        //Mnemonic
        String mnemonic = bindingView.etMnemonic.getText().toString().trim();
        boolean validMnemonic = RegularExpressionUtils.valid(mnemonic, Constans.REGULAR.REGULAR_MNEMONIC);
        if (!validMnemonic) {
            Toast.makeText(MyApp.getContext(), "Mnemonic is error", Toast.LENGTH_SHORT).show();
            return false;
        }
        //path
        String path = bindingView.etPath.getText().toString().trim();
        if (path.split("/").length != 6) {
            Toast.makeText(MyApp.getContext(), "path is error", Toast.LENGTH_SHORT).show();
            return false;
        }

        //pwd
        String pwd = bindingView.etPwd.getText().toString().trim();
        if (pwd.length() <= 8) {
            Toast.makeText(MyApp.getContext(), "password length  is short", Toast.LENGTH_LONG).show();
            return false;
        }
        //rep pwd
        String repPwd = bindingView.etCheckPwd.getText().toString().trim();
        if (pwd.equals(repPwd)) {
            Toast.makeText(MyApp.getContext(), "password is different", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }


    private void initListener() {
        bindingView.tvActionImport.setOnClickListener(this);
        bindingView.include.cbPrivacyPolicy.setOnCheckedChangeListener(this);
        bindingView.include.llCheckPolicy.setOnClickListener(this);
        bindingView.include.tvPrivacyPolicy.setOnClickListener(this);
        bindingView.tvAboutMnemonic.setOnClickListener(this);
        bindingView.ivBtnShowPath.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_action_import:
                boolean b = checkInput();
                if (!b) return;
                importByMnemonic();
                break;
            case R.id.ll_check_policy:
                bindingView.include.cbPrivacyPolicy.setChecked(!bindingView.include.cbPrivacyPolicy.isChecked());
                break;

            case R.id.tv_privacy_policy:


                break;
            case R.id.tv_about_mnemonic:


                break;

            case R.id.iv_btn_show_path:
                showDialog();
                break;


        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setCheckBoxStatus(getContext(), isChecked, bindingView.tvActionImport);
    }


    public void importByMnemonic() {
        String mnemonic = bindingView.etMnemonic.getText().toString().trim();
        WalletFile walletFile = null;
        try {
            walletFile = OCPWalletUtils.getWalletFileByMnemonic(mnemonic, bindingView.etPath.getText().toString().trim(), bindingView.etPwd.getText().toString().trim());
        } catch (CipherException e) {
            e.printStackTrace();
            //todo  create fail
        }
        if (walletFile == null) return;
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


    private void showDialog() {
        Window win = mDialog.getWindow();
        win.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        WindowManager.LayoutParams lp = win.getAttributes();
        float getY = bindingView.viewLinePath.getY();
        lp.y = (int) getY + dp2px(getContext(), 100);
        bindingView.ivBtnShowPath.setBackgroundResource(R.mipmap.ic_list_open);
        mDialog.show();

    }

    @Override
    public void onDestroy() {
        mDialog.destroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
