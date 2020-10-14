package com.jcpl.persist;

/**
 * @author Administrator
 */
public class User {

    /**
     * 用户标志 唯一的
     */
    private String username;

    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
