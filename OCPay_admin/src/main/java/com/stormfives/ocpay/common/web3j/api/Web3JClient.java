package com.stormfives.ocpay.common.web3j.api;

import com.stormfives.ocpay.common.web3j.service.CustomNodeService;
import org.web3j.protocol.Web3j;

public class Web3JClient {
    private Web3JClient(){}

    private volatile static Web3j web3j;

    public static Web3j getClient(){
        if(web3j==null){
            synchronized (Web3JClient.class){
                if(web3j==null){
                    web3j = Web3j.build(new CustomNodeService());
                }
            }
        }
        return web3j;
    }
}
