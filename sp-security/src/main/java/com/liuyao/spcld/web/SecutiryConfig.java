package com.liuyao.spcld.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableWebSecurity
// prePostEnabled 方法并
// securedEnabled 方法且
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
        // 忽略静态请求 不需要登录
        //  ? 单字符
        //  * 0或任意数量字符
        //  ** 0或更多目录
        web.ignoring().antMatchers("/img/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                // 哪些地址需要登录
                .authorizeRequests()
                // 忽略静态请求 /img下的 不需要登录
//                .antMatchers("/img/**").permitAll()
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


    @Autowired
    DataSource dataSource;
    @Autowired
    MyUserDeatilsService userService;
    @Autowired
    MyUserAuth userAuth;

    // 权限认账
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);

        PasswordEncoder encod = new BCryptPasswordEncoder();

//        auth
//                // 缓存账号密码
//                .inMemoryAuthentication()
//                .withUser("123").password(encod.encode("123")).roles("admin")
//                .and()
//                .withUser("321").password(encod.encode("321")).roles("user")


//        // jdbc
//        JdbcUserDetailsManager manager = auth.jdbcAuthentication()
//                .dataSource(dataSource).getUserDetailsService();
//        if (manager.userExists("liuyao")) {
//            manager.createUser(User.withUsername("liuyao")
//                    .password(new BCryptPasswordEncoder().encode("111"))
//                    .roles("admin","xxoo").build());
//        }


        // 自定义 ORM
        auth.userDetailsService(userService)
        .and()
        .authenticationProvider(userAuth)


        ;
    }

//
//    // 配置这里 则不需要重写 configure(AuthenticationManagerBuilder auth)
//    @Bean
//    public UserDetailsService userDetailsService() {
//
////        // 基于内存存储用户 -------------------------------------------------------------------------
////        // UserDetails
////		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////
////		// 使用 用户名 找 user对象
//////		manager
//////		.loadUserByUsername("xx")
//////		.changePassword("123", "234");
////
////		User user = new User("liuyao", new BCryptPasswordEncoder().encode("111"),
////                true, true, true, true,
////                Collections.singletonList(new SimpleGrantedAuthority("admin")));
////		manager.createUser(user);
////		manager.createUser(User.withUsername("123").password(
////		        new BCryptPasswordEncoder().encode("123")).roles("user").build());
//
//
//        // 基于JDBC的 用户存储 --------------------------------------------------------------------------
//
//        // 默认建表语句 org.springframework.security.core.userdetails.jdbc.users.ddl
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        manager
//                .createUser(User.withUsername("liuyao")
//                        .password(new BCryptPasswordEncoder().encode("111"))
//                        .roles("admin","xxoo").build());
//
//        return manager;
//    }

    // 加密
    // hash(hash(hash(密码)+盐)+盐)...
    // sha1 - sha256 比较安全
    // 安全 加密+人机交互
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
