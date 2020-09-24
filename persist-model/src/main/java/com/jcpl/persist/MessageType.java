package com.jcpl.persist;

/**
 * @author Administrator
 */

public enum MessageType {
    /**
     * 信息类型
     */
    LOGIN_MSG(1, "建立连接"),
    LOGOUT_MSG(2, "断开连接"),
    INTERACTIVE_MSG(3, "信息交互"),
    HEART_MSG(0, "心跳信息")
    ;

    private int type;
    private String name;

    MessageType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static MessageType getType(int type) {
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
}
