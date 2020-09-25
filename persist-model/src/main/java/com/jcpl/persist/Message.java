package com.jcpl.persist;

import java.io.Serializable;

/**
 * 消息
 * @author chenglei
 */
public interface Message extends Serializable {
    /**
     * 取得消息类型
     * @return
     */
    int getType();
}
