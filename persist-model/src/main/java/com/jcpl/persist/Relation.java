package com.jcpl.persist;

/**
 * 链接关系
 * @author Administrator
 */
public class Relation {
    /**
     * 用户名 (用户标志)
     */
    private String username;

    /**
     * 链接状态 0 等待链接  1 链接中
     */
    private int status;

    /**
     * 链接创建时间
     */
    private String createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "username='" + username + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
