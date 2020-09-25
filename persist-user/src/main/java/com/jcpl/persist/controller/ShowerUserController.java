package com.jcpl.persist.controller;

import com.jcpl.persist.*;
import com.jcpl.persist.exception.ExceptionEnum;
import com.jcpl.persist.impl.MsgVerificationFactory;
import com.jcpl.persist.view.JcJsonView;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenglei
 */
@Controller
@RequestMapping("/shower/user/")
public class ShowerUserController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private SocketService socketService;

    /**
     * 用户注册自己的信息
     * @return
     */
    @PostMapping("/register.do")
    public JcJsonView register(User user) {
        // TODO 判断user信息的正确性
        // TODO 目前只用到username
        final String username = user.getUsername();
        ExceptionEnum.INVALID_SAM_PAT_EXCEPTION.assertNotNull(username);
        Relation relation = new Relation(username);
        boolean flag = relationService.createRelation(relation);
        if (flag) {
            return new JcJsonView(relation);
        }
        throw ExceptionEnum.OPERATION_EXCEPTION.newException("建立关系出错");
    }

    /**
     * 异常情况下 用户调用关闭链接进行关闭
     * @return
     */
    @PostMapping("/socket/close.do")
    public JcJsonView socketClose(String relationId) {
        if (JcStringUtils.isNotEmpty(relationId)) {
            socketService.closeConnect(relationId);
        }
        return new JcJsonView();
    }

    @PostMapping("/publish.do")
    @ReqValidate(factory = MsgVerificationFactory.class)
    public JcJsonView publishMessage(TimelyMsgFrom message) {
        messageService.sendMessage(message);
        return new JcJsonView();
    }
}
