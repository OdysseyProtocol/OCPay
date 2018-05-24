package com.stormfives.ocpay.common.web;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: LuoYuanchun
 * Date: 17/10/9
 * Time: 上午11:32
 */
@Component
public class BaseController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                if (value != null && !value.isEmpty()) {

                    if (value.contains("-")) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            setValue(dateFormat.parse(value));

                        } catch (ParseException e) {
                            e.printStackTrace();
                            //todo 吞噬转化异常
                        }
                    } else {
                        setValue(new Date(Long.valueOf(value))); //将时间戳格式封装为date
                    }
                }

            }
        });

    }

}
