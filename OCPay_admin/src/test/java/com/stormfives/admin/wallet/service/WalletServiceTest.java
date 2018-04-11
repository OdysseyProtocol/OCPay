package com.stormfives.admin.wallet.service;

import com.stormfives.admin.token.domain.Page;
import com.stormfives.admin.wallet.controller.req.MerchantInfoBillListReq;
import com.stormfives.admin.wallet.controller.req.WalletGroupReq;
import com.stormfives.admin.wallet.dao.entity.MerchantInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: lyc
 * Date: 2018/3/16
 * Time: 上午11:26
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class WalletServiceTest {

    @Autowired
    WalletService walletService;


    @Test
    public void addMerchantInfo() throws Exception {
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setMerchantName("hahaah");

        walletService.addMerchantInfo(merchantInfo,1);

    }

    @Test
    public void getWalletGroupList() throws Exception{
        WalletGroupReq walletGroupReq = new WalletGroupReq();
        walletGroupReq.setPageNum(1);
        Page page = walletService.getWalletGroupList(walletGroupReq);
        System.out.println(page);

    }

    @Test
    public void getWalletGroupPlatforms() throws Exception{
        String walletGroupPlatforms = walletService.getWalletGroupPlatforms(1);
        System.out.println(walletGroupPlatforms);
    }

    @Test
    public  void testAmount(){
        MerchantInfoBillListReq req = new MerchantInfoBillListReq();
        req.setMerchantId(1);
        req.setBeginTradingTime("1520956800000");
        req.setEndTradingTime("1522857600000");
        Page page = walletService.getMerchantInfoBillList(req);
        System.out.println(page);
    }

}