package com.jcpl.persist.websocket.server;

import com.alibaba.fastjson.JSON;
import com.jcpl.persist.Message;
import com.jcpl.persist.Publish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenglei
 */
@ServerEndpoint("/websocket/shower")
@Component
public class WebSocketServer implements Publish {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    public static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    @Override
    public void publish(Message message) {
        for(Session session : sessionPools.values()) {
            sendMessage(session, JSON.toJSONString(message));
        }
    }

    /**
     * 发送信息
     * @param session
     * @param message
     * @throws IOException
     */
    public void sendMessage(Session session, String message) {
        if(session != null){
            synchronized (session) {
                try {
                    logger.info("用户:" + session.getId() + "发送信息: " + message);
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.error("websocket服务信息发送失败: " + e.toString());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 建立连接成功调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        logger.info("用户: " + session.getId() + " 链接成功!");
        sessionPools.put(session.getId(), session);
    }

    /**
     * 关闭链接调用
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        logger.info("用户: " + session.getId() + " 链接关闭!");
        sessionPools.remove(session.getId());
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
