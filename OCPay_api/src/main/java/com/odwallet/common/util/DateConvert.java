package com.odwallet.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by zxb on 28/09/2016.
 */
public class DateConvert {

    public static String toBeijingTime(String greenwichTime) {

        String dateStr = greenwichTime.substring(0, 10) + " " + greenwichTime.substring(11, 19);

        String beijingTime = "";
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

    /**
     * @author lyc
     * 将字符串转为Date
     */

    public static final String format1 = "yyyy-MM-dd";
    public static final String format2 = "yyyy-MM-dd HH:mm:ss";
    public static final String format3 = "HH:mm:ss";
    public static final String format4 = "yyyy-MM-dd HH";
    public static final String format5 = "yyyyMMddHHmmss";
    public static final String format6 = "yyyy年MM月dd日";
    public static final String format7 = "yyyyMMddHHmm";


    public static long convertToLongTime(String date, String format) {
        Date retValue = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            retValue = sdf.parse(date);
            return retValue.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static Date convertToDate(String date, String format) {
        Date retValue = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            retValue = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retValue;
    }


    public static Date convertToDate(String date) {
        Date retValue = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format2);
        try {
            retValue = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retValue;
    }


    /**
     * Date 转字符串
     */
    public static String convertToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(format2);
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * 时间增加指定ms
     *
     * @param date
     * @param addMillis
     * @return
     */
    public static String dayAddMillis(Date date, long addMillis) {
        Date newDate = new Date(date.getTime() + addMillis);
        return convertToString(newDate);
    }


    public static String convertToString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String formatNow(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }


    /**
     * 事件类型比较
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算当前时间到24点的时间
     *
     * @return
     */
    public static long timeTo24() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal);
        final long diff = cal.getTimeInMillis() - System.currentTimeMillis();
        return diff;

    }

    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    public static java.util.Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    public static final int daysBetween(Date early, Date late) {

        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calst.set(java.util.Calendar.MINUTE, 0);
        calst.set(java.util.Calendar.SECOND, 0);
        caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
        caled.set(java.util.Calendar.MINUTE, 0);
        caled.set(java.util.Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    /**
     * @param date1 <String>
     * @param date2 <String>
     * @return int
     * @throws ParseException
     */
    public static int getMonthSpace(String date1, String date2)
            throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }

    public static final int secondsBetween(Date early, Date late) {

        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //得到两个日期相差的天数
        int seconds = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000));

        return seconds;
    }
}
