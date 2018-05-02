package com.ocpay.wallet.utils.web3j.api;

import com.ocpay.wallet.utils.web3j.service.CustomNodeService;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;

public class Web3JClient {
    private Web3JClient() {
    }

    private volatile static Web3j web3j;

    public static Web3j getClient() {
        if (web3j == null) {
            synchronized (Web3JClient.class) {
                if (web3j == null) {

                    web3j = Web3jFactory.build(new CustomNodeService());
                }
            }
        }
        return web3j;
    }
}
