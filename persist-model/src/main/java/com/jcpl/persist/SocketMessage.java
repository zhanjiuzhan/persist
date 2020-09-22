package com.jcpl.persist;

/**
 * @author Administrator
 */
public class SocketMessage {

    private int type;

    private String content;

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

    public static enum MessageType {
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

        private MessageType(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
