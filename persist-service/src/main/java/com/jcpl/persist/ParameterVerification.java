package com.jcpl.persist;


/**
 * 请求参数校验接口
 * @author Administrator
 */
public interface ParameterVerification<F> {
    /**
     * 请求参数校验
     * @param parameters
     */
    void validated(F parameters);

    /**
     * 请求参数校验
     * @param parameters
     * @return
     */
    F validated(String parameters);
}
