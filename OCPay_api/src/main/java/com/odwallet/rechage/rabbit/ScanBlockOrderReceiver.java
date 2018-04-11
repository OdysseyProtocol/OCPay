package com.odwallet.rechage.rabbit;

import com.alibaba.fastjson.JSON;
import com.odwallet.rechage.dao.ScheduleBlockNumLogMapper;
import com.odwallet.rechage.dao.ScheduleBlockNumMapper;
import com.odwallet.rechage.entity.ScanBlockInfo;
import com.odwallet.rechage.entity.ScheduleBlockNum;
import com.odwallet.rechage.entity.ScheduleBlockNumLog;
import com.odwallet.rechage.service.CheckRechargeOrderOnScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class ScanBlockOrderReceiver {

    Logger logger = LoggerFactory.getLogger(ScanBlockOrderReceiver.class);

    @Autowired
    private CheckRechargeOrderOnScanService checkRechargeOrderOnScanService;

    @Autowired
    private ScheduleBlockNumMapper scheduleBlockNumMapper;
    @Autowired
    private ScheduleBlockNumLogMapper scheduleBlockNumLogMapper;

    @RabbitListener(queues = RabbitRechargeConfig.SCAN_BLOCK_ORDER,containerFactory = "myConnectionFactory")
    public void scanBlockOrder(String msg) {
        try {

            ScanBlockInfo scanBlockInfo = JSON.parseObject(msg, new com.alibaba.fastjson.TypeReference<ScanBlockInfo>() {
            });
            checkRechargeOrderOnScanService.checkScanOrder(scanBlockInfo);
            ScheduleBlockNum blockNum = scanBlockInfo.getScheduleBlockNum();
            if (blockNum != null) {
                scheduleBlockNumMapper.updateByPrimaryKeySelective(blockNum);
                ScheduleBlockNumLog scheduleBlockNumLog = new ScheduleBlockNumLog();
                scheduleBlockNumLog.setStartBlockNum(blockNum.getStartBlockNum());
                scheduleBlockNumLog.setEndBlockNum(blockNum.getEndBlockNum());
                scheduleBlockNumLog.setCoinId(blockNum.getCoinId());
                scheduleBlockNumLog.setLastScanTime(new Date());
                scheduleBlockNumLogMapper.insertSelective(scheduleBlockNumLog);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }


}
