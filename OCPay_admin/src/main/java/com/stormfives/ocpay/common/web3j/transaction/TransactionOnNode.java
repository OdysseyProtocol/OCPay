package com.stormfives.ocpay.common.web3j.transaction;

import com.alibaba.fastjson.JSON;
import com.stormfives.ocpay.common.web3j.api.Web3JClient;
import com.stormfives.ocpay.common.web3j.bean.TransactionVerificationInfo;
import com.stormfives.ocpay.common.web3j.utils.CommonUtils;
import com.stormfives.ocpay.common.web3j.utils.OWalletUtils;
import com.stormfives.ocpay.common.web3j.utils.RawTransactionUtils;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.tx.TransactionManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by y on 2018/3/7.
 */
@Component
public class TransactionOnNode {

    static Logger logger = org.slf4j.LoggerFactory.getLogger(TransactionOnNode.class);

    @Autowired
    RabbitTemplate rabbitTemplate;
//
//
//    @Autowired
//    private WalletAddressNonceMapper walletAddressNonceMapper;


    /**
     * @param web3j
     * @param contractAddress
     * @param walletAddress
     * @return
     * @throws IOException
     */
    public static BigDecimal balanceOfContractToken(Web3j web3j, String contractAddress, String walletAddress)  {
        Function function = new Function("balanceOf",
                Arrays.<Type>asList(new Address(walletAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        TransactionManager transactionManager = new TransactionManager(web3j, walletAddress) {
            @Override
            public EthSendTransaction sendTransaction(BigInteger gasPrice, BigInteger gasLimit, String to, String data, BigInteger value) throws IOException {
                return null;
            }
        };
        String encodedFunction = FunctionEncoder.encode(function);
        try {
            EthCall ethCall = web3j.ethCall(
                    org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
                            transactionManager.getFromAddress(), contractAddress, encodedFunction),
                    DefaultBlockParameterName.LATEST)
                    .send();
            return (ethCall == null) ? null : new BigDecimal(CommonUtils.Hex2Decimal(ethCall.getValue()));
        }catch (Exception e){
            logger.warn(e.getMessage());
        }

        return  null;

    }


    /**
     * @param web3j
     * @param walletAddress
     * @return
     * @throws IOException
     */
    public static BigDecimal balanceOfETH(Web3j web3j, String walletAddress)  {
        try {

            EthGetBalance ethGetBalance = web3j.ethGetBalance(walletAddress,
                    DefaultBlockParameterName.LATEST).send();
            return CommonUtils.bit18(ethGetBalance.getBalance());
        }catch (IOException e){
            e.printStackTrace();
        }
        return  null;



    }


    /**
     * @param ethAmount eg:"0.05";//(实际额为0.05 Eth)
     * @param toAddress eg:"0x7e8247c7d145debe8a8c2d2a2ab450992aa884c9";
     * @param gas_price eg:"30000000000";
     * @param gas_limit eg:"200000"
     * @param data      default ""
     * @param ecKeyPair
     * @return txHex
     * @throws IOException
     * @throws InterruptedException
     */
    public String transactionEth(Web3j web3j, ECKeyPair ecKeyPair, String ethAmount, String toAddress, String gas_price, String gas_limit, String data) throws IOException, InterruptedException {
        Credentials credentials = Credentials.create(ecKeyPair);
        String walletAddress = credentials.getAddress();
        EthGetTransactionCount nonce = web3j.ethGetTransactionCount(walletAddress, DefaultBlockParameterName.LATEST).send();

        BigInteger nonce1 = nonce.getTransactionCount();

//        nonce1 = this.getWalletAddressNonce(nonce1, walletAddress);

        RawTransaction tx = RawTransactionUtils.getTransaction(nonce1, null, ethAmount, gas_price, gas_limit, data, toAddress);


        byte[] signed = TransactionEncoder.signMessage(tx, credentials);
        String transactionData = "0x" + Hex.toHexString(signed);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(transactionData).send();

        if (ethSendTransaction.getTransactionHash() == null || "".equals(ethSendTransaction.getTransactionHash())) {
            logger.warn("txHash is null on NODE \n " +

                    "nonce: " + nonce.getTransactionCount() + "\n" +
                    "nonce1: " + nonce1 + "\n" +
                    "transactionData" + transactionData


            );
        }


        return ethSendTransaction.getTransactionHash();

    }

//    public BigInteger getWalletAddressNonce(BigInteger nonce, String walletAddress) {
//
//
//        WalletAddressNonceExample walletAddressNonceExample = new WalletAddressNonceExample();
//        walletAddressNonceExample.or().andWalletAddressEqualTo(walletAddress);
//        List<WalletAddressNonce> walletAddressNonces = walletAddressNonceMapper.selectByExample(walletAddressNonceExample);
//        if (walletAddressNonces != null && walletAddressNonces.size() > 0) {
//            WalletAddressNonce walletAddressNonce = walletAddressNonces.get(0);
//            if (nonce.equals(walletAddressNonce.getNonce())) {
//                nonce = nonce.add(new BigInteger("1"));
//            }
//            WalletAddressNonce updateWalletAddressNonce = new WalletAddressNonce();
//            updateWalletAddressNonce.setId(walletAddressNonce.getId());
//            updateWalletAddressNonce.setNonce(nonce.longValue());
//            updateWalletAddressNonce.setUpdatedAt(new Date());
//            walletAddressNonceMapper.updateByPrimaryKeySelective(updateWalletAddressNonce);
//        }else {
//            WalletAddressNonce walletAddressNonce = new WalletAddressNonce();
//            walletAddressNonce.setNonce(nonce.longValue());
//            walletAddressNonce.setWalletAddress(walletAddress);
//            walletAddressNonce.setCreatedAt(new Date());
//            walletAddressNonceMapper.insertSelective(walletAddressNonce);
//        }
//
//        return nonce;
//    }


    /**
     * @param ecKeyPair
     * @param OCNAmount
     * @param toAddress
     * @param gas_price
     * @param gas_limit
     * @param data
     * @param ERC20Address
     * @return
     * @throws IOException
     * @throws InterruptedException
     */

    public String transactionOnContract(Web3j web3j, ECKeyPair ecKeyPair, String OCNAmount, String toAddress, String gas_price, String gas_limit, String data, String ERC20Address) throws IOException, InterruptedException {
        Credentials credentials = Credentials.create(ecKeyPair);
        String walletAddress = credentials.getAddress();
        EthGetTransactionCount nonce = web3j.ethGetTransactionCount(walletAddress, DefaultBlockParameterName.LATEST).send();

//        BigInteger nonce1 = this.getWalletAddressNonce(nonce.getTransactionCount(), walletAddress);
        BigInteger nonce1 = null;
        RawTransaction tx = RawTransactionUtils.getTransaction(nonce1, ERC20Address, OCNAmount, gas_price, gas_limit, data, toAddress);
        byte[] signed = TransactionEncoder.signMessage(tx, credentials);
        String transactionData = "0x" + Hex.toHexString(signed);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(transactionData).send();
        if (ethSendTransaction == null ||ethSendTransaction.getTransactionHash()==null) {
            logger.warn("通过节点转账：Txhash为空，返回结果为："+ JSON.toJSONString(ethSendTransaction)+
                    "\n ,发送数据：gas_price"+gas_price+" gas_limit:"+gas_limit+" OCNAmount:"+OCNAmount+
           "\n fromAddress:"+ walletAddress+" toAddress:"+toAddress+" nonce="+nonce1);
        }

        return ethSendTransaction.getTransactionHash();
    }




    /**
     * get the transaction Receipt
     *
     * @param web3j
     * @param txHash
     * @return
     * @throws IOException
     */
    public static TransactionReceipt getTransactionReceipt(Web3j web3j, String txHash) throws IOException {
        EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(txHash).send();
        return receipt.getResult();
    }


    /**
     * @param web3j
     * @return
     * @throws IOException
     */
    public static BigInteger getGasPrice(Web3j web3j) {
        try {
            EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
            return (ethGasPrice == null) ? null : ethGasPrice.getGasPrice();
        } catch (IOException e) {
            logger.error(e.toString());
            return null;
        }

    }


    /**
     * @param web3j
     * @param fromAddress
     * @param nonce
     * @param gasPrice
     * @param gasLimit
     * @param addressTo
     * @param amount
     * @param data
     * @return
     * @throws IOException
     */
    public static BigInteger getAmountUsedGas(Web3j web3j, String fromAddress, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String addressTo, String amount, String data) throws IOException {
        EthEstimateGas gas = web3j.ethEstimateGas(new org.web3j.protocol.core.methods.request.Transaction(fromAddress, nonce, gasPrice, gasLimit, addressTo, new BigInteger(amount), data)).send();
        return (gas == null) ? null : gas.getAmountUsed();
    }


    /**
     * @param web3j
     * @return
     * @throws IOException
     */
    public static BigInteger getRecentBlockNumber(Web3j web3j) throws IOException {
        EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
        return (ethBlockNumber == null) ? null : ethBlockNumber.getBlockNumber();


    }


    /**
     * @param web3j
     * @param from
     * @param nonce
     * @param gasPrice
     * @return
     * @throws IOException
     */
    public static BigInteger getEthTransactionGasLimit(Web3j web3j, String from, BigInteger nonce, BigInteger gasPrice) throws IOException {

        EthEstimateGas ethEstimateGas = web3j.ethEstimateGas(
                org.web3j.protocol.core.methods.request.Transaction.createContractTransaction(
                        from, nonce,
                        gasPrice, "")).send();
        return (ethEstimateGas == null) ? null : ethEstimateGas.getAmountUsed();
    }


    /**
     * @param web3j
     * @param from
     * @param nonce
     * @param gasPrice
     * @return
     * @throws IOException
     */
    public static BigInteger getContractTransactionGasLimit(Web3j web3j, String from, BigInteger nonce, BigInteger gasPrice) throws IOException {
        EthEstimateGas gasContract = web3j.ethEstimateGas(
                org.web3j.protocol.core.methods.request.Transaction.createContractTransaction(
                        from, nonce,
                        gasPrice, "")).send();
        return (gasContract == null) ? null : gasContract.getAmountUsed();
    }


    public static EthBlock getBlockBuNumber(Web3j web3j, BigInteger blockNumber) throws IOException {
        EthBlock ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), true).send();
        return ethBlock;
    }

