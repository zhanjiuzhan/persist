package com.jcpl.persist;

import javax.websocket.Session;

/**
 * 用户通讯
 * @author Administrator
 */
public interface SocketService {

    /**
     * 服务器和用户成功创建一个连接
     * @param relationId
     * @param session
     * @return
     */
    void openConnect(String relationId, Session session);

    /**
     * 服务器和用户关闭一个连接
     * @param relationId
     */
    void closeConnect(String relationId);

    /**
     * 发送信息
     * @param session
     * @param message
     */
    void sendMessage(Session session, String message);

    /**
     * 接受一个信息
     * @param message
     * @param session
     */
    void receiveMessage(String message, Session session);
}
