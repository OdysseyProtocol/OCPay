package com.ocpay.wallet.greendao.manager;


import android.content.Context;

import com.ocpay.wallet.greendao.WalletInfo;

import java.util.List;

import static com.ocpay.wallet.greendao.manager.DaoManager.getInstance;


/**
 * Created by y on 2017/11/10.
 */

public class WalletInfoDaoUtils {

    /**
     * 增
     *
     * @param foolBean
     * @return
     */
    public static boolean insertFool(WalletInfo foolBean, Context mContext) {
        long result = getInstance(mContext).getDaoSeesion().insert(foolBean);
        return result > 0;
    }

    /**
     * 删
     *
     * @param foolBean
     */
    public static void dealFool(WalletInfo foolBean, Context mContext) {
        getInstance(mContext).getDaoSeesion().delete(foolBean);
    }

    /**
     * 改
     *
     * @param foolBean
     */
    public static void update(WalletInfo foolBean, Context mContext) {
        getInstance(mContext).getDaoSeesion().update(foolBean);
    }

    /**
     * 查
     *
     * @return
     */
    public static List<WalletInfo> sqlAll(Context mContext) {
        return getInstance(mContext).getDaoSeesion().loadAll(WalletInfo.class);
    }
}
