package com.jcpl.persist.exception.exception;

/**
 * @author chenglei
 */
public interface ExResponse {
    /**
     * 取得错误码
     * @return
     */
    int getCode();

    /**
     * 取得错误信息
     * @return
     */
    String getMessage();

}
