package com.jcpl.persist.feign.impl;

import com.alibaba.fastjson.JSON;
import com.jcpl.persist.view.product.JsonView;
import com.jcpl.persist.view.product.RetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * @author Administrator
 */
public class FeignBaseServer {

    private Logger logger = LoggerFactory.getLogger(FeignBaseServer.class);

    private Object resolveResponse(String json) {
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

    boolean booleanResult(Supplier<String> supplier) {
        try {
            Boolean op = (Boolean) resolveResponse(supplier.get());
            if (op == null) {
                logger.warn("RPC 远程调用反馈结果为null！");
                return false;
            }
            return op;
        } catch (Exception e) {
            logger.warn("RPC 远程调用请求异常: " + e.getMessage());
            return false;
        }
    }
}
