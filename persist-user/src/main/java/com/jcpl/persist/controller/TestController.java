package com.jcpl.persist.controller;

import com.jcpl.persist.Relation;
import com.jcpl.persist.RelationDao;
import com.jcpl.persist.exception.ExceptionEnum;
import com.jcpl.persist.view.JsonRetFactory;
import com.jcpl.persist.view.product.JsonView;
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
    public JsonView test1() {
        Map<String, String> map = new HashMap<String, String>() {{
            put("name", "贾鹏君");
            put("description", "大大的优秀");
        }};
        return JsonRetFactory.getRet(map);
    }

    @GetMapping("/test2.do")
    public JsonView test2() {
        return JsonRetFactory.getRet(500);
    }

    @GetMapping("/test3.do")
    public JsonView test3() {
        Relation relation = new Relation("dw_chenglei");
        relationDaoRedisImpl.addRelation(relation);
        return JsonRetFactory.getRet(relationDaoRedisImpl.getRelation(relation.getRelationId()));
    }
}
