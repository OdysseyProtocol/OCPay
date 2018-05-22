package com.ocpay.wallet.utils;

import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by y on 2018/5/19.
 */

public class CurrencyUtils {

    public static final String CNY = "CNY";
    public static final String CNY_FLAG = "￥";
    public static final String USD = "USD";
    public static final String USD_FLAG = "＄";


    public static Map<String, String> getCurrencyMap() {
        Map<String, String> map = new ArrayMap<>();
        map.put(CNY, CNY_FLAG);
        map.put(USD, USD_FLAG);
        return map;
    }
}
