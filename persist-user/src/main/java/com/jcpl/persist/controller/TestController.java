package com.jcpl.persist.controller;

import com.jcpl.persist.Relation;
import com.jcpl.persist.RelationDao;
import com.jcpl.persist.feign.UserRemoteClient;
import com.jcpl.persist.view.JsonRetFactory;
import com.jcpl.persist.view.product.JsonView;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/test")
@Configuration
public class TestController {

    @Resource
    private RelationDao relationDaoRedisImpl;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRemoteClient userRemoteClient;

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

    @GetMapping("/eureka.do")
    @ResponseBody
    public String eurekaTest() {
        List<ServiceInstance> list = discoveryClient.getInstances("account");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        return "ok";
    }

    @GetMapping("/ribbon.do")
    @ResponseBody
    public String ribbonRurekaTest() {
        List<ServiceInstance> list = discoveryClient.getInstances("account");
        // 取得服务列表
        List<Server> servers = Optional.ofNullable(list).orElse(Collections.emptyList()).stream()
                .map(serviceInstance -> new Server(serviceInstance.getHost(), serviceInstance.getPort()))
                .collect(Collectors.toList());

        // 构建负载实例
        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(servers);

        // 循环调用测试效果
        for (int i = 0; i < 5; i++) {
            String result = LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer)
                    .build().submit(server -> {
                        try {
                            String addr = "http://" + server.getHost() + ":" + server.getPort() + "/user/hello";
                            // url调用
                            return  Observable.just(addr);
                        } catch (Exception e) {
                            return Observable.error(e);
                        }
                    }).toBlocking().first();
            System.out.println(result);
        }
        return "ok";
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/ribbon2.do")
    @ResponseBody
    public String ribbonRurekaTest2() {
        return restTemplate.getForObject("http://account/admin/user/gets.do", String.class);
    }

    @GetMapping("/feign.do")
    @ResponseBody
    public String feignTest() {
        return "";
    }
}
