package com.ocpay.wallet.utils;

import android.content.Context;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.bean.Contact;
import com.ocpay.wallet.greendao.WalletInfo;
import com.snow.commonlibrary.utils.PrefUtils;
import com.snow.commonlibrary.utils.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by y on 2018/5/11.
 */

public class OCPPrefUtils {


    /**
     * @return
     */
    public static List<Contact> getContacts() {
        List<Contact> list = (List<Contact>) PrefUtils.getBean(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_CONTACTS);
        if (list == null) {
            return new ArrayList<Contact>();
        }
        return list;
    }


    /**
     * @param list
     */
    public static void setContacts(List<Contact> list) {
        if (list == null || list.size() < 0) return;
        PrefUtils.putBean(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_CONTACTS, list);

    }

    /**
     * @return
     */
    public static String getCurrency() {
        return PrefUtils.getString(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_CURRENCY, "CNY");
    }

    /**
     * @param currency
     */
    public static void setCurrency(String currency) {
        if (StringUtil.isEmpty(currency)) return;
        PrefUtils.putString(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_CURRENCY, currency);

    }


    public static String getLanguage(Context context) {
        return PrefUtils.getString(context, Constans.PREFKEY.APP_LANGUAGE, "English");
    }

    public static void setLanguage(Context context, String language) {
        PrefUtils.putString(context, Constans.PREFKEY.APP_LANGUAGE, language);
    }


    public static void setCurrentWallet(WalletInfo data) {
        PrefUtils.putBean(MyApp.getContext(), Constans.WALLET.CURRENT_WALLET, data);
    }


    public static WalletInfo getCurrentWallet() {
        return (WalletInfo) PrefUtils.getBean(MyApp.getContext(), Constans.WALLET.CURRENT_WALLET);
    }


    public static void setTokenPrice(String tokenName, BigDecimal b) {
        PrefUtils.setDouble(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_TOKEN_PRICE + tokenName, b.doubleValue());
    }


    public static BigDecimal getTokenPrice(String tokenName) {
        return new BigDecimal(PrefUtils.getDouble(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_TOKEN_PRICE + tokenName, 0));
    }


    public static void setRatePairs(String base, String to, BigDecimal b) {
        PrefUtils.setDouble(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_TOKEN_PRICE + base + "_" + to, b.doubleValue());
    }


    public static BigDecimal getRatePairs(String base, String to) {
        return new BigDecimal(PrefUtils.getDouble(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_TOKEN_PRICE + base + "_" + to, 0));
    }


    // record first block No
    public static void setFirstStartBlockNo(String blockNo) {
        PrefUtils.putString(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_FIRST_BLOCK_NO, blockNo);
    }


    public static String getFirstStartBlockNo() {
        return PrefUtils.getString(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_FIRST_BLOCK_NO, "0");
    }

    //has record first block No
    public static void hasRecordFirstBlock(boolean has) {
        PrefUtils.putBoolean(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_HAS_RECORD_B_N, has);

    }

    public static boolean isRecordFirstBlock() {
        return PrefUtils.getBoolean(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_HAS_RECORD_B_N, false);
    }

}
