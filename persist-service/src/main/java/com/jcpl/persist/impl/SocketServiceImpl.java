package com.jcpl.persist.impl;

import com.alibaba.fastjson.JSON;
import com.jcpl.persist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 */
@Service
public class SocketServiceImpl implements SocketService, Publish, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SocketServiceImpl.class);
    private static final ConcurrentHashMap<String, SocketSession> sessionMap = new ConcurrentHashMap<>(16);
    private static final String SUCCESS_LOGIN = "200";
    private static final String SOCKET_NAME = "socketApplication";
    private boolean isSocketApplication = false;

    @Override
    public void init() {
        // TODO 链接创建时的初始化操作
    }

    @Override
    public void destroy(Session session) {
        try {
            if (session != null) {
                session.close();
            }
        } catch (Exception e) {}
    }

    @Override
    public void receiveMessage(SocketMessage message, SocketSession session) {
         logger.info("接受到信息: " + message);
         switch (SocketMessage.Type.get(message.getType())) {
             case LOGIN_MSG:
                 // content内容为用户的标志信息
                 openConnect(message.getContent(), session);
                 break;
             case LOGOUT_MSG:
                 // content内容为用户的标志信息
                 closeConnect(message.getContent());
                 break;
             case INTERACTIVE_MSG:
                 // TODO 目前不做交流
                 break;
             case HEART_MSG:
                 heart(message.getContent(), session.getSession().getId());
                 break;
             default:
                 logger.error("未知消息类型!");
                 break;
         }

    }

    /**
     * 取得一个合法的user
     * @param content
     * @return
     */
    private User getValidUser(String content) {
        User user = null;
        if (!StringUtils.isEmpty(content)) {
            try {
                user = JSON.parseObject(content, User.class);
                if (user != null) {
                    // TODO 用户身份校验
                } else {
                    // 无效用户
                    user = null;
                }
            } catch (Exception e) {
                logger.error("user: " + content + ", 信息取得失败:" + e.getMessage());
            }
        }
        return user;
    }

    /**
     *  创建一个有效连接
     * @param content
     * @param session
     */
    private void openConnect(String content, SocketSession session) {
        User user = getValidUser(content);
        if (user != null) {
            // 1. 完善SocketSession 信息
            session.setUsername(user.getUsername());
            // 2. 添加信息到内存map中
            sessionMap.put(user.getUsername(), session);
            // 3. 返回登录成功的信息
            sendMessage(session, getLoginSuccessMsg());
        } else {
            // 无效用户 非法登录
            destroy(session.getSession());
        }
    }

    /**
     * 发送信息
     * @param session
     * @param message
     */
    private void sendMessage(SocketSession session, String message) {
        if (session != null && session.getSession() != null) {
            synchronized (session.getSession()) {
                try {
                    logger.info("SessionId:" + session.getSession().getId() + "发送信息: " + message);
                    session.getSession().getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.error("websocket服务信息发送失败: " + e.toString());
                }
            }
        }
    }

    /**
     * 心跳信息处理
     * @param content
     * @param sessionId
     */
    private void heart(final String content, final String sessionId) {
        Optional.ofNullable(content)
            .map(msg->JSON.parseObject(msg, SocketHeartbeat.class))
            .map(heartbeat -> heartbeat.getUserId())
            .map(userId->sessionMap.get(userId))
            .filter(session-> session.getSession().getId().equals(sessionId)).ifPresent(session->{
                SocketHeartbeat heart = JSON.parseObject(content, SocketHeartbeat.class);
                // 刷新维度经度
                session.setLatitude(heart.getLatitude());
                session.setLongitude(heart.getLongitude());
                // 发送心跳回馈
                sendMessage(session, getHeartMsg(heart.getHeart()));
            });
    }

    /**
     * 关闭信息
     * @param content
     */
    public void closeConnect(final String content) {
        User user = getValidUser(content);
        if (user != null) {
            // 有效的用户信息
            // 内存关系表中移除
            SocketSession session = sessionMap.remove(user.getUsername());
            // 关闭session
            destroy(session.getSession());
        }
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    private void closeInvalidSession() {
        // TODO currentmap 的迭代删除
        if (isSocketApplication) {
            logger.info("每1分钟执行一次, 失效的session回收, 执行...");
            for (Map.Entry<String, SocketSession> tmp : sessionMap.entrySet()) {
                SocketSession session = tmp.getValue();
                if (session == null || session.getSession() == null || !session.getSession().isOpen() ) {
                    sessionMap.remove(tmp.getKey());
                }
            }
        }
    }

    /**
     * 登录成功的信息
     * @return
     */
    private String getLoginSuccessMsg() {
        SocketMessage msg = new SocketMessage();
        msg.setType(SocketMessage.Type.LOGIN_MSG.getType());
        msg.setContent(SUCCESS_LOGIN);
        return JSON.toJSONString(msg);
    }

    /**
     * 心跳信息
     * @param content
     * @return
     */
    private String getHeartMsg(int content) {
        SocketMessage msg = new SocketMessage();
        msg.setType(SocketMessage.Type.HEART_MSG.getType());
        msg.setContent((++content) + "");
        return JSON.toJSONString(msg);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (applicationContext != null && applicationContext.containsBean(SOCKET_NAME)) {
            isSocketApplication = true;
        }
    }

    @Override
    public boolean publish(BaseMessage message) {
        if (sessionMap.size() == 0) {
            return false;
        }
        for(SocketSession session : sessionMap.values()) {
            // TODO 条件发送
            sendMessage(session, JSON.toJSONString(message));
        }
        return true;
    }
}
