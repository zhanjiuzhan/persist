package com.jcpl.persist;

import com.alibaba.fastjson.JSONObject;

/**
 * 消息类型的校验器
 * @author Administrator
 */
public abstract class MsgVerification<F extends Form> implements ParameterVerification<F> {
    @Override
    public void validated(F parameters) {
        checkParameter(parameters);
    }

    @Override
    public F validated(String parameters) {
        F parametersObj = JSONObject.parseObject(parameters, getClassType());
        checkParameter(parametersObj);
        return parametersObj;
    }

    /**
     * 返回一个身份识别
     * @return
     */
    public abstract MessageType identification();

    /**
     * 取得转换类型
     * @return
     */
    public abstract Class<F> getClassType();

    /**
     * 参数校验
     * @param parameters
     */
    public abstract void checkParameter(F parameters);
}
