package com.ocpay.wallet.utils.eth;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.utils.CommonUtils;
import com.ocpay.wallet.utils.eth.bean.OCPWalletFile;
import com.ocpay.wallet.utils.eth.util.CustomMnemonicUtils;
import com.ocpay.wallet.utils.eth.util.bip44.HdKeyNode;
import com.ocpay.wallet.utils.eth.util.bip44.hdpath.HdKeyPath;
import com.ocpay.wallet.utils.wallet.WalletStorage;
import com.ocpay.wallet.utils.web3j.api.EtherScanApi;
import com.ocpay.wallet.utils.web3j.utils.RawTransactionUtils;
import com.snow.commonlibrary.utils.RegularExpressionUtils;

import org.spongycastle.util.encoders.Hex;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;


/**
 */
public class OCPWalletUtils {


    private Credentials credentials;

    private String walletPass;

    private WalletFile walletFile;

    private String mnemonic;

    private String passphrase;

    private String path;


    private OCPWalletUtils() {

    }


    public OCPWalletUtils(String privateKey) {
        Credentials credentials = Credentials.create(privateKey);
    }


    public OCPWalletUtils(String passPhrase, byte[] walletContent) throws CipherException {
        walletFile = createWalletFileByKeystore(walletContent);
        credentials = Credentials.create(Wallet.decrypt(passPhrase, walletFile));
        walletPass = passPhrase;
    }


    public OCPWalletUtils(String password, String walletContent) throws CipherException {
        walletFile = createWalletFileByKeystore(walletContent);
        credentials = Credentials.create(Wallet.decrypt(password, walletFile));
        walletPass = password;
    }


    public OCPWalletUtils(String passPhrase, File file) throws CipherException {
        walletFile = createWalletFileByKeystore(file);
        credentials = Credentials.create(Wallet.decrypt(passPhrase, walletFile));
        walletPass = passPhrase;
    }


    public static OCPWalletUtils create(String password) throws Exception {
        OCPWalletUtils wa = new OCPWalletUtils();
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        wa.walletFile = Wallet.createStandard(password, ecKeyPair);
        wa.credentials = Credentials.create(Wallet.decrypt(password, wa.walletFile));
        wa.walletPass = password;
        return wa;
    }


    public static OCPWalletFile createWithMnemonic(String password, String path, Context context, boolean isImTokenCriterion) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        byte[] initialEntropy = new byte[16];
        SecureRandom secureRandom = SecureRandomUtils.secureRandom();
        secureRandom.nextBytes(initialEntropy);
        String mnemonic = CustomMnemonicUtils.generateMnemonic(initialEntropy, context);
        String seedPassword = isImTokenCriterion ? "" : password;
        byte[] seed = CustomMnemonicUtils.generateSeed(mnemonic, seedPassword);
        ECKeyPair ecKeyPair = createBip44NodeFromSeed(seed, path);

        OCPWalletFile ocpWalletFile = new OCPWalletFile();
        if (password != null) {
            ocpWalletFile.setWalletFile(Wallet.createStandard(password, ecKeyPair));
        }

        ocpWalletFile.setPath(path);

        ocpWalletFile.setMnemonic(mnemonic);

        return ocpWalletFile;
    }


    public static OCPWalletFile modifyWalletPwd(String oldPwd, String newPwd, OCPWalletFile ocpWalletFile) throws CipherException {

        ECKeyPair ecKeyPair = Wallet.decrypt(oldPwd, ocpWalletFile.getWalletFile());

        ocpWalletFile.setWalletFile(Wallet.createStandard(newPwd, ecKeyPair));

        return ocpWalletFile;

    }


    /**
     * 载入一个钱包
     *
     * @param password  wallet file的密码；如果助记词密码为空则不再生成wallet file
     *                 imtoken助记词密码为null
     * @param mnemonic 助记词
     * @param path     路径 m/44'/60'/0'／0／0   m/purpse'/coin_type'/account'/change/address_index
     * @return 钱包
     * @throws CipherException
     */
