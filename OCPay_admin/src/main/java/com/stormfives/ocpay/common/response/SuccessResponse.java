package com.stormfives.ocpay.common.response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cc on 16/10/11.
 */
public class SuccessResponse extends ResponseValue {

    public SuccessResponse(Object data) {
        this.setSuccess(true);
        this.setData(data);
    }

    public SuccessResponse(String key, Object data) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(key, data);
        this.setSuccess(true);
        this.setData(dataMap);
    }
}
