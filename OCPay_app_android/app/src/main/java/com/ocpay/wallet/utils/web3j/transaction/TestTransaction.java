package com.ocpay.wallet.utils.web3j.transaction;

import com.ocpay.wallet.utils.web3j.api.Web3JClient;
import com.ocpay.wallet.utils.web3j.utils.OWalletUtils;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by y on 2018/3/13.
 */
public class TestTransaction {


    static String testPrivateContra = "0x44c93945Be58d30D89643ECdeC1e3C8005cd2413";
    static String path1 = "C:/Users/liuhuan/Desktop/keystore";
    static String path2 = "C:/Users/liuhuan/Desktop/keystore_2";
    static String path3 = "C:/Users/liuhuan/Desktop/keystore_3";

    static String Acount1 = "";
    static String Acount_2 = "0xe6F637D4d98CFD1E43c4C66F6CE5a7C6d5C8D364";
    static String testWalletAddr = "0xFa7C12466Bfcb1a702ca9Bc6715BC5964452466c";
    static String test_TxHash_main = "0xb4db3a8557ad4d69e4fa58f7d0967d52bec17850bbeaad39318951c689f10a24";

    public static void main(String[] arg) throws Exception{

        test();

    }


 

    public static void getFee() {
        BigDecimal gasPrice = new BigDecimal("18000000000");
        BigDecimal gasLimit = new BigDecimal("53000");

        BigDecimal transactionFee = OWalletUtils.getTransactionFee(gasPrice, gasLimit);
        System.out.println("transactionFee" + transactionFee);

    }

    public static void oneToAll() throws IOException, CipherException, InterruptedException {

        Web3j web3j = Web3JClient.getClient();    
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
        Credentials credentials = WalletUtils.loadCredentials("123456", path1);
        Credentials credentials2 = WalletUtils.loadCredentials("88888888", path2);
        Credentials credentials3 = WalletUtils.loadCredentials("88888888", path3);
        BigInteger nonce1 = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getTransactionCount();
        BigInteger nonce2 = web3j.ethGetTransactionCount(credentials2.getAddress(), DefaultBlockParameterName.LATEST).send().getTransactionCount();

//        //1 get gas of token transaction
//
//        System.out.println("1 get gas of token transaction");
//        BigInteger contractTransactionGasLimit = TransactionOnNode.getContractTransactionGasLimit(web3j, credentials.getAddress(), nonce1, gasPrice);
//
//        //2 get gas of gas wallet  to  user wallet
//        System.out.println("  2 get gas of gas wallet  to  user wallet ");
//
//        BigInteger ethTransactionGasLimit = TransactionOnNode.getEthTransactionGasLimit(web3j, credentials2.getAddress(), nonce2, gasPrice);
//
//        //3 gas wallet to user wallet
//        System.out.println(" 3 gas wallet to user wallet ");
//
//        BigDecimal needGas = CommonUtils.bit18(contractTransactionGasLimit.multiply(gasPrice));
//
//        System.out.println("needGas:"+needGas);

//        String txHash = TransactionOnNode.transactionEth(web3j, credentials2.getEcKeyPair(), needGas.toPlainString(), credentials3.getAddress(), gasPrice.toString(), ethTransactionGasLimit.toString(), "");
//        System.out.println("txHash:" + txHash);
//        TransactionVerificationInfo transactionVerificationInfo = TransactionOnNode.verifyTransaction(txHash);

        //4  confirm 3 transaction
        System.out.println(" 4  confirm 3 transaction");

//        while (!transactionVerificationInfo.isVerification()) {
//            Thread.sleep(10000);
//            transactionVerificationInfo = TransactionOnNode.verifyTransaction(txHash);
//        }

//        Thread.sleep(30000);

        //5 user wallet to  master wallet
        System.out.println(" 5 user wallet to  master wallet");


//        BigDecimal userWallet_OCN = TransactionOnNode.balanceOfContractToken(web3j, testPrivateContra, credentials3.getAddress());
//        BigDecimal userWallet_ETH = TransactionOnNode.balanceOfETH(web3j, credentials3.getAddress());
//        System.out.println(userWallet_OCN);
//        System.out.println(userWallet_ETH);
//
//        System.out.println("userWallet_OCN" + userWallet_OCN);
//        System.out.println("userWallet_ETH" + userWallet_ETH);
//        BigDecimal bigDecimal = CommonUtils.bit18(contractTransactionGasLimit.multiply(gasPrice));
//        BigDecimal bigDecimal1 = CommonUtils.bit18(new BigInteger(userWallet_OCN.toString()));


//        String tx_user = TransactionOnNode.transactionOnContract(web3j, credentials3.getEcKeyPair(), bigDecimal1.toString(), credentials2.getAddress(), gasPrice.toString(), contractTransactionGasLimit.toString(), "", testPrivateContra);
//        System.out.println("address_from:" + bigDecimal1.toString());
//        System.out.println("to:" + credentials2.getAddress());
//        System.out.println("gas_price" + gasPrice.toString());
//        System.out.println("t_ocn" + bigDecimal1.toString());
//        System.out.println("gas_limit" + contractTransactionGasLimit.toString());


        //6 confirm 5 transaction
        System.out.println(" 6 confirm 5 transaction");

//        TransactionVerificationInfo transactionVerificationInfo_user = TransactionOnNode.verifyTransaction(tx_user);

//        while (!transactionVerificationInfo_user.isVerification()) {
//            Thread.sleep(10000);
//            transactionVerificationInfo_user = TransactionOnNode.verifyTransaction(tx_user);
//            System.out.println("txHash" + transactionVerificationInfo_user.isVerification());

//        }
        Thread.sleep(30000);

//        BigDecimal userWallet_OCN_result = TransactionOnNode.balanceOfContractToken(web3j, testPrivateContra, credentials3.getAddress());
//        System.out.println("userWallet_OCN" + userWallet_OCN_result);
    }

