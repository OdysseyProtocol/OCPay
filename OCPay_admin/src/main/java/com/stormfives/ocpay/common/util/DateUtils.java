package com.stormfives.ocpay.common.util;


import com.stormfives.ocpay.common.util.entity.TimeInterval;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateUtils {

    private static String defaultDatePattern = "yyyy-MM-dd";

    private static String yMdHmsDatePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * 返回预设Format的当前日期字符串
     */
    public static String getToday() {
        Date today = new Date();
        return format(today);
    }



    public  static String longToStrDate(String timeStamp){
        try {
            Date  date = timeStamp2Date(timeStamp);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yMdHmsDatePattern);
            return  simpleDateFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
      return  null;
    }





    /**
     * 使用预设Format格式化Date成字符串
     */
    public static String formatYMDHMS(Date date) {
        return date == null ? " " : format(date, yMdHmsDatePattern);
    }

    /**
     * 使用预设Format格式化Date成字符串
     */
    public static String format(Date date) {
        return date == null ? " " : format(date, getDatePattern());
    }

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }


    /**
     * 在日期上增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    public static String getLastDayOfMonth(String year, String month) {
        Calendar cal = Calendar.getInstance();
        // 年
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        // 月，因为Calendar里的月是从0开始，所以要-1
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        // 日，设为一号
        cal.set(Calendar.DATE, 1);
        // 月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1);
        // 下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
    }


    public static Date addDay(Date date, int n) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, n);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }

    public static Date getDayWithAssignTime(int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        Date date = c.getTime();
        return date;
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException {
        return StringUtils.isBlank(strDate) ? null : parse(strDate,
                getDatePattern());
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException {
        return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
                pattern).parse(strDate);
    }

    public static Integer getAge(Date birthday) {
        Calendar calendar = new GregorianCalendar();
        Date now = new Date();
        calendar.setTime(now);
        int nowYear = calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        int birthdayYear = calendar.get(Calendar.YEAR);

        return nowYear - birthdayYear;
    }

    public static Date addMinute(Date time, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MINUTE, minute);
        Date date = c.getTime();
        return date;
    }

    public static String formatStringDate(String time) {
        if (time == null) return null;
        return time.substring(0, 10);
    }

    /**
     * 获取指定时间的前一天
     *
     * @param date   日期
     * @param format 日期格式
     * @return
     */
    public static String getYesterday(Date date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return format(date, format);
    }

    /**
     * 生成指定时间跨度的日期对象
     *
     * @param source
     * @param diffWithSource
     * @param interval
     * @return 保留日期
     */
    public static TimeInterval createDateInterval(Long source, Integer diffWithSource, Integer interval) {
        TimeInterval timeInterval = new TimeInterval();
        Date sourceDate = new Date(source);//源时间
        Date sourceDateZeroOclock = org.apache.commons.lang.time.DateUtils.truncate(sourceDate, Calendar.DATE);//保留日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(sourceDateZeroOclock);
        cal.add(Calendar.DATE, diffWithSource);//相差天数
        timeInterval.setStartTime(cal.getTime());
        cal.add(Calendar.DATE, interval);//时间跨度
        timeInterval.setEndTime(cal.getTime());
        return timeInterval;
    }


    /**
     * 获取当前时间的前几天或者后几天
     *
     * @param date 当前时间
     * @param days 要增加或减少的天数，正数为当前日期以后，负数为当前日期之前
     * @return
     */
    public static Date getCurrentBeforeOrAfter(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        date = calendar.getTime();
        return date;
    }

    /**
     * 判断两个时间段是否有交集
     *
     * @param startTime
     * @param endTime
     * @param start
     * @param end
     * @return
     */
    public static Boolean checkTwoPeriodTimeHaveIntersection(Date startTime, Date endTime, Date start, Date end) {
        if ((startTime.getTime() <= start.getTime()) && endTime.getTime() >= start.getTime()) {
            return true;
        } else if ((startTime.getTime() >= start.getTime()) && startTime.getTime() <= end.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getEnglishDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        return df.format(date);
    }

    /**
     * 截取日期
     *
     * @param date
     * @return
     */
    public static Date truncateDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        return org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }


    /**
     * 时间戳转换成 Date
     * @param timeStamp
     * @return
     * @throws Exception
     */
    public static Date timeStamp2Date(String timeStamp) throws Exception {
        try {
            Long t = Long.valueOf(timeStamp);
            Timestamp timestamp = new Timestamp(t);
            return new Date(timestamp.getTime());
        } catch (Exception e) {
            throw new Exception("parse err");
        }
    }




}
