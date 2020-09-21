package com.jcpl.persist;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;

/**
 * 位置距离计算
 * @author Administrator
 */
public class GeodesyUtils {

    public static double getSphereDistance(final double sLatitude, final double sLongitude, final double eLatitude, final double eLongitude) {
        return getDistance(sLatitude, sLongitude, eLatitude, eLongitude, Ellipsoid.Sphere);
    }

    public static double getWGS84Distance(final double sLatitude, final double sLongitude, final double eLatitude, final double eLongitude) {
        return getDistance(sLatitude, sLongitude, eLatitude, eLongitude, Ellipsoid.WGS84);
    }

    private static double getDistance(final double sLatitude, final double sLongitude, final double eLatitude, final double eLongitude, Ellipsoid ellipsoid) {
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(
                ellipsoid,
                new GlobalCoordinates(sLatitude, sLongitude),
                new GlobalCoordinates(eLatitude, eLongitude));
        double distance = geoCurve.getEllipsoidalDistance();
        BigDecimal b = new BigDecimal(distance);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println("Sphere坐标系计算结果：" + getSphereDistance(113.3317, 23.156019, 113.342439, 23.15661) + "米");
        System.out.println("WGS84坐标系计算结果：" + getWGS84Distance(113.3317, 23.156019, 113.342439, 23.15661) + "米");
    }
}
