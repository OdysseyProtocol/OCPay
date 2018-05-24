package com.stormfives.ocpay.common.web3j.api;



import com.stormfives.ocpay.common.web3j.bean.WalletInfo;
import com.stormfives.ocpay.common.web3j.response.TransactionsResponse;
import com.stormfives.ocpay.common.web3j.transaction.OWalletTransaction;
import com.stormfives.ocpay.common.web3j.utils.OWalletUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

/**
 * Created by y on 2018/3/5.
 */
public class OWalletAPI {


    public static WalletInfo generateWallet() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        return OWalletUtils.generateWallet();
    }


    /**
     * @param walletAddress
     * @return
     */
    public static String balanceOfETH(String walletAddress) {
        return OWalletTransaction.balanceOfETH(walletAddress);
    }


    /**
     * @param walletAddress
     * @param contractAddress
     */
    public static String balanceOfContractToken(String walletAddress, String contractAddress) throws RuntimeException {

        return OWalletTransaction.balanceOfContractToken(walletAddress, contractAddress);

    }


    /**
     * @param ethAmount eg:"0.05";//( 0.05 Eth)
     * @param toAddress eg:"0x7e8247c7d145debe8a8c2d2a2ab450992aa884c9";
     * @param gas_price eg:"30000000000";
     * @param gas_limit eg:"200000"
     * @param data      default ""
     * @param ecKeyPair
     * @return txHex
     * @throws IOException
     * @throws InterruptedException
     */
//    public static String transactionEth(ECKeyPair ecKeyPair, String ethAmount, String toAddress, String gas_price, String gas_limit, String data) throws IOException, InterruptedException {
//        return OWalletTransaction.transactionEth(ecKeyPair, ethAmount, toAddress, gas_price, gas_limit, data);
//    }


    /**
     * @param ecKeyPair
     * @param tokenAmount
     * @param toAddress
     * @param gas_price
     * @param gas_limit
     * @param data
     * @param ERC20Address
     * @return
     * @throws IOException
     * @throws InterruptedException
     */

//    public static String transactionOnContract(ECKeyPair ecKeyPair, String tokenAmount, String toAddress, String gas_price, String gas_limit, String data, String ERC20Address) throws IOException, InterruptedException {
////        return OWalletTransaction.transactionOnContract(ecKeyPair, tokenAmount, toAddress, gas_price, gas_limit, data, ERC20Address);
//    }


    /**
     * @param address
     * @param startBlockNumber
     * @param endBlockNumber
     * @return
     */
    public static List<TransactionsResponse.CustomTransaction> getTransactionList(String address, String startBlockNumber, String endBlockNumber) throws Exception {
        return OWalletTransaction.getTransactionList(address, startBlockNumber, endBlockNumber);
    }


    /**
     *
     * @return
     * @throws IOException
     */
    public static BigInteger getRecentBlockNumber() throws IOException {
        return OWalletTransaction.getRecentBlockNumber();
    }


}