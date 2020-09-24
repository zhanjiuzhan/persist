package com.jcpl.persist.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class RelationRedisConf {
    @Value("${spring.relation-redis.database}")
    private int database;

    @Value("${spring.relation-redis.host}")
    private String host;

    @Value("${spring.relation-redis.port}")
    private int port;

    @Value("${spring.relation-redis.password}")
    private String password;

    /**
     * 最大连接数  0没有限制
     */
    @Value("${spring.relation-redis.lettuce.pool.max-active}")
    private int maxActive;

    /**
     * 最大等待的毫秒 超时报错 -1表示无限等待
     */
    @Value("${spring.relation-redis.lettuce.pool.max-wait}")
    private int maxWait;

    /**
     * 最大空闲数, 超过空闲时间，数据库连
     * 接将被标记为不可用，然后被释放。设为0表示无限制
     */
    @Value("${spring.relation-redis.lettuce.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.relation-redis.lettuce.pool.min-idle}")
    private int minIdle;

    public int getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }
}
