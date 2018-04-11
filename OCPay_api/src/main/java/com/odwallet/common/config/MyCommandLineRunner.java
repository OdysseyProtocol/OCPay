package com.odwallet.common.config;

import com.odwallet.common.web3j.api.Web3JClient;
import com.odwallet.common.web3j.transaction.TransactionOnNode;
import com.odwallet.rechage.dao.CoinInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;

import java.util.List;

/**
 * Created by liuhuan on 2018/3/13.
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CoinInfoMapper coinInfoMapper;

    @Autowired
    private TransactionOnNode transactionOnNode;


    @Override
    public void run(String... strings) throws Exception {
        List<String> address = coinInfoMapper.selectContractAddress();
        Web3j web3j = Web3JClient.getClient();
        transactionOnNode.subscribeContractAddress(web3j, address);
    }
}
