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

    @Override
    public String toString() {
        return "SocketMessage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
