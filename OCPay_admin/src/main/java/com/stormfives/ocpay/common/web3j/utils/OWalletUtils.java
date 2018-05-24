package com.stormfives.ocpay.common.web3j.utils;

import com.alibaba.fastjson.JSON;
import com.stormfives.ocpay.common.web3j.bean.WalletInfo;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by y on 2018/3/5.
 */
public class OWalletUtils {


    /**
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static WalletInfo generateWallet() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        return new WalletInfo(ecKeyPair);
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


    /**
     * @param jsonKeySotre
     * @return
     */
    public static WalletFile json2WalletFile(String jsonKeySotre) {
        return JSON.parseObject(jsonKeySotre, new com.alibaba.fastjson.TypeReference<WalletFile>() {
        });
    }


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


    public static boolean verify12Block(BigInteger txBlockNumber, BigInteger recentBlockNumber) {
        return recentBlockNumber.compareTo(txBlockNumber.add(new BigInteger("12"))) > 0;
    }
}