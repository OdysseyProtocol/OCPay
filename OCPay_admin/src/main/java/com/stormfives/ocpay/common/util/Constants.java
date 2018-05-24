package com.stormfives.ocpay.common.util;

import java.math.BigDecimal;

/**
 * Created by tlw on 2018/1/23.
 */
public class Constants {


   // public static final String BLOCK_NODE_URL = "http://h.stormfives.com:34378";

    public static final String BLOCK_NODE_URL = "http://172.31.1.75:34378";

    //user_coin_log表类型
    public static final int USER_COIN_RECHARGE = 1;//用户充值
    public static final int USER_COIN_COLLECT = 2;//提币到总账
    public static final int USER_COIN_CONSUME = 3;//用户消费
    public static final int USER_COIN_OBTAIN = 4;//用户获得奖励

    //transcation_order 订单状态
    public static final int ORDER_STATUS_PEEDING = 1;//订单打包状态
    public static final int ORDER_STATUS_SUCCESS = 2;//订单成功状态
    public static final int ORDER_STATUS_FAILED = 3;//订单失败状态

    public static final int ORDER_CALLBACK_PROCESS = 1;//订单开始回调
    public static final int ORDER_CALLBACK_SUCCESS = 2;//订单回调成功


    public static final String USER_PRIVATEKEY_ERROR = "1";//用户私钥错误
    public static final String USER_LACK_OF_BALANCE = "2";//账上余额不足
    public static final String TRANSFER_ERROR = "error";//订单失败状态


    //
    public static final int USER_TRANS_NORMAL = 0;//用户提币完成
    public static final int USER_IN_TRANS = 1;//用户提币中

    public static final int USER_GAS_ACQUIRE = 2;//用户邮费获得 提币失败

    public static BigDecimal COIN_TO_GATHER = new BigDecimal("0");


    //transcation_order 订单类型
    public static final int ORDER_TYPE_USER_RECHARGE = 1;//用户充值
    public static final int ORDER_TYPE_GAS_RECHARGE = 2;//gas账户转汽油费到用户账户
    public static final int ORDER_TYPE_GATHER_RECHARGE = 3;//提币到总账
    public static final int ORDER_TYPE_PLATFORM_TRANSFER = 4;//第三方平台转账

    //确认订单时选择一次查询多少条
    public static final int NODE_UNCONFIRM_ROW = 30;//节点确认
    public static final int SCAN_UNCONFIRM_ROW = 10;//SCAN确认

    //给用户提币
    public static final int NO_TRANSFER_ORDER = 0;//无转账操作
    public static final int IN_TRANSFER_ORDER = 1;//正在提币中


    public static final String GAS_COIN_NAME = "ETH";//邮费货币名称
    public static final int GAS_COIN_ID = 3;//邮费货币id

    public static final int NOT_REPEATED_CALL_BACK_CODE = 400;//不需要重复回调的错误码
    public static final BigDecimal USER_ENOUGH_GAS = new BigDecimal("0.00001");//用户有足够的gas时


    //Integers
    public static final Integer ZERO_INTEGER = new Integer("0");
    public static final Integer ONE_INTEGER = new Integer("1");
    public static final Integer TWO_INTEGER = new Integer("2");
    public static final Integer TRHEE_INTEGER = new Integer("3");

    //Byte
    public static final Byte FIVE_BTYE = new Byte("5");
    public static final Byte FOUR_BTYE = new Byte("4");
    public static final Byte THREE_BYTE = new Byte("3");
    public static final Byte TWO_BYTE = new Byte("2");
    public static final Byte ONE_BYTE = new Byte("1");
    public static final Byte ZERO_BYTE = new Byte("0");
    public static final Byte NEGATIVE_ONE_BYTE = new Byte("-1");
}
