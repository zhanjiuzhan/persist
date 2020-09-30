package com.jcpl.persist;

/**
 * 互助信息
 * @author chenglei
 */
public class HelpMessage extends BaseMessage {
    /**
     * 信息的标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 详细的信息
     */
    private String detail;

    /**
     * 图片地址
     */
    private String imgPath;

    {
        detail = "";
        imgPath = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
