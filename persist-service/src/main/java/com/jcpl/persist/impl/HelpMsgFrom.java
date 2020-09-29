package com.jcpl.persist.impl;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.MessageFrom;
import com.jcpl.persist.exception.ExceptionEnum;

/**
 * @author Administrator
 */
public class HelpMsgFrom extends MessageFrom<HelpMessage> {

    private String message;

    @Override
    public HelpMessage convert() {
        System.out.println("进行转换:" + this.toString());
        return new HelpMessage();
    }

    @Override
    public void validated() {
        ExceptionEnum.INVALID_SAM_PAT_EXCEPTION.assertNotNull(null);
        System.out.println("进行校验" + this.toString());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelpMsgFrom{" +
                "message='" + message + '\'' +
                '}';
    }
}
