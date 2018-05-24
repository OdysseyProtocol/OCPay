package com.stormfives.ocpay.common.log;

import java.lang.annotation.*;

/**
 * Created by tlw on 2017/9/8.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Remark {
    String value() default "";
}
