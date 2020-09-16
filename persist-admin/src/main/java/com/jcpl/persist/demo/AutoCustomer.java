package com.jcpl.persist.demo;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.MqConst;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@RabbitListener(queues = MqConst.HELP_MQ)
@Component
public class AutoCustomer {

    @RabbitHandler
    public void receive(HelpMessage message) throws Exception {
        System.out.println(message);
    }
}
