package com.jcpl.persist;

/**
 * @author Administrator
 */
public abstract class MessageFrom<F, R> implements Form, Message {

    private static final long serialVersionUID = 5596994652468468482L;

    /**
     * 根据from信息转换成model信息
     * @param formObj
     * @return
     */
    public abstract R convert(F formObj);
}
