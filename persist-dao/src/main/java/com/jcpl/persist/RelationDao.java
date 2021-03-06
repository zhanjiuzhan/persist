package com.jcpl.persist;

import java.util.Set;

/**
 * @author Administrator
 */
public interface RelationDao {

    /**
     * 添加一个关系
     * @param relation
     * @return
     */
    boolean addRelation(Relation relation);

    /**
     * 取得一个用户关系
     * @param relationId
     * @return
     */
    Relation getRelation(String relationId);

    /**
     * 刷新关系
     * @param relationId
     */
    void refreshRelation(String relationId);

    /**
     * 添加到在线用户表中
     * @param username
     * @return
     */
    boolean addOnlineUser(String username);

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
