package com.jcpl.persist;

import java.util.function.Supplier;

/**
 * 参数校验工厂
 * @author Administrator
 */
public interface VerificationFactory {

    /**
     * 创建一个请求参数校验器
     * @param type 根据类型
     * @return
     */
    ParameterVerification newVerification(Supplier type);
}
