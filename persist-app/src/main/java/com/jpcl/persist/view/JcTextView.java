package com.jpcl.persist.view;

import com.jpcl.persist.view.model.TextRes;
import com.jpcl.persist.view.resolver.impl.JcTextViewResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenglei
 */
public class JcTextView  extends ModelAndView {
    private TextRes textRes;

    public JcTextView(String message) {
        this.textRes = new TextRes();
        textRes.setMessage(message);
        super.setView(new JcTextViewResolver(this.textRes));
    }
}
