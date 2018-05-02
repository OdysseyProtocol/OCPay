package com.ocpay.wallet.utils.eth.bean;

import org.web3j.crypto.WalletFile;

/**
 * Created by y on 2018/4/16.
 */

public class OCPWalletFile {


    private String mnemonic;

    private String path;

    private WalletFile walletFile;

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }


    public WalletFile getWalletFile() {
        return walletFile;
    }

    public void setWalletFile(WalletFile walletFile) {
        this.walletFile = walletFile;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
