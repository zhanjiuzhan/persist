package com.jcpl.persist.impl.mysql.slave;

import com.jcpl.persist.HelpMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface HelpMsgContentSlaveMapper {
    String TABLE_NAME_HEADER = "message_header";
    String TABLE_NAME = "help_message_content";

    /**
     * 查询所有的求助信息
     * @return
     */
    @Select("select t1.id as id, t1.*, t2.* from " + TABLE_NAME + " as t1 left join " + TABLE_NAME_HEADER + " as t2 on t1.id = t2.id")
    List<HelpMessage> gets();
}
