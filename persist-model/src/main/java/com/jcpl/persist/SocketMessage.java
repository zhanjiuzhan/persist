package com.jcpl.persist;

/**
 * @author Administrator
 */
public class SocketMessage {

    /**
     * 通讯具有的session id 由websocket框架生成
     */
    private String id;

    /**
     * 聊天的类型
     */
    private int type;

    /**
     * 发送的内容
     */
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SocketMessage{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }

    public enum Type {
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

        public int getType() {
            return type;
        }
    }
}
