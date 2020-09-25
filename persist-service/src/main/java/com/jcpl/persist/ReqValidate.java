package com.jcpl.persist;

import java.lang.annotation.*;

/**
 * 自定义请求参数注解
 * @author Administrator
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReqValidate {
    Class<? extends VerificationFactory> factory();
}
