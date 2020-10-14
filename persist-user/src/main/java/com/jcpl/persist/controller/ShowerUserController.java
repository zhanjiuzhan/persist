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

/**
 * @author chenglei
 */
@Controller
@RequestMapping("/shower/user/")
public class ShowerUserController {

    @Autowired
    private MessageService messageService;


    @PostMapping("/publish.do")
    @ControllerLog
    public JsonView publishMessage(HelpMsgFrom message) {
        message.validated();
        messageService.sendMessage(message.convert());
        return JsonRetFactory.getRet();
    }

    @GetMapping("/message/gets.do")
    public JsonView gets() {
        return JsonRetFactory.getRet(messageService.gets());
    }
}
