package com.jcpl.persist.mq;

import com.jcpl.persist.Message;
import com.jcpl.persist.MessageDao;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 发送一个信息到 mq中
 * @author chenglei
 */
@Repository
public class MessageDaoMqImpl implements MessageDao {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Override
    public <T extends Message> void sendMessage(String mqName, T message) {
        rabbitTemplate.convertAndSend(mqName, message);
    }
}
