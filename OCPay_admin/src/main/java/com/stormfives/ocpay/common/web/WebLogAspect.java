package com.stormfives.ocpay.common.web;

import com.alibaba.fastjson.JSON;
import com.stormfives.ocpay.common.util.WalletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxb on 18/04/2017.
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(public * com.stormfives..controller..*.*(..))")
    public void webLog(){}


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        Object[] args = joinPoint.getArgs();

        if (!request.getMethod().equalsIgnoreCase("get")){
            List<Object> param = new ArrayList<>();
            for (Object object : args){
                if (!(object instanceof HttpServletRequest) && !(object instanceof HttpServletResponse)&&!(object instanceof ExtendedServletRequestDataBinder)){  //过滤掉 HttpServletRequest 、 HttpServletResponse 和 ExtendedServletRequestDataBinder 参数
                    param.add(object);
                }
            }
            request.setAttribute("param", JSON.toJSONString(param));
        }else{//get request
            request.setAttribute("param", WalletUtil.getReqGetParam(request));
        }



    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

}
