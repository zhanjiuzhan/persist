package com.jcpl.persist.impl.mysql.master;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.MessageDao;
import com.jcpl.persist.impl.mysql.slave.HelpMsgContentSlaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Repository
public class MessageDaoHelpMysqlImpl implements MessageDao<HelpMessage> {

    @Autowired(required=false)
    private HelpMsgContentMasterMapper helpMsgContentMasterMapper;

    @Autowired(required=false)
    private MsgHeaderMasterMapper msgHeaderMasterMapper;

    @Autowired(required=false)
    private HelpMsgContentSlaveMapper helpMsgContentSlaveMapper;

    @Override
    public void sendMessage(HelpMessage message) {
        if (msgHeaderMasterMapper != null && helpMsgContentMasterMapper != null) {
            msgHeaderMasterMapper.addMessage(message);
            helpMsgContentMasterMapper.addMessage(message);
        }
    }

    @Override
    public List<HelpMessage> gets() {
        if (helpMsgContentSlaveMapper != null) {
            return helpMsgContentSlaveMapper.gets();
        }
        return new ArrayList<>(0);
    }
}
