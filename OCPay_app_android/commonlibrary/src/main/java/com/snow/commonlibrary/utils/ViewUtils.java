package com.snow.commonlibrary.utils;

import android.content.Context;

/**
 * Created by y on 2017/11/22.
 */

public class ViewUtils {
    public static int dp2px(Context context, int dp) {
        if (context == null) return dp;
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5);
    }
}
