package com.ocpay.wallet;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.ocpay.wallet.http.HttpUtils;
import com.ocpay.wallet.http.client.DataBlockClientIml;

import java.math.BigInteger;
import java.util.Locale;

import static com.ocpay.wallet.utils.CurrencyUtils.CNY;
import static com.ocpay.wallet.utils.CurrencyUtils.USD;

/**
 * Created by y on 2018/4/16.
 */

public class MyApp extends Application {


    static public BigInteger ethBlockNumber;

    static private Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        Locale.setDefault(Locale.ENGLISH);
        ethBlockNumber = new BigInteger("99999999");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        HttpUtils.getInstance().init(mContext, true);

//        initEthInfo();

    }

    private void initEthInfo() {
        //update rate
        DataBlockClientIml.getPairOCN_ETH(-1);
        //update ocn_eth
        DataBlockClientIml.getRate(USD,CNY);


    }


    public static Context getContext() {
        return mContext;

    }


    public static BigInteger getEthBlockNumber() {
        return ethBlockNumber;
    }

    public static void setEthBlockNumber(BigInteger ethBlockNumber) {
        MyApp.ethBlockNumber = ethBlockNumber;
    }


}