//    public static OCPWalletUtils fromMnemonic(String password, String mnemonic, String path) throws CipherException {
//        OCPWalletUtils wa = new OCPWalletUtils();
//        byte[] seed = MnemonicUtils.generateSeed(mnemonic, password);
//        ECKeyPair ecKeyPair = createBip44NodeFromSeed(seed, path);
//        if (password != null) {
//            wa.walletFile = Wallet.createStandard(password, ecKeyPair);
//        }
//        wa.credentials = Credentials.create(ecKeyPair);
//        wa.walletPass = password;
//        wa.mnemonic = mnemonic;
//        wa.passphrase = password;
//        wa.path = path;
//        return wa;
//    }

    /**
     * m/purpse'/coin_type'/account'/change/address_index
     * m/44'/60'/0'／0／0
     *
     * @param seed 种子
     * @param path 路径 m/44'/60'/0'／0／0
     * @return key 秘钥
     */
    public static ECKeyPair createBip44NodeFromSeed(byte[] seed, String path) {
        HdKeyPath p = HdKeyPath.valueOf(path);
        HdKeyNode node = HdKeyNode.fromSeed(seed);
        node = node.createChildNode(p);
        byte[] privateKeyByte = node.getPrivateKey().getPrivateKeyBytes();
        ECKeyPair ecKeyPair = ECKeyPair.create(privateKeyByte);
        return ecKeyPair;
    }

    /**
     * 导出一个新的wallet file，并记录下当前的wallet file信息
     *
     * @param password 设定一个钱包文件密码
     * @return 钱包文件
     * @throws CipherException
     */
    public WalletFile exportWalletFile(String password) throws CipherException {
        ECKeyPair ekp = credentials.getEcKeyPair();
        WalletFile walletFile = Wallet.createStandard(password, ekp);
        this.walletFile = walletFile;
        this.walletPass = password;
        return walletFile;
    }

    /**
     * wallet file的json内容
     *
     * @param wf
     * @return json格式的内容
     */
    public static String walletFileJson(OCPWalletFile wf) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(wf);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 离线签名一笔交易
     *
     * @param toAddress 目标地址
     * @param gasPrice  油价
     * @param gasLimit  汽油上限
     * @param amount    转账额度
     * @param nonce     nonce
     * @return
     */
//    public String signOfflineTransaction(String toAddress, BigInteger gasPrice, BigInteger gasLimit, BigInteger amount, BigInteger nonce) {
//        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, toAddress, amount);
//        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
//        String hexValue = Numeric.toHexString(signedMessage);
//        return hexValue;
//    }

    /**
     * 签名合约交互
     *
     * @param toAddress
     * @param gasPrice
     * @param gasLimit
     * @param nonce
     * @param data
     * @return
     */
