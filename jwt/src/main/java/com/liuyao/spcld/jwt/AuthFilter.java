package com.liuyao.spcld.jwt;

import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/**")
@Component
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(" auth 启动成功");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            System.err.println("没登录");
        } else {
            String s = JwtUtil.parseToken(token);
            if (StringUtils.isEmpty(s)) {
                System.err.println("权限错误");
            } else {
                System.out.println("auth 成功");
                chain.doFilter(request, response);
            }
        }

    }
}
