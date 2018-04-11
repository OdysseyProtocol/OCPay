package com.odwallet.common.exception;

import com.odwallet.common.web.ObikeHttpStatus;
import com.odwallet.common.web.ObikeResponseStatus;

/**
 * 禁止访问异常类. 403
 * Created by zxb on 10/19/15.
 */
@ObikeResponseStatus(ObikeHttpStatus.FORBIDDEN)
public class NoPermissionException extends RuntimeException {

    /**
     * 无参构造函数.
     */
    public NoPermissionException() {
    }

    /**
     * 有参构造函数.
     *
     * @param s 参数
     */
    public NoPermissionException(String s) {
        super(s);
    }
}
