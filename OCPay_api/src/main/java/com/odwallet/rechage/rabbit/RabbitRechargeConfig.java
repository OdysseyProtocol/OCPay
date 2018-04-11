package com.odwallet.rechage.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by fly on 2017/11/26.
 */
@Configuration
public class RabbitRechargeConfig {
    public static final String CHECK_BALANCE = "check_balance";
    public static final String RECHARGE_GAS_TO_USER_ACCOUNT = "recharge_gas_to_user_account";
    public static final String RECHARGE_COIN_TO_GATHER_ACCOUNT = "recharge_coin_to_gather_account";
    public static final String SCAN_BLOCK_ORDER = "scan_block_order";
    public static final String CHECK_NODE_RECHARGE = "check_node_recharge";
    public static final String AFFIRM_RECHARGE_TO_GATHER = "affirm_recharge_to_gather";
    public static final String AFFIRM_PLATFORM_TRANSFER = "affirm_platform_transfer";
    public static final String OPERATION_API_LOG = "operation_api_log";


    @Bean
    public Queue checkCoinBalance() {
        return new Queue(RabbitRechargeConfig.CHECK_BALANCE);
    }

    @Bean
    public Queue rechargeGasToUserAccount() {
        return new Queue(RabbitRechargeConfig.RECHARGE_GAS_TO_USER_ACCOUNT);
    }

    @Bean
    public Queue scanBlockOrder() {
        return new Queue(RabbitRechargeConfig.SCAN_BLOCK_ORDER);
    }

    @Bean
    public Queue checkNodeRecharge() {
        return new Queue(RabbitRechargeConfig.CHECK_NODE_RECHARGE);
    }


    @Bean
    public Queue rechargeCoinToGatherAccount() {
        return new Queue(RabbitRechargeConfig.RECHARGE_COIN_TO_GATHER_ACCOUNT);
    }

    @Bean
    public Queue affirmRechargeToGather() {
        return new Queue(RabbitRechargeConfig.AFFIRM_RECHARGE_TO_GATHER);
    }

    @Bean
    public Queue recordOperationApiLog() {
        return new Queue(RabbitRechargeConfig.AFFIRM_PLATFORM_TRANSFER);
    }


    @Bean
    public Queue affirmPlatformTransfer() {
        return new Queue(RabbitRechargeConfig.OPERATION_API_LOG);
    }

    @Bean(name = "myConnectionFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(5);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setPrefetchCount(2);
        return factory;
    }

}



