package com.jcpl.persist.impl.mysql.slave;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface MsgHeaderSlaveMapper {
    String TABLE_NAME = "message_header";
}
