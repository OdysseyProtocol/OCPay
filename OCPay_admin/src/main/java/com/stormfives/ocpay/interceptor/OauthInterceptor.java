package com.stormfives.ocpay.interceptor;



import com.stormfives.ocpay.common.exception.UnauthorizedException;
import com.stormfives.ocpay.common.util.MessageSourceUtil;
import com.stormfives.ocpay.common.util.WalletUtil;
import com.stormfives.ocpay.log.dao.LogOperateAdminMapper;
import com.stormfives.ocpay.log.dao.entity.LogOperateAdmin;
import com.stormfives.ocpay.token.service.OauthService;
import com.stormfives.ocpay.token.vo.TokenVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by cc on 16/10/11.
 *
 * 权限校验
 */
@Component
public class OauthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private MessageSourceUtil messageSourceUtil;

    @Autowired
    LogOperateAdminMapper logOperateAdminMapper;




    //线程的时间
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("watch_startTime");



    Logger logger = LoggerFactory.getLogger(OauthInterceptor.class);


    /**
     * This implementation always returns {@code true}.
     *
     * @param request
     * @param response
     * @param handler
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long beginTime = System.currentTimeMillis();// 1、开始时间
        startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）

        StringBuffer url = request.getRequestURL();

        setLanguage(request);

        if (url.indexOf("/api/isAlive") >=0 || url.indexOf("/token") >=0 || url.indexOf("login") >= 0 | url.indexOf("pic/code") >= 0)
            return super.preHandle(request, response, handler);

        String headToken = request.getHeader("Authorization");

        if (request.getParameter("Authorization") != null){
            headToken = request.getParameter("Authorization");
        }
        if (StringUtils.isBlank(headToken) || headToken.length()<="Bearer ".length())
            throw new UnauthorizedException(messageSourceUtil.getMessage("authorization"));

        String accessToken = headToken.substring("Bearer ".length());
        TokenVo token = oauthService.getTokenByAccessToken(accessToken);

        if (token == null || !token.getScope().equalsIgnoreCase("ocpay"))
            throw new UnauthorizedException(messageSourceUtil.getMessage("authorization"));

        request.setAttribute("adminId", token.getResourceOwnerId());
        return super.preHandle(request, response, handler);
    }


    public void setLanguage(HttpServletRequest request){
        String language = request.getHeader("Accept-Language");    //获取语言信息
        if (StringUtils.isNotBlank(language)){
            if (language.indexOf("hant") == 0 || language.indexOf("Hant") > 0){
                LocaleContextHolder.setLocale(new Locale("hant", "CN"));
            }else if(language.indexOf("en-uk") == 0){
                //英国英语
                LocaleContextHolder.setLocale(new Locale("en-uk", "GB"));
            }else if(language.indexOf("en-au") == 0){
                //澳大利亚英语
                LocaleContextHolder.setLocale(new Locale("en-au", "AU"));
            }else if (language.indexOf("en") == 0){
                //英语
                LocaleContextHolder.setLocale(new Locale("en", "US"));
            }else if (language.indexOf("zh") == 0){
                //简体中文
                LocaleContextHolder.setLocale(new Locale("zh", "CN"));
            }else if (language.indexOf("ms") == 0){
                //马来西亚
                LocaleContextHolder.setLocale(new Locale("ms", "MY"));
            }else if (language.indexOf("th") == 0){
                //泰语
                LocaleContextHolder.setLocale(new Locale("th", "TH"));
            }else if(language.indexOf("nl") == 0){
                //荷兰语
                LocaleContextHolder.setLocale(new Locale("nl", "NL"));
            }else if(language.indexOf("ko") == 0){
                //韩语
                LocaleContextHolder.setLocale(new Locale("ko", "KR"));
            }else if(language.indexOf("fr-ch") == 0){
                //瑞士法语
                LocaleContextHolder.setLocale(new Locale("fr-ch", "CH"));
            }else if(language.indexOf("fr") == 0){
                //法语
                LocaleContextHolder.setLocale(new Locale("fr", "FR"));
            }else if(language.indexOf("de-ch") == 0){
                //瑞士德语
                LocaleContextHolder.setLocale(new Locale("de-ch", "CH"));
            }else if(language.indexOf("de") == 0){
                //德语
                LocaleContextHolder.setLocale(new Locale("de", "DE"));
            }
        }else{
            LocaleContextHolder.setLocale(new Locale("en", "US"));
        }
    }



    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        try{
            String actionName = "";
            if(handler instanceof  HandlerMethod){
                HandlerMethod handler2 = (HandlerMethod) handler;
                actionName = handler2.getMethod().getName();
            }else{
                actionName = request.getRequestURI();
            }
            long endTime = System.currentTimeMillis();// 2、结束时间
            long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
            int consumeTime = (int)(endTime - beginTime);// 3、消耗的时间
            String ip = WalletUtil.getStringIP(request);
            int userId=0;
            if(request.getAttribute("adminId")!=null){
                userId = Integer.valueOf(request.getAttribute("adminId").toString());
            }
            String param ="";
            if(request.getAttribute("param")!=null){
                param =  request.getAttribute("param").toString();
            }
            LogOperateAdmin logOperation = WalletUtil.initCustomerLog(userId,actionName,consumeTime,ip,param);
            logOperateAdminMapper.insertSelective(logOperation);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }


}
