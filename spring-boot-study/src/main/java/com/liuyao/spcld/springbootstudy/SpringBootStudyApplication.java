package com.liuyao.spcld.springbootstudy;

import com.liuyao.spcld.springbootstudy.listener.MySessionListener;
import com.liuyao.spcld.springbootstudy.servlet.MyWebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // 默认扫描当前类目录
//@ComponentScan("") // 启动类位置 需要修改扫描包
//@ServletComponentScan // 自动扫描 servlet
public class SpringBootStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

//    @Bean
//    public ServletRegistrationBean<MyWebServlet> getServletRegistrationBean() {
//        final ServletRegistrationBean<MyWebServlet> bean = new ServletRegistrationBean<>(new MyWebServlet(), "/srv2");
//        bean.setLoadOnStartup(1);
//        return bean;
//    }
//
//    @Bean
//    public ServletListenerRegistrationBean<MySessionListener> listenerRegistrationBean() {
//        final ServletListenerRegistrationBean<MySessionListener> bean = new ServletListenerRegistrationBean<>();
//        bean.setListener(new MySessionListener());
//        System.out.println("listener bean create");
//        return bean;
//    }
}
