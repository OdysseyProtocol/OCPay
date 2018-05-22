package com.ocpay.wallet.http.client;

import com.ocpay.wallet.utils.RateUtils;
import com.ocpay.wallet.utils.TokenUtils;
import com.ocpay.wallet.utils.web3j.response.RateResponse;
import com.ocpay.wallet.utils.web3j.response.SymbolPairResponse;
import com.ocpay.wallet.utils.web3j.response.TokenPriceResponse;

import java.math.BigDecimal;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by y on 2018/5/4.
 */

public class DataBlockClientIml {

    public static void getPairOCN_ETH(int requestId) {
        HttpClient.Builder
                .getDataBlockServer()
                .getPairOCN_ETH()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<SymbolPairResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(SymbolPairResponse o) {
                                if (o == null) return;
                                String s = o.getData().symbol_pair;

                                String[] tokenName = s.split("_");

                                RateUtils.setRatePairs(tokenName[0], tokenName[1], new BigDecimal(o.getData().last));

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    public static void getRate(String base, String to) {
        HttpClient.Builder
                .getDataBlockServer()
                .getRate(base, to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<RateResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(RateResponse o) {
                                if (o == null) return;
                                RateUtils.setRatePairs(o.getData().getBase(), "CNY", new BigDecimal(o.getData().getRates().CNY));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }


    public static void getTokenPrice(int requetId, String tokenName) {
        HttpClient.Builder
                .getDataBlockServer()
                .getTokenPrice(tokenName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<TokenPriceResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(TokenPriceResponse o) {
                                if (o == null || o.getData() == null || o.getData().size() <= 0)
                                    return;
                                TokenPriceResponse.TokenPriceDate tokenPriceDate = o.getData().get(0);
                                TokenUtils.setTokenPrice(tokenPriceDate.symbol, new BigDecimal(tokenPriceDate.price));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }


}
