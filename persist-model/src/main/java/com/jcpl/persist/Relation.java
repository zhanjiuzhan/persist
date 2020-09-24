package com.jcpl.persist;

/**
 * 链接关系
 * @author Administrator
 */
public class Relation {

    /**
     * 关系Id
     */
    private String relationId;

    /**
     * 用户名 (用户标志)
     */
    private String username;

    /**
     * 链接状态 0 等待链接  1 链接中
     */
    private int status;

    /**
     * 链接创建时间
     */
    private String createTime;

    public Relation() {}

    public Relation(String username) {
        this.relationId = JcUUIDUtils.generate32UUID();
        this.status = 0;
        this.createTime = JcDateUtils.getToDay();
        this.username = username;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(TYPE type) {
        this.status = type.getType();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "relationId='" + relationId + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public static enum TYPE {
        /**
         * 等待
         */
        WAITING(0),
        /**
         * 连接中
         */
        RUNNING(1);

        private int type;

        TYPE(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }
}
