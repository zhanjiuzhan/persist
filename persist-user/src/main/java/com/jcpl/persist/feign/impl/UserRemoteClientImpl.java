package com.jcpl.persist.feign.impl;

import com.jcpl.persist.feign.UserRemoteClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class UserRemoteClientImpl extends FeignBaseServer {

    private Logger logger = LoggerFactory.getLogger(UserRemoteClientImpl.class);

    @Autowired
    private UserRemoteClient userRemoteClient;

    /**
     * 发布自己的controller信息
     * @param permissions
     * @return
     */
    public boolean upPermissions(List<Map<String, String>> permissions) {
        try {
            Boolean op = (Boolean) resolveResponse(userRemoteClient.upPermissions(permissions));
            if (op == null) {
                logger.warn("RPC 远程调用反馈结果为null！");
                return false;
            }
            return op;
        } catch (Exception e) {
            logger.warn("RPC 远程调用请求异常: " + e.getMessage());
            return false;
        }
    }
}
