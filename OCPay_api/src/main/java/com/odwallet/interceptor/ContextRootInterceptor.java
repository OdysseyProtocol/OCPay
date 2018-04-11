package com.odwallet.interceptor;


import com.alibaba.fastjson.JSON;
import com.odwallet.rechage.entity.LogOperateApi;
import com.odwallet.rechage.rabbit.RabbitRechargeConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class ContextRootInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("watch_startTime");

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();// 1、开始时间
        startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization,,X-File-Name");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }


    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String actPath = request.getRequestURI();
        String in = (String) request.getAttribute("in");
        String seed = (String) request.getAttribute("seed");
        Integer merchantId = (Integer) request.getAttribute("merchantId");
        long endTime = System.currentTimeMillis();// 2、结束时间
        long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
        int consumeTime = (int)(endTime - beginTime);// 3、消耗的时间
        LogOperateApi logOperateApi = new LogOperateApi();
        logOperateApi.setActpath(actPath);
        logOperateApi.setCreatetime(new Date());
        logOperateApi.setConsumetime(consumeTime);
        logOperateApi.setParam(in);
        logOperateApi.setSeed(seed);
        logOperateApi.setMerchantId(merchantId);
        rabbitTemplate.convertAndSend(RabbitRechargeConfig.OPERATION_API_LOG,JSON.toJSONString(logOperateApi));


    }


}