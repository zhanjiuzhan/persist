package com.jcpl.persist.impl;

import com.jcpl.persist.Message;
import com.jcpl.persist.MessageDao;
import com.jcpl.persist.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息服务实现
 * @author chenglei
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public <T extends Message> void sendMessage(String mqName, T message) {
        messageDao.sendMessage(mqName, message);
    }
}
