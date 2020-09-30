package com.jcpl.persist.impl;

import com.alibaba.fastjson.JSON;
import com.jcpl.persist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Administrator
 */
@Service
public class SocketServiceImpl implements SocketService, Publish, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SocketServiceImpl.class);
    private static final ConcurrentHashMap<String, DefineSession> sessionMap = new ConcurrentHashMap<>(16);
    private static final String SUCCESS_LOGIN = "200";
    private static final String SOCKET_NAME = "socketApplication";
    private boolean isSocketApplication = false;

    @Autowired
    private RelationService relationService;

    @Override
    public void openConnect(String relationId, Session session) {
        Relation relation = relationService.getRelation(relationId);
        if (relation != null && relation.getStatus() == Relation.TYPE.WAITING.getType()) {
            relation.setStatus(Relation.TYPE.RUNNING);
            // TODO 创建失败的可能
            // 添加到关系表中(自动过期) 覆盖之前的关系表 并修改了状态
            relationService.createRelation(relation);
            // 添加到在线表中 已存在的话不理会
            relationService.addOnlineUser(relation.getUsername());
            // 添加到内存关系表中 已存在的话替换值
            sessionMap.put(relation.getUsername(), new DefineSession(relationId, relation.getUsername(), session));
            // 发送登录成功的信息
            sendMessage(session, getLoginSuccessMsg());
        }
    }

    @Override
    public void closeConnect(final String relationId) {
        Optional.ofNullable(relationService.getRelation(relationId)).ifPresent((relation -> close(relation.getUsername())));
    }

    @Override
    public void sendMessage(Session session, String message) {
        if (session != null) {
            synchronized (session) {
                try {
                    logger.info("SessionId:" + session.getId() + "发送信息: " + message);
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.error("websocket服务信息发送失败: " + e.toString());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void receiveMessage(String message, Session session) {
        Optional.ofNullable(message).map((msg)-> JSON.parseObject(msg, SocketMessage.class))
            .ifPresent((objMsg)->{
                logger.info("接受到信息: " + objMsg);
                switch (SocketMessage.Type.get(objMsg.getType())) {
                    case LOGIN_MSG:
                        // content内容几位relationId
                        openConnect(objMsg.getContent(), session);
                        break;
                    case LOGOUT_MSG:
                        // content内容几位relationId
                        closeConnect(objMsg.getContent());
                        break;
                    case INTERACTIVE_MSG:
                        // TODO 目前不做交流
                        break;
                    case HEART_MSG:
                        heart(objMsg.getContent(), session.getId());
                        break;
                    default:
                        logger.error("位置消息类型");
                        break;
                }
            });
    }

    private void heart(final String content, final String sessionId) {
        Optional.ofNullable(content)
            .map(msg->JSON.parseObject(msg, Heartbeat.class))
            .map(heart->relationService.getRelation(heart.getRelationId()))
            .map(relation -> sessionMap.get(relation.getUsername()))
            .filter(session-> session.getSession().getId().equals(sessionId)).ifPresent(session->{
                Heartbeat heart = JSON.parseObject(content, Heartbeat.class);
                // 刷新维度经度
                session.setLatitude(heart.getLatitude());
                session.setLongitude(heart.getLongitude());
                // 刷新缓存关系时间
                relationService.refreshRelation(heart.getRelationId());
                // 发送心跳回馈
                sendMessage(session.getSession(), getHeartMsg(heart.getHeart()));
            });
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    private void closeInvalidSession() {
        if (isSocketApplication) {
            logger.info("每1分钟执行一次, 失效的session回收, 执行...");
            Set<String> onlineUsers = relationService.getAllOnlineUser();
            for (final String username : onlineUsers) {
                DefineSession session = sessionMap.get(username);
                if (session != null) {
                    if (relationService.getRelation(session.getRelationId()) == null) {
                        close(username);
                    }
                } else {
                    close(username);
                }
            }
        }
    }

    private void close(final String username) {
        // 在线表中移除
        relationService.removeOnlineUser(username);
        // 内存关系表中移除
        DefineSession session = sessionMap.remove(username);
        // 关闭session
        if (session != null && session.getSession() != null) {
            try {
                session.getSession().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getLoginSuccessMsg() {
        SocketMessage msg = new SocketMessage();
        msg.setType(SocketMessage.Type.LOGIN_MSG.getType());
        msg.setContent(SUCCESS_LOGIN);
        return JSON.toJSONString(msg);
    }

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
        for(DefineSession session : sessionMap.values()) {
            sendMessage(session.getSession(), JSON.toJSONString(message));
        }
        return true;
    }
}
