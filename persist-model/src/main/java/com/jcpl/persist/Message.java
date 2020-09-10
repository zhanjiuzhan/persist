package com.jcpl.persist;

import java.io.Serializable;

/**
 * 消息
 * @author chenglei
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -5552799986757429425L;
    /**
     * 消息的持有者
     */
    private String holder;

    /**
     * 消息的内容
     */
    private String content;

    /**
     * 消息发送的时间
     */
    private String time;

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
