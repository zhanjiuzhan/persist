package com.jcpl.persist.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author Administrator
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.remove("timestamp");
        errorAttributes.remove("path");
        errorAttributes.remove("timestamp");
        errorAttributes.remove("error");
        errorAttributes.put("data", "");
        return errorAttributes;
    }
}
