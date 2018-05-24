package com.stormfives.ocpay.common.web3j.api;


import com.stormfives.ocpay.common.web3j.key.APIKey;

import java.io.IOException;

/**
 * Created by y on 2018/3/5.
 */
public class EtherScanApi {


    /**
     * @param ROPSTEN_API
     * @param ROPSTEN_CHAIN_ID
     * @param ROPSTEN_API
     * @param MAIN_CHAIN_ID
     */
    private String token;
    public static int MAIN_CHAIN_ID = 1;
    public static int ROPSTEN_CHAIN_ID = 3;

    public static String ETH_API = "http://api.etherscan.io/api?";
//    public static String ROPSTEN_API = "https://ropsten.etherscan.io/api?";

    public static String Url = ETH_API;
//        public static int CHAIN_ID = ROPSTEN_CHAIN_ID;
    public static int CHAIN_ID = MAIN_CHAIN_ID;

    /**
     *
     * http://api-ropsten.etherscan.io/api?module=account&action=txlist&address=0xd1bcbe82f40a9d7fbcbd28cca6043d72d66d8e9d&startblock=0&endblock=99999999&sort=asc&apikey=UJQ2R2QSC3NE9NVZEVUA8AZJVYFBCP11KT
     */

    /**
     * @param address
     * @return
     */
    public static String getNonceForAddress(String address) {
        return Url + "module=proxy&action=eth_getTransactionCount&address=" + address + "&tag=latest&apikey=" + APIKey.EtherScan_API_KEY;
    }


    /**
     * @param raw
     * @throws IOException
     */
    public static String forwardTransaction(String raw) throws IOException {
        return Url + "module=proxy&action=eth_sendRawTransaction&hex=" + raw + "&apikey=" + APIKey.EtherScan_API_KEY;
    }


    /**
     * eg:      https://ropsten.etherscan.io/api?module=proxy&action=eth_getTransactionReceipt&txhash=0xe33b2e37a4aec55bcd9776f9f6f93f7f4b4c28e10ce7c88ae1901b12eb524f02&apikey=UJQ2R2QSC3NE9NVZEVUA8AZJVYFBCP11KT
     *
     * @param txhash
     * @throws IOException
     */
    public static String getTransactionReceipt(String txhash) {
        return Url + "module=proxy&action=eth_getTransactionReceipt&txhash=" + txhash + "&apikey=" + APIKey.EtherScan_API_KEY;

    }


    public static String getBalanceUrl(String address) {
        return Url + "module=account&action=balance&address=" + address + "&apikey=" + APIKey.EtherScan_API_KEY;
    }


    /**
     * get balance of token
     *
     * @param to
     * @param data
     * @throws IOException
     */
    public static String eth_call(String to, String data) {

        return Url + "module=proxy&action=eth_call&to=" + to + "&data=" + data + "&tag=latest&apikey=" + APIKey.EtherScan_API_KEY;


    }


    /**
     * get transactions list about contractAddress
     *
     * @param address
     * @param startBlockNumber
     * @param endBlockNumber
     * @return
     */
    public static String transactions_by_address(String address, String startBlockNumber, String endBlockNumber) {
        return Url + "module=account&action=txlist&address=" + address + "&startblock=" + startBlockNumber + "&endblock=" + endBlockNumber + "&sort=asc&apikey=" + APIKey.EtherScan_API_KEY;

    }


    /**
     * get blockNumber recent
     * https://api.etherscan.io/api?module=proxy&action=eth_blockNumber&apikey=YourApiKeyToken
     *
     * https://api.etherscan.io/api?module=proxy&action=eth_blockNumber&apikey=YourApiKeyToken
     */


    public static String getEthRecentBlockNumber() {
        return Url + "module=proxy&action=eth_blockNumber&apikey=" + APIKey.EtherScan_API_KEY;

    }


    /**
     * //https://api.etherscan.io/api?module=block&action=getblockreward&blockno=2165403&apikey=YourApiKeyToken
     *
     * @return
     */
    public static String getEthRecentBlockInfo(String blockNo) {
        return Url + "module=block&action=getblockreward&blockno=" + blockNo + "&apikey=" + APIKey.EtherScan_API_KEY;

    }
}
