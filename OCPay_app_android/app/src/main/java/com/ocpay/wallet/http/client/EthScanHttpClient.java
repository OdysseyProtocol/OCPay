package com.ocpay.wallet.http.client;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by y on 2018/4/17.
 */

public interface EthScanHttpClient {


    /**
     * https://api.etherscan.io/api?module=block&action=getblockreward&blockno=2165403&apikey=YourApiKeyToken
     *
     * @param apikey
     * @param blockno
     * @return
     */
    @GET("api?module=block&action=getblockreward")
    Observable<Object> checkUpdate(@Query("apikey") String apikey, @Query("blockno") String blockno);


    /**
     * https://api.etherscan.io/api?module=block&action=getblockreward&blockno=2165403&apikey=YourApiKeyToken
     *
     * @return
     */
    @GET("api?module=account&action=balance&tag=latest")
    Observable<Object> getEthBalance(@Query("address") String address);


//    /**
//     * eth
//     * https://api-ropsten.etherscan.io/api?module=account&action=balance&address=0x7E8247C7d145dEBe8a8C2D2a2Ab450992AA884c9&tag=latest&apikey=YourApiKeyToken
//     * @param address
//     * @return
//     */
//    public static String getBalanceUrl(String address) {
//        return Url + "module=account&action=balance&address=" + address + "&apikey=" + APIKey.EtherScan_API_KEY;
//    }
//
//
//    /**
//     * get balance of token
//     *
//     * @param to
//     * @param data
//     * @throws IOException
//     */
//    public static String eth_call(String to, String data) {
//
//        return Url + "module=proxy&action=eth_call&to=" + to + "&data=" + data + "&tag=latest&apikey=" + APIKey.EtherScan_API_KEY;
//
//
//    }


}
