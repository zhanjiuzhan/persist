package com.jcpl.persist.impl.mysql.master;

import com.jcpl.persist.BaseMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface MsgHeaderMasterMapper {

    String TABLE_NAME = "message_header";

    /**
     * 添加一条数据
     * @param message
     * @return
     */
    @Insert("insert into " + TABLE_NAME + " values (#{id}, #{type}, #{group}, #{status}, #{latitude}, #{longitude}, now())")
    int addMessage(BaseMessage message);
}
