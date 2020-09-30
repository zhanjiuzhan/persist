package com.jcpl.persist.impl;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.Message;
import com.jcpl.persist.MessageFrom;
import com.jcpl.persist.exception.ExceptionEnum;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class HelpMsgFrom extends MessageFrom<HelpMessage> {

    /**
     * 信息类型
     */
    private int type;

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
     * 经度
     */
    private double latitude;

    /**
     * 维度
     */
    private double longitude;

    /**
     * 图片地址
     */
    private String[] imgPath;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String[] getImgPath() {
        return imgPath;
    }

    public void setImgPath(String[] imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public HelpMessage convert() {
        HelpMessage message = new HelpMessage();
        message.setContent(this.content);
        message.setDetail(this.detail);
        message.setImgPath(this.imgPath);
        message.setLatitude(this.latitude);
        message.setLongitude(this.getLongitude());
        message.setTitle(this.title);
        message.setType(this.type);
        return message;
    }

    @Override
    public void validated() {
        ExceptionEnum.INVALID_PARAMETER_EXCEPTION.assertTrue(Message.Type.contains(type), "消息类型错误", "type", type);
        ExceptionEnum.INVALID_PARAMETER_EXCEPTION.assertTrue(
            title != null && title.length() > 0 && title.length() <= 16, "标题不合法", "title", title);
        ExceptionEnum.INVALID_PARAMETER_EXCEPTION.assertTrue(
            content != null && content.length() > 0 && content.length() < 64, "信息内容不合法", "content", content);
        if (detail != null && detail.length() > 0) {
            ExceptionEnum.INVALID_PARAMETER_EXCEPTION.assertTrue(detail.length() < 256, "信息详细内容不合法", "detail", detail);
        }
        ExceptionEnum.INVALID_PARAMETER_EXCEPTION.assertTrue(latitude > 0, "经度信息错误", "经度", latitude);
        ExceptionEnum.INVALID_PARAMETER_EXCEPTION.assertTrue(longitude > 0, "维度信息错误", "经度", longitude);
    }

    @Override
    public String toString() {
        return "HelpMsgFrom{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", detail='" + detail + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", imgPath=" + Arrays.toString(imgPath) +
                "} " + super.toString();
    }
}
