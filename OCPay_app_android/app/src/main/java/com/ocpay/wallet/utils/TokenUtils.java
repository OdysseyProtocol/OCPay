package com.ocpay.wallet.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.ocpay.wallet.Constans.TEST.OCN_TOKEN_ADDRESS;

/**
 * Created by y on 2018/5/18.
 */

public class TokenUtils {

    public final static String OCN = "OCN";
    public final static String ETH = "ETH";
    public static BigDecimal ethPrice;
    public static BigDecimal ocnPrice;


    public static String getTokenAddress(String tokenName) {
        return getTokenMap().get(tokenName);
    }


    public static Map<String, String> getTokenMap() {
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put(OCN, OCN_TOKEN_ADDRESS);
        return tokenMap;

    }


    public static BigDecimal getTokenPrice(String tokenName) {
        return OCPPrefUtils.getTokenPrice(tokenName);

    }

    public static void setTokenPrice(String tokenName, BigDecimal tokenPrice) {
        OCPPrefUtils.setTokenPrice(tokenName, tokenPrice);
    }

    public static String getTokenNameByAddress(String address) {
        if (address == null ) return "";
        if (!address.startsWith("0x") && address.length() == 40) {
            address = "0x" + address;
        }
        Map<String, String> tokenMap = getTokenMap();
        Set<String> strings = tokenMap.keySet();
        for (String key : strings) {
            if (address.equals(tokenMap.get(key))) {
                return key;
            }
        }
        return "";
    }


}
