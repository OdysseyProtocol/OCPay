package com.ocpay.wallet.utils.web3j.transaction;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.util.Arrays;

/**
 * Created by y on 2018/3/5.
 */

public class OWalletTransaction {


//
////    @Autowired
//    private WalletGroupDetailMapper walletGroupDetailMapper;
////    @Autowired
//    private TransactionOnNode transactionOnNode;
//
//    /**
//     * @param walletAddress
//     * @return
//     */
//    public static String balanceOfETH(String walletAddress) {
//        String responseResult = RequestUtils.sendGet(EtherScanApi.getBalanceUrl(walletAddress));
//        responseResult = responseResult.replace("/n", "");
//        EtherScanJsonrpcResponse responseToken = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<EtherScanJsonrpcResponse>() {
//        });
//        String balanceOfETH = new BigDecimal(responseToken.result).divide(new BigDecimal(1000000000000000000d), 7, BigDecimal.ROUND_UP).toPlainString();
//        return balanceOfETH;
//
//    }
//
//
//    /**
//     * @param walletAddress
//     * @param contractAddress
//     */
//    public static String balanceOfContractToken(String walletAddress, String contractAddress) {
//        Function function = new Function("balanceOf",
//                Arrays.<Type>asList(new Address(walletAddress)),
//                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
//                }));
//        String data = FunctionEncoder.encode(function);
//        String url = eth_call(contractAddress, data);
//        String responseResult = RequestUtils.sendGet(url);
//        responseResult = responseResult.replace("/n", "");
//
//        EtherScanJsonrpcResponse responseToken = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<EtherScanJsonrpcResponse>() {
//        });
//        String result = responseToken.result;
//        if (!"".equals(result) && result.startsWith("0x")) {
//            List<TypeReference<?>> typeReferences = Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
//            });
//            List<Type> decode = FunctionReturnDecoder.decode(result, convert(typeReferences));
//            BigDecimal ethbal = new BigDecimal(decode.get(0).getValue().toString());
//            BigDecimal divide = ethbal.divide(new BigDecimal(1000000000000000000d), 3, BigDecimal.ROUND_UP);
//            return divide.toString();
//        }
//        throw new RuntimeException("get token fail");
//    }




