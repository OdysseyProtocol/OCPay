package com.stormfives.ocpay.token.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by zxb on 28/09/2016.
 */
public class DateConvert {

    //将date转成可读的string字符串
    public static String dateConvert(String date){
        if (date != null) {
            date = date.substring(0, 19);
        }
        return date;
    }

    public static String toBeijingTime(String greenwichTime){

        String dateStr = greenwichTime.substring(0,10) + " "+ greenwichTime.substring(11,19);

        String beijingTime = "" ;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            Date dateTmp = dateFormat.parse(dateStr);
            beijingTime = format.format(dateTmp);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return beijingTime;
    }

}
