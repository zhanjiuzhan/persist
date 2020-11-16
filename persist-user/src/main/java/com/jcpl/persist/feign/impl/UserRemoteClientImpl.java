package com.jcpl.persist.feign.impl;

import com.jcpl.persist.feign.UserRemoteClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public boolean upPermissions(final List<Map<String, String>> permissions) {
        return booleanResult(()->userRemoteClient.upPermissions(permissions));
    }

    /**
     * 用户是否已经登陆了
     * @param username
     * @param token
     * @return
     */
    public boolean isLogin(final String username, final String token) {
        return booleanResult(()->userRemoteClient.isLogin(username, token));
    }

    /**
     * 用户是否具有权限信息
     * @param username
     * @param token
     * @param project
     * @param url
     * @param method
     * @return
     */
    public boolean isPermission(final String username, final String token, final String project, final String url, final String method) {
        return booleanResult(()->userRemoteClient.isPermission(username, token, project, url, method));
    }
}
