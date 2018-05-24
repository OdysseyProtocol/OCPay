package com.stormfives.ocpay.common.util;

import com.alibaba.fastjson.JSONObject;
import com.stormfives.ocpay.log.dao.entity.LogOperateAdmin;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class WalletUtil {

    public static LogOperateAdmin initCustomerLog(int userId, String actionName, int consumeTime, String ip,String param) {
        LogOperateAdmin logOperation = new LogOperateAdmin();
        logOperation.setActpath(actionName);
        logOperation.setConsumetime(consumeTime);
        logOperation.setIp(ip);
        logOperation.setCreatetime(new Date());
        logOperation.setUserid(userId);
        logOperation.setParam(param);
        return logOperation;
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



    public static String getReqGetParam(HttpServletRequest request){
        Map<String,Object> map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return JSONObject.toJSONString(map);
    }




}