    public static void test() throws IOException {
        Web3j web3j = Web3JClient.getClient();
        EthBlockNumber send1 = web3j.ethBlockNumber().send();
        System.out.println("tt"+send1.getBlockNumber());
//        EthGetTransactionReceipt send = web3j.ethGetTransactionReceipt("0x0d612fa2308c0e53d49887512f4f26d5f79ba4c5478094e473f6d008688d5381").send();
//        send.ge
    }

    public static void testBlockGasLimit() throws IOException, CipherException {
        Web3j web3j = Web3JClient.getClient();  // defaults to http://localhost:8545/
        Credentials credentials1 = WalletUtils.loadCredentials("123456", path1);

        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
        System.out.println(" ————-----------------------1-----------------------------");

        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials1.getAddress(), DefaultBlockParameterName.LATEST).send();
//        EthEstimateGas gas = web3j.ethEstimateGas(new org.web3j.protocol.core.methods.request.EthTransaction(credentials1.getAddress(), ethGetTransactionCount.getTransactionCount(), gasPrice, new BigInteger("210000"), testPrivateContra, new BigInteger("0"), "")).send();
//        System.out.println(" ————------------------2----------------------------------");


        EthEstimateGas ethEstimateGas = web3j.ethEstimateGas(
                org.web3j.protocol.core.methods.request.Transaction.createContractTransaction(
                        credentials1.getAddress(), ethGetTransactionCount.getTransactionCount(),
                        gasPrice, "")).send();
        System.out.println("fsd:" + ethEstimateGas.getAmountUsed());


        EthEstimateGas gasContract = web3j.ethEstimateGas(
                org.web3j.protocol.core.methods.request.Transaction.createContractTransaction(
                        credentials1.getAddress(), ethGetTransactionCount.getTransactionCount(),
                        gasPrice, "")).send();
        System.out.println("[]ttt————" + gasContract.getAmountUsed());
//        try {
//            String s = TransactionOnNode.transactionOnContract(web3j, credentials1.getEcKeyPair(), "1", Acount_2, gasPrice.toString(), gasContract.getAmountUsed().toString(), "", testPrivateContra);
//            System.out.println("[]ttt————[][]" + s);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }


    public void testEthEstimateGas(Web3j web3j) throws Exception {
        web3j.ethEstimateGas(
                org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
                        "0xa70e8dd61c5d32be8058bb8eb970870f07233155",
                        "0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f", "0x0")).send();

//        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_estimateGas\","
//                + "\"params\":[{\"from\":\"0xa70e8dd61c5d32be8058bb8eb970870f07233155\","
//                + "\"to\":\"0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f\",\"data\":\"0x0\"}],"
//                + "\"id\":1}");
    }


    public void testEthEstimateGasContractCreation(Web3j web3j) throws Exception {
        web3j.ethEstimateGas(
                org.web3j.protocol.core.methods.request.Transaction.createContractTransaction(
                        "0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f", BigInteger.ONE,
                        BigInteger.TEN, "")).send();

//        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_estimateGas\","
//                + "\"params\":[{\"from\":\"0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f\","
//                + "\"gasPrice\":\"0xa\",\"data\":\"0x\",\"nonce\":\"0x1\"}],\"id\":1}");
    }
}
