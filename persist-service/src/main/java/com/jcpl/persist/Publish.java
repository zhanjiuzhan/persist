package com.jcpl.persist;

/**
 * @author Administrator
 */
public interface Publish {

    /**
     * 发布信息
     * @param message
     * @return
     */
    boolean publish(BaseMessage message);
}
