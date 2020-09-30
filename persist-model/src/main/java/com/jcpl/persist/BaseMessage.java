package com.jcpl.persist;

import java.io.Serializable;

/**
 * 消息
 * @author chenglei
 */
public class BaseMessage implements Serializable {

    private long id;
    private int type;
    private int group;
    private int status;
    private double latitude;
    private double longitude;
    private String createTime;

    /**
     * 每创建一个HelpMessage其Id都是独一无二的
     */
    public BaseMessage() {
        this.id = JcUUIDUtils.generateLongUUID();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public enum Type {

        /**
         * 及时互助信息
         */
        TIMELY_MSG(1, "及时"),

        /**
         * 公益类的信息
         */
        WELFARE_MSG(2, "公益"),

        /**
         * 长时间的互助信息
         */
        LONG_TERM_MSG(3, "延时")
        ;
        private int type;
        private String name;

        Type(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public static Type get(int type) {
            for (Type obj : Type.values()) {
                if (obj.getType() == type) {
                    return obj;
                }
            }
            throw new RuntimeException("枚举范围有误");
        }

        public static boolean contains(int type) {
            for (Type obj : Type.values()) {
                if (obj.getType() == type) {
                    return true;
                }
            }
            return false;
        }

        public int getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }
}
