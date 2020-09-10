package com.jcpl.persist.view;

import com.jcpl.persist.view.model.ImageRes;
import com.jcpl.persist.view.resolver.impl.JcImageViewResolver;
import org.springframework.web.servlet.ModelAndView;

import java.awt.image.BufferedImage;

/**
 * @author chenglei
 */
public class JcImageView extends ModelAndView {

    private ImageRes imageRes;

    public JcImageView(BufferedImage buf) throws Exception {
        this(buf, ImageRes.JPG);
    }

    public JcImageView(BufferedImage buf, String type) throws Exception {
        this.imageRes = new ImageRes(buf, type);
        super.setView(new JcImageViewResolver(this.imageRes));
    }
}
