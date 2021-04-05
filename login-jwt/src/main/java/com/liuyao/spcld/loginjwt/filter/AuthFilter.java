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

    @Value("${server.port}")
    private Integer port;

    @Value("${auth.not.path}")
    private String nonAuth;



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
        while (uri.endsWith("/")) {
            uri = uri.substring(0, uri.length()-1);
        }

        // 排除静态资源
        for (int i = 0; i < this.staticSuffix.length; i++) {
            if (uri.endsWith(this.staticSuffix[i])) {
                chain.doFilter(request, response);
                return;
            }
        }

        String loginPath = path + this.nonAuth;

        // 校验token
        if (null != req.getCookies()) {
            for (Cookie ck : req.getCookies()) {
                if (!ck.getName().equals("token")) { continue; }

                String token = ck.getValue();
                // token 为空
                if (StringUtils.isEmpty(token)) { break; }
                // 解码token
                String s = JwtUtil.parseToken(token);
                // token 错误或过期
                if (StringUtils.isEmpty(s)) { break; }
                // 校验通过 但请求的是登录页
                if (loginPath.equals(uri)) {
                    resp.sendRedirect(path + "/");
                    return;
                }
                // 校验通过 跳转
                chain.doFilter(request, response);
                return;
            }
        }

        // 排除不需要认证的请求
        if (uri.startsWith(path + this.nonAuth)) {
            chain.doFilter(request, response);
            return;
        }

        // 跳转登录页
        resp.sendRedirect(loginPath);
    }
}
