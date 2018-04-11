package com.odwallet.rechage.schedule;

import com.alibaba.fastjson.JSON;
import com.odwallet.common.util.Constants;
import com.odwallet.rechage.dao.CoinInfoMapper;
import com.odwallet.rechage.dao.UserCoinBalanceMapper;
import com.odwallet.rechage.dao.UserWalletInfoMapper;
import com.odwallet.rechage.entity.*;
import com.odwallet.rechage.rabbit.RabbitRechargeConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@EnableScheduling
public class RechargeGatherTask implements SchedulingConfigurer {

    @Autowired
    private CoinInfoMapper coinInfoMapper;

    @Autowired
    private UserCoinBalanceMapper userCoinBalanceMapper;

    @Autowired
    private UserWalletInfoMapper userWalletInfoMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     *
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        CoinInfo coinInfo = coinInfoMapper.selectByPrimaryKey(1);
        scheduledTaskRegistrar.addTriggerTask(
                () -> {
                    UserCoinBalanceExample userCoinBalanceExample = new UserCoinBalanceExample();
                    UserCoinBalanceExample.Criteria criteria = userCoinBalanceExample.createCriteria();
                    criteria.andTransferStatusEqualTo(Constants.ZERO_BYTE).andCoinBalanceGreaterThan(new BigDecimal(coinInfo.getCoinLowerLimit()));
                    List<UserCoinBalance> userCoinBalances = userCoinBalanceMapper.selectByExample(userCoinBalanceExample);

                    if (userCoinBalances != null && userCoinBalances.size() > 0) {

                        for (UserCoinBalance userCoinBalance : userCoinBalances) {
                            UserWalletInfo userWalletInfo = userWalletInfoMapper.selectByUserIdAndMerchantInfoId(userCoinBalance.getUserid(), userCoinBalance.getMerchantId());
                            //去获取邮费
                            TransactionMsg transactionMsg = new TransactionMsg();
                            transactionMsg.setUserCoinBalance(userCoinBalance);
                            transactionMsg.setUserWalletInfo(userWalletInfo);
                            String toTransferMsg = JSON.toJSONString(transactionMsg);
                            rabbitTemplate.convertAndSend(RabbitRechargeConfig.RECHARGE_GAS_TO_USER_ACCOUNT, toTransferMsg);

                        }
                    }

                },
                triggerContext -> {
                    String cron = "0 0 0 */" + coinInfo.getDayCycleRound() + " * ?";
                    if (StringUtils.isEmpty(cron)) {

                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
