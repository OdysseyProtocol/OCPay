package com.ocpay.wallet.http.utils;


import com.ocpay.wallet.http.HttpUtils;

import static com.ocpay.wallet.Constans.HTTP.API_ETHSCAN;

public class BuildFactory {

    private static BuildFactory instance;
    private Object gankHttps;
    private Object doubanHttps;
    private Object dongtingHttps;
    private Object firHttps;
    private Object wanAndroidHttps;
    private Object nhdzHttps;
    private Object qsbkHttps;

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
                if (gankHttps == null) {
                    synchronized (BuildFactory.class) {
                        if (gankHttps == null) {
                            gankHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
                        }
                    }
                }
                return (T) gankHttps;
//            case API_DOUBAN:
//                if (doubanHttps == null) {
//                    synchronized (BuildFactory.class) {
//                        if (doubanHttps == null) {
//                            doubanHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
//                        }
//                    }
//                }
//                return (T) doubanHttps;
//            case API_TING:
//                if (dongtingHttps == null) {
//                    synchronized (BuildFactory.class) {
//                        if (dongtingHttps == null) {
//                            dongtingHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
//                        }
//                    }
//                }
//                return (T) dongtingHttps;
//            case API_FIR:
//                if (firHttps == null) {
//                    synchronized (BuildFactory.class) {
//                        if (firHttps == null) {
//                            firHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
//                        }
//                    }
//                }
//                return (T) firHttps;
//            case API_WAN_ANDROID:
//                if (wanAndroidHttps == null) {
//                    synchronized (BuildFactory.class) {
//                        if (wanAndroidHttps == null) {
//                            wanAndroidHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
//                        }
//                    }
//                }
//                return (T) wanAndroidHttps;
//            case API_NHDZ:
//                if (nhdzHttps == null) {
//                    synchronized (BuildFactory.class) {
//                        if (nhdzHttps == null) {
//                            nhdzHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
//                        }
//                    }
//                }
//                return (T) nhdzHttps;
//            case API_QSBK:
//                if (qsbkHttps == null) {
//                    synchronized (BuildFactory.class) {
//                        if (qsbkHttps == null) {
//                            qsbkHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
//                        }
//                    }
//                }
//                return (T) qsbkHttps;
//            default:
//                if (gankHttps == null) {
//                    synchronized (BuildFactory.class) {
//                        if (gankHttps == null) {
//                            gankHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
//                        }
//                    }
//                }
//                return (T) gankHttps;
        }
        return null;
    }

}
