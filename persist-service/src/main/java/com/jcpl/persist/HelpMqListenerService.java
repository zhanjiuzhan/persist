package com.jcpl.persist;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * @author Administrator
 */
public interface HelpMqListenerService extends ChannelAwareMessageListener {

    /**
     * 消费信息
     * @param msg
     */
    void consumeMessage(Message msg);
}
