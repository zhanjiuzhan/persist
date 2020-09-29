package com.jcpl.persist.exception;

import com.jcpl.persist.exception.exception.MyDefException;
import com.jcpl.persist.view.JsonRetFactory;
import com.jcpl.persist.view.product.JsonView;
import com.jcpl.persist.view.product.RetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author chenglei
 */
@Component
@ControllerAdvice
@ConditionalOnWebApplication
@ConditionalOnMissingBean(MyDefineExceptionHandler.class)
public class MyDefineExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(MyDefineExceptionHandler.class);

    /**
     * 业务异常
     * @param ex 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MyDefException.class)
    public JsonView handleMySelfException(MyDefException ex) {
        JsonRes res = new JsonRes();
        switch (ex.getExceptionEnum()) {
            case OPERATION_EXCEPTION:
                return exeOperationException(res, ex);
            case AUTHORIZATION_EXCEPTION:
            case INVALID_PARAMETER_EXCEPTION:
                return exeInvalidParameterException(res, ex);
            case INTERNAL_INVALID_PARAMETER_EXCEPTION:
                return exeInternalInvalidParameterException(res, ex);
            default:
                logger.error("未知类型异常");
                return JsonRetFactory.getRet(500);
        }
    }

    private JsonView exeOperationException(JsonRes res, MyDefException ex) {
        res.setStatus(ex.getCode());
        res.setMsg(ex.getMessage());
        logger.error(ex.getMessage());
        return JsonRetFactory.getRet(res);
    }

    private JsonView exeInvalidParameterException(JsonRes res, MyDefException ex) {
        res.setStatus(ex.getCode());
        res.setMsg(ex.getMessage());
        logger.error("外部调用接口传递参数异常: " + ex.getMessage());
        return JsonRetFactory.getRet(res);
    }

    private JsonView exeInternalInvalidParameterException(JsonRes res, MyDefException ex) {
        res.setStatus(ex.getCode());
        res.setMsg(RetUtils.ERROR_MSG);
        logger.error("内部调用接口传递参数异常: " + ex.getMessage());
        ex.printStackTrace();
        return JsonRetFactory.getRet(res);
    }
}
