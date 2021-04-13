package com.liuyao.spcld.springbootstudy.listener;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class MySessionListener implements HttpSessionListener {

    public static final AtomicInteger COUNT = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session 创建");
        COUNT.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session 销毁");
        COUNT.decrementAndGet();
    }
}
