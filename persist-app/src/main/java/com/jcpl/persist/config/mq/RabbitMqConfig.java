package com.jcpl.persist.config.mq;


import com.jcpl.persist.MqConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenglei
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 创建一个队列
     * @return
     */
    @Bean
    public Queue helpMq() {
        // durable: 是否持久化.  持久化: 存储在磁盘上, 消息代理再次重启仍然有效.  暂存队列: 当前链接有效
        // exclusive: 优先级高于durable, 只能被当前链接创建并使用, 链接断/关闭开则队列删除
        // autoDelete: 是否自动删除, 没有生产者消费者是自动删除
        return new Queue(MqConst.HELP_MQ);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MqConst.SHOWER_EXCHANGE);
    }

    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(helpMq()).to(directExchange()).with(MqConst.HELP_MQ);
    }
}
