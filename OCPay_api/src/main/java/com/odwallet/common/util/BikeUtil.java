package com.odwallet.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maniac on 2017-02-19.
 */
public class BikeUtil {


    public static final String ymdDatePattern = "yyyy-MM-dd";

    public static final String yMdHmsDatePattern = "yyyy-MM-dd HH:mm:ss";





    public static boolean isDataBetweenStartEnd(Date date, String start, String end) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        Date now = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(date));
            beginTime = df.parse(start);
            endTime = df.parse(end);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boolean flag = belongCalendar(now, beginTime, endTime);
        return flag;
    }


    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        Date beginTimeToCompare = new Date(beginTime.getTime()-1);
        begin.setTime(beginTimeToCompare);
        Calendar end = Calendar.getInstance();
        Date endTimeToCompare = new Date(endTime.getTime()+1);
        end.setTime(endTimeToCompare);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }



    /**
     *  生成4位数字验证码
     * @return
     */
    public static String produceVerify() {

        String rt_vercode = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(Rand.RandNum(10));
            rt_vercode += rand;
        }

        return rt_vercode;
    }






}
