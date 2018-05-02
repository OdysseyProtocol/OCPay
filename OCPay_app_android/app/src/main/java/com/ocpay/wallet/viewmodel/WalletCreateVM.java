package com.ocpay.wallet.viewmodel;

import android.databinding.ObservableField;

import com.ocpay.wallet.bean.WalletCreateBean;

public class WalletCreateVM {

    public ObservableField<WalletCreateBean> walletCreateBean = new ObservableField<>();
    public ObservableField<String> walletName = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();
    public ObservableField<String> passwordTip = new ObservableField<>();

    public WalletCreateVM() {

    }


}
