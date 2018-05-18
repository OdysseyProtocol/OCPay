package com.ocpay.wallet.utils.web3j.bean;

import com.ocpay.wallet.utils.web3j.utils.OWalletUtils;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

/**
 * Created by y on 2018/3/5.
 */
public class OWalletInfo {

    ECKeyPair ecKeyPair;
    String walletAddress;
    String privateKey;


    public OWalletInfo(ECKeyPair ecKeyPair) {
        this.ecKeyPair = ecKeyPair;
        this.walletAddress = Keys.getAddress(ecKeyPair);
        this.privateKey = OWalletUtils.getWalletPrivateKey(ecKeyPair);
    }

    public OWalletInfo(String privateKey) {
        this.ecKeyPair = OWalletUtils.getEckeyPair(privateKey);
        this.walletAddress = Keys.getAddress(ecKeyPair);
        this.privateKey = privateKey;
    }


    public ECKeyPair getEcKeyPair() {
        return ecKeyPair;
    }

    public void setEcKeyPair(ECKeyPair ecKeyPair) {
        this.ecKeyPair = ecKeyPair;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
