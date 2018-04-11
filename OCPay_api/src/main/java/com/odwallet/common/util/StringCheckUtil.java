package com.odwallet.common.util;

import java.util.regex.Pattern;

/**
 * Created by tlw on 2017/7/28.
 */
public class StringCheckUtil {

    /**
     * 必需包含大小写字母、数字
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean isPassword(String str, int min, int max) {
        String regex = "^(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{"+min+","+max+"}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }
    /**
     * 字母数字
     * @param str
     * @return
     */
    public static boolean isAlphabetDigit(String str){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        return pattern.matcher(str).matches();
    }

    /**
     * 纯数字
     * @param str
     * @return
     */
    public static boolean isDigit(String str){
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(str).matches();
    }

    /**
     * 包含字母
     * @param str
     * @return
     */
    public static boolean containsAlphabet(String str){
        String regex=".*[a-zA-Z]+.*";
        return Pattern.compile(regex).matcher(str).matches();
    }

    /**
     * 是否包含大小写字母和数字
     * @return
     */
    public static boolean isContainsLowAndUpperAlphabetAndDigit(String str,int minLength,int maxLength){
        String regex="^(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{"+minLength+","+maxLength+"}$";
        return Pattern.compile(regex).matcher(str).matches();
    }
}
