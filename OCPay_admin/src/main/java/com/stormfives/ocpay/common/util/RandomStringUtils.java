package com.stormfives.ocpay.common.util;


import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: lyc
 * Date: 2018/3/16
 * Time: 下午1:30
 */
public class RandomStringUtils {

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ%-+#$";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


}
