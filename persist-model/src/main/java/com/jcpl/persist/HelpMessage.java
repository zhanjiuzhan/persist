package com.jcpl.persist;

/**
 * 互助信息
 * @author chenglei
 */
public class HelpMessage implements Message {

    private static final long serialVersionUID = 7411133030625659095L;

    private String content;

    public HelpMessage() {}

    public HelpMessage(String content) {
        this.content = content;
    }

    public HelpMessage setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String getContent() {
        return this.content;
    }
}
