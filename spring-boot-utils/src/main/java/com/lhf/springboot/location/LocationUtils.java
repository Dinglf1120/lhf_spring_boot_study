package com.lhf.springboot.location;

/**
 * @ClassName LocationUtils
 * @Desc 计算两个经纬度之间的距离
 * @Author liuhefei
 * @Date 2021/5/26 14:40
 **/
public class LocationUtils {

    // 赤道半径(单位km)
    private static double EARTH_RADIUS_KM = 6378.137;
    // 赤道半径(单位m)
    private static final  double EARTH_RADIUS_M = 6378137;


    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS_M;
        s = Math.round(s * 10000d) / 10000d;
        //s = s*1000;
        return s;
    }

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,
     * 计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     * @param lon1 第一点的经度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的经度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位km
     * */
    public static double getDistance1(double lon1,double lat1,double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS_KM;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


    public static void main(String[] args) {
        double longitude1 = 103.2528656;
        double latitude1 = -4.01888606;

        double longitude2 = 107.55866544;
        double latitude2 = -6.89490358;

        double distance = getDistance(longitude1, latitude1, longitude2, latitude2);
        System.out.println("相距：" + distance + "米");

        double distance1 = getDistance1(longitude1, latitude1, longitude2, latitude2);
        System.out.println("相距：" + distance1 + "千米");
    }

}
