package com.jcpl.persist.websocket.server;

import com.alibaba.fastjson.JSON;
import com.jcpl.persist.SocketMessage;
import com.jcpl.persist.SocketService;
import com.jcpl.persist.SocketSession;
import com.jcpl.persist.config.WebSocketConfig;
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
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
        logger.info(" ***** 链接创建成功[ sessionId: " + session.getId() + ", 最大空闲时间: " + config.getMaxIdleTimeOut() + "]");
        // 设置空闲超时时间 单位毫秒 在该时间内session没有会话 那么会自动关闭链接
        // 并且会触发onclose动作
        session.setMaxIdleTimeout(config.getMaxIdleTimeOut());
        // 初始化操作
        socketService.init();
    }

    /**
     * 关闭链接调用
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        logger.info(" ***** 链接关闭[ sessionId: " + session.getId() + "]");
        destroyConnect(session);
    }

    /**
     * 收到信息调用
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(final String message, final Session session) {
        try {
            if (message == null || message.length() == 0 || session == null) {
                throw new Exception("空的message或者session无效!");
            }
            String msg = new String(Base64.getDecoder().decode(message), StandardCharsets.UTF_8);
            // 转换接受到的消息
            SocketMessage socketMessage = JSON.parseObject(msg, SocketMessage.class);
            if (socketMessage == null || session.getId().equals(socketMessage.getId())) {
                throw new Exception("空的message或者session 身份不正!");
            }
            // 包装自己的session
            SocketSession socketSession = new SocketSession(session);
            socketService.receiveMessage(socketMessage, socketSession);
        } catch (Exception e) {
            logger.error(" ***** 接受到的信息不和法, 疑似错误信息[msg: " + e.getMessage() + "]");
            destroyConnect(session);
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error(" ***** 链接异常[ sessionId: " + session.getId() + ", 错误信息: " + throwable.getMessage() + "]");
        destroyConnect(session);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info(" ***** 初始化socket服务...");
        WebSocketServer.socketService = applicationContext.getBean(SocketService.class);
        config = applicationContext.getBean(WebSocketConfig.class);
        if (WebSocketServer.socketService == null || config == null) {
            logger.error(" ***** socket服务初始化失败, 没有可用的socket服务.");
            throw new RuntimeException("Bean SocketService or WebSocketConfig not found.");
        }
        logger.info(" ***** socket服务初始化完成");
    }

    private void destroyConnect(Session session) {
        // 清理操作
        socketService.destroy(session);
    }
}
