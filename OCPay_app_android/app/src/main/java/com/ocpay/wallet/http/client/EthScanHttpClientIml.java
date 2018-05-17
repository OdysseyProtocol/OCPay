package com.ocpay.wallet.http.client;

import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.web3j.response.EthBalanceResponse;
import com.ocpay.wallet.utils.web3j.response.EtherScanJsonrpcResponse;
import com.ocpay.wallet.utils.web3j.transaction.OWalletTransaction;
import com.snow.commonlibrary.log.MyLog;

import java.math.BigInteger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.ocpay.wallet.Constans.TEST.OCN_TOKEN_ADDRESS;
import static com.ocpay.wallet.Constans.TEST.WALLET_ADDRESS;

/**
 * Created by y on 2018/5/4.
 */

public class EthScanHttpClientIml {


    public static void showBalance() {
        HttpClient.Builder
                .getEthScanServer()
                .getEthBalance(WALLET_ADDRESS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<EthBalanceResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(EthBalanceResponse o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }

    public static void getTokenBalanceOf() {

        String data = OWalletTransaction.getBalanceOfTokenData(WALLET_ADDRESS);
        HttpClient.Builder
                .getEthScanServer()
                .getTokenBalance(OCN_TOKEN_ADDRESS, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }

    public static void getTransactionList() {

        HttpClient.Builder
                .getEthScanServer()
                .getEthTransactionList(WALLET_ADDRESS, "5000", "9999999")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }


    public static void getBlockNumber() {
        HttpClient.Builder
                .getEthScanServer()
                .getEthBlockNumber()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }


    public static void getTokenTrList() {
        String walletAddress = OCPWalletUtils.walletAddress32b(WALLET_ADDRESS);
        HttpClient.Builder
                .getEthScanServer()
                .getTokenTransactionList(OCN_TOKEN_ADDRESS, walletAddress, walletAddress, "1000", "last")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }

    public static void sendRaw() {
        HttpClient.Builder
                .getEthScanServer()
                .getEthBalance(WALLET_ADDRESS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<EthBalanceResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(EthBalanceResponse o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }


    public static void getAddressNonce(final int requestId, String walletAddress) {
        HttpClient.Builder
                .getEthScanServer()
                .getNonce(walletAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<EtherScanJsonrpcResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(EtherScanJsonrpcResponse o) {
                                MyLog.i("onNext" + o.toString());
                                RxBus.getInstance().post(requestId, o);

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }


    public static void sendTransaction(final int requestId, String hexSign) {
        HttpClient.Builder
                .getEthScanServer()
                .sendTransaction(hexSign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());
                                RxBus.getInstance().post(requestId, o);

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }


    public static void getGasPrice() {
        /** interval get gasprice **/
        HttpClient.Builder
                .getEthScanServer()
                .getGasPrice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<EtherScanJsonrpcResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(EtherScanJsonrpcResponse e) {
                                BigInteger gasPrice = e.getDecimalFromDex();
                                OCPWallet.stGasPrice = gasPrice;
                                MyLog.i("gasprice" + gasPrice.toString());
                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }
}
