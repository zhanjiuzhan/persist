package com.jcpl.persist.impl;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.SocketService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class SocketServiceImpl implements SocketService {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String msg = message.toString();
            String[] msgArray = msg.split("'");
            SocketService.queue.offer(new HelpMessage(msgArray[1]));
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
}
