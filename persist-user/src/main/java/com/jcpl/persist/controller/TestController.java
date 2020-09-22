package com.jcpl.persist.controller;

import com.jcpl.persist.Relation;
import com.jcpl.persist.RelationDao;
import com.jcpl.persist.view.JcJsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private RelationDao relationDaoRedisImpl;

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

    @GetMapping("/test3.do")
    public JcJsonView test3() {
        Relation relation = new Relation();
        relation.setStatus(1);
        relation.setCreateTime("aaaa");
        relation.setUsername("cl");
        relationDaoRedisImpl.addRelation(relation);
        return new JcJsonView(relationDaoRedisImpl.getRelation(relation.getUsername()));
    }
}
