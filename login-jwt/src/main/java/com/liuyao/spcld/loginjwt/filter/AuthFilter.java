package com.liuyao.spcld.loginjwt.filter;

import com.liuyao.spcld.loginjwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/**")
@Component
public class AuthFilter implements Filter {

    @Value("${static.source.suffix}")
    private String[] staticSuffix;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(" auth 启动成功");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getContextPath();
        String uri = req.getRequestURI();
        for (int i = 0; i < staticSuffix.length; i++) {
            if (uri.endsWith(staticSuffix[i])) {
                chain.doFilter(request, response);
                return;
            }
        }

        String token = null;
        for (Cookie ck: req.getCookies()) {
            if (ck.getName().equals("token")) {
                token = ck.getValue();
                break;
            }
        }
        if (uri.startsWith(path + "/nonlogin")) {
            chain.doFilter(request, response);
            return;
        }
        if (StringUtils.isEmpty(token)) {
            resp.sendRedirect(path + "/nonlogin/login");
        } else {
            String s = JwtUtil.parseToken(token);
            if (StringUtils.isEmpty(s)) {
                resp.sendRedirect(path + "/nonlogin/powerover");
            } else {
                chain.doFilter(request, response);
            }
        }

    }
}
