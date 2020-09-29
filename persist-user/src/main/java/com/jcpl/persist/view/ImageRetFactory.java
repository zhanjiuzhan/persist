package com.jcpl.persist.view;

import com.jcpl.persist.view.product.ImageView;

import java.awt.image.BufferedImage;

/**
 * @author Administrator
 */
public interface ImageRetFactory {

    /**
     * 返回一个视图
     * @param buf
     * @return
     */
    static ImageView getRet(BufferedImage buf) {
        if (buf == null) {
            throw new RuntimeException("参数错误!");
        }
        return new ImageView(buf);
    }

    /**
     * 返回一个视图
     * @param buf
     * @param type
     * @return
     */
    static ImageView getRet(BufferedImage buf, ImageView.Type type) {
        if (buf == null) {
            throw new RuntimeException("参数错误!");
        }
        return new ImageView(buf, type);
    }
}
