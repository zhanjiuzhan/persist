package com.jcpl.persist;

import java.util.Set;

/**
 * @author Administrator
 */
public interface RelationService {

    /**
     * 创建一个关系
     * @param relation
     * @return
     */
    boolean createRelation(Relation relation);

    /**
     * 添加到在线用户表中
     * @param username
     * @return
     */
    boolean addOnlineUser(String username);

    /**
     * 取到一个关系
     * @param relationId
     * @return
     */
    Relation getRelation(String relationId);

    /**
     * 刷新一个关系的生命周期
     * @param relationId
     */
    void refreshRelation(String relationId);

    /**
     * 从在线用户表中移除
     * @param username
     * @return
     */
    void removeOnlineUser(String username);

    /**
     * 取得所有在线的用户
     * @return
     */
    Set<String> getAllOnlineUser();
}
