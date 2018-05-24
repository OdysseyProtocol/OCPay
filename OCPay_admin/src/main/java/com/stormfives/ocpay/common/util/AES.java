package com.stormfives.ocpay.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zxb on 8/31/16.
 */
public class AES {

    private static final String ENCODING = "UTF-8";
    private static final String KEY_ALGORITHM = "AES";//产生密钥的算法
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//加解密算法 格式：算法/工作模式/填充模式 注意：ECB不使用IV参数
    public static final String DESKEY = "PIlpNkvKoNGyM4CCGBA2gQ==";

    /**
     * 产生密钥
     */
    public static String getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(128);//初始化密钥长度,128,192,256（选用192和256的时候需要配置无政策限制权限文件--JDK6）
        SecretKey key = keyGenerator.generateKey();//产生密钥
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 还原密钥：二进制字节数组转换为Java对象
     */
    public static Key toKey(byte[] keyByte) {
        return new SecretKeySpec(keyByte, KEY_ALGORITHM);
    }

    /**
     * AES加密
     *
     * @param data  带加密数据
     * @param base64Key  base64加密后的密钥
     */
    public static String encrypt(String data, String base64Key) throws InvalidKeyException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            BadPaddingException,
            UnsupportedEncodingException {
        Key key = toKey(Base64.decodeBase64(base64Key));//还原密钥
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);//JDK下用
        cipher.init(Cipher.ENCRYPT_MODE, key);//设置加密模式并且初始化key
        byte[] encodedByte = cipher.doFinal(data.getBytes(ENCODING));
        return Base64.encodeBase64String(encodedByte);
    }

    /**
     * AES解密
     * @param data 待解密数据为字符串
     * @param base64Key 密钥
     */
    public static String decrypt(String data, String base64Key) throws InvalidKeyException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            BadPaddingException {

        Key key = toKey(Base64.decodeBase64(base64Key));//还原密钥
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);//JDK下用
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encodedByte = cipher.doFinal(Base64.decodeBase64(data));
        return new String(encodedByte);
    }
}
