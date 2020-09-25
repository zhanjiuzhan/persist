package com.jcpl.persist;

/**
 * @author Administrator
 */

public enum MessageType {

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

    MessageType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static MessageType get(int type) {
        for (MessageType obj : MessageType.values()) {
            if (obj.getType() == type) {
                return obj;
            }
        }
        throw new RuntimeException("枚举范围有误");
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
