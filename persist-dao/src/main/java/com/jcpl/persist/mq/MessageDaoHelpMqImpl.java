package com.jcpl.persist.mq;

import com.alibaba.fastjson.JSON;
import com.jcpl.persist.Message;
import com.jcpl.persist.MessageDao;
import com.jcpl.persist.MqConst;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 发送一个信息到 mq中
 * @author chenglei
 */
@Repository
@RabbitListener(queues = MqConst.HELP_MQ)
public class MessageDaoHelpMqImpl implements MessageDao {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public <T extends Message> void sendMessage(T message) {
        rabbitTemplate.convertAndSend(MqConst.SHOWER_EXCHANGE, MqConst.HELP_MQ, message);
    }

    @Override
    public <T extends Message> T receiveMessage(Class<T> t) {
        T o = JSON.parseObject("", t);
        return o;
    }

    @RabbitHandler
    private <T extends Message> void receiveMessage(T message) {
        System.out.println("接受信息: " + message);
    }
}
