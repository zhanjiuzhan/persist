package com.jcpl.persist.impl.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jcpl.persist.Relation;
import com.jcpl.persist.RelationDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Repository
public class RelationDaoRedisImpl implements RelationDao {

    @Resource
    private RedisTemplate<String, Relation> relationRedis;

    @Override
    public boolean addRelation(Relation relation) {
        return relationRedis.opsForValue().setIfAbsent(relation.getUsername(), relation);
    }

    @Override
    public Relation getRelation(String username) {
        return JSON.parseObject(String.valueOf(relationRedis.opsForValue().get(username)), Relation.class);
    }
}
