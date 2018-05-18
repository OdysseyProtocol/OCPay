package com.ocpay.wallet.bean.home;


/**
 * Created by y on 2017/11/17.
 */

public class TokenBalanceBean extends BastBean {

    String tokenIconUrl;
    String tokenName;
    String tokenBalance;
    String tokenBalanceOFUNIT;


    public TokenBalanceBean(String tokenIconUrl, String tokenName, String tokenBalance) {
        this.tokenIconUrl = tokenIconUrl;
        this.tokenName = tokenName;
        this.tokenBalance = tokenBalance;
    }


    public String getTokenIconUrl() {
        return tokenIconUrl;
    }

    public void setTokenIconUrl(String tokenIconUrl) {
        this.tokenIconUrl = tokenIconUrl;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getTokenBalance() {
        return tokenBalance;
    }

    public void setTokenBalance(String tokenBalance) {
        this.tokenBalance = tokenBalance;
    }


}
