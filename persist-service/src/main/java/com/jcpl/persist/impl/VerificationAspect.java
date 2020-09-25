package com.jcpl.persist.impl;

import com.jcpl.persist.Message;
import com.jcpl.persist.MessageType;
import com.jcpl.persist.ReqValidate;
import com.jcpl.persist.VerificationFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 参数校验切面
 * @author Administrator
 */
@Aspect
@Component
public class VerificationAspect {
    @Pointcut("@annotation(com.jcpl.persist.ReqValidate)")
    public void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        ReqValidate annotation = method.getAnnotation(ReqValidate.class);
        Class<? extends VerificationFactory> clazz = annotation.factory();
        try {
            VerificationFactory factory = clazz.newInstance();
            Object obj = args[0];
            if (obj == null) {
                return;
            }
            if (obj instanceof Message) {
                Message msg = (Message)obj;
                factory.newVerification(()->MessageType.get(msg.getType())).validated(msg);
            }
        } catch (IllegalAccessException | InstantiationException e ) {
            throw new RuntimeException("参数校验, 校验工厂出错");
        }
    }
}
