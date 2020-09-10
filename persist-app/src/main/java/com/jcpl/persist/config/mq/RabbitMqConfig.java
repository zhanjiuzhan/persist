package com.jcpl.persist.config.mq;


import com.jcpl.persist.MqConst;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenglei
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue helpMq() {
        return new Queue(MqConst.HELP_MQ);
    }
}