    /**
     * balanceOf
     *
     * @param address
     * @return
     */
    public static String getBalanceOfTokenData(String address) {
        Function function = new Function("balanceOf",
                Arrays.<Type>asList(new Address(address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return FunctionEncoder.encode(function);

    }

//
//
//    /**
//     * @param ethAmount eg:"0.05";//(实际额为0.05 Eth)
//     * @param toAddress eg:"0x7e8247c7d145debe8a8c2d2a2ab450992aa884c9";
//     * @param gas_price eg:"30000000000";
//     * @param gas_limit eg:"200000"
//     * @param data      default ""
//     * @param ecKeyPair
//     * @return txHex
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public String transactionEth(ECKeyPair ecKeyPair, String ethAmount, String toAddress, String gas_price, String gas_limit, String data) throws IOException, InterruptedException {
//        Credentials credentials = Credentials.create(ecKeyPair);
//        String walletAddress = credentials.getAddress();
//        String responseResult = RequestUtils.sendGet(getNonceForAddress(walletAddress));
//        responseResult = responseResult.replace("/n", "");
//        EtherScanJsonrpcResponse responseToken = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<EtherScanJsonrpcResponse>() {
//        });
//        BigInteger nonce = new BigInteger(responseToken.result.substring(2), 16);
//        nonce = this.transactionOnNode.getWalletAddressNonce(nonce, walletAddress);
//        RawTransaction tx = RawTransactionUtils.getTransaction(nonce, null, ethAmount, gas_price, gas_limit, data, toAddress);
//        byte[] signed = TransactionEncoder.signMessage(tx, (byte) EtherScanApi.CHAIN_ID, credentials);
//        String url = forwardTransaction("0x" + Hex.toHexString(signed));
//        //进行交易需要先获得nonce,该账号的交易次数
//        String transactionResp = RequestUtils.sendGet(url);
//        transactionResp = transactionResp.replace("/n", "");
//        EtherScanJsonrpcResponse txHashResponse = JSON.parseObject(transactionResp, new com.alibaba.fastjson.TypeReference<EtherScanJsonrpcResponse>() {
//        });
//        if (txHashResponse.result == null || "".equals(txHashResponse.result)) {
//            MyLog.w("txHash is null on EtherScan \n " +
//                    "getnonce url: " + getNonceForAddress(walletAddress) + "\n" +
//                    "getnonce responseResult: " + responseResult + "\n" +
//                    "nonce: " + nonce + "\n" +
//                    "transaction url:" + url + "\n" +
//                    "transaction resp: " + transactionResp
//
//            );
//        }
//
//        return txHashResponse.result;
//
//    }
//
//    /**
//     * @param ecKeyPair
//     * @param OCNAmount
//     * @param toAddress
//     * @param gas_price
//     * @param gas_limit
//     * @param data
//     * @param ERC20Address
//     * @return
//     * @throws IOException
//     * @throws InterruptedException
//     */
//
//    public String transactionOnContract(ECKeyPair ecKeyPair, String OCNAmount, String toAddress, String gas_price, String gas_limit, String data, String ERC20Address) throws IOException, InterruptedException {
//        Credentials credentials = Credentials.create(ecKeyPair);
//        String walletAddress = credentials.getAddress();
//        String responseResult = RequestUtils.sendGet(getNonceForAddress(walletAddress));
//        responseResult = responseResult.replace("/n", "");
//        EtherScanJsonrpcResponse responseToken = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<EtherScanJsonrpcResponse>() {
//        });
//        BigInteger nonce = new BigInteger(responseToken.result.substring(2), 16);
//
//        nonce = this.transactionOnNode.getWalletAddressNonce(nonce, walletAddress);
//
//        RawTransaction tx = RawTransactionUtils.getTransaction(nonce, ERC20Address, OCNAmount, gas_price, gas_limit, data, toAddress);
//        byte[] signed = TransactionEncoder.signMessage(tx, (byte) EtherScanApi.CHAIN_ID, credentials);
//        String url = forwardTransaction("0x" + Hex.toHexString(signed));
//        String transactionResp = RequestUtils.sendGet(url);
//        transactionResp = transactionResp.replace("/n", "");
//        EtherScanJsonrpcResponse txHashResponse = JSON.parseObject(transactionResp, new com.alibaba.fastjson.TypeReference<EtherScanJsonrpcResponse>() {
//        });
//        if (txHashResponse.result == null || "".equals(txHashResponse.result)) {
//             MyLog.w("通过ETHSCAN转账失败， \n " +
//                    "getnonce url: " + getNonceForAddress(walletAddress) + "\n" +
//                    "getnonce responseResult: " + responseResult + "\n" +
//                    "nonce: " + nonce + "\n" +
//                    "transaction url:" + url + "\n" +
//                    "transaction resp: " + transactionResp
//
//            );
//        }
//
//        return txHashResponse.result;
//
//    }
//
//
//    public static final String txHash = "0x1a763acca69a048d413311bdf2ce795b01415c5220892dcb5765a47fce559143";
//
//
//    /**
//     * @param txHash
//     * @return
//     */
//    public static TransactionReceiptResponse transactionReceipt(String txHash) {
//        String url = getTransactionReceipt(txHash);
//        String responseResult = RequestUtils.sendGet(url);
//        responseResult = responseResult.replace("/n", "");
//        TransactionReceiptResponse transactionReceiptResponse = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<TransactionReceiptResponse>() {
//
//        });
//        return transactionReceiptResponse;
//
//    }
//
//
//    /**
//     * @param address
//     * @param startBlockNumber
//     * @param endBlockNumber   eg:99999999
//     * @return
//     */
//    public static List<TransactionsResponse.CustomTransaction> getEthTransactionList(String address, String startBlockNumber, String endBlockNumber) throws Exception {
//        if (address == null || "".equals(address)) {
//            return null;
//        }
//        String url = transactions_by_address(address, startBlockNumber, endBlockNumber);
//        String responseResult = RequestUtils.sendGet(url);
//        responseResult = responseResult.replace("/n", "");
//        TransactionsResponse transactionResponse = null;
//        try {
//            transactionResponse = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<TransactionsResponse>() {
//            });
//        } catch (Exception e) {
//
//            logger.error(String.format("扫快失败,url:{%s},responseResult:{%s}", url, responseResult), e);
//            throw new Exception("扫快失败");
//        }
//        return (transactionResponse == null) ? null : transactionResponse.getResult();
//
//    }
//
//
//    /**
//     * @return
//     */
//    public static BigInteger getRecentBlockNumber() {
//        String scanUrl = getEthRecentBlockNumber();
//        String responseResult = RequestUtils.sendGet(scanUrl);
//        logger.info("getEthRecentBlockNumber URL="+ scanUrl);
//        responseResult = responseResult.replace("/n", "");
//        EtherScanJsonrpcResponse responseToken = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<EtherScanJsonrpcResponse>() {
//        });
//        if (responseToken == null) {
//            return null;
//        }
//
//        return new BigInteger(responseToken.result.replace("0x", ""), 16);
//    }
//
//    /**
//     * 双重转代币
//     *
//     * @param web3j
//     * @param address
//     * @param ecKeyPair
//     * @param ocnAmount
//     * @param gas_price
//     * @param gas_limit
//     * @param contractAddress
//     * @param data
//     * @return
//     */
//    public String doubleTransactionCoin(Web3j web3j, String address, ECKeyPair ecKeyPair, String ocnAmount, String gas_price, String gas_limit, String contractAddress, String data) {
//        try {
//            String txHash = transactionOnNode.transactionOnContract(web3j, ecKeyPair, ocnAmount, address, gas_price, gas_limit, data, contractAddress);
//            if (StringUtils.isBlank(txHash)) {
//                logger.error("通过节点转账Txhash为空，用ETHSCAN转账");
//                txHash = transactionOnContract(ecKeyPair, ocnAmount, address, gas_price, gas_limit, data, contractAddress);
//            }
//            return txHash;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), "ETHSCAN转账失败：" + e);
//            try {
//                return transactionOnContract(ecKeyPair, ocnAmount, address, gas_price, gas_limit, data, contractAddress);
//            } catch (Exception e1) {
//                logger.error(e.getMessage(), "节点合约转账失败" + e);
//                return Constants.TRANSFER_ERROR;
//            }
//        }
//
//    }
//
//    /**
//     * 双重转eth
//     *
//     * @param web3j
//     * @param address
//     * @param ecKeyPair
//     * @param ETHAmount
//     * @param gas_price
//     * @param gas_limit
//     * @param data
//     * @return
//     */
//    public String doubleTransactionETH(Web3j web3j, String address, ECKeyPair ecKeyPair, String ETHAmount, String gas_price, String gas_limit, String data) {
//        try {
//            String txHash = transactionOnNode.transactionEth(web3j, ecKeyPair, ETHAmount, address, gas_price, gas_limit, data);
//            if (StringUtils.isBlank(txHash)) {
//                txHash = transactionEth(ecKeyPair, ETHAmount, address, gas_price, gas_limit, data);
//            }
//            return txHash;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), "节点转ETH失败：" + e);
//            try {
//                return transactionEth(ecKeyPair, ETHAmount, address, gas_price, gas_limit, data);
//            } catch (Exception e1) {
//                logger.error(e.getMessage(), "ETHSCAN转ETH失败：" + e);
//                return Constants.TRANSFER_ERROR;
//            }
//        }
//
//    }
//
//
//    /**
//     * @param blockNo
//     * @return
//     */
//    public static BlockInfoResponse getBlockInfo(String blockNo) {
//        String responseResult = RequestUtils.sendGet(getEthRecentBlockInfo(blockNo));
//        responseResult = responseResult.replace("/n", "");
//
//        BlockInfoResponse responseToken = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<BlockInfoResponse>() {
//        });
//        return responseToken;
//    }
//
//
//    /**
//     * s双重验证
//     *
//     * @param txHash
//     * @return
//     */
//    public static TransactionVerificationInfo doubleVerifyTransaction(String txHash) {
//        TransactionVerificationInfo verificationInfo = verifyTransaction(txHash);
//        if (verificationInfo == null) {
//            return TransactionOnNode.verifyTransaction(txHash);
//        }
//        return verificationInfo;
//
//    }
//
//
//    /**
//     * @param txHash
//     * @return
//     * @throws IOException
//     */
//    public static TransactionVerificationInfo verifyTransaction(String txHash) {
//        try {
//            Thread.sleep(100);
//            BigInteger recentBlockNumber = OWalletAPI.getRecentBlockNumber();
//            TransactionReceiptResponse transactionReceiptResponse = OWalletTransaction.transactionReceipt(txHash);
//            if (recentBlockNumber == null || transactionReceiptResponse == null || transactionReceiptResponse.getResult() == null || transactionReceiptResponse.getResult().getBlockNumber() == null) {
//                logger.info("根据txhash获取交易为空：txhash=", txHash);
//                return null;
//            }
//
//            String blockNumberRaw = transactionReceiptResponse.getResult().getBlockNumber();
//            String gasUsed = transactionReceiptResponse.getResult().getGasUsed();
//            BigDecimal gasUsed_B = new BigDecimal(Hex2Decimal(gasUsed).toString());
//            if (blockNumberRaw == null) return null;
//            BigInteger txBlockNumber = blockNumberRaw.startsWith("0x") ? Hex2Decimal(blockNumberRaw) : new BigInteger(blockNumberRaw);
//            boolean isConfirm12 = OWalletUtils.verify12Block(txBlockNumber, recentBlockNumber);
//            boolean statusIsSuccess = "0x1".equals(transactionReceiptResponse.getResult().getStatus());
//            BlockInfoResponse blockInfo = OWalletTransaction.getBlockInfo(txBlockNumber.toString());
//            Long timeStamp = new Long(blockInfo.getResult().getTimeStamp());
//            return new TransactionVerificationInfo(isConfirm12 && statusIsSuccess, timeStamp, gasUsed_B);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), "verifyTransaction fail:" + e);
//            return null;
//        }
//    }
//
//
//    /**
//     * 双重扫快
//     */
//    public  static  void doubleScanBlock(){
//
//
//    }
//
//
//
//
//    /**
//     * 根据区块获得transactions
//     *
//     * @param blockNo
//     * @return
//     */
//    public static BlockTransactionsResponse.Block getBlockByNumber(String blockNo) throws Exception {
//        String blockByNumberUrl = getBlockByNumberUrl(blockNo);
//        String responseResult = RequestUtils.sendGet(blockByNumberUrl);
//        responseResult = responseResult.replace("/n", "");
//        if (StringUtil.isEmpty(responseResult)) {
//            return null;
//        }
//        try {
//            BlockTransactionsResponse ethBlock = JSON.parseObject(responseResult, new com.alibaba.fastjson.TypeReference<BlockTransactionsResponse>() {
//            });
//            return ethBlock.getResult();
//        } catch (Exception e) {
//            throw e;
//        }
//
//
//    }

}