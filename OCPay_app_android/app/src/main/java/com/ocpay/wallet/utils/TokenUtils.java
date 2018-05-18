package com.ocpay.wallet.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.ocpay.wallet.Constans.TEST.OCN_TOKEN_ADDRESS;

/**
 * Created by y on 2018/5/18.
 */

public class TokenUtils {

    public final static String TOKEN_OCN = "OCN";
    public final static String ETH = "ETH";
    public static BigDecimal ethPrice;
    public static BigDecimal ocnPrice;


    public static String getTokenAddress(String tokenName) {
        return getTokenMap().get(tokenName);
    }


    public static Map<String, String> getTokenMap() {
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put(TOKEN_OCN, OCN_TOKEN_ADDRESS);
        return tokenMap;

    }


    public static BigDecimal getTokenPrice(String tokenName) {
        return OCPPrefUtils.getTokenPrice(tokenName);

    }

    public static void setTokenPrice(String tokenName, BigDecimal tokenPrice) {
        OCPPrefUtils.setTokenPrice(tokenName, tokenPrice);
    }


}
