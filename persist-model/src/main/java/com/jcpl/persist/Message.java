package com.jcpl.persist;

import java.io.Serializable;

/**
 * 消息
 * @author chenglei
 */
public interface Message extends Serializable {

    /**
     * 取得消息内容
     * @return
     */
    String getContent();
}
