package com.jcpl.persist.controller;

import com.jcpl.persist.*;
import com.jcpl.persist.exception.ExceptionEnum;
import com.jcpl.persist.impl.HelpMsgFrom;
import com.jcpl.persist.reqlog.ControllerLog;
import com.jcpl.persist.view.JsonRetFactory;
import com.jcpl.persist.view.product.JsonView;
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
    public JsonView register(User user) {
        // TODO 判断user信息的正确性
        // TODO 目前只用到username
        final String username = user.getUsername();
        ExceptionEnum.INVALID_SAM_PAT_EXCEPTION.assertNotNull(username);
        Relation relation = new Relation(username);
        boolean flag = relationService.createRelation(relation);
        if (flag) {
            return JsonRetFactory.getRet(relation);
        }
        throw ExceptionEnum.OPERATION_EXCEPTION.newException("建立关系出错");
    }

    /**
     * 异常情况下 用户调用关闭链接进行关闭
     * @return
     */
    @PostMapping("/socket/close.do")
    public JsonView socketClose(String relationId) {
        if (JcStringUtils.isNotEmpty(relationId)) {
            socketService.closeConnect(relationId);
        }
        return JsonRetFactory.getRet();
    }

    @PostMapping("/publish.do")
    @ControllerLog
    public JsonView publishMessage(HelpMsgFrom message) {
        ExceptionEnum.INVALID_SAM_PAT_EXCEPTION.assertNotNull(null);
        //message.validated();
        //ExceptionEnum.INVALID_SAM_PAT_EXCEPTION.assertNotNull(null);messageService.sendMessage(message.convert());
        return JsonRetFactory.getRet();
    }
}
