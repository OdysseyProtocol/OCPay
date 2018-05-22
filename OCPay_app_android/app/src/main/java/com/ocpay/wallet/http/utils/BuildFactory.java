package com.ocpay.wallet.http.utils;


import com.ocpay.wallet.http.HttpUtils;

import static com.ocpay.wallet.Constans.HTTP.API_DATA_BLOCK;
import static com.ocpay.wallet.Constans.HTTP.API_ETHSCAN;

public class BuildFactory {

    private static BuildFactory instance;
    private Object ethscanHttps;
    private Object datablockHttps;


    public static BuildFactory getInstance() {
        if (instance == null) {
            synchronized (BuildFactory.class) {
                if (instance == null) {
                    instance = new BuildFactory();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> a, String type) {
        switch (type) {
            case API_ETHSCAN:
                if (ethscanHttps == null) {
                    synchronized (BuildFactory.class) {
                        if (ethscanHttps == null) {
                            ethscanHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
                        }
                    }
                }
                return (T) ethscanHttps;
            case API_DATA_BLOCK:
                if (datablockHttps == null) {
                    synchronized (BuildFactory.class) {
                        if (datablockHttps == null) {
                            datablockHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
                        }
                    }
                }
                return (T) datablockHttps;
        }
        return null;
    }

}
