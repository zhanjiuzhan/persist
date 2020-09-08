package com.jpcl.persist.controller;

import com.jpcl.persist.view.JcJsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test1.do")
    public JcJsonView test1() {
        Map<String, String> map = new HashMap<String, String>() {{
            put("name", "贾鹏君");
            put("description", "大大的优秀");
        }};
        return new JcJsonView(map);
    }

    @GetMapping("/test2.do")
    public JcJsonView test2() {
        return new JcJsonView(500);
    }
}
