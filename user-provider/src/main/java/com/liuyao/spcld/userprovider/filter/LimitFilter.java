package com.liuyao.spcld.userprovider.filter;

import com.google.common.util.concurrent.RateLimiter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LimitFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    // guawa 限流器
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 限流业务
        if (RATE_LIMITER.tryAcquire()) {
            chain.doFilter(request, response);
        } else {
            PrintWriter pw = null;
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            pw = response.getWriter();
            pw.write("当前访问量过高，请稍后再试。");
            pw.close();
        }
    }

    @Override
    public void destroy() {

    }
}
