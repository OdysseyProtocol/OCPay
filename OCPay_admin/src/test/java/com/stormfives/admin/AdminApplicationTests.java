package com.stormfives.admin;

import com.stormfives.admin.coin.dao.entity.CoinInfo;
import com.stormfives.admin.common.response.ResponseValue;
import com.stormfives.admin.user.dao.UserCoinBalanceMapper;
import com.stormfives.admin.user.dao.entity.UserCoinBalance;
import com.stormfives.admin.user.service.UserManagerService;
import com.stormfives.admin.wallet.dao.WalletGroupMapper;
import com.stormfives.admin.wallet.dao.entity.WalletGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

    @Autowired
    UserCoinBalanceMapper userCoinBalanceMapper;
    @Autowired
    UserManagerService thirdPaltformService;

    @Test
    public void contextLoads() {
        UserCoinBalance userCoinBalance = new UserCoinBalance();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            userCoinBalance.setUserid(random.nextInt(1000));
            userCoinBalance.setMerchantId(random.nextInt(2) + 1);
            int coinId = random.nextInt(2) + 1;
            String coinName = coinId == 2 ? "DATX" : coinId == 3 ? "EOS" : "OCN";
            userCoinBalance.setCoinId(coinId);
            userCoinBalance.setCoinName(coinName);
            int balance = random.nextInt(1000);
            userCoinBalance.setShowBalance(new BigDecimal(balance));
            userCoinBalance.setCoinBalance(new BigDecimal(balance));
            int insert = userCoinBalanceMapper.insert(userCoinBalance);
            System.out.println("insert" + insert);
        }
    }

    @Autowired
    WalletGroupMapper walletGroupMapper;

    @Test
    public void testSelectPage() {
        WalletGroup walletGroup = new WalletGroup();
        walletGroup.setId(25);
        walletGroup.setGroupName("zhu_zi");
        walletGroup.setGroupType(1);
        int i = walletGroupMapper.updateByPrimaryKeySelective(walletGroup);
        System.out.println("sql_status"+i);
//        CoinBalanceReq coinBalanceReq = new CoinBalanceReq();
//        coinBalanceReq.setMerchantId(1);
//        ResponseValue responseValue = thirdPaltformService.selectByPage(coinBalanceReq);
//        Page data = (Page) responseValue.getData();
//        List<UserCoinBalance> coinBalances = (List<UserCoinBalance>) data.getList();
//        if (coinBalances != null) {
//            for (UserCoinBalance userCoinBalance : coinBalances) {
//                System.out.println("merchant:" + userCoinBalance.getMerchantId() + "_" + "coinName:" + userCoinBalance.getCoinName());
//            }
//        }
//        System.out.println(responseValue.getData().toString());
    }


    @Test
    public void testGetCoinInfo() {
        ResponseValue coinInfo = thirdPaltformService.getCoinInfo();
        List<CoinInfo> coinInfos = (List<CoinInfo>) coinInfo.getData();
        for (CoinInfo coininfo : coinInfos) {
            System.out.println("id:" + coininfo.getId() + "_" + "name:" + coininfo.getCoinName());
        }
    }

}
