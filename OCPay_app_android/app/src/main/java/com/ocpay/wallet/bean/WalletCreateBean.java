package com.ocpay.wallet.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ocpay.wallet.BR;

import java.io.Serializable;

public class WalletCreateBean extends BaseObservable implements Serializable {

    public String walletName;

    public String password;

    public String confirmPassword;

    public String passwordTip;

    @Bindable
    public String getWalletName() {
        return walletName;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Bindable
    public String getPasswordTip() {
        return passwordTip;
    }


    public void setWalletName(String walletName) {
        this.walletName = walletName;
        notifyPropertyChanged(BR.walletName);

    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);

    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
    }

    public void setPasswordTip(String passwordTip) {
        this.passwordTip = passwordTip;
        notifyPropertyChanged(BR.passwordTip);

    }
}
