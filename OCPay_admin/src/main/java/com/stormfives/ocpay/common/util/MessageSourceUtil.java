package com.stormfives.ocpay.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Created by zxb on 2016/11/7.
 */
@Component
public class MessageSourceUtil {

    @Resource
    private MessageSource messageSource;

    private Logger logger = LoggerFactory.getLogger(MessageSourceUtil.class);

    public String getMessage(String key) {
        return getMessage(key, null);
    }

    /**
     *
     * @param key ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String key, Object[] args){
        return getMessage(key, args, "");
    }

    /**
     *
     * @param key ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String key,Object[] args,String defaultMessage){
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, defaultMessage,locale);
    }
}