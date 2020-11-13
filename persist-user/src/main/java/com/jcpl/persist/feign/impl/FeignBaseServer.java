package com.jcpl.persist.feign.impl;

import com.alibaba.fastjson.JSON;
import com.jcpl.persist.view.product.JsonView;
import com.jcpl.persist.view.product.RetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class FeignBaseServer {

    private Logger logger = LoggerFactory.getLogger(FeignBaseServer.class);

    public Object resolveResponse(String json) {
        if (json == null) {
            return null;
        }

        JsonView.JsonRet jsonRet =  JSON.parseObject(json, JsonView.JsonRet.class);
        if (jsonRet.getCode() == RetUtils.SUCCESS_CODE) {
            // 请求成功的
            return jsonRet.getData();
        } else {
            // 请求失败了
            logger.error("远程RPC调用反馈失败: " + jsonRet.getMsg());
            return null;
        }
    }
}
