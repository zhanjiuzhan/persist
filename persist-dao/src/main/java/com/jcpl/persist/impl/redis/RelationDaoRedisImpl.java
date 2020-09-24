package com.jcpl.persist.impl.redis;

import com.jcpl.persist.Relation;
import com.jcpl.persist.RelationDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;

/**
 * @author Administrator
 */
@Repository
public class RelationDaoRedisImpl implements RelationDao {

    private static final String PREFIX = "relation_";
    private static final String ONLINE_PREFIX = "relation_online_user";
    private final int RELATION_EXPIRE = 300;

    @Resource(name="relationRedis")
    private RedisTemplate redis;

    private RedisUtils<String, Relation> relationRedis;
    private RedisUtils<String, String> stringRedis;

    @PostConstruct
    public void redisInit() {
        this.relationRedis = new RedisUtils<String, Relation>(redis);
        this.stringRedis = new RedisUtils<String, String>(redis);
        this.redis.delete(ONLINE_PREFIX);
    }

    @Override
    public boolean addRelation(Relation relation) {
        // 关系设置为60秒 没有心跳就会过期
        return relationRedis.set(getKey(relation.getRelationId()), relation, RELATION_EXPIRE);
    }

    @Override
    public Relation getRelation(String relationId) {
        return relationRedis.get(getKey(relationId), Relation.class);
    }

    @Override
    public void refreshRelation(String relationId) {
        relationRedis.expire(getKey(relationId), RELATION_EXPIRE);
    }

    @Override
    public boolean addOnlineUser(String username) {
        return stringRedis.zadd(ONLINE_PREFIX, username, System.currentTimeMillis());
    }

    @Override
    public void removeOnlineUser(String username) {
        stringRedis.zrem(ONLINE_PREFIX, username);
    }

    @Override
    public Set<String> getAllOnlineUser() {
        return stringRedis.zrangeAll(ONLINE_PREFIX);
    }

    private String getKey(String key) {
        return PREFIX + key;
    }
}
