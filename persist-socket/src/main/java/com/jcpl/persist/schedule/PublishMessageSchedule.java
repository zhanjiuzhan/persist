package com.jcpl.persist.schedule;

import com.jcpl.persist.Message;
import com.jcpl.persist.SocketService;
import com.jcpl.persist.websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PublishMessageSchedule {

    private final static Logger logger = LoggerFactory.getLogger(PublishMessageSchedule.class);

    @Autowired
    private WebSocketServer webSocketServer;

    @Scheduled(fixedDelay = 100)
    public void execute() {
        try {
            Message message = SocketService.queue.take();
            if (message != null) {
                logger.info("消费信息: " + message.getContent());
                for (String sessionId : WebSocketServer.sessionPools.keySet()) {
                    webSocketServer.sendMessage(WebSocketServer.sessionPools.get(sessionId), message.getContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
