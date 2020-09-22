package com.jcpl.persist;

/**
 * 链接心跳
 * @author Administrator
 */
public class Heartbeat {

    /**
     * 心跳数
     */
    private int heart;

    /**
     * 经度
     */
    private double latitude;

    /**
     * 维度
     */
    private double longitude;

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
