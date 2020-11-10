package com.jcpl.persist.config;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 相关配置
 * @author Administrator
 */
@Configuration
public class FeignConfiguration {

    /**
     * NONE 不输出日志
     * BASIC 只输出请求方法的URL 和响应状态码以及接口执行时间
     * HEADERS 将BASIC 信息和请求头信息输出
     * FULL 输出完整的请求信息
     * 其次需要在 log4j2.xml中进行配置
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 第一个是连接超时 第二个是取得结果超时
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

    @Bean
    public RequestInterceptor accountFeignInterceptor() {
        return requestTemplate -> {
            System.out.println("feign 自定义拦截: " + requestTemplate.queryLine());
        };
    }
}
