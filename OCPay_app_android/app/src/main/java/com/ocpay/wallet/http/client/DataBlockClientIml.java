package com.ocpay.wallet.http.client;

import com.ocpay.wallet.utils.web3j.response.RateResponse;
import com.ocpay.wallet.utils.web3j.response.SymbolPairResponse;
import com.ocpay.wallet.utils.web3j.response.TokenPriceResponse;

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

    public static void getRate() {
        HttpClient.Builder
                .getDataBlockServer()
                .getRate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<RateResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(RateResponse o) {

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


    public static void getTokenPrice(int requetId,String tokenName) {
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
//                                o.getTokenPriceData().get(0).name;

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
