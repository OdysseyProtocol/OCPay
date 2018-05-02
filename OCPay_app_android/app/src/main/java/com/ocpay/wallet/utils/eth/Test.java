package com.ocpay.wallet.utils.eth;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import java.security.SecureRandom;

/**
 * Created by y on 2018/4/9.
 */
public class Test {
    static String privateKey = "787133bcc23b5e452adb78b44ea02bcfa959c2fbd0b6464325d9080f4d11e306";
    static String password = "888888888";
    static String walletFileString = "{\"address\":\"22a9f4f71d4c096f4d591b22bf523819ca40f6f5\",\"crypto\":{\"cipher\":\"aes-128-ctr\",\"ciphertext\":\"4985743643b2fc1e7b13f2b8827ac97c95eac380df3d6d6140ae94efdd681a50\",\"cipherparams\":{\"iv\":\"fbccd8de56328b039d522567b39fe7a5\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"n\":262144,\"p\":1,\"r\":8,\"salt\":\"08df6786345e6ed54b30d2282fa89dbdfb59e0d15fe8f531ec6f66c4a4e69293\"},\"mac\":\"84223af346e90aeed5294115120d0fa8f3b14baf4eb9f3e5cf76fc5dfec92ec7\"},\"id\":\"5f0541a6-45c6-4732-aa5c-913f11161e7b\",\"version\":3}\n";
    static final String ImToken_SEED_PATH = "m/44'/60'/0'/0/0";

    public static void main(String[] args) {
//        getWallet();
        test();
    }


    public static void getWallet() {
        WalletFile walletFile = OCPWalletUtils.createWalletFileByKeystore(walletFileString);
        try {
            ECKeyPair decrypt = Wallet.decrypt(password, walletFile);
            System.out.println(decrypt.getPrivateKey());
        } catch (CipherException e) {
            e.printStackTrace();
        }
//        AES.getKey()

        System.out.println(walletFile.getAddress());


//            System.out.println(w);


//        } catch (CipherException e) {
//            e.printStackTrace();
//        }


    }

    public static void createWallet() {
//        try {
//            OCPWalletUtils withMnemonic = OCPWalletUtils.createWithMnemonic(password, ImToken_SEED_PATH,null,true);
//
////            AES.decrypt()
//            System.out.println(withMnemonic.getWalletFile().toString());
//            System.out.println(withMnemonic.getWalletFile().getCrypto());
//            Gson gson = new Gson();
//            String s = gson.toJson(withMnemonic.getWalletFile());
//            System.out.println(s);
//            System.out.println(withMnemonic.getMnemonic());
//
//        } catch (CipherException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//        } catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        }

    }


    public static void test() {
        byte[] initialEntropy = new byte[16];
        SecureRandom secureRandom = SecureRandomUtils.secureRandom();

        secureRandom.nextBytes(initialEntropy);

//        String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);

//        byte[] seed = MnemonicUtils.generateSeed(mnemonic, "");


//        ECKeyPair ecKeyPair1 = OCPWalletUtils.createBip44NodeFromSeed(seed, ImToken_SEED_PATH);


//        Credentials credentials = Credentials.create(ecKeyPair1);


//        System.out.println("mnemonic:" + mnemonic);
//        System.out.println("address:" + credentials.getAddress());
    }

}