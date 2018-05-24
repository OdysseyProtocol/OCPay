package com.stormfives.ocpay.common.web3j.utils;


import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.RawTransaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * Created by y on 2018/2/6.
 */

public class RawTransactionUtils {

    public static final BigDecimal ONE_ETHER = new BigDecimal("1000000000000000000");

    /**
     *
     *
     * @param nonce
     * @param gas_price
     * @param gas_limit
     * @param toAddress
     * @param amount
     * @param data
     * @return
     */

    public static RawTransaction getRawTransactionForEth(BigInteger nonce, String gas_price, String gas_limit, String toAddress, String amount, String data) {
        return RawTransaction.createTransaction(
                nonce,
                new BigInteger(gas_price),
                new BigInteger(gas_limit),
                toAddress,
                new BigDecimal(amount).multiply(ONE_ETHER).toBigInteger(),
                data
        );
    }


    /**
     *
     *
     * @param nonce
     * @param contractAddress
     * @param amount
     * @param gas_price
     * @param gas_limit
     * @param data1
     * @param toAddress
     * @return
     * @throws InterruptedException
     * @throws IOException
     */

    public static RawTransaction getRawTransactionForToken(BigInteger nonce, String contractAddress, String amount, String gas_price, String gas_limit, String data1, String toAddress) throws InterruptedException, IOException {
        String h = "1000000000000000000";//  18 bit
        BigDecimal bigDecimal = new BigDecimal(h);
        String resultAmount = BigDecimal.valueOf(Double.valueOf(amount)).multiply(bigDecimal).setScale(0).toString();
        Function function = new Function(
                "transfer",
                Arrays.<Type>asList(new Address(toAddress),
                        new Uint256(new BigInteger(resultAmount))),
                Collections.<TypeReference<?>>emptyList());
        String data = FunctionEncoder.encode(function);

        return RawTransaction.createTransaction(
                nonce,
                new BigInteger(gas_price),
                new BigInteger(gas_limit),
                contractAddress,
                BigInteger.ZERO,
                data
        );
    }

    /**
     * @param
     * @param nonce
     * @param contractAddress
     * @param amount
     * @param gas_price
     * @param gas_limit
     * @param data1
     * @param toAddress
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public static RawTransaction getTransaction(BigInteger nonce, String contractAddress, String amount, String gas_price, String gas_limit, String data1, String toAddress) throws InterruptedException, IOException {
        if (contractAddress == null) {
            return getRawTransactionForEth(nonce, gas_price, gas_limit, toAddress, amount, data1);
        } else {
            return getRawTransactionForToken(nonce, contractAddress, amount, gas_price, gas_limit, data1, toAddress);

        }
    }


}
