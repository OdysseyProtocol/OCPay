package com.ocpay.wallet.http.client;

import com.ocpay.wallet.utils.web3j.response.EtherScanJsonrpcResponse;
import com.ocpay.wallet.utils.web3j.response.EtherScanTxListResponse;
import com.ocpay.wallet.utils.web3j.response.EventLogTransactionResponse;
import com.ocpay.wallet.utils.web3j.response.TokenBalanceResponse;

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
    Observable<TokenBalanceResponse> getEthBalance(@Query("address") String address);


    @GET("api?module=proxy&action=eth_call&tag=latest")
    Observable<TokenBalanceResponse> getTokenBalance(@Query("to") String address, @Query("data") String data);


    /**
     *
     * http://api-ropsten.etherscan.io/api?module=account&action=txlist&address=0x7E8247C7d145dEBe8a8C2D2a2Ab450992AA884c9&startblock=0&endblock=99999999&sort=desc&apikey=YourApiKeyToken
     * about eth transaction list
     *
     * @param address
     * @param startblock
     * @param endBlockNumber
     * @return
     */
    @GET("api?module=account&action=txlist&sort=desc")
    Observable<EtherScanTxListResponse> getEthTransactionList(@Query("address") String address, @Query("startblock") String startblock, @Query("endblock") String endBlockNumber);


    /**
     * https://api-ropsten.etherscan.io/api?module=logs&action=getLogs
     * &fromBlock=2916984
     * &toBlock=latest
     * &address=0xd1bcbe82f40a9d7fbcbd28cca6043d72d66d8e9d
     * &topic2=0x0000000000000000000000007e8247c7d145debe8a8c2d2a2ab450992aa884c9
     * &topic1=0x0000000000000000000000007e8247c7d145debe8a8c2d2a2ab450992aa884c9
     * &topic1_2_opr=or
     */
    @GET("api?module=logs&action=getLogs&topic1_2_opr=or")
    Observable<EventLogTransactionResponse> getTokenTransactionList(@Query("address") String contractAddress, @Query("topic1") String topic1, @Query("topic2") String topic2, @Query("fromBlock") String fromBlock, @Query("toBlock") String toBlock);


    /**
     * https://api-ropsten.etherscan.io/api?module=proxy&action=eth_blockNumber&apikey=YourApiKeyToken
     */
    @GET("api?module=proxy&action=eth_blockNumber")
    Observable<EtherScanJsonrpcResponse> getEthBlockNumber();


    /**
     * eth_gasPrice
     * Returns the current price per gas in wei.
     * https://api-ropsten.etherscan.io/api?module=proxy&action=eth_gasPrice&apikey=YourApiKeyToken
     *
     * @return
     */
    @GET("api?module=proxy&action=eth_gasPrice")
    Observable<EtherScanJsonrpcResponse> getGasPrice();

    /**
     * https://api-ropsten.etherscan.io/api?module=proxy&action=eth_getTransactionCount&address=0x2910543af39aba0cd09dbb2d50200b3e800a63d2&tag=latest&apikey=YourApiKeyToken
     *
     * @param address
     * @return
     */
    @GET("api?module=proxy&action=eth_getTransactionCount")
    Observable<EtherScanJsonrpcResponse> getNonce(@Query("address") String address);


    @GET("api?module=proxy&action=eth_sendRawTransaction")
    Observable<EtherScanJsonrpcResponse> sendTransaction(@Query("hex") String hex);




}
