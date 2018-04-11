package com.odwallet.common.util;

/**
 * Created with IntelliJ IDEA.
 * User: LuoYuanchun
 * Date: 17/12/6
 * Time: 下午10:07
 */
public class MapUtils {

    /**
     * 根据经纬度计算两点之间的距离
     * @return 距离 单位 米
     * @since JDK 1.6
     */
    public static double calculateDistance(double longitudeA, double latitudeA,double longitudeB, double latitudeB) {
        double radLat1 = getRadian(latitudeA);
        double radLat2 = getRadian(latitudeB);
        double a = radLat1 - radLat2;// 两点纬度差
        double b = getRadian(longitudeA) - getRadian(longitudeB);// 两点的经度差
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378.137;
        return s * 1000;
    }

    /**
     * 角度弧度计算公式 rad:(). <br/>
     * 360度=2π π=Math.PI
     * x度 = x*π/360 弧度
     * @author chiwei
     * @param degree
     * @return
     * */
    public static double getRadian(double degree) {
        return degree * Math.PI / 180.0;
    }

}
