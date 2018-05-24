package com.stormfives.ocpay.common.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class APIEncryptHelper {


    private String password;

    private Cipher cipher;
    private SecretKeySpec key;

    public APIEncryptHelper(String version, String platform) {
        if ("Android".equalsIgnoreCase(platform)) platform = "Add";
        password = "oB" + platform + "MYFUzLed" + version;
        if (Integer.parseInt(version) >= 302 && "Add".equalsIgnoreCase(platform)) {
            platform = "and";
            password = "oB" + version + "yNWvPszb" + platform;
        }
        key = new SecretKeySpec(getKey(), "AES");
        try {
            cipher = Cipher.getInstance(getTransformation());
        } catch (Exception ignore) {
        }
    }

    private String getTransformation() {
        return "AES/CBC/PKCS5Padding";
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private byte[] getKey() {
        byte[] keySource;
        if (StringUtils.isNotEmpty(password)) {
            keySource = password.getBytes();
        } else {
            keySource = new byte[24];
        }
        return keySource;
    }

    private byte[] encrypt(String content) {
        try {
            byte[] byteContent = content.getBytes("utf-8");
            IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(byteContent);
        } catch (Exception ignore) {
        }
        return null;
    }

    private byte[] decrypt(byte[] content) {
        try {
            IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return cipher.doFinal(content);
        } catch (Exception ignore) {
        }
        return null;
    }

    private String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder("");
        if (buf != null) {
            for (byte aBuf : buf) {
                String hex = Integer.toHexString(aBuf & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toLowerCase());
            }
        } else {
            return null;
        }
        return sb.toString();
    }

    /**
     * Encode on main thread
     *
     * @param content content
     * @return result
     */
    public String encode(String content) {
        return parseByte2HexStr(encrypt(content));
    }

    private byte[] parseHexStr2Byte(String hexStr) {
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * Decode, on main thread
     *
     * @param content content
     * @return result
     */
    private static Logger logger = LoggerFactory.getLogger(APIEncryptHelper.class);

    public String decode(String content) {

        //logger.error("加密的字符串==="+content);
        byte[] result = decrypt(parseHexStr2Byte(content));
        if (result == null) {
            return content;
        }
        String str = new String(result);

        //logger.error("解密后的字符串==="+str);
        return str;
    }

}