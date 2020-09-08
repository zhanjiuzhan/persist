package com.jpcl.persist.view.resolver.impl;

import com.jpcl.persist.view.model.TextRes;
import com.jpcl.persist.view.resolver.JcAbstractViewResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * @author chenglei
 */
public class JcTextViewResolver  extends JcAbstractViewResolver {
    private TextRes textRes;

    public JcTextViewResolver(TextRes textRes) {
        this.textRes = textRes;
    }

    @Override
    protected void makeResponse(HttpServletResponse response) throws Exception {
        String res = textRes.getMessage();
        response.setContentLength(res.getBytes("UTF-8").length);
        Writer out = response.getWriter();
        out.write(res);
        out.flush();
    }

    @Override
    protected String getContentSpType() {
        return "text/plain; charset=UTF-8";
    }
}
