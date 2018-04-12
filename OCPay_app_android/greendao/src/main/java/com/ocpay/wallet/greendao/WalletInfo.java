package com.ocpay.wallet.greendao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by y on 2017/11/10.
 */

@Entity
public class WalletInfo {

    @Id(autoincrement = true)
    private long id;

    private String userId;

    @Unique
    private String walletName;

    @NotNull
    private String walletAddress;

    @NotNull
    private String walletFile;

    private String mnemonic;

    @Generated(hash = 495254212)
    public WalletInfo(long id, String userId, String walletName, @NotNull String walletAddress, @NotNull String walletFile,
            String mnemonic) {
        this.id = id;
        this.userId = userId;
        this.walletName = walletName;
        this.walletAddress = walletAddress;
        this.walletFile = walletFile;
        this.mnemonic = mnemonic;
    }


    @Generated(hash = 1144910350)
    public WalletInfo() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getWalletFile() {
        return walletFile;
    }

    public void setWalletFile(String walletFile) {
        this.walletFile = walletFile;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }
}
