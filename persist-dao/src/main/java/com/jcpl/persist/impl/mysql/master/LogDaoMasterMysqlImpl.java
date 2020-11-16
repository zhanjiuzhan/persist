package com.jcpl.persist.impl.mysql.master;

import com.jcpl.persist.LogDao;
import com.jcpl.persist.OperateLog;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Repository
public class LogDaoMasterMysqlImpl implements LogDao {

    @Resource
    private LogDaoMasterMapper logDaoMasterMapper;

    @Override
    public boolean addLog(OperateLog operateLog) {
        return logDaoMasterMapper.addLog(operateLog);
    }
}
