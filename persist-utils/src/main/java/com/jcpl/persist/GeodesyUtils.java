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
public interface GeodesyUtils {

    static double getSphereDistance(final double sLatitude, final double sLongitude, final double eLatitude, final double eLongitude) {
        return getDistance(sLatitude, sLongitude, eLatitude, eLongitude, Ellipsoid.Sphere);
    }

    public static double getWGS84Distance(final double sLatitude, final double sLongitude, final double eLatitude, final double eLongitude) {
        return getDistance(sLatitude, sLongitude, eLatitude, eLongitude, Ellipsoid.WGS84);
    }

    static double getDistance(final double sLatitude, final double sLongitude, final double eLatitude, final double eLongitude, Ellipsoid ellipsoid) {
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(
                ellipsoid,
                new GlobalCoordinates(sLatitude, sLongitude),
                new GlobalCoordinates(eLatitude, eLongitude));
        double distance = geoCurve.getEllipsoidalDistance();
        BigDecimal b = new BigDecimal(distance);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
