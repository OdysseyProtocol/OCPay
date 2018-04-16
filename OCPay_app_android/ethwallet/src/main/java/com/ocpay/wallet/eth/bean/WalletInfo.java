package com.ocpay.wallet.eth.bean;

public class WalletInfo {

    String walletName;

    String walletFileString;

    String walletAddress;


//    String


    public WalletInfo(String walletName, String walletFileString, String walletAddress) {
        this.walletName = walletName;
        this.walletFileString = walletFileString;
        this.walletAddress = walletAddress;
    }
}
