package com.jcpl.persist.websocket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenglei
 */
@ServerEndpoint("/websocket/shower")
@Component
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    /**
     * 发送信息
     * @param session
     * @param message
     * @throws IOException
     */
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
                logger.info("发送信息: " + message);
                session.getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 建立连接成功调用
     * @param session
     * @param userName
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName){
        logger.info("链接创建成功");
    }

    /**
     * 关闭链接调用
     * @param userName
     */
    @OnClose
    public void onClose(@PathParam(value = "sid") String userName){
        logger.info("链接创建断开");
    }

    /**
     * 收到信息调用
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message) {
        logger.info("接受到信息: " + message);
    }

    /**
     * 发生错误时调用
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable){
        logger.error("发生链接错误: " + throwable.getMessage());
    }
}
