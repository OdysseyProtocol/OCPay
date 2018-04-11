package com.odwallet.rechage.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.odwallet.common.apisecurity.AESCBC;
import com.odwallet.common.apisecurity.AESCBCUtil;
import com.odwallet.common.web3j.api.Web3JClient;
import com.odwallet.common.web3j.response.TransactionsResponse;
import com.odwallet.common.web3j.service.CustomNodeService;
import com.odwallet.common.web3j.transaction.OWalletTransaction;
import com.odwallet.common.web3j.transaction.TransactionOnNode;
import com.odwallet.rechage.dao.TransactionOrderMapper;
import com.odwallet.rechage.entity.TransactionOrder;
import com.odwallet.rechage.entity.TransactionOrderExample;
import com.odwallet.rechage.service.RechargeCoinToGatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lyc
 * Date: 2018/3/19
 * Time: 上午10:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RechargeCoinToGatherReceiverTest {

    @Autowired
    private RechargeCoinToGatherService rechargeCoinToGatherService;

    @Test
    public void getWalletAddressByTypeAndMerchantId() throws Exception {

        String s = rechargeCoinToGatherService.getWalletAddressByTypeAndMerchantId(1, 1);
    }

    @Test
    public void checkBalance() throws Exception {
        //回调通知充值成功
        JSONObject callBackJSONObject = new JSONObject();
        callBackJSONObject.put("from", "0x33c6ff30b9f95fee1e19b03798a40a96bd6f5324");
        callBackJSONObject.put("to", "0xe241b53ab309fb863843cc229a3ad1d3964fb387");
        callBackJSONObject.put("orderId",new BigInteger("2018033012345678"));
        callBackJSONObject.put("coinNum", new BigDecimal("1.000000000000000000"));
        callBackJSONObject.put("fee", new BigDecimal("00021276"));
        callBackJSONObject.put("tradingTime", new Date());
        callBackJSONObject.put("txHash","0x1021308d7c96bdcf2b15c76fdddf2e46fa6977b19cbc6792dbcbbe0491305537");

        String seed = AESCBC.getRandomStringByLength(16);
        String in = AESCBCUtil.encrypt(callBackJSONObject, "oBike", "HtKtU2tf3pegtvP8ZhnDlj8kg7Tbti", "2ue-/,jgtw6uSw&e", seed);
        Map map = new HashMap();
        map.put("in", in);
        map.put("seed", seed);
        System.out.println(in);
        System.out.println(seed);



    }


    @Test
    public void transfer() throws Exception {
        //回调通知充值成功
        JSONObject callBackJSONObject = new JSONObject();
        callBackJSONObject.put("fromAddress", "0xdfa9540e3a6fe6ac0bca70bb7a3c1aeb93e43da1");
        callBackJSONObject.put("toAddress", "0x5623629c15733d9394706c6c9dd92ded590acbf5");
        callBackJSONObject.put("orderId",100);
        callBackJSONObject.put("coinNum", new BigDecimal("3"));
        callBackJSONObject.put("fee", new BigDecimal("0.000217"));
        callBackJSONObject.put("privateKey", "00b0fb794774c598c5afb6761e0ebdd33590ea1a442ed7eb8587a29cb9fbdb941c");
        callBackJSONObject.put("coinId", 1);


        String seed = AESCBC.getRandomStringByLength(16);
        System.out.println(seed);
        String in = AESCBCUtil.encrypt(callBackJSONObject, "avazu1111", "jJ89tLnG4qBNQ4QgrLMzhG6SFwqM3#", "9M8gFU94ZzRX%ac7", seed);
        System.out.println(in);
        Map map = new HashMap();
        map.put("in", in);
        map.put("seed", seed);
       // String s = HttpRequestUtil.doPostHeaderJsonFromJSONtoString("http://mobile-dev.o.bike/api/v1/ocnwallet/recharge", map);



    }


    @Autowired
    private TransactionOrderMapper transactionOrderMapper;

    @Test
    public void getBlockBetween() throws IOException {
        TransactionOrderExample transactionOrderExample = new TransactionOrderExample();
        TransactionOrderExample.Criteria criteria = transactionOrderExample.createCriteria();
        criteria.andCoinIdEqualTo(1);
        CustomNodeService customNodeService = new CustomNodeService();
        Web3j web3j = Web3JClient.getClient();
        List<TransactionOrder> transactionOrders = transactionOrderMapper.selectByExample(transactionOrderExample);
        BigInteger maxBlockNumber = null;
        BigInteger minBlockNumber = null;
        for (TransactionOrder t : transactionOrders) {
            TransactionReceipt transactionReceipt = TransactionOnNode.getTransactionReceipt(web3j, t.getTxHash());
            if (maxBlockNumber == null) {
                maxBlockNumber = transactionReceipt.getBlockNumber();
                minBlockNumber = transactionReceipt.getBlockNumber();
            } else {
                minBlockNumber = (minBlockNumber.compareTo(transactionReceipt.getBlockNumber()) > 0) ? transactionReceipt.getBlockNumber() : minBlockNumber;
                maxBlockNumber = (maxBlockNumber.compareTo(transactionReceipt.getBlockNumber()) > 0) ? maxBlockNumber : transactionReceipt.getBlockNumber();
            }

        }

    }

    //max:[5283587]___min:[5280007]
    //5283961
    String fromBlockNumebr = "5280007";
    String toBlockNumebr = "5283937";
    String OCNAddress = "0x4092678e4e78230f46a1534c0fbc8fa39780892b";

    @Test
    public void verifyBlockScan() throws Exception {

        List<TransactionsResponse.CustomTransaction> transactionList = OWalletTransaction.getTransactionList(OCNAddress, fromBlockNumebr, toBlockNumebr);
        List<TransactionsResponse.CustomTransaction> lostList = new ArrayList<>();
        TransactionOrderExample transactionOrderExample = new TransactionOrderExample();
        for (TransactionsResponse.CustomTransaction transaction : transactionList) {
            transactionOrderExample.clear();
            TransactionOrderExample.Criteria criteria = transactionOrderExample.createCriteria();
            criteria.andTxHashEqualTo(transaction.getHash());
            List<TransactionOrder> transactionOrders = transactionOrderMapper.selectByExample(transactionOrderExample);
            if (transactionOrders == null || transactionOrders.size() <= 0) {
                lostList.add(transaction);
                System.out.println(String.format("Txhash:_[!!不存在的!]__[%s]_[%s]", transaction.getHash(), transactionOrders.size()));

            } else {
                System.out.println(String.format("Txhash:__________________________数据库中存在___________[%s]_[%s]", transaction.getHash(), transactionOrders.size()));

            }
        }


        for (TransactionsResponse.CustomTransaction transaction : lostList) {
            System.out.println(String.format("Txhash:[%s]", transaction.getHash()));
        }
        TransactionOrderExample transactionOrderExample1 = new TransactionOrderExample();
        TransactionOrderExample.Criteria criteria1 = transactionOrderExample1.createCriteria();
        criteria1.andCoinNameEqualTo("OCN");
        List<TransactionOrder> transactionOrders = transactionOrderMapper.selectByExample(transactionOrderExample1);
        boolean get = false;
        for (TransactionOrder t : transactionOrders) {
            get = false;
            for (TransactionsResponse.CustomTransaction transaction : transactionList) {
                if (t.getTxHash().equals(transaction.getHash())) {
                    get = true;
                    break;
                }
            }
            if (!get) {
                System.out.println(String.format("遗漏————————————:[%s]", t.getTxHash()));
            }

        }

        System.out.println(String.format("Txhash:__________between_size___________[%s]", transactionList.size()));
        System.out.println(String.format("Txhash:__________sql_ocn_size___________[%s]", transactionOrders.size()));


    }

}