    /**
     * @param txHash
     * @return
     * @throws IOException
     */
    public static TransactionVerificationInfo verifyTransaction(String txHash) {
        try {
            Thread.sleep(100);
            Web3j web3j = Web3JClient.getClient();
            BigInteger recentBlockNumber = getRecentBlockNumber(web3j);
            logger.debug("txHash" + txHash);
            TransactionReceipt transactionReceipt = getTransactionReceipt(web3j, txHash);
            if (transactionReceipt == null || recentBlockNumber == null) return null;
            String blockNumberRaw = transactionReceipt.getBlockNumber().toString();
            String gasUsed = transactionReceipt.getGasUsed().toString();


            if (blockNumberRaw == null) return null;
            if (gasUsed == null) return null;

            BigInteger txBlockNumber = blockNumberRaw.startsWith("0x") ? CommonUtils.Hex2Decimal(blockNumberRaw) : new BigInteger(blockNumberRaw);
            BigDecimal gasUsed_B = gasUsed.startsWith("0x") ? new BigDecimal(CommonUtils.Hex2Decimal(gasUsed)) : new BigDecimal(gasUsed);


            boolean isConfirm12 = OWalletUtils.verify12Block(txBlockNumber, recentBlockNumber);
            boolean statusIsSuccess = "0x1".equals(transactionReceipt.getStatus());
            EthBlock block = getBlockBuNumber(web3j, txBlockNumber);
            Long timeStamp = new Long(block.getBlock().getTimestamp().toString());
            return new TransactionVerificationInfo(isConfirm12 && statusIsSuccess, timeStamp, gasUsed_B);
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }

}