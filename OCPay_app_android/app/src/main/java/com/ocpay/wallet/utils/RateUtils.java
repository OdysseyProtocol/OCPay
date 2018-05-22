package com.ocpay.wallet.utils;

import com.ocpay.wallet.bean.home.TokenBalanceBean;
import com.snow.commonlibrary.log.MyLog;

import java.math.BigDecimal;
import java.util.List;

import static com.ocpay.wallet.utils.CurrencyUtils.USD;
import static com.ocpay.wallet.utils.TokenUtils.OCN;

/**
 * Created by y on 2018/5/19.
 */

public class RateUtils {

    /**
     * eg:OCN-ETH ,USD-CNY
     *
     * @param base base symbol
     * @param to   to symbol
     * @param b    amount
     */
    public static void setRatePairs(String base, String to, BigDecimal b) {
        OCPPrefUtils.setRatePairs(base, to, b);
    }


    /**
     * eg:OCN-ETH ,USD-CNY
     *
     * @param base base symbol
     * @param to   to symbol
     */
    public static BigDecimal getRatePairs(String base, String to) {
        if (base.equals(to)) {
            return new BigDecimal(1);
        }
        return OCPPrefUtils.getRatePairs(base, to);
    }


    public static BigDecimal getUserDecimal() {
        String currency = OCPPrefUtils.getCurrency();
        return getRatePairs(USD, currency);
    }


    public static BigDecimal getTotalOCN(List<TokenBalanceBean> tokenList) {
        BigDecimal total = new BigDecimal(0);
        for (TokenBalanceBean t : tokenList) {
            if (OCN.equals(t.getTokenName())) {
                total = total.add(new BigDecimal(t.getTokenBalance()));
            } else {
                BigDecimal tokenRate = getRatePairs(OCN, t.getTokenName());
                BigDecimal balance = new BigDecimal(t.getTokenBalance());
                BigDecimal totalOCN = balance.divide(tokenRate, 5, BigDecimal.ROUND_DOWN);
                total = total.add(totalOCN);
            }
        }
        return total.setScale(4, BigDecimal.ROUND_DOWN);
    }


    /**
     * @param d
     * @return
     */
    public static String estimateToken(BigDecimal d) {
        String currency = OCPPrefUtils.getCurrency();
        String name = "≈" + CurrencyUtils.getCurrencyMap().get(OCPPrefUtils.getCurrency());
        BigDecimal ratePairs = getRatePairs(USD, currency);
        MyLog.i("ratePairs:" + ratePairs);

        String value = ratePairs.multiply(d).setScale(3, BigDecimal.ROUND_DOWN).toString();
        return name + value;
    }

    /**
     * @param tokenName
     * @param tokenBalance
     * @return
     */
    public static String estimateToken(String tokenName, BigDecimal tokenBalance) {
        BigDecimal tokenPrice = TokenUtils.getTokenPrice(tokenName);
        BigDecimal multiply = tokenPrice.multiply(tokenBalance);
        return estimateToken(multiply);
    }


}
