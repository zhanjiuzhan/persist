package com.jcpl.persist;

import java.util.List;

/**
 * 消息服务
 * @author chenglei
 */
public interface MessageService<T> {

    /**
     * 发送信息
     * @param message
     */
    void sendMessage(T message);

    /**
     * 接受一个信息
     * @param msg
     */
    void receiveMessage(BaseMessage msg);

    /**
     * 取得所有的求助信息
     * @return
     */
    List<T> gets();
}
