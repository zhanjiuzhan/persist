package com.jcpl.persist.impl.mysql.master;

import com.jcpl.persist.LogDao;
import com.jcpl.persist.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public class LogDaoMasterMysqlImpl implements LogDao {

    @Autowired(required = false)
    private LogDaoMasterMapper logDaoMasterMapper;

    @Override
    public boolean addLog(OperateLog operateLog) {
        return logDaoMasterMapper.addLog(operateLog);
    }
}
