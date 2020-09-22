package com.jcpl.persist;

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
     * @param username
     * @return
     */
    Relation getRelation(String username);
}
