package com.jcpl.persist;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Administrator
 */
public interface SocketService extends ChannelAwareMessageListener {
    LinkedBlockingDeque<Message> queue = new LinkedBlockingDeque<>(1000);
}
