package com.ocpay.wallet;

import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.utils.OCPPrefUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by y on 2018/5/17.
 */

public class OCPWallet {

    private static WalletInfo currentWallet;


    public static BigInteger stGasPrice;

    public static BigInteger minGasLimit;

    public static BigInteger minGasPrice;
    public static String UNIT_G = "1000000000";

    public static BigInteger mMinGasPrice = new BigInteger("4000000000");

    public static BigInteger getSTGasPrice() {
        if (stGasPrice == null || stGasPrice.longValue() <= 0) {
            stGasPrice = mMinGasPrice;
        }
        return stGasPrice;
    }

    public static BigInteger getMinGasLimit() {
        if (minGasLimit == null || minGasLimit.longValue() <= 0) {
            minGasLimit = new BigInteger("21000");
        }
        return minGasLimit;

    }

    public static BigInteger getMinGasPrice() {
        BigInteger divide = getSTGasPrice().divide(mMinGasPrice);
        if (divide.longValue() >= 2) {
            return getSTGasPrice().subtract(new BigInteger(UNIT_G));
        } else {
            return mMinGasPrice;
        }
    }


    public static BigDecimal wei2Gwei(BigInteger wei) {
        BigDecimal bigDecimal = new BigDecimal(wei);
        return bigDecimal.divide(new BigDecimal(UNIT_G), 3, BigDecimal.ROUND_UP);
    }

    public static BigInteger gwei2Wei(BigInteger gwei) {

        return gwei.multiply(new BigInteger(UNIT_G));
    }


    private static WalletInfo initWalletInfo() {
        WalletInfo wallet = OCPPrefUtils.getCurrentWallet();
        if (wallet == null) {
            List<WalletInfo> walletInfo = WalletInfoDaoUtils.sqlAll(MyApp.getContext());
            if (walletInfo != null && walletInfo.size() > 0) {
                wallet = walletInfo.get(0);
            }
        }
        return wallet;
    }


    public static WalletInfo getCurrentWallet() {
        if (currentWallet == null) {
            currentWallet = initWalletInfo();
        }
        return currentWallet;
    }


    public static void setCurrentWallet(WalletInfo wallet){
        if(wallet ==null) return;
         currentWallet = wallet;
         OCPPrefUtils.setCurrentWallet(wallet);
    }
}
