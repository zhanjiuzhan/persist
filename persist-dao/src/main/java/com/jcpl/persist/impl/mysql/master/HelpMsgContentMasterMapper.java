package com.jcpl.persist.impl.mysql.master;

import com.jcpl.persist.HelpMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface HelpMsgContentMasterMapper {

    String TABLE_NAME = "help_message_content";

    /**
     * 添加一条数据
     * @param message
     * @return
     */
    @Insert("insert into " + TABLE_NAME + " values (#{id}, #{title}, #{content}, #{detail}, #{imgPath})")
    int addMessage(HelpMessage message);
}
