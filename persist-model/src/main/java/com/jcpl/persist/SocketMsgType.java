package com.jcpl.persist;

/**
 * @author Administrator
 */

public enum SocketMsgType {
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

    SocketMsgType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static SocketMsgType get(int type) {
        for (SocketMsgType obj : SocketMsgType.values()) {
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
