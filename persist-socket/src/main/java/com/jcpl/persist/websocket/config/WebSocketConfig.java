package com.jcpl.persist.websocket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author chenglei
 */
@RefreshScope
@Component
public class WebSocketConfig {

    @Value("${socket.session.maxIdleTimeOut}")
    private long maxIdleTimeOut = 5000;

    public long getMaxIdleTimeOut() {
        return maxIdleTimeOut;
    }
}
