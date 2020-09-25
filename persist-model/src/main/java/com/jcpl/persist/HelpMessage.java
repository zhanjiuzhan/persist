package com.jcpl.persist;

/**
 * 互助信息
 * @author chenglei
 */
public class HelpMessage implements Message {
    private static final long serialVersionUID = 5260784031699301164L;

    /**
     * 信息id 这个由数据库自动生成
     */
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
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
}
