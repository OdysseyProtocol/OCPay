package com.ocpay.wallet;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.ocpay.wallet.http.HttpUtils;

/**
 * Created by y on 2018/4/16.
 */

public class MyApp extends Application {


    static private Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        HttpUtils.getInstance().init(mContext, true);
    }


    public static Context getContext() {
        return mContext;

    }
}
