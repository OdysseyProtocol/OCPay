package com.odwallet.common.util;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    //获取Long类型IP
    public static long getIP(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isNotBlank(ip)) {
            ip = ip.split(",")[0];
        }
        Long longIp = 0L;
        try {
            longIp = IPHelper.ipToLong(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return longIp;

    }

    //获取String类型IP
    public static String getStringIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isNotBlank(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    //判断请求来源
    public static int getSource(HttpServletRequest request) {
        String source = request.getHeader("User-Agent");
        if (source.contains("wechat")) {
            return 1;
        } else if (source.contains("Android")) {
            return 2;
        } else if (source.contains("iPhone")) {
            return 3;
        } else if (source.contains("pc")) {
            return 4;
        } else if (source.contains("meituanwaimai")) {
            return 5;
        } else {
            return 0;   //未知来源
        }
    }
}
