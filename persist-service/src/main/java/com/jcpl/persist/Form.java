package com.jcpl.persist;

/**
 * @author Administrator
 */
public interface Form<R> {
    /**
     * 根据from信息转换成model信息
     * @return
     */
    R convert();
}
