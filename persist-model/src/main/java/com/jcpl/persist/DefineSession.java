package com.jcpl.persist;

import javax.websocket.Session;

/**
 * @author Administrator
 */
public class DefineSession {

    private String relationId;
    private String username;
    private double latitude;
    private double longitude;
    private Session session;

    public DefineSession() {}

    public DefineSession(String relationId, String username, Session session) {
        this.username = username;
        this.relationId = relationId;
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
