package com.odwallet.common.exception;


import com.odwallet.common.web.ObikeHttpStatus;
import com.odwallet.common.web.ObikeResponseStatus;

/**
 *
 * 用于标识短信发送次数超出每日上限的异常行为
 *
 * Created with IntelliJ IDEA.
 * User: LuoYuanchun
 * Date: 17/12/12
 * Time: 下午9:33
 */
@ObikeResponseStatus(ObikeHttpStatus.BAD_REQUEST)
public class SmsDayLimitException extends Exception{

    public SmsDayLimitException() {
    }

    public SmsDayLimitException(String s) {
        super(s);
    }
}
