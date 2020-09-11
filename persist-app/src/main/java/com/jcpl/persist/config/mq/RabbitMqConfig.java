package com.jcpl.persist.config.mq;

import com.jcpl.persist.MqConst;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
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

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(RabbitMqConfig::confirmCallback);
        rabbitTemplate.setReturnCallback(RabbitMqConfig::returnCallback);
        return rabbitTemplate;
    }

    /**
     * 投递交换机之后的回调
     * @param correlationData 相关数据
     * @param ack 确认情况 投递是否成功
     * @param cause 原因
     */
    private static void confirmCallback(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
        System.out.println("ConfirmCallback:     "+"确认情况："+ack);
        System.out.println("ConfirmCallback:     "+"原因："+cause);
    }

    /**
     * 交换机投递mq的回调 投递交换机正常 投递队列异常的情况下才会回调
     * @param message 消息
     * @param replyCode 回应码 312: 没有队列
     * @param replyText 回应信息
     * @param exchange 交换机
     * @param routingKey 路由键
     */
    private static void returnCallback(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("ReturnCallback:     "+"消息："+message);
        System.out.println("ReturnCallback:     "+"回应码："+replyCode);
        System.out.println("ReturnCallback:     "+"回应信息："+replyText);
        System.out.println("ReturnCallback:     "+"交换机："+exchange);
        System.out.println("ReturnCallback:     "+"路由键："+routingKey);
    }
}
