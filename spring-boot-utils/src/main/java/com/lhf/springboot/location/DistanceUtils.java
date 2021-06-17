package com.lhf.springboot.location;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName DistanceUtils
 * @Desc 根据经纬度计算距离
 * @Author liuhefei
 * @Date 2021/5/26 16:05
 **/
public class DistanceUtils {

    /**
     * 根据经纬度计算距离
     *
     * @param lon1 经度1
     * @param lat1 纬度1
     * @param lon2 经度2
     * @param lat2 纬度2
     * @return 距离 （km）
     */
    public static String getDistance(String lon1, String lat1, String lon2, String lat2) {
        GlobalCoordinates source = new GlobalCoordinates(Double.parseDouble(lat1), Double.parseDouble(lon1));
        GlobalCoordinates target = new GlobalCoordinates(Double.parseDouble(lat2), Double.parseDouble(lon2));
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target);
        double distance = geoCurve.getEllipsoidalDistance();
        BigDecimal distanceBig = new BigDecimal(distance).setScale(2, RoundingMode.UP);
        distanceBig = distanceBig.multiply(new BigDecimal("0.001")).setScale(2, RoundingMode.UP);
        return distanceBig.toString().concat("km");
    }

    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);

        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }

    public static void main(String[] args) {
        String lon1 = "119.6438888888889";
        String lat1 = "37.966944444444444";
        String lon2 = "119.63916666666667";
        String lat2 = "37.9675";
        System.out.println(getDistance(lon1, lat1, lon2, lat2));
        System.out.println("经纬度距离计算结果：" + getDistance(lon1, lat1, lon2, lat2));

        System.out.println("经纬度距离计算结果：" + getDistance(109.371319, 22.155406, 108.009758, 21.679011) + "米");

    }

}
