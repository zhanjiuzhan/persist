package com.jcpl.persist.impl;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.HelpMqListenerService;
import com.jcpl.persist.Publish;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class HelpMqListenerServiceImpl implements HelpMqListenerService, ApplicationContextAware {

    private Map<String, Publish> publishMap = new HashMap<>(1);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            HelpMessage msg = (HelpMessage) new SimpleMessageConverter().fromMessage(message);
            consumeMessage(msg);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // 消费失败信息应该被记录 TODO
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

    @Override
    public void consumeMessage(final com.jcpl.persist.Message msg) {
        publishMap.forEach((beanName, publish)-> publish.publish(msg));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            publishMap = applicationContext.getBeansOfType(Publish.class);
        }
    }
}
