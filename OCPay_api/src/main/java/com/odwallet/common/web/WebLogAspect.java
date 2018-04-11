package com.odwallet.common.web;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxb on 18/04/2017.
 */
@Aspect
@Component
@Profile("!default")
public class WebLogAspect {

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(public * com.odwallet..controller..*.*(..))")
    public void webLog(){}


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        Object[] args = joinPoint.getArgs();
        if (!request.getMethod().equalsIgnoreCase("get")){
            List<Object> param = new ArrayList<>();
            for (Object object : args){
                if (!(object instanceof HttpServletRequest) && !(object instanceof HttpServletResponse)){  //过滤掉 HttpServletRequest 和 HttpServletResponse 参数
                    param.add(object);
                }
            }
            request.setAttribute("postBody", JSON.toJSONString(param));
        }
    }

}
