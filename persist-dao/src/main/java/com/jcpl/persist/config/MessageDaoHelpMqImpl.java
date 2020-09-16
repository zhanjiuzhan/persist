package com.jcpl.persist.config;

import com.jcpl.persist.Message;
import com.jcpl.persist.MessageDao;
import com.jcpl.persist.MqConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 发送一个信息到 mq中
 * @author chenglei
 */
@Repository
public class MessageDaoHelpMqImpl implements MessageDao {

    private final static Logger logger = LoggerFactory.getLogger(MessageDaoHelpMqImpl.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public <T extends Message> void sendMessage(T message) {
        rabbitTemplate.convertAndSend(MqConst.SHOWER_EXCHANGE, MqConst.HELP_MQ, message, new CorrelationData(System.currentTimeMillis()+""));
    }
}
