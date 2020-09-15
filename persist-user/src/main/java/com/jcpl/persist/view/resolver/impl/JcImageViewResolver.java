package com.jcpl.persist.view.resolver.impl;

import com.jcpl.persist.view.model.ImageRes;
import com.jcpl.persist.view.resolver.JcAbstractViewResolver;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @author chenglei
 */
public class JcImageViewResolver extends JcAbstractViewResolver {

    private ImageRes imageRes;

    public JcImageViewResolver(ImageRes imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    protected void makeResponse(HttpServletResponse response) throws Exception {

        OutputStream out = response.getOutputStream();
        ImageIO.write(this.imageRes.getImg(), this.imageRes.getType(), out);
    }

    @Override
    protected String getContentSpType() {
        if (this.imageRes.getType() == ImageRes.JPG) {
            return "image/jpeg";
        } else {
            // TODO
            return "";
        }
    }
}