//    public String signContractTransaction(String toAddress, BigInteger gasPrice, BigInteger gasLimit, BigInteger nonce, String data) {
//        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, toAddress, new BigInteger("0"), data);
//        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
//        String hexValue = Numeric.toHexString(signedMessage);
//        return hexValue;
//    }
//
//    /**
//     * 签名合约
//     *
//     * @param tr transaction
//     * @return 签名结果
//     */
//    public String signContractTransaction(RawTransaction tr) {
//        return signContractTransaction(tr, credentials);
//    }
//
//    /**
//     * 签名
//     *
//     * @param tr transaction
//     * @param c  身份
//     * @return 签名结果
//     */
//    public static String signContractTransaction(RawTransaction tr, Credentials c) {
//        byte[] signedMessage = TransactionEncoder.signMessage(tr, c);
//        String hexValue = Numeric.toHexString(signedMessage);
//        return hexValue;
//    }

    /**
     * 是否合法
     *
     * @return
     */
    public static boolean valid(String walletPass, WalletFile walletFile) {
        ECKeyPair keyPair;
        try {
            keyPair = Wallet.decrypt(walletPass, walletFile);
        } catch (CipherException e) {
            return false;
        }
        return true;
    }

    /**
     *
     */
    public static boolean loginWallet(String walletAddress, String walletPass) throws Exception {
        OCPWalletFile ocpWalletFile = WalletStorage.getInstance().getOCPWallet(walletAddress);
        if (ocpWalletFile != null && ocpWalletFile.getWalletFile() != null) {
            return valid(walletPass, ocpWalletFile.getWalletFile());
        } else {
            throw new Exception(MyApp.getContext().getResources().getString(R.string.wallet_file_not_exists));
        }

    }


    /**
     * 反序列化一个钱包
     *
     * @param content
     * @return 钱包文件
     */
    public static WalletFile createWalletFileByKeystore(byte[] content) {
        try {
            ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
            WalletFile walletFile = objectMapper.readValue(content, WalletFile.class);
            return walletFile;
        } catch (IOException ex) {

        }
        return null;
    }

    /**
     * 反序列化钱包
     *
     * @param keystore
     * @return 钱包文件
     */
    public static WalletFile createWalletFileByKeystore(String keystore) {
        try {
            ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
            WalletFile walletFile = objectMapper.readValue(keystore, WalletFile.class);
            return walletFile;
        } catch (IOException ex) {

        }
        return null;
    }

    /**
     * 反序列化一个钱包
     *
     * @param file
     * @return 钱包文件
     */
    public static WalletFile createWalletFileByKeystore(File file) {
        try {
            ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
            WalletFile walletFile = objectMapper.readValue(file, WalletFile.class);
            return walletFile;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 地址转换
     *
     * @param number 公钥
     * @return 地址
     */
//    public static String address(BigInteger number) {
//        return Keys.toChecksumAddress(Keys.getAddress(number));
//    }

    /**
     * 私钥转换
     *
     * @param prik 私钥
     * @return 私钥的hex格式
     */
    public static String privateKey(BigInteger prik) {
        return Numeric.toHexStringWithPrefixZeroPadded(prik, Keys.PRIVATE_KEY_LENGTH_IN_HEX);
    }


    /**
     * 私钥
     *
     * @return 私钥
     */
    public String getPrivateKey() {
        return privateKey(credentials.getEcKeyPair().getPrivateKey());
    }


    /**
     * @param password
     * @param ecKeyPair
     * @return
     * @throws CipherException
     */
    public static WalletFile getWalletFile(String password, ECKeyPair ecKeyPair) throws CipherException {
        return Wallet.createStandard(password, ecKeyPair);
    }


    /**
     * @param ecKeyPair
     * @return
     */
    public static String getWalletPrivateKey(ECKeyPair ecKeyPair) {
        byte[] bytes = new BigInteger(ecKeyPair.getPrivateKey().toString()).toByteArray();
        byte[] encode = Hex.encode(bytes);
        return new String(encode);
    }


    /**
     * @param privateKey
     * @return
     */
    public static ECKeyPair getEckeyPair(String privateKey) {
        //通用 private key 还原 big integer private key
        byte[] decodeCode = Hex.decode(privateKey.getBytes());
        BigInteger resultBig = Numeric.toBigInt(decodeCode);
        //通过 (BigInteger)privateKey 创建秘钥对
        return ECKeyPair.create(resultBig);
    }


    /**
     * @param password
     * @param walletFile
     * @return
     * @throws CipherException
     */
    public static ECKeyPair walletFileDecrypt(String password, WalletFile walletFile) throws CipherException {
        return Wallet.decrypt(password, walletFile);
    }


//    /**
//     * @param jsonKeySotre
//     * @return
//     */
//    public static WalletFile json2WalletFile(String jsonKeySotre) {
//        return JSON.parseObject(jsonKeySotre, new com.alibaba.fastjson.TypeReference<WalletFile>() {
//        });
//    }


    /**
     * custom info about to address
     *
     * @param input
     * @return
     */
    public static String getTransactionTo(String input) {
        if (input == null || "".equals(input)) return null;
        return "0x" + input.substring(34, input.length() - 64);

    }

    /**
     * custom info about contract amount
     *
     * @param input
     * @return
     */
    public static BigDecimal getTransactionAmount(String input) {
        if (input == null || "".equals(input)) return null;
        return CommonUtils.getSTAmount(input);
    }


    /**
     * @param gasPrice
     * @param gasUsed
     * @return
     */
    public static BigDecimal getTransactionFee(BigDecimal gasPrice, BigDecimal gasUsed) {
        BigDecimal bigDecimal = gasPrice.multiply(gasUsed).divide(new BigDecimal(1000000000000000000d), 18, BigDecimal.ROUND_UP);
        return bigDecimal;
    }


    /**
     * @param txBlockNumber
     * @param recentBlockNumber
     * @return
     */
    public static boolean verify12Block(BigInteger txBlockNumber, BigInteger recentBlockNumber) {
        return recentBlockNumber.compareTo(txBlockNumber.add(new BigInteger("12"))) > 0;
    }


    /**
     * @param walletAddress
     * @return
     */
    public static String foldWalletAddress(String walletAddress) {
        StringBuilder stringBuilder = new StringBuilder(walletAddress);
        if (!walletAddress.startsWith("0x")) {
            stringBuilder.insert(0, "0x");
        }
        stringBuilder.replace(10, stringBuilder.length() - 10, "...");
        return stringBuilder.toString();

    }

    /**
     * @param privateKey
     * @param password
     * @return
     * @throws CipherException
     */
    public static WalletFile getWalletFileByPrivateKey(String privateKey, String password) throws CipherException {
        ECKeyPair eckeyPair = getEckeyPair(privateKey);
        WalletFile walletFile = Wallet.createStandard(password, eckeyPair);
        return walletFile;

    }


    /**
     * @param mnemonic
     * @param path
     * @param password
     * @return
     * @throws CipherException
     */
    public static WalletFile getWalletFileByMnemonic(String mnemonic, String path, String password) throws CipherException {
        byte[] seed = CustomMnemonicUtils.generateSeed(mnemonic, "");
        ECKeyPair ecKeyPair = createBip44NodeFromSeed(seed, path);
        WalletFile walletFile = Wallet.createStandard(password, ecKeyPair);
        return walletFile;
    }


    public static String walletAddress32b(String walletAddress) {
        if (walletAddress.startsWith("0x")) {
            walletAddress = walletAddress.replace("0x", "0x000000000000000000000000");
        } else {
            walletAddress = "0x000000000000000000000000" + walletAddress;
        }
        return walletAddress;
    }


    public static String subWalletAddress(String walletAddress) {
        if (walletAddress.startsWith("0x")) {
            return walletAddress.replace("0x000000000000000000000000", "0x").toString();
        }
        return walletAddress;
    }


    /**
     * parse input
     *
     * @param input
     * @return
     */
    public static BigDecimal getSTAmount(String input) {
        if (input.startsWith("0x")) {
            input = input.replace("0x", "");
        }
        String hexAmount = input.substring(input.length() - 64, input.length());

        return getAmountByP1(hexAmount);
    }


    /**
     * parse data about Event Log EthTransaction
     *
     * @param input
     * @return
     */
    public static BigDecimal getAmountByP1(String input) {
        if (input.startsWith("0x")) {
            input = input.replace("0x", "");
        }
        BigDecimal amount = new BigDecimal(new BigInteger(input, 16).toString());
        BigDecimal bigDecimal = amount.divide(new BigDecimal(1000000000000000000d), 4, BigDecimal.ROUND_UP);
        return bigDecimal;
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
    public static String transactionEth(ECKeyPair ecKeyPair, String ethAmount, String toAddress, String gas_price, String gas_limit, String data, BigInteger nonce) throws IOException, InterruptedException {
        Credentials credentials = Credentials.create(ecKeyPair);
        RawTransaction tx = RawTransactionUtils.getTransaction(nonce, null, ethAmount, gas_price, gas_limit, data, toAddress);
        byte[] signed = TransactionEncoder.signMessage(tx, (byte) EtherScanApi.CHAIN_ID, credentials);
        String signHex = "0x" + Hex.toHexString(signed);
        return signHex;
//        String url = forwardTransaction();
        //进行交易需要先获得nonce,该账号的交易次数
//        String transactionResp = RequestUtils.sendGet(url);
//        transactionResp = transactionResp.replace("/n", "");
//        EtherScanJsonrpcResponse txHashResponse = JSON.parseObject(transactionResp, new com.alibaba.fastjson.TypeReference<EtherScanJsonrpcResponse>() {
//        });
//        if (txHashResponse.result == null || "".equals(txHashResponse.result)) {
//            logger.warn("txHash is null on EtherScan \n " +
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

//        return null;

    }

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
    public static String signTransaction(ECKeyPair ecKeyPair, String OCNAmount, String toAddress, String gas_price, String gas_limit, String data, String ERC20Address, BigInteger nonce) throws IOException, InterruptedException {
        Credentials credentials = Credentials.create(ecKeyPair);
        RawTransaction tx = RawTransactionUtils.getTransaction(nonce, ERC20Address, OCNAmount, gas_price, gas_limit, data, toAddress);
        byte[] signed = TransactionEncoder.signMessage(tx, (byte) EtherScanApi.CHAIN_ID, credentials);
        return "0x" + Hex.toHexString(signed);
    }


    public static boolean isEthAddress(String address) {
        return RegularExpressionUtils.valid(address, Constans.REGULAR.REGULAR_ETH_ADDRESS);

    }


}
