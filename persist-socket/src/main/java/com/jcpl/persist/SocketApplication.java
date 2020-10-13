package com.jcpl.persist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * DataSourceAutoConfiguration mysql配置问题去除
 * RedisRepositoriesAutoConfiguration Multiple Spring Data modules found redis多数据源问题
 * @author chenglei
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@EnableScheduling
public class SocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocketApplication.class);
    }
}
