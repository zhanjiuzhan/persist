package com.jcpl.persist.feign;

import com.jcpl.persist.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * account 项目的交互接口信息
 * @author Administrator
 */
@FeignClient(value = RemoteApplication.ACCOUNT, configuration = FeignConfiguration.class)
public interface UserRemoteClient {

    /**
     * 取得用户的所有的信息
     * @return
     */
    @GetMapping("/admin/user/gets.do")
    String gets(@RequestParam String auth);
}
