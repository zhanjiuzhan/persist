package com.jcpl.persist.impl.mysql.master;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.MessageDao;
import com.jcpl.persist.impl.mysql.slave.HelpMsgContentSlaveMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@Repository
public class MessageDaoHelpMysqlImpl implements MessageDao<HelpMessage> {

    @Resource
    private HelpMsgContentMasterMapper helpMsgContentMasterMapper;

    @Resource
    private MsgHeaderMasterMapper msgHeaderMasterMapper;

    @Resource
    private HelpMsgContentSlaveMapper helpMsgContentSlaveMapper;

    @Override
    public void sendMessage(HelpMessage message) {
        msgHeaderMasterMapper.addMessage(message);
        helpMsgContentMasterMapper.addMessage(message);
    }

    @Override
    public List<HelpMessage> gets() {
        return helpMsgContentSlaveMapper.gets();
    }
}
