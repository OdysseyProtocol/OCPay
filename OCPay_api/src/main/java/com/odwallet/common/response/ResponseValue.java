package com.odwallet.common.response;

/**
 * Created by fly on 17/12/05.
 */
public class ResponseValue<T> {

    private static final long serialVersionUID = 4994712878622993977L;

    private T data;
    private boolean success = true;
    /**
     * 专门给微服务使用
     */
    private int errorCode = 100;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
