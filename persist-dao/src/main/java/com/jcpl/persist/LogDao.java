package com.jcpl.persist;

/**
 * @author Administrator
 */
public interface LogDao {

    /**
     * 添加一个操作日志信息
     * @param operateLog
     * @return
     */
    boolean addLog(OperateLog operateLog);
}
