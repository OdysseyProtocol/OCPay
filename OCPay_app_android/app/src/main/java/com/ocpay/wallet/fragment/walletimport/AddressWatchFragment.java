package com.ocpay.wallet.fragment.walletimport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.FragmentAddressWatchBinding;
import com.ocpay.wallet.fragment.BaseFragment;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.http.rx.RxBus;
import com.snow.commonlibrary.log.MyLog;
import com.snow.commonlibrary.utils.StringUtil;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_IMPORT_WALLET_WATCH;
import static com.ocpay.wallet.Constans.WALLET.WALLET_TYPE_WATCHING;

public class AddressWatchFragment extends BaseFragment<FragmentAddressWatchBinding> implements View.OnClickListener {

    @Override
    public int setContentView() {
        return R.layout.fragment_address_watch;
    }

    @Override
    public void loadData() {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initRXBus();
    }


    private void initRXBus() {
        Disposable disposable = RxBus.getInstance()
                .toObservable(ACTION_IMPORT_WALLET_WATCH, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        bindingView.etAddress.setText(s);
                    }
                });
        addDisposable(disposable);
    }

    private boolean checkInput() {

        //rep pwd
        String repPwd = bindingView.etAddress.getText().toString().trim();

        if (StringUtil.isEmpty(repPwd)) {
            Toast.makeText(MyApp.getContext(), "wallet address  is error", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }


    private void initListener() {
        bindingView.tvActionImport.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_action_import:
                boolean b = checkInput();
                if (!b) return;
                importWatchWallet();
                break;


            case R.id.tv_about_offline_signature:

                break;

        }
    }


    private void importWatchWallet() {
        WalletInfo walletInfo = new WalletInfo();
        walletInfo.setBackup(true);
        walletInfo.setWalletAddress(bindingView.etAddress.getText().toString());
        walletInfo.setWalletType(WALLET_TYPE_WATCHING);
        MyLog.i("New Wallet name:" + bindingView.etAddress.getText().toString());
        walletInfo.setWalletName("New Wallet:" + bindingView.etAddress.getText().toString().subSequence(0, 5) + "...");
        WalletInfoDaoUtils.insertWalletInfo(walletInfo, MyApp.getContext());
        RxBus.getInstance().post(Constans.RXBUS.ACTION_UPDATE_WALLET_LIST, "");
        getActivity().finish();
    }
}
