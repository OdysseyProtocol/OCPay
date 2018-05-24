package com.stormfives.ocpay.common.web3j.utils;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by y on 2018/3/7.
 */
public class CommonUtils {


    public static BigDecimal getSTAmount(String input) {
        if (input.startsWith("0x")) {
            input = input.replace("0x", "");
        }
        String hexAmount = input.substring(input.length() - 64, input.length());
        BigDecimal amount = new BigDecimal(new BigInteger(hexAmount, 16).toString());
        BigDecimal bigDecimal = amount.divide(new BigDecimal(1000000000000000000d), 18, BigDecimal.ROUND_UP);
        return bigDecimal;
    }

    public static String getContractAddressTo(String input) {
        if (input == null || "".equals(input) || input.length() != 138) return null;
        String addressTo = "";
        try {
            addressTo = "0x" + input.substring(34, input.length() - 64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addressTo;
    }


    public static BigDecimal bit18(BigInteger bigInteger) {
        BigDecimal amount = new BigDecimal(bigInteger);
        BigDecimal bigDecimal = amount.divide(new BigDecimal(1000000000000000000d), 18, BigDecimal.ROUND_UP);

        return bigDecimal;
    }

    /**
     * @param transactionReceipt
     * @return
     * @throws IOException
     */
    public static boolean verifyTransaction(TransactionReceipt transactionReceipt, BigInteger ethBlockNumber) throws IOException {
        String status = transactionReceipt.getStatus();
        boolean statusIsSuccess = "0x1".equals(status);
        boolean verifyTrain = ethBlockNumber.subtract(transactionReceipt.getBlockNumber()).compareTo(new BigInteger("12")) > 0;
        return statusIsSuccess && verifyTrain;
    }


    /**
     * @param x
     * @return
     */
    public static BigInteger Hex2Decimal(String x) {
        if (x.startsWith("0x")) {
            x = x.replace("0x", "");
        }
        return new BigInteger(x, 16);
    }


}

