package com.jcpl.persist.impl;

import com.jcpl.persist.*;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息服务实现
 * @author chenglei
 */
@Service
public class MessageServiceHelpMqImpl implements MessageService, ChannelAwareMessageListener, ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(MessageServiceHelpMqImpl.class);
    private Map<String, Publish> publishMap = new HashMap<>(1);

    @Resource(name = "messageDaoHelpMqImpl")
    private MessageDao messageDao;

    @Override
    public <T extends Message> void sendMessage(T message) {
        messageDao.sendMessage(message);
    }

    @Override
    public void receiveMessage(final Message msg) {
        publishMap.forEach((beanName, publish)-> publish.publish(msg));
    }

    @Override
    public void onMessage(org.springframework.amqp.core.Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            HelpMessage msg = (HelpMessage) new SimpleMessageConverter().fromMessage(message);
            receiveMessage(msg);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // 消费失败信息应该被记录 TODO
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            publishMap = applicationContext.getBeansOfType(Publish.class);
        }
    }
}