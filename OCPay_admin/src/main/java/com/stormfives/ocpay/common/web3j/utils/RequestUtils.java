package com.stormfives.ocpay.common.web3j.utils;


public class RequestUtils {
    public static String sendGet(String url) {
        try {
            String s = HttpRequestUtil.doGet(url,null);
            return  s;
        }catch (Exception e){

        }
      return  null;
    }




}  