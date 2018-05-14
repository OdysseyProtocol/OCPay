package com.ocpay.wallet.utils;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.bean.Contact;
import com.snow.commonlibrary.utils.PrefUtils;

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
    public static void putContacts(List<Contact> list) {
        if (list == null || list.size() < 0) return;
        PrefUtils.putBean(MyApp.getContext(), Constans.PREFKEY.PREF_KEY_CONTACTS, list);

    }


}
