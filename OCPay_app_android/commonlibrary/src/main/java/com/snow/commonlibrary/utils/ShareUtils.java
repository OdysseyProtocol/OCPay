package com.snow.commonlibrary.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by y on 2018/5/1.
 */

public class ShareUtils {

    public static void toShare(Activity aCtivity, String content, String title) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, content);
        aCtivity.startActivity(Intent.createChooser(i, title));

    }
}
