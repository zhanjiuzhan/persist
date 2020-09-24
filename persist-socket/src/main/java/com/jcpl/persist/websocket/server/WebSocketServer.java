package com.jcpl.persist.websocket.server;

import com.jcpl.persist.Message;
import com.jcpl.persist.Publish;
import com.jcpl.persist.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author chenglei
 */
@Component
@ServerEndpoint("/websocket/shower")
public class WebSocketServer implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    private static SocketService socketService;

    /**
     * 建立连接成功调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        logger.info("接受到用户连接: " + session.getId() + " 链接成功!");
    }

    /**
     * 关闭链接调用
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        logger.info("用户: " + session.getId() + " 链接关闭!");
        try {
            if (session != null) {
                session.close();
            }
        } catch (Exception e) {}
    }

    /**
     * 收到信息调用
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(final String message, final Session session) {
        socketService.receiveMessage(message, session);
    }

    /**
     * 发生错误时调用
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("发生链接错误: " + throwable.getMessage());
        try {
            if (session != null) {
                session.close();
            }
        } catch (Exception e) {}
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebSocketServer.socketService = applicationContext.getBean(SocketService.class);
    }
}
