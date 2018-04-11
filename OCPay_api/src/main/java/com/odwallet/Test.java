package com.odwallet;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhuan on 2018/3/28.
 */
public class Test {

    public static void main(String[] args) {
//        JSONObject callBackJSONObject = new JSONObject();
//        callBackJSONObject.put("userid",100);
//        callBackJSONObject.put("coinNum",new BigDecimal("3.5"));
//        callBackJSONObject.put("type",3);
//
//        String seed = AESCBC.getRandomStringByLength(16);
//        String in = AESCBCUtil.encrypt(callBackJSONObject, "oBike", "HtKtU2tf3pegtvP8ZhnDlj8kg7Tbti", "2ue-/,jgtw6uSw&e", seed);
//        Map map = new HashMap();
//        map.put("in", in);
//        map.put("seed", seed);
//        System.out.println(in);
//        System.out.println(seed);


//        String decrypt = null;
//        try {
//            decrypt = AESCBCUtil.decrypt("w4qY%2BUwXbinLc%2FJslaIbCe8uERB6YLmk649yCFqSci2RWXJLmSjof%2F0G9njaV6sSc6%2BOIAUz9pRAQhdnQQlXT6H07ilCSROL%2FMqGVs1httX3LNsoRyIHj6CBayRR9v%2Ftl8mTeaqHAlCCaI9P0LU64bjvC4CmMoS8t0PL6LbK5h0O6xmlmIsjtliUehEUycwR8%2BCwLO5HYBejIwt1Cno%2FAQS3WXUv3hCVLy5IYt%2BwA4ulR3FF4Yr%2FBBlLU6nH1eG8wZh031AFkdCsxmU0%2BqXpznCP8tBCLi4mohPn45BUhDTXZNVBWsP7%2BTLOtFZYzOTUqA1p66ctKIZtjnBq%2Fevi6qP54dPm1MtdKk%2BHLXFbYfSBjN%2BoT8MJv2KM%2FoKln8DwGCroQOcngCBZvch6RfXEyZ5JUKz65WQQ0JR9zc9vObfWh0t0x4OvfoJU%2Fe1kOa1XmFpsidzV8LufpYmmhXbRN9B7PwzmTIyJ2GMqWqv3ex65Lo77GnJuv9p4B6oGxz%2Bg4EA2vk9Pg3TfkUnL2%2FIrfd1Adf%2Fe5hSYs1%2BxqCdqZ4M%3D", "avazu","jJ89tLnG4qBNQ4QgrLMzhG6SFwqM3#", "9M8gFU94ZzRX%ac7", "hplNb1nZ7QGBPFhN");
//        } catch (Exception e) {
//        }
//        if (decrypt == null) {
//        }
//        JSONObject jsonObject = JSON.parseObject(decrypt);
//        Integer coinId = (Integer) jsonObject.get("coinId");
//        BigInteger orderId = new BigInteger(jsonObject.get("orderId").toString());
//        BigDecimal coinNum = new BigDecimal(jsonObject.get("coinNum").toString());
//        BigDecimal fee = new BigDecimal(jsonObject.get("fee").toString());
//        String fromAddress = (String) jsonObject.get("fromAddress");
//        String toAddress = (String) jsonObject.get("toAddress");
//        String privateKey = (String) jsonObject.get("privateKey");
//        if (coinId == null || orderId == null || coinNum == null
//                || fee == null || StringUtils.isBlank(fromAddress)
//                || StringUtils.isBlank(toAddress) || StringUtils.isBlank(toAddress)) {
//            System.out.println("参数错误");
//        }
//
//        String result = null;
//        BigInteger gasPrice = null;
//        try {
//            Web3j web3j = Web3JClient.getClient();
//            gasPrice = web3j.ethGasPrice().send().getGasPrice();
//            Credentials credentials = Credentials.create(privateKey);
//            if (!fromAddress.equals(credentials.getAddress())) {
//                System.out.println( Constants.USER_PRIVATEKEY_ERROR);
//            }
//            BigDecimal gasDecimal = CommonUtils.bit18(gasPrice);
//            BigInteger gasLimit = fee.divide(gasDecimal).toBigInteger();
//            String txHash;
//            OWalletTransaction oWalletTransaction = new OWalletTransaction();
//            if (StringUtils.isNotBlank("0xabbbb6447b68ffd6141da77c18c7b5876ed6c5ab")) {
//                BigDecimal userCoinNum = TransactionOnNode.balanceOfContractToken(web3j, "0xabbbb6447b68ffd6141da77c18c7b5876ed6c5ab", credentials.getAddress());
//                if (validateTransferNum(coinNum, userCoinNum)) System.out.println(Constants.USER_LACK_OF_BALANCE);
//
//                txHash = oWalletTransaction.doubleTransactionCoin(web3j, toAddress, credentials.getEcKeyPair(), coinNum.toString(), gasPrice.toString(), gasLimit.toString(), "0xabbbb6447b68ffd6141da77c18c7b5876ed6c5ab", "");
//                System.out.println(txHash);
//            } else {
//                BigDecimal userCoinNum = TransactionOnNode.balanceOfETH(web3j, credentials.getAddress());
//                if (validateTransferNum(coinNum, userCoinNum)) System.out.println( Constants.USER_LACK_OF_BALANCE);
//                txHash = oWalletTransaction.doubleTransactionETH(web3j, toAddress, credentials.getEcKeyPair(), coinNum.toString(), gasPrice.toString(), gasLimit.toString(), "");
//                System.out.println(txHash);
//
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        if (Constants.USER_PRIVATEKEY_ERROR.equals(result)){
//            System.out.println("privateKey is error");
//        }else if (Constants.USER_LACK_OF_BALANCE.equals(result)){
//            System.out.println("lack of balance");
//        }else if (Constants.TRANSFER_ERROR.equals(result)){
//            System.out.println("transfer is error");
//        }else {
//
//        }
//
//        Map map = new HashMap();
//        map.put("txHash", result);
//        map.put("status", Constants.ORDER_STATUS_PEEDING);
//        map.put("orderId", orderId);
//        System.out.println(map);
//    }
//
//    /**
//     * @param coinNum
//     * @param userCoinNum
//     * @return
//     */
//    private static boolean validateTransferNum(BigDecimal coinNum, BigDecimal userCoinNum) {
//        if (userCoinNum.compareTo(coinNum) < 0) {
//            return true;
//        }
//        return false;
//    }
//
//        //回调通知充值成功
//        JSONObject callBackJSONObject = new JSONObject();
//        callBackJSONObject.put("from", "0x33c6ff30b9f95fee1e19b03798a40a96bd6f5324");
//        callBackJSONObject.put("to", "0xe241b53ab309fb863843cc229a3ad1d3964fb387");
//        callBackJSONObject.put("orderId",new BigInteger("2018033012345678"));
//        callBackJSONObject.put("coinNum", new BigDecimal("0.100000000000000000"));
//        callBackJSONObject.put("fee", new BigDecimal("0.000076380000000000"));
//        callBackJSONObject.put("tradingTime", new Date());
//        callBackJSONObject.put("txHash","0x91a6fa04dceadf93f547ee86f957937bcd64de96588fb04e8018076a702ecea2");
//
//        String seed = AESCBC.getRandomStringByLength(16);
//        String in = AESCBCUtil.encrypt(callBackJSONObject, "avazu", "jJ89tLnG4qBNQ4QgrLMzhG6SFwqM3#", "9M8gFU94ZzRX%ac7", seed);
//        Map map = new HashMap();
//        map.put("in", in);
//        map.put("seed", seed);
//        System.out.println(in);
//        System.out.println(seed);

    }

}
