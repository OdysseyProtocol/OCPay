package com.odwallet.common.exception;

import com.odwallet.common.web.ObikeHttpStatus;
import com.odwallet.common.web.ObikeResponseStatus;

/**
 * 收到此错误参数 拿到信息 继续处理. 返回码 100
 * Created by zhaimi on 15/10/31.
 */
@ObikeResponseStatus(ObikeHttpStatus.CONTINUE)
public class ContinueException extends Exception {

    /**
     * 无参构造方法.
     */
    public ContinueException() {
    }

    /**
     * 有参构造方法.
     *
     * @param s 参数
     */
    public ContinueException(String s) {
        super(s);
    }
}
