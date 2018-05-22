package com.ocpay.wallet.http.client;

import com.ocpay.wallet.utils.web3j.response.RateResponse;
import com.ocpay.wallet.utils.web3j.response.SymbolPairResponse;
import com.ocpay.wallet.utils.web3j.response.TokenPriceResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by y on 2018/4/17.
 */

public interface DataBlockClient {


    /**
     * https://data.block.cc/api/v1/price?symbol=ETH
     *
     * @param tokenName
     * @return
     */
    @GET("api/v1/price?")
    Observable<TokenPriceResponse> getTokenPrice(@Query("symbol") String tokenName);


    /**
     * https://data.block.cc/api/v1/ticker?market=huobipro&symbol_pair=OCN_ETH
     *
     * @return
     */
    @GET("api/v1/ticker?market=huobipro&symbol_pair=OCN_ETH")
    Observable<SymbolPairResponse> getPairOCN_ETH();

    /**
     * https://data.block.cc/api/v1/exrate?base=USD&symbol=CNY
     *
     * @return
     */
    @GET("api/v1/exrate?")
    Observable<RateResponse> getRate(@Query("base")String base,@Query("symbol")String symbol);


}
