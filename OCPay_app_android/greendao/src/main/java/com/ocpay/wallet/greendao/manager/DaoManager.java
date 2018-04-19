package com.ocpay.wallet.greendao.manager;


import android.content.Context;

import com.ocpay.wallet.greendao.gen.DaoMaster;
import com.ocpay.wallet.greendao.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by y on 2017/11/10.
 */

public class DaoManager {
    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "OCPWallet";
    private Context context;

    //多线程中要被共享的使用volatile关键字修饰
    private volatile static DaoManager daoManager;

    private DaoMaster mDaoMaster;

    public static DaoManager getInstance(Context context) {
        if (daoManager == null) {
            daoManager = new DaoManager(context);
        }
        return daoManager;
    }


    public DaoManager(Context context) {
        this.context = context;
    }

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 获得数据库
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    private DaoSession mDaoSession;

    /**
     * 获得session执行增删改查
     * @return
     */
    public DaoSession getDaoSeesion() {
        if (mDaoSession == null) {
            if(mDaoMaster ==null){
                mDaoMaster =getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return  mDaoSession;
    }


    /**
     * 打开输出日志，默认关闭
     */
    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }


    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    private void closeDaoSession() {
        if(mDaoSession!=null){
            mDaoSession.clear();
            mDaoSession =null;
        }
    }

    private void closeHelper() {


    }




}
