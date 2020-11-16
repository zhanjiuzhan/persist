package com.jcpl.persist.config;

import com.alibaba.fastjson.JSON;
import com.jcpl.persist.feign.impl.UserRemoteClientImpl;
import com.jcpl.persist.view.product.JsonView;
import com.jcpl.persist.view.product.RetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 添加过滤器 若有token信息 保存到request的属性中
 * @author Administrator
 */
@Component
public class AccessFilter extends OncePerRequestFilter {

    @Autowired
    private UserRemoteClientImpl userRemoteClient;

    @Value("${spring.application.name}")
    private String project;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) {
            httpServletRequest.setAttribute("token", token);
            // 有token了 那么就要身份校验下了 当携带token的时候带入一下username噢
            String username = httpServletRequest.getParameter("username");
            if (username != null && userRemoteClient.isPermission(username, token, project, httpServletRequest.getRequestURI(), httpServletRequest.getMethod())) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.setContentType(RetUtils.CONTENT_TYPE_JSON);
                String res = JSON.toJSONString(new JsonView.JsonRet(403, "用户没有权限！"));
                try (Writer out = httpServletResponse.getWriter();) {
                    httpServletResponse.setContentLength(res.getBytes(RetUtils.CHARACTER_CODE).length);
                    out.write(res);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
