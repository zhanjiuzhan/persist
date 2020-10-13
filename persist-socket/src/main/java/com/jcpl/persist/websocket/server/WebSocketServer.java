package com.jcpl.persist.websocket.server;

import com.jcpl.persist.SocketService;
import com.jcpl.persist.websocket.config.WebSocketConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * webSocketServer 不是单例的 是多例的
 * @author chenglei
 */
@Component
@ConditionalOnBean(ServerEndpointExporter.class)
@ServerEndpoint("/websocket/shower")
public class WebSocketServer implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    private static WebSocketConfig config;
    private static SocketService socketService;

    /**
     * 建立连接成功调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        logger.info(" ***** 链接创建成功[ sessionId : " + session.getId() + " 最大空闲时间: " + config.getMaxIdleTimeOut() + "]");
        // 设置空闲超时时间 单位毫秒 在该时间内session没有会话 那么会自动关闭链接
        // 并且会触发onclose动作
        session.setMaxIdleTimeout(config.getMaxIdleTimeOut());
        // TODO 初始化操作
    }

    /**
     * 关闭链接调用
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        logger.info(" ***** 链接关闭[ sessionId : " + session.getId() + "]");
        // TODO 清理操作
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
        logger.info(" ***** 初始化socket服务...");
        WebSocketServer.socketService = applicationContext.getBean(SocketService.class);
        config = applicationContext.getBean(WebSocketConfig.class);
        if (WebSocketServer.socketService == null || config == null) {
            logger.info(" ***** socket服务初始化失败, 没有可用的socket服务.");
            throw new RuntimeException("Bean SocketService or WebSocketConfig not found.");
        }
        logger.info(" ***** socket服务初始化完成");
    }
}
