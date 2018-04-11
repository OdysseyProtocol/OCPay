package com.odwallet.common.exception;


import com.odwallet.common.web.ObikeHttpStatus;
import com.odwallet.common.web.ObikeResponseStatus;

/**
 * 请求参数错误. 返回码 400
 * Created by zhaimi on 15/10/31.
 */
@ObikeResponseStatus(ObikeHttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends Exception {

    /**
     * 无参构造方法.
     */
    public InvalidArgumentException() {
    }

    /**
     * 有参构造方法.
     *
     * @param s 参数
     */
    public InvalidArgumentException(String s) {
        super(s);
    }
}
