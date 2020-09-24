package com.jcpl.persist.impl;

import com.jcpl.persist.Relation;
import com.jcpl.persist.RelationDao;
import com.jcpl.persist.RelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author Administrator
 */
@Service
public class RelationServiceImpl implements RelationService {

    @Resource(name="relationDaoRedisImpl")
    private RelationDao relationDao;

    @Override
    public boolean createRelation(Relation relation) {
        return relationDao.addRelation(relation);
    }

    @Override
    public boolean addOnlineUser(String username) {
        return relationDao.addOnlineUser(username);
    }

    @Override
    public Relation getRelation(String relationId) {
        return relationDao.getRelation(relationId);
    }

    @Override
    public void refreshRelation(String relationId) {
        relationDao.refreshRelation(relationId);
    }

    @Override
    public void removeOnlineUser(String username) {
        relationDao.removeOnlineUser(username);
    }

    @Override
    public Set<String> getAllOnlineUser() {
        return relationDao.getAllOnlineUser();
    }
}
