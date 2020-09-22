package com.jcpl.persist;

import javax.websocket.Session;

/**
 * 用户通讯
 * @author Administrator
 */
public interface SocketService {

    void openConnect(Session session);

    void closeConnect(Session session);

    /**
     * 发送信息
     * @param message
     */
    void sendMessage(Message message);

    /**
     * 接受一个信息
     * @param message
     */
    void receiveMessage(Message message);
}
