package com.stormfives.ocpay.common.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

/**
 * 格式化工具
 * Created by tlw on 2017/7/20.
 */
public class DecimalUtil {

    /**
     * 展示两位小数
     * @param b
     * @return
     */
    public static String formatTwoDecimalPlaces(BigDecimal b){
        DecimalFormat twoDecimalPlaces = new DecimalFormat("0.00");
        return twoDecimalPlaces.format(b);
    }

}
