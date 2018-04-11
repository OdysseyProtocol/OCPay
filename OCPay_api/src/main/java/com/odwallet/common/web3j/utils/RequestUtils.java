package com.odwallet.common.web3j.utils;

import com.odwallet.common.util.HttpClient;

public class RequestUtils {
    public static String sendGet(String url) {
        HttpClient client = new HttpClient(url, "get");
        String s="";
        try {
            s = client.request();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  s;
    }




}  