package com.jcpl.persist;

/**
 * 发布信息
 * @author chenglei
 */
public interface MessageDao {

    /**
     * 发送信息
     * @param mqName
     * @param message
     * @param <T>
     */
    <T extends Message> void sendMessage(String mqName, T message);
}
