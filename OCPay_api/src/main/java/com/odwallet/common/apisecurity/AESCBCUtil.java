package com.odwallet.common.apisecurity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.odwallet.common.util.MD5Util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tlw on 2017/12/9.
 */

public class AESCBCUtil {

    private static final String CHANNEL = "";
    private static final String CHANNEL_KEY = "";
    private static final String SKEY = "";
   // @Test
    public void encrypt() throws Exception {

        String seed = AESCBC.getRandomStringByLength(16);
        System.out.println(seed);//6h8cSi585LwO6Fyn
        JSONObject object = new JSONObject();
        object.put("action", "test");
        object.put("message", "Connected.");
        String in = AESCBCUtil.encrypt(object, CHANNEL,CHANNEL_KEY,SKEY,seed);
        System.out.println(in);
        //uYhxkCUTcQ2OMxUO2SvnFK%2FSuKh1w5rpsD2TVigF44V1T7a0PwBw4CDeaERoB21S4tLwb2F16DcmbwqOYyzaElps1Lh77%2FLQMrVr2LF9PMPg%2Bm2XDwDvbZXj52JFdI4M8Ohx1hJ8fjSUblW9prsrXYFrWtNKsMFmvvHvRGz59PBJU7TaXPz1%2BU3EPgNTZxlYoAOZF4E%2BIOF4TeCXTPiFVQ%3D%3D
    }

   // @Test
    public void decrypt() throws Exception {
        String in = "uYhxkCUTcQ2OMxUO2SvnFK%2FSuKh1w5rpsD2TVigF44V1T7a0PwBw4CDeaERoB21S4tLwb2F16DcmbwqOYyzaElps1Lh77%2FLQMrVr2LF9PMPg%2Bm2XDwDvbZXj52JFdI4M8Ohx1hJ8fjSUblW9prsrXYFrWtNKsMFmvvHvRGz59PBJU7TaXPz1%2BU3EPgNTZxlYoAOZF4E%2BIOF4TeCXTPiFVQ%3D%3D";
        String seed = "6h8cSi585LwO6Fyn";
        String decrypted = AESCBCUtil.decrypt(in,CHANNEL,CHANNEL_KEY,SKEY,seed);
        System.out.println(decrypted);//{"action":"test","message":"Connected."}
    }


    public static String encrypt(JSONObject object, String channel, String channelKey, String skey, String seed) {
        String sign = String.format("%s%s%s@%s",
                channel, JSON.toJSONString(object, SerializerFeature.MapSortField), seed, channelKey);
        sign = MD5Util.MD5Encode(sign,"utf-8", true);
        try {
            String input = String.format("channel=%s&data=%s&seed=%s&sign=%s",
                    channel, URLEncoder.encode(object.toJSONString(), "utf-8"), seed, sign);
            String in = AESCBC.getInstance().encrypt(input, skey, seed);
            in = URLEncoder.encode(in, "utf-8");
            return in;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String in,String merchant, String apiKey,String security, String randomSeed) {
        JSONObject jsonObject = new JSONObject();
        String data = null;
        try {
            in = URLDecoder.decode(in, "utf-8");
            in = AESCBC.getInstance().decrypt(in, security, randomSeed);
            Map<String, String> dataMap = getQueryMap(in);
            data = dataMap.get("data");
            String sign = dataMap.get("sign");

            data = URLDecoder.decode(data, "utf-8");
            jsonObject = JSON.parseObject(data);
            String mySign = merchant + JSON.toJSONString(jsonObject, SerializerFeature.MapSortField) + randomSeed + "@" + apiKey;
            mySign = MD5Util.MD5Encode(mySign,"utf-8", true);;
            if (!sign.equals(mySign)) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    public static Map<String, String> getQueryMap(String in) {
        Map<String, String> map = new HashMap<>();
        String data[] = in.split("&");
        for (String str : data) {
            String s[] = str.split("=");
            map.put(s[0], s[1]);
        }
        return map;
    }
}
