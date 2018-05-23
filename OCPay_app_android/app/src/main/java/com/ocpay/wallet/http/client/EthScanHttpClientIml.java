package com.ocpay.wallet.http.client;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.OCPPrefUtils;
import com.ocpay.wallet.utils.TokenUtils;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.web3j.response.EtherScanJsonrpcResponse;
import com.ocpay.wallet.utils.web3j.response.EtherScanTxListResponse;
import com.ocpay.wallet.utils.web3j.response.TokenBalanceResponse;
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


    public static void getEthBalanceOf(final String address, final String tokenName, final boolean isUpdate) {
        HttpClient.Builder
                .getEthScanServer()
                .getEthBalance(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<TokenBalanceResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(TokenBalanceResponse o) {
                                //save
                                if (isUpdate) {
                                    o.setTokenName(tokenName);
                                    RxBus.getInstance().post(Constans.RXBUS.ACTION_TOKEN_BALANCE_UPDATE, o);
                                } else {
                                    //update sql
                                    if (o != null && o.getEthBalance() != null) {
                                        WalletInfo walletInfo = WalletInfoDaoUtils.sqlByAddress(MyApp.getContext(), address);
                                        if (walletInfo == null) return;
                                        walletInfo.setEthBalance(o.getEthBalance().toString());
                                        WalletInfoDaoUtils.update(MyApp.getContext(), walletInfo);
                                    }
                                }


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

    public static void getTokenBalanceOf(String address, final String tokenName) {
        if (TokenUtils.ETH.equals(tokenName)) {
            getEthBalanceOf(address, tokenName, false);
            return;
        }

        String data = OWalletTransaction.getBalanceOfTokenData(address);
        HttpClient.Builder
                .getEthScanServer()
                .getTokenBalance(TokenUtils.getTokenAddress(tokenName), data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<TokenBalanceResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(TokenBalanceResponse o) {
                                o.setTokenName(tokenName);
                                RxBus.getInstance().post(Constans.RXBUS.ACTION_TOKEN_BALANCE_UPDATE, o);
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

    public static void getTransactionList(final int requestId, String address, String startBlock, String endBlock) {

        HttpClient.Builder
                .getEthScanServer()
                .getEthTransactionList(address, startBlock, endBlock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<EtherScanTxListResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(EtherScanTxListResponse o) {
                                RxBus.getInstance().post(requestId, o);
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


    public static void getBlockNumber(final int requestId) {
        HttpClient.Builder
                .getEthScanServer()
                .getEthBlockNumber()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<EtherScanJsonrpcResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(EtherScanJsonrpcResponse o) {
                                if (o != null && requestId == Constans.RXBUS.ACTION_RECORD_BLOCK_NO) {
                                    BigInteger blockNo = o.getDecimalFromDex();
                                    if (blockNo != null && blockNo.longValue() > 0) {
                                        OCPPrefUtils.hasRecordFirstBlock(true);
                                        OCPPrefUtils.setFirstStartBlockNo(blockNo.toString());
                                    }
                                }
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
