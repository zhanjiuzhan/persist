package com.jcpl.persist.impl;

import com.jcpl.persist.MessageType;
import com.jcpl.persist.MsgVerification;
import com.jcpl.persist.VerificationFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 消息类型的参数校验工厂
 * @author Administrator
 */
@Component
public class MsgVerificationFactory implements VerificationFactory, ApplicationContextAware {

    /**
     * 默认大小为啥是16 因为一般为2的幂可以均匀分布 减少冲突 太大不行 太小不好 所以默认是16  这里8就可以了
     */
    private static HashMap<MessageType, MsgVerification> msgVerifications = new HashMap<>(8);

    @Override
    public MsgVerification newVerification(Supplier type) {
        return Optional.ofNullable(type.get()).map(t->msgVerifications.get(t)).orElseThrow(()->new RuntimeException("校验类型取得失败"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            Map<String, MsgVerification> verificationList = applicationContext.getBeansOfType(MsgVerification.class);
            for (MsgVerification msgVerification : verificationList.values()) {
                msgVerifications.put(msgVerification.identification(), msgVerification);
            }
        }
    }
}
