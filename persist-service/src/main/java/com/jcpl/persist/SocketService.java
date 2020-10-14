package com.jcpl.persist;

import javax.websocket.Session;

/**
 * 用户通讯
 * @author Administrator
 */
public interface SocketService {

    /**
     * 初始化操作
     */
    void init();

    /**
     * 链接断开的操作
     * @param session
     */
    void destroy(Session session);

    /**
     * 接受一个信息
     * @param message
     * @param session
     */
    void receiveMessage(SocketMessage message, SocketSession session);
}
