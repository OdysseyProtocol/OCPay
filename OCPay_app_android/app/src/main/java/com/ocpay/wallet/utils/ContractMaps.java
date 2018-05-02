package com.ocpay.wallet.utils;

import com.snow.commonlibrary.utils.StringUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by y on 2018/4/16.
 */

public class ContractMaps<K, V> extends LinkedHashMap<K, V> {
    static String regex = ",";

    public static ContractMaps getInstance(String contracts) {
        ContractMaps contractMaps = new ContractMaps();

        if (StringUtil.isEmpty(contracts)) {
            return contractMaps;
        }

        String[] split = contracts.split(regex);
        for (String s : split) {
            contractMaps.put(s, s);
        }
        return contractMaps;
    }


    public String getContractsToString() {
        if (this.size() <= 0) return "";
        Iterator<K> iterator = this.keySet().iterator();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            builder.append(iterator.next() + regex);
        }
        return builder.toString();

    }

}
