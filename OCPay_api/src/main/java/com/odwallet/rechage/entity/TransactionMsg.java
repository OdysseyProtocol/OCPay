package com.odwallet.rechage.entity;

/**
 * Created by liuhuan on 2018/3/21.
 */
public class TransactionMsg {
    private UserWalletInfo userWalletInfo;
    private UserCoinBalance userCoinBalance;

    public UserWalletInfo getUserWalletInfo() {
        return userWalletInfo;
    }

    public void setUserWalletInfo(UserWalletInfo userWalletInfo) {
        this.userWalletInfo = userWalletInfo;
    }

    public UserCoinBalance getUserCoinBalance() {
        return userCoinBalance;
    }

    public void setUserCoinBalance(UserCoinBalance userCoinBalance) {
        this.userCoinBalance = userCoinBalance;
    }
}
