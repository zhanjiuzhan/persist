package com.jcpl.persist.controller;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.Message;
import com.jcpl.persist.MessageService;
import com.jcpl.persist.MqConst;
import com.jcpl.persist.view.JcJsonView;
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

    @PostMapping("/publish.do")
    public JcJsonView publishMessage(HelpMessage message) {
        messageService.sendMessage(message);
        return new JcJsonView();
    }
}
