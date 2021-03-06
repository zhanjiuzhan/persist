package com.jcpl.persist.exception.exception;

import java.util.function.Supplier;

/**
 * 一个专门处理自定义异常的断言
 * @author chenglei
 */
public interface ExAssert {

    /**
     * 创建异常
     * @param args
     * @return
     */
    MyDefException newException(Object... args);

    /**
     * 创建异常
     * @param t
     * @param args
     * @return
     */
    MyDefException newException(Throwable t, Object... args);

    /**
     * 断言该对象不能为空对象
     * 特别的对于string 不能是空字符串
     * @param obj 判断的对象
     */
    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException();
        }
        if (obj instanceof String) {
            if (((String) obj).length() == 0) {
                throw newException();
            }
        }
    }

    /**
     * 断言该对象不能为空对象
     * 特别的对于string 不能是空字符串
     * 带参数
     * @param obj 判断的对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw newException(args);
        }
        if (obj instanceof String) {
            if (((String) obj).length() == 0) {
                throw newException(args);
            }
        }
    }

    /**
     * 断言是否True，是True则测试用例通过。
     * @param isTrue
     * @param args
     */
    default void assertTrue(boolean isTrue, Object... args) {
        if (!isTrue) {
            throw newException(args);
        }
    }

    /**
     * 断言是否True，是True则测试用例通过。
     * @param supplier
     * @param args
     */
    default void assertTrue(Supplier<Boolean> supplier, Object... args) {
        assertTrue(supplier.get(), args);
    }

    /**
     * 断言是否False，是False则测试用例通过。
     * @param isTrue
     * @param args
     */
    default void assertFalse(boolean isTrue, Object... args) {
        if (isTrue) {
            throw newException(args);
        }
    }
}
