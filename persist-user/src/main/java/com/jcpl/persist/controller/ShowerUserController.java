package com.jcpl.persist.controller;

import com.jcpl.persist.*;
import com.jcpl.persist.exception.ExceptionEnum;
import com.jcpl.persist.impl.HelpMsgFrom;
import com.jcpl.persist.reqlog.ControllerLog;
import com.jcpl.persist.view.JsonRetFactory;
import com.jcpl.persist.view.product.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenglei
 */
@Controller
@RequestMapping("/shower/user/")
public class ShowerUserController {

    @Autowired
    private MessageService messageService;

    /**
     * 发布一个互助信息
     * @param message
     * @return
     */
    @PostMapping("/publish.do")
    @ControllerLog
    public JsonView publishMessage(HelpMsgFrom message) {
        // 信息的字段校验
        message.validated();

        // 首先要进行信息的转接 然后 发送信息到mq中 保存信息到mysql中
        messageService.sendMessage(message.convert());

        return JsonRetFactory.getRet();
    }

    @GetMapping("/message/gets.do")
    public JsonView gets() {
        return JsonRetFactory.getRet(messageService.gets());
    }
}
