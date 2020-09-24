package com.jcpl.persist.config.mq;

import com.jcpl.persist.MessageService;
import com.jcpl.persist.MqConst;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 消费者的mq配置
 * @author chenglei
 */
@Configuration
public class MqConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Resource(name="messageServiceHelpMqImpl")
    private MessageService messageService;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置一个队列
        container.setQueueNames(MqConst.HELP_MQ);

        container.setMessageListener(messageService);
        return container;
    }

}
