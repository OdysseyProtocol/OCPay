package com.odwallet.common.web;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by zxb on 2017/7/11.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ObikeResponseStatus {

    /**
     * Alias for {@link #code}.
     */
    @AliasFor("code")
    ObikeHttpStatus value() default ObikeHttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * The status <em>code</em> to use for the response.
     * <p>Default is {@link ObikeHttpStatus#INTERNAL_SERVER_ERROR}, which should
     * typically be changed to something more appropriate.
     * @since 4.2
     * @see javax.servlet.http.HttpServletResponse#setStatus(int)
     * @see javax.servlet.http.HttpServletResponse#sendError(int)
     */
    @AliasFor("value")
    ObikeHttpStatus code() default ObikeHttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * The <em>reason</em> to be used for the response.
     * @see javax.servlet.http.HttpServletResponse#sendError(int, String)
     */
    String reason() default "";

}