package com.odwallet.rechage.rabbit;

import com.alibaba.fastjson.JSON;
import com.odwallet.rechage.dao.LogOperateApiMapper;
import com.odwallet.rechage.entity.LogOperateApi;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuhuan on 2018/3/27.
 */
@Component
public class OperationApiLogReceiver {
    @Autowired
    private LogOperateApiMapper logOperateApiMapper;
    /**
     * @param msg
     */
    @RabbitListener(queues = RabbitRechargeConfig.OPERATION_API_LOG)
    public void recordApiLog(String msg) {
            LogOperateApi logOperateApi = JSON.parseObject(msg, LogOperateApi.class);
            logOperateApiMapper.insertSelective(logOperateApi);

    }

}
