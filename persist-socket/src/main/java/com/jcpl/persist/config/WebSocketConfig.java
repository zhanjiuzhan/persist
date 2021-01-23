package com.jcpl.persist.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author chenglei
 */
@RefreshScope
@Component
public class WebSocketConfig implements EnvironmentAware {
    private final String MAX_IDLE_TIMEOUT_KEY = "socket.session.maxIdleTimeOut";

    /**
     * websoket 最大空闲时间 毫秒 当该时间内无通信 session自动关闭
     */
    private long maxIdleTimeOut = 60000;

    public long getMaxIdleTimeOut() {
        return maxIdleTimeOut;
    }

    @Override
    public void setEnvironment(Environment env) {
        Optional.ofNullable(env).map(environment->environment.getProperty(MAX_IDLE_TIMEOUT_KEY))
            .ifPresent(val->maxIdleTimeOut = Long.parseLong(val));
    }
}
