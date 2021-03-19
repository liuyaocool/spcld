package com.liuyao.spcld.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                // 哪些地址需要登录
                .authorizeRequests()
                // 所有请求都需要验证
                .anyRequest().authenticated()

                .and()
                // 自定义登录页面
                .formLogin().loginPage("/loginPage")
                // 配置登录页的表单名
                .loginProcessingUrl("/login")
                .usernameParameter("xx")
                .passwordParameter("oo")
                // 登录失败页面
                .failureForwardUrl("/login?error")
                // 简单：登录成功 跳转页面
                .defaultSuccessUrl("/") // 跳转登录之前的页面
//                .defaultSuccessUrl("/page", true) // 强制指定页面
                // 分权限展示页面
                // 。。。
                // 给没登陆的用户可以访问这个地址的权限
                .permitAll()
                // 登录 异常处理
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        // 根据不同异常 跳转页面
                        req.getRequestDispatcher("/error").forward(req, resp);

                        // 记录登录失败次数
                    }
                })

//                .and()
//                .antMatchers("").denyAll()


                //  csrf 默认 session token
                .and()
                .csrf()
//                 .disable() // 关闭csrf token
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())




                ;

    }

    // 高并发 session会过大 使用jwt

    // 权限认账
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);

        PasswordEncoder encod = new BCryptPasswordEncoder();
        // 两次加密结果不同
        System.out.println(encod.encode("123"));
        System.out.println(encod.encode("123"));
        auth
                .inMemoryAuthentication()
                .withUser("123").password(encod.encode("123")).roles("admin")

                .and()
                .withUser("321").password(encod.encode("321")).roles("user")


                ;

    }

    // 加密
    // sha1 - sha256 比较安全
    // 安全 加密+人机交互
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
