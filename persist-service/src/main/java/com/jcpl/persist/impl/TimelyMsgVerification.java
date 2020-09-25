package com.jcpl.persist.impl;

import com.jcpl.persist.MessageType;
import com.jcpl.persist.MsgVerification;
import com.jcpl.persist.TimelyMsgFrom;
import com.jcpl.persist.exception.ExceptionEnum;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class TimelyMsgVerification extends MsgVerification<TimelyMsgFrom> {

    @Override
    public MessageType identification() {
        return MessageType.TIMELY_MSG;
    }

    @Override
    public void checkParameter(TimelyMsgFrom parameters) {
        System.out.println(parameters.toString());
        throw ExceptionEnum.INVALID_SAM_PAT_EXCEPTION.newException();
    }

    @Override
    public Class<TimelyMsgFrom> getClassType() {
        return TimelyMsgFrom.class;
    }
}
