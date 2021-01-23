package com.jcpl.persist.impl;

import com.jcpl.persist.*;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息服务实现
 * @author chenglei
 */
@Service
public class MessageServiceHelpMqImpl implements MessageService<HelpMessage>, ChannelAwareMessageListener, ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(MessageServiceHelpMqImpl.class);
    private Map<String, Publish> publishMap = new HashMap<>(1);

    @Resource(name = "messageDaoHelpMqImpl")
    private MessageDao<HelpMessage> messageMq;

    @Resource(name = "messageDaoHelpMysqlImpl")
    private MessageDao<HelpMessage> messageMysql;

    @Autowired
    private LogDao logDao;

    @Override
    @Transactional(value=MysqlConst.MASTER_TRANSACTION_MANAGER, rollbackFor=Exception.class)
    public void sendMessage(HelpMessage message) {
        boolean flag = false;
        try {
            messageMysql.sendMessage(message);
            messageMq.sendMessage(message);
            logger.info("成功发送信息: " + message.toString());
            flag = true;
        } catch (Exception e) {
            logger.error("发送信息失败, 将进行回滚: " + message.toString());
            throw e;
        } finally {
            // 添加用户的操作信息
            logDao.addLog(new OperateLog().setOp(OperateLog.Op.SEND_MSG.getIndex())
                .setDetail(message.toString()).setUsername(message.getUsername())
                .setRes(flag ? 1 : 0));
        }
    }

    @Override
    public boolean receiveMessage(final BaseMessage msg) {
        boolean flag = false;
        for (Publish publish : publishMap.values()) {
            flag = publish.publish(msg);
        }
        return flag;
    }

    @Override
    public List<HelpMessage> gets() {
        return messageMysql.gets();
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            HelpMessage msg = (HelpMessage) new SimpleMessageConverter().fromMessage(message);
            if (receiveMessage(msg)) {
                channel.basicAck(deliveryTag, false);
            } else {
                channel.basicReject(deliveryTag, true);
            }
        } catch (Exception e) {
            // 消费失败信息应该被记录 TODO
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        publishMap = applicationContext.getBeansOfType(Publish.class);
    }
}
