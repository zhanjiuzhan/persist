package com.jcpl.persist.impl.mysql.master;

import com.jcpl.persist.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface LogDaoMasterMapper {

    String TABLE_NAME = "op_log";

    /**
     * 添加一个log日志
     * @param operateLog
     * @return
     */
    @Insert("insert into " + TABLE_NAME + " values (#{id}, #{username}, #{op}, #{detail}, #{res}, now())")
    boolean addLog(OperateLog operateLog);
}
