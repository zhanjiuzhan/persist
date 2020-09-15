package com.jcpl.persist.controller;

import com.jcpl.persist.HelpMessage;
import com.jcpl.persist.MessageService;
import com.jcpl.persist.exception.ExceptionEnum;
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
    public JcJsonView publishMessage(String message) {
        ExceptionEnum.INVALID_SAM_PAT_EXCEPTION.assertNotNull(message);
        messageService.sendMessage(new HelpMessage(message));
        return new JcJsonView();
    }
}
