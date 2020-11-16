package com.jcpl.persist.feign;

import com.jcpl.persist.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * account 项目的交互接口信息
 * @author Administrator
 */
@FeignClient(value = RemoteApplication.ACCOUNT_USER, configuration = FeignConfiguration.class)
public interface UserRemoteClient {

    /**
     * 发布自己的controller信息
     * @param permissions
     * @return
     */
    @PostMapping("/feign/upPermissions.do")
    String upPermissions(@RequestBody List<Map<String, String>> permissions);

    /**
     * 用户是否已经登陆了
     * @param username
     * @param token
     * @return
     */
    @GetMapping("/feign/isLogin.do")
    String isLogin(@RequestParam String username, @RequestParam String token);

    /**
     * 用户是否具有权限信息
     * @param username
     * @param token
     * @param project
     * @param url
     * @param method
     * @return
     */
    @GetMapping("/feign/isPermission.do")
    String isPermission(@RequestParam String username, @RequestParam String token, @RequestParam String project, @RequestParam String url, @RequestParam String method);
}
