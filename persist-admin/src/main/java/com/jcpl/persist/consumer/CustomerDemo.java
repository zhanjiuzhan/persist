package com.jcpl.persist.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 接受消息
 * @author chenglei
 */
@Component
public class CustomerDemo implements ChannelAwareMessageListener {

    /*
    @RabbitHandler
    public <T extends Message> void receiveMessage(T message) {
        System.out.println(message);
    }*/

    @Override
    public void onMessage(org.springframework.amqp.core.Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            //可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            String[] msgArray = msg.split("'");
            System.out.println(msgArray[1]);
            System.out.println("消费的主题消息来自: " + message.getMessageProperties().getConsumerQueue());
            channel.basicAck(deliveryTag, true);
            //为true会重新放回队列
            //channel.basicReject(deliveryTag, true);
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
}
