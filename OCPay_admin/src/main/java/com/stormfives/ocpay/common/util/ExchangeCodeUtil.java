package com.stormfives.ocpay.common.util;


import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by cc on 17/1/6.
 */
public class ExchangeCodeUtil {

    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String createId() {
        String id = UUID.randomUUID().toString();

        id = DEKHash(id) + "";
        int diff = 10 - id.length();
        String randStr = RandomStringUtils.randomAlphabetic(12);
        for (int i = 0; i < diff; i++) {
            int randIndex = (int) (Math.random() * randStr.length());
            int index = (int) (Math.random() * id.length());
            id = id.substring(0, index) + randStr.charAt(randIndex) + id.substring(index, id.length());
        }
        return id;
    }

    private static int DEKHash(String str) {
        int hash = str.length();

        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }

        return (hash & 0x7FFFFFFF);
    }

    public static String getsubUUID(Integer length){
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0,length-1);
    }

    public static String getRandomALLChar(int n) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < n; i++) {
            sb.append( allChar.charAt(random.nextInt(allChar.length())));
            System.out.println(sb.toString());
        }
        return sb.toString();
    }
}
