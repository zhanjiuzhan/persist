package com.jcpl.persist;

import com.alibaba.fastjson.JSON;

/**
 * 链接心跳
 * @author Administrator
 */
public class SocketHeartbeat {

    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public static void main(String[] args) {
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setType(0);
        SocketHeartbeat heartbeat = new SocketHeartbeat();
        heartbeat.setHeart(1);
        heartbeat.setLatitude(12.442);
        heartbeat.setLongitude(54.452);
        heartbeat.setUserId("934a9e5a57124aa4ad52d797bd9c6107");
        socketMessage.setContent(JSON.toJSONString(heartbeat));
        System.out.println(JSON.toJSONString(socketMessage));
    }
}
