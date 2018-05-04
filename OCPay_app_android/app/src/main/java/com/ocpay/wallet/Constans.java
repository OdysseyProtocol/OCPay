package com.ocpay.wallet;

/**
 * Created by y on 2018/4/16.
 */

public class Constans {

    public static class WALLET {
        public static int WALLET_TYPE_LOCAL_GEN = 1;
        public static int WALLET_TYPE_IMPORT = 2;
        public static int WALLET_TYPE_WATCHING = 3;
        public static String CURRENT_WALLET = "CURRENT_WALLET";

        public static String TOKEN_NAME = "token_name";
        public static String WALLET_ADDRESS = "wallet_address";
        public static String ADDRESS_FROM = "wallet_from";
        public static String WALLET_NAME = "wallet_name";


        public static String WALLET_SAVE_PATH = MyApp.getContext().getExternalCacheDir().getAbsolutePath();


        public static String TX_EVENT_LOG = "tx_event_log";
        public static String TX_ETH = "tx_eth";

        //Metamask
        public static String PATH_Metamask = "m/44'/60'/0'/0/0 Jaxx,Metamask(ETH)";
        //Ledger
        public static String PATH_Ledger = "m/44'/60'/0'/0 Ledger(ETH)";
        //imToken
        public static String PATH_imToken = "m/44'/60'/0'/0/0 imToken(ETH)";
        //path
        public static String PATH_Custom = "m/44'/60'/1'/0/0 Custom Path";

    }


    public static class HTTP {
        public final static String API__ETHSCAN = "https://api.etherscan.io/";
        public final static String API_ROPSTEN_ETHSCAN = "https://ropsten.etherscan.io/";
        public final static String API_ETHSCAN = API_ROPSTEN_ETHSCAN;
        public final static String API_TXHAH = API_ETHSCAN + "tx/";


    }


    public static class HOME {
        public final static int GENERALIZE = 1;
        public final static int WHEEL_AD = 2;
        public final static int MERCHANT = 3;


    }


    public static class CONFIG {
        public final static String HIDE_ASSET = "hide_asset";


    }


    public static class RXBUS {
        public final static int ACTION_SELECT_WALLET = 1;
        public final static int ACTION_UPDATE_WALLET_LIST = 2;
        public final static int ACTION_OPEN_DRAWER = 3;

        public final static int ACTION_IMPORT_WALLET_MNEMONIC = 4;
        public final static int ACTION_IMPORT_WALLET_KEYSTORE = 5;
        public final static int ACTION_IMPORT_WALLET_PRIVATE_KEY = 6;
        public final static int ACTION_IMPORT_WALLET_WATCH = 7;


        public final static int ACTION_UPDATE_TRANSACTION_LIST = 8;


    }

    public static class REGULAR {

        public final static String REGULAR_FLOAT = "(^0\\.\\d*[1-9]\\d?$)|(^[1-9]\\d*(.\\d*[1-9]\\d?)?$)|(^[1-9]\\d*(.0)?$)|(^0\\.)|(^[1-9]\\d*.)";

        public final static String REGULAR_MNEMONIC = "^([a-zA-Z]+ )+[a-zA-Z]+$";

    }


    public static class TEST {
        public final static String WALLET_ADDRESS = "0x7E8247C7d145dEBe8a8C2D2a2Ab450992AA884c9";
        public final static String OCN_TOKEN_ADDRESS = "0xd1bcbe82f40a9d7fbcbd28cca6043d72d66d8e9d";
    }


}
