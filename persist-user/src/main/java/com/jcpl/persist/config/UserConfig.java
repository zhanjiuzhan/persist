package com.jcpl.persist.config;

import com.jcpl.persist.feign.impl.UserRemoteClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * @author Administrator
 */
@Configuration
public class UserConfig implements ApplicationContextAware, SmartInitializingSingleton {

    private final static Logger logger = LoggerFactory.getLogger(UserConfig.class);

    @Value("${spring.application.name}")
    private String projectName;

    private ApplicationContext context;

    @Autowired
    private UserRemoteClientImpl userRemoteClientImpl;

    /**
     * 所有的Bean加载完成后的回调
     * 主要用于把所有的Controller的url放置到权限表中 更好的去控制权限
     */
    @Override
    public void afterSingletonsInstantiated() {
        RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<Map<String, String>> permissions = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()){
            // 获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();

            // 获取请求方式 Get,Post等等
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            for (String url : patterns){
                // 系统路径和 测试路径均不会加入
                if ("/error".equals(url) || url.contains("/test")) {
                    continue;
                }
                Map<String, String> tmp = new HashMap<>(3);
                tmp.put("project", projectName);
                tmp.put("url", url);
                tmp.put("method", methods.size() > 0 ? methods.iterator().next().toString() : "");
                permissions.add(tmp);
            }
        }
        upPermissionsToDb(permissions);
    }

    /**
     * 将权限信息更新到DB 中, 项目启动时会做这一步
     * @param permissions
     */
    private void upPermissionsToDb(List<Map<String, String>> permissions) {
        if (permissions.size() > 0) {
            if (userRemoteClientImpl.upPermissions(permissions)) {
                logger.info("项目权限初始化完成!");
            } else {
                logger.info("项目权限初始化失败!");
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
