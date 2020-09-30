package com.jcpl.persist;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布信息
 * @author chenglei
 */
public interface MessageDao<T extends BaseMessage> {

    /**
     * 发送信息
     * @param message
     */
    void sendMessage(T message);

    /**
     * 查询所有的信息
     *
     * @return
     */
    default List<T> gets() {
        return new ArrayList<>(0);
    }
}
