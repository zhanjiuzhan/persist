package com.jcpl.persist.reqlog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Administrator
 */
@Aspect
@Component
public class ControllerLogAspect {
    private final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("@annotation(com.jcpl.persist.reqlog.ControllerLog)")
    public void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        //String[] paths = annotation.value();
        Object[] args = joinPoint.getArgs();
        //logger.info("请求路径为: " + StringUtils.join(paths));
        StringBuilder par = new StringBuilder();
        for (Object tmp : args) {
            par.append(tmp.toString());
            par.append(", ");
        }
        logger.info("请求参数为: " + par.substring(0, par.length() > 0 ? par.length() - 1 : 0));
    }
}
