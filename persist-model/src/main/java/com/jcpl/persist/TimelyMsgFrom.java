package com.jcpl.persist;

/**
 * @author Administrator
 */
public class TimelyMsgFrom extends MessageFrom<TimelyMsgFrom, HelpMessage> {
    /**
     * 信息类型
     */
    private int type;

    private String message;

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public HelpMessage convert(TimelyMsgFrom formObj) {
        return null;
    }

    @Override
    public String toString() {
        return "TimelyMsgFrom{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}
