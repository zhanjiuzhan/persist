package com.jcpl.persist;

/**
 * 消息服务
 * @author chenglei
 */
public interface MessageService {

    /**
     * 发送信息
     * @param message
     * @param <T>
     */
    <T extends Message> void sendMessage(T message);
}